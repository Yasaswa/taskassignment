package com.erp.HtSalaryRules.Controller;

import com.erp.HtSalaryRules.Model.CHtSalaryRulesModel;
import com.erp.HtSalaryRules.Service.CHtSalaryRulesServiceImpl;
import com.erp.HtSalaryRules.Service.IHtSalaryRulesService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/salaryRules", produces = "application/json")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CHtSalaryRulesController {

    @Autowired
    IHtSalaryRulesService iHtSalaryRulesService;

    @PostMapping("/FnAddUpdateRecord")
    public Map<String, Object> FnAddUpdateRecord(@RequestParam("SalaryRulesObject") JSONObject jsonObject) {
        return iHtSalaryRulesService.FnAddUpdateRecord(jsonObject);
    }


    @GetMapping("/FnShowParticularRecord/{property_id}/{company_id}")
    public Map<String, Object> FnShowParticularRecord( @PathVariable Integer property_id, @PathVariable Integer company_id)
            throws SQLException {
        Map<String, Object> resp = iHtSalaryRulesService.FnShowParticularRecord(property_id, company_id);
        return resp;
    }

    @DeleteMapping("/FnDeleteRecord/{salary_rule_id}/{company_id}/{deleted_by}")
    public Object FnDeleteRecord(@PathVariable int salary_rule_id, @PathVariable Integer company_id, @PathVariable String deleted_by) {
        return iHtSalaryRulesService.FnDeleteRecord(salary_rule_id, company_id, deleted_by);

    }

}
