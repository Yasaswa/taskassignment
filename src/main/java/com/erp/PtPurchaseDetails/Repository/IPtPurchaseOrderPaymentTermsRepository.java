package com.erp.PtPurchaseDetails.Repository;

import com.erp.PtPurchaseDetails.Model.CPtPurchaseOrderPaymentTermsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface IPtPurchaseOrderPaymentTermsRepository
		extends JpaRepository<CPtPurchaseOrderPaymentTermsModel, Integer> {

	@Modifying
	@Transactional
	@Query(value = "update pt_purchase_order_payment_terms set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where purchase_order_no = ?1 and purchase_order_version = ?2  and company_id = ?3", nativeQuery = true)
	void updatePurchaseOrderPaymentStatus(String purchase_order_no, Integer purchase_order_version, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update pt_purchase_order_payment_terms set is_delete = 1, deleted_on = CURRENT_TIMESTAMP(),deleted_by= ?4 where purchase_order_no = ?1 and purchase_order_version = ?2 and company_id = ?3", nativeQuery = true)
	void deletePurchaseOrderPaymentTerms(String purchase_order_no,
	                                     int purchase_order_version, int company_id, String user_name);

	@Query(value = "from CPtPurchaseOrderPaymentTermsModel model where model.is_delete = 0 and model.purchase_order_no = ?1 and model.purchase_order_version = ?2 and model.company_id = ?3")
	List<CPtPurchaseOrderPaymentTermsModel> FnShowPurchaseOrderPaymentTerms(String purchase_order_no,
	                                                                        Integer purchase_order_version, int company_id);

}
