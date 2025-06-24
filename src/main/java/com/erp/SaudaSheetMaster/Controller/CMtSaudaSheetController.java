package com.erp.SaudaSheetMaster.Controller;

import com.erp.SaudaSheetMaster.Service.IMtSaudaSheetService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/SaudaSheetMaster")
public class CMtSaudaSheetController {
    @Autowired
    IMtSaudaSheetService iMtSaudaSheetService;

    @PostMapping("/FnAddUpdateRecord")
    Map<String, Object> FnAddUpdateRecord(@RequestParam("SaudaSheetMaster") JSONObject jsonObject) {
        return iMtSaudaSheetService.FnAddUpdateRecord(jsonObject);
    }

    @DeleteMapping("/FnDeleteRecord/{sauda_sheet_master_id}/{company_id}/{deleted_by}")
    public Map<String, Object> FnDeleteRecord(@PathVariable int sauda_sheet_master_id, @PathVariable int company_id,@PathVariable String deleted_by) {
        return iMtSaudaSheetService.FnDeleteRecord(sauda_sheet_master_id, company_id,deleted_by);

    }

    @GetMapping("/FnShowParticularRecordForUpdate/{sauda_sheet_master_id}/{company_id}")
    public Map<String, Object> FnShowParticularRecordForUpdate(@PathVariable int sauda_sheet_master_id, @PathVariable int company_id)  {
        Map<String, Object> responce =  iMtSaudaSheetService.FnShowParticularRecordForUpdate(sauda_sheet_master_id, company_id);
        return responce;
    }

}
