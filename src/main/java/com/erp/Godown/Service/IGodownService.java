package com.erp.Godown.Service;

import com.erp.Godown.Model.CGodownModel;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface IGodownService {

	JSONObject FnAddUpdateRecord(CGodownModel cGodownModel);

	Object FnDeleteRecord(int godown_id, String deleted_by);

	Object FnShowAllRecords(Pageable pageable);

	Object FnShowAllActiveRecords(Pageable pageable);

	JSONObject FnShowParticularRecordForUpdate(int godown_id);

	JSONObject FnShowParticularRecord(int company_id, int godown_id);

	Page<List<Map<String, Object>>> FnShowAllReportRecords(Pageable pageable);

}
