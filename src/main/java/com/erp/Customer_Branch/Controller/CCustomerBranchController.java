package com.erp.Customer_Branch.Controller;

import com.erp.Customer_Branch.Model.CCustomerBranchModel;
import com.erp.Customer_Branch.Service.ICustomerBranchService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/customerbranch", produces = "application/json")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CCustomerBranchController {

	@Autowired
	ICustomerBranchService iCustomerBranchService;

	@PostMapping("/FnAddUpdateRecord")
	public Object FnAddUpdateRecord(@RequestBody CCustomerBranchModel customerBranchModel) throws SQLException {
		JSONObject resp = new JSONObject();
		try {
			resp = iCustomerBranchService.FnAddUpdateRecord(customerBranchModel);


		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp.toString();
	}


	@DeleteMapping("/FnDeleteRecord/{cust_branch_id}/{company_id}/{deleted_by}")
	public Object FnDeleteRecord(@PathVariable int cust_branch_id, @PathVariable int company_id, @PathVariable String deleted_by) {
		return iCustomerBranchService.FnDeleteRecord(cust_branch_id, company_id, deleted_by);

	}

	@GetMapping("/FnShowAllRecords")
	Object FnShowAllRecords(Pageable pageable) throws SQLException {
		Object cCustomerBranchViewModel = null;
		try {
			cCustomerBranchViewModel = iCustomerBranchService.FnShowAllRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cCustomerBranchViewModel.toString();
	}

	@GetMapping("/FnShowAllActiveRecords")
	Object FnShowAllActiveRecords(Pageable pageable) throws SQLException {
		Object cCustomerBranchViewModel = null;

		try {
			cCustomerBranchViewModel = iCustomerBranchService.FnShowAllActiveRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cCustomerBranchViewModel.toString();
	}

	@GetMapping("/FnShowParticularRecord/{company_id}/{company_branch_id}/{cust_branch_id}")
	public Object FnShowParticularRecord(@PathVariable int company_id, @PathVariable int company_branch_id,
	                                     @PathVariable int cust_branch_id) throws SQLException {
		Object cCustomerBranchViewModel = null;
		try {
			cCustomerBranchViewModel = iCustomerBranchService.FnShowParticularRecord(company_id, company_branch_id,
					cust_branch_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cCustomerBranchViewModel.toString();

	}

	@GetMapping("/FnShowParticularRecordForUpdate/{cust_branch_id}")
	public Object FnShowParticularRecordForUpdate(@PathVariable int cust_branch_id) throws SQLException {

		Object cCustomerBranchViewModel = null;
		try {
			cCustomerBranchViewModel = iCustomerBranchService.FnShowParticularRecordForUpdate(cust_branch_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cCustomerBranchViewModel.toString();

	}

	@GetMapping("/FnShowAllReportRecords")
	public Map<String, Object> FnShowAllReportRecords(Pageable pageable) throws SQLException {
		return iCustomerBranchService.FnShowAllReportRecords(pageable);

	}

}
