package com.erp.HtSalaryRules.Service;

import com.erp.HtSalaryRules.Model.CHtSalaryRulesModel;
import com.erp.HtSalaryRules.Repository.IHtSalaryRulesRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
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
public class CHtSalaryRulesServiceImpl implements IHtSalaryRulesService {

    @Autowired
    IHtSalaryRulesRepository iHtSalaryRulesRepository;


    @Override
    public Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject) {
        Map<String, Object> response = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JSONObject commonIdsObj = jsonObject.getJSONObject("commonIds");
            int salary_rule_id = commonIdsObj.getInt("salary_rule_id");
            int company_id = commonIdsObj.getInt("company_id");
            JSONArray salaryRulesDetails = jsonObject.getJSONArray("SalaryRulesData");

            List<CHtSalaryRulesModel> salaryRulesList = new ArrayList<>();
            String message = "Record inserted successfully!";

            List<CHtSalaryRulesModel> salaryRulesModels = objectMapper.readValue(salaryRulesDetails.toString(), new TypeReference<List<CHtSalaryRulesModel>>() {
            });

            salaryRulesModels = iHtSalaryRulesRepository.saveAll(salaryRulesModels);

            response.put("success", 1);
            response.put("data", salaryRulesModels);
            response.put("error", "");
            response.put("message", message);

        } catch (DataAccessException | JsonProcessingException e) {
            e.printStackTrace();
            response.put("success", 0);
            response.put("data", "");
            response.put("error", e.getMessage());
            response.put("message", "Error occurred.");
        }

        return response;
    }


    @Override
    public Map<String, Object> FnShowParticularRecord(Integer property_id, Integer companyId) {
        Map<String, Object> response = new HashMap<>();
        try {
            List<CHtSalaryRulesModel> salaryrulesdetails = iHtSalaryRulesRepository.FnShowParticularRecord(property_id, companyId);

            response.put("success", 1);
            response.put("SalaryRulesData", salaryrulesdetails);
            response.put("error", "");
        } catch (DataAccessException e) {
            if (e.getRootCause() instanceof SQLException) {
                SQLException sqlEx = (SQLException) e.getRootCause();
                System.out.println(sqlEx.getMessage());
                response.put("success", 1);
                response.put("data", "");
                response.put("error", e.getMessage());
                return response;
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", "0");
            response.put("data", "");
            response.put("error", e.getMessage());
            return response;
        }
        return response;
    }

    @Override
    public Object FnDeleteRecord(int salary_rule_id, Integer company_id, String deleted_by) {
        Map<String, Object> responce = new HashMap<>();
        try {
           iHtSalaryRulesRepository.deleteRecords(salary_rule_id, company_id, deleted_by);

           responce.put("success", "1");
           responce.put("error", "");
           responce.put("message", "Record deleted successfully!...");
           return responce;
       }catch (Exception e){
           e.printStackTrace();
       }finally {
            return responce;
        }
    }
}
