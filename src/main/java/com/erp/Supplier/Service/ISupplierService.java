package com.erp.Supplier.Service;

import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface ISupplierService {

	//JSONObject FnAddUpdateRecord(CSupplierModel cSupplierModel);

	Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject);

	Map<String, Object> FnDeleteRecord(int supplier_id, String deleted_by);

	Object FnShowAllActiveRecords(Pageable pageable);

	Map<String, Object> FnShowParticularRecordForUpdate(int supplier_id);

	Object FnShowAllRecords(Pageable pageable);

	Object FnShowParticularRecord(int company_id, int company_branch_id, int supplier_id);

	Page<List<Map<String, Object>>> FnShowAllReportRecords(Pageable pageable);

	Map<String, Object> FnShowParticularRecordForUpdateForBranch(int supp_branch_id);

	Map<String, Object> FnDeleteSupplierBranchRecord(int supp_id, int supp_branch_id, String deleted_by);

}
