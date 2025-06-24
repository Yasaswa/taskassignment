package com.erp.weeklyoff.Service;

import com.erp.weeklyoff.Model.CWeeklyoffModel;
import org.json.JSONObject;
import org.springframework.data.domain.Pageable;

public interface IWeeklyoffService {

	JSONObject FnAddUpdateRecord(CWeeklyoffModel cWeeklyoffModel);

	Object FnDeleteRecord(int weeklyoff_id);

	Object FnShowAllRecords(Pageable pageable);

	Object FnShowAllActiveRecords(Pageable pageable);

	JSONObject FnShowParticularRecordForUpdate(int weeklyoff_id);

	JSONObject FnShowParticularRecord(int company_id, int company_branch_id, int weeklyoff_id);

	Object FnShowAllReportRecords(Pageable pageable);

}
