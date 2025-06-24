package com.erp.MtSalesOrderMasterTrading.Repository;

import com.erp.MtSalesOrderMasterTrading.Model.CMtSalesOrderMasterTaxSummaryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface IMtSalesOrderTaxSummaryRepository extends JpaRepository<CMtSalesOrderMasterTaxSummaryModel, Integer> {

	@Modifying
	@Transactional
	@Query(value = "update mt_sales_order_tax_summary set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where  sales_order_no= ?1 and sales_order_version = ?2 and company_id = ?3", nativeQuery = true)
	void updateTaxSummary(String sales_order_no, Integer sales_order_version, int company_id);


	@Modifying
	@Transactional
	@Query(value = "update mt_sales_order_tax_summary set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where  sales_order_no= ?1 and sales_order_version = ?2 and company_id = ?3", nativeQuery = true)
	void updateStatus(String sales_order_no, Integer sales_order_version, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update mt_sales_order_tax_summary set is_delete = 1,deleted_by = ?4, deleted_on = CURRENT_TIMESTAMP() where  sales_order_no= ?1 and sales_order_version = ?2 and company_id = ?3", nativeQuery = true)
	void deleteSalesTaxSummary(String sales_order_no, int sales_order_version, int company_id,String
			deleted_by);


}
