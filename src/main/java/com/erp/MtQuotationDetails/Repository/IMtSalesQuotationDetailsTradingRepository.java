package com.erp.MtQuotationDetails.Repository;

import com.erp.MtQuotationDetails.Model.CMtSalesQuotationDetailsTradingModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

public interface IMtSalesQuotationDetailsTradingRepository extends JpaRepository<CMtSalesQuotationDetailsTradingModel, Integer> {

	@Modifying
	@Transactional
	@Query(value = "update mt_sales_quotation_details_trading set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where quotation_no = ?1 and quotation_version = ?2 and company_id = ?3", nativeQuery = true)
	void updateStatus(String quotation_no, Integer quotation_version, int company_id);


	@Modifying
	@Transactional
	@Query(value = "update mt_sales_quotation_details_trading set is_delete = 1, deleted_on = CURRENT_TIMESTAMP(), deleted_by = ?4 where quotation_no = ?1 and quotation_version = ?2 and company_id = ?3", nativeQuery = true)
	void deleteQuotationDetails(String quotation_no, int quotation_version, int company_id, String deleted_by);

	@Query(value = "SELECT * FROM mtv_sales_quotation_trading_details  WHERE quotation_no = ?1 and quotation_version = ?2 and financial_year = ?3 and company_id= ?4 and is_delete = 0", nativeQuery = true)
	List<Map<String, Object>> FnShowQuotationDetailsTradingRecord(String quotation_no, int quotation_version,
	                                                              String financial_year, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update mt_sales_quotation_details_trading set quotation_item_status = 'X' where is_delete = 0 and quotation_no = ?1 and company_id = ?2", nativeQuery = true)
	void updateSalesQuotationDetails(String quotation_no, int company_id);


	@Modifying
	@Transactional
	@Query(value = "update mt_sales_quotation_details_trading set quotation_item_status = 'S' where is_delete = 0 and quotation_no = ?1 and company_id = ?2", nativeQuery = true)
	void updateSalesQuotationDetailsStatus(String quotationNo, int company_id);


}
