package com.erp.MtServiceReportingMaster.Service;
import java.util.Map;

import org.json.JSONObject;


public interface IMtServiceReportingMasterService {

	Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject);

	Map<String, Object> FnShowParticularRecordForUpdate(int service_reporting_master_transaction_id, int company_id);
	
	Map<String, Object> FnDeleteRecord(int service_reporting_master_transaction_id, int company_id, String deleted_by);

}
