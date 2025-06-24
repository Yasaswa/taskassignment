package com.erp.StIndentDetails.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.Common.Documents.Repository.IDocumentsRepository;
import com.erp.PtPurchaseDetails.Model.CPtPurchaseOrderDetailsModel;
import com.erp.RawMaterial.Product_Rm_Commercial.Model.CProductRmCommercialModel;
import com.erp.StIndentDetails.Model.*;
import com.erp.StIndentDetails.Repository.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.script.DateFieldScript;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.*;

@Service
public class CStIndentDetailsServiceImpl implements IStIndentDetailsService {

    @Autowired
    CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

    @Autowired
    IStIndentDetailsRepository iStIndentDetailsRepository;

    @Autowired
    IStIndentDetailsViewRepository iStIndentDetailsViewRepository;

    @Autowired
    IStIndentMasterRepository iStIndentMasterRepository;

    @Autowired
    IStIndentSchedulesRepository iStIndentSchedulesRepository;

    @Autowired
    IStIndentSummaryRptRepository indentSummaryRptRepository;

    @Autowired
    IStIndentPOTermsRepository iStIndentPOTermsRepository;

    @Autowired
    IStIndentSchedulesViewRepository iStIndentSchedulesViewRepository;

    @Autowired
    private JdbcTemplate executeQuery;

    @Autowired
    IDocumentsRepository iDocumentsRepository;


    @Transactional
    @Override
    public Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject, boolean isApprove) {
        Map<String, Object> responce = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();

        boolean update = false;

        JSONObject commonIdsObj = (JSONObject) jsonObject.get("commonIds");
        int company_id = commonIdsObj.getInt("company_id");
        int company_branch_id = commonIdsObj.getInt("company_branch_id");
        String indent_no = commonIdsObj.getString("indent_no");
        String financial_year = commonIdsObj.getString("financial_year");

        JSONObject json = jsonObject.getJSONObject("TransHeaderData");
        JSONArray array = (JSONArray) jsonObject.get("TransDetailData");
        JSONArray scheduleArray = (JSONArray) jsonObject.get("TransScheduleData");
        JSONArray poTermsArray = (JSONArray) jsonObject.get("TransPOTermsData");

        try {
            CStIndentMasterModel jsonModel = objectMapper.readValue(json.toString(), CStIndentMasterModel.class);
            if (!isApprove) {
                String indent_date = jsonModel.getIndent_date();
//			Indent Master
                CStIndentMasterModel stIndentMasterModel = new CStIndentMasterModel();

                String query = "Select * FROM st_indent_master WHERE is_delete = 0 and indent_no = '" + indent_no.toString()
                        + "' and indent_version = " + jsonModel.getIndent_version() + " and financial_year = '"
                        + financial_year.toString() + "' and company_id = " + company_id + "";


                List<CStIndentMasterModel> results = executeQuery.query(query,
                        new BeanPropertyRowMapper<>(CStIndentMasterModel.class));

                if (!results.isEmpty()) {
                    update = true;
                    stIndentMasterModel = results.get(0);
                    stIndentMasterModel.setDeleted_on(new Date());
                    stIndentMasterModel.setIs_delete(true);
                    iStIndentMasterRepository.save(stIndentMasterModel);
                    jsonModel.setIndent_version(stIndentMasterModel.getIndent_version() + 1);
                    int indentVersion = json.getInt("indent_version");
                    iStIndentDetailsRepository.updateStatus(indent_no, indentVersion, financial_year,
                            company_id);
                }
                String indentNo = commonIdsObj.getString("indent_no").replaceAll("/", "_");
                List<String> groupIds = Collections.singletonList(indentNo);
                iDocumentsRepository.updateDocActive(groupIds);

                CStIndentMasterModel responceIndentMaster = iStIndentMasterRepository.save(jsonModel);

//			Indent Details
                List<CStIndentDetailsModel> indentDetailsModels = objectMapper.readValue(array.toString(),
                        new TypeReference<List<CStIndentDetailsModel>>() {
                        });

                indentDetailsModels.forEach(items -> {
                    items.setIndent_version(jsonModel.getIndent_version());
                    items.setIndent_master_id(responceIndentMaster.getIndent_master_id());

                });
                iStIndentDetailsRepository.saveAll(indentDetailsModels);

                //---------------------------save CostCenter------------------

//                int costCenterId = jsonModel.getCost_center_id();

                indentDetailsModels.forEach(items -> {
                    String result = iStIndentDetailsRepository.getCostCenterForProductMaterialIds(items.getProduct_material_id());
                    if (result != null && !result.isEmpty()) {
                        // Only update if the existing cost center is 0
                        iStIndentDetailsRepository.updateCostCenterForProductMaterialIds(items.getProduct_material_id(), items.getCost_center_id());
                    }
                });


//			Indent Schedules
                if (!scheduleArray.isEmpty()) {
                    iStIndentSchedulesRepository.updateIndentSchedulesStatus(indent_no, json.getInt("indent_version"),
                            company_id);

                    List<CStIndentSchedulesModel> indentschedulesModel = objectMapper.readValue(scheduleArray.toString(),
                            new TypeReference<List<CStIndentSchedulesModel>>() {
                            });

                    indentschedulesModel.forEach(items -> {
                        items.setIndent_schedule_id(0);
                        items.setIndent_version(jsonModel.getIndent_version());
                        items.setIndent_master_id(responceIndentMaster.getIndent_master_id());
                    });
                    iStIndentSchedulesRepository.saveAll(indentschedulesModel);
                }

//			PO Terms
                if (!poTermsArray.isEmpty()) {

                    iStIndentPOTermsRepository.updateIndentPOTermsStatus(indent_no, json.getInt("indent_version"),
                            company_id);

                    List<CStIndentPOTermsModel> indentPoTermsModel = objectMapper.readValue(poTermsArray.toString(),
                            new TypeReference<List<CStIndentPOTermsModel>>() {
                            });
                    indentPoTermsModel.forEach(items -> {
                        items.setIndent_version(jsonModel.getIndent_version());
                        items.setIndent_master_id(responceIndentMaster.getIndent_master_id());
                    });
                    iStIndentPOTermsRepository.saveAll(indentPoTermsModel);
                }
                responce.put("success", "1");
                responce.put("data", responceIndentMaster);
                responce.put("error", "");
                responce.put("message", update ? "Record updated successfully!" : "Record added successfully!");
            } else {
                responce = FnIndentDetailsUpdateRecord(array, jsonModel, commonIdsObj, scheduleArray);
            }

        } catch (DataAccessException e) {
            e.printStackTrace();
            if (e.getRootCause() instanceof SQLException) {
                SQLException sqlEx = (SQLException) e.getRootCause();
                amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
                        "/api/StIndentDetails/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
                System.out.println(sqlEx.getMessage());
                responce.put("success", "0");
                responce.put("data", "");
                responce.put("error", e.getMessage());
            }

        } catch (Exception e) {
            e.printStackTrace();
            amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/StIndentDetails/FnAddUpdateRecord",
                    0, e.getMessage());
            responce.put("success", "0");
            responce.put("data", "");
            responce.put("error", e.getMessage());
        }
        return responce;
    }

    public Map<String, Object> FnIndentDetailsUpdateRecord(JSONArray array, CStIndentMasterModel jsonModel, JSONObject commonIdsObj, JSONArray scheduleArray) {
        Map<String, Object> responce = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();

        int company_id = commonIdsObj.getInt("company_id");
        int company_branch_id = commonIdsObj.getInt("company_branch_id");
        String indent_no = commonIdsObj.getString("indent_no");
        String financial_year = commonIdsObj.getString("financial_year");

        try {
            //Indent master
            iStIndentMasterRepository.save(jsonModel);

            //Indent details
            iStIndentSchedulesRepository.updateIndentSchedulesStatus(indent_no, jsonModel.getIndent_version(),
                    company_id);

            List<CStIndentDetailsModel> indentDetailsModels = objectMapper.readValue(array.toString(),
                    new TypeReference<List<CStIndentDetailsModel>>() {
                    });
            iStIndentDetailsRepository.saveAll(indentDetailsModels);

            //indent Order schedule

            List<CStIndentSchedulesModel> cstIndentSchedulesModel = objectMapper
                    .readValue(scheduleArray.toString(), new TypeReference<List<CStIndentSchedulesModel>>() {
                    });
            iStIndentSchedulesRepository.saveAll(cstIndentSchedulesModel);

            responce.put("success", "1");
            responce.put("data", jsonModel);
            responce.put("error", "");
            String indent_status = jsonModel.getIndent_status();
            responce.put("message", indent_status.equals("A") ? "Record approved successfully...!" : "Record rejected ! ");


        } catch (DataAccessException e) {
            e.printStackTrace();
            if (e.getRootCause() instanceof SQLException) {
                SQLException sqlEx = (SQLException) e.getRootCause();
                amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
                        "/api/StIndentDetails/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
                System.out.println(sqlEx.getMessage());
                responce.put("success", "0");
                responce.put("data", "");
                responce.put("error", e.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/StIndentDetails/FnAddUpdateRecord",
                    0, e.getMessage());
            responce.put("success", "0");
            responce.put("data", "");
            responce.put("error", e.getMessage());
        }
        return responce;
    }

    @Override
    public Page<CStIndentDetailsViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id) {
        return iStIndentDetailsViewRepository.FnShowAllActiveRecords(pageable, company_id);
    }

    @Override
    public Page<CStIndentDetailsViewModel> FnShowParticularRecord(int indent_details_id, Pageable pageable,
                                                                  int company_id) {
        return iStIndentDetailsViewRepository.FnShowParticularRecord(indent_details_id, pageable, company_id);
    }

    @Override
    public Map<String, Object> FnShowAllDetailsandMastermodelRecords(String indent_no, int indent_version,
                                                                     String financial_year, int company_id) {
        Map<String, Object> responce = new HashMap<>();
        try {

            Map<String, Object> indentMasterRecord = iStIndentMasterRepository.FnShowIndentMasterRecord(indent_no,
                    indent_version, financial_year, company_id);
            List<Map<String, Object>> indentDetailsRecords = iStIndentDetailsRepository
                    .FnShowIndentDetailsRecords(indent_no, indent_version, financial_year, company_id);
            List<CStIndentSchedulesViewModel> indentSchedulesModels = iStIndentSchedulesViewRepository
                    .FnShowIndentSchedules(indent_no, indent_version, company_id);
            List<CStIndentPOTermsModel> indentPoTermsModels = iStIndentPOTermsRepository.FnShowIndentPoTerms(indent_no,
                    indent_version, company_id);

            responce.put("IndentMasterRecord", indentMasterRecord);
            responce.put("IndentDetailsRecords", indentDetailsRecords);
            responce.put("IndentSchedules", indentSchedulesModels);
            responce.put("IndentPOTerms", indentPoTermsModels);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return responce;
    }


    @Override
    public Map<String, Object> FnDeleteRecord(String indent_no, int indent_version, int company_id, String user_name) {
        Map<String, Object> responce = new HashMap<>();
        try {
            iStIndentMasterRepository.deleteIndent(indent_no,
                    indent_version, company_id, user_name);
            iStIndentDetailsRepository.deleteIndentDetails(indent_no,
                    indent_version, company_id, user_name);
            iStIndentSchedulesRepository
                    .deleteIndentSchedules(indent_no, indent_version, company_id, user_name);
            iStIndentPOTermsRepository.deleteIndentPOTerms(indent_no,
                    indent_version, company_id, user_name);

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
    public Map<String, Object> FnCancleRecord(String indent_no, int indent_version, int company_id, String user_name) {
        Map<String, Object> responce = new HashMap<>();
        try {
            iStIndentMasterRepository.cancelIndent(indent_no, indent_version, company_id, user_name);
            iStIndentDetailsRepository.cancelIndentDetails(indent_no, indent_version, company_id, user_name);
            iStIndentSchedulesRepository.cancelIndentSchedules(indent_no, indent_version, company_id, user_name);
            //iStIndentPOTermsRepository.cancelIndentPOTerms(indent_no, indent_version, company_id, user_name);

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
    public Map<String, Object> FnGetPartialIndentMaterials(String indent_no, int indent_version, String financial_year , int company_id) {
        Map<String, Object> responce = new HashMap<>();
        try {
            Map<String, Object> indentMasterRecord = iStIndentMasterRepository.FnShowIndentMasterRecord(indent_no,
                    indent_version, financial_year, company_id);
            List<Map<String, Object>> indentDetailsRecords = iStIndentDetailsRepository
                    .FnShowPrecloseIndentDetailsRecords(indent_no, indent_version, financial_year, company_id);

            responce.put("IndentDetailsRecords", indentDetailsRecords);
            responce.put("indentMasterRecord", indentMasterRecord);

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
    public Map<String, Object> FnPreCloseRecord(String indent_no, int company_id, int UserId, JSONObject stIndentData) {
        Map<String, Object> responce = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JSONArray TransDetailData =  stIndentData.getJSONArray("TransDetailData");
            JSONArray detailsArray = (JSONArray) stIndentData.get("TransDetailData");
            JSONObject  commonIds = stIndentData.getJSONObject("commonIds");
            String financial_year = commonIds.getString("financial_year");
            int indent_version = commonIds.getInt("indent_version");

            List<CStIndentDetailsModel> indentDetailsModels = objectMapper.readValue(detailsArray.toString(),
                    new TypeReference<List<CStIndentDetailsModel>>() {
                    });

            for (CStIndentDetailsModel details : indentDetailsModels) {
                iStIndentDetailsRepository.updatePreClosedDetailsRecord(details.getProduct_material_preclosed_quantity(), details.getPreclosed_remark(), details.getIndent_details_id(), indent_no, company_id , UserId, details.getIndent_version());
            }

            Boolean check = true;
            List<Map<String, Object>> indentDetailsRecords = iStIndentDetailsRepository.GetallPrecloseEntries(indent_no, financial_year, company_id);

            for (Map<String, Object> details : indentDetailsRecords) {
                String itemStatus = (String) details.get("indent_item_status"); // Extract status from map
                if (!"Z".equals(itemStatus)) {
                    check = false; // If any item does not contain 'Z', set to false
                    break; // Exit loop as soon as we find a non-'Z' status
                }
            }

            if(check){
                iStIndentMasterRepository.updatePreClosedmastersRecord(indent_no, company_id , UserId);
            }

            responce.put("success", "1");
            responce.put("message", "Records Pre-Closed successfully...!");
            responce.put("data", "");
            responce.put("error", "");

        } catch (Exception e) {
            e.printStackTrace();
            responce.put("success", "0");
            responce.put("message", "");
            responce.put("data", "");
            responce.put("error", e.getMessage());
        }
        return responce;
    }

    @Override
    public Map<String, Object> FnShowAllReportRecords(Pageable pageable, String reportType) {
        Map<String, Object> response = new HashMap<>();
        String query;

        if ("summary".equals(reportType)) {
            query = "SELECT * FROM stv_indent_master_summary_rpt";
            List<Map<String, Object>> results = executeQuery.queryForList(query.toString());
            response.put("content", results);
        } else {
            query = "SELECT * FROM stv_indent_details_rpt";
            List<Map<String, Object>> results = executeQuery.queryForList(query.toString());
            response.put("content", results);
        }

        return response;
    }

}
	