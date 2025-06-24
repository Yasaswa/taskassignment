package com.erp.MtSalesOrderMasterTrading.Repository;

import com.erp.MtSalesOrderMasterTrading.Model.CMtSalesOrderMasterTradingSummaryRptModel_Not_Used;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IMtSalesOrderMasterTradingSummaryRptRepository_Not_Used extends JpaRepository<CMtSalesOrderMasterTradingSummaryRptModel_Not_Used, String> {

//	@Query(value = "SELECT * FROM mtv_sales_order_master_trading_summary_rpt", nativeQuery = true)
//	Page<CMtSalesOrderMasterTradingSummaryRptModel> FnShowAllReportRecords(Pageable pageable, int company_id);


}
