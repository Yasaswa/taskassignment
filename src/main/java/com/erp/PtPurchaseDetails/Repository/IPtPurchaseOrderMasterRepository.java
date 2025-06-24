package com.erp.PtPurchaseDetails.Repository;

import com.erp.PtPurchaseDetails.Model.CPtPurchaseOrderMasterModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;


public interface IPtPurchaseOrderMasterRepository extends JpaRepository<CPtPurchaseOrderMasterModel, Integer> {

	@Query(value = "FROM CPtPurchaseOrderMasterModel model where model.is_delete =0 and model.purchase_order_master_transaction_id = ?1 and model.company_id = ?2")
	CPtPurchaseOrderMasterModel FnShowParticularRecordForUpdate(int purchase_order_master_transaction_id, int company_id);


	@Modifying
	@Transactional
	@Query(value = "update pt_purchase_order_master set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where purchase_order_no = ?1 and purchase_order_version = ?2 and company_id = ?3", nativeQuery = true)
	void updateStatus(String purchase_order_no, Integer purchase_order_version, String financial_year, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update pt_purchase_order_master set is_delete = 1, deleted_on = CURRENT_TIMESTAMP(), deleted_by = ?4 where purchase_order_no = ?1 and purchase_order_version = ?2 and company_id = ?3", nativeQuery = true)
	void deletePurchase(String purchase_order_no, int purchase_order_version, int company_id, String user_name);

	@Query(value = "Select * from pt_purchase_order_master where is_delete = 0 and purchase_order_no = ?1 and company_id = ?2", nativeQuery = true)
	CPtPurchaseOrderMasterModel getLastPurchaseOrderVersion(String purchase_order_no, String company_id);

	@Query(value = "SELECT * FROM ptv_purchase_order_master_summary  WHERE purchase_order_no = ?1 and purchase_order_version = ?2 and financial_year = ?3 and company_id= ?4 and is_delete = 0", nativeQuery = true)
	Map<String, Object> FnShowPurchaseOrderMasterRecord(String purchase_order_no, int purchase_order_version,
	                                                    String financial_year, int company_id);


	@Modifying
	@Transactional
	@Query(value = "update pt_purchase_order_master set purchase_order_mail_sent_status = ?1 where company_id = ?2 and purchase_order_master_transaction_id =?3", nativeQuery = true)
	void updateMailStatus(String purchase_order_mail_sent_status, int company_id, int purchase_order_master_transaction_id);

	@Modifying
	@Transactional
	@Query(value = "update pt_purchase_order_master set purchase_order_acceptance_status = ?1 where purchase_order_master_transaction_id =?2 and company_id = ?3", nativeQuery = true)
	void FnAcceptPurchaseOrder(String purchase_order_acceptance_status, int purchase_order_master_transaction_id,
	                           int company_id);

	@Modifying
	@Transactional
	@Query(value = "update pt_purchase_order_master set purchase_order_status = 'B' where purchase_order_no IN ?1 and is_delete = 0 and company_id = ?2", nativeQuery = true)
	void FnUpdatePurchaseOrderMasterRecord(List<String> purchaseOrderNos, int company_id);


	@Modifying
	@Transactional
	@Query(value = "update pt_purchase_order_master set purchase_order_status = 'A' where purchase_order_no IN ?1 and is_delete = 0 and company_id = ?2", nativeQuery = true)
	void FnUpdatePurchaseOrderStatus(List<String> purchaseOrderNos, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update pt_purchase_order_master set purchase_order_status = 'Z' where purchase_order_master_transaction_id = ?1 and is_delete = 0", nativeQuery = true)
	void updatePreClosedMasterStatus(Integer poMasterTransactionId);


	// Update Supplier Id from Bales GRN
	@Query(value = "FROM CPtPurchaseOrderMasterModel model where model.is_delete =0 and model.purchase_order_no = ?1 and model.company_id = ?2")
	CPtPurchaseOrderMasterModel FnFetchPO(String purchase_order_no, int company_id);

}
