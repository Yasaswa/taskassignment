package com.erp.Hsn_Sac.Service;

import com.erp.Hsn_Sac.Model.CHsn_SacModel;
import org.json.JSONObject;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface IHsn_SacService {

	Map<String, Object> FnAddUpdateRecord(CHsn_SacModel cHsn_SacModel);

	Object FnDeleteRecord(int hsn_sac_id, String deleted_by);

	Object FnShowAllRecords(Pageable pageable);

	Object FnShowAllActiveRecords(Pageable pageable);

	JSONObject FnShowParticularRecordForUpdate(int hsn_sac_id);

	JSONObject FnShowParticularRecord(int company_id, int hsn_sac_id);

	Object FnShowAllReportRecords(Pageable pageable);

}
