package com.erp.Customer_Bank.Service;

import org.json.JSONObject;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;

public interface ICustomerBankService {

	JSONObject FnAddUpdateRecord(JSONObject jsonObject);

	Object FnShowAllActiveRecords(Pageable pageable);

	HashMap<String, Object> FnShowParticularRecordForUpdate(int cust_branch_id, int company_id);

	Object FnDeleteRecord(int cust_bank_id);

	Object FnShowAllRecords(Pageable pageable);

	JSONObject FnShowParticularRecord(int company_id, int company_branch_id, int cust_bank_id);

}
