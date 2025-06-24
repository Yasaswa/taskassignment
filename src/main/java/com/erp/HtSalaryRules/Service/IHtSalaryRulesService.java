package com.erp.HtSalaryRules.Service;

import org.json.JSONObject;

import java.util.Map;

public interface IHtSalaryRulesService {
    Map<String, Object> FnAddUpdateRecord(JSONObject cHtSalaryRulesModel);

    Map<String, Object> FnShowParticularRecord(Integer salaryRuleId, Integer companyId);

    Object FnDeleteRecord(int salary_rule_id, Integer company_id, String deleted_by);
}
