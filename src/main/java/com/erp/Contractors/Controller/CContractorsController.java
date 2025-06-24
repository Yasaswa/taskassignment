package com.erp.Contractors.Controller;

import com.erp.Contractors.Model.CContractorsModel;
import com.erp.Contractors.Service.IContractorsService;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/contractor", produces = "application/json")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CContractorsController {

	@Autowired
	IContractorsService iContractorsService;

//	@PostMapping("/FnAddUpdateRecord")
//	public HashMap<String, Object> FnAddUpdateRecord(@RequestBody CContractorsModel cContractorsModel)
//			throws SQLException {
//		HashMap<String, Object> resp = new HashMap<>();
//		try {
//			resp = iContractorsService.FnAddUpdateRecord(cContractorsModel);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return resp;
//	}
	
	@PostMapping("/FnAddUpdateRecord")
	public Map<String, Object> FnAddUpdateRecord(@RequestParam("TransContractorDetailData") JSONObject jsonObject)
			throws SQLException {
		Map<String, Object> resp = iContractorsService.FnAddUpdateRecord(jsonObject);
		return resp;
	}
	
	@DeleteMapping("/FnDeleteRecord/{contractor_id}/{deleted_by}")
	public Map<String, Object> FnDeleteRecord(@PathVariable int contractor_id, @PathVariable String deleted_by) {
		return iContractorsService.FnDeleteRecord(contractor_id, deleted_by);

	}

	@GetMapping("/FnShowAllRecords")
	Object FnShowAllRecords(Pageable pageable) throws SQLException {
		Object cContractorsViewModel = null;
		try {
			cContractorsViewModel = iContractorsService.FnShowAllRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return cContractorsViewModel;
	}

	@GetMapping("/FnShowAllActiveRecords")
	Object FnShowAllActiveRecords(Pageable pageable) throws SQLException {

		Object cContractorsViewModel = null;

		try {
			cContractorsViewModel = iContractorsService.FnShowAllActiveRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cContractorsViewModel;
	}

	@GetMapping("/FnShowParticularRecordForUpdate/{contractor_id}/{company_id}")
	public HashMap<String, Object> FnShowParticularRecordForUpdate(@PathVariable int contractor_id)
			throws SQLException {
		HashMap<String, Object> cContractorsViewModel = new HashMap<>();
		cContractorsViewModel = iContractorsService.FnShowParticularRecordForUpdate(contractor_id);
 		return cContractorsViewModel;
 	}

//	@GetMapping("/FnShowParticularRecord/{company_id}/{company_branch_id}/{contractor_id}")
//	public Object FnShowParticularRecord(@PathVariable int company_id, @PathVariable int company_branch_id,
//	                                     @PathVariable int contractor_id) throws SQLException {
//		Object cContractorsViewModel = null;
//		try {
//			cContractorsViewModel = iContractorsService.FnShowParticularRecord(company_id, company_branch_id, contractor_id);
//		
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return cContractorsViewModel;
//
//	}

	@GetMapping("/FnShowParticularRecord/{company_id}/{contractor_id}")
	public Map<String, Object> FnShowParticularRecord(@PathVariable int company_id, @PathVariable int contractor_id) throws SQLException {
		return iContractorsService.FnShowParticularRecord(company_id, contractor_id);
	}

	
	
	
	@GetMapping("/FnShowAllReportRecords")
	Object FnShowAllReportRecords(Pageable pageable) throws SQLException {
		Object CContractorsRptModel = null;
		try {
			CContractorsRptModel = iContractorsService.FnShowAllReportRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return CContractorsRptModel;

	}

}
