package com.erp.HmCompOffDetails.Controller;

import com.erp.HmCompOffDetails.Model.CHmCompOffDetailsModel;
import com.erp.HmCompOffDetails.Service.IHmCompOffDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/HmCompoffDetails")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CHmCompOffDetailsController {

    @Autowired
    IHmCompOffDetailsService iHmCompOffDetailsService;

    @PostMapping("/FnAddUpdateRecord")
    public Map<String, Object> FnAddUpdateRecord(@RequestBody CHmCompOffDetailsModel cHmCompOffDetailsModel) {
        Map<String, Object> resp = iHmCompOffDetailsService.FnAddUpdateRecord(cHmCompOffDetailsModel);
        return resp;

    }

    @GetMapping("/FnShowParticularRecordForUpdate/{company_id}/{comp_off_intimation_details_id}")
    public Map<String, Object> FnShowParticularRecordForUpdate(@PathVariable int company_id,
                                                               @PathVariable int comp_off_intimation_details_id) {
        return iHmCompOffDetailsService.FnShowParticularRecordForUpdate(company_id, comp_off_intimation_details_id);
    }

    @DeleteMapping("/FnDeleteRecord/{punch_code}/{company_id}/{att_date_time}/{deleted_by}")
    public Object FnDeleteRecord(@PathVariable String punch_code,@PathVariable int company_id ,@PathVariable String att_date_time,@PathVariable String deleted_by) {
        return iHmCompOffDetailsService.FnDeleteRecord(punch_code,company_id,att_date_time,deleted_by);
    }

}