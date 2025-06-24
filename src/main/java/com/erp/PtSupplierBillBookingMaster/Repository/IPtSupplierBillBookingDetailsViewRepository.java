package com.erp.PtSupplierBillBookingMaster.Repository;

import com.erp.PtSupplierBillBookingMaster.Model.CPtSupplierBillBookingDetailsViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface IPtSupplierBillBookingDetailsViewRepository extends JpaRepository<CPtSupplierBillBookingDetailsViewModel, Integer> {

	@Query(value = "SELECT * FROM ftv_supplier_bill_booking_details WHERE supplier_bill_booking_no = ?1 and supplier_bill_booking_version = ?2 and financial_year = ?3 and company_id= ?4", nativeQuery = true)
	List<Map<String, Object>> FnShowSupplierBillBookingDetailsRecords(String supplier_bill_booking_no,
	                                                                  int supplier_bill_booking_version, String financial_year, int company_id);

	@Query(value = "SELECT * FROM ftv_supplier_bill_booking_details_rpt", nativeQuery = true)
	Map<String, Object> FnShowAlldetailsReportRecords();


	@Query(value = "FROM CPtSupplierBillBookingDetailsViewModel model where model.company_id = ?1 AND model.supplier_id = ?2  AND model.supplier_bill_booking_type_id = ?3 AND model.supplier_bill_item_booking_status IN('F','R') ORDER BY model.payment_due_date ASC")
	List<CPtSupplierBillBookingDetailsViewModel> FnShowSupplierBillBookingDetailsRecords(int company_id, int supplier_id, int bill_booking_type_id);

}
