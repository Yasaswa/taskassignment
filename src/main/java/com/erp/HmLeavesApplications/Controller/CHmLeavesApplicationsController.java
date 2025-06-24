package com.erp.HmLeavesApplications.Controller;

import com.erp.HmLeavesApplications.Model.CHmLeavesApplicationsModel;
import com.erp.HmLeavesApplications.Service.IHmLeavesApplicationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/HmLeavesApplications")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CHmLeavesApplicationsController {

    @Autowired
    IHmLeavesApplicationsService iHmLeavesApplicationsService;

    @PostMapping("/FnAddUpdateRecord/{isApprove}")
    public Map<String, Object> FnAddUpdateRecord(@RequestBody CHmLeavesApplicationsModel HmLeavesApplicationsModel, @PathVariable boolean isApprove) {
        Map<String, Object> resp = iHmLeavesApplicationsService.FnAddUpdateRecord(HmLeavesApplicationsModel, isApprove);
        return resp;

    }

    @DeleteMapping("/FnDeleteRecord/{punch_code}/{leaves_requested_from_date}/{leaves_requested_to_date}/{deleted_by}")
    public Map<String, Object> FnDeleteRecord(@PathVariable String punch_code, @PathVariable String leaves_requested_from_date, @PathVariable String leaves_requested_to_date,
                                              @PathVariable String deleted_by) {
        Map<String, Object> resp = iHmLeavesApplicationsService.FnDeleteRecord(punch_code, leaves_requested_from_date, leaves_requested_to_date, deleted_by);
        return resp;
    }

    @GetMapping("/FnShowParticularRecordForUpdate/{company_id}/{leaves_transaction_id}")
    public Map<String, Object> FnShowParticularRecordForUpdate(@PathVariable int company_id,
                                                               @PathVariable int leaves_transaction_id) {
        return iHmLeavesApplicationsService.FnShowParticularRecordForUpdate(company_id, leaves_transaction_id);
    }


    @GetMapping("/FnShowLeaveBalanceDetails/{company_id}/{employee_code}")
    public Map<String, Object> FnShowLeaveBalanceDetails(@PathVariable int company_id,
                                                         @PathVariable String employee_code) {
        Map<String, Object> responce = iHmLeavesApplicationsService.FnShowLeaveBalanceDetails(company_id, employee_code);
        return responce;
    }


    @GetMapping("/FnShowExisitingCount/{employee_code}/{leaves_requested_from_date}/{leaves_requested_to_date}")
    public Map<String, Object> FnShowExisitingCount(@PathVariable String employee_code,
                                                    @PathVariable String leaves_requested_from_date, @PathVariable String leaves_requested_to_date) {
        Map<String, Object> responce = iHmLeavesApplicationsService.FnShowExisitingCount(employee_code, leaves_requested_from_date, leaves_requested_to_date);
        return responce;
    }

//    @GetMapping("/FnShowCheckSandwich/{weeklyOff}/{leavesRequestedFromDate}/{leavesRequestedToDate}")
//    public Map<String, Object> FnShowCheckSandwich(
//            @PathVariable String weeklyOff,
//            @PathVariable String leavesRequestedFromDate,
//            @PathVariable String leavesRequestedToDate) {
//
//        // Prepare the response map
//        Map<String, Object> response = new HashMap<>();
//
//        // Call the service method
//        boolean isSandwiched = iHmLeavesApplicationsService.FnShowCheckSandwich(weeklyOff, leavesRequestedFromDate, leavesRequestedToDate);
//
//        // Add result to response
//        response.put("isSandwiched", isSandwiched);
//
//        return response;
//    }


}
