package com.erp.HmShiftManagement.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.Employee.Employees.Model.CEmployeesViewModel;
import com.erp.HmHrpayrollSettings.Repository.IHmHrpayrollSettingsRepository;
import com.erp.HmLeavesApplications.Repository.IHmLeavesApplicationsRepository;
import com.erp.HmProfessionalTax.Model.CHmProfessionalTaxModel;
import com.erp.HmShiftManagement.Model.CHtMissPunchCorrectionModel;
import com.erp.HmShiftManagement.Repository.IHtMissPunchCorrectionRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CHtShiftManagementServiceImpl implements IHtShiftManagementService {
    @Autowired
    CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

    @Autowired
    IHmLeavesApplicationsRepository iHmLeavesApplicationsRepository;
    @Autowired
    IHmHrpayrollSettingsRepository iHmHrpayrollSettingsRepository;
    @Autowired
    IHtMissPunchCorrectionRepository iHtMissPunchCorrectionRepository;

    @Override
    public Map<String, Object> FnAddUpdateMissPunchRecord(JSONObject jsonObject) {
        Map<String, Object> responce = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        int misspunch_correction_id = 0;

        JSONObject commonIdsObj = (JSONObject) jsonObject.get("commonIds");
        int company_id = commonIdsObj.getInt("company_id");
        JSONArray detailsArray = (JSONArray) jsonObject.get("MisspunchAllData");
        try {
            if (detailsArray != null) {
                List<CHtMissPunchCorrectionModel> cHtMissPunchCorrectionModel = objectMapper.readValue(detailsArray.toString(),
                        new TypeReference<List<CHtMissPunchCorrectionModel>>() {
                        });

                // List to store the records that need to be saved or updated
                List<CHtMissPunchCorrectionModel> updatedModels = new ArrayList<>();

                // Iterate over the list of models
                for (CHtMissPunchCorrectionModel model : cHtMissPunchCorrectionModel) {
                    String punchCode = model.getPunch_code(); // Extract punch code
                    String date = model.getAtt_date_time();
                    iHtMissPunchCorrectionRepository.updateMissPunchCorrectionRecord(punchCode, date, company_id);
                    // Fetch employee details for the current punch code
                    List<CEmployeesViewModel> employeeDetailsForSamePunchCode =
                            iHmLeavesApplicationsRepository.employeeDetailsForSamePunchCode(punchCode);

                    // If employee details exist for the punch code, update the data
                    if (employeeDetailsForSamePunchCode != null && !employeeDetailsForSamePunchCode.isEmpty()) {
                        for (CEmployeesViewModel employee : employeeDetailsForSamePunchCode) {
                            // Clone the original model to modify it with the employee code and punch code
                            CHtMissPunchCorrectionModel newModel = new CHtMissPunchCorrectionModel(model);
                            newModel.setEmployee_code(employee.getEmployee_code());  // Set the employee code
                            newModel.setPunch_code(punchCode);  // Optionally, set the punch_code, though it might be the same

                            // Add to the list of models to save or update
                            updatedModels.add(newModel);
                        }
                    }
                }


                List<CHtMissPunchCorrectionModel> responseContent = iHtMissPunchCorrectionRepository
                        .saveAll(updatedModels);

                responce.put("success", 1);
                responce.put("data", responseContent);
                responce.put("error", "");
                responce.put("message",
                        misspunch_correction_id == 0 ? "Record added successfully!..." : "Record update successfully!");
            }

        } catch (DataAccessException e) {
            if (e.getRootCause() instanceof SQLException) {
                SQLException sqlEx = (SQLException) e.getRootCause();
                amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
                        "/api/HmProfessionalTax/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
                System.out.println(sqlEx.getMessage());
                responce.put("success", "0");
                responce.put("data", "");
                responce.put("error", e.getMessage());
            }

        } catch (Exception e) {
            e.printStackTrace();
            amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/HmProfessionalTax/FnAddUpdateRecord",
                    0, e.getMessage());
            responce.put("success", "0");
            responce.put("data", "");
            responce.put("error", e.getMessage());

        }

        return responce;
    }

    @Override
    public Map<String, Object> FnUpdateLockStatus(Map<String, Object> jsonObject) {
        String lockDate = (String) jsonObject.get("lock_date");
        boolean lockStatus = Boolean.parseBoolean(jsonObject.get("lock_status").toString());
        int companyId = Integer.parseInt(jsonObject.get("company_id").toString());
        int hrpayrollSettingsId = Integer.parseInt(jsonObject.get("hrpayroll_settings_id").toString());

        // Call the repository method to update the record
        iHmHrpayrollSettingsRepository.UpdateLockStatus(lockDate, lockStatus, companyId, hrpayrollSettingsId);
        // Create a response map to return
        Map<String, Object> response = new HashMap<>();
        response.put("success", 1);
        response.put("status", "success");
        response.put("message", "Lock status and date updated successfully.");
        return response;
    }


}

