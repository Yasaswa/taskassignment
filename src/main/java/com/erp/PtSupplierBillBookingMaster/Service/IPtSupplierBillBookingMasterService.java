package com.erp.PtSupplierBillBookingMaster.Service;

import org.json.JSONObject;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface IPtSupplierBillBookingMasterService {

	Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject, String isApproveOrCancel);

	Map<String, Object> FnDeleteRecord(String supplier_bill_booking_no, int supplier_bill_booking_version,
	                                   int company_id, String user_name);

	Map<String, Object> FnShowAllReportRecords(Pageable pageable, String reportType);

	Map<String, Object> FnShowAllDetailsandMastermodelRecords(String supplier_bill_booking_no,
	                                                          int supplier_bill_booking_version, String financial_year, int company_id);

	Map<String, Object> FnShowGoodReceiptSummaryRecords(JSONObject goodReceiptNo, Pageable pageable);

	//
	Map<String, Object> FnSendEmail(int company_id, int supplier_bill_booking_master_transaction_id,
	                                JSONObject emailData);

	Map<String, Object> FnShowBillBookingDetailsRecords(int supplier_id, int company_id, int bill_book_type_id);

	Map<String, Object> FnShowSalesInvoiceDetailsRecords(int customer_id, int company_id);


}
