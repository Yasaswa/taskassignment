package com.erp.Customer_Bank.Controller;

import com.erp.Customer_Bank.Service.ICustomerBankService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;

@RestController
@RequestMapping(value = "/api/customerbank", produces = "application/json")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CCustomerBankController {

	@Autowired
	ICustomerBankService iCustomerBankService;

	@Autowired
	DataSource dataSource;

	@PostMapping("/FnAddUpdateRecord")
	public Object FnAddUpdateRecord(@RequestParam("customerBankJson") JSONObject jsonObject) throws SQLException {
		JSONObject responce = new JSONObject();
		responce = iCustomerBankService.FnAddUpdateRecord(jsonObject);

		return responce.toString();

	}

	@DeleteMapping("/FnDeleteRecord/{cust_bank_id}")
	public Object FnDeleteRecord(@PathVariable int cust_bank_id) {
		return iCustomerBankService.FnDeleteRecord(cust_bank_id);

	}

	@GetMapping("/FnShowAllRecords")
	Object FnShowAllRecords(Pageable pageable) throws SQLException {
		Object cCustomerBankViewModel = iCustomerBankService.FnShowAllRecords(pageable);

		return cCustomerBankViewModel.toString();
	}

	@GetMapping("/FnShowParticularRecord/{company_id}/{company_branch_id}/{cust_bank_id}")
	public Object FnShowParticularRecord(@PathVariable int company_id, @PathVariable int company_branch_id,
	                                     @PathVariable int cust_bank_id) throws SQLException {
		JSONObject resp = new JSONObject();

		resp = iCustomerBankService.FnShowParticularRecord(company_id, company_branch_id, cust_bank_id);

		return resp.toString();

	}

	@GetMapping("/FnShowAllActiveRecords")
	Object FnShowAllActiveRecords(Pageable pageable) throws SQLException {
		Object cCustomerBankViewModel = null;
		cCustomerBankViewModel = iCustomerBankService.FnShowAllActiveRecords(pageable);

		return cCustomerBankViewModel.toString();
	}

	@GetMapping("/FnShowParticularRecordForUpdate/{cust_branch_id}/{company_id}")
	public HashMap<String, Object> FnShowParticularRecordForUpdate(@PathVariable int cust_branch_id,
	                                                               @PathVariable int company_id) throws SQLException {
		HashMap<String, Object> responce = new HashMap<>();
		responce = iCustomerBankService.FnShowParticularRecordForUpdate(cust_branch_id, company_id);

		return responce;

	}

}
