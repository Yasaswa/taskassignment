package com.erp.MtDispatchScheduleDetails.Service;

import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

import com.erp.Common.Documents.Repository.IDocumentsRepository;
import com.erp.MtSalesOrderMasterTrading.Model.CMtSalesOrderDetailsTradingModel;
import com.erp.MtSalesOrderMasterTrading.Repository.IMtSalesOrderDetailsTradingRepository;
import com.erp.SmProductTypeDynamicControls.Model.CSmProductTypeDynamicControlsViewModel;
import com.erp.SmProductTypeDynamicControls.Repository.ISmProductTypeDynamicControlsRepository;
import com.erp.SmProductTypeDynamicControls.Repository.ISmProductTypeDynamicControlsViewRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.MtDispatchScheduleDetails.Model.CMtDispatchScheduleDetailsTradingModel;
import com.erp.MtDispatchScheduleDetails.Model.CMtDispatchScheduleDetailsTradingViewModel;
import com.erp.MtDispatchScheduleDetails.Model.CMtDispatchScheduleInspectionDetailsTradingModel;
import com.erp.MtDispatchScheduleDetails.Model.CMtDispatchScheduleInspectionDetailsTradingViewModel;
import com.erp.MtDispatchScheduleDetails.Model.CMtDispatchScheduleMasterTradingModel;
import com.erp.MtDispatchScheduleDetails.Model.CMtDispatchScheduleMasterTradingViewModel;
import com.erp.MtDispatchScheduleDetails.Model.CMtDispatchSchedulePackingDetailsModel;
import com.erp.MtDispatchScheduleDetails.Model.CMtDispatchSchedulePackingDetailsViewModel;
import com.erp.MtDispatchScheduleDetails.Model.CMtDispatchScheduleProductDynamicParametersModel;
import com.erp.MtDispatchScheduleDetails.Model.CMtDispatchScheduleProductDynamicParametersViewModel;
import com.erp.MtDispatchScheduleDetails.Repository.IMtDispatchScheduleDetailsTradingRepository;
import com.erp.MtDispatchScheduleDetails.Repository.IMtDispatchScheduleDetailsTradingViewRepository;
import com.erp.MtDispatchScheduleDetails.Repository.IMtDispatchScheduleInspectionDetailsTradingRepository;
import com.erp.MtDispatchScheduleDetails.Repository.IMtDispatchScheduleInspectionDetailsTradingViewRepository;
import com.erp.MtDispatchScheduleDetails.Repository.IMtDispatchScheduleMasterTradingRepository;
import com.erp.MtDispatchScheduleDetails.Repository.IMtDispatchScheduleMasterTradingViewRepository;
import com.erp.MtDispatchScheduleDetails.Repository.IMtDispatchSchedulePackingDetailsRepository;
import com.erp.MtDispatchScheduleDetails.Repository.IMtDispatchSchedulePackingDetailsViewRepository;
import com.erp.MtDispatchScheduleDetails.Repository.IMtDispatchScheduleProductDynamicParametersRepository;
import com.erp.MtDispatchScheduleDetails.Repository.IMtDispatchScheduleProductDynamicParametersViewRepository;
import com.erp.MtSalesOrderMasterTrading.Model.CMtSalesOrderDetailsTradingViewModel;
import com.erp.MtSalesOrderMasterTrading.Model.CMtSalesOrderProductDynamicParametersViewModel;
import com.erp.MtSalesOrderMasterTrading.Repository.IMtSalesOrderDetailsTradingViewRepository;
import com.erp.MtSalesOrderMasterTrading.Repository.IMtSalesOrderProductDynamicParametersViewRepository;
import com.erp.XtSpinningYarnPackingMaster.Repository.IXtSpinningYarnPackingDetailsRepository;
import com.erp.XtWeavingProductionInspectionMaster.Repository.IXtWeavingProductionInspectionDetailsRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CMtDispatchDetailsServiceImpl implements IMtDispatchDetailsService {

    @Autowired
    CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;
    @Autowired
    IMtDispatchScheduleMasterTradingRepository iMtDispatchScheduleMasterTradingRepository;

    @Autowired
    IMtDispatchScheduleDetailsTradingRepository iMtDispatchScheduleDetailsTradingRepository;

    @Autowired
    IMtDispatchScheduleDetailsTradingViewRepository iMtDispatchScheduleDetailsTradingViewRepository;

    @Autowired
    IMtSalesOrderDetailsTradingViewRepository iMtSalesOrderDetailsTradingViewRepository;

    @Autowired
    IMtSalesOrderDetailsTradingRepository iMtSalesOrderDetailsTradingRepository;
    @Autowired
    ISmProductTypeDynamicControlsRepository iSmProductTypeDynamicControlsRepository;
    @Autowired
    IMtDispatchScheduleMasterTradingViewRepository iMtDispatchScheduleMasterTradingViewRepository;

    @Autowired
    IMtSalesOrderProductDynamicParametersViewRepository iMtSalesOrderProductDynamicParametersViewRepository;

    @Autowired
    IMtDispatchSchedulePackingDetailsRepository iMtDispatchSchedulePackingDetailsRepository;

    @Autowired
    IMtDispatchSchedulePackingDetailsViewRepository iMtDispatchSchedulePackingDetailsViewRepository;

    @Autowired
    IMtDispatchScheduleProductDynamicParametersRepository iMtDispatchScheduleProductDynamicParametersRepository;

    @Autowired
    IMtDispatchScheduleProductDynamicParametersViewRepository iMtDispatchScheduleProductDynamicParametersViewRepository;

    @Autowired
    IMtDispatchScheduleInspectionDetailsTradingRepository iMtDispatchScheduleInspectionDetailsTradingRepository;

    @Autowired
    IMtDispatchScheduleInspectionDetailsTradingViewRepository iMtDispatchScheduleInspectionDetailsTradingViewRepository;


    //  Yarn Packing Repos.
    @Autowired
    IXtSpinningYarnPackingDetailsRepository iXtSpinningYarnPackingDetailsRepository;

    //  Weaving Inspection Repos.
    @Autowired
    IXtWeavingProductionInspectionDetailsRepository iXtWeavingProductionInspectionDetailsRepository;

    @Autowired
    IDocumentsRepository iDocumentsRepository;

    @Autowired
    private JdbcTemplate executeQuery;

    @Transactional
    @Override
    public Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject) {
        Map<String, Object> response = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();

        String status = "";

        JSONObject commonIdsObj = (JSONObject) jsonObject.get("commonIds");
        int company_id = commonIdsObj.getInt("company_id");
        int company_branch_id = commonIdsObj.getInt("company_branch_id");
        String dispatch_schedule_no = commonIdsObj.getString("dispatch_schedule_no");
        String financial_year = commonIdsObj.getString("financial_year");

        JSONObject masterjson = jsonObject.getJSONObject("TransHeaderData");
        JSONArray detailsArray = (JSONArray) jsonObject.get("TransDetailData");
        JSONArray dispSchPackingDetails = (JSONArray) jsonObject.get("TransPackingDetailData");
        JSONArray productParametersDetails = (JSONArray) jsonObject.get("TransProductParametersData");
        JSONArray inspectionDetails = (JSONArray) jsonObject.get("TransInspectionDetailsData");


        try {
            CMtDispatchScheduleMasterTradingModel jsonModel = objectMapper.readValue(masterjson.toString(),
                    CMtDispatchScheduleMasterTradingModel.class);

//			Dispatch Schedule Master
            CMtDispatchScheduleMasterTradingModel cMtDispatchScheduleMasterTradingModel = new CMtDispatchScheduleMasterTradingModel();

            CMtDispatchScheduleMasterTradingModel responceScheduleMasterTrading = iMtDispatchScheduleMasterTradingRepository.save(jsonModel);

//			iMtDispatchScheduleDetailsTradingRepository.updateStatus(dispatch_schedule_no,
//					jsonModel.getDispatch_schedule_version(), financial_year, company_id);
            List<CMtDispatchScheduleDetailsTradingModel> savedDetails = new ArrayList<CMtDispatchScheduleDetailsTradingModel>();
            if (!detailsArray.isEmpty()) {
                String dispatchScheduleNo = commonIdsObj.getString("dispatch_schedule_no").replaceAll("/", "_");
                List<String> groupIds = Collections.singletonList(dispatchScheduleNo);
                iDocumentsRepository.updateDocActive(groupIds);
                List<CMtDispatchScheduleDetailsTradingModel> cmtDispatchScheduleDetailsTradingModel = objectMapper
                        .readValue(detailsArray.toString(),
                                new TypeReference<List<CMtDispatchScheduleDetailsTradingModel>>() {
                                });

                cmtDispatchScheduleDetailsTradingModel.forEach(items -> {
                    items.setDispatch_schedule_version(jsonModel.getDispatch_schedule_version());
                    items.setDispatch_schedule_master_transaction_id(responceScheduleMasterTrading.getDispatch_schedule_master_transaction_id());
                });

                savedDetails = iMtDispatchScheduleDetailsTradingRepository.saveAll(cmtDispatchScheduleDetailsTradingModel);
            }


            if (!dispSchPackingDetails.isEmpty()) {

                List<CMtDispatchSchedulePackingDetailsModel> cmtDispatchSchedulePackingDetailsModel = objectMapper
                        .readValue(dispSchPackingDetails.toString(),
                                new TypeReference<List<CMtDispatchSchedulePackingDetailsModel>>() {
                                });

                cmtDispatchSchedulePackingDetailsModel.forEach(items -> {
                    items.setDispatch_schedule_version(responceScheduleMasterTrading.getDispatch_schedule_version());
                    items.setDispatch_schedule_master_transaction_id(responceScheduleMasterTrading.getDispatch_schedule_master_transaction_id());
                });
                List<CMtDispatchSchedulePackingDetailsModel> savedPkgDtls = iMtDispatchSchedulePackingDetailsRepository.saveAll(cmtDispatchSchedulePackingDetailsModel);

//	            // Update the Yarn Packing Status. (Uncomment this block for yarn packing details status updatation)
//	            List<Integer> yarnPkgDtlsIds = new ArrayList<Integer>();
//	            savedDetails.forEach(savedDtl -> {
//	                Integer soDtlId = savedDtl.getSales_order_details_transaction_id();
//	            	if(savedDtl.getActual_dispatch_quantity() > 0) {
//	                    List<CMtDispatchSchedulePackingDetailsModel> matchingPkgDetails = savedPkgDtls.stream()
//	                            .filter(detail -> soDtlId.equals(detail.getSales_order_details_transaction_id()))
//	                            .collect(Collectors.toList());
//	                    // If Found the add in pkgDtl ids for update the status.
//	                    matchingPkgDetails.forEach(pkgDtl -> {
//	                    	yarnPkgDtlsIds.add(pkgDtl.getYarn_packing_details_id());
//	                    });
//	            	}
//	            });
//	            iXtSpinningYarnPackingDetailsRepository.updateSpinningYarnPackingDetailsByDispNote(yarnPkgDtlsIds, "DG", responceScheduleMasterTrading.getCreated_by());
            }

            if (!productParametersDetails.isEmpty()) {
                List<CMtDispatchScheduleProductDynamicParametersModel> cmtDispatchScheduleProductDynamicParameters = objectMapper
                        .readValue(productParametersDetails.toString(),
                                new TypeReference<List<CMtDispatchScheduleProductDynamicParametersModel>>() {
                                });

                cmtDispatchScheduleProductDynamicParameters.forEach(items -> {
                    items.setDispatch_schedule_version(responceScheduleMasterTrading.getDispatch_schedule_version());
                    items.setDispatch_schedule_master_transaction_id(responceScheduleMasterTrading.getDispatch_schedule_master_transaction_id());
                });
                iMtDispatchScheduleProductDynamicParametersRepository.saveAll(cmtDispatchScheduleProductDynamicParameters);
            }

            if (!inspectionDetails.isEmpty()) {

                // Parse Dispatch Schedule Inspection Details for the retrieved data
                List<CMtDispatchScheduleInspectionDetailsTradingModel> cMtDispatchScheduleInspectionDetails =
                        objectMapper.readValue(inspectionDetails.toString(), new TypeReference<List<CMtDispatchScheduleInspectionDetailsTradingModel>>() {
                        });

                cMtDispatchScheduleInspectionDetails.forEach(inspectiondetail -> {
                    inspectiondetail.setDispatch_schedule_version(responceScheduleMasterTrading.getDispatch_schedule_version());
                    inspectiondetail.setDispatch_schedule_master_transaction_id(responceScheduleMasterTrading.getDispatch_schedule_master_transaction_id());
                });
                //save cMtDispatchScheduleInspectionDetails
                List<CMtDispatchScheduleInspectionDetailsTradingModel> savedInspectionDtls = iMtDispatchScheduleInspectionDetailsTradingRepository.saveAll(cMtDispatchScheduleInspectionDetails);

//				// Update the Weaving inspection details Status. (Uncomment this block for Weaving inspection details status updatation)
//	            List<Integer> inspectionDtlsIds = new ArrayList<Integer>();
//	            savedDetails.forEach(savedDtl -> {
//	                Integer soDtlId = savedDtl.getSales_order_details_transaction_id();
//	            	if(savedDtl.getActual_dispatch_quantity() > 0) {
//	                    List<CMtDispatchScheduleInspectionDetailsTradingModel> matchingInspectionDetails = savedInspectionDtls.stream()
//	                            .filter(detail -> soDtlId.equals(detail.getSales_order_details_transaction_id()))
//	                            .collect(Collectors.toList());
//	                    // If Found the add in Weaving inspection details ids for update the status.
//	                    matchingInspectionDetails.forEach(pkgDtl -> {
//	                    	inspectionDtlsIds.add(pkgDtl.getWeaving_production_inspection_details_id());
//	                    });
//	            	}
//	            });
//	            iXtWeavingProductionInspectionDetailsRepository.updateInspectionDtlsByDispNote(inspectionDtlsIds, "DG",responceScheduleMasterTrading.getCreated_by());
            }


            response.put("success", "1");
            response.put("data", responceScheduleMasterTrading);
            response.put("error", "");
            if (jsonModel.getDispatch_note_status().equals("P")) {
                response.put("message",
                        masterjson.getInt("dispatch_schedule_master_transaction_id") == 0
                                ? "Record added successfully!..."
                                : "Record updated successfully!...");
            } else {
                if (jsonModel.getDispatch_note_status().equals("A")) {
                    status = "Approved";
                } else if (jsonModel.getDispatch_note_status().equals("R")) {
                    status = "Rejected";
                } else if (jsonModel.getDispatch_note_status().equals("X")) {
                    status = "Canceled";
                }
                response.put("message", "Record " + status + " successfully!...");
            }

        } catch (DataAccessException e) {
            e.printStackTrace();
            if (e.getRootCause() instanceof SQLException) {
                SQLException sqlEx = (SQLException) e.getRootCause();
                amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
                        "/api/MtScheduleDetails/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
                System.out.println(sqlEx.getMessage());
                response.put("success", "0");
                response.put("data", "");
                response.put("error", e.getMessage());
            }

        } catch (Exception e) {
            e.printStackTrace();
            amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/MtScheduleDetails/FnAddUpdateRecord",
                    0, e.getMessage());
            response.put("success", "0");
            response.put("data", "");
            response.put("error", e.getMessage());
        }
        return response;
    }

    @Override
    public Map<String, Object> FnDispatchScheduleDetailsUpdateRecord(JSONObject jsonObject) {
        Map<String, Object> response = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();

        JSONObject commonIdsObj = (JSONObject) jsonObject.get("commonIds");
        int company_id = commonIdsObj.getInt("company_id");
        int company_branch_id = commonIdsObj.getInt("company_branch_id");
        String dispatch_schedule_no = commonIdsObj.getString("dispatch_schedule_no");
        String financial_year = commonIdsObj.getString("financial_year");

        JSONArray detailsArray = (JSONArray) jsonObject.get("TransDetailData");

        try {

            List<CMtDispatchScheduleDetailsTradingModel> cmtDispatchScheduleDetailsTradingModel = objectMapper
                    .readValue(detailsArray.toString(),
                            new TypeReference<List<CMtDispatchScheduleDetailsTradingModel>>() {
                            });

            iMtDispatchScheduleDetailsTradingRepository.saveAll(cmtDispatchScheduleDetailsTradingModel);

            response.put("success", "1");
            response.put("data", cmtDispatchScheduleDetailsTradingModel);
            response.put("error", "");
            response.put("message", "Record updates successfully!...");
        } catch (DataAccessException e) {
            e.printStackTrace();
            if (e.getRootCause() instanceof SQLException) {
                SQLException sqlEx = (SQLException) e.getRootCause();
                amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
                        "/api/MtDispatchScheduleDetails/FnDispatchScheduleDetailsUpdateRecord", sqlEx.getErrorCode(),
                        sqlEx.getMessage());
                System.out.println(sqlEx.getMessage());
                response.put("success", "0");
                response.put("data", "");
                response.put("error", e.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
                    "/api/MtDispatchScheduleDetails/FnDispatchScheduleDetailsUpdateRecord", 0, e.getMessage());
            response.put("success", "0");
            response.put("data", "");
            response.put("error", e.getMessage());
        }
        return response;
    }

    @Override
    public Map<String, Object> FnDeleteRecord(String dispatch_schedule_no, int dispatch_schedule_version,
                                              int company_id, String deleted_by) {
        Map<String, Object> response = new HashMap<>();
        try {

            iMtDispatchScheduleMasterTradingRepository.deleteDispatchMaster(dispatch_schedule_no,
                    dispatch_schedule_version, company_id, deleted_by);

            iMtDispatchScheduleDetailsTradingRepository.deleteDispatchDetails(dispatch_schedule_no,
                    dispatch_schedule_version, company_id, deleted_by);

            //Delete Dispatch Schedule Packing Details
            iMtDispatchSchedulePackingDetailsRepository.deleteDispatchSchedulePackingDetails(dispatch_schedule_no,
                    dispatch_schedule_version, company_id, deleted_by);


            //Delete Dispatch Schedule Product Dynamic Parameters Record
            iMtDispatchScheduleProductDynamicParametersRepository.deleteDispatchScheduleProductDynamicParameters(dispatch_schedule_no,
                    dispatch_schedule_version, company_id, deleted_by);

            //Delete Dispatch Schedule Inspection Details Record
            iMtDispatchScheduleInspectionDetailsTradingRepository.deleteDispatchScheduleInspectionDetailsRecord(dispatch_schedule_no,
                    dispatch_schedule_version, company_id, deleted_by);

            response.put("success", "1");
            response.put("data", "");
            response.put("error", "");

        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", "0");
            response.put("data", "");
            response.put("error", "");
        }
        return response;
    }

    @Override
    public Map<String, Object> FnShowAllDetailsandMastermodelRecords(String dispatch_schedule_no,
                                                                     int dispatch_schedule_version, String financial_year, int company_id) {
        Map<String, Object> response = new HashMap<>();
        try {

//        		// Old Query.
//            Map<String, Object> dispatchScheduleMasterRecords = iMtDispatchScheduleMasterTradingRepository
//                    .FnShowDispatchScheduleMasterRecord(dispatch_schedule_no, dispatch_schedule_version, financial_year,
//                            company_id);

            CMtDispatchScheduleMasterTradingViewModel dispatchScheduleMasterRecords = iMtDispatchScheduleMasterTradingViewRepository
                    .FnShowDispatchScheduleMasterRecord(dispatch_schedule_no, dispatch_schedule_version, financial_year,
                            company_id);

            List<Map<String, Object>> dispatchScheduleDetailsRecords = iMtDispatchScheduleDetailsTradingRepository
                    .FnShowDispatchScheduleDetailsRecords(dispatch_schedule_no, dispatch_schedule_version,
                            financial_year, company_id);

            //Fetch Dispatch Schedule Packing Details for update 
//            List<CMtDispatchSchedulePackingDetailsViewModel> dispatchSchedulePackingDetailsRecord = iMtDispatchSchedulePackingDetailsViewRepository
//            		.FnShowDispatchSchedulePackingDetailsRecords(dispatch_schedule_no, dispatch_schedule_version,
//                    financial_year, company_id);

            //Fetch Dispatch Schedule Product Dynamic Parameters Record for update 
//            List<CMtDispatchScheduleProductDynamicParametersViewModel> productDynamicParametersRecord =
//            		iMtDispatchScheduleProductDynamicParametersViewRepository.FnShowDispatchScheduleProductDynamicParametersRecords(dispatch_schedule_no, dispatch_schedule_version,
//                            company_id);

            //Fetch Dispatch Schedule Inspection Details  Record for update 
//            List<CMtDispatchScheduleInspectionDetailsTradingViewModel>  dispatchScheduleInspectionDetailsRecord = iMtDispatchScheduleInspectionDetailsTradingViewRepository.FnShowDispatchScheduleInspectionDetailsRecords(dispatch_schedule_no, dispatch_schedule_version,
//                    company_id);

            //Fetch Dispatch Schedule Inspection Details  Record for update
//            List<CMtDispatchScheduleInspectionDetailsTradingViewModel>  dispatchScheduleProprtiesDetailsRecord = iMtDispatchScheduleInspectionDetailsTradingViewRepository.FnShowDispatchScheduleInspectionDetailsRecords(dispatch_schedule_no, dispatch_schedule_version,
//                    company_id);

            response.put("DispatchScheduleMasterRecord", dispatchScheduleMasterRecords);
            response.put("DispatchScheduleDetailsRecords", dispatchScheduleDetailsRecords);
//            response.put("DispatchSchedulePackingDetailsRecord", dispatchSchedulePackingDetailsRecord);
//            response.put("ProductDynamicParametersRecord", productDynamicParametersRecord);
//            response.put("DispatchScheduleInspectionDetailsRecord", dispatchScheduleInspectionDetailsRecord);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public Page<CMtDispatchScheduleDetailsTradingViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id) {
        return iMtDispatchScheduleDetailsTradingViewRepository.FnShowAllActiveRecords(pageable, company_id);

    }

    @Override
    public Page<CMtDispatchScheduleDetailsTradingViewModel> FnShowParticularRecord(
            int dispatch_schedule_details_transaction_id, Pageable pageable, int company_id) {
        return iMtDispatchScheduleDetailsTradingViewRepository
                .FnShowParticularRecord(dispatch_schedule_details_transaction_id, pageable, company_id);

    }

    @Override
    public Map<String, Object> FnShowAllReportRecords(Pageable pageable, String reportType) {
        Map<String, Object> response = new HashMap<>();
        if ("summary".equals(reportType)) {
            Map<String, Object> results = iMtDispatchScheduleMasterTradingViewRepository
                    .FnShowAllSummaryReportRecords();
            response.put("content", results);
        } else {
            Map<String, Object> results = iMtDispatchScheduleDetailsTradingViewRepository
                    .FnShowAlldetailsReportRecords();
            response.put("content", results);
        }
        return response;
    }

    @Override
    public Map<String, Object> FnShowDispatchScheduleDetailsTradingRecords(JSONObject customerOrderNo,
                                                                           String dispatch_schedule_no, int dispatch_schedule_version, int company_id) {
        Map<String, Object> response = new HashMap<>();
        try {
            JSONArray customerOrderNos = customerOrderNo.getJSONArray("cust_order_nos");
            List<String> orderNumbersList = customerOrderNos.toList() // Convert JSONArray to List<Object>
                    .stream().map(Object::toString) // Convert each element to String
                    .collect(Collectors.toList());

//            List<CMtSalesOrderDetailsTradingModel> fetchData = iMtSalesOrderDetailsTradingRepository
//                    .FnShowSalesOrderDetailsForDispatch(orderNumbersList, company_id);
            List<Map<String, Object>> fetchData = iMtSalesOrderDetailsTradingRepository
                    .FnShowSalesOrderDetailsForDispatch(orderNumbersList);
            //Fetch Sales Order Product Dynamic Parameters Record for update
//            List<CMtSalesOrderProductDynamicParametersViewModel> salesOrderProductDynamicParametersRecord =
//                    iMtSalesOrderProductDynamicParametersViewRepository.FnShowSOProductDynamicParameters(orderNumbersList, company_id);
//            List<String> productMaterialIds = fetchData.stream()
//                    .map(CMtSalesOrderDetailsTradingModel::getProduct_material_id)
//                    .collect(Collectors.toList());

//            List<Map<String, Object>> getProductDynamicProperties = iSmProductTypeDynamicControlsRepository.FnGetProductBasedPropertiesForDispatch(productMaterialIds);


            response.put("data", fetchData);
//            response.put("productParametersData", salesOrderProductDynamicParametersRecord);
//            response.put("productParametersData", getProductDynamicProperties);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;

    }

}
