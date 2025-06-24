package com.erp.PtSupplierBillBookingMaster.Repository;

import com.erp.PtSupplierBillBookingMaster.Model.CPtSupplierBillBookingDetailsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface IPtSupplierBillBookingDetailsRepository extends JpaRepository<CPtSupplierBillBookingDetailsModel, Integer> {

	@Modifying
	@Transactional
	@Query(value = "update ft_supplier_bill_booking_details set is_delete = 1,deleted_by = ?4, deleted_on = CURRENT_TIMESTAMP() where supplier_bill_booking_no = ?1 and supplier_bill_booking_version = ?2 and company_id = ?3", nativeQuery = true)
	void deleteSupplierBillBookingDetailsRecords(String supplier_bill_booking_no, int supplier_bill_booking_version,
	                                             int company_id, String user_name);

	@Modifying
	@Transactional
	@Query(value = "update ft_supplier_bill_booking_details set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where supplier_bill_booking_no = ?1 and supplier_bill_booking_version = ?2 and company_id = ?3", nativeQuery = true)
	void FnUpdateBillBookingDetailsRecord(String supplier_bill_booking_no, int supplier_bill_booking_version, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update ft_supplier_bill_booking_details set supplier_bill_item_booking_status = ?1 WHERE supplier_bill_booking_no =?2 AND is_delete = 0  AND company_id = ?3", nativeQuery = true)
	void FnUpdateBillBookingDetailsStatus(String supplier_bill_booking_status, String supplier_bill_booking_no, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update ft_supplier_bill_booking_details set supplier_bill_item_booking_status = ?2 WHERE supplier_bill_booking_details_transaction_id = ?1 AND is_delete = 0 ", nativeQuery = true)
	void FnUpdateStatusByPurchasePaymentDetails(int billBookTransactionId, String billBookStatus);


	@Modifying
	@Transactional
	@Query(value = "update ft_supplier_bill_booking_master set supplier_bill_booking_status = ?1, modified_on = CURRENT_TIMESTAMP() where supplier_bill_booking_no = ?2 and is_delete = 0", nativeQuery = true)
	void FnUpdateBillMasterStatusByPayment(String billStatus, String billNumber);

//	List<Map<String, Object>> FnShowSupplierBillBookingDetailsRecords(String supplier_bill_booking_no,
//			int supplier_bill_booking_version, String financial_year, int company_id);

}
