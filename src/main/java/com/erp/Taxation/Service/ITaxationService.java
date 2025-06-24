package com.erp.Taxation.Service;

import com.erp.Taxation.Model.CTaxationModel;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface ITaxationService {

	Map<String, Object> FnAddUpdateRecord(CTaxationModel cTaxationModel);

	Object FnDeleteRecord(int taxation_id, String deleted_by);

	Page<List<Map<String, Object>>> FnShowAllReportRecords(Pageable pageable);

	Object FnShowAllRecords(Pageable pageable);

	Object FnShowAllActiveRecords(Pageable pageable);

	JSONObject FnShowParticularRecordForUpdate(int taxation_id);


	JSONObject FnShowParticularRecord(int company_id, int taxation_id);


}