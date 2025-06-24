package com.erp.Customer.Controller;


import com.erp.Customer.Service.ICustomerService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/customer")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CCustomerController {

	@Autowired
	ICustomerService iCustomerService;

	@Autowired
	private JdbcTemplate jdbcTemplate;

//	@PostMapping("/FnAddUpdateRecord")
//	public Object FnAddUpdateRecord(@RequestBody CCustomerModel customerModel) throws SQLException {
//		JSONObject resp = new JSONObject();
//		try {
//
//			resp = iCustomerService.FnAddUpdateRecord(customerModel);
//			System.out.println("Responce: " + resp.toString());
//			return resp.toString();
//
//		} catch (Exception e) {
//		}
//		return resp.toString();
//
//	}

	@PostMapping("/FnAddUpdateRecord")
	public Map<String, Object> FnAddUpdateRecord(@RequestParam("CustomerServiceData") JSONObject jsonObject) {
		Map<String, Object> response = iCustomerService.FnAddUpdateRecord(jsonObject);
		return response;
	}

	@GetMapping("/FnShowCustomerBankBranchAndContactAllRecords/{customer_id}/{company_id}")
	public Map<String, Object> FnShowCustomerBankBranchAndContactAllRecords(@PathVariable int customer_id, @PathVariable int company_id) {
		return iCustomerService.FnShowCustomerBankBranchAndContactAllRecords(customer_id, company_id);
	}

	@DeleteMapping("/FnDeleteCustomerBranchRecord/{customer_id}/{cust_branch_id}/{deleted_by}")
	public Map<String, Object> FnDeleteSupplierBranchRecord(@PathVariable int customer_id, @PathVariable int cust_branch_id, @PathVariable String deleted_by) {
		return iCustomerService.FnDeleteCustomerBranchRecord(customer_id, cust_branch_id, deleted_by);

	}

	@DeleteMapping("/FnDeleteRecord/{customer_id}/{deleted_by}/{company_id}")
	public Object FnDeleteRecord(@PathVariable int customer_id, @PathVariable String deleted_by,
	                             @PathVariable int company_id) {
		return iCustomerService.FnDeleteRecord(customer_id, deleted_by, company_id);

	}

	@GetMapping("/FnShowAllRecords")
	public Map<String, Object> FnShowAllRecords(Pageable pageable) throws SQLException {
		return iCustomerService.FnShowAllRecords(pageable);
	}

	@GetMapping("/FnShowAllActiveRecords")
	Object FnShowAllActiveRecords(Pageable pageable) throws SQLException {
		Object ccustomerViewModel = null;
		try {
			ccustomerViewModel = iCustomerService.FnShowAllActiveRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ccustomerViewModel.toString();
	}

	@GetMapping("/FnShowAllReportRecords")
	public Map<String, Object> FnShowAllReportRecords(Pageable pageable) throws SQLException {
		return iCustomerService.FnShowAllReportRecords(pageable);
	}

	@GetMapping("/FnShowParticularRecordForUpdate/{customer_id}/{company_id}")
	public Object FnShowParticularRecordForUpdate(@PathVariable int customer_id, @PathVariable int company_id)
			throws SQLException {
		JSONObject resp = new JSONObject();
		resp = iCustomerService.FnShowParticularRecordForUpdate(customer_id, company_id);

		return resp.toString();

	}

	@GetMapping("/FnShowParticularRecord/{company_id}/{company_branch_id}/{customer_id}")
	public Object FnShowParticularRecord(@PathVariable int company_id, @PathVariable int company_branch_id,
	                                     @PathVariable int customer_id) throws SQLException {
		JSONObject resp = new JSONObject();
		resp = iCustomerService.FnShowParticularRecord(company_id, company_branch_id, customer_id);

		return resp.toString();
	}

	@PostMapping("/FnGenerateCode/{company_id}")
	public Object FnShowParticularRecord(@RequestParam("data") JSONObject json, @PathVariable int company_id)
			throws SQLException {
		String jsonData = json.toString();
		String tblName = json.getString("tblName");
		String fieldName = json.getString("fieldName");
		int length = json.getInt("Length");
		String additionalParam1 = json.getString("additionalParam1");
		String additionalParam1Value = json.getString("additionalParam1Value");

		String autoNumber = "";
		int diffLength = 0;
		String strAppend = "";

		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("SELECT ");
		queryBuilder.append("COALESCE(MAX(SUBSTRING(" + fieldName + ", LENGTH(" + fieldName + ") - " + length + " + 1, "
				+ length + "))+1, 1) ");
		queryBuilder.append("FROM " + tblName);
		// below line coment bcoz supplier and customer code generate without company dependency.
//		queryBuilder.append(" WHERE company_id = '" + company_id + "' ");

		if (!additionalParam1.equals("") && !additionalParam1Value.equals("")) {
			queryBuilder.append(" WHERE " + additionalParam1 + " = '" + additionalParam1Value + "'");
		}

		Integer maxNumber = jdbcTemplate.queryForObject(queryBuilder.toString(), Integer.class);
		autoNumber = maxNumber.toString();

		if (length > autoNumber.length())
			diffLength = length - autoNumber.length();
		else if (length < autoNumber.length())
			diffLength = autoNumber.length() - length;
		if (diffLength > 0) {
			for (int recCnt = 0; recCnt < diffLength; recCnt++)
				strAppend = "0" + strAppend;
		}
		autoNumber = strAppend + autoNumber;
		return autoNumber;
	}

}
