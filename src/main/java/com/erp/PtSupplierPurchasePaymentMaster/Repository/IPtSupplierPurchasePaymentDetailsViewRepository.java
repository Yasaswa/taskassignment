package com.erp.PtSupplierPurchasePaymentMaster.Repository;

import com.erp.PtSupplierPurchasePaymentMaster.Model.CPtSupplierPurchasePaymentDetailsViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface IPtSupplierPurchasePaymentDetailsViewRepository
		extends JpaRepository<CPtSupplierPurchasePaymentDetailsViewModel, Integer> {

	@Query(value = "SELECT * FROM ftv_supplier_purchase_payment_details WHERE supplier_purchase_payment_no = ?1 and supplier_purchase_payment_version = ?2 and financial_year = ?3 and company_id= ?4", nativeQuery = true)
	List<Map<String, Object>> FnShowSupplierPurchasePaymentDetailsRecords(String supplier_purchase_payment_no,
	                                                                      int supplier_purchase_payment_version, String financial_year, int company_id);

}
