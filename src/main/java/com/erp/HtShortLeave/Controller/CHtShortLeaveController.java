package com.erp.HtShortLeave.Controller;

import com.erp.HmLeavesApplications.Model.CHmLeavesApplicationsModel;
import com.erp.HtShortLeave.Model.CHtShortLeaveModel;
import com.erp.HtShortLeave.Service.IHtShortLeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/HtShortLeave")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CHtShortLeaveController {
    @Autowired
    IHtShortLeaveService iHtShortLeaveService;

    @PostMapping("/FnAddUpdateRecord/{isApprove}")
    public Map<String, Object> FnAddUpdateRecord(@RequestBody CHtShortLeaveModel cHtShortLeaveModel, @PathVariable boolean isApprove) {
        Map<String, Object> resp = iHtShortLeaveService.FnAddUpdateRecord(cHtShortLeaveModel, isApprove);
        return resp;

    }
    @DeleteMapping("/FnDeleteRecord/{short_leave_transaction_id}/{deleted_by}")
    public Map<String, Object> FnDeleteRecord(@PathVariable int short_leave_transaction_id, @PathVariable String deleted_by) {
        Map<String, Object> resp = iHtShortLeaveService.FnDeleteRecord(short_leave_transaction_id, deleted_by);
        return resp;
    }
    @GetMapping("/FnShowParticularRecordForUpdate/{short_leave_transaction_id}/{company_id}")
    public Map<String, Object> FnShowParticularRecordForUpdate(@PathVariable int short_leave_transaction_id, @PathVariable int company_id)  {
        return  iHtShortLeaveService.FnShowParticularRecordForUpdate(short_leave_transaction_id, company_id);
    }
}
