package com.erp.PtSupplierBillBookingMaster.Repository;

import com.erp.PtSupplierBillBookingMaster.Model.CPtPurchaseBillTaxSummaryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface IFtPurchaseBillTaxSummaryRepository extends JpaRepository<CPtPurchaseBillTaxSummaryModel, Integer> {

	@Modifying
	@Transactional
	@Query(value = "update ft_purchase_bill_tax_summary set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where supplier_bill_booking_no = ?1 and supplier_bill_booking_version = ?2 and company_id = ?3", nativeQuery = true)
	void FnUpdatePurchaseBillTaxSummaryRecord(String supplier_bill_booking_no, int supplier_bill_booking_version, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update ft_purchase_bill_tax_summary set is_delete = 1,deleted_by = ?4, deleted_on = CURRENT_TIMESTAMP() where supplier_bill_booking_no = ?1 and supplier_bill_booking_version = ?2 and company_id = ?3", nativeQuery = true)
	void deletePurchaseBillTaxSummaryRecords(String supplier_bill_booking_no, int supplier_bill_booking_version,
	                                         int company_id, String user_name);

}
