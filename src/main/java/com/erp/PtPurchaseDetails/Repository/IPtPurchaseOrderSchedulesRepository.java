package com.erp.PtPurchaseDetails.Repository;

import com.erp.PtPurchaseDetails.Model.CPtPurchaseOrderSchedulesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface IPtPurchaseOrderSchedulesRepository extends JpaRepository<CPtPurchaseOrderSchedulesModel, Integer> {

	@Query(value = "FROM CPtPurchaseOrderSchedulesModel model where model.is_delete =0 and model.purchase_order_schedules_transaction_id = ?1 and model.company_id = ?2")
	CPtPurchaseOrderSchedulesModel FnShowParticularRecordForUpdate(int purchase_order_schedules_transaction_id,
	                                                               int company_id);

	@Modifying
	@Transactional
	@Query(value = "update pt_purchase_order_schedules set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where purchase_order_no = ?1 and purchase_order_version = ?2 and company_id = ?3", nativeQuery = true)
	void updatePurchaseOrderSchedulesStatus(String purchase_order_no, Integer purchase_order_version, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update pt_purchase_order_schedules set is_delete = 1, deleted_on = CURRENT_TIMESTAMP(), deleted_by= ?4 where purchase_order_no = ?1 and purchase_order_version = ?2 and company_id = ?3", nativeQuery = true)
	void deletePurchaseSchedules(String purchase_order_no, int purchase_order_version,
	                             int company_id, String user_name);

	@Modifying
	@Transactional
	@Query(value = "update pt_purchase_order_schedules set purchase_order_schedules_item_status = 'B' where purchase_order_no IN ?1 and is_delete = 0  and company_id = ?2", nativeQuery = true)
	void FnUpdatePurchaseOrderSchedulesRecord(List<String> purchaseOrderNos, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update pt_purchase_order_schedules set purchase_order_schedules_item_status = 'A' where purchase_order_no IN ?1 and is_delete = 0  and company_id = ?2", nativeQuery = true)
	void FnUpdatePurchaseOrderSchedulesStatus(List<String> purchaseOrderNos, int company_id);

}
