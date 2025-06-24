package com.erp.PtMaterialLoanMaster.Service;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.PtMaterialLoanMaster.Model.CPtMaterialLoanDetailsModel;
import com.erp.PtMaterialLoanMaster.Repository.IPtMaterialLoanDetailsRepository;
import com.erp.PtMaterialLoanMaster.Repository.IPtMaterialLoanDetailsViewRepository;
import com.erp.PtMaterialLoanMaster.Repository.IPtvMaterialLoanMasterSummaryRepository;
import com.erp.SmProductRmStockDetails.Controller.CSmProductRmStockDetailsController;
import com.erp.SmProductRmStockDetails.Model.CSmProductRmStockDetailsModel;
import com.erp.SmProductRmStockDetails.Model.CSmProductRmStockSummaryModel;
import com.erp.SmProductRmStockDetails.Model.CSmProductStockTracking;
import com.erp.SmProductRmStockDetails.Repository.ISmProductRmStockDetailsRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.erp.PtMaterialLoanMaster.Model.CPtMaterialLoanMasterModel;
import com.erp.PtMaterialLoanMaster.Repository.IPtMaterialLoanMasterRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CPtMaterialLoanMasterServiceImpl implements IPtMaterialLoanMasterService {

    @Autowired
    CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;
    @Autowired
    IPtMaterialLoanMasterRepository iPtMaterialLoanMasterRepository;

    @Autowired
    IPtvMaterialLoanMasterSummaryRepository iPtvMaterialLoanMasterSummaryRepository;

    @Autowired
    IPtMaterialLoanDetailsRepository iPtMaterialLoanDetailsRepository;

    @Autowired
    IPtMaterialLoanDetailsViewRepository iPtMaterialLoanDetailsViewRepository;

    @Autowired
    ISmProductRmStockDetailsRepository iSmProductRmStockDetailsRepository;

    @Autowired
    CSmProductRmStockDetailsController cSmProductRmStockDetailsController;

    @Override
    @Transactional
    public Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject, String addOrIssued) {
        Map<String, Object> responce = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();

        JSONObject commonIdsObj = (JSONObject) jsonObject.get("commonIds");
        int company_id = commonIdsObj.getInt("company_id");
//        int company_branch_id = commonIdsObj.getInt("company_branch_id");
        String loan_no = commonIdsObj.getString("loan_no");
        String financial_year = commonIdsObj.getString("financial_year");

        JSONObject masterjson = jsonObject.getJSONObject("TransHeaderData");
        JSONArray detailsArray = (JSONArray) jsonObject.get("TransDetailData");
        boolean update = false;
        try {
            CPtMaterialLoanMasterModel jsonModel = objectMapper.readValue(masterjson.toString(),
                    CPtMaterialLoanMasterModel.class);

//            CPtMaterialLoanMasterModel getExistingRecord = iPtMaterialLoanMasterRepository.getExistingRecord(
//                    loan_no, jsonModel.getLoan_version(), financial_year, company_id);
//
//            if (getExistingRecord != null) {
//                getExistingRecord.setDeleted_by(jsonModel.getCreated_by());
//                getExistingRecord.setDeleted_on(new Date());
//                getExistingRecord.set_delete(true);
////				iPtMaterialLoanMasterRepository.save(getExistingRecord);
//                jsonModel.setMaterial_loan_master_id(0);
//                jsonModel.setLoan_version(getExistingRecord.getLoan_version() + 1);
//            }


            CPtMaterialLoanMasterModel responceMaterialLoanMasterModel = iPtMaterialLoanMasterRepository
                    .save(jsonModel);

//            if (update) {
//                iPtMaterialLoanDetailsRepository.updateStatus(loan_no,
//                        masterjson.getInt("loan_version"), financial_year, company_id);
//            }

            List<CPtMaterialLoanDetailsModel> cPtMaterialLoanDetails = objectMapper
                    .readValue(detailsArray.toString(), new TypeReference<List<CPtMaterialLoanDetailsModel>>() {
                    });

            cPtMaterialLoanDetails.forEach(items -> {
                items.setLoan_version(jsonModel.getLoan_version());
                items.setMaterial_loan_master_id(
                        responceMaterialLoanMasterModel.getMaterial_loan_master_id());
            });

            iPtMaterialLoanDetailsRepository.saveAll(cPtMaterialLoanDetails);

//            if(addOrIssued.equalsIgnoreCase("issue")){
//                FnUpdateIssuedStock(responceMaterialLoanMasterModel, cPtMaterialLoanDetails);
//            }

            responce.put("message", addOrIssued.equalsIgnoreCase("issue") ? "Material Issued Successfully!" : addOrIssued.equalsIgnoreCase("return") ? "Record Returned Successfully!" : "Record Added Successfully!");
            responce.put("data", responceMaterialLoanMasterModel);
            responce.put("success", 1);
            responce.put("error", "");

        } catch (DataAccessException e) {
            if (e.getRootCause() instanceof SQLException) {
                SQLException sqlEx = (SQLException) e.getRootCause();
                amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/PtMaterialLoanDetails/FnAddUpdateRecord",
                        sqlEx.getErrorCode(), sqlEx.getMessage());
                responce.put("success", "0");
                responce.put("data", "");
                responce.put("error", e.getMessage());
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/producttype/FnAddUpdateRecord", 0,
                    ex.getMessage());
            responce.put("success", "0");
            responce.put("data", "");
            responce.put("error", ex.getMessage());

        }
        return responce;
    }

    private void FnUpdateIssuedStock(CPtMaterialLoanMasterModel responceMaterialLoanMasterModel, List<CPtMaterialLoanDetailsModel> cPtMaterialLoanDetails) {
        //		Get CurrentDate
        Date currentDate = new Date();
//	    Formatting the date to display only the date portion
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String todayDate = dateFormat.format(currentDate);

        Map<String, Object> stockDetails = new HashMap<>();

        List<CSmProductRmStockDetailsModel> addProductRmStockDetailsList = new ArrayList<>();
        List<CSmProductRmStockSummaryModel> addProductRmStockSummaryList = new ArrayList<>();
        List<CSmProductStockTracking> addStockTracking = new ArrayList<>();

//		Get distinct material ids
        List<String> distinctMaterialIds = cPtMaterialLoanDetails.stream().map(CPtMaterialLoanDetailsModel::getProduct_material_id).distinct()
                .collect(Collectors.toList());

//		Get the data from sm_product_rm_stock_details table for stock issue
        List<CSmProductRmStockDetailsModel> productStockDetailsList = distinctMaterialIds != null ? iSmProductRmStockDetailsRepository.FnGetAllProductRmStockDetails(distinctMaterialIds, responceMaterialLoanMasterModel.getTo_company_id()) : null;

//		iterate on indent materials for stock issue
        cPtMaterialLoanDetails.forEach(indentRecord -> {
            AtomicReference<Double> issue_quantity = new AtomicReference<>(indentRecord.getProduct_material_issue_quantity());
            AtomicReference<Double> issue_weight = new AtomicReference<>(indentRecord.getProduct_material_issue_weight());

            String[] splitGodowns = indentRecord.getGodown_id().split(",");

            int[] getGodowns = Arrays.stream(splitGodowns)
                    .mapToInt(Integer::parseInt)
                    .toArray();

            AtomicReference<Double> reducedQty = new AtomicReference<>();
            AtomicReference<Double> reducedWt = new AtomicReference<>();

            CSmProductRmStockSummaryModel productRmStockSummaryModel = new CSmProductRmStockSummaryModel();

//			Get only specific material stock
            List<CSmProductRmStockDetailsModel> getDistinctMaterialWiseStock = productStockDetailsList.stream()
                    .filter(item -> item.getProduct_rm_id().equals(indentRecord.getProduct_material_id())
                            && item.getClosing_balance_quantity() > 0
                            && IntStream.of(getGodowns).anyMatch(gdItem -> gdItem == item.getGodown_id()))
                    .collect(Collectors.toList());

            getDistinctMaterialWiseStock.forEach(stockRecord -> {
                if (issue_quantity.get() > 0) {
                    double available_stock_quantity = stockRecord.getClosing_balance_quantity();

                    double available_stock_weight = stockRecord.getClosing_balance_weight();

                    reducedQty.set(Math.min(issue_quantity.get(), available_stock_quantity));

                    reducedWt.set(Math.min(issue_weight.get(), available_stock_weight));

                    issue_quantity.set(issue_quantity.get() - reducedQty.get());

                    CSmProductRmStockDetailsModel productRmStockDetailsModel = new CSmProductRmStockDetailsModel();

                    BeanUtils.copyProperties(stockRecord, productRmStockDetailsModel);

//					if object is present in details then just increase the qty of existing object
                    Optional<CSmProductRmStockDetailsModel> getDetailsObject = addProductRmStockDetailsList.stream()
                            .filter(item -> item.getProduct_rm_id().equals(stockRecord.getProduct_rm_id()) &&
                                    Objects.equals(stockRecord.getGodown_id(), item.getGodown_id()) &&
                                    Objects.equals(stockRecord.getGodown_section_id(), item.getGodown_section_id()) &&
                                    Objects.equals(stockRecord.getGodown_section_beans_id(), item.getGodown_section_beans_id())
                                    && item.getGoods_receipt_no().equals(stockRecord.getGoods_receipt_no())
                            ).findFirst();

                    if (getDetailsObject.isPresent()) {
                        productRmStockDetailsModel = getDetailsObject.get();
                        productRmStockDetailsModel.setClosing_balance_quantity(productRmStockDetailsModel.getClosing_balance_quantity() + (-reducedQty.get()));
                        productRmStockDetailsModel.setClosing_balance_weight(productRmStockDetailsModel.getClosing_balance_weight() + (-reducedWt.get()));
                        productRmStockDetailsModel.setProduction_issue_quantity(productRmStockDetailsModel.getProduction_issue_quantity() + reducedQty.get());
                        productRmStockDetailsModel.setProduction_issue_weight(productRmStockDetailsModel.getProduction_issue_weight() + reducedWt.get());
                    } else {
                        productRmStockDetailsModel.setClosing_balance_quantity(-reducedQty.get());
                        productRmStockDetailsModel.setClosing_balance_weight(-reducedWt.get());
                        productRmStockDetailsModel.setProduction_issue_quantity(reducedQty.get());
                        productRmStockDetailsModel.setProduction_issue_weight(reducedWt.get());

                        addProductRmStockDetailsList.add(productRmStockDetailsModel);

                    }

                    // Concatenate the new batch number with the existing one using a colon
                    String currentBatchNo = indentRecord.getIssue_batch_no();
                    String newBatchNo = stockRecord.getBatch_no();
                    String concatenatedBatchNo = currentBatchNo != null ? currentBatchNo + ":" + newBatchNo : newBatchNo;
                    indentRecord.setIssue_batch_no(concatenatedBatchNo);


                    CSmProductStockTracking cSmProductStockTracking = new CSmProductStockTracking();

                    cSmProductStockTracking.setCompany_id(stockRecord.getCompany_id());
                    cSmProductStockTracking.setCompany_branch_id(stockRecord.getCompany_branch_id());
                    cSmProductStockTracking.setFinancial_year(stockRecord.getFinancial_year());
                    cSmProductStockTracking.setProduct_material_id(indentRecord.getProduct_material_id());
                    cSmProductStockTracking.setGoods_receipt_no(stockRecord.getGoods_receipt_no());
                    cSmProductStockTracking.setConsumption_no(indentRecord.getLoan_no());
                    cSmProductStockTracking.setConsumption_detail_no(indentRecord.getIndent_no());
                    cSmProductStockTracking.setConsumption_date(new Date());
                    cSmProductStockTracking.setConsumption_location("Loan Issue");
                    cSmProductStockTracking.setConsumption_detail_no(String.valueOf(indentRecord.getIndent_details_id()));
                    cSmProductStockTracking.setConsumption_quantity(reducedQty.get());
                    cSmProductStockTracking.setCreated_by(indentRecord.getCreated_by());

                    addStockTracking.add(cSmProductStockTracking);  // Add into stock tracking list

//					if object is present in summary then just increase the qty of existing object
                    Optional<CSmProductRmStockSummaryModel> getSummaryObject = addProductRmStockSummaryList.stream()
                            .filter(item -> item.getProduct_rm_id().equals(stockRecord.getProduct_rm_id()) &&
                                    Objects.equals(stockRecord.getGodown_id(), item.getGodown_id()) &&
                                    Objects.equals(stockRecord.getGodown_section_id(), item.getGodown_section_id()) &&
                                    Objects.equals(stockRecord.getGodown_section_beans_id(), item.getGodown_section_beans_id())
                            ).findFirst();

                    if (getSummaryObject.isPresent()) {
                        CSmProductRmStockSummaryModel getProductRmStockSummaryModel = getSummaryObject.get();
                        productRmStockSummaryModel.setClosing_balance_quantity(getProductRmStockSummaryModel.getClosing_balance_quantity() + (-reducedQty.get()));
                        productRmStockSummaryModel.setClosing_balance_weight(getProductRmStockSummaryModel.getClosing_balance_weight() + (-reducedWt.get()));
                        productRmStockSummaryModel.setProduction_issue_quantity(getProductRmStockSummaryModel.getProduction_issue_quantity() + reducedQty.get());
                        productRmStockSummaryModel.setProduction_issue_weight(getProductRmStockSummaryModel.getProduction_issue_weight() + reducedWt.get());
                    } else {
                        productRmStockSummaryModel.setCompany_id(indentRecord.getCompany_id());
                        productRmStockSummaryModel.setCompany_branch_id(indentRecord.getCompany_branch_id());
                        productRmStockSummaryModel.setFinancial_year(indentRecord.getFinancial_year());
                        productRmStockSummaryModel.setClosing_balance_quantity(-reducedQty.get());
                        productRmStockSummaryModel.setClosing_balance_weight(-reducedWt.get());
                        productRmStockSummaryModel.setProduction_issue_quantity(reducedQty.get());
                        productRmStockSummaryModel.setProduction_issue_weight(reducedWt.get());
                        productRmStockSummaryModel.setProduct_rm_id(indentRecord.getProduct_material_id());
                        productRmStockSummaryModel.setProduct_material_unit_id(indentRecord.getProduct_material_unit_id());
                        productRmStockSummaryModel.setProduct_material_packing_id(indentRecord.getProduct_material_unit_id());
                        productRmStockSummaryModel.setModified_by(indentRecord.getCreated_by());
                        productRmStockSummaryModel.setGodown_id(productRmStockDetailsModel.getGodown_id());
                        productRmStockSummaryModel.setGodown_section_id(productRmStockDetailsModel.getGodown_section_id());
                        productRmStockSummaryModel.setGodown_section_beans_id(productRmStockDetailsModel.getGodown_section_beans_id());

                        addProductRmStockSummaryList.add(productRmStockSummaryModel);

                    }
                } else {
                    return;
                }
            });

        });
        stockDetails.put("RmStockSummary", addProductRmStockSummaryList);
        stockDetails.put("RmStockDetails", addProductRmStockDetailsList);
        stockDetails.put("StockTracking", addStockTracking);

        cSmProductRmStockDetailsController.FnAddUpdateRmStockRawMaterials(stockDetails, "Decrease", responceMaterialLoanMasterModel.getTo_company_id());

    }

    @Override
    public Map<String, Object> FnShowAllDetailsandMastermodelRecords(String loan_no, int loan_version,
                                                                     String financial_year, int company_id) {
        Map<String, Object> responce = new HashMap<>();
        try {
            Map<String, Object> loanIssueMasterRecords = iPtvMaterialLoanMasterSummaryRepository
                    .FnShowLoanIssueMasterRecord(loan_no, loan_version, financial_year
//                            ,company_id
                    );

            List<Map<String, Object>> loanIssueDetailsRecords = iPtMaterialLoanDetailsViewRepository
                    .FnShowLoanIssueDetailsRecords(loan_no, loan_version, financial_year
//                            , company_id
                    );

            responce.put("loanIssueMasterRecords", loanIssueMasterRecords);
            responce.put("loanIssueDetailsRecords", loanIssueDetailsRecords);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responce;
    }
    @Override
    public Map<String, Object> FnDeleteRecord(int material_loan_master_id, String UserName) {
        Map<String, Object> responce = new HashMap<>();
        try {
            iPtMaterialLoanMasterRepository.deleteLoanIssueMaster(material_loan_master_id, UserName);
            iPtMaterialLoanDetailsRepository.deleteLoanIssueDetails(material_loan_master_id, UserName);
            responce.put("success", "1");
            responce.put("data", "");
            responce.put("error", "");

        } catch (Exception e) {
            e.printStackTrace();
            responce.put("success", "0");
            responce.put("data", "");
            responce.put("error", "");
        }
        return responce;
    }


}
