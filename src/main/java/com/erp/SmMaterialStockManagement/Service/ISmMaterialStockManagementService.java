package com.erp.SmMaterialStockManagement.Service;

import java.util.Map;

public interface ISmMaterialStockManagementService {


    Map<String, Object> FnGRNAddUpdateRecord(Map<String, Object> grnStockdata);

//	Object FnDeleteRecord(int stock_transaction_id, int company_id);
//
//	Page<CSmProductMaterialStockNewViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id);
//
//	Page<CSmProductMaterialStockNewRptModel> FnShowAllReportRecords(Pageable pageable, int company_id);
//
//	Map<String, Object> FnShowParticularRecordForUpdate(int stock_transaction_id, int company_id);
//
//	Page<CSmProductMaterialStockNewViewModel> FnShowParticularRecord(int stock_transaction_id, Pageable pageable, int company_id);

}
