package com.erp.Customer_Branch.Service;

import com.erp.Customer_Branch.Model.CCustomerBranchModel;
import org.json.JSONObject;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface ICustomerBranchService {

	JSONObject FnAddUpdateRecord(CCustomerBranchModel customerBranchModel);

	Object FnShowAllRecords(Pageable pageable);

	Object FnShowAllActiveRecords(Pageable pageable);

	Object FnShowParticularRecordForUpdate(int cust_branch_id);

	Object FnDeleteRecord(int cust_branch_id, int company_id, String deleted_by);

	Map<String, Object> FnShowAllReportRecords(Pageable pageable);

	Object FnShowParticularRecord(int company_id, int company_branch_id, int cust_branch_id);


}
