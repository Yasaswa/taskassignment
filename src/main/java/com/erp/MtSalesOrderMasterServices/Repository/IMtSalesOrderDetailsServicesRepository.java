package com.erp.MtSalesOrderMasterServices.Repository;

import com.erp.MtSalesOrderMasterServices.Model.CMtSalesOrderDetailsServicesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface IMtSalesOrderDetailsServicesRepository extends JpaRepository<CMtSalesOrderDetailsServicesModel, Integer> {

	@Modifying
	@Transactional
	@Query(value = "update mt_sales_order_details_services set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where sales_order_no = ?1 and sales_order_version = ?2 and company_id = ?3", nativeQuery = true)
	void FnUpdateSalesOrderDetailsServicesRecord(String sales_order_no, int sales_order_version, int company_id);


	@Modifying
	@Transactional
	@Query(value = "update mt_sales_order_details_services  set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where sales_order_no = ?1 and sales_order_version = ?2 and company_id = ?3", nativeQuery = true)
	void deleteSalesOrderDetailsServiceRecords(String sales_order_no, int sales_order_version, int company_id);



	@Modifying
	@Transactional
	@Query(value = "update mt_sales_order_details_services  set is_delete = 1, deleted_by = ?4 , deleted_on = CURRENT_TIMESTAMP() where sales_order_no = ?1 and sales_order_version = ?2 and company_id = ?3", nativeQuery = true)
	void deleteSalesOrderDetailsServiceRecords(String sales_order_no, int sales_order_version, int company_id,
			String deleted_by);

}
