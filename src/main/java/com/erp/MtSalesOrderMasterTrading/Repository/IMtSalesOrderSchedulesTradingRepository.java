package com.erp.MtSalesOrderMasterTrading.Repository;

import com.erp.MtSalesOrderMasterTrading.Model.CMtSalesOrderSchedulesTradingModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;


public interface IMtSalesOrderSchedulesTradingRepository extends JpaRepository<CMtSalesOrderSchedulesTradingModel, Integer> {

	@Query(value = "FROM CMtSalesOrderSchedulesTradingModel model where model.is_delete =0 and model.sales_order_schedules_transaction_id = ?1 and model.company_id = ?2")
	CMtSalesOrderSchedulesTradingModel FnShowParticularRecordForUpdate(int sales_order_schedules_transaction_id, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update mt_sales_order_schedules_trading set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where sales_order_no= ?1 and sales_order_version = ?2 and company_id = ?3", nativeQuery = true)
	void updatePaymentSchedules(String sales_order_no, Integer sales_order_version, int company_id);


	@Modifying
	@Transactional
	@Query(value = "update mt_sales_order_schedules_trading set is_delete = 1,deleted_by = ?4, deleted_on = CURRENT_TIMESTAMP() where sales_order_no = ?1 and sales_order_version = ?2 and company_id = ?3", nativeQuery = true)
	void deleteSalesSchedules(String sales_order_no, int sales_order_version, int company_id,String deleted_by);

	@Modifying
	@Transactional
	@Query(value = "update mt_sales_order_schedules_trading set sales_order_schedules_trading_item_status = 'X',modified_by = ?4, modified_on = CURRENT_TIMESTAMP() where sales_order_no = ?1 and sales_order_version = ?2 and company_id = ?3", nativeQuery = true)
	void cancelSalesSchedules(String sales_order_no, int sales_order_version, int company_id, String modified_by);

}
