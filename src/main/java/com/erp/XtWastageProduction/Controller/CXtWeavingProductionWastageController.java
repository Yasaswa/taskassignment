package com.erp.XtWastageProduction.Controller;

import java.util.Map;


import com.erp.XtWastageProduction.Service.IXtWeavingProductionWastageService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.erp.XtWeavingProductionSizingDetails.Service.IXtWeavingProductionSizingDetailsService;

@RestController
@RequestMapping("/api/XtWeavingProductionWastage")
public class CXtWeavingProductionWastageController {

    @Autowired
    IXtWeavingProductionWastageService iXtWeavingProductionWastageService;

    @PostMapping("/FnAddUpdateRecord")
    public Map<String, Object> FnAddUpdateRecord(@RequestParam("WeavingProductionWatageData") JSONObject jsonObject) {
        Map<String, Object> responce = iXtWeavingProductionWastageService.FnAddUpdateRecord(jsonObject);
        return responce;

    }

    @DeleteMapping("/FnDeleteRecord/{weaving_production_wastage_id}/{deleted_by}")
    public Map<String, Object> FnDeleteRecord(@PathVariable int weaving_production_wastage_id, @PathVariable String deleted_by) {
        return iXtWeavingProductionWastageService.FnDeleteRecord(weaving_production_wastage_id, deleted_by);

    }

    @GetMapping("/FnShowParticularRecordForUpdate/{production_set_no}/{sub_section_id}/{company_id}")
    public Map<String, Object> FnShowParticularRecordForUpdate(@PathVariable String production_set_no,@PathVariable int sub_section_id, @PathVariable int company_id)  {
        return  iXtWeavingProductionWastageService.FnShowParticularRecordForUpdate(production_set_no,sub_section_id, company_id);
    }

    @GetMapping("/FnShowParticularRecordForUpdateWVWV/{weaving_production_wastage_id}/{sub_section_id}/{company_id}")
    public Map<String, Object> FnShowParticularRecordForUpdateWVWV(@PathVariable int weaving_production_wastage_id,@PathVariable int sub_section_id ,@PathVariable int company_id)  {
        return  iXtWeavingProductionWastageService.FnShowParticularRecordForUpdateWVWV(weaving_production_wastage_id,sub_section_id, company_id);
    }

}
