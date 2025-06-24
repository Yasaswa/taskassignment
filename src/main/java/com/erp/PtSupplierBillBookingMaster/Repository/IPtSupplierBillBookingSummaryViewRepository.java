package com.erp.PtSupplierBillBookingMaster.Repository;

import com.erp.PtSupplierBillBookingMaster.Model.CPtSupplierBillBookingSummaryViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;

public interface IPtSupplierBillBookingSummaryViewRepository extends JpaRepository<CPtSupplierBillBookingSummaryViewModel, Integer> {

	@Query(value = "SELECT * FROM ftv_supplier_bill_booking_summary WHERE supplier_bill_booking_no = ?1 and supplier_bill_booking_version = ?2 and financial_year = ?3 and company_id= ?4", nativeQuery = true)
	Map<String, Object> FnShowSupplierBillBookingMasterRecords(String supplier_bill_booking_no,
	                                                           int supplier_bill_booking_version, String financial_year, int company_id);

	@Query(value = "SELECT * FROM ftv_supplier_bill_booking_summary_rpt", nativeQuery = true)
	Map<String, Object> FnShowAllSummaryReportRecords();


}
