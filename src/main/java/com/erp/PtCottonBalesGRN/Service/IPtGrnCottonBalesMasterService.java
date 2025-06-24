package com.erp.PtCottonBalesGRN.Service;


import org.json.JSONObject;
import java.util.Map;

public interface IPtGrnCottonBalesMasterService {

	Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject, boolean isApprove);
//
//	Object FnDeleteRecord(int grn_cotton_bales_master_transaction_id, int company_id);
	Map<String, Object> FnDeleteRecord(int grn_cotton_bales_master_transaction_id, String UserName);

	Map<String, Object> FnShowAllDetailsandMastermodelRecords(String goodsReceiptNo, int goodsReceiptVersion, String financialYear, int companyId);

    Map<String, Object> FnShowPurchaseOrderDetailsRecords(JSONObject purchaseOrderNo);

//
//	Page<CPtGrnCottonBalesMasterViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id);
//
//	Page<CPtGrnCottonBalesMasterRptModel> FnShowAllReportRecords(Pageable pageable, int company_id);
//
//	Map<String, Object> FnShowParticularRecordForUpdate(int grn_cotton_bales_master_transaction_id, int company_id);
//
//	Page<CPtGrnCottonBalesMasterViewModel> FnShowParticularRecord(int grn_cotton_bales_master_transaction_id, Pageable pageable, int company_id);

}
