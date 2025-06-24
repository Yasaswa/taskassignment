package com.erp.State.Service;

import com.erp.State.Model.CStateModel;
import org.json.JSONObject;
import org.springframework.data.domain.Pageable;

public interface IStateService {

	JSONObject FnAddUpdateRecord(CStateModel cStateModel);

	Object FnDeleteRecord(int state_id);

	Object FnShowAllRecords(Pageable pageable);

	Object FnShowAllActiveRecords(Pageable pageable);

	Object FnShowParticularRecordForUpdate(int state_id);

	Object FnShowParticularRecord(int company_id, int state_id);

	Object FnShowAllReportRecords(Pageable pageable);

}
