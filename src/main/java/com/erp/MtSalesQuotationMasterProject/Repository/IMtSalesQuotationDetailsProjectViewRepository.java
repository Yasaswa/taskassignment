package com.erp.MtSalesQuotationMasterProject.Repository;

import com.erp.MtSalesQuotationMasterProject.Model.CMtSalesQuotationProjectDetailsViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface IMtSalesQuotationDetailsProjectViewRepository extends JpaRepository<CMtSalesQuotationProjectDetailsViewModel, Integer> {

	@Query(value = "SELECT * FROM mtv_sales_quotation_project_details  WHERE quotation_no = ?1 and quotation_version = ?2 and financial_year = ?3 and company_id= ?4 and is_delete = 0", nativeQuery = true)
	List<Map<String, Object>> FnShowQuotationDetailsProjectRecord(String quotation_no, int quotation_version,
	                                                              String financial_year, int company_id);


	@Query(value = "SELECT * FROM mtv_sales_quotation_project_details  WHERE quotation_no = ?1 and quotation_version = ?2 and financial_year = ?3 and quotation_item_status = ?4 and company_id= ?5 and is_delete = 0", nativeQuery = true)
	List<CMtSalesQuotationProjectDetailsViewModel> FnGetQuotationProjectDetailsByItemStatus(String quotation_no, int quotation_version, String financial_year, String quotation_item_status, int company_id);

}
