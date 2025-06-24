package com.erp.InternalMaterialTransferDetails.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.InternalMaterialTransferDetails.Model.CInternalMaterialTransferDetailsModel;
import com.erp.InternalMaterialTransferDetails.Model.CInternalMaterialTransferMasterModel;
import com.erp.InternalMaterialTransferDetails.Repository.IInternalMaterialTransferDetailsRepository;
import com.erp.InternalMaterialTransferDetails.Repository.IInternalMaterialTransferMasterRepository;

import com.erp.MaterialTransferMaster.Model.CMaterialTransferDetailsModel;
import com.erp.MaterialTransferMaster.Model.CMaterialTransferMasterModel;
//import com.erp.SmProductRmStockDetails.Controller.CSmProductRmStockDetailsController;
import com.erp.SmProductRmStockDetails.Model.CSmProductRmStockDetailsModel;
import com.erp.SmProductRmStockDetails.Model.CSmProductRmStockSummaryModel;
import com.erp.SmProductRmStockDetails.Model.CSmProductStockTracking;
import com.erp.SmProductRmStockDetails.Repository.ISmProductRmStockDetailsRepository;
import com.erp.SmProductRmStockDetails.Repository.ISmProductRmStockSummaryHistoryModelRepository;
import com.erp.SmProductRmStockDetails.Repository.ISmProductRmStockSummaryRepository;
import com.erp.SmProductRmStockDetails.Repository.ISmProductStockTrackingRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class CInternalMaterialTransferDetailsServiceImpl implements IInternalMaterialTransferDetailsService {

    @Autowired
    CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

    @Autowired
    IInternalMaterialTransferMasterRepository iInternalMaterialTransferMasterRepository;

    @Autowired
    IInternalMaterialTransferDetailsRepository iInternalMaterialTransferDetailsRepository;

    @Autowired
    ISmProductRmStockDetailsRepository iSmProductRmStockDetailsRepository;

    @Autowired
    ISmProductRmStockSummaryHistoryModelRepository iSmProductRmStockSummaryHistoryModelRepository;

    @Autowired
    ISmProductRmStockSummaryRepository iSmProductRmStockSummaryRepository;
    @Autowired
    ISmProductStockTrackingRepository iSmProductStockTrackingRepository;
//    @Autowired
//    CSmProductRmStockDetailsController cSmProductRmStockDetailsController;
    @Override
    @Transactional
    public Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject, String addOrIssued) {
        Map<String, Object> responce = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();

        JSONObject commonIdsObj = (JSONObject) jsonObject.get("commonIds");
        int company_id = commonIdsObj.getInt("company_id");
//        int company_branch_id = commonIdsObj.getInt("company_branch_id");
        String inter_material_transfer_no = commonIdsObj.getString("inter_material_transfer_no");
        String financial_year = commonIdsObj.getString("financial_year");
        JSONObject masterjson = jsonObject.getJSONObject("TransHeaderData");
        JSONArray detailsArray = (JSONArray) jsonObject.get("TransDetailData");
        boolean update = false;

        try {
            CInternalMaterialTransferMasterModel jsonModel = objectMapper.readValue(masterjson.toString(),
                    CInternalMaterialTransferMasterModel.class);

//            CInternalMaterialTransferMasterModel getExistingRecord = iInternalMaterialTransferMasterRepository.getExistingRecord(
//                    inter_material_transfer_no, jsonModel.getLoan_version(), financial_year, company_id);
//
//            if (getExistingRecord != null) {
//                getExistingRecord.setDeleted_by(jsonModel.getCreated_by());
//                getExistingRecord.setDeleted_on(new Date());
//                getExistingRecord.set_delete(true);
////				iInternalMaterialTransferMasterRepository.save(getExistingRecord);
//                jsonModel.setMaterial_loan_master_id(0);
//                jsonModel.setLoan_version(getExistingRecord.getLoan_version() + 1);
//            }


            CInternalMaterialTransferMasterModel cInternalMaterialTransferMasterModel = iInternalMaterialTransferMasterRepository.save(jsonModel);

//            if (update) {
//                iInternalMaterialTransferDetailsRepository.updateStatus(inter_material_transfer_no,financial_year, company_id);
//            }

            List<CInternalMaterialTransferDetailsModel> cPtInternalMaterialDetails = objectMapper
                    .readValue(detailsArray.toString(), new TypeReference<List<CInternalMaterialTransferDetailsModel>>() {
                    });

            cPtInternalMaterialDetails.forEach(items -> {
                items.setInter_material_transfer_master_id(
                        cInternalMaterialTransferMasterModel.getInter_material_transfer_master_id());
            });

            iInternalMaterialTransferDetailsRepository.saveAll(cPtInternalMaterialDetails);

//            if(addOrIssued.equalsIgnoreCase("issue")){
//                FnUpdateIssuedStock(cInternalMaterialTransferMasterModel, cPtInternalMaterialDetails);
//            }
            Map<String, Object> FnTranferReduceStockDetails = FnTransferReduceStockDetails(cInternalMaterialTransferMasterModel, cPtInternalMaterialDetails, company_id);


            responce.put("message", addOrIssued.equalsIgnoreCase("receive") ? "Material Received Successfully!" : addOrIssued.equalsIgnoreCase("return") ? "Record Returned Successfully!" : "Record Added Successfully!");
            responce.put("data", cInternalMaterialTransferMasterModel);
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



    public Map<String, Object> FnTransferReduceStockDetails(CInternalMaterialTransferMasterModel responseMaterialTransferMaster, List<CInternalMaterialTransferDetailsModel> MaterialTransferDetails, Integer company_id) {
        Map<String, Object> batchresponse = new HashMap<>();
        System.out.println("responseMaterialTransferMasters: " + responseMaterialTransferMaster);
        System.out.println("MaterialTransferDetails: " + MaterialTransferDetails);
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String todayDate = dateFormat.format(currentDate);
        Map<String, Object> stockDetails = new HashMap<>();
        int companyId = responseMaterialTransferMaster.getCompany_id();

        List<CSmProductRmStockDetailsModel> reduceProductRmStockDetailsList = new ArrayList<>();
        List<CSmProductRmStockSummaryModel> reduceProductRmStockSummaryList = new ArrayList<>();
        List<CSmProductRmStockDetailsModel> addProductRmStockDetailsList = new ArrayList<>();
        List<CSmProductRmStockSummaryModel> addProductRmStockSummaryList = new ArrayList<>();

        //Get distinct Transfer material ids from MaterialTransferDetails
        List<String> distinctMaterialIds = MaterialTransferDetails.stream().map(CInternalMaterialTransferDetailsModel::getProduct_material_id).distinct()
                .collect(Collectors.toList());
        System.out.println("distinct transfered MaterialIds: " + distinctMaterialIds);

        //Get All data from sm_product_rm_stock_details table for stock transfer material
        List<CSmProductRmStockDetailsModel> productStockDetailsList = !distinctMaterialIds.isEmpty() ? iInternalMaterialTransferDetailsRepository.FnGetAllProductRmStockDetailsRawMaterials(distinctMaterialIds, responseMaterialTransferMaster.getCompany_id(),MaterialTransferDetails.get(0).getFrom_godown_id()) : null;

        //iterate on indent materials for stock issue
        MaterialTransferDetails.forEach(stockTransferDetail -> {
            System.out.println("checking for Product_material_id: " + stockTransferDetail.getProduct_material_id());

            List<CSmProductRmStockDetailsModel> getDistinctMaterialWiseStock = productStockDetailsList.stream()
                    .filter(item ->
                            Objects.equals(item.getProduct_rm_id(), stockTransferDetail.getProduct_material_id()) &&
                                    Objects.equals(item.getGodown_id(), stockTransferDetail.getFrom_godown_id()) &&
                                    Objects.equals(item.getGodown_section_id(), stockTransferDetail.getFrom_godown_section_id()) &&
                                    Objects.equals(item.getGodown_section_beans_id(), stockTransferDetail.getFrom_godown_section_beans_id()) &&
                                    Objects.equals(item.getCompany_id(), company_id) &&
                                    item.getClosing_balance_quantity() > 0
                    )
                    .collect(Collectors.toList());
            System.out.println("Filtered List: " + getDistinctMaterialWiseStock);

            AtomicReference<Double> transfer_quantity = new AtomicReference<>(stockTransferDetail.getInter_material_transfer_quantity());
            AtomicReference<Double> transfer_weight = new AtomicReference<>(stockTransferDetail.getInter_material_transfer_weight());
            AtomicReference<Integer> transfer_box = new AtomicReference<>(stockTransferDetail.getInter_material_transfer_boxes());

            AtomicReference<Double> reducedQty = new AtomicReference<>();
            AtomicReference<Double> reducedWt = new AtomicReference<>();
            AtomicReference<Integer> reducedBox = new AtomicReference<>();

            CSmProductRmStockSummaryModel reduceproductStockSummaryModel = new CSmProductRmStockSummaryModel();
            CSmProductRmStockSummaryModel addproductStockSummaryModel = new CSmProductRmStockSummaryModel();

            getDistinctMaterialWiseStock.forEach(stockRecord -> {
                if (transfer_quantity.get() > 0) {
                    double available_stock_quantity = stockRecord.getClosing_balance_quantity();
                    double available_stock_weight = stockRecord.getClosing_balance_weight();
                    Integer available_stock_box = stockRecord.getClosing_no_of_boxes();

                    reducedQty.set(Math.min(transfer_quantity.get(), available_stock_quantity));
                    reducedWt.set(Math.min(transfer_weight.get(), available_stock_weight));
                    reducedBox.set(Math.min(transfer_box.get(), available_stock_box));

                    transfer_quantity.set(transfer_quantity.get() - reducedQty.get());
                    transfer_box.set(transfer_box.get() - reducedBox.get());

                    System.out.println("transfer_quantity: " + transfer_quantity + " reducedQty = " + reducedQty + " reducedWt = " + reducedWt);
                    CSmProductRmStockDetailsModel reduceproductRmStockDetailsModel = new CSmProductRmStockDetailsModel();
//                    BeanUtils.copyProperties(stockRecord, reduceproductRmStockDetailsModel);

                    CSmProductRmStockDetailsModel addproductRmStockDetailsModel = new CSmProductRmStockDetailsModel();
//                    BeanUtils.copyProperties(stockRecord, addproductRmStockDetailsModel);

                    //if object is present in details then just increase the qty of existing object
                    Optional<CSmProductRmStockDetailsModel> reducegetDetailsObject = reduceProductRmStockDetailsList.stream()
                            .filter(item ->

                                    item.getProduct_rm_id().equals(stockRecord.getProduct_rm_id()) &&
                                            Objects.equals(stockRecord.getGodown_id(), item.getGodown_id()) &&
                                            Objects.equals(stockRecord.getGodown_section_id(), item.getGodown_section_id()) &&
                                            Objects.equals(stockRecord.getGodown_section_beans_id(), item.getGodown_section_beans_id())
                                            && item.getGoods_receipt_no().equals(stockRecord.getGoods_receipt_no())
                            ).findFirst();

                    Optional<CSmProductRmStockDetailsModel> addgetDetailsObject = addProductRmStockDetailsList.stream()
                            .filter(item ->
                                    item.getProduct_rm_id().equals(stockRecord.getProduct_rm_id()) &&
                                            Objects.equals(stockRecord.getGodown_id(), item.getGodown_id()) &&
                                            Objects.equals(stockRecord.getGodown_section_id(), item.getGodown_section_id()) &&
                                            Objects.equals(stockRecord.getGodown_section_beans_id(), item.getGodown_section_beans_id())
                                            && item.getGoods_receipt_no().equals(stockRecord.getGoods_receipt_no())
                            ).findFirst();
                    String transfer_date = dateFormat.format(stockTransferDetail.getInter_material_transfer_date());


                    if (reducegetDetailsObject.isPresent()) {
                        reduceproductRmStockDetailsModel = reducegetDetailsObject.get();
                        reduceproductRmStockDetailsModel.setClosing_balance_quantity(reduceproductRmStockDetailsModel.getClosing_balance_quantity() + (-reducedQty.get()));
                        reduceproductRmStockDetailsModel.setClosing_balance_weight(reduceproductRmStockDetailsModel.getClosing_balance_weight() + (-reducedWt.get()));
                        reduceproductRmStockDetailsModel.setClosing_no_of_boxes(reduceproductRmStockDetailsModel.getClosing_no_of_boxes() + (-reducedBox.get()));

                        reduceproductRmStockDetailsModel.setLoan_issue_quantity(reduceproductRmStockDetailsModel.getLoan_issue_quantity() + reducedQty.get());
                        reduceproductRmStockDetailsModel.setLoan_issue_weight(reduceproductRmStockDetailsModel.getLoan_issue_weight() + reducedWt.get());
                        reduceproductRmStockDetailsModel.setLoan_issue_boxes(reduceproductRmStockDetailsModel.getLoan_issue_boxes() + reducedBox.get());

                        reduceproductRmStockDetailsModel.setStock_date(transfer_date);
                        reduceproductRmStockDetailsModel.setCompany_id(company_id);

                        addproductRmStockDetailsModel =  addgetDetailsObject.get();
                        addproductRmStockDetailsModel.setClosing_balance_quantity(addproductRmStockDetailsModel.getClosing_balance_quantity() + (-reducedQty.get()));
                        addproductRmStockDetailsModel.setClosing_balance_weight(addproductRmStockDetailsModel.getClosing_balance_weight() + (-reducedWt.get()));
                        addproductRmStockDetailsModel.setClosing_no_of_boxes(addproductRmStockDetailsModel.getClosing_no_of_boxes() + (-reducedBox.get()));

                        addproductRmStockDetailsModel.setLoan_receipt_quantity(addproductRmStockDetailsModel.getLoan_receipt_quantity() + reducedQty.get());
                        addproductRmStockDetailsModel.setLoan_receipt_weight(addproductRmStockDetailsModel.getLoan_receipt_weight() + reducedWt.get());
                        addproductRmStockDetailsModel.setLoan_receipt_boxes(addproductRmStockDetailsModel.getLoan_receipt_boxes()+ reducedBox.get());

                        addproductRmStockDetailsModel.setStock_date(transfer_date);
                        addproductRmStockDetailsModel.setCompany_id(companyId);


                    } else {
                        reduceproductRmStockDetailsModel.setClosing_balance_quantity(-reducedQty.get());
                        reduceproductRmStockDetailsModel.setClosing_balance_weight(-reducedWt.get());
                        reduceproductRmStockDetailsModel.setClosing_no_of_boxes(-reducedBox.get());
                        reduceproductRmStockDetailsModel.setLoan_issue_quantity(reducedQty.get());
                        reduceproductRmStockDetailsModel.setLoan_issue_weight(reducedWt.get());
                        reduceproductRmStockDetailsModel.setLoan_issue_boxes(reducedBox.get());
                        reduceproductRmStockDetailsModel.setStock_date(transfer_date);
                        reduceproductRmStockDetailsModel.setFinancial_year(stockTransferDetail.getFinancial_year());
                        reduceproductRmStockDetailsModel.setGoods_receipt_no(stockRecord.getGoods_receipt_no());
                        reduceproductRmStockDetailsModel.setProduct_rm_id(stockRecord.getProduct_rm_id());
                        reduceproductRmStockDetailsModel.setProduct_material_unit_id((stockRecord.getProduct_material_unit_id()));
                        reduceproductRmStockDetailsModel.setProduct_type_id(stockRecord.getProduct_type_id());
                        reduceproductRmStockDetailsModel.setGodown_id(stockTransferDetail.getFrom_godown_id());
                        reduceproductRmStockDetailsModel.setGodown_section_id(stockTransferDetail.getFrom_godown_section_id());
                        reduceproductRmStockDetailsModel.setGodown_section_beans_id(stockTransferDetail.getFrom_godown_section_beans_id());
                        reduceproductRmStockDetailsModel.setCreated_by(stockTransferDetail.getCreated_by());
                        reduceproductRmStockDetailsModel.setModified_by(stockTransferDetail.getModified_by());
                        reduceproductRmStockDetailsModel.setCompany_branch_id(stockTransferDetail.getCompany_branch_id());
                        reduceproductRmStockDetailsModel.setCompany_id(company_id);
                        reduceproductRmStockDetailsModel.setWeight_per_box_item(stockTransferDetail.getCone_per_wt());
                        reduceproductRmStockDetailsModel.setBatch_no(stockTransferDetail.getBatch_no());
                        reduceproductRmStockDetailsModel.setSupplier_id(stockTransferDetail.getSupplier_id());
                        reduceproductRmStockDetailsModel.setProduct_material_unit_id(stockTransferDetail.getProduct_unit_id());

                        reduceProductRmStockDetailsList.add(reduceproductRmStockDetailsModel);


                        addproductRmStockDetailsModel.setCompany_id(companyId);
                        addproductRmStockDetailsModel.setFinancial_year(stockTransferDetail.getFinancial_year());
                        addproductRmStockDetailsModel.setCompany_branch_id(responseMaterialTransferMaster.getCompany_branch_id());
                        addproductRmStockDetailsModel.setProduct_rm_id(stockRecord.getProduct_rm_id());
                        addproductRmStockDetailsModel.setProduct_material_unit_id((stockRecord.getProduct_material_unit_id()));
                        addproductRmStockDetailsModel.setProduct_type_group(stockRecord.getProduct_type_group());
                        addproductRmStockDetailsModel.setProduct_type_id(stockRecord.getProduct_type_id());
                        addproductRmStockDetailsModel.setStock_date(transfer_date);
                        addproductRmStockDetailsModel.setBatch_rate(stockRecord.getBatch_rate());
                        addproductRmStockDetailsModel.setGoods_receipt_no(stockRecord.getGoods_receipt_no());
                        addproductRmStockDetailsModel.setLoan_receipt_quantity(reducedQty.get());
                        addproductRmStockDetailsModel.setLoan_receipt_weight(reducedWt.get());
                        addproductRmStockDetailsModel.setLoan_receipt_boxes(reducedBox.get());

                        addproductRmStockDetailsModel.setClosing_balance_quantity(reducedQty.get());
                        addproductRmStockDetailsModel.setClosing_balance_weight(reducedWt.get());
                        addproductRmStockDetailsModel.setClosing_no_of_boxes(reducedBox.get());
                        addproductRmStockDetailsModel.setGodown_id(stockTransferDetail.getTo_godown_id());
                        addproductRmStockDetailsModel.setGodown_section_id(stockTransferDetail.getTo_godown_section_id());
                        addproductRmStockDetailsModel.setGodown_section_beans_id(stockTransferDetail.getTo_godown_section_beans_id());
                        addproductRmStockDetailsModel.setCreated_by(stockTransferDetail.getCreated_by());
                        addproductRmStockDetailsModel.setModified_by(stockTransferDetail.getModified_by());
                        addproductRmStockDetailsModel.setWeight_per_box_item(stockTransferDetail.getCone_per_wt());
                        addproductRmStockDetailsModel.setBatch_no(stockTransferDetail.getBatch_no());
                        addproductRmStockDetailsModel.setSupplier_id(stockTransferDetail.getSupplier_id());
                        addproductRmStockDetailsModel.setProduct_material_unit_id(stockTransferDetail.getProduct_unit_id());

                        addProductRmStockDetailsList.add(addproductRmStockDetailsModel);
                    }


                    //if object is present in summary then just increase the qty of existing object
                    Optional<CSmProductRmStockSummaryModel> reducegetSummaryObject = reduceProductRmStockSummaryList.stream()
                            .filter(item -> (item.getProduct_rm_id().equals(stockRecord.getProduct_rm_id()) &&
                                    Objects.equals(stockRecord.getGodown_id(), item.getGodown_id()) &&
                                    Objects.equals(stockRecord.getGodown_section_id(), item.getGodown_section_id()) &&
                                    Objects.equals(stockRecord.getGodown_section_beans_id(), item.getGodown_section_beans_id())
                            )).findFirst();

                    Optional<CSmProductRmStockSummaryModel> addgetSummaryObject = addProductRmStockSummaryList.stream()
                            .filter(item -> (item.getProduct_rm_id().equals(stockRecord.getProduct_rm_id()) &&
                                    Objects.equals(stockRecord.getGodown_id(), item.getGodown_id()) &&
                                    Objects.equals(stockRecord.getGodown_section_id(), item.getGodown_section_id()) &&
                                    Objects.equals(stockRecord.getGodown_section_beans_id(), item.getGodown_section_beans_id())
                            )).findFirst();

                    if (reducegetSummaryObject.isPresent() && addgetSummaryObject.isPresent() ) {
                        CSmProductRmStockSummaryModel getReduceProductRmStockSummaryModel = reducegetSummaryObject.get();
                        reduceproductStockSummaryModel.setClosing_balance_quantity(getReduceProductRmStockSummaryModel.getClosing_balance_quantity() + (-reducedQty.get()));
                        reduceproductStockSummaryModel.setClosing_balance_weight(getReduceProductRmStockSummaryModel.getClosing_balance_weight() + (-reducedWt.get()));
                        reduceproductStockSummaryModel.setClosing_no_of_boxes(getReduceProductRmStockSummaryModel.getClosing_no_of_boxes() + (-reducedBox.get()));
                        reduceproductStockSummaryModel.setLoan_issue_quantity(reduceproductStockSummaryModel.getLoan_issue_quantity() + reducedQty.get());
                        reduceproductStockSummaryModel.setLoan_issue_weight(reduceproductStockSummaryModel.getLoan_issue_weight() + reducedWt.get());
                        reduceproductStockSummaryModel.setLoan_issue_boxes(reduceproductStockSummaryModel.getLoan_issue_boxes() + reducedBox.get());

                        reduceproductStockSummaryModel.setCompany_id(company_id);

                        CSmProductRmStockSummaryModel getAddProductRmStockSummaryModel = addgetSummaryObject.get();
                        addproductStockSummaryModel.setClosing_balance_quantity(getAddProductRmStockSummaryModel.getClosing_balance_quantity() + reducedQty.get());
                        addproductStockSummaryModel.setClosing_balance_weight(getAddProductRmStockSummaryModel.getClosing_balance_weight() + reducedWt.get());
                        addproductStockSummaryModel.setClosing_no_of_boxes(getReduceProductRmStockSummaryModel.getClosing_no_of_boxes() + reducedBox.get());

                        addproductStockSummaryModel.setLoan_receipt_quantity(addproductStockSummaryModel.getLoan_receipt_quantity() + reducedQty.get());
                        addproductStockSummaryModel.setLoan_receipt_weight(addproductStockSummaryModel.getLoan_receipt_weight() + reducedWt.get());
                        addproductStockSummaryModel.setLoan_issue_boxes(reduceproductStockSummaryModel.getLoan_receipt_boxes() + reducedBox.get());

                        addproductStockSummaryModel.setCompany_id(companyId);

                    } else {
                        reduceproductStockSummaryModel.setFinancial_year(responseMaterialTransferMaster.getFinancial_year());
                        reduceproductStockSummaryModel.setClosing_balance_quantity(-reducedQty.get());
                        reduceproductStockSummaryModel.setClosing_balance_weight(-reducedWt.get());
                        reduceproductStockSummaryModel.setClosing_no_of_boxes(-reducedBox.get());

                        reduceproductStockSummaryModel.setLoan_issue_quantity(reducedQty.get());
                        reduceproductStockSummaryModel.setLoan_issue_weight(reducedWt.get());
                        reduceproductStockSummaryModel.setLoan_issue_boxes(reducedBox.get());
                        reduceproductStockSummaryModel.setProduct_rm_id(stockRecord.getProduct_rm_id());
                        reduceproductStockSummaryModel.setProduct_type_id(stockRecord.getProduct_type_id());
                        reduceproductStockSummaryModel.setGodown_id(stockRecord.getGodown_id());
                        reduceproductStockSummaryModel.setGodown_section_id(stockRecord.getGodown_section_id());
                        reduceproductStockSummaryModel.setGodown_section_beans_id(stockRecord.getGodown_section_beans_id());
                        reduceproductStockSummaryModel.setCompany_id(company_id);
                        reduceproductStockSummaryModel.setCompany_branch_id(stockRecord.getCompany_branch_id());


                        addproductStockSummaryModel.setCompany_id(companyId);
                        addproductStockSummaryModel.setCompany_branch_id(responseMaterialTransferMaster.getCompany_branch_id());
                        addproductStockSummaryModel.setFinancial_year(responseMaterialTransferMaster.getFinancial_year());
                        addproductStockSummaryModel.setProduct_type_id(responseMaterialTransferMaster.getProduct_type_id());
                        addproductStockSummaryModel.setProduct_type_group(stockRecord.getProduct_type_group());
                        addproductStockSummaryModel.setProduct_rm_id(stockRecord.getProduct_rm_id());
                        addproductStockSummaryModel.setProduct_material_unit_id(stockRecord.getProduct_material_unit_id());
                        addproductStockSummaryModel.setLoan_receipt_quantity(reducedQty.get());
                        addproductStockSummaryModel.setLoan_receipt_weight(reducedWt.get());
                        addproductStockSummaryModel.setLoan_receipt_boxes(reducedBox.get());

                        addproductStockSummaryModel.setClosing_balance_quantity(reducedQty.get());
                        addproductStockSummaryModel.setClosing_balance_weight(reducedWt.get());
                        addproductStockSummaryModel.setClosing_no_of_boxes(reducedBox.get());
                        addproductStockSummaryModel.setRemark(null);
                        addproductStockSummaryModel.setGodown_id(stockRecord.getGodown_id());
                        addproductStockSummaryModel.setGodown_section_id(stockRecord.getGodown_section_id());
                        addproductStockSummaryModel.setGodown_section_beans_id(stockRecord.getGodown_section_beans_id());
                        addproductStockSummaryModel.setCreated_by(stockTransferDetail.getCreated_by());
                        addproductStockSummaryModel.setModified_by(stockTransferDetail.getCreated_by());


                        reduceProductRmStockSummaryList.add(reduceproductStockSummaryModel);
                        addProductRmStockSummaryList.add(addproductStockSummaryModel);
                    }
                } else {
                    return;
                }
            });

        });


        stockDetails.put("RmStockSummary", reduceProductRmStockSummaryList);
        stockDetails.put("RmStockDetails", reduceProductRmStockDetailsList);
        Map<String, Object> decreaseResponse =FnAddUpdateRmStockRawMaterials(stockDetails, "Decrease", company_id);
        String decreaseMessage = (String) decreaseResponse.get("message");
        int decreaseSuccess = (int) decreaseResponse.get("Success");
        batchresponse.put("Decrease", decreaseMessage);

        stockDetails.clear();
        stockDetails.put("RmStockSummary", addProductRmStockSummaryList);
        stockDetails.put("RmStockDetails", addProductRmStockDetailsList);
        Map<String, Object> increaseResponse = FnAddUpdateRmStockRawMaterials(stockDetails, "Increase", companyId);


        String increaseMessage = (String) increaseResponse.get("message");
        int increaseSuccess = (int) increaseResponse.get("Success");
        batchresponse.put("Increase", increaseMessage);

        if (decreaseSuccess == 1 && increaseSuccess == 1) {
            batchresponse.put("Status", "1");
            batchresponse.put("Success", 1);
            batchresponse.put("message", "Both Decrease and Increase Stock Details operations were successful!");
        } else {
            batchresponse.put("Status", "0");
            batchresponse.put("Success", 0);
            batchresponse.put("message", "One or both Decrease and Increase Stock Details operations failed!");
        }
        return batchresponse;

    }
    @Override
    public Map<String, Object> FnShowAllDetailsandMastermodelRecords(String inter_material_transfer_no,
                                                                     String financial_year, int company_id) {
        Map<String, Object> responce = new HashMap<>();
        try {
            Map<String, Object> internalTransferMasterRecords = iInternalMaterialTransferMasterRepository
                    .FnShowInternalTransferMasterRecord(inter_material_transfer_no, financial_year,company_id
                    );

            List<Map<String, Object>> internalTransferDetailsRecords = iInternalMaterialTransferDetailsRepository
                    .FnShowInternalTransferDetailsRecords(inter_material_transfer_no,  financial_year, company_id
                    );

            responce.put("internalTransferMasterRecords", internalTransferMasterRecords);
            responce.put("internalTransferDetailsRecords", internalTransferDetailsRecords);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responce;
    }
    @Override
    public Map<String, Object> FnDeleteRecord(int inter_material_transfer_master_id, String UserName) {
        Map<String, Object> responce = new HashMap<>();
        try {
            iInternalMaterialTransferMasterRepository.deleteInternalMaterial(inter_material_transfer_master_id, UserName);
            iInternalMaterialTransferDetailsRepository.deleteInternalMaterialDetails(inter_material_transfer_master_id, UserName);
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

    @Override
    public Map<String, Object> FnAddUpdateRmStockRawMaterials(Map<String, Object> stockDetails, String iuFlag, int company_id) {
        Map<String, Object> response = new HashMap<>();
        List<Map<String, Object>> batchList = new ArrayList<>();
        Map<String, Object> batchObject = new HashMap<>();

//  ----------------------------------------------------------  material Stock Updation--------------------------------------------------------------------------------------
//			Create list to add object to save stock details & Summary
        List<CSmProductRmStockDetailsModel> addProductRmStockDetailsList = new ArrayList<>();
        List<CSmProductRmStockSummaryModel> addProductRmStockSummaryList = new ArrayList<>();
        List<CSmProductStockTracking> addProductStockTracking = new ArrayList<>();

//			Get Rm & Fg Stock
        List<CSmProductRmStockSummaryModel> getRmStockSummary = (List<CSmProductRmStockSummaryModel>) stockDetails.get("RmStockSummary");
        List<CSmProductRmStockDetailsModel> getRmStockDetails = (List<CSmProductRmStockDetailsModel>) stockDetails.get("RmStockDetails");
        List<CSmProductStockTracking> getStockTrackingDetails = (List<CSmProductStockTracking>) stockDetails.get("StockTracking");

//        boolean is_product_type_raw_material = false;
        AtomicReference<List<CSmProductRmStockSummaryModel>> productRmStockSummaryList = new AtomicReference<>();
        AtomicReference<List<CSmProductRmStockDetailsModel>> productRmStockDetailsList = new AtomicReference<>();
        AtomicReference<List<CSmProductStockTracking>> productRmStockTrackingDetailsList = new AtomicReference<>();

        // Define a date formatter matching your date string format
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Adjust the pattern as needed
        // Get today's date
        LocalDate todayDate = LocalDate.now();
        Optional<LocalDate> issue_date = Optional.empty();

//          Get distinct material Ids
        List<String> distinctMaterialIds = null;
        if (getRmStockDetails != null && !getRmStockDetails.isEmpty()) {
            distinctMaterialIds = getRmStockDetails.stream().map(CSmProductRmStockDetailsModel::getProduct_rm_id).distinct().collect(Collectors.toList());
            issue_date = getRmStockDetails.stream()
                    .map(CSmProductRmStockDetailsModel::getStock_date) // map to stock_date as String
                    .filter(Objects::nonNull) // filter out any null values to avoid NullPointerException
                    .map(date -> LocalDate.parse(date, dateFormatter)) // parse directly in stream
                    .findFirst(); // get the first element if available

        }
        productRmStockSummaryList.set(distinctMaterialIds != null ? iSmProductRmStockSummaryRepository.FnGetAllProductRmStockSummary(distinctMaterialIds, company_id) : null);

        // Get data from stock table if issue date is small then current
        if (iuFlag.equalsIgnoreCase("Decrease") && issue_date.isPresent() && issue_date.get().isBefore(todayDate)) {
            // Using map and orElse to handle the empty case
            String issueDate = issue_date.map(LocalDate::toString).orElse("");
            productRmStockDetailsList.set(distinctMaterialIds != null ? iSmProductRmStockDetailsRepository.FnGetAllProductRmStockDetailsByIssueDate(distinctMaterialIds, company_id, issueDate) : null);
        } else {
            productRmStockDetailsList.set(distinctMaterialIds != null ? iSmProductRmStockDetailsRepository.FnGetAllProductRmStockDetails(distinctMaterialIds, company_id) : null);
        }
        productRmStockTrackingDetailsList.set(distinctMaterialIds != null ? iSmProductStockTrackingRepository.FnGetAllProductTrackingDetails(distinctMaterialIds, company_id) : null);

//  Check incoming stock is available in our stock then if available the update it or add it (For Raw material stock summary table)
        if (getRmStockSummary != null && getRmStockSummary.size() != 0) {
            getRmStockSummary.forEach(summObject -> {
                CSmProductRmStockSummaryModel productRmStockSummaryModel = new CSmProductRmStockSummaryModel();
                String material_id = summObject.getProduct_rm_id();
                Integer godown_id = summObject.getGodown_id();
                Integer godown_section_id = summObject.getPrev_godown_section_id() != null ? summObject.getPrev_godown_section_id() : summObject.getGodown_section_id();
                Integer godown_section_beans_id = summObject.getPrev_godown_section_id() != null ? summObject.getPrev_godown_section_beans_id() : summObject.getGodown_section_beans_id();

//				Find the object in productRmStockDetailsList based on material_id
                Optional<CSmProductRmStockSummaryModel> matchingObject = productRmStockSummaryList.get().stream()
                        .filter(item ->
                                material_id.equals(item.getProduct_rm_id()) &&
//                                        (item.getProduct_type_id() != '2' || Objects.equals(company_id, item.getCompany_id())) &&
                                        (Objects.equals(company_id, item.getCompany_id())) &&
                                        Objects.equals(godown_id, item.getGodown_id()) &&
                                        Objects.equals(godown_section_id, item.getGodown_section_id()) &&
                                        Objects.equals(godown_section_beans_id, item.getGodown_section_beans_id()))
                        .findFirst();

//              Matching object present using material Id then update it otherwise insert it
                if (matchingObject.isPresent()) {
                    CSmProductRmStockSummaryModel existingSummMaterislStock = matchingObject.get();

                    BeanUtils.copyProperties(existingSummMaterislStock, productRmStockSummaryModel);

                    productRmStockSummaryModel.setPending_quantity(existingSummMaterislStock.getPending_quantity() + (summObject.getPending_quantity()));
                    productRmStockSummaryModel.setPending_weight(existingSummMaterislStock.getPending_weight() + (summObject.getPending_weight()));
                    productRmStockSummaryModel.setReserve_quantity(existingSummMaterislStock.getReserve_quantity() + (summObject.getReserve_quantity()));
                    productRmStockSummaryModel.setReserve_weight(existingSummMaterislStock.getReserve_weight() + (summObject.getReserve_weight()));
                    productRmStockSummaryModel.setExcess_quantity(existingSummMaterislStock.getExcess_quantity() + (summObject.getExcess_quantity()));
                    productRmStockSummaryModel.setExcess_weight(existingSummMaterislStock.getExcess_weight() + (summObject.getExcess_weight()));
                    productRmStockSummaryModel.setPree_closed_quantity(existingSummMaterislStock.getPree_closed_quantity() + (summObject.getPree_closed_quantity()));
                    productRmStockSummaryModel.setPree_closed_weight(existingSummMaterislStock.getPree_closed_weight() + (summObject.getPree_closed_weight()));
                    productRmStockSummaryModel.setPurchase_quantity(existingSummMaterislStock.getPurchase_quantity() + (summObject.getPurchase_quantity()));
                    productRmStockSummaryModel.setPurchase_weight(existingSummMaterislStock.getPurchase_weight() + (summObject.getPurchase_weight()));
                    productRmStockSummaryModel.setPurchase_return_quantity(existingSummMaterislStock.getPurchase_return_quantity() + (summObject.getPurchase_return_quantity()));
                    productRmStockSummaryModel.setPurchase_return_weight(existingSummMaterislStock.getPurchase_return_weight() + (summObject.getPurchase_return_weight()));
                    productRmStockSummaryModel.setPurchase_rejection_quantity(existingSummMaterislStock.getPurchase_rejection_quantity() + (summObject.getPurchase_rejection_quantity()));
                    productRmStockSummaryModel.setPurchase_rejection_weight(existingSummMaterislStock.getPurchase_rejection_weight() + (summObject.getPurchase_rejection_weight()));
                    productRmStockSummaryModel.setJobcard_quantity(existingSummMaterislStock.getJobcard_quantity() + (summObject.getJobcard_quantity()));
                    productRmStockSummaryModel.setJobcard_weight(existingSummMaterislStock.getJobcard_weight() + (summObject.getJobcard_weight()));
                    productRmStockSummaryModel.setProduction_issue_quantity(existingSummMaterislStock.getProduction_issue_quantity() + (summObject.getProduction_issue_quantity()));
                    productRmStockSummaryModel.setProduction_issue_weight(existingSummMaterislStock.getProduction_issue_weight() + (summObject.getProduction_issue_weight()));
                    productRmStockSummaryModel.setProduction_issue_return_quantity(existingSummMaterislStock.getProduction_issue_return_quantity() + (summObject.getProduction_issue_return_quantity()));
                    productRmStockSummaryModel.setProduction_issue_return_weight(existingSummMaterislStock.getProduction_issue_return_weight() + (summObject.getProduction_issue_return_weight()));
                    productRmStockSummaryModel.setProduction_issue_rejection_quantity(existingSummMaterislStock.getProduction_issue_rejection_quantity() + (summObject.getProduction_issue_rejection_quantity()));
                    productRmStockSummaryModel.setProduction_issue_rejection_weight(existingSummMaterislStock.getProduction_issue_rejection_weight() + (summObject.getProduction_issue_rejection_weight()));
                    productRmStockSummaryModel.setProduction_quantity(existingSummMaterislStock.getProduction_quantity() + (summObject.getProduction_quantity()));
                    productRmStockSummaryModel.setProduction_weight(existingSummMaterislStock.getProduction_weight() + (summObject.getProduction_weight()));
                    productRmStockSummaryModel.setProduction_return_quantity(existingSummMaterislStock.getProduction_return_quantity() + (summObject.getProduction_return_quantity()));
                    productRmStockSummaryModel.setProduction_return_weight(existingSummMaterislStock.getProduction_return_weight() + (summObject.getProduction_return_weight()));
                    productRmStockSummaryModel.setProduction_rejection_quantity(existingSummMaterislStock.getProduction_rejection_quantity() + (summObject.getProduction_rejection_quantity()));
                    productRmStockSummaryModel.setProduction_rejection_weight(existingSummMaterislStock.getProduction_rejection_weight() + (summObject.getProduction_rejection_quantity()));
                    productRmStockSummaryModel.setAssembly_production_issue_quantity(existingSummMaterislStock.getAssembly_production_issue_quantity() + (summObject.getAssembly_production_issue_quantity()));
                    productRmStockSummaryModel.setAssembly_production_issue_weight(existingSummMaterislStock.getAssembly_production_issue_weight() + (summObject.getAssembly_production_issue_weight()));
                    productRmStockSummaryModel.setSales_quantity(existingSummMaterislStock.getSales_quantity() + (summObject.getSales_quantity()));
                    productRmStockSummaryModel.setSales_weight(existingSummMaterislStock.getSales_weight() + (summObject.getSales_weight()));
                    productRmStockSummaryModel.setSales_return_quantity(existingSummMaterislStock.getSales_return_quantity() + (summObject.getSales_return_quantity()));
                    productRmStockSummaryModel.setSales_return_weight(existingSummMaterislStock.getSales_return_weight() + (summObject.getSales_return_weight()));
                    productRmStockSummaryModel.setSales_rejection_quantity(existingSummMaterislStock.getSales_rejection_quantity() + (summObject.getSales_rejection_quantity()));
                    productRmStockSummaryModel.setSales_rejection_weight(existingSummMaterislStock.getSales_rejection_weight() + (summObject.getSales_rejection_weight()));
                    productRmStockSummaryModel.setCustomer_receipt_quantity(existingSummMaterislStock.getCustomer_receipt_quantity() + (summObject.getCustomer_receipt_quantity()));
                    productRmStockSummaryModel.setCustomer_receipt_weight(existingSummMaterislStock.getCustomer_receipt_weight() + (summObject.getCustomer_receipt_weight()));
                    productRmStockSummaryModel.setCustomer_return_quantity(existingSummMaterislStock.getCustomer_return_quantity() + (summObject.getCustomer_return_quantity()));
                    productRmStockSummaryModel.setCustomer_return_weight(existingSummMaterislStock.getCustomer_return_weight() + (summObject.getCustomer_return_weight()));
                    productRmStockSummaryModel.setCustomer_rejection_weight(existingSummMaterislStock.getCustomer_rejection_weight() + (summObject.getCustomer_rejection_weight()));
                    productRmStockSummaryModel.setCustomer_rejection_quantity(existingSummMaterislStock.getCustomer_rejection_quantity() + (summObject.getCustomer_rejection_quantity()));
                    productRmStockSummaryModel.setTransfer_issue_quantity(existingSummMaterislStock.getTransfer_issue_quantity() + (summObject.getTransfer_issue_quantity()));
                    productRmStockSummaryModel.setTransfer_issue_weight(existingSummMaterislStock.getTransfer_issue_weight() + (summObject.getTransfer_issue_weight()));
                    productRmStockSummaryModel.setTransfer_receipt_quantity(existingSummMaterislStock.getTransfer_receipt_quantity() + (summObject.getTransfer_receipt_quantity()));
                    productRmStockSummaryModel.setTransfer_receipt_weight(existingSummMaterislStock.getTransfer_receipt_weight() + (summObject.getTransfer_receipt_weight()));
                    productRmStockSummaryModel.setOutsources_out_quantity(existingSummMaterislStock.getOutsources_out_quantity() + (summObject.getOutsources_out_quantity()));
                    productRmStockSummaryModel.setOutsources_out_weight(existingSummMaterislStock.getOutsources_out_weight() + (summObject.getOutsources_out_weight()));
                    productRmStockSummaryModel.setOutsources_in_quantity(existingSummMaterislStock.getOutsources_in_quantity() + (summObject.getOutsources_in_quantity()));
                    productRmStockSummaryModel.setOutsources_in_weight(existingSummMaterislStock.getOutsources_in_weight() + (summObject.getOutsources_in_weight()));
                    productRmStockSummaryModel.setOutsources_rejection_quantity(existingSummMaterislStock.getOutsources_rejection_quantity() + (summObject.getOutsources_rejection_quantity()));
                    productRmStockSummaryModel.setOutsources_rejection_weight(existingSummMaterislStock.getOutsources_rejection_weight() + (summObject.getOutsources_rejection_weight()));
                    productRmStockSummaryModel.setLoan_receipt_quantity(existingSummMaterislStock.getLoan_receipt_quantity() + (summObject.getLoan_receipt_quantity()));
                    productRmStockSummaryModel.setLoan_receipt_weight(existingSummMaterislStock.getLoan_receipt_weight() + (summObject.getLoan_receipt_weight()));
                    productRmStockSummaryModel.setLoan_receipt_boxes(existingSummMaterislStock.getLoan_receipt_boxes() + (summObject.getLoan_receipt_boxes()));
                    productRmStockSummaryModel.setLoan_issue_quantity(existingSummMaterislStock.getLoan_issue_quantity() + (summObject.getLoan_issue_quantity()));
                    productRmStockSummaryModel.setLoan_issue_weight(existingSummMaterislStock.getLoan_issue_weight() + (summObject.getLoan_issue_weight()));
                    productRmStockSummaryModel.setLoan_issue_boxes(existingSummMaterislStock.getLoan_issue_boxes() + (summObject.getLoan_issue_boxes()));
                    productRmStockSummaryModel.setCancel_quantity(existingSummMaterislStock.getCancel_quantity() + (summObject.getCancel_quantity()));
                    productRmStockSummaryModel.setCancel_weight(existingSummMaterislStock.getCancel_weight() + (summObject.getCancel_weight()));
                    productRmStockSummaryModel.setDifference_quantity(existingSummMaterislStock.getDifference_quantity() + (summObject.getDifference_quantity()));
                    productRmStockSummaryModel.setDifference_weight(existingSummMaterislStock.getDifference_weight() + (summObject.getDifference_weight()));
                    productRmStockSummaryModel.setClosing_balance_quantity(existingSummMaterislStock.getClosing_balance_quantity() + (summObject.getClosing_balance_quantity()));
                    productRmStockSummaryModel.setClosing_balance_weight(existingSummMaterislStock.getClosing_balance_weight() + (summObject.getClosing_balance_weight()));
                    productRmStockSummaryModel.setModified_by(summObject.getCreated_by());
                    productRmStockSummaryModel.setGodown_id(summObject.getGodown_id());
                    productRmStockSummaryModel.setGodown_section_id(summObject.getGodown_section_id());
                    productRmStockSummaryModel.setGodown_section_beans_id(summObject.getGodown_section_beans_id());
                    productRmStockSummaryModel.setOpening_no_of_boxes(existingSummMaterislStock.getOpening_no_of_boxes() + (summObject.getOpening_no_of_boxes()));
                    productRmStockSummaryModel.setPurchase_no_of_boxes(existingSummMaterislStock.getPurchase_no_of_boxes() + (summObject.getPurchase_no_of_boxes()));
                    productRmStockSummaryModel.setReserve_no_of_boxes(existingSummMaterislStock.getReserve_no_of_boxes() + (summObject.getReserve_no_of_boxes()));
                    productRmStockSummaryModel.setIssue_no_of_boxes(existingSummMaterislStock.getIssue_no_of_boxes() + (summObject.getIssue_no_of_boxes()));
                    productRmStockSummaryModel.setClosing_no_of_boxes(existingSummMaterislStock.getClosing_no_of_boxes() + (summObject.getClosing_no_of_boxes()));

                    productRmStockSummaryModel.setSupplier_return_quantity(existingSummMaterislStock.getSupplier_return_quantity() + (summObject.getSupplier_return_quantity()));
                    productRmStockSummaryModel.setSupplier_return_weight(existingSummMaterislStock.getSupplier_return_weight() + (summObject.getSupplier_return_weight()));
                    productRmStockSummaryModel.setSupplier_return_boxes(existingSummMaterislStock.getSupplier_return_boxes() + (summObject.getSupplier_return_boxes()));
                    if (iuFlag.equalsIgnoreCase("Increase")) {
                        productRmStockSummaryModel.setMaterial_rate(summObject.getMaterial_rate());
                    }

                    addProductRmStockSummaryList.add(productRmStockSummaryModel);
                } else {
                    productRmStockSummaryModel.setCompany_id(summObject.getCompany_id());
                    productRmStockSummaryModel.setCompany_branch_id(summObject.getCompany_branch_id());
                    productRmStockSummaryModel.setFinancial_year(summObject.getFinancial_year());
                    productRmStockSummaryModel.setCustomer_id(summObject.getCustomer_id());
                    productRmStockSummaryModel.setProduct_type_group(summObject.getProduct_type_group());
                    productRmStockSummaryModel.setProduct_type_id(summObject.getProduct_type_id());
                    productRmStockSummaryModel.setProduct_rm_id(summObject.getProduct_rm_id());
                    productRmStockSummaryModel.setProduct_material_unit_id(summObject.getProduct_material_unit_id());
                    productRmStockSummaryModel.setProduct_material_packing_id(summObject.getProduct_material_packing_id());
                    productRmStockSummaryModel.setCustomer_id(summObject.getCustomer_id());
                    productRmStockSummaryModel.setOrder_quantity(summObject.getOrder_quantity());
                    productRmStockSummaryModel.setOrder_weight(summObject.getOrder_weight());
                    productRmStockSummaryModel.setPending_quantity(summObject.getPending_quantity());
                    productRmStockSummaryModel.setPending_weight(summObject.getPending_weight());
                    productRmStockSummaryModel.setReserve_quantity(summObject.getReserve_quantity());
                    productRmStockSummaryModel.setReserve_weight(summObject.getReserve_weight());
                    productRmStockSummaryModel.setExcess_quantity(summObject.getExcess_quantity());
                    productRmStockSummaryModel.setExcess_weight(summObject.getExcess_weight());
                    productRmStockSummaryModel.setPree_closed_quantity(summObject.getPree_closed_quantity());
                    productRmStockSummaryModel.setPree_closed_weight(summObject.getPree_closed_weight());
                    productRmStockSummaryModel.setPurchase_quantity(summObject.getPurchase_quantity());
                    productRmStockSummaryModel.setPurchase_weight(summObject.getPurchase_weight());
                    productRmStockSummaryModel.setPurchase_return_quantity(summObject.getPurchase_return_quantity());
                    productRmStockSummaryModel.setPurchase_return_weight(summObject.getPurchase_return_weight());
                    productRmStockSummaryModel.setPurchase_rejection_quantity(summObject.getPurchase_rejection_quantity());
                    productRmStockSummaryModel.setPurchase_rejection_weight(summObject.getPurchase_rejection_weight());
                    productRmStockSummaryModel.setJobcard_quantity(summObject.getJobcard_quantity());
                    productRmStockSummaryModel.setJobcard_weight(summObject.getJobcard_weight());
                    productRmStockSummaryModel.setProduction_issue_quantity(summObject.getProduction_issue_quantity());
                    productRmStockSummaryModel.setProduction_issue_weight(summObject.getProduction_issue_weight());
                    productRmStockSummaryModel.setProduction_issue_return_quantity(summObject.getProduction_issue_return_quantity());
                    productRmStockSummaryModel.setProduction_issue_return_weight(summObject.getProduction_issue_return_weight());
                    productRmStockSummaryModel.setProduction_issue_rejection_quantity(summObject.getProduction_issue_rejection_quantity());
                    productRmStockSummaryModel.setProduction_issue_rejection_weight(summObject.getProduction_issue_rejection_weight());
                    productRmStockSummaryModel.setProduction_quantity(summObject.getProduction_quantity());
                    productRmStockSummaryModel.setProduction_weight(summObject.getProduction_weight());
                    productRmStockSummaryModel.setProduction_return_quantity(summObject.getProduction_return_quantity());
                    productRmStockSummaryModel.setProduction_return_weight(summObject.getProduction_return_weight());
                    productRmStockSummaryModel.setProduction_rejection_quantity(summObject.getProduction_rejection_quantity());
                    productRmStockSummaryModel.setProduction_rejection_weight(summObject.getProduction_rejection_quantity());
                    productRmStockSummaryModel.setAssembly_production_issue_quantity(summObject.getAssembly_production_issue_quantity());
                    productRmStockSummaryModel.setAssembly_production_issue_weight(summObject.getAssembly_production_issue_weight());
                    productRmStockSummaryModel.setSales_quantity(summObject.getSales_quantity());
                    productRmStockSummaryModel.setSales_weight(summObject.getSales_weight());
                    productRmStockSummaryModel.setSales_return_quantity(summObject.getSales_return_quantity());
                    productRmStockSummaryModel.setSales_return_weight(summObject.getSales_return_weight());
                    productRmStockSummaryModel.setSales_rejection_quantity(summObject.getSales_rejection_quantity());
                    productRmStockSummaryModel.setSales_rejection_weight(summObject.getSales_rejection_weight());
                    productRmStockSummaryModel.setCustomer_receipt_quantity(summObject.getCustomer_receipt_quantity());
                    productRmStockSummaryModel.setCustomer_receipt_weight(summObject.getCustomer_receipt_weight());
                    productRmStockSummaryModel.setCustomer_return_quantity(summObject.getCustomer_return_quantity());
                    productRmStockSummaryModel.setCustomer_return_weight(summObject.getCustomer_return_weight());
                    productRmStockSummaryModel.setCustomer_rejection_weight(summObject.getCustomer_rejection_weight());
                    productRmStockSummaryModel.setCustomer_rejection_quantity(summObject.getCustomer_rejection_quantity());
                    productRmStockSummaryModel.setTransfer_issue_quantity(summObject.getTransfer_issue_quantity());
                    productRmStockSummaryModel.setTransfer_issue_weight(summObject.getTransfer_issue_weight());
                    productRmStockSummaryModel.setTransfer_receipt_quantity(summObject.getTransfer_receipt_quantity());
                    productRmStockSummaryModel.setTransfer_receipt_weight(summObject.getTransfer_receipt_weight());
                    productRmStockSummaryModel.setOutsources_out_quantity(summObject.getOutsources_out_quantity());
                    productRmStockSummaryModel.setOutsources_out_weight(summObject.getOutsources_out_weight());
                    productRmStockSummaryModel.setOutsources_in_quantity(summObject.getOutsources_in_quantity());
                    productRmStockSummaryModel.setOutsources_in_weight(summObject.getOutsources_in_weight());
                    productRmStockSummaryModel.setOutsources_rejection_quantity(summObject.getOutsources_rejection_quantity());
                    productRmStockSummaryModel.setOutsources_rejection_weight(summObject.getOutsources_rejection_weight());
                    productRmStockSummaryModel.setLoan_receipt_quantity(summObject.getLoan_receipt_quantity());
                    productRmStockSummaryModel.setLoan_receipt_weight(summObject.getLoan_receipt_weight());
                    productRmStockSummaryModel.setLoan_receipt_boxes(summObject.getLoan_receipt_boxes());
                    productRmStockSummaryModel.setLoan_issue_quantity(summObject.getLoan_issue_quantity());
                    productRmStockSummaryModel.setLoan_issue_weight(summObject.getLoan_issue_weight());
                    productRmStockSummaryModel.setLoan_issue_boxes(summObject.getLoan_issue_boxes());
                    productRmStockSummaryModel.setCancel_quantity(summObject.getCancel_quantity());
                    productRmStockSummaryModel.setCancel_weight(summObject.getCancel_weight());
                    productRmStockSummaryModel.setDifference_quantity(summObject.getDifference_quantity());
                    productRmStockSummaryModel.setDifference_weight(summObject.getDifference_weight());
                    productRmStockSummaryModel.setClosing_balance_quantity(summObject.getClosing_balance_quantity());
                    productRmStockSummaryModel.setClosing_balance_weight(summObject.getClosing_balance_weight());
                    productRmStockSummaryModel.setOpening_quantity(summObject.getOpening_quantity());
                    productRmStockSummaryModel.setOpening_weight(summObject.getOpening_weight());
                    productRmStockSummaryModel.setCreated_by(summObject.getCreated_by());
                    productRmStockSummaryModel.setGodown_id(godown_id);
                    productRmStockSummaryModel.setGodown_section_id(godown_section_id);
                    productRmStockSummaryModel.setGodown_section_beans_id(godown_section_beans_id);
                    productRmStockSummaryModel.setTotal_box_weight(summObject.getTotal_box_weight());
                    productRmStockSummaryModel.setTotal_quantity_in_box(summObject.getTotal_quantity_in_box());
                    productRmStockSummaryModel.setWeight_per_box_item(summObject.getWeight_per_box_item());
                    productRmStockSummaryModel.setOpening_no_of_boxes(summObject.getOpening_no_of_boxes());
                    productRmStockSummaryModel.setPurchase_no_of_boxes(summObject.getPurchase_no_of_boxes());
                    productRmStockSummaryModel.setReserve_no_of_boxes(summObject.getReserve_no_of_boxes());
                    productRmStockSummaryModel.setIssue_no_of_boxes(summObject.getIssue_no_of_boxes());
                    productRmStockSummaryModel.setClosing_no_of_boxes(summObject.getClosing_no_of_boxes());
                    productRmStockSummaryModel.setMaterial_rate(summObject.getMaterial_rate());

                    addProductRmStockSummaryList.add(productRmStockSummaryModel);
                }
            });
        }

//          Check incoming stock is available in our stock then if available the update it or add it (For Raw material stock details table)
        if (getRmStockDetails != null && getRmStockDetails.size() != 0) {
            getRmStockDetails.forEach(detailsObject -> {
                CSmProductRmStockDetailsModel productRmStockDetailsModel = new CSmProductRmStockDetailsModel();
                String material_id = detailsObject.getProduct_rm_id();
                String good_receipt_no = detailsObject.getGoods_receipt_no();
                String batch_no = detailsObject.getBatch_no();
                Double cone_per_wt = detailsObject.getWeight_per_box_item();
                Integer godown_id = detailsObject.getGodown_id();
                Integer godown_section_id = detailsObject.getGodown_section_id();
                Integer godown_section_bean_id = detailsObject.getGodown_section_beans_id();


//				    Find the object in productRmStockDetailsList based on material_id & good receipt no
//                Optional<CSmProductRmStockDetailsModel> matchingObject = productRmStockDetailsList.get().stream()
//                        .filter(item -> material_id.equals(item.getProduct_rm_id())
//                                && good_receipt_no.equals(item.getGoods_receipt_no()))
//                        .findFirst();
                Optional<CSmProductRmStockDetailsModel> matchingObject = productRmStockDetailsList.get().stream()
                        .filter(item -> material_id.equals(item.getProduct_rm_id())
                                && good_receipt_no.equals(item.getGoods_receipt_no())
                                && cone_per_wt.equals(item.getWeight_per_box_item())
                                && godown_id.equals(item.getGodown_id())
                                && godown_section_id.equals(item.getGodown_section_id())
                                && godown_section_bean_id.equals(item.getGodown_section_beans_id())
                                && batch_no.equals(item.getBatch_no()))
                        .findFirst();

//                  Matching object present using material Id then update it otherwise insert it
                if (matchingObject.isPresent()) {
                    CSmProductRmStockDetailsModel existingSummMaterislStock = matchingObject.get();

                    BeanUtils.copyProperties(existingSummMaterislStock, productRmStockDetailsModel);

//						Concat existing no & new no
                    String existingBatchNo = existingSummMaterislStock.getBatch_no();
                    String newBatchNo = detailsObject.getBatch_no();
                    String existingBatchExpiryDate = existingSummMaterislStock.getBatch_expiry_date();
                    String existingGoodsReceiptNo = existingSummMaterislStock.getGoods_receipt_no();
                    String existingSalesOrderNo = existingSummMaterislStock.getSales_order_no();
                    String existingCustomerOrderNo = existingSummMaterislStock.getCustomer_order_no();
                    String existingCustomerGoodsReceiptNo = existingSummMaterislStock.getCustomer_goods_receipt_no();
                    Integer existingGodownId = existingSummMaterislStock.getGodown_id();
                    Integer existingGodownSectionId = existingSummMaterislStock.getGodown_section_id();
                    Integer existingGodownSectionBeansId = existingSummMaterislStock.getGodown_section_beans_id();

                    if (iuFlag.equalsIgnoreCase("stockAdjustment")) {
                        productRmStockDetailsModel.setBatch_rate(detailsObject.getBatch_rate());
                    }
                    productRmStockDetailsModel.setBatch_no((newBatchNo != null && !newBatchNo.isEmpty()) ? newBatchNo : existingBatchNo);
                    productRmStockDetailsModel.setBatch_expiry_date((detailsObject.getBatch_expiry_date() != null && !detailsObject.getBatch_expiry_date().isEmpty()) ? detailsObject.getBatch_expiry_date() : existingBatchExpiryDate);
                    productRmStockDetailsModel.setGoods_receipt_no((detailsObject.getGoods_receipt_no() != null && !detailsObject.getGoods_receipt_no().isEmpty()) ? detailsObject.getGoods_receipt_no() : existingGoodsReceiptNo);
                    productRmStockDetailsModel.setSales_order_no((detailsObject.getSales_order_no() != null && !detailsObject.getSales_order_no().isEmpty()) ? detailsObject.getSales_order_no() : existingSalesOrderNo);
                    productRmStockDetailsModel.setCustomer_order_no((detailsObject.getCustomer_order_no() != null && !detailsObject.getCustomer_order_no().isEmpty()) ? detailsObject.getCustomer_order_no() : existingCustomerOrderNo);
                    productRmStockDetailsModel.setCustomer_goods_receipt_no((detailsObject.getCustomer_goods_receipt_no() != null && !detailsObject.getCustomer_goods_receipt_no().isEmpty()) ? detailsObject.getCustomer_goods_receipt_no() : existingCustomerGoodsReceiptNo);
                    productRmStockDetailsModel.setGodown_id((detailsObject.getGodown_id() != null && detailsObject.getGodown_id() != 0) ? detailsObject.getGodown_id() : existingGodownId);
                    productRmStockDetailsModel.setGodown_section_id((detailsObject.getGodown_section_id() != null && detailsObject.getGodown_section_id() != 0) ? detailsObject.getGodown_section_id() : existingGodownSectionId);
                    productRmStockDetailsModel.setGodown_section_beans_id((detailsObject.getGodown_section_beans_id() != null && detailsObject.getGodown_section_beans_id() != 0) ? detailsObject.getGodown_section_beans_id() : existingGodownSectionBeansId);
                    productRmStockDetailsModel.setOrder_quantity(existingSummMaterislStock.getOrder_quantity() + (detailsObject.getOrder_quantity()));
                    productRmStockDetailsModel.setOrder_weight(existingSummMaterislStock.getOrder_weight() + (detailsObject.getOrder_weight()));
                    productRmStockDetailsModel.setPending_quantity(existingSummMaterislStock.getPending_quantity() + (detailsObject.getPending_quantity()));
                    productRmStockDetailsModel.setPending_weight(existingSummMaterislStock.getPending_weight() + (detailsObject.getPending_weight()));
                    productRmStockDetailsModel.setReserve_quantity(existingSummMaterislStock.getReserve_quantity() + (detailsObject.getReserve_quantity()));
                    productRmStockDetailsModel.setReserve_weight(existingSummMaterislStock.getReserve_weight() + (detailsObject.getReserve_weight()));
                    productRmStockDetailsModel.setPree_closed_quantity(existingSummMaterislStock.getPree_closed_quantity() + (detailsObject.getPree_closed_quantity()));
                    productRmStockDetailsModel.setPree_closed_weight(existingSummMaterislStock.getPree_closed_weight() + (detailsObject.getPree_closed_weight()));
                    productRmStockDetailsModel.setPurchase_return_quantity(existingSummMaterislStock.getPurchase_return_quantity() + (detailsObject.getPurchase_return_quantity()));
                    productRmStockDetailsModel.setPurchase_return_weight(existingSummMaterislStock.getPurchase_return_weight() + (detailsObject.getPurchase_return_weight()));
                    productRmStockDetailsModel.setPurchase_rejection_quantity(existingSummMaterislStock.getPurchase_rejection_quantity() + (detailsObject.getPurchase_rejection_quantity()));
                    productRmStockDetailsModel.setPurchase_rejection_weight(existingSummMaterislStock.getPurchase_rejection_weight() + (detailsObject.getPurchase_rejection_weight()));
                    productRmStockDetailsModel.setJobcard_quantity(existingSummMaterislStock.getJobcard_quantity() + (detailsObject.getJobcard_quantity()));
                    productRmStockDetailsModel.setJobcard_weight(existingSummMaterislStock.getJobcard_weight() + (detailsObject.getJobcard_weight()));
                    productRmStockDetailsModel.setProduction_issue_quantity(existingSummMaterislStock.getProduction_issue_quantity() + (detailsObject.getProduction_issue_quantity()));
                    productRmStockDetailsModel.setProduction_issue_weight(existingSummMaterislStock.getProduction_issue_weight() + (detailsObject.getProduction_issue_weight()));
                    productRmStockDetailsModel.setProduction_issue_return_quantity(existingSummMaterislStock.getProduction_issue_return_quantity() + (detailsObject.getProduction_issue_return_quantity()));
                    productRmStockDetailsModel.setProduction_issue_return_weight(existingSummMaterislStock.getProduction_issue_return_weight() + (detailsObject.getProduction_issue_return_weight()));
                    productRmStockDetailsModel.setProduction_issue_rejection_quantity(existingSummMaterislStock.getProduction_issue_rejection_quantity() + (detailsObject.getProduction_issue_rejection_quantity()));
                    productRmStockDetailsModel.setProduction_issue_rejection_weight(existingSummMaterislStock.getProduction_issue_rejection_weight() + (detailsObject.getProduction_issue_rejection_weight()));
                    productRmStockDetailsModel.setProduction_quantity(existingSummMaterislStock.getProduction_quantity() + (detailsObject.getProduction_quantity()));
                    productRmStockDetailsModel.setProduction_weight(existingSummMaterislStock.getProduction_weight() + (detailsObject.getProduction_weight()));
                    productRmStockDetailsModel.setProduction_return_quantity(existingSummMaterislStock.getProduction_return_quantity() + (detailsObject.getProduction_return_quantity()));
                    productRmStockDetailsModel.setProduction_return_weight(existingSummMaterislStock.getProduction_return_weight() + (detailsObject.getProduction_return_weight()));
                    productRmStockDetailsModel.setProduction_rejection_quantity(existingSummMaterislStock.getProduction_rejection_quantity() + (detailsObject.getProduction_rejection_quantity()));
                    productRmStockDetailsModel.setProduction_rejection_weight(existingSummMaterislStock.getProduction_rejection_weight() + (detailsObject.getProduction_rejection_quantity()));
                    productRmStockDetailsModel.setAssembly_production_issue_quantity(existingSummMaterislStock.getAssembly_production_issue_quantity() + (detailsObject.getAssembly_production_issue_quantity()));
                    productRmStockDetailsModel.setAssembly_production_issue_weight(existingSummMaterislStock.getAssembly_production_issue_weight() + (detailsObject.getAssembly_production_issue_weight()));
                    productRmStockDetailsModel.setSales_quantity(existingSummMaterislStock.getSales_quantity() + (detailsObject.getSales_quantity()));
                    productRmStockDetailsModel.setSales_weight(existingSummMaterislStock.getSales_weight() + (detailsObject.getSales_weight()));
                    productRmStockDetailsModel.setSales_return_quantity(existingSummMaterislStock.getSales_return_quantity() + (detailsObject.getSales_return_quantity()));
                    productRmStockDetailsModel.setSales_return_weight(existingSummMaterislStock.getSales_return_weight() + (detailsObject.getSales_return_weight()));
                    productRmStockDetailsModel.setSales_rejection_quantity(existingSummMaterislStock.getSales_rejection_quantity() + (detailsObject.getSales_rejection_quantity()));
                    productRmStockDetailsModel.setSales_rejection_weight(existingSummMaterislStock.getSales_rejection_weight() + (detailsObject.getSales_rejection_weight()));
                    productRmStockDetailsModel.setCustomer_receipt_quantity(existingSummMaterislStock.getCustomer_receipt_quantity() + (detailsObject.getCustomer_receipt_quantity()));
                    productRmStockDetailsModel.setCustomer_receipt_weight(existingSummMaterislStock.getCustomer_receipt_weight() + (detailsObject.getCustomer_receipt_weight()));
                    productRmStockDetailsModel.setCustomer_return_quantity(existingSummMaterislStock.getCustomer_return_quantity() + (detailsObject.getCustomer_return_quantity()));
                    productRmStockDetailsModel.setCustomer_return_weight(existingSummMaterislStock.getCustomer_return_weight() + (detailsObject.getCustomer_return_weight()));
                    productRmStockDetailsModel.setCustomer_rejection_weight(existingSummMaterislStock.getCustomer_rejection_weight() + (detailsObject.getCustomer_rejection_weight()));
                    productRmStockDetailsModel.setCustomer_rejection_quantity(existingSummMaterislStock.getCustomer_rejection_quantity() + (detailsObject.getCustomer_rejection_quantity()));
                    productRmStockDetailsModel.setTransfer_issue_quantity(existingSummMaterislStock.getTransfer_issue_quantity() + (detailsObject.getTransfer_issue_quantity()));
                    productRmStockDetailsModel.setTransfer_issue_weight(existingSummMaterislStock.getTransfer_issue_weight() + (detailsObject.getTransfer_issue_weight()));
                    if (iuFlag.equalsIgnoreCase("Increase")) {
                        productRmStockDetailsModel.setExcess_quantity(existingSummMaterislStock.getExcess_quantity() + (detailsObject.getExcess_quantity()));
                        productRmStockDetailsModel.setExcess_weight(existingSummMaterislStock.getExcess_weight() + (detailsObject.getExcess_weight()));
                        productRmStockDetailsModel.setTransfer_receipt_quantity(existingSummMaterislStock.getTransfer_receipt_quantity() + (detailsObject.getTransfer_receipt_quantity()));
                        productRmStockDetailsModel.setTransfer_receipt_weight(existingSummMaterislStock.getTransfer_receipt_weight() + (detailsObject.getTransfer_receipt_weight()));
                    }
                    productRmStockDetailsModel.setOutsources_out_quantity(existingSummMaterislStock.getOutsources_out_quantity() + (detailsObject.getOutsources_out_quantity()));
                    productRmStockDetailsModel.setOutsources_out_weight(existingSummMaterislStock.getOutsources_out_weight() + (detailsObject.getOutsources_out_weight()));
                    productRmStockDetailsModel.setOutsources_in_quantity(existingSummMaterislStock.getOutsources_in_quantity() + (detailsObject.getOutsources_in_quantity()));
                    productRmStockDetailsModel.setOutsources_in_weight(existingSummMaterislStock.getOutsources_in_weight() + (detailsObject.getOutsources_in_weight()));
                    productRmStockDetailsModel.setOutsources_rejection_quantity(existingSummMaterislStock.getOutsources_rejection_quantity() + (detailsObject.getOutsources_rejection_quantity()));
                    productRmStockDetailsModel.setOutsources_rejection_weight(existingSummMaterislStock.getOutsources_rejection_weight() + (detailsObject.getOutsources_rejection_weight()));
                    productRmStockDetailsModel.setLoan_receipt_quantity(existingSummMaterislStock.getLoan_receipt_quantity() + (detailsObject.getLoan_receipt_quantity()));
                    productRmStockDetailsModel.setLoan_receipt_weight(existingSummMaterislStock.getLoan_receipt_weight() + (detailsObject.getLoan_receipt_weight()));
                    productRmStockDetailsModel.setLoan_receipt_boxes(existingSummMaterislStock.getLoan_receipt_boxes() + (detailsObject.getLoan_receipt_boxes()));
                    productRmStockDetailsModel.setLoan_issue_quantity(existingSummMaterislStock.getLoan_issue_quantity() + (detailsObject.getLoan_issue_quantity()));
                    productRmStockDetailsModel.setLoan_issue_weight(existingSummMaterislStock.getLoan_issue_weight() + (detailsObject.getLoan_issue_weight()));
                    productRmStockDetailsModel.setLoan_issue_boxes(existingSummMaterislStock.getLoan_issue_boxes() + (detailsObject.getLoan_issue_boxes()));
                    productRmStockDetailsModel.setCancel_quantity(existingSummMaterislStock.getCancel_quantity() + (detailsObject.getCancel_quantity()));
                    productRmStockDetailsModel.setCancel_weight(existingSummMaterislStock.getCancel_weight() + (detailsObject.getCancel_weight()));
                    productRmStockDetailsModel.setDifference_quantity(existingSummMaterislStock.getDifference_quantity() + (detailsObject.getDifference_quantity()));
                    productRmStockDetailsModel.setDifference_weight(existingSummMaterislStock.getDifference_weight() + (detailsObject.getDifference_weight()));
                    productRmStockDetailsModel.setClosing_balance_quantity(existingSummMaterislStock.getClosing_balance_quantity() + (detailsObject.getClosing_balance_quantity()));
                    productRmStockDetailsModel.setClosing_balance_weight(existingSummMaterislStock.getClosing_balance_weight() + (detailsObject.getClosing_balance_weight()));
                    productRmStockDetailsModel.setOpening_no_of_boxes(existingSummMaterislStock.getOpening_no_of_boxes() + (detailsObject.getOpening_no_of_boxes()));
                    productRmStockDetailsModel.setPurchase_no_of_boxes(existingSummMaterislStock.getPurchase_no_of_boxes() + (detailsObject.getPurchase_no_of_boxes()));
                    productRmStockDetailsModel.setReserve_no_of_boxes(existingSummMaterislStock.getReserve_no_of_boxes() + (detailsObject.getReserve_no_of_boxes()));
                    productRmStockDetailsModel.setIssue_no_of_boxes(existingSummMaterislStock.getIssue_no_of_boxes() + (detailsObject.getIssue_no_of_boxes()));
                    productRmStockDetailsModel.setClosing_no_of_boxes(existingSummMaterislStock.getClosing_no_of_boxes() + (detailsObject.getClosing_no_of_boxes()));

                    productRmStockDetailsModel.setSupplier_return_quantity(existingSummMaterislStock.getSupplier_return_quantity() + (detailsObject.getSupplier_return_quantity()));
                    productRmStockDetailsModel.setSupplier_return_weight(existingSummMaterislStock.getSupplier_return_weight() + (detailsObject.getSupplier_return_weight()));
                    productRmStockDetailsModel.setSupplier_return_boxes(existingSummMaterislStock.getSupplier_return_boxes() + (detailsObject.getSupplier_return_boxes()));

                    addProductRmStockDetailsList.add(productRmStockDetailsModel);
                    batchObject.put("goods_receipt_no", existingSummMaterislStock.getGoods_receipt_no());
                    batchObject.put("issue_date", existingSummMaterislStock.getStock_date());
                    batchObject.put("product_material_id", existingSummMaterislStock.getProduct_rm_id());
                    batchList.add(batchObject);

                } else {
                    productRmStockDetailsModel.setCompany_id(detailsObject.getCompany_id());
                    productRmStockDetailsModel.setCompany_branch_id(detailsObject.getCompany_branch_id());
                    productRmStockDetailsModel.setFinancial_year(detailsObject.getFinancial_year());
                    productRmStockDetailsModel.setSupplier_id(detailsObject.getSupplier_id());
                    productRmStockDetailsModel.setCustomer_id(detailsObject.getCustomer_id());
                    productRmStockDetailsModel.setProduct_type_group(detailsObject.getProduct_type_group());
                    productRmStockDetailsModel.setProduct_type_id(detailsObject.getProduct_type_id());
                    productRmStockDetailsModel.setProduct_rm_id(detailsObject.getProduct_rm_id());
                    productRmStockDetailsModel.setProduct_material_unit_id(detailsObject.getProduct_material_unit_id());
                    productRmStockDetailsModel.setProduct_material_packing_id(detailsObject.getProduct_material_packing_id());

                    productRmStockDetailsModel.setBatch_rate(detailsObject.getBatch_rate());
                    productRmStockDetailsModel.setBatch_no(detailsObject.getBatch_no());
                    productRmStockDetailsModel.setBatch_expiry_date(detailsObject.getBatch_expiry_date());
                    productRmStockDetailsModel.setGoods_receipt_no(detailsObject.getGoods_receipt_no());
                    productRmStockDetailsModel.setSales_order_no(detailsObject.getSales_order_no());
                    productRmStockDetailsModel.setCustomer_order_no(detailsObject.getCustomer_order_no());
                    productRmStockDetailsModel.setCustomer_goods_receipt_no(detailsObject.getCustomer_goods_receipt_no());

                    productRmStockDetailsModel.setStock_date(detailsObject.getStock_date());
                    productRmStockDetailsModel.setCustomer_id(detailsObject.getCustomer_id());
                    productRmStockDetailsModel.setOrder_quantity(detailsObject.getOrder_quantity());
                    productRmStockDetailsModel.setOrder_weight(detailsObject.getOrder_weight());
                    productRmStockDetailsModel.setPending_quantity(detailsObject.getPending_quantity());
                    productRmStockDetailsModel.setPending_weight(detailsObject.getPending_weight());
                    productRmStockDetailsModel.setReserve_quantity(detailsObject.getReserve_quantity());
                    productRmStockDetailsModel.setReserve_weight(detailsObject.getReserve_weight());
                    productRmStockDetailsModel.setExcess_quantity(detailsObject.getExcess_quantity());
                    productRmStockDetailsModel.setExcess_weight(detailsObject.getExcess_weight());
                    productRmStockDetailsModel.setPree_closed_quantity(detailsObject.getPree_closed_quantity());
                    productRmStockDetailsModel.setPree_closed_weight(detailsObject.getPree_closed_weight());
                    productRmStockDetailsModel.setPurchase_quantity(detailsObject.getPurchase_quantity());
                    productRmStockDetailsModel.setPurchase_weight(detailsObject.getPurchase_weight());
                    productRmStockDetailsModel.setPurchase_return_quantity(detailsObject.getPurchase_return_quantity());
                    productRmStockDetailsModel.setPurchase_return_weight(detailsObject.getPurchase_return_weight());
                    productRmStockDetailsModel.setPurchase_rejection_quantity(detailsObject.getPurchase_rejection_quantity());
                    productRmStockDetailsModel.setPurchase_rejection_weight(detailsObject.getPurchase_rejection_weight());
                    productRmStockDetailsModel.setJobcard_quantity(detailsObject.getJobcard_quantity());
                    productRmStockDetailsModel.setJobcard_weight(detailsObject.getJobcard_weight());
                    productRmStockDetailsModel.setProduction_issue_quantity(detailsObject.getProduction_issue_quantity());
                    productRmStockDetailsModel.setProduction_issue_weight(detailsObject.getProduction_issue_weight());
                    productRmStockDetailsModel.setProduction_issue_return_quantity(detailsObject.getProduction_issue_return_quantity());
                    productRmStockDetailsModel.setProduction_issue_return_weight(detailsObject.getProduction_issue_return_weight());
                    productRmStockDetailsModel.setProduction_issue_rejection_quantity(detailsObject.getProduction_issue_rejection_quantity());
                    productRmStockDetailsModel.setProduction_issue_rejection_weight(detailsObject.getProduction_issue_rejection_weight());
                    productRmStockDetailsModel.setProduction_quantity(detailsObject.getProduction_quantity());
                    productRmStockDetailsModel.setProduction_weight(detailsObject.getProduction_weight());
                    productRmStockDetailsModel.setProduction_return_quantity(detailsObject.getProduction_return_quantity());
                    productRmStockDetailsModel.setProduction_return_weight(detailsObject.getProduction_return_weight());
                    productRmStockDetailsModel.setProduction_rejection_quantity(detailsObject.getProduction_rejection_quantity());
                    productRmStockDetailsModel.setProduction_rejection_weight(detailsObject.getProduction_rejection_quantity());
                    productRmStockDetailsModel.setAssembly_production_issue_quantity(detailsObject.getAssembly_production_issue_quantity());
                    productRmStockDetailsModel.setAssembly_production_issue_weight(detailsObject.getAssembly_production_issue_weight());
                    productRmStockDetailsModel.setSales_quantity(detailsObject.getSales_quantity());
                    productRmStockDetailsModel.setSales_weight(detailsObject.getSales_weight());
                    productRmStockDetailsModel.setSales_return_quantity(detailsObject.getSales_return_quantity());
                    productRmStockDetailsModel.setSales_return_weight(detailsObject.getSales_return_weight());
                    productRmStockDetailsModel.setSales_rejection_quantity(detailsObject.getSales_rejection_quantity());
                    productRmStockDetailsModel.setSales_rejection_weight(detailsObject.getSales_rejection_weight());
                    productRmStockDetailsModel.setCustomer_receipt_quantity(detailsObject.getCustomer_receipt_quantity());
                    productRmStockDetailsModel.setCustomer_receipt_weight(detailsObject.getCustomer_receipt_weight());
                    productRmStockDetailsModel.setCustomer_return_quantity(detailsObject.getCustomer_return_quantity());
                    productRmStockDetailsModel.setCustomer_return_weight(detailsObject.getCustomer_return_weight());
                    productRmStockDetailsModel.setCustomer_rejection_weight(detailsObject.getCustomer_rejection_weight());
                    productRmStockDetailsModel.setCustomer_rejection_quantity(detailsObject.getCustomer_rejection_quantity());
                    productRmStockDetailsModel.setTransfer_issue_quantity(detailsObject.getTransfer_issue_quantity());
                    productRmStockDetailsModel.setTransfer_issue_weight(detailsObject.getTransfer_issue_weight());
                    productRmStockDetailsModel.setTransfer_receipt_quantity(detailsObject.getTransfer_receipt_quantity());
                    productRmStockDetailsModel.setTransfer_receipt_weight(detailsObject.getTransfer_receipt_weight());
                    productRmStockDetailsModel.setOutsources_out_quantity(detailsObject.getOutsources_out_quantity());
                    productRmStockDetailsModel.setOutsources_out_weight(detailsObject.getOutsources_out_weight());
                    productRmStockDetailsModel.setOutsources_in_quantity(detailsObject.getOutsources_in_quantity());
                    productRmStockDetailsModel.setOutsources_in_weight(detailsObject.getOutsources_in_weight());
                    productRmStockDetailsModel.setOutsources_rejection_quantity(detailsObject.getOutsources_rejection_quantity());
                    productRmStockDetailsModel.setOutsources_rejection_weight(detailsObject.getOutsources_rejection_weight());
                    productRmStockDetailsModel.setLoan_receipt_quantity(detailsObject.getLoan_receipt_quantity());
                    productRmStockDetailsModel.setLoan_receipt_weight(detailsObject.getLoan_receipt_weight());
                    productRmStockDetailsModel.setLoan_receipt_boxes(detailsObject.getLoan_receipt_boxes());
                    productRmStockDetailsModel.setLoan_issue_quantity(detailsObject.getLoan_issue_quantity());
                    productRmStockDetailsModel.setLoan_issue_weight(detailsObject.getLoan_issue_weight());
                    productRmStockDetailsModel.setLoan_issue_boxes(detailsObject.getLoan_issue_boxes());
                    productRmStockDetailsModel.setCancel_quantity(detailsObject.getCancel_quantity());
                    productRmStockDetailsModel.setCancel_weight(detailsObject.getCancel_weight());
                    productRmStockDetailsModel.setDifference_quantity(detailsObject.getDifference_quantity());
                    productRmStockDetailsModel.setDifference_weight(detailsObject.getDifference_weight());
                    productRmStockDetailsModel.setClosing_balance_quantity(detailsObject.getClosing_balance_quantity());
                    productRmStockDetailsModel.setClosing_balance_weight(detailsObject.getClosing_balance_weight());
                    productRmStockDetailsModel.setOpening_quantity(detailsObject.getOpening_quantity());
                    productRmStockDetailsModel.setOpening_weight(detailsObject.getOpening_weight());
                    productRmStockDetailsModel.setGodown_id(detailsObject.getGodown_id());
                    productRmStockDetailsModel.setGodown_section_id(detailsObject.getGodown_section_id());
                    productRmStockDetailsModel.setGodown_section_beans_id(detailsObject.getGodown_section_beans_id());
                    productRmStockDetailsModel.setTotal_box_weight(detailsObject.getTotal_box_weight());
                    productRmStockDetailsModel.setTotal_quantity_in_box(detailsObject.getTotal_quantity_in_box());
                    productRmStockDetailsModel.setWeight_per_box_item(detailsObject.getWeight_per_box_item());
                    productRmStockDetailsModel.setOpening_no_of_boxes(detailsObject.getOpening_no_of_boxes());
                    productRmStockDetailsModel.setPurchase_no_of_boxes(detailsObject.getPurchase_no_of_boxes());
                    productRmStockDetailsModel.setReserve_no_of_boxes(detailsObject.getReserve_no_of_boxes());
                    productRmStockDetailsModel.setIssue_no_of_boxes(detailsObject.getIssue_no_of_boxes());
                    productRmStockDetailsModel.setClosing_no_of_boxes(detailsObject.getClosing_no_of_boxes());

                    addProductRmStockDetailsList.add(productRmStockDetailsModel);
                }
            });
        }

        if (getStockTrackingDetails != null && getStockTrackingDetails.size() != 0) {
            AtomicReference<List<CSmProductStockTracking>> productRmStockTrackingDetailsListCopy = new AtomicReference<>();

            if (productRmStockTrackingDetailsList != null) {
                List<CSmProductStockTracking> trackingList = productRmStockTrackingDetailsList.get().stream()
                        .map(details -> {
                            CSmProductStockTracking copy = new CSmProductStockTracking();
                            BeanUtils.copyProperties(details, copy);
                            return copy;
                        })
                        .collect(Collectors.toList());

                productRmStockTrackingDetailsListCopy.set(trackingList);
            }

            getStockTrackingDetails.forEach(trackingObject -> {
                CSmProductStockTracking smProductStockTracking = new CSmProductStockTracking();

                String material_id = trackingObject.getProduct_material_id();
                String good_receipt_no = trackingObject.getGoods_receipt_no();

//				Find the object in productRmStockDetailsList based on material_id & good receipt no
                Optional<CSmProductStockTracking> matchingObject = null;
                matchingObject = productRmStockTrackingDetailsList.get().stream()
                        .filter(item -> material_id.equals(item.getProduct_material_id())
                                && good_receipt_no.equals(item.getGoods_receipt_no())
                                && !item.is_stock_consumed())
                        .findFirst();

                if (!matchingObject.isPresent()) {
//                  from list added to save we need to find object after consumption
                    matchingObject = addProductStockTracking.stream()
                            .filter(item -> material_id.equals(item.getProduct_material_id())
                                    && good_receipt_no.equals(item.getGoods_receipt_no())
                                    && !item.is_stock_consumed())
                            .findFirst();

                }

                if (matchingObject.isPresent()) {
                    smProductStockTracking = matchingObject.get();

                    if (!trackingObject.isStockAddOrConsume()) {    // if it is stock consume then isStockAddOrConsume status will be false
                        smProductStockTracking.setConsumption_no(trackingObject.getConsumption_no());
                        smProductStockTracking.setConsumption_detail_no(trackingObject.getConsumption_detail_no());
                        smProductStockTracking.setConsumption_date(trackingObject.getConsumption_date());
                        smProductStockTracking.setConsumption_location(trackingObject.getConsumption_location());
                        smProductStockTracking.setConsumption_quantity(trackingObject.getConsumption_quantity());
                        smProductStockTracking.setCreated_by(trackingObject.getCreated_by());
                        smProductStockTracking.setModified_by(trackingObject.getCreated_by());
                        smProductStockTracking.set_stock_consumed(true);
                    } else {
                        smProductStockTracking.setStock_quantity(smProductStockTracking.getStock_quantity() + trackingObject.getStock_quantity());
                    }


//					Add one new entry after consumption
                    if (matchingObject.get().getStock_quantity() - smProductStockTracking.getConsumption_quantity() > 0 && !trackingObject.isStockAddOrConsume()) {

                        CSmProductStockTracking cSmProductStockTracking = new CSmProductStockTracking();
                        cSmProductStockTracking.setCompany_id(matchingObject.get().getCompany_id());
                        cSmProductStockTracking.setCompany_branch_id(matchingObject.get().getCompany_branch_id());
                        cSmProductStockTracking.setFinancial_year(matchingObject.get().getFinancial_year());
                        cSmProductStockTracking.setGoods_receipt_no(matchingObject.get().getGoods_receipt_no());
                        cSmProductStockTracking.setProduct_material_id(matchingObject.get().getProduct_material_id());
                        cSmProductStockTracking.setStock_date(matchingObject.get().getStock_date());
                        cSmProductStockTracking.setStock_quantity(matchingObject.get().getStock_quantity() - smProductStockTracking.getConsumption_quantity());
                        cSmProductStockTracking.setProduct_material_unit_id(matchingObject.get().getProduct_material_unit_id());
                        cSmProductStockTracking.setStock_type(matchingObject.get().getStock_type());
                        cSmProductStockTracking.setCreated_by(matchingObject.get().getCreated_by());

                        addProductStockTracking.add(cSmProductStockTracking);

                    }

                } else {
                    smProductStockTracking.setCompany_id(trackingObject.getCompany_id());
                    smProductStockTracking.setCompany_branch_id(trackingObject.getCompany_branch_id());
                    smProductStockTracking.setFinancial_year(trackingObject.getFinancial_year());
                    smProductStockTracking.setGoods_receipt_no(trackingObject.getGoods_receipt_no());
                    smProductStockTracking.setProduct_material_id(trackingObject.getProduct_material_id());
                    smProductStockTracking.setStock_date(trackingObject.getStock_date());
                    smProductStockTracking.setStock_quantity(trackingObject.getStock_quantity());
                    smProductStockTracking.setProduct_material_unit_id(trackingObject.getProduct_material_unit_id());
                    smProductStockTracking.setStock_type(trackingObject.getStock_type());
                    smProductStockTracking.setCreated_by(trackingObject.getCreated_by());
                }

                addProductStockTracking.add(smProductStockTracking);
            });
        }
        if (!addProductRmStockSummaryList.isEmpty() && addProductRmStockSummaryList != null) {
            iSmProductRmStockSummaryRepository.saveAll(addProductRmStockSummaryList);                // STore details in sm_product_rm_stock_summary
        }
        if (!addProductRmStockDetailsList.isEmpty() && addProductRmStockDetailsList != null) {
            iSmProductRmStockDetailsRepository.saveAll(addProductRmStockDetailsList);                    // STore details in sm_product_rm_stock_details

        }
        if (addProductStockTracking != null && !addProductStockTracking.isEmpty()) {
            iSmProductStockTrackingRepository.saveAll(addProductStockTracking);                      // STore details in sm_product_stock_tracking
        }
        response.put("issuedBatchList", batchList);
        response.put("Success", 1);
        response.put("message", "Stock " + iuFlag + " updated successfully!...");
        return response;
    }
}

