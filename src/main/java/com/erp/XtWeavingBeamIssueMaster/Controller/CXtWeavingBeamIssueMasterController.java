package com.erp.XtWeavingBeamIssueMaster.Controller;

import com.erp.XtWeavingBeamIssueMaster.Repository.IXtWeavingBeamIssueMasterRepository;
import com.erp.XtWeavingBeamIssueMaster.Service.IXtWeavingBeamIssueMasterService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/XtWeavingBeamIssueMaster")
public class CXtWeavingBeamIssueMasterController {

    @Autowired
    IXtWeavingBeamIssueMasterService iXtWeavingBeamIssueMasterService;
    @Autowired
    IXtWeavingBeamIssueMasterRepository iXtWeavingBeamIssueMasterRepository;

    @PostMapping("/FnAddUpdateRecord")
    public Map<String, Object> FnAddUpdateRecord(@RequestParam("BeamIssueMasterData") JSONObject jsonObject) {
        Map<String, Object> resp = new HashMap<>();
        resp = iXtWeavingBeamIssueMasterService.FnAddUpdateRecord(jsonObject);
        return resp;

    }

    @DeleteMapping("/FnDeleteRecord/{weaving_beam_issue_master_id}/{deleted_by}")
    public Map<String, Object> FnDeleteRecord(@PathVariable int weaving_beam_issue_master_id, @PathVariable String deleted_by) {
        Map<String, Object> resp = iXtWeavingBeamIssueMasterService.FnDeleteRecord(weaving_beam_issue_master_id, deleted_by);
        return resp;

    }

    @GetMapping("/FnShowParticularRecordForUpdate/{company_id}/{weaving_beam_issue_master_id}")
    public Map<String, Object> FnShowParticularRecordForUpdate(@PathVariable int company_id,
                                                               @PathVariable int weaving_beam_issue_master_id) {
        return iXtWeavingBeamIssueMasterService.FnShowParticularRecordForUpdate(company_id, weaving_beam_issue_master_id);
    }

    @GetMapping("/FnGetBeamIssueSlipMasterData/{company_id}/{set_no}/{beam_no}/{status}/{sort_no}")
    public Map<String, Object> FnGetBeamIssueSlipMasterData(@PathVariable Integer company_id, @PathVariable String set_no, @PathVariable String beam_no,
                                                            @PathVariable boolean status, @PathVariable String sort_no) {
        Map<String, Object> response = new HashMap<>();
        try {
            ArrayList<Map<String, Object>> getBeamSizedMasterData = new ArrayList<>();
            if (status && !sort_no.equals("sort_no")) {
                getBeamSizedMasterData = iXtWeavingBeamIssueMasterRepository.FnGetBeamIssueSlipMasterDataBySortNo(company_id, sort_no);
            } else {
                getBeamSizedMasterData = iXtWeavingBeamIssueMasterRepository.FnGetBeamIssueSlipMasterData(company_id, set_no, beam_no);
            }
            response.put("success", 1);
            response.put("BeamSizedMasterData", getBeamSizedMasterData);
        } catch (Exception e) {
            response.put("data", "");
            response.put("error", e.getMessage());
        }

        return response;
    }
}
