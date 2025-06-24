package com.erp.PtSupplierPurchasePaymentMaster.Repository;

import com.erp.PtSupplierPurchasePaymentMaster.Model.CPtSupplierPurchasePaymentMasterModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;


public interface IPtSupplierPurchasePaymentMasterRepository extends JpaRepository<CPtSupplierPurchasePaymentMasterModel, Integer> {


	@Query(value = "SELECT * FROM ft_supplier_purchase_payment_master WHERE supplier_purchase_payment_no = ?1 and supplier_purchase_payment_version = ?2 and company_id= ?3 and financial_year = ?4 and is_delete = 0", nativeQuery = true)
	CPtSupplierPurchasePaymentMasterModel getExistingRecord(String supplier_purchase_payment_no,
	                                                        int supplier_purchase_payment_version, int company_id, String financial_year);

	@Modifying
	@Transactional
	@Query(value = "update ft_supplier_purchase_payment_master set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where supplier_purchase_payment_no = ?1 and supplier_purchase_payment_version = ?2 and company_id = ?3", nativeQuery = true)
	void deleteSupplierPurchasePaymentMasterRecords(String supplier_purchase_payment_no,
	                                                int supplier_purchase_payment_version, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update ft_supplier_purchase_payment_master set supplier_purchase_payment_status = ?1 WHERE supplier_purchase_payment_no =?2 AND is_delete = 0  AND company_id = ?3", nativeQuery = true)
	void FnUpdatePurchaePaymentMasterStatus(String status, String supplierPurchasePaymentNo, int companyId);


	@Modifying
	@Transactional
	@Query(value = "update ft_supplier_purchase_payment_master set purchase_payment_mail_sent_status = ?1 where company_id = ?2 and supplier_purchase_payment_master_transaction_id =?3", nativeQuery = true)
	void updateMailStatus(String mailSentStatus, int company_id, int supplierPurchasePaymentMasterTransactionId);
}
