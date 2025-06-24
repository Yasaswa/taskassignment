package com.erp.ContractorCommission.Controller;

import com.erp.ContractorCommission.Model.CContractorCommissionModel;
import com.erp.ContractorCommission.Service.IContractorCommissionService;
import com.erp.JobType.Model.CJobTypeModel;
import com.erp.JobType.Service.JobTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Map;


@RestController
@RequestMapping(value = "/api/contractCommission", produces = "application/json")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CContractorCommissionController {

    @Autowired
    IContractorCommissionService iContractorCommissionService;

    @PostMapping("/FnAddUpdateRecord")
    public Map<String, Object> FnAddUpdateRecord(@RequestBody CContractorCommissionModel cContractorCommissionModel) {
        Map<String, Object> responce = iContractorCommissionService.FnAddUpdateRecord(cContractorCommissionModel);
        return responce;

    }

    @DeleteMapping("/FnDeleteRecord/{commission_rate_id}/{deleted_by}")
    public Map<String, Object> FnDeleteRecord(@PathVariable int commission_rate_id, @PathVariable String deleted_by) {
        return iContractorCommissionService.FnDeleteRecord(commission_rate_id, deleted_by);

    }



    @GetMapping("/FnShowParticularRecord/{commission_rate_id}")
    public Map<String, Object> FnShowParticularRecord( @PathVariable int commission_rate_id)
            throws SQLException {
        Map<String, Object> resp = iContractorCommissionService.FnShowParticularRecord(commission_rate_id);
        return resp;

    }

//    @GetMapping("/FnShowAllReportRecords")
//    public Map<String, Object> FnShowAllReportRecords(Pageable pageable) throws SQLException {
//        Map<String, Object> cJobTypeRptModel = iContractorCommissionService.FnShowAllReportRecords(pageable);
//        return cJobTypeRptModel;
//
//    }

}
