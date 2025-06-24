package com.erp.City.Service;

import com.erp.City.Model.CCityModel;
import com.erp.City.Model.CCityViewModel;
import org.json.JSONObject;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface ICityService {


	JSONObject FnAddUpdateRecord(CCityModel cityModel);

	Object FnDeleteRecord(int city_id, String deleted_by);

	Object FnShowAllRecords(Pageable pageable);

	Map<String, Object> FnShowParticularRecordForUpdate(int city_id);

	Object FnShowAllReportRecords(Pageable pageable);

	Object FnShowAllActiveRecords(Pageable pageable);

	Object FnShowParticularRecord(int company_id, int city_id);

	List<CCityViewModel> FnShowFilterRecords(org.json.JSONObject jsonQuery);

	JSONObject FnMShowFilterRecords(JSONObject jsonQuery, int page, int size);


}
