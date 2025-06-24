package com.erp.FinishGoods.SmProductFg.Service;

import org.json.JSONObject;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface ISmProductFgService {

//	JSONObject FnAddUpdateRecord(CSmProductFgModel cSmProductFgModel);

	Object FnDeleteRecord(String product_fg_id, String company_id);

	Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject, MultipartFile qrCodeFile);

	Map<String, Object> FnShowAllProductFgDetailsAndMasterRecords(String product_fg_id, int company_id);

	Map<String, Object> FnShowAllReportRecords(Pageable pageable, String reportType);

	Map<String, Object> FnShowAllRecords(int company_id, String updatAllRecordsKey);

}
