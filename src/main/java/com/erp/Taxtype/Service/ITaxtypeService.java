package com.erp.Taxtype.Service;

import com.erp.Taxtype.Model.CTaxtypeModel;
import org.json.JSONObject;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface ITaxtypeService {

	Map<String, Object> FnAddUpdateRecord(CTaxtypeModel cTaxtypeModel);

	Object FnDeleteRecord(int taxtype_id, String deleted_by);

	Object FnShowAllRecords(Pageable pageable);

	Object FnShowAllActiveRecords(Pageable pageable);

	JSONObject FnShowParticularRecordForUpdate(int taxtype_id);

	JSONObject FnShowParticularRecord(int company_id, int taxtype_id);

	Object FnShowAllReportRecords(Pageable pageable);


}
