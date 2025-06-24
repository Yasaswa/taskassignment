package com.erp.District.Service;

import com.erp.District.Model.CDistrictModel;
import org.json.simple.JSONObject;
import org.springframework.data.domain.Pageable;

public interface IDistrictService {

	JSONObject FnAddUpdateRecord(CDistrictModel cDistrictModel);

	Object FnDeleteRecord(int district_id);

	Object FnShowAllRecords(Pageable pageable);

	Object FnShowAllActiveRecords(Pageable pageable);

	Object FnShowParticularRecordForUpdate(int district_id);

	Object FnShowParticularRecord(int company_id, int district_id);

	Object FnShowAllReportRecords(Pageable pageable);

}
