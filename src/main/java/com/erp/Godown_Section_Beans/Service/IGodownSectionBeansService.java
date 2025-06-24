package com.erp.Godown_Section_Beans.Service;

import com.erp.Godown_Section_Beans.Model.CGodownSectionBeansModel;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface IGodownSectionBeansService {

	JSONObject FnAddUpdateRecord(CGodownSectionBeansModel cGodownSectionBeansModel);

	Object FnDeleteRecord(int godown_section_beans_id, String deleted_by);

	Object FnShowAllActiveRecords(Pageable pageable);

	Object FnShowAllRecords(Pageable pageable);

	JSONObject FnShowParticularRecordForUpdate(int godown_section_beans_id);

	Page<List<Map<String, Object>>> FnShowAllReportRecords(Pageable pageable);

	JSONObject FnShowParticularRecord(int company_id, int godown_section_beans_id);

}
