package com.erp.PtSupplierPurchasePaymentMaster.Repository;

import com.erp.PtSupplierPurchasePaymentMaster.Model.CPtSupplierPurchasePaymentMasterSummaryViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;

public interface IPtSupplierPurchasePaymentMasterSumamryViewRepository extends JpaRepository<CPtSupplierPurchasePaymentMasterSummaryViewModel, Integer> {


	@Query(value = "SELECT * FROM ftv_supplier_purchase_payment_summary WHERE supplier_purchase_payment_no = ?1 and supplier_purchase_payment_version = ?2 and financial_year = ?3 and company_id= ?4", nativeQuery = true)
	Map<String, Object> FnShowSupplierPurchasePaymentMasterRecords(String supplier_purchase_payment_no,
	                                                               int supplier_purchase_payment_version, String financial_year, int company_id);


	@Query(value = "SELECT * FROM ftv_supplier_purchase_payment_summary_rpt", nativeQuery = true)
	Map<String, Object> FnShowAllSummaryReportRecords();


	@Query(value = "SELECT * FROM ftv_supplier_purchase_payment_details_rpt", nativeQuery = true)
	Map<String, Object> FnShowAlldetailsReportRecords();

}
