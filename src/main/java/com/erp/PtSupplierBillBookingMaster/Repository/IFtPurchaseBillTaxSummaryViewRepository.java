package com.erp.PtSupplierBillBookingMaster.Repository;

import com.erp.PtSupplierBillBookingMaster.Model.CFtPurchaseBillTaxSummaryViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IFtPurchaseBillTaxSummaryViewRepository extends JpaRepository<CFtPurchaseBillTaxSummaryViewModel, Integer> {

	@Query(value = "FROM CFtPurchaseBillTaxSummaryViewModel model where model.is_delete = 0 AND model.supplier_bill_booking_no = ?1 AND model.supplier_bill_booking_version = ?2  AND model.financial_year = ?3 AND company_id = ?4")
	List<CFtPurchaseBillTaxSummaryViewModel> FnShowPurchaseBillTaxSummaryRecords(String supplier_bill_booking_no,
	                                                                             int supplier_bill_booking_version, String financial_year, int company_id);

}
