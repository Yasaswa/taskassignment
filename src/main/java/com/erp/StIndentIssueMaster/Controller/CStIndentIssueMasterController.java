package com.erp.StIndentIssueMaster.Controller;

import com.erp.StIndentIssueMaster.Service.IStIndentIssueMasterService;
import com.erp.StIndentIssueMaster.Service.RescheduleIssueServiceImp;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/StIndentIssueMaster")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CStIndentIssueMasterController {

	@Autowired
	IStIndentIssueMasterService iStIndentIssueMasterService;
	@Autowired
	RescheduleIssueServiceImp rescheduleIssueServiceImp;

	@PostMapping("/FnAddUpdateRecord/{acceptFlag}")
	public Map<String, Object> FnAddUpdateRecord(@RequestParam("StIndentMaterialIssueData") JSONObject jsonObject, @PathVariable String acceptFlag) {

		Map<String, Object> response = iStIndentIssueMasterService.FnAddUpdateRecord(jsonObject, acceptFlag);
		if(response.containsKey("issuedBatchList")){
			JSONObject commonIdsObj = (JSONObject) jsonObject.get("commonIds");
			int company_id = commonIdsObj.getInt("company_id");
			int godownId = 2;
			List<Map<String, Object>> issuedBatchList = (List<Map<String, Object>>) response.get("issuedBatchList");
			if (!issuedBatchList.isEmpty()) {
				// Commit the transaction explicitly and clear the session
				 rescheduleIssueServiceImp.rescheduleStockIssue(issuedBatchList, company_id, godownId);
			}
		}

		return response;
	}


	@DeleteMapping("/FnDeleteRecord/{issue_version}/{company_id}/{deleted_by}")
	public Map<String, Object> FnDeleteRecord(@RequestParam("issue_no") String issue_no, @PathVariable int issue_version, @PathVariable int company_id, @PathVariable String deleted_by) {
		return iStIndentIssueMasterService.FnDeleteRecord(issue_no, issue_version, company_id, deleted_by);

	}


	@GetMapping("/FnShowAllDetailsAndMastermodelRecords/{issue_version}/{financial_year}/{company_id}")
	public Map<String, Object> FnShowAllDetailsandMastermodelRecords(@RequestParam("issue_no") String issue_no,
	                                                                 @PathVariable int issue_version, @PathVariable String financial_year, @PathVariable int company_id)
			throws SQLException {
		return iStIndentIssueMasterService.FnShowAllDetailsandMastermodelRecords(issue_no, issue_version, financial_year,
				company_id);
	}

	@PostMapping("/FnShowIndentDetailsRecords")
	public Map<String, Object> FnShowIndentDetailsRecords(@RequestParam("indentNos") JSONObject indentNo) {
		return iStIndentIssueMasterService.FnShowIndentDetailsRecords(indentNo);
	}


}
