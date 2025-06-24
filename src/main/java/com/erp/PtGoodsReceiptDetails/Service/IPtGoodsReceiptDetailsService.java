package com.erp.PtGoodsReceiptDetails.Service;

import com.erp.PtGoodsReceiptDetails.Model.CPtGoodsReceiptDetailsViewModel;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;


public interface IPtGoodsReceiptDetailsService {

	Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject, boolean isApprove);

	Map<String, Object> FnDeleteRecord(int goods_receipt_master_transaction_id, String UserName);

	Page<CPtGoodsReceiptDetailsViewModel> FnShowParticularRecord(int goods_receipt_details_transaction_id,
	                                                             Pageable pageable, int company_id);

	Map<String, Object> FnShowAllDetailsandMastermodelRecords(String goods_receipt_no, int goods_receipt_version,
	                                                          String financial_year, int company_id);

	Map<String, Object> FnShowAllReportRecords(Pageable pageable, String reportType);

	Map<String, Object> FnShowPurchaseOrderDetailsTradingPurchaseRecord(JSONObject purchaseOrderNo, Pageable pageable,
	                                                                    int company_id);

	Map<String, Object> FnShowPurchaseOrderDetailsRecords(JSONObject purchaseOrderNo);


}
