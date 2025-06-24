package com.erp.Destinations.Service;

import com.erp.Destinations.Model.CDestinationsModel;
import org.json.JSONObject;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface IDestinationsService {

	Map<String, Object> FnAddUpdateRecord(CDestinationsModel cDestinationsModel);

	Object FnDeleteRecord(int destination_id, String deleted_by);

	Object FnShowAllRecords(Pageable pageable);

	Object FnShowAllActiveRecords(Pageable pageable);

	Map<String, Object> FnShowParticularRecordForUpdate(int destination_id);

	Object FnShowAllReportRecords(Pageable pageable);

	JSONObject FnShowParticularRecord(int company_id, int destination_id);

}
