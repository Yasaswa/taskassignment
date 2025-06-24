package com.erp.PtSupplierPurchasePaymentMaster.Repository;

import com.erp.PtSupplierPurchasePaymentMaster.Model.CPtSupplierPurchasePaymentDetailsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface IPtSupplierPurchasePaymentDetailsRepository extends JpaRepository<CPtSupplierPurchasePaymentDetailsModel, Integer> {

	@Modifying
	@Transactional
	@Query(value = "update ft_supplier_purchase_payment_details set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where supplier_purchase_payment_no = ?1 and supplier_purchase_payment_version = ?2 and company_id = ?3", nativeQuery = true)
	void FnUpdateSupplierPurchasePaymentDetailsRecord(String supplier_purchase_payment_no, int supplier_purchase_payment_version, int company_id);


	@Modifying
	@Transactional
	@Query(value = "update ft_supplier_purchase_payment_details set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where supplier_purchase_payment_no = ?1 and supplier_purchase_payment_version = ?2 and company_id = ?3", nativeQuery = true)
	void deleteSupplierPurchasePaymentDetailsRecords(String supplier_purchase_payment_no,
	                                                 int supplier_purchase_payment_version, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update ft_supplier_purchase_payment_details set supplier_purchase_item_payment_status = ?1 WHERE supplier_purchase_payment_no =?2 AND is_delete = 0  AND company_id = ?3", nativeQuery = true)
	void FnUpdatePurchasePaymentDetailsStatus(String status, String supplierPurchasePaymentNo, int companyId);

}
