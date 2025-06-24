package com.erp.XtWarpingProductionOrder.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.RawMaterial.Product_Rm.Model.CProductRmModel;
import com.erp.RawMaterial.Product_Rm.Repository.IProductRmRepository;
import com.erp.SmProductTypeDynamicControls.Repository.ISmProductTypeDynamicControlsRepository;
import com.erp.XtWarpingProductionOrder.Model.*;
import com.erp.XtWarpingProductionOrder.Repository.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CXtWarpingProductionOrderServiceImpl implements IXtWarpingProductionOrderService {

    @Autowired
    CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

    @Autowired
    IXtWarpingProductionOrderRepository iXtWarpingProductionOrderRepository;

    @Autowired
    IXtWarpingProductionOrderViewModelRepository iXtWarpingProductionOrderViewModelRepository;

    @Autowired
    IXtWarpingProductionOrderDetailsRepository iXtWarpingProductionOrderDetailsRepository;

    @Autowired
    IXtWarpingProductionOrderStockDetailsRepository iXtWarpingProductionOrderStockDetailsRepository;

    @Autowired
    IXtvWarpingProductionOrderStockDetailsRepository iXtvWarpingProductionOrderStockDetailsRepository;

    @Autowired
    IXtWarpingProductionOrderCreelsRepository iXtWarpingProductionOrderCreelsRepository;

    @Autowired
    ISmProductTypeDynamicControlsRepository iSmProductTypeDynamicControlsRepository;

    @Autowired
    IXtWarpingProductionBeamDetailsRepository iXtWarpingProductionBeamWiseDetailsRepository;

    @Autowired
    IProductRmRepository iProductRmRepository;

    @Override
    @Transactional(rollbackFor = {Exception.class, SQLException.class, DataAccessException.class})
    public Map<String, Object> FnAddUpdateRecord(JSONObject warpingProductionOrderRequest) throws JsonProcessingException {
        Map<String, Object> response = new HashMap<>();

        ObjectMapper objectMapper = new ObjectMapper();

        JSONObject commonIdsObj = (JSONObject) warpingProductionOrderRequest.get("commonIds");

        int company_id = commonIdsObj.getInt("company_id");
        String keyForViewUpdate = commonIdsObj.getString("keyForViewUpdate");
        try {
            JSONObject warpingProductionMaster = warpingProductionOrderRequest.getJSONObject("TransHeaderData");
            JSONArray warpingProductionMaterialDetails = (JSONArray) warpingProductionOrderRequest.get("TransDetailData");
            JSONArray warpingProductionMaterialStockDetails = (JSONArray) warpingProductionOrderRequest.get("TransStockDetailData");
            JSONArray warpingProductionBeamDetails = (JSONArray) warpingProductionOrderRequest.get("TransBeamDetailData");
            JSONArray warpingProductionBeamWiseDetails = (JSONArray) warpingProductionOrderRequest.get("BeamwiseDetails");

//			SAVE THE WARPING PRODUCTION MASTER DETAILS IN xt_warping_production_order
            CXtWarpingProductionOrderModel convertJsonToMasterModel = objectMapper.readValue(warpingProductionMaster.toString(), CXtWarpingProductionOrderModel.class);

            convertJsonToMasterModel = iXtWarpingProductionOrderRepository.save(convertJsonToMasterModel);

            //Updating set No in sales order details trading
            if ("approve".equals(keyForViewUpdate) && convertJsonToMasterModel != null) {
                iXtWarpingProductionOrderRepository.updateSetNoInSalesOrder(convertJsonToMasterModel.getCustomer_order_no(), convertJsonToMasterModel.getSet_no(), convertJsonToMasterModel.getProduct_material_id(), company_id);
            }


            CXtWarpingProductionOrderModel finalConvertJsonToMasterModel = convertJsonToMasterModel;

            if (convertJsonToMasterModel.getWarping_order_status().equals("P")) {

                if (!keyForViewUpdate.equals("update")) {
                    //			    SAVE THE WARPING PRODUCTION MATERIAL & STOCK DETAILS IN XT_WARPING_PRODUCTION_ORDER_DETAILS
                    List<CXtWarpingProductionOrderDetailsModel> convertJsonToDetailsModel = objectMapper.readValue(warpingProductionMaterialDetails.toString(), new TypeReference<List<CXtWarpingProductionOrderDetailsModel>>() {
                    });


                    convertJsonToDetailsModel.forEach(items -> {
                        items.setWarping_production_order_id(finalConvertJsonToMasterModel.getWarping_production_order_id());
                    });
                    iXtWarpingProductionOrderDetailsRepository.saveAll(convertJsonToDetailsModel);

//			    SAVE THE STOCK DETAILS OF WARPING
                    List<CXtWarpingProductionOrderStockDetailsModel> convertJsonToStockDetailsModel = objectMapper.readValue(warpingProductionMaterialStockDetails.toString(), new TypeReference<List<CXtWarpingProductionOrderStockDetailsModel>>() {
                    });

                    convertJsonToStockDetailsModel.forEach(items -> {
                        items.setWarping_production_order_id(finalConvertJsonToMasterModel.getWarping_production_order_id());
                    });

                    iXtWarpingProductionOrderStockDetailsRepository.saveAll(convertJsonToStockDetailsModel);
                }
//				INSERT THE WARPING CREELS DETAILS
                List<Integer> warpingProdOrderCreeslsId = new ArrayList<>();
                int result;
                List<CXtWarpingProductionOrderCreelsModel> convertJsonToBeamDetailsModel = objectMapper.readValue(warpingProductionBeamDetails.toString(), new TypeReference<List<CXtWarpingProductionOrderCreelsModel>>() {
                });
                convertJsonToBeamDetailsModel.forEach(items -> {
                    if (items.getWarping_production_order_creels_id() > 0) {
                        warpingProdOrderCreeslsId.add(items.getWarping_production_order_creels_id());
                    }
                    items.setWarping_production_order_id(finalConvertJsonToMasterModel.getWarping_production_order_id());
                });
                if (warpingProdOrderCreeslsId == null || warpingProdOrderCreeslsId.isEmpty()) {
                    // If the list is empty, delete all records for this production order
                    result = iXtWarpingProductionOrderCreelsRepository.updateAllWarpingCreelData(finalConvertJsonToMasterModel.getCreated_by(), finalConvertJsonToMasterModel.getWarping_production_order_id());
                }
                // If the list is not empty, delete records NOT in the list
                result = iXtWarpingProductionOrderCreelsRepository.FnDeleteWarpingCreelData(warpingProdOrderCreeslsId, finalConvertJsonToMasterModel.getCreated_by(), finalConvertJsonToMasterModel.getWarping_production_order_id());
                iXtWarpingProductionOrderCreelsRepository.saveAll(convertJsonToBeamDetailsModel);


                // Saving Beam wise Data
                if (warpingProductionBeamWiseDetails != null && warpingProductionBeamWiseDetails.length() != 0) {
                    List<CXtWarpingProductionOrderBeamDetailsModel> beamwiseDetailsModelList = objectMapper.readValue(
                            warpingProductionBeamWiseDetails.toString(),
                            new TypeReference<List<CXtWarpingProductionOrderBeamDetailsModel>>() {}
                    );

                    iXtWarpingProductionBeamWiseDetailsRepository.FnDeleteBeamDetailsRecord(
                            finalConvertJsonToMasterModel.getWarping_production_order_id(),
                            beamwiseDetailsModelList.get(0).getCreated_by(),
                            company_id
                    );

                    beamwiseDetailsModelList.forEach(beamwiseModel -> {
                        beamwiseModel.setWarping_production_order_id(finalConvertJsonToMasterModel.getWarping_production_order_id());
                    });

                    iXtWarpingProductionBeamWiseDetailsRepository.saveAll(beamwiseDetailsModelList);
                }

            }
            response.put("success", 1);
            response.put("error", "");
            response.put("message",
                    "Add".equals(keyForViewUpdate) ? "Record added successfully!" :
                            "update".equals(keyForViewUpdate) ? "Record updated successfully!" :
                                    "Record approved successfully!");


        } catch (DataAccessException e) {
            handleDataAccessException(e, company_id, response);
            throw e; // rethrow the exception to ensure rollback
        } catch (Exception e) {
            handleGenericException(e, company_id, response);
            throw e; // rethrow the exception to ensure rollback
        }

        return response;

    }


    @Transactional
    public void updateSetNo(String customerOrderNo, String setNo, String productMaterialId, Integer companyId) {
        iXtWarpingProductionOrderRepository.updateSetNoInSalesOrder(customerOrderNo, setNo, productMaterialId, companyId);
    }


    private void handleDataAccessException(DataAccessException e, int company_id, Map<String, Object> response) {
        e.printStackTrace();
        Throwable rootCause = e.getRootCause();
        if (rootCause instanceof SQLException) {
            SQLException sqlEx = (SQLException) rootCause;
            amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
                    "/api/XtWarpingProductionOrder/FnAddUpdateRecord", sqlEx.getErrorCode(),
                    sqlEx.getMessage());
        } else {
            amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
                    "/api/XtWarpingProductionOrder/FnAddUpdateRecord", 0, e.getMessage());
        }
        response.put("success", 0);
        response.put("data", "");
        response.put("error", e.getMessage());
    }

    private void handleGenericException(Exception e, int company_id, Map<String, Object> response) {
        e.printStackTrace();
        amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
                "/api/XtWarpingProductionOrder/FnAddUpdateRecord", 0, e.getMessage());
        response.put("success", 0);
        response.put("data", "");
        response.put("error", e.getMessage());
    }

    @Override
    public Map<String, Object> FnDeleteRecord(int warping_production_order_id, String deleted_by, int company_id) {
        Map<String, Object> response = new HashMap<>();
        try {
//			DELETE WARPING PRODUCTION ORDER MASTER
            iXtWarpingProductionOrderRepository.FnDeleteRecord(warping_production_order_id, deleted_by, company_id);
//			DELETE WARPING PRODUCTION DETAILS
            iXtWarpingProductionOrderRepository.FnDeleteDetailsRecord(warping_production_order_id, deleted_by, company_id);
//			DELETE WARPING PRODUCTION DETAILS
            iXtWarpingProductionOrderStockDetailsRepository.FnDeleteStockDetailsRecord(warping_production_order_id, deleted_by, company_id);
//			DELETE WARPING PRODUCTION BEAM OR CREELS DETAILS
            iXtWarpingProductionOrderCreelsRepository.FnDeleteBeamDetailsRecord(warping_production_order_id, deleted_by, company_id);
            // Delete Warping Beam Details
            iXtWarpingProductionBeamWiseDetailsRepository.FnDeleteBeamDetailsRecord(warping_production_order_id, deleted_by, company_id);
            response.put("success", 1);
        } catch (Exception e) {
            response.put("success", 0);
            response.put("error", e.getMessage());
            return response;
        }
        return response;
    }

    @Override
    public Map<String, Object> FnShowParticularRecordForUpdate(int warping_production_order_id, int company_id) {
        Map<String, Object> response = new HashMap<>();

        try {
//			FETCH WARPING PRODUCTION ORDER RECORDS
            CxtWarpingProductionOrderViewModel cxtWarpingProductionOrderModel = iXtWarpingProductionOrderViewModelRepository.FnShowParticularRecordForUpdate(warping_production_order_id, company_id);

//			FETCH WARPING PRODUCTION MATERIAL RECORDS
            List<CxtWarpingProductionOrderDetailsViewModel> WarpingProductionOrderDetailsRecord = iXtWarpingProductionOrderRepository
                    .FnWarpingProductionDetailsRecordForUpdate(warping_production_order_id, company_id);

//			FETCH WARPING PRODUCTION STOCK MATERIAL RECORDS
            List<CXtvWarpingProductionOrderStockDetailsModel> warpingProductionOrderStockDetailsModels = iXtvWarpingProductionOrderStockDetailsRepository
                    .FnShowParticularRecordForUpdate(warping_production_order_id, company_id);

//			FETCH WARPING PRODUCTION CREELS RECORDS
            List<CXtWarpingProductionOrderCreelsModel> warpingProductionOrderCreelsModels = iXtWarpingProductionOrderCreelsRepository
                    .FnShowParticularRecordForUpdate(warping_production_order_id, company_id);

            //Get Beamwise Data
            List<CXtWarpingProductionOrderBeamDetailsModel> beamDetailsModelList = iXtWarpingProductionBeamWiseDetailsRepository.FnShowParticularRecordForUpdate(warping_production_order_id, company_id);
            response.put("success", 1);
            response.put("warpingProductionOrderRecord", cxtWarpingProductionOrderModel);
            response.put("warpingProductionOrderDetailsRecord", WarpingProductionOrderDetailsRecord);
            response.put("warpingProductionOrderStockDetailsRecord", warpingProductionOrderStockDetailsModels);
            response.put("warpingProductionOrderCreelsRecords", warpingProductionOrderCreelsModels);
            response.put("warpingProductionBeamwiseRecords", beamDetailsModelList);
            response.put("error", "");
        } catch (DataAccessException e) {
            e.printStackTrace();
            if (e.getRootCause() instanceof SQLException) {
                SQLException sqlEx = (SQLException) e.getRootCause();
                System.out.println(sqlEx.getMessage());
                response.put("success", 0);
                response.put("data", "");
                response.put("error", e.getMessage());
                return response;
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", 0);
            response.put("data", "");
            response.put("error", e.getMessage());
            return response;
        }
        return response;
    }

    @Override
    public Map<String, Object> FnGetProductBasedProperties(String product_id) {
        Map<String, Object> response = new HashMap<>();
        try {
            List<CProductRmModel> getRawMaterials = iProductRmRepository.getRawMaterials();

            List<Map<String, Object>> getProductDynamicProperties = iSmProductTypeDynamicControlsRepository.FnGetProductBasedProperties(product_id);

            // Filter, process, and transform the list
            List<Map<String, Object>> result = getProductDynamicProperties.stream()
                    .collect(Collectors.groupingBy(
                            map -> new AbstractMap.SimpleEntry<>(
                                    map.get("product_parameter_name"),
                                    map.get("product_parameter_value")
                            ), // Group by combination of product_parameter_name and product_parameter_value
                            Collectors.counting() // Count occurrences
                    ))
                    .entrySet()
                    .stream()
                    .map(entry -> {
                        Map<String, Object> newMap = new LinkedHashMap<>();
                        String parameterName = (String) entry.getKey().getKey();
                        Object parameterValue = entry.getKey().getValue();

                        // Check if parameterName contains the word "COUNT" (case-insensitive)
                        if (parameterName != null && parameterName.toUpperCase().contains("COUNT")) {
                            // Check if control_master column contains "smv_product_rm"
                            Optional<Map<String, Object>> controlMasterRecord = getProductDynamicProperties.stream()
                                    .filter(prop -> "smv_product_rm".equals(prop.get("control_master")))
                                    .findFirst();

                            // If "smv_product_rm" is present, perform additional logic
                            if (controlMasterRecord.isPresent()) {
                                Map<String, Object> getControlMaster = controlMasterRecord.get();

                                CProductRmModel productRmModel = getRawMaterials.parallelStream()
                                        .filter(item -> item.getProduct_rm_id().equals(getControlMaster.get("product_parameter_value"))).findFirst().get();

                                newMap.put(parameterName, productRmModel.getActual_count());
                            }
                        } else {
                            // Insert the parameter name and value in the map
                            newMap.put(parameterName, parameterValue);
                        }

                        return newMap;
                    })
                    .collect(Collectors.toList());

            // Extract unique product_parameter_name values for columns
            List<String> columns = getProductDynamicProperties.stream()
                    .map(map -> (String) map.get("product_parameter_name"))
                    .distinct()
                    .collect(Collectors.toList());


            // Assuming response is a map to hold the output
            response.put("productDynamicPropsList", result);
            response.put("columns", columns);


        } catch (DataAccessException e) {
            e.printStackTrace();
            if (e.getRootCause() instanceof SQLException) {
                SQLException sqlEx = (SQLException) e.getRootCause();
                System.out.println(sqlEx.getMessage());
                response.put("success", 0);
                response.put("data", "");
                response.put("error", e.getMessage());
                return response;
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", 0);
            response.put("data", "");
            response.put("error", e.getMessage());
            return response;
        }
        return response;
    }

}
