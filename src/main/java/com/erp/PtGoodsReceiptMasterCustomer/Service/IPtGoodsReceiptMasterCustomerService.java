package com.erp.PtGoodsReceiptMasterCustomer.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONObject;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface IPtGoodsReceiptMasterCustomerService {

	Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject) throws JsonProcessingException;

	Map<String, Object> FnDeleteRecord(String customer_goods_receipt_no, int customer_id, int company_id, String userName);

	Map<String, Object> FnShowAllReportRecords(Pageable pageable, String reportType);

	Map<String, Object> FnShowAllDetailsandMastermodelRecords(String customer_goods_receipt_no,
	                                                          int customer_goods_receipt_version, String financial_year, int company_id);

	Map<String, Object> FnUpdateCustomerStockDetailsRecord(JSONObject jsonObject);


}
