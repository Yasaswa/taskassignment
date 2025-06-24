package com.erp.PtPurchaseDetails.Repository;

import com.erp.PtPurchaseDetails.Model.CPtPurchaseOrderDetailsViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface IPtPurchaseOrderDetailsViewRepository extends JpaRepository<CPtPurchaseOrderDetailsViewModel, Long> {

	@Query(value = "FROM CPtPurchaseOrderDetailsViewModel model where model.is_delete = 0 and model.company_id = ?1 order by model.purchase_order_details_transaction_id Desc")
	Page<CPtPurchaseOrderDetailsViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id);

	@Query(value = "FROM CPtPurchaseOrderDetailsViewModel model where model.is_delete =0 and model.purchase_order_details_transaction_id = ?1 and model.company_id = ?2 order by model.purchase_order_details_transaction_id Desc")
	Page<CPtPurchaseOrderDetailsViewModel> FnShowParticularRecord(int purchase_order_details_transaction_id, Pageable pageable, int company_id);

	@Query(value = "FROM CPtPurchaseOrderDetailsViewModel model where model.is_delete = 0 and model.purchase_order_no = ?1 and model.company_id = ?2")
	List<CPtPurchaseOrderDetailsViewModel> FngetExistingPurchaseOrderDetails(String orderNumbersList, int company_id);

	@Query(value = "SELECT * FROM ptv_purchase_order_details  WHERE purchase_order_no = ?1 and purchase_order_version = ?2 and financial_year = ?3 and company_id= ?4 and is_delete = 0", nativeQuery = true)
	List<Map<String, Object>> FnShowPurchaseOrderDetailsRecords(String purchase_order_no, int purchase_order_version,
	                                                            String financial_year, int company_id);
	@Query(value = "SELECT * FROM ptv_purchase_order_details  WHERE indent_no IN ?1  and company_id= ?2 and is_delete = 0 and is_active = 1", nativeQuery = true)
	List<CPtPurchaseOrderDetailsViewModel> getAllExistingPODetails(List<String> distinctIndentNumbers, int companyId);

	//query to get material for pre-closed those partial grn done only till
	@Query(value = "SELECT * FROM ptv_purchase_order_details  WHERE purchase_order_no = ?1 and purchase_order_version = ?2 and financial_year = ?3 and company_id= ?4 and is_delete = 0 and purchase_order_item_status = 'A' and grn_item_status IN ('I','P')", nativeQuery = true)
	List<Map<String, Object>> FnShowPurchaseOrderDetailsForPreClosed(String purchaseOrderNo, int purchaseOrderVersion, String financialYear, int companyId);
}
