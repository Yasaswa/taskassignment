package com.erp.Supplier.Controller;

import com.erp.Supplier.Service.ISupplierService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/supplier", produces = "application/json")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CSupplierController {

	@Autowired
	ISupplierService iSupplierService;

	@PostMapping("/FnAddUpdateRecord")
	public Map<String, Object> FnAddUpdateRecord(@RequestParam("SupplierServiceData") JSONObject jsonObject) {
		Map<String, Object> response = iSupplierService.FnAddUpdateRecord(jsonObject);
		return response;

	}

	@DeleteMapping("/FnDeleteRecord/{supplier_id}/{deleted_by}")
	public Map<String, Object> FnDeleteRecord(@PathVariable int supplier_id, @PathVariable String deleted_by) {
		return iSupplierService.FnDeleteRecord(supplier_id, deleted_by);

	}

	@GetMapping("/FnShowAllRecords")
	Object FnShowAllRecords(Pageable pageable) throws SQLException {
		Object cSupplierViewModel = null;
		try {
			cSupplierViewModel = iSupplierService.FnShowAllRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cSupplierViewModel.toString();
	}

	@GetMapping("/FnShowAllActiveRecords")
	Object FnShowAllActiveRecords(Pageable pageable) throws SQLException {

		Object cSupplierViewModel = null;

		try {
			cSupplierViewModel = iSupplierService.FnShowAllActiveRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cSupplierViewModel.toString();
	}

	@GetMapping("/FnShowParticularRecordForUpdate/{supplier_id}")
	public Map<String, Object> FnShowParticularRecordForUpdate(@PathVariable int supplier_id) throws SQLException {

		return iSupplierService.FnShowParticularRecordForUpdate(supplier_id);

	}

	@GetMapping("/FnShowParticularRecord/{company_id}/{company_branch_id}/{supplier_id}")
	public Object FnShowParticularRecord(@PathVariable int company_id, @PathVariable int company_branch_id,
	                                     @PathVariable int supplier_id) throws SQLException {
		Object cSupplierViewModel = null;
		try {
			cSupplierViewModel = iSupplierService.FnShowParticularRecord(company_id, company_branch_id, supplier_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cSupplierViewModel.toString();

	}

	@GetMapping("/FnShowAllReportRecords")
	public Page<List<Map<String, Object>>> FnShowAllReportRecords(Pageable pageable) {
		return iSupplierService.FnShowAllReportRecords(pageable);

	}

	@GetMapping("/FnShowParticularRecordForUpdateForBranch/{supp_branch_id}")
	public Map<String, Object> FnShowParticularRecordForUpdateForBranch(@PathVariable int supp_branch_id) throws SQLException {

		return iSupplierService.FnShowParticularRecordForUpdateForBranch(supp_branch_id);

	}


	@DeleteMapping("/FnDeleteSupplierBranchRecord/{supp_id}/{supp_branch_id}/{deleted_by}")
	public Map<String, Object> FnDeleteSupplierBranchRecord(@PathVariable int supp_id, @PathVariable int supp_branch_id, @PathVariable String deleted_by) {
		return iSupplierService.FnDeleteSupplierBranchRecord(supp_id, supp_branch_id, deleted_by);

	}

}
