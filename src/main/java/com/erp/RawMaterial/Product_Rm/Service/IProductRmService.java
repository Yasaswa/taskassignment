package com.erp.RawMaterial.Product_Rm.Service;

import org.json.JSONObject;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface IProductRmService {

	Map<String, Object> FnDeleteRecord(String product_rm_id, String deleted_by);

//	Object FnShowAllRecords(Pageable pageable);

	Object FnShowAllActiveRecords(Pageable pageable);

	JSONObject FnShowParticularRecord(int company_id, int product_rm_id);

	Map<String, Object> FnShowAllReportSummaryRecords(Pageable pageable, String reportType);

	Object FnShowAllProductRmmaterialSummaryViewRecords(Pageable pageable);

	Object FnShowAllProductRmListViewRecords(Pageable pageable);

	Object FnShowAllProductRmDetailsViewRecords(Pageable pageable);

	JSONObject FnShowParticularRecordForUpdate(int company_id, int company_branch_id, int product_rm_id);

	Map<String, Object> FnShowAllProductRmSummaryDetailsAndMasterRecords(String product_rm_id, int company_id);

	Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject, MultipartFile qrCodeFile);

	Map<String, Object> FnShowAllRecords(int company_id, String accordianSelectKey);


}
