package com.erp.SmProductSr.Service;

import com.erp.SmProductSr.Model.CSmProductSrViewModel;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface ISmProductSrService {

//	Map<String, Object> FnAddUpdateRecord(CSmProductSrModel cSmProductSrModel);

	Object FnDeleteRecord(String product_sr_id, int company_id, String deleted_by);

//	Page<CSmProductSrViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id);

	Map<String, Object> FnShowParticularRecordForUpdate(int product_sr_id, int company_id);

	Page<CSmProductSrViewModel> FnShowParticularRecord(int product_sr_id, Pageable pageable, int company_id);

//	Map<String, Object> FnShowAllReportRecords(Pageable pageable, String reportType);

	Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject, MultipartFile qrCodeFile);

	Map<String, Object> FnShowAllSmProductSrMasterAndDetailsRecords(String product_sr_id, int company_id);

	Map<String, Object> FnShowAllReportRecords(Pageable pageable, String reportType);

	Map<String, Object> FnShowAllDefaultMappingsRecords(int company_id, String accordianSelectKey);

}
