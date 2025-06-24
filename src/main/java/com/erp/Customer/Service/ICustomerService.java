package com.erp.Customer.Service;

import org.json.JSONObject;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface ICustomerService {

	Map<String, Object> FnShowAllRecords(Pageable pageable);

	Object FnShowAllActiveRecords(Pageable pageable);

	Map<String, Object> FnShowAllReportRecords(Pageable pageable);

	Object FnDeleteRecord(int customer_id, String deleted_by, int company_id);

	JSONObject FnShowParticularRecordForUpdate(int customer_id, int company_id);

	JSONObject FnShowParticularRecord(int company_id, int company_branch_id, int customer_id);

//	JSONObject FnAddUpdateRecord(CCustomerModel customerModel);

	Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject);

	Map<String, Object> FnDeleteCustomerBranchRecord(int customer_id, int cust_branch_id, String deleted_by);

	Map<String, Object> FnShowCustomerBankBranchAndContactAllRecords(int customer_id, int company_id);
}
