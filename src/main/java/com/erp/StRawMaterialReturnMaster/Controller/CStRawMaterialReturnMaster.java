package com.erp.StRawMaterialReturnMaster.Controller;

import com.erp.StRawMaterialReturnMaster.Service.CStRawMaterialReturnMasterService;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/StRawMaterialReturnMaster")
public class CStRawMaterialReturnMaster {

	private final CStRawMaterialReturnMasterService cStRawMaterialReturnMasterService;

	@PostMapping("/FnAddUpdateRecord/{acceptFlag}")
	public ResponseEntity<?> FnAddUpdateRecord(@RequestParam("StRawMaterialReturnMasterData") JSONObject jsonObject, @PathVariable String acceptFlag) {
		return cStRawMaterialReturnMasterService.FnAddUpdateRecord(jsonObject, acceptFlag);
	}

	@DeleteMapping("/FnDeleteRecord/{issue_return_master_transaction_id}/{company_id}/{deleted_by}")
	public ResponseEntity<?> FnDeleteRecord(@PathVariable int issue_return_master_transaction_id, @PathVariable int company_id,
	                                          @PathVariable String deleted_by) {
		return cStRawMaterialReturnMasterService.FnDeleteRecord(issue_return_master_transaction_id, company_id, deleted_by);

	}

	@GetMapping("/FnShowRawMaterialReturnDetails/{company_id}")
	public ResponseEntity<?> FnShowRawMaterialReturnDetails(@RequestParam("issue_return_no") String issue_return_no, @PathVariable int company_id)
			throws SQLException {
		return cStRawMaterialReturnMasterService.FnShowRawMaterialReturnDetails(issue_return_no, company_id);
	}
	@PostMapping("/FnAddUpdateRecordForRMReturn")
	public ResponseEntity<?> FnAddUpdateRecordForRMReturn(@RequestParam("MaterialReturnToStoreData") JSONObject jsonObject) {
		return cStRawMaterialReturnMasterService.FnAddUpdateRecordForRMReturn(jsonObject);
	}

	@GetMapping("/FnShowParticularReturnRecord/{company_id}")
	public ResponseEntity<?> FnShowParticularReturnRecordDetails(@PathVariable int company_id, @RequestParam String issue_return_no )
			throws SQLException {
		return cStRawMaterialReturnMasterService.FnShowParticularReturnRecordDetails(company_id, issue_return_no);
	}
}
