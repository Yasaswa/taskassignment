package com.erp.MtSalesOrderMasterServices.Repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.erp.MtSalesOrderMasterServices.Model.CMtSalesOrderPaymentTermsServiceModel;

public interface IMtSalesOrderPaymentTermsServicesRepository extends JpaRepository<CMtSalesOrderPaymentTermsServiceModel, Integer>{

	@Modifying
	@Transactional
	@Query(value = "update mt_sales_order_payment_terms_services set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where sales_order_no = ?1 and sales_order_version = ?2 and company_id = ?3", nativeQuery = true)
	void updatePaymentTermsStatus(String sales_order_no, int sales_order_version, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update mt_sales_order_payment_terms_services set is_delete = 1, deleted_by = ?4, deleted_on = CURRENT_TIMESTAMP() where sales_order_no = ?1 and sales_order_version = ?2 and company_id = ?3", nativeQuery = true)
	void deleteSalesOrderPaymentTermsServiceRecords(String sales_order_no, int sales_order_version, int company_id,
			String deleted_by);

}
