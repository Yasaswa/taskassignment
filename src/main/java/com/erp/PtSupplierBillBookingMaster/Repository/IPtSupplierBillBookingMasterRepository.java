package com.erp.PtSupplierBillBookingMaster.Repository;

import com.erp.PtSupplierBillBookingMaster.Model.CPtSupplierBillBookingMasterModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;


public interface IPtSupplierBillBookingMasterRepository extends JpaRepository<CPtSupplierBillBookingMasterModel, Integer> {


	@Query(value = "SELECT * FROM ft_supplier_bill_booking_master  WHERE supplier_bill_booking_no = ?1 and supplier_bill_booking_version = ?2 and financial_year = ?3 and company_id= ?4", nativeQuery = true)
	CPtSupplierBillBookingMasterModel getExistingRecord(String supplier_bill_booking_no,
	                                                    int supplier_bill_booking_version, String financial_year, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update ft_supplier_bill_booking_master set is_delete = 1,deleted_by = ?4, deleted_on = CURRENT_TIMESTAMP() where supplier_bill_booking_no = ?1 and supplier_bill_booking_version = ?2 and company_id = ?3", nativeQuery = true)
	void deleteSupplierBillBookingMasterRecords(String supplier_bill_booking_no, int supplier_bill_booking_version,
	                                            int company_id, String user_name);

	@Modifying
	@Transactional
	@Query(value = "update ft_supplier_bill_booking_master set supplier_bill_booking_status = ?1 WHERE supplier_bill_booking_no =?2 AND is_delete = 0  AND company_id = ?3", nativeQuery = true)
	void FnUpdateBillBookingMasterStatus(String supplier_bill_booking_status, String supplier_bill_booking_no, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update ft_supplier_bill_booking_master set bill_booking_mail_sent_status = ?1 where company_id = ?2 and purchase_order_master_transaction_id =?3", nativeQuery = true)
	void updateMailStatus(String string, int company_id, int supplier_bill_booking_master_transaction_id);


//	@Modifying
//	@Transactional
//	@Query(value = "update ft_supplier_bill_booking_master set purchase_order_mail_sent_status = ?1 where company_id = ?2 and purchase_order_master_transaction_id =?3", nativeQuery = true)
//	void updateMailStatus(String string, int company_id, int supplier_bill_booking_master_transaction_id);


}
