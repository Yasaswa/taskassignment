package com.erp.MtSalesQuotationMasterServices.Repository;

import com.erp.MtSalesQuotationMasterServices.Model.CMtSalesQuotationMasterServicesViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface IMtSalesQuotationMasterServicesViewRepository extends JpaRepository<CMtSalesQuotationMasterServicesViewModel, Integer> {

	@Query(value = "FROM CMtSalesQuotationMasterServicesViewModel model WHERE model.is_delete = 0 AND model.quotation_no = ?1 and model.quotation_version = ?2 and model.financial_year = ?3 and model.company_id= ?4")
	CMtSalesQuotationMasterServicesViewModel FnShowsQuotationMasterServicesDetailsRecords(String quotation_no, int quotation_version,
	                                                                                      String financial_year, int company_id);

	@Query(value = "SELECT * FROM mtv_sales_quotation_services_summary_rpt", nativeQuery = true)
	Map<String, Object> FnShowAllSummaryReportRecords();

	@Query(value = "SELECT * FROM mtv_sales_quotation_details_services_rpt", nativeQuery = true)
	Map<String, Object> FnShowAlldetailsReportRecords();


	@Query(value = "SELECT model.quotation_no FROM CMtSalesQuotationMasterServicesViewModel model WHERE model.is_delete = 0 AND model.company_id = ?1 AND model.quotation_status = 'A'")
	List<String> FnGetQuotationNoDetails(int company_id);

}
