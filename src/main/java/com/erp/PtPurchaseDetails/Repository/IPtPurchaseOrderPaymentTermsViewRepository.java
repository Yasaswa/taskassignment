package com.erp.PtPurchaseDetails.Repository;

import com.erp.PtPurchaseDetails.Model.CPtPurchaseOrderPaymentTermsViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface IPtPurchaseOrderPaymentTermsViewRepository extends JpaRepository<CPtPurchaseOrderPaymentTermsViewModel, Long> {

	@Query(value = "Select * FROM  ptv_purchase_order_payment_terms where is_delete =0  order by purchase_order_payment_terms_transaction_id Desc", nativeQuery = true)
	Page<CPtPurchaseOrderPaymentTermsViewModel> FnShowAllActiveRecords(Pageable pageable);

	@Query(value = "FROM CPtPurchaseOrderPaymentTermsViewModel viewM where viewM.is_delete =0 and viewM.purchase_order_payment_terms_transaction_id = ?1 and viewM.company_id = ?2 order by viewM.purchase_order_payment_terms_transaction_id Desc")
	Page<CPtPurchaseOrderPaymentTermsViewModel> FnShowParticularRecord(int purchase_order_payment_terms_transaction_id, Pageable pageable, int company_id);

	@Query(value = "FROM CPtPurchaseOrderPaymentTermsViewModel model where model.is_delete =0 and model.purchase_order_payment_terms_transaction_id = ?1 and model.company_id = ?2")
	CPtPurchaseOrderPaymentTermsViewModel FnShowParticularRecordForUpdate(int purchase_order_payment_terms_transaction_id, int company_id);


	@Query(value = "FROM CPtPurchaseOrderPaymentTermsViewModel model where model.is_delete =0 and model.company_id = ?1 and model.purchase_order_no IN ?2")
	Collection<? extends CPtPurchaseOrderPaymentTermsViewModel> FnShowpurchaseOrderPaymentTermsData(int company_id,
	                                                                                                List<String> distinctPoNos);


}
