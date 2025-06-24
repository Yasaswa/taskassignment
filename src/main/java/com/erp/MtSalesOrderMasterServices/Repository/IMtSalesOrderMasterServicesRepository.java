package com.erp.MtSalesOrderMasterServices.Repository;

import com.erp.MtSalesOrderMasterServices.Model.CMtSalesOrderMasterServicesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface IMtSalesOrderMasterServicesRepository extends JpaRepository<CMtSalesOrderMasterServicesModel, Integer> {

	@Query(value = "FROM CMtSalesOrderMasterServicesModel model where model.is_delete =0 and model.sales_order_master_transaction_id = ?1 and model.company_id = ?2")
	CMtSalesOrderMasterServicesModel FnShowParticularRecordForUpdate(int sales_order_master_transaction_id,
	                                                                 int company_id);

	@Query(nativeQuery = true, value = "SELECT * FROM mt_sales_order_master_services WHERE sales_order_life = ?1")
	CMtSalesOrderMasterServicesModel checkIfNameExist(String getsales_order_life);

	@Modifying
	@Transactional
	@Query(value = "update mt_sales_order_master_services set sales_order_mail_sent_status = ?1 where company_id = ?2 and sales_order_master_transaction_id =?3", nativeQuery = true)
	void updateMailStatus(String sales_order_mail_sent_status, int company_id, int sales_order_master_transaction_id);

	@Modifying
	@Transactional
	@Query(value = "update mt_sales_order_master_services set sales_order_acceptance_status = ?1 where sales_order_master_transaction_id =?2 and company_id = ?3", nativeQuery = true)
	void FnAcceptCustomerOrder(String sales_order_acceptance_status, int sales_order_master_transaction_id, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update mt_sales_order_master_services  set is_delete = 1, deleted_by = ?4 ,deleted_on = CURRENT_TIMESTAMP() where sales_order_no = ?1 and sales_order_version = ?2 and company_id = ?3", nativeQuery = true)
	void deleteSalesOrderServiceRecords(String sales_order_no, int sales_order_version, int company_id,
			String deleted_by);


}
