package com.erp.Bank.Service;

import org.json.JSONObject;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface IBankService {

//	JSONObject FnAddUpdateRecord(CBankModel bankModel);

	Map<String, Object> FnShowParticularRecordForUpdate(int bank_id, int company_id);

	Object FnDeleteRecord(int bank_id, int company_id, String deleted_by);

	Object FnShowAllRecords();

	Object FnShowAllActiveRecords(int company_id, Pageable pageable);

	Object FnShowParticularRecord(int bank_id, Pageable pageable);

	Object FnShowAllReportRecords(Pageable pageable);

	Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject);


}
