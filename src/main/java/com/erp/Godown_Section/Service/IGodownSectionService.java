package com.erp.Godown_Section.Service;

import com.erp.Godown_Section.Model.CGodownSectionModel;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface IGodownSectionService {

	JSONObject FnAddUpdateRecord(CGodownSectionModel cGodownSectionModel);

	Object FnShowAllRecords(Pageable pageable);

	Object FnDeleteRecord(int godown_section_id, String deleted_by);

	Object FnShowAllActiveRecords(Pageable pageable);

	JSONObject FnShowParticularRecordForUpdate(int godown_section_id);

	JSONObject FnShowParticularRecord(int company_id, int godown_section_id);

	Page<List<Map<String, Object>>> FnShowAllReportRecords(Pageable pageable);

}
