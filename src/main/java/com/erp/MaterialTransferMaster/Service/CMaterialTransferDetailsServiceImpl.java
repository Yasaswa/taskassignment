package com.erp.MaterialTransferMaster.Service;


import com.erp.Common.ExceptionHandling.ExceptionHandlingClass;
import com.erp.MaterialTransferMaster.Model.CMaterialTransferDetailsModel;
import com.erp.MaterialTransferMaster.Model.CMaterialTransferMasterModel;
import com.erp.MaterialTransferMaster.Repository.IMaterialTransferDetailsRepository;
import com.erp.MaterialTransferMaster.Repository.IMaterialTransferMasterRepository;
import com.erp.PtGoodsReceiptDetails.Service.CPtGoodsReceiptDetailsServiceImpl;
import com.erp.SmProductRmStockDetails.Controller.CSmProductRmStockDetailsController;
import com.erp.SmProductRmStockDetails.Model.CSmProductRmStockDetailsModel;
import com.erp.SmProductRmStockDetails.Model.CSmProductRmStockSummaryModel;
import com.erp.SmProductRmStockDetails.Repository.ISmProductRmStockDetailsRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

;

@Service
@RequiredArgsConstructor
public class CMaterialTransferDetailsServiceImpl implements IMaterialTransferDetailsService {

    @Autowired
    private IMaterialTransferDetailsRepository iMaterialTransferDetailsRepository;

    @Autowired
    private IMaterialTransferMasterRepository iMaterialTransferMasterRepository;

    private final ExceptionHandlingClass exceptionHandlingClass;

    @Autowired
    CSmProductRmStockDetailsController cSmProductRmStockDetailsController;

    @Autowired
    ISmProductRmStockDetailsRepository iSmProductRmStockDetailsRepository;

    @Autowired
    CPtGoodsReceiptDetailsServiceImpl cPtGoodsReceiptDetailsService;


    @Override
    @Transactional
    public ResponseEntity<Map<String, Object>> FnAddUpdateTransferDetail(JSONObject jsonObject, Integer company_id) {
        Map<String, Object> response = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        JSONObject commonIdsObj = jsonObject.getJSONObject("commonIds");

        try {
            JSONObject masterjson = jsonObject.getJSONObject("TransMasterData");
            JSONArray detailsArray = (JSONArray) jsonObject.get("TransDetailData");

//			Convert transfer MASTER DATA FROM JSON
            CMaterialTransferMasterModel cMaterialTransferMasterModel = objectMapper.readValue(masterjson.toString(), CMaterialTransferMasterModel.class);

//			transfer MASTER SAVE
            CMaterialTransferMasterModel responseMaterialTransferMaster = iMaterialTransferMasterRepository.save(cMaterialTransferMasterModel);

            List<CMaterialTransferDetailsModel> cMaterialTransferDetailsModels = objectMapper.readValue(detailsArray.toString(), new TypeReference<List<CMaterialTransferDetailsModel>>() {
            });

            cMaterialTransferDetailsModels.forEach(items -> {
                items.setMaterial_transfer_master_id(responseMaterialTransferMaster.getMaterial_transfer_master_id());
            });

            // for transfer update of Stock tables
            Map<String, Object> FnTranferReduceStockDetails = FnTransferReduceStockDetails(responseMaterialTransferMaster, cMaterialTransferDetailsModels, company_id);
            //Map<String, Object> FnTranferAddStockDetails = FnTransferAddStockDetails(responseMaterialTransferMaster, cMaterialTransferDetailsModels, company_id);

            if (!FnTranferReduceStockDetails.get("Status").equals("1")) {
                throw new RuntimeException("Failed to Reduce/Add stock details: " + FnTranferReduceStockDetails.get("error"));
            }

            iMaterialTransferDetailsRepository.saveAll(cMaterialTransferDetailsModels);
            Map<String, Object> successResponse = exceptionHandlingClass.createSuccessResponse("Record processed successfully");
            successResponse.put("responseMaterialTransferMaster", responseMaterialTransferMaster);
            return ResponseEntity.ok(successResponse);

        } catch (DataAccessException e) {
            return exceptionHandlingClass.handleException(e, company_id, "Database access error", HttpStatus.INTERNAL_SERVER_ERROR, "/api/materialTransfer/FnAddUpdateRecord");
        } catch (Exception e) {
            return exceptionHandlingClass.handleException(e, company_id, "An unexpected error occurred", HttpStatus.BAD_REQUEST, "/api/materialTransfer/FnAddUpdateRecord");
        }
    }


    public Map<String, Object> FnTransferReduceStockDetails(CMaterialTransferMasterModel responseMaterialTransferMaster, List<CMaterialTransferDetailsModel> MaterialTransferDetails, Integer company_id) {
        Map<String, Object> batchresponse = new HashMap<>();
        System.out.println("responseMaterialTransferMasters: " + responseMaterialTransferMaster);
        System.out.println("MaterialTransferDetails: " + MaterialTransferDetails);
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String todayDate = dateFormat.format(currentDate);
        Map<String, Object> stockDetails = new HashMap<>();
        int to_company = responseMaterialTransferMaster.getTo_company_id();

        List<CSmProductRmStockDetailsModel> reduceProductRmStockDetailsList = new ArrayList<>();
        List<CSmProductRmStockSummaryModel> reduceProductRmStockSummaryList = new ArrayList<>();
        List<CSmProductRmStockDetailsModel> addProductRmStockDetailsList = new ArrayList<>();
        List<CSmProductRmStockSummaryModel> addProductRmStockSummaryList = new ArrayList<>();

        //Get distinct Transfer material ids from MaterialTransferDetails
        List<String> distinctMaterialIds = MaterialTransferDetails.stream().map(CMaterialTransferDetailsModel::getProduct_material_id).distinct()
                .collect(Collectors.toList());
        System.out.println("distinct transfered MaterialIds: " + distinctMaterialIds);

        //Get All data from sm_product_rm_stock_details table for stock transfer material
        List<CSmProductRmStockDetailsModel> productStockDetailsList = !distinctMaterialIds.isEmpty() ? iSmProductRmStockDetailsRepository.FnGetAllProductRmStockDetailsRawMaterials(distinctMaterialIds, responseMaterialTransferMaster.getFrom_company_id()) : null;

        //iterate on indent materials for stock issue
        MaterialTransferDetails.forEach(stockTransferDetail -> {
            System.out.println("checking for Product_material_id: " + stockTransferDetail.getProduct_material_id());

            List<CSmProductRmStockDetailsModel> getDistinctMaterialWiseStock = productStockDetailsList.stream()
                    .filter(item ->
                            Objects.equals(item.getProduct_rm_id(), stockTransferDetail.getProduct_material_id()) &&
                                    Objects.equals(item.getGodown_id(), stockTransferDetail.getGodown_id()) &&
                                    Objects.equals(item.getGodown_section_id(), stockTransferDetail.getGodown_section_id()) &&
                                    Objects.equals(item.getGodown_section_beans_id(), stockTransferDetail.getGodown_section_beans_id()) &&
                                    Objects.equals(item.getCompany_id(), company_id) &&
                                    item.getClosing_balance_quantity() > 0
                    )
                    .collect(Collectors.toList());
            System.out.println("Filtered List: " + getDistinctMaterialWiseStock);

            AtomicReference<Double> transfer_quantity = new AtomicReference<>(stockTransferDetail.getTransfer_quantity());
            AtomicReference<Double> transfer_weight = new AtomicReference<>(stockTransferDetail.getTransfer_weight());

            AtomicReference<Double> reducedQty = new AtomicReference<>();
            AtomicReference<Double> reducedWt = new AtomicReference<>();
            CSmProductRmStockSummaryModel reduceproductStockSummaryModel = new CSmProductRmStockSummaryModel();
            CSmProductRmStockSummaryModel addproductStockSummaryModel = new CSmProductRmStockSummaryModel();

            getDistinctMaterialWiseStock.forEach(stockRecord -> {
                if (transfer_quantity.get() > 0) {
                    double available_stock_quantity = stockRecord.getClosing_balance_quantity();
                    double available_stock_weight = stockRecord.getClosing_balance_weight();

                    reducedQty.set(Math.min(transfer_quantity.get(), available_stock_quantity));
                    reducedWt.set(Math.min(transfer_weight.get(), available_stock_weight));

                    transfer_quantity.set(transfer_quantity.get() - reducedQty.get());

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
                    String transfer_date = dateFormat.format(stockTransferDetail.getTransfer_date());


                    if (reducegetDetailsObject.isPresent()) {
                        reduceproductRmStockDetailsModel = reducegetDetailsObject.get();
                        reduceproductRmStockDetailsModel.setClosing_balance_quantity(reduceproductRmStockDetailsModel.getClosing_balance_quantity() + (-reducedQty.get()));
                        reduceproductRmStockDetailsModel.setClosing_balance_weight(reduceproductRmStockDetailsModel.getClosing_balance_weight() + (-reducedWt.get()));
                        reduceproductRmStockDetailsModel.setTransfer_issue_quantity(reduceproductRmStockDetailsModel.getTransfer_issue_quantity() + reducedQty.get());
                        reduceproductRmStockDetailsModel.setTransfer_issue_weight(reduceproductRmStockDetailsModel.getTransfer_issue_weight() + reducedWt.get());
                        reduceproductRmStockDetailsModel.setStock_date(transfer_date);
                        reduceproductRmStockDetailsModel.setCompany_id(company_id);

                        addproductRmStockDetailsModel =  addgetDetailsObject.get();
                        addproductRmStockDetailsModel.setClosing_balance_quantity(addproductRmStockDetailsModel.getClosing_balance_quantity() + (-reducedQty.get()));
                        addproductRmStockDetailsModel.setClosing_balance_weight(addproductRmStockDetailsModel.getClosing_balance_weight() + (-reducedWt.get()));
                        addproductRmStockDetailsModel.setTransfer_receipt_quantity(addproductRmStockDetailsModel.getTransfer_receipt_quantity() + reducedQty.get());
                        addproductRmStockDetailsModel.setTransfer_receipt_weight(addproductRmStockDetailsModel.getTransfer_receipt_weight() + reducedWt.get());
                        addproductRmStockDetailsModel.setStock_date(transfer_date);
                        addproductRmStockDetailsModel.setCompany_id(to_company);


                    } else {
                        reduceproductRmStockDetailsModel.setClosing_balance_quantity(-reducedQty.get());
                        reduceproductRmStockDetailsModel.setClosing_balance_weight(-reducedWt.get());
                        reduceproductRmStockDetailsModel.setTransfer_issue_quantity(reducedQty.get());
                        reduceproductRmStockDetailsModel.setTransfer_issue_weight(reducedWt.get());
                        reduceproductRmStockDetailsModel.setStock_date(transfer_date);
                        reduceproductRmStockDetailsModel.setFinancial_year(stockTransferDetail.getFinancial_year());
                        reduceproductRmStockDetailsModel.setGoods_receipt_no(stockRecord.getGoods_receipt_no());
                        reduceproductRmStockDetailsModel.setProduct_rm_id(stockRecord.getProduct_rm_id());
                        reduceproductRmStockDetailsModel.setProduct_material_unit_id((stockRecord.getProduct_material_unit_id()));
                        reduceproductRmStockDetailsModel.setProduct_type_id(stockRecord.getProduct_type_id());
                        reduceproductRmStockDetailsModel.setGodown_id(stockRecord.getGodown_id());
                        reduceproductRmStockDetailsModel.setGodown_section_id(stockRecord.getGodown_section_id());
                        reduceproductRmStockDetailsModel.setGodown_section_beans_id(stockRecord.getGodown_section_beans_id());
                        reduceproductRmStockDetailsModel.setCreated_by(stockTransferDetail.getCreated_by());
                        reduceproductRmStockDetailsModel.setModified_by(stockTransferDetail.getModified_by());
                        reduceproductRmStockDetailsModel.setCompany_id(company_id);
                        reduceProductRmStockDetailsList.add(reduceproductRmStockDetailsModel);


                        addproductRmStockDetailsModel.setCompany_id(to_company);
                        addproductRmStockDetailsModel.setFinancial_year(stockTransferDetail.getFinancial_year());
                        addproductRmStockDetailsModel.setCompany_branch_id(responseMaterialTransferMaster.getCompany_branch_id());
                        addproductRmStockDetailsModel.setProduct_rm_id(stockRecord.getProduct_rm_id());
                        addproductRmStockDetailsModel.setProduct_material_unit_id((stockRecord.getProduct_material_unit_id()));
                        addproductRmStockDetailsModel.setProduct_type_group(stockRecord.getProduct_type_group());
                        addproductRmStockDetailsModel.setProduct_type_id(stockRecord.getProduct_type_id());
                        addproductRmStockDetailsModel.setStock_date(transfer_date);
                        addproductRmStockDetailsModel.setBatch_rate(stockRecord.getBatch_rate());
                        addproductRmStockDetailsModel.setGoods_receipt_no(stockRecord.getGoods_receipt_no());
                        addproductRmStockDetailsModel.setTransfer_receipt_quantity(reducedQty.get());
                        addproductRmStockDetailsModel.setTransfer_receipt_weight(reducedWt.get());
                        addproductRmStockDetailsModel.setClosing_balance_quantity(reducedQty.get());
                        addproductRmStockDetailsModel.setClosing_balance_weight(reducedWt.get());
                        addproductRmStockDetailsModel.setClosing_no_of_boxes(0);
                        addproductRmStockDetailsModel.setGodown_id(stockRecord.getGodown_id());
                        addproductRmStockDetailsModel.setGodown_section_id(stockRecord.getGodown_section_id());
                        addproductRmStockDetailsModel.setGodown_section_beans_id(stockRecord.getGodown_section_beans_id());
                        addproductRmStockDetailsModel.setCreated_by(stockTransferDetail.getCreated_by());
                        addproductRmStockDetailsModel.setModified_by(stockTransferDetail.getModified_by());

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
                        reduceproductStockSummaryModel.setTransfer_issue_quantity(reduceproductStockSummaryModel.getTransfer_issue_quantity() + reducedQty.get());
                        reduceproductStockSummaryModel.setTransfer_issue_weight(reduceproductStockSummaryModel.getTransfer_issue_weight() + reducedWt.get());
                        reduceproductStockSummaryModel.setCompany_id(company_id);

                        CSmProductRmStockSummaryModel getAddProductRmStockSummaryModel = addgetSummaryObject.get();
                        addproductStockSummaryModel.setClosing_balance_quantity(getAddProductRmStockSummaryModel.getClosing_balance_quantity() + reducedQty.get());
                        addproductStockSummaryModel.setClosing_balance_weight(getAddProductRmStockSummaryModel.getClosing_balance_weight() + reducedWt.get());
                        addproductStockSummaryModel.setTransfer_receipt_quantity(addproductStockSummaryModel.getTransfer_receipt_quantity() + reducedQty.get());
                        addproductStockSummaryModel.setTransfer_receipt_weight(addproductStockSummaryModel.getTransfer_receipt_weight() + reducedWt.get());
                        addproductStockSummaryModel.setCompany_id(to_company);

                    } else {
                        reduceproductStockSummaryModel.setFinancial_year(responseMaterialTransferMaster.getFinancial_year());
                        reduceproductStockSummaryModel.setClosing_balance_quantity(-reducedQty.get());
                        reduceproductStockSummaryModel.setClosing_balance_weight(-reducedWt.get());
                        reduceproductStockSummaryModel.setTransfer_issue_quantity(reducedQty.get());
                        reduceproductStockSummaryModel.setTransfer_issue_weight(reducedWt.get());
                        reduceproductStockSummaryModel.setProduct_rm_id(stockRecord.getProduct_rm_id());
                        reduceproductStockSummaryModel.setProduct_type_id(stockRecord.getProduct_type_id());
                        reduceproductStockSummaryModel.setGodown_id(stockRecord.getGodown_id());
                        reduceproductStockSummaryModel.setGodown_section_id(stockRecord.getGodown_section_id());
                        reduceproductStockSummaryModel.setGodown_section_beans_id(stockRecord.getGodown_section_beans_id());
                        reduceproductStockSummaryModel.setCompany_id(company_id);

                        addproductStockSummaryModel.setCompany_id(to_company);
                        addproductStockSummaryModel.setCompany_branch_id(responseMaterialTransferMaster.getCompany_branch_id());
                        addproductStockSummaryModel.setFinancial_year(responseMaterialTransferMaster.getFinancial_year());
                        addproductStockSummaryModel.setProduct_type_id(responseMaterialTransferMaster.getMaterial_transfer_type_id());
                        addproductStockSummaryModel.setProduct_type_group(stockRecord.getProduct_type_group());
                        addproductStockSummaryModel.setProduct_rm_id(stockRecord.getProduct_rm_id());
                        addproductStockSummaryModel.setProduct_material_unit_id(stockRecord.getProduct_material_unit_id());
                        addproductStockSummaryModel.setTransfer_receipt_quantity(reducedQty.get());
                        addproductStockSummaryModel.setTransfer_receipt_weight(reducedWt.get());
                        addproductStockSummaryModel.setClosing_balance_quantity(reducedQty.get());
                        addproductStockSummaryModel.setClosing_balance_weight(reducedWt.get());
                        addproductStockSummaryModel.setClosing_no_of_boxes(0);
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
        Map<String, Object> decreaseResponse = cSmProductRmStockDetailsController.FnAddUpdateRmStockRawMaterials(stockDetails, "Decrease", company_id);
        String decreaseMessage = (String) decreaseResponse.get("message");
        int decreaseSuccess = (int) decreaseResponse.get("Success");
        batchresponse.put("Decrease", decreaseMessage);

        stockDetails.clear();
        stockDetails.put("RmStockSummary", addProductRmStockSummaryList);
        stockDetails.put("RmStockDetails", addProductRmStockDetailsList);
        Map<String, Object> increaseResponse = cSmProductRmStockDetailsController.FnAddUpdateRmStockRawMaterials(stockDetails, "Increase", to_company);


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


    private Map<String, Object> FnTransferAddStockDetails(CMaterialTransferMasterModel responseMaterialTransferMaster, List<CMaterialTransferDetailsModel> MaterialTransferDetails, Integer companyId) {

        System.out.println("responseMaterialTransferMasters: " + responseMaterialTransferMaster);
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String todayDate = dateFormat.format(currentDate);
        Map<String, Object> stockDetails = new HashMap<>();

        int to_company = responseMaterialTransferMaster.getTo_company_id();

        List<CSmProductRmStockDetailsModel> addProductRmStockDetailsList = new ArrayList<>();
        List<CSmProductRmStockSummaryModel> addProductRmStockSummaryList = new ArrayList<>();

        //Get distinct Transfer material ids from MaterialTransferDetails
        List<String> distinctMaterialIds = MaterialTransferDetails.stream().map(CMaterialTransferDetailsModel::getProduct_material_id).distinct()
                .collect(Collectors.toList());
        System.out.println("distinct transfered MaterialIds: " + distinctMaterialIds);

        //Get All data from sm_product_rm_stock_details table for stock transfer material
        CSmProductRmStockDetailsModel productRmStockDetailsModelList = !distinctMaterialIds.isEmpty() ? iSmProductRmStockDetailsRepository.FnGetToCompanieOpeningBalanceEntry(distinctMaterialIds, to_company, "Opening Balance") : null;
        System.out.println("Size of data from sm_product_rm_stock_details table: " + productRmStockDetailsModelList);

        //iterate on indent materials for stock issue
        MaterialTransferDetails.forEach(object -> {
            System.out.println("checking for Product_material_id: " + object.getProduct_material_id());
            String transfer_date = dateFormat.format(object.getTransfer_date());

            String material_id = object.getProduct_material_id();
            Map<String, Object> responce = cPtGoodsReceiptDetailsService.getTotalsOfStockBatches(material_id, to_company);
            Double averageRate = object.getMaterial_rate();
            if (!responce.get("closing_balance_quantity").equals(0)) {
                Double currentGRNMaterialAmt = object.getTransfer_quantity() * object.getMaterial_rate();
                BigDecimal closingTotalMaterialValue = (BigDecimal) responce.get("closing_total_material_value");
                BigDecimal closingBalanceQuantity = (BigDecimal) responce.get("closing_balance_quantity");
                Double existingTotalAmount = closingTotalMaterialValue.doubleValue();
                Double existingTotalQuantity = closingBalanceQuantity.doubleValue();
                averageRate = (existingTotalAmount + currentGRNMaterialAmt) / (existingTotalQuantity + object.getTransfer_quantity());
            }
//            iProductRmCommercialRepository.updateMaasterRateByAdjustment(material_id, averageRate);// update in material master


//			smv_product_rm_stock_summary
                CSmProductRmStockSummaryModel productRmStockSummaryModel = new CSmProductRmStockSummaryModel();

                productRmStockSummaryModel.setCompany_id(to_company);
                productRmStockSummaryModel.setCompany_branch_id(responseMaterialTransferMaster.getCompany_branch_id());
                productRmStockSummaryModel.setFinancial_year(object.getFinancial_year());
                productRmStockSummaryModel.setProduct_type_id(object.getMaterial_transfer_type_id());
                productRmStockSummaryModel.setProduct_rm_id(material_id);
                productRmStockSummaryModel.setProduct_material_unit_id(object.getProduct_unit_id());
                productRmStockSummaryModel.setTransfer_receipt_quantity(object.getTransfer_quantity());
                productRmStockSummaryModel.setTransfer_receipt_weight(object.getTransfer_weight());
                productRmStockSummaryModel.setClosing_balance_quantity(object.getTransfer_quantity());
                productRmStockSummaryModel.setClosing_balance_weight(object.getTransfer_weight());
                productRmStockSummaryModel.setGodown_id(object.getGodown_id());
                productRmStockSummaryModel.setGodown_section_id(object.getGodown_section_id());
                productRmStockSummaryModel.setGodown_section_beans_id(object.getGodown_section_beans_id());
                productRmStockSummaryModel.setCreated_by(object.getCreated_by());
                productRmStockSummaryModel.setModified_by(object.getCreated_by());
                productRmStockSummaryModel.setMaterial_rate(averageRate);

                addProductRmStockSummaryList.add(productRmStockSummaryModel);

                //smv_product_rm_stock_details
                CSmProductRmStockDetailsModel productRmStockDetailsModel = new CSmProductRmStockDetailsModel();

                productRmStockDetailsModel.setCompany_id(to_company);
                productRmStockDetailsModel.setCompany_branch_id(responseMaterialTransferMaster.getCompany_branch_id());
                productRmStockDetailsModel.setFinancial_year(object.getFinancial_year());
                productRmStockDetailsModel.setProduct_rm_id(object.getProduct_material_id());
                productRmStockDetailsModel.setGoods_receipt_no("Opening Balance");
                productRmStockDetailsModel.setTransfer_receipt_quantity(object.getTransfer_quantity());
                productRmStockDetailsModel.setTransfer_receipt_weight(object.getTransfer_weight());
                productRmStockDetailsModel.setClosing_balance_quantity(object.getTransfer_quantity());
                productRmStockDetailsModel.setClosing_balance_weight(object.getTransfer_weight());
                productRmStockDetailsModel.setGodown_id(object.getGodown_id());
                productRmStockDetailsModel.setGodown_section_id(object.getGodown_section_id());
                productRmStockDetailsModel.setGodown_section_beans_id(object.getGodown_section_beans_id());
                productRmStockDetailsModel.setCreated_by(object.getCreated_by());
                productRmStockDetailsModel.setModified_by(object.getCreated_by());
                productRmStockDetailsModel.setProduct_type_id(object.getMaterial_transfer_type_id());
                productRmStockDetailsModel.setBatch_rate(object.getMaterial_rate());
                productRmStockDetailsModel.setStock_date(transfer_date);
                addProductRmStockDetailsList.add(productRmStockDetailsModel);
        });
        stockDetails.put("RmStockSummary", addProductRmStockSummaryList);
        stockDetails.put("RmStockDetails", addProductRmStockDetailsList);
        Map<String, Object> batchresponse = cSmProductRmStockDetailsController.FnAddUpdateRmStockRawMaterials(stockDetails, "Increase", to_company);
        return batchresponse;
    }
}
