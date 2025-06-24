package com.erp.XtStoppageProduction.Controller;

import com.erp.XtStoppageProduction.Service.IXtWeavingProductionStoppageService;
import com.erp.XtWeavingProductionSizingDetails.Service.IXtWeavingProductionSizingDetailsService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/XtWeavingProductionStoppage")
public class CxtWeavingProductionStoppageController {

    @Autowired
    IXtWeavingProductionStoppageService iXtWeavingProductionStoppageService;

    @PostMapping("/FnAddUpdateRecord")
    public Map<String, Object> FnAddUpdateRecord(@RequestParam("WeavingProductionStoppageData") JSONObject jsonObject) {
        Map<String, Object> response = iXtWeavingProductionStoppageService.FnAddUpdateRecord(jsonObject);
        return response;

    }

    @DeleteMapping("/FnDeleteRecord/{weaving_production_sizing_master_id}/{deleted_by}")
    public Map<String, Object> FnDeleteRecord(@PathVariable int weaving_production_stoppage_id, @PathVariable String deleted_by) {
        return iXtWeavingProductionStoppageService.FnDeleteRecord(weaving_production_stoppage_id, deleted_by);

    }

    @GetMapping("/FnShowParticularRecordForUpdate/{weaving_production_stoppage_id}/{sub_section_id}/{company_id}")
    public Map<String, Object> FnShowParticularRecordForUpdate(@PathVariable Integer weaving_production_stoppage_id, @PathVariable int sub_section_id, @PathVariable int company_id)  {
        return  iXtWeavingProductionStoppageService.FnShowParticularRecordForUpdate(weaving_production_stoppage_id,sub_section_id, company_id);
    }



    @GetMapping("/FnShowParticularRecordForUpdateWVWV/{weaving_production_stoppage_id}/{sub_section_id}/{company_id}")
    public Map<String, Object> FnShowParticularRecordForUpdateWVWV(@PathVariable int weaving_production_stoppage_id,@PathVariable int sub_section_id ,@PathVariable int company_id)  {
        return  iXtWeavingProductionStoppageService.FnShowParticularRecordForUpdateWVWV(weaving_production_stoppage_id,sub_section_id, company_id);
    }

}
