package com.erp.Contractors.Service;

import com.erp.Contractors.Model.CContractorsModel;

import org.json.JSONObject;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.Map;

public interface IContractorsService {

//	HashMap<String, Object> FnAddUpdateRecord(CContractorsModel cContractorsModel);
	Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject);

	Map<String, Object> FnDeleteRecord(int contractor_id, String deleted_by);

	Object FnShowAllRecords(Pageable pageable);

	Object FnShowAllActiveRecords(Pageable pageable);

	HashMap<String, Object> FnShowParticularRecordForUpdate(int contractor_id);

//	Object FnShowParticularRecord(int company_id, int company_branch_id, int contractor_id);
	Map<String, Object> FnShowParticularRecord(int company_id, int contractor_id);

	Object FnShowAllReportRecords(Pageable pageable);


}
