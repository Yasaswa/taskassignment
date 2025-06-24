package com.erp.PtPurchaseDetails.Repository;

import com.erp.PtPurchaseDetails.Model.CPtPurchaseOrderTaxSummaryViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IPtPurchaseOrderTaxSummaryViewRepository
		extends JpaRepository<CPtPurchaseOrderTaxSummaryViewModel, Long> {

	@Query(value = "FROM CPtPurchaseOrderTaxSummaryViewModel model where model.is_delete = 0 and model.company_id = ?1 order by model.purchase_order_tax_summary_transaction_id Desc")
	Page<CPtPurchaseOrderTaxSummaryViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id);

	// @Query(value = "FROM CPtPurchaseOrderTaxSummaryViewModel viewM where
	// viewM.is_delete =0 and viewM.purchase_order_tax_summary_transaction_id = ?1
	// and viewM.company_id = ?2 order by
	// viewM.purchase_order_tax_summary_transaction_id Desc")

	@Query(value = "FROM CPtPurchaseOrderTaxSummaryViewModel model where model.is_delete =0 and model.purchase_order_tax_summary_transaction_id = ?1 and model.company_id = ?2")
	Page<CPtPurchaseOrderTaxSummaryViewModel> FnShowParticularRecord(int purchase_order_tax_summary_transaction_id,
	                                                                 Pageable pageable, int company_id);

	@Query(value = "from CPtPurchaseOrderTaxSummaryViewModel model where model.is_delete = 0 and model.purchase_order_no = ?1 and model.purchase_order_version = ?2 and model.company_id = ?3 and model.is_delete = 0")
	List<CPtPurchaseOrderTaxSummaryViewModel> FnShowPurchaseOrderTaxSummary(String purchase_order_no,
	                                                                        int purchase_order_version, int company_id);

	@Query(value = "from CPtPurchaseOrderTaxSummaryViewModel model where model.is_delete = 0 and model.purchase_order_no = ?1 and model.purchase_order_version = ?2 and model.company_id = ?3")
	List<CPtPurchaseOrderTaxSummaryViewModel> FnShowPurchaseOrderTaxSummaryView(String purchase_order_no,
	                                                                            Integer purchase_order_version, int company_id);

}
