package com.erp.Transporter.Service;

import com.erp.Transporter.Model.CTransporterViewModel;
import org.json.JSONObject;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface ITransporterService {

//	JSONObject FnAddUpdateRecord(CTransporterModel cTransporterModel);

	Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject);

	Object FnDeleteRecord(int transporter_id, String deleted_by);

	Object FnShowAllRecords(Pageable pageable);

	Object FnShowAllActiveRecords(Pageable pageable);

	Object FnShowParticularRecordForUpdate(int transporter_id);

	JSONObject FnShowParticularRecord(int company_id, int company_branch_id, int transporter_id);

	Object FnShowAllReportRecords(Pageable pageable);

	Map<String, Object> FnShowAllRecords(int transporter_id, int company_id);


}
