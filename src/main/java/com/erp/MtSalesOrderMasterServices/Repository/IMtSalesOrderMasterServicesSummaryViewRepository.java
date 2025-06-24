package com.erp.MtSalesOrderMasterServices.Repository;

import com.erp.MtSalesOrderMasterServices.Model.CMtSalesOrderMasterServicesSummaryViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;

public interface IMtSalesOrderMasterServicesSummaryViewRepository extends JpaRepository<CMtSalesOrderMasterServicesSummaryViewModel, Integer> {

	@Query(value = "FROM CMtSalesOrderMasterServicesSummaryViewModel model where model.is_delete =0 and model.customer_order_no = ?1 and model.company_id = ?2 order by model.customer_order_no Desc")
	Page<CMtSalesOrderMasterServicesSummaryViewModel> FnShowSalesOrderDetailsTradingCustomerRecord(
			String customer_order_no, Pageable pageable, int company_id);

	@Query(value = "SELECT * FROM mtv_sales_order_master_services_summary  WHERE sales_order_no = ?1 and sales_order_version = ?2 and financial_year = ?3 and company_id= ?4", nativeQuery = true)
	Map<String, Object> FnShowSalesOrderMasterServiceRecord(String sales_order_no, int sales_order_version,  String financial_year, int company_id);

	@Query(value = "Select * From mtv_sales_order_master_services_summary_rpt", nativeQuery = true)
	Map<String, Object> FnShowAllSummaryReportRecords();

	@Query(value = "Select * From mtv_sales_order_details_services_rpt", nativeQuery = true)
	Map<String, Object> FnShowAlldetailsReportRecords();

}
