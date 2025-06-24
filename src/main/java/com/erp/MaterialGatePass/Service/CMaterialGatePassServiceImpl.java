package com.erp.MaterialGatePass.Service;

import com.erp.MaterialGatePass.Model.CPtMaterialGatePassDetailsModel;
import com.erp.MaterialGatePass.Model.CPtMaterialGatePassMasterModel;
import com.erp.MaterialGatePass.Model.CPtMaterialGatePassReturnSummaryModel;
import com.erp.MaterialGatePass.Repository.IPtMaterialGatePassDetailsRepository;
import com.erp.MaterialGatePass.Repository.IPtMaterialGatePassMasterRepository;
import com.erp.MaterialGatePass.Repository.IPtMaterialGatePassReturnSummaryRepository;
import com.erp.SmProductRmStockDetails.Controller.CSmProductRmStockDetailsController;
import com.erp.SmProductRmStockDetails.Model.CSmProductRmStockDetailsModel;
import com.erp.SmProductRmStockDetails.Model.CSmProductRmStockSummaryModel;
import com.erp.SmProductRmStockDetails.Repository.ISmProductRmStockDetailsRepository;
import com.erp.StIndentIssueMaster.Service.RescheduleIssueServiceImp;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import com.fasterxml.jackson.core.type.TypeReference;


@Service
@RequiredArgsConstructor
public class CMaterialGatePassServiceImpl implements CMaterialGatePassService {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    RescheduleIssueServiceImp rescheduleIssueServiceImp;

    @Autowired
    ISmProductRmStockDetailsRepository iSmProductRmStockDetailsRepository;

    @Autowired
    IPtMaterialGatePassDetailsRepository iPtmaterialGatePassDetailsRepository;

    @Autowired
    CSmProductRmStockDetailsController cSmProductRmStockDetailsController;

    @Autowired
    private IPtMaterialGatePassMasterRepository iPtMaterialGatePassMasterRepository;

    @Autowired
    private IPtMaterialGatePassDetailsRepository iPtMaterialGatePassDetailsRepository;

    @Autowired
    private IPtMaterialGatePassReturnSummaryRepository iPtMaterialGatePassReturnSummaryRepository;

    @Transactional
    @Override
    public Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject) {
        Map<String, Object> response = new HashMap<>();

        try {
            JSONObject companyIds = jsonObject.getJSONObject("commonIds");
            int companyId = companyIds.getInt("company_id");
            String keyForViewUpdate = companyIds.getString("keyForViewUpdate");
            boolean is_deduct_stock = companyIds.getBoolean("is_deduct_stock");
            int isDeductStockInt = is_deduct_stock ? 1 : 0;

            JSONObject materialGatePassMasterDetails = jsonObject.getJSONObject("TransMaterialGatePassMasterDetails");
            JSONArray materialGatePassDetails = jsonObject.getJSONArray("TransMaterialGatePassDetails");
            JSONArray materialGatePassReturnSummary = jsonObject.getJSONArray("TransMaterialGatePassReturnSummary");

            // Master Model
            CPtMaterialGatePassMasterModel cPtMaterialGatePassMasterModel = objectMapper.readValue(materialGatePassMasterDetails.toString(), CPtMaterialGatePassMasterModel.class);

            // Detail Models
            List<CPtMaterialGatePassDetailsModel> cPtMaterialGatePassDetailsModels = objectMapper.readValue(
                    materialGatePassDetails.toString(),
                    new TypeReference<List<CPtMaterialGatePassDetailsModel>>() {}
            );
            List<CPtMaterialGatePassReturnSummaryModel> cPtMaterialGatePassReturnSummaryModels = objectMapper.readValue(
                    materialGatePassReturnSummary.toString(),
                    new TypeReference<List<CPtMaterialGatePassReturnSummaryModel>>() {}
            );

            if (cPtMaterialGatePassDetailsModels != null && !cPtMaterialGatePassDetailsModels.isEmpty()) {
                List<Integer> detailmodelsIds = cPtMaterialGatePassDetailsModels.stream()
                        .map(CPtMaterialGatePassDetailsModel::getMaterial_gate_pass_details_id)
                        .collect(Collectors.toList());

                List<CPtMaterialGatePassDetailsModel> oldDataList = iPtMaterialGatePassDetailsRepository.findAllById(detailmodelsIds);
                Map<Integer, Double> oldInwardQuantitiesMap = oldDataList.stream()
                        .collect(Collectors.toMap(
                                CPtMaterialGatePassDetailsModel::getMaterial_gate_pass_details_id,
                                CPtMaterialGatePassDetailsModel::getInward_quantity
                        ));

                cPtMaterialGatePassDetailsModels.forEach(data -> {
                    Double oldQty = oldInwardQuantitiesMap.getOrDefault(data.getMaterial_gate_pass_details_id(), 0.0);
                    data.setInward_quantity(oldQty + data.getInward_quantity());
                });

                if ("return".equals(keyForViewUpdate)) {
                    long count = cPtMaterialGatePassDetailsModels.stream().filter(data -> {
                        if (data.getInward_quantity() < data.getOutward_quantity()) {
                            data.setGate_pass_item_status("I");
                            return true;
                        } else {
                            data.setGate_pass_item_status("R");
                        }
                        return false;
                    }).count();

                    cPtMaterialGatePassMasterModel.setGate_pass_status(count > 0 ? "I" : "R");
                    iPtMaterialGatePassReturnSummaryRepository.saveAll(cPtMaterialGatePassReturnSummaryModels);
                }

                cPtMaterialGatePassMasterModel = iPtMaterialGatePassMasterRepository.save(cPtMaterialGatePassMasterModel);
                final Integer masterId = cPtMaterialGatePassMasterModel.getMaterial_gate_pass_master_id();

                cPtMaterialGatePassDetailsModels.forEach(data -> data.setMaterial_gate_pass_master_id(masterId));

                if (!detailmodelsIds.isEmpty() && "update".equals(keyForViewUpdate)) {
                    iPtMaterialGatePassDetailsRepository.deleteDetailModelForUpdate(
                            detailmodelsIds, companyId, masterId, cPtMaterialGatePassMasterModel.getModified_by()
                    );
                }

                if ("approve".equals(keyForViewUpdate) && isDeductStockInt == 1) {
                    Map<String, Object> reduceStockResponse = FnReduceStockDetails(
                            cPtMaterialGatePassMasterModel, cPtMaterialGatePassDetailsModels, companyId
                    );

                    if (reduceStockResponse.containsKey("issuedBatchList")) {
                        response.put("issuedBatchList", reduceStockResponse.get("issuedBatchList"));
                    }
                }



                iPtMaterialGatePassDetailsRepository.saveAll(cPtMaterialGatePassDetailsModels);
            }

            response.put("success", 1);
            response.put("error", "");
            switch (keyForViewUpdate) {
                case "add": response.put("message", "Record Added Successfully"); break;
                case "update": response.put("message", "Record Updated Successfully"); break;
                case "approve": response.put("message", "Record Approved Successfully"); break;
                case "return": response.put("message", "Material Returned Successfully"); break;
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", 0);
            response.put("data", "");
            response.put("error", e.getMessage());
            response.put("message", "Error occurred.");
        }

        return response;
    }

    // For Reducing Stock
    @Transactional
    public Map<String, Object> FnReduceStockDetails(CPtMaterialGatePassMasterModel responseMaterialTransferMaster, List<CPtMaterialGatePassDetailsModel> MaterialTransferDetails, Integer company_id) {
        Map<String, Object> batchresponse = new HashMap<>();
        System.out.println("responseMaterialTransferMasters: " + responseMaterialTransferMaster);
        System.out.println("MaterialTransferDetails: " + MaterialTransferDetails);
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String todayDate = dateFormat.format(currentDate);
        Map<String, Object> stockDetails = new HashMap<>();


        List<CSmProductRmStockDetailsModel> reduceProductRmStockDetailsList = new ArrayList<>();
        List<CSmProductRmStockSummaryModel> reduceProductRmStockSummaryList = new ArrayList<>();

        //Get distinct Transfer material ids from MaterialTransferDetails
        List<String> distinctMaterialIds = MaterialTransferDetails.stream().map(CPtMaterialGatePassDetailsModel::getProduct_material_id).distinct()
                .collect(Collectors.toList());
        System.out.println("distinct transfered MaterialIds: " + distinctMaterialIds);

        //Get All data from sm_product_rm_stock_details table for stock transfer material
        List<CSmProductRmStockDetailsModel> productStockDetailsList = !distinctMaterialIds.isEmpty() ? iSmProductRmStockDetailsRepository.FnGetAllProductRmStockDetailsRawMaterials(distinctMaterialIds, responseMaterialTransferMaster.getCompany_id()) : null;

        //iterate on indent materials for stock issue
        MaterialTransferDetails.forEach(stockTransferDetail -> {
            System.out.println("checking for Product_material_id: " + stockTransferDetail.getProduct_material_id());


            Optional<Map<String, Object>> godownInfoOpt = iPtmaterialGatePassDetailsRepository.findGodownInfoByProductMaterialId(stockTransferDetail.getProduct_material_id());


                Map<String, Object> godownInfo = godownInfoOpt.get();

            Integer godownId = ((BigInteger) godownInfo.get("godownId")).intValue();
            Integer godownSectionId = ((BigInteger) godownInfo.get("godownSectionId")).intValue();
            Integer godownSectionBeansId = ((BigInteger) godownInfo.get("godownSectionBeansId")).intValue();

            List<CSmProductRmStockDetailsModel> getDistinctMaterialWiseStock = productStockDetailsList.stream()
                    .filter(item ->
                                    Objects.equals(item.getProduct_rm_id(), stockTransferDetail.getProduct_material_id()) &&
                                    Objects.equals(item.getGodown_id(), godownId) &&
                                    Objects.equals(item.getGodown_section_id(), godownSectionId) &&
                                    Objects.equals(item.getGodown_section_beans_id(), godownSectionBeansId) &&

//                                            //only for trial
//                                            Objects.equals(item.getGodown_id(), 2) &&
//                                            Objects.equals(item.getGodown_section_id(), 2) &&
//                                            Objects.equals(item.getGodown_section_beans_id(), 468) &&

                                            Objects.equals(item.getCompany_id(), company_id) &&
                                            item.getClosing_balance_quantity() > 0
                    )
                    .collect(Collectors.toList());
            System.out.println("Filtered List: " + getDistinctMaterialWiseStock);

            AtomicReference<Double> transfer_quantity = new AtomicReference<>(stockTransferDetail.getOutward_quantity());
            AtomicReference<Double> transfer_weight = new AtomicReference<>(stockTransferDetail.getOutward_weight());

            AtomicReference<Double> reducedQty = new AtomicReference<>();
            AtomicReference<Double> reducedWt = new AtomicReference<>();
            CSmProductRmStockSummaryModel reduceproductStockSummaryModel = new CSmProductRmStockSummaryModel();

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



                    //if object is present in details then just increase the qty of existing object
                    Optional<CSmProductRmStockDetailsModel> reducegetDetailsObject = reduceProductRmStockDetailsList.stream()
                            .filter(item ->

                                    item.getProduct_rm_id().equals(stockRecord.getProduct_rm_id()) &&
                                            Objects.equals(stockRecord.getGodown_id(), item.getGodown_id()) &&
                                            Objects.equals(stockRecord.getGodown_section_id(), item.getGodown_section_id()) &&
                                            Objects.equals(stockRecord.getGodown_section_beans_id(), item.getGodown_section_beans_id())
                                            && item.getGoods_receipt_no().equals(stockRecord.getGoods_receipt_no())
                            ).findFirst();



                    String gate_pass_date = stockTransferDetail.getGate_pass_date();


                    if (reducegetDetailsObject.isPresent()) {
                        reduceproductRmStockDetailsModel = reducegetDetailsObject.get();
                        reduceproductRmStockDetailsModel.setClosing_balance_quantity(reduceproductRmStockDetailsModel.getClosing_balance_quantity() + (-reducedQty.get()));
                        reduceproductRmStockDetailsModel.setClosing_balance_weight(reduceproductRmStockDetailsModel.getClosing_balance_weight() + (-reducedWt.get()));
                        reduceproductRmStockDetailsModel.setSupplier_return_quantity(reduceproductRmStockDetailsModel.getTransfer_issue_quantity() + reducedQty.get());
                        reduceproductRmStockDetailsModel.setSupplier_return_weight(reduceproductRmStockDetailsModel.getTransfer_issue_weight() + reducedWt.get());
                        reduceproductRmStockDetailsModel.setStock_date(gate_pass_date);
                        reduceproductRmStockDetailsModel.setCompany_id(company_id);




                    } else {
                        reduceproductRmStockDetailsModel.setClosing_balance_quantity(-reducedQty.get());
                        reduceproductRmStockDetailsModel.setClosing_balance_weight(-reducedWt.get());
                        reduceproductRmStockDetailsModel.setSupplier_return_quantity(reducedQty.get());
                        reduceproductRmStockDetailsModel.setSupplier_return_weight(reducedWt.get());
                        reduceproductRmStockDetailsModel.setStock_date(gate_pass_date);
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
                        reduceproductRmStockDetailsModel.setCompany_branch_id(stockTransferDetail.getCompany_branch_id());
                        reduceProductRmStockDetailsList.add(reduceproductRmStockDetailsModel);



                    }


                    //if object is present in summary then just increase the qty of existing object
                    Optional<CSmProductRmStockSummaryModel> reducegetSummaryObject = reduceProductRmStockSummaryList.stream()
                            .filter(item -> (item.getProduct_rm_id().equals(stockRecord.getProduct_rm_id()) &&
                                    Objects.equals(stockRecord.getGodown_id(), item.getGodown_id()) &&
                                    Objects.equals(stockRecord.getGodown_section_id(), item.getGodown_section_id()) &&
                                    Objects.equals(stockRecord.getGodown_section_beans_id(), item.getGodown_section_beans_id())
                            )).findFirst();



                    if (reducegetSummaryObject.isPresent() ) {
                        CSmProductRmStockSummaryModel getReduceProductRmStockSummaryModel = reducegetSummaryObject.get();
                        reduceproductStockSummaryModel.setClosing_balance_quantity(getReduceProductRmStockSummaryModel.getClosing_balance_quantity() + (-reducedQty.get()));
                        reduceproductStockSummaryModel.setClosing_balance_weight(getReduceProductRmStockSummaryModel.getClosing_balance_weight() + (-reducedWt.get()));
                        reduceproductStockSummaryModel.setSupplier_return_quantity(reduceproductStockSummaryModel.getTransfer_issue_quantity() + reducedQty.get());
                        reduceproductStockSummaryModel.setSupplier_return_weight(reduceproductStockSummaryModel.getTransfer_issue_weight() + reducedWt.get());
                        reduceproductStockSummaryModel.setCompany_id(company_id);



                    } else {
                        reduceproductStockSummaryModel.setFinancial_year(responseMaterialTransferMaster.getFinancial_year());
                        reduceproductStockSummaryModel.setClosing_balance_quantity(-reducedQty.get());
                        reduceproductStockSummaryModel.setClosing_balance_weight(-reducedWt.get());
                        reduceproductStockSummaryModel.setSupplier_return_quantity(reducedQty.get());
                        reduceproductStockSummaryModel.setSupplier_return_weight(reducedWt.get());
                        reduceproductStockSummaryModel.setProduct_rm_id(stockRecord.getProduct_rm_id());
                        reduceproductStockSummaryModel.setProduct_type_id(stockRecord.getProduct_type_id());
                        reduceproductStockSummaryModel.setGodown_id(stockRecord.getGodown_id());
                        reduceproductStockSummaryModel.setGodown_section_id(stockRecord.getGodown_section_id());
                        reduceproductStockSummaryModel.setGodown_section_beans_id(stockRecord.getGodown_section_beans_id());
                        reduceproductStockSummaryModel.setCompany_id(company_id);
                        reduceproductStockSummaryModel.setCompany_branch_id(stockRecord.getCompany_branch_id());




                        reduceProductRmStockSummaryList.add(reduceproductStockSummaryModel);
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

        if (decreaseResponse.containsKey("issuedBatchList")) {
            batchresponse.put("issuedBatchList", decreaseResponse.get("issuedBatchList"));
        }


        if (decreaseSuccess == 1 ) {
            batchresponse.put("Status", "1");
            batchresponse.put("Success", 1);
            batchresponse.put("message", "Decrease in Stock Details operations were successful!");
            batchresponse.put("issuedBatchList", decreaseResponse.get("issuedBatchList"));
        } else {
            batchresponse.put("Status", "0");
            batchresponse.put("Success", 0);
            batchresponse.put("message", " Decrease in Stock Details operations failed!");
        }
        return batchresponse;

    }


}
