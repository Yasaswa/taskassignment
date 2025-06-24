package com.erp.MtSalesQuotationMasterServices.Repository;

import com.erp.MtSalesQuotationMasterServices.Model.CMtSalesQuotationDetailsServicesViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface IMtSalesQuotationDetailsServicesViewRepository extends JpaRepository<CMtSalesQuotationDetailsServicesViewModel, Integer> {

	@Query(value = "SELECT * FROM mtv_sales_quotation_details_services WHERE is_delete = 0 AND  quotation_no = ?1 and quotation_version = ?2 and financial_year = ?3 and company_id= ?4", nativeQuery = true)
	List<Map<String, Object>> FnShowQuotationDetailsServicesRecords(String quotation_no, int quotation_version,
	                                                                String financial_year, int company_id);


	@Query(value = "FROM CMtSalesQuotationDetailsServicesViewModel model WHERE model.is_delete=0 AND model.company_id = ?1 AND model.quotation_no IN ?2")
	List<CMtSalesQuotationDetailsServicesViewModel> FnShowQuotationDetailsRecord(int company_id,
	                                                                             List<String> quotationNoList);

	@Query(value = "SELECT * FROM mtv_sales_quotation_details_services  WHERE quotation_no = ?1 and quotation_version = ?2 and financial_year = ?3 and quotation_item_status = ?4 and company_id= ?5 and is_delete = 0", nativeQuery = true)
	List<CMtSalesQuotationDetailsServicesViewModel> FnGetQuotationDetailsByItemStatus(String quotation_no,
	                                                                                  int quotation_version, String financial_year, String quotation_item_status, int company_id);

}
