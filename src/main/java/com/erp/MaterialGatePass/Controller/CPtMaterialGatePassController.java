package com.erp.MaterialGatePass.Controller;

import com.erp.MaterialGatePass.Model.*;
import com.erp.MaterialGatePass.Repository.*;
import com.erp.MaterialGatePass.Service.CMaterialGatePassService;
import com.erp.SmProductRmStockDetails.Controller.CSmProductRmStockDetailsController;
import com.erp.SmProductRmStockDetails.Repository.ISmProductRmStockDetailsRepository;
import com.erp.StIndentIssueMaster.Service.RescheduleIssueServiceImp;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/MaterialGatePass")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CPtMaterialGatePassController {

    @Autowired
    IPtMaterialGatePassMasterRepository iPtMaterialGatePassMasterRepository;

    @Autowired
    IPtMaterialReturnGatePassMasterRepository iPtMaterialReturnGatePassMasterRepository;
    @Autowired
    IPtMaterialReturnGatePassDetailsRepository iPtMaterialReturnGatePassDetailsRepository;
    @Autowired
    IPtMaterialGatePassDetailsRepository iPtMaterialGatePassDetailsRepository;

    @Autowired
    IPtMaterialGatePassMasterViewRepository iPtMaterialGatePassMasterViewRepository;

    @Autowired
    IPtMaterialGatePassDetailsViewRepository iPtMaterialGatePassDetailsViewRepository;
    @Autowired
    IPtMaterialGatePassReturnSummaryRepository iPtMaterialGatePassReturnSummaryRepository;

    @Autowired
    CSmProductRmStockDetailsController cSmProductRmStockDetailsController;

    @Autowired
    ISmProductRmStockDetailsRepository iSmProductRmStockDetailsRepository;

    @Autowired
    RescheduleIssueServiceImp rescheduleIssueServiceImp;

    @Autowired
    private CMaterialGatePassService cMaterialGatePassService;


//    @Transactional
//    @PostMapping("/FnAddUpdateRecord")
//    public Map<String, Object> FnAddUpdateRecord(@RequestParam("MaterialGatePassData") JSONObject jsonObject) {
//
//        Map<String, Object> response = new HashMap<>();
//        ObjectMapper objectMapper = new ObjectMapper();
//        try {
//            JSONObject companyIds = jsonObject.getJSONObject("commonIds");
//            int companyId = companyIds.getInt("company_id");
//            String keyForViewUpdate = companyIds.getString("keyForViewUpdate");
//            boolean is_deduct_stock = companyIds.getBoolean("is_deduct_stock");
//            int isDeductStockInt = is_deduct_stock ? 1 : 0;
//
//
//            JSONObject materialGatePassMasterDetails = jsonObject.getJSONObject("TransMaterialGatePassMasterDetails");
//            JSONArray materialGatePassDetails = jsonObject.getJSONArray("TransMaterialGatePassDetails");
//            JSONArray materialGatePassReturnSummary = jsonObject.getJSONArray("TransMaterialGatePassReturnSummary");
//
//            //JSONObject Master Model
//            CPtMaterialGatePassMasterModel cPtMaterialGatePassMasterModel = objectMapper.readValue(materialGatePassMasterDetails.toString(), CPtMaterialGatePassMasterModel.class);
//
//            //JSONArray to Details Models
//            List<CPtMaterialGatePassDetailsModel> cPtMaterialGatePassDetailsModels = objectMapper.readValue(materialGatePassDetails.toString(), new TypeReference<List<CPtMaterialGatePassDetailsModel>>() {
//            });
//            List<CPtMaterialGatePassReturnSummaryModel> cPtMaterialGatePassReturnSummaryModels = objectMapper.readValue(materialGatePassReturnSummary.toString(), new TypeReference<List<CPtMaterialGatePassReturnSummaryModel>>() {
//            });
//
//
//            if (cPtMaterialGatePassDetailsModels != null && !cPtMaterialGatePassDetailsModels.isEmpty()) {
//
//                //Collectin all detail id's
//                List<Integer> detailmodelsIds = cPtMaterialGatePassDetailsModels.parallelStream().map(data -> data.getMaterial_gate_pass_details_id()).collect(Collectors.toList());
//
//                // Fetch old records from the database using the IDs
//                List<CPtMaterialGatePassDetailsModel> oldDataList = iPtMaterialGatePassDetailsRepository.findAllById(detailmodelsIds);
//
//// Creating a map of old data for quick lookup
//                Map<Integer, Double> oldInwardQuantitiesMap = oldDataList.stream()
//                        .collect(Collectors.toMap(
//                                CPtMaterialGatePassDetailsModel::getMaterial_gate_pass_details_id,
//                                CPtMaterialGatePassDetailsModel::getInward_quantity
//                        ));
//
//// Updating each model by summing old and new inward quantities
//                cPtMaterialGatePassDetailsModels.forEach(data -> {
//                    Double oldInwardQuantity = oldInwardQuantitiesMap.getOrDefault(data.getMaterial_gate_pass_details_id(), 0.0);
//                    Double newInwardQuantity = data.getInward_quantity();
//                    data.setInward_quantity(oldInwardQuantity + newInwardQuantity); // Updating inward_quantity with sum
//                });
//                if (keyForViewUpdate.equals("return")) {
//                    int count = (int) cPtMaterialGatePassDetailsModels.parallelStream()
//                            .filter(data -> {
//                                if (data.getInward_quantity() < data.getOutward_quantity()) {
//                                    data.setGate_pass_item_status("I");
//                                    return true;
//                                } else {
//                                    data.setGate_pass_item_status("R");
//                                }
//                                return false;
//                            }).count();
//                    if (count > 0) {
//                        cPtMaterialGatePassMasterModel.setGate_pass_status("I");
//                    } else {
//                        cPtMaterialGatePassMasterModel.setGate_pass_status("R");
//                    }
//
//                    iPtMaterialGatePassReturnSummaryRepository.saveAll(cPtMaterialGatePassReturnSummaryModels);
//                }
//                //saving master data
//                cPtMaterialGatePassMasterModel = iPtMaterialGatePassMasterRepository.save(cPtMaterialGatePassMasterModel);
//                final Integer masterId = cPtMaterialGatePassMasterModel.getMaterial_gate_pass_master_id(); //Storing primary key from master model
//                cPtMaterialGatePassDetailsModels.forEach((data) -> data.setMaterial_gate_pass_master_id(masterId)); //Appending it to details data
//
//                //Deleting details data which are not in details list for update
//                if (!detailmodelsIds.isEmpty() && keyForViewUpdate.equals("update")) {
//                    iPtMaterialGatePassDetailsRepository.deleteDetailModelForUpdate(detailmodelsIds, companyId, masterId, cPtMaterialGatePassMasterModel.getModified_by());
//                }
//
//
//                if (keyForViewUpdate.equals("approve") && isDeductStockInt == 1) {
//                    Map<String, Object> reduceStockResponse = FnTransferReduceStockDetails(cPtMaterialGatePassMasterModel, cPtMaterialGatePassDetailsModels, companyId);
//                }
//                iPtMaterialGatePassDetailsRepository.saveAll(cPtMaterialGatePassDetailsModels); //Saving details data
//
//
//            }
//
//            response.put("success", 1);
//            response.put("error", "");
//            if (keyForViewUpdate.equals("add")) {
//                response.put("message", "Record Added Successfully");
//            } else if (keyForViewUpdate.equals("update")) {
//                response.put("message", "Record Updated Successfully");
//            } else if (keyForViewUpdate.equals("approve")) {
//                response.put("message", "Record Approved Successfully");
//            } else if (keyForViewUpdate.equals(("return"))) {
//                response.put("message", "Material Returned Successfully");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            response.put("success", 0);
//            response.put("data", "");
//            response.put("error", e.getMessage());
//            response.put("message", "Error occurred.");
//        }
//        return response;
//    }

@PostMapping("/FnAddUpdateRecord")
public Map<String, Object> FnAddUpdateRecord(@RequestParam("MaterialGatePassData") JSONObject jsonObject) {

    Map<String, Object> response = cMaterialGatePassService.FnAddUpdateRecord(jsonObject);

    if (response.containsKey("issuedBatchList")) {
        List<Map<String, Object>> issuedBatchList = (List<Map<String, Object>>) response.get("issuedBatchList");
        if (issuedBatchList != null && !issuedBatchList.isEmpty()) {
            JSONObject companyIds = jsonObject.getJSONObject("commonIds");
            int companyId = companyIds.getInt("company_id");
            int godownId = 2;
                    rescheduleIssueServiceImp.rescheduleStockIssue(issuedBatchList, companyId, godownId);
        }
    }

    return response;
}




    @GetMapping("/FnShowParticularRecord/{material_gate_pass_master_id}/{company_id}")
    public Map<String, Object> FnShowParticularRecord(@PathVariable Integer material_gate_pass_master_id, @PathVariable Integer company_id) {
        Map<String, Object> response = new HashMap<>();
        try {
            CPtMaterialGatePassMasterViewModel cPtMaterialGatePassMasterViewModel = iPtMaterialGatePassMasterViewRepository.FnGetParticularRecord(material_gate_pass_master_id, company_id);
            List<CPtMaterialGatePassDetailsViewModel> cPtMaterialGatePassDetailsViewModels = iPtMaterialGatePassDetailsViewRepository.FnGetParticularRecord(material_gate_pass_master_id, company_id);
            List<CPtMaterialGatePassReturnSummaryModel> cPtMaterialGatePassReturnSummaryViewModels = iPtMaterialGatePassReturnSummaryRepository.FnGetParticularMaterialReturnSummary(material_gate_pass_master_id, company_id);

            response.put("success", 1);
            response.put("MaterialGatePassMasterDetails", cPtMaterialGatePassMasterViewModel);
            response.put("MaterialGatePassDetails", cPtMaterialGatePassDetailsViewModels);
            response.put("MaterialGatePassReturnSummary", cPtMaterialGatePassReturnSummaryViewModels);
        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", 0);
            response.put("data", "");
            response.put("error", e.getMessage());
        }
        return response;
    }


    @Transactional
    @PostMapping("/FnDeleteParticularRecord/{material_gate_pass_master_id}/{company_id}/{Username}")
    public Map<String, Object> FnDeleteParticularRecord(@PathVariable Integer material_gate_pass_master_id, @PathVariable Integer company_id, @PathVariable String Username) {
        Map<String, Object> response = new HashMap<>();
        try {
            int masterDeleteStatus = iPtMaterialGatePassMasterRepository.deleteMasterRecord(material_gate_pass_master_id, company_id, Username);
            int detailsDeleteStatus = iPtMaterialGatePassDetailsRepository.deleteDetails(company_id, material_gate_pass_master_id, Username);

            if (masterDeleteStatus > 0 && detailsDeleteStatus > 0) {
                response.put("success", 1);
                response.put("message", "Records Deleted Successfully");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", 0);
            response.put("error", e.getMessage());
        }
        return response;
    }


    @GetMapping("/FnShowParticularReturnInwardRecord/{material_gate_pass_master_id}/{company_id}")
    public Map<String, Object> FnShowParticularReturnInwardRecord(@PathVariable Integer material_gate_pass_master_id, @PathVariable Integer company_id) {
        Map<String, Object> response = new HashMap<>();
        try {
            CPtMaterialGatePassMasterViewModel cPtMaterialGatePassMasterViewModel = iPtMaterialGatePassMasterViewRepository.FnGetParticularInwardRecord(material_gate_pass_master_id, company_id);
            List<CPtMaterialGatePassDetailsViewModel> cPtMaterialGatePassDetailsViewModels = iPtMaterialGatePassDetailsViewRepository.FnGetParticularInwardRecord(material_gate_pass_master_id, company_id);
//            List<CPtMaterialGatePassReturnSummaryModel> cPtMaterialGatePassReturnSummaryViewModels = iPtMaterialGatePassReturnSummaryRepository.FnGetParticularMaterialReturnSummary(material_gate_pass_master_id, company_id);

            response.put("success", 1);
            response.put("MaterialGatePassMasterDetails", cPtMaterialGatePassMasterViewModel);
            response.put("MaterialGatePassDetails", cPtMaterialGatePassDetailsViewModels);
//            response.put("MaterialGatePassReturnSummary", cPtMaterialGatePassReturnSummaryViewModels);
        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", 0);
            response.put("data", "");
            response.put("error", e.getMessage());
        }
        return response;
    }


    @Transactional
    @PostMapping("/FnAddUpdateReturnRecord")
    public Map<String, Object> FnAddUpdateReturnRecord(@RequestParam("MaterialReturnGatePassData") JSONObject jsonObject) {

        Map<String, Object> response = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JSONObject companyIds = jsonObject.getJSONObject("commonIds");
            int companyId = companyIds.getInt("company_id");
            String keyForViewUpdate = companyIds.getString("keyForViewUpdate");

            JSONObject materialGatePassMasterDetails = jsonObject.getJSONObject("TransMaterialGatePassMasterDetails");
            JSONArray materialGatePassDetails = jsonObject.getJSONArray("TransMaterialGatePassDetails");
            JSONArray materialGatePassReturnSummary = jsonObject.getJSONArray("TransMaterialGatePassReturnSummary");

            //JSONObject Master Model
            CPtMaterialReturnGatePassMasterModel cPtMaterialReturnGatePassMasterModel = objectMapper.readValue(materialGatePassMasterDetails.toString(), CPtMaterialReturnGatePassMasterModel.class);
            //JSONArray to Details Models
            List<CPtMaterialReturnGatePassDetailsModel> cPtMaterialReturnGatePassDetailsModels = objectMapper.readValue(materialGatePassDetails.toString(), new TypeReference<List<CPtMaterialReturnGatePassDetailsModel>>() {
            });
            List<CPtMaterialGatePassReturnSummaryModel> cPtMaterialGatePassReturnSummaryModels = objectMapper.readValue(materialGatePassReturnSummary.toString(), new TypeReference<List<CPtMaterialGatePassReturnSummaryModel>>() {
            });

            if (cPtMaterialReturnGatePassDetailsModels != null && !cPtMaterialReturnGatePassDetailsModels.isEmpty()) {

                //Collectin all detail id's
                List<Integer> detailmodelsIds = cPtMaterialReturnGatePassDetailsModels.parallelStream().map(data -> data.getMaterial_gate_pass_details_id()).collect(Collectors.toList());

                // Fetch old records from the database using the IDs
                List<CPtMaterialReturnGatePassDetailsModel> oldDataList = iPtMaterialReturnGatePassDetailsRepository.getAllDetailsById(detailmodelsIds, companyId);

// Creating a map of old data for quick lookup
                Map<Integer, Double> oldInwardQuantitiesMap = oldDataList.stream()
                        .collect(Collectors.toMap(
                                CPtMaterialReturnGatePassDetailsModel::getMaterial_return_gate_pass_details_id,
                                CPtMaterialReturnGatePassDetailsModel::getInward_quantity
                        ));

// Updating each model by summing old and new inward quantities
                cPtMaterialReturnGatePassDetailsModels.forEach(data -> {
                    Double oldInwardQuantity = oldInwardQuantitiesMap.getOrDefault(data.getMaterial_return_gate_pass_details_id(), 0.0);
                    Double newInwardQuantity = data.getInward_quantity();
                    data.setInward_quantity(oldInwardQuantity + newInwardQuantity); // Updating inward_quantity with sum
                });
                int count = (int) cPtMaterialReturnGatePassDetailsModels.parallelStream()
                        .filter(data -> {
                            if (data.getInward_quantity() < data.getOutward_quantity()) {
                                data.setReturn_item_status("I");
                                return true;
                            } else {
                                data.setReturn_item_status("R");
                            }
                            return false;
                        }).count();
                if (count > 0) {
                    cPtMaterialReturnGatePassMasterModel.setReturn_status("I");
                } else {
                    cPtMaterialReturnGatePassMasterModel.setReturn_status("R");
                }

                //saving master data
                cPtMaterialReturnGatePassMasterModel = iPtMaterialReturnGatePassMasterRepository.save(cPtMaterialReturnGatePassMasterModel);
                final Integer masterId = cPtMaterialReturnGatePassMasterModel.getMaterial_return_gate_pass_master_id(); //Storing primary key from master model
                final String gatePassNo = cPtMaterialReturnGatePassMasterModel.getGate_pass_no(); //Storing primary key from master model


                cPtMaterialReturnGatePassDetailsModels.forEach((data) -> data.setMaterial_return_gate_pass_master_id(masterId)); //Appending it to details data
                cPtMaterialGatePassReturnSummaryModels.forEach((data) -> data.setMaterial_return_gate_pass_master_id(masterId));

                iPtMaterialGatePassReturnSummaryRepository.saveAll(cPtMaterialGatePassReturnSummaryModels);

                iPtMaterialReturnGatePassDetailsRepository.saveAll(cPtMaterialReturnGatePassDetailsModels); //Saving details data


                // Collect all detail IDs from return details models
                List<Integer> detailmodelsId = cPtMaterialReturnGatePassDetailsModels.stream()
                        .map(CPtMaterialReturnGatePassDetailsModel::getMaterial_gate_pass_details_id)
                        .collect(Collectors.toList());

// Fetch old records from the database
                List<CPtMaterialGatePassDetailsModel> oldDataLists = iPtMaterialGatePassDetailsRepository.findAllById(detailmodelsId);

// Create a map for quick lookup of old data
                Map<Integer, CPtMaterialGatePassDetailsModel> oldDataMap = oldDataLists.stream()
                        .collect(Collectors.toMap(
                                CPtMaterialGatePassDetailsModel::getMaterial_gate_pass_details_id,
                                data -> data
                        ));

// Prepare data for bulk update
                List<Integer> idsToUpdate = new ArrayList<>();
                List<Double> updatedInwardQuantities = new ArrayList<>();
                List<Double> updatedPendingQuantities = new ArrayList<>();


                boolean hasPendingStatus = false;
                boolean hasPartialReturnStatus = false;
                boolean hasFullReturnStatus = true;  // Start assuming all items are fully returned


                cPtMaterialReturnGatePassDetailsModels.forEach(returnData -> {
                    Integer id = returnData.getMaterial_gate_pass_details_id();
                    CPtMaterialGatePassDetailsModel oldData = oldDataMap.get(id);

                    if (oldData != null) {
                        Double oldInwardQuantity = oldData.getInward_quantity();
                        Double oldPendingQuantity = oldData.getPending_inward_quantity();
                        Double newInwardQuantity = returnData.getInward_quantity();

                        // Compute updated values
                        Double updatedInwardQuantity = oldInwardQuantity + newInwardQuantity;
                        Double updatedPendingQuantity = oldPendingQuantity - newInwardQuantity;

                        // Add data to lists for batch update
                        idsToUpdate.add(id);
                        updatedInwardQuantities.add(updatedInwardQuantity);
                        updatedPendingQuantities.add(updatedPendingQuantity);
                    }
                });


// Fetch all material details from the database (Existing Data)
                List<CPtMaterialGatePassDetailsModel> gatePassDetails =
                        iPtMaterialGatePassDetailsRepository.getAllGatePassDetails(gatePassNo, companyId);


// Create a Map to track the latest inward & pending quantity updates
                Map<Integer, Double> updatedInwardQuantitiesMap = new HashMap<>();
                Map<Integer, Double> updatedPendingQuantitiesMap = new HashMap<>();

// Process frontend data (cPtMaterialReturnGatePassDetailsModels) and update quantities
                for (CPtMaterialReturnGatePassDetailsModel returnData : cPtMaterialReturnGatePassDetailsModels) {
                    Integer id = returnData.getMaterial_gate_pass_details_id();

                    // Store the new inward & pending quantities from frontend
                    updatedInwardQuantitiesMap.put(id, returnData.getInward_quantity());
                    updatedPendingQuantitiesMap.put(id, returnData.getPending_inward_quantity());
                }

// Process each material in the details (existing ones from DB)
                for (CPtMaterialGatePassDetailsModel returnData : gatePassDetails) {
                    Integer id = returnData.getMaterial_gate_pass_details_id();

                    // Get old values from DB
                    Double oldInwardQuantity = Optional.ofNullable(returnData.getInward_quantity()).orElse(0.0);
                    Double oldPendingQuantity = Optional.ofNullable(returnData.getPending_inward_quantity()).orElse(0.0);

                    // Get updated values from frontend (if present)
                    Double newInwardQuantity = updatedInwardQuantitiesMap.getOrDefault(id, 0.0);
                    Double newPendingQuantity = updatedPendingQuantitiesMap.getOrDefault(id, oldPendingQuantity);

                    // Compute the final updated values
                    Double updatedInwardQuantity = oldInwardQuantity + newInwardQuantity;
                    Double updatedPendingQuantity = oldPendingQuantity - newInwardQuantity;


                    if (updatedInwardQuantity > 0 && updatedInwardQuantity < returnData.getOutward_quantity()) {
                        hasPartialReturnStatus = true;  // If inward quantity > 0 but < outward, mark as Partial Return
                        hasFullReturnStatus = false;  // If at least one item is Partial Return, it's not fully returned
                    }

                    // Now check if there is Pending status ("P") ONLY IF NO MATERIAL IS PARTIAL
                    if (!hasPartialReturnStatus && updatedPendingQuantity > 0) {
                        hasPendingStatus = true;  // If any item has pending quantity
                        hasFullReturnStatus = false;  // If any item is pending, it's not fully returned
                    }
                }

// Determine Master Return Status
                String masterReturnStatus = "R";  // Default to "R" (Return)

                if (hasPendingStatus) {
                    masterReturnStatus = "P";  // If any item is pending
                } else if (hasPartialReturnStatus) {
                    masterReturnStatus = "I";  // If any item is partially returned
                }

// Execute batch update if there are records to update
                if (!idsToUpdate.isEmpty()) {
                    for (int i = 0; i < idsToUpdate.size(); i++) {
                        iPtMaterialGatePassDetailsRepository.updateInwardPendingReturnStatus(
                                idsToUpdate.get(i), updatedInwardQuantities.get(i)
                        );
                    }
                }
                iPtMaterialGatePassMasterRepository.updateReturnStatus(gatePassNo, masterReturnStatus, companyId);


            }

            response.put("success", 1);
            response.put("error", "");
            response.put("message", "Material Returned Successfully");

        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", 0);
            response.put("data", "");
            response.put("error", e.getMessage());
            response.put("message", "Error occurred.");
        }
        return response;
    }


    @GetMapping("/FnShowParticularReturnRecord/{material_return_gate_pass_master_id}/{company_id}")
    public Map<String, Object> FnShowParticularReturnRecord(@PathVariable Integer material_return_gate_pass_master_id, @PathVariable Integer company_id) {
        Map<String, Object> response = new HashMap<>();
        try {
            CPtMaterialReturnGatePassMasterViewModel cPtMaterialGatePassMasterViewModel = iPtMaterialReturnGatePassMasterRepository.FnGetParticularRecord(material_return_gate_pass_master_id, company_id);
            List<CPtMaterialReturnGatePassDetailsViewModel> cPtMaterialGatePassDetailsViewModels = iPtMaterialReturnGatePassDetailsRepository.FnGetParticularRecord(material_return_gate_pass_master_id, company_id);
            List<CPtMaterialGatePassReturnSummaryModel> cPtMaterialGatePassReturnSummaryViewModels = iPtMaterialGatePassReturnSummaryRepository.FnGetParticularMaterialReturnSummaryDetails(material_return_gate_pass_master_id, company_id);

            response.put("success", 1);
            response.put("MaterialGatePassMasterDetails", cPtMaterialGatePassMasterViewModel);
            response.put("MaterialGatePassDetails", cPtMaterialGatePassDetailsViewModels);
            response.put("MaterialGatePassReturnSummary", cPtMaterialGatePassReturnSummaryViewModels);
        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", 0);
            response.put("data", "");
            response.put("error", e.getMessage());
        }
        return response;
    }




}
