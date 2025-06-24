package com.erp.PtPurchaseDetails.Repository;

import com.erp.PtPurchaseDetails.Model.CPtPurchaseOrderTaxSummaryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface IPtPurchaseOrderTaxSummaryRepository extends JpaRepository<CPtPurchaseOrderTaxSummaryModel, Integer> {

	@Query(value = "FROM CPtPurchaseOrderTaxSummaryModel model where model.is_delete =0 and model.purchase_order_tax_summary_transaction_id = ?1 and model.company_id = ?2")
	CPtPurchaseOrderTaxSummaryModel FnShowParticularRecordForUpdate(int purchase_order_tax_summary_transaction_id,
	                                                                int company_id);

	@Modifying
	@Transactional
	@Query(value = "update pt_purchase_order_tax_summary set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where purchase_order_no = ?1 and purchase_order_version = ?2 and financial_year = ?3 and company_id = ?4", nativeQuery = true)
	void updatePurchaseTaxSummaryStatus(String purchase_order_no, Integer purchase_order_version, String financial_year,
	                                    int company_id);

	@Modifying
	@Transactional
	@Query(value = "update pt_purchase_order_tax_summary set is_delete = 1, deleted_on = CURRENT_TIMESTAMP(), deleted_by= ?4 where purchase_order_no = ?1 and purchase_order_version = ?2 and company_id = ?3", nativeQuery = true)
	void deletePurchaseTaxSummary(String purchase_order_no, int purchase_order_version,
	                              int company_id, String user_name);


	// Update Supplier Id from Bales GRN
	@Query(value = "FROM CPtPurchaseOrderTaxSummaryModel model where model.is_delete =0 and model.purchase_order_no = ?1 and model.company_id = ?2")
	CPtPurchaseOrderTaxSummaryModel FnFetchPO(String purchase_order_no, int company_id);
}
