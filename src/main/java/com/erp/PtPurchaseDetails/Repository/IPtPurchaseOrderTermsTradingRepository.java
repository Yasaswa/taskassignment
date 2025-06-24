package com.erp.PtPurchaseDetails.Repository;

import com.erp.PtPurchaseDetails.Model.CPtPurchaseOrderTermsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface IPtPurchaseOrderTermsTradingRepository extends JpaRepository<CPtPurchaseOrderTermsModel, Integer> {

	@Query(value = "FROM CPtPurchaseOrderTermsModel model where model.is_delete =0 and model.purchase_order_terms_transaction_id = ?1 and model.company_id = ?2")
	CPtPurchaseOrderTermsModel FnShowParticularRecordForUpdate(int purchase_order_terms_transaction_id, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update pt_purchase_order_terms set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where purchase_order_no = ?1 and purchase_order_version = ?2 and company_id = ?3", nativeQuery = true)
	void updatePurchaseOrderTermsStatus(String purchase_order_no, Integer purchase_order_version, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update pt_purchase_order_terms set is_delete = 1, deleted_on = CURRENT_TIMESTAMP(),deleted_by= ?4 where purchase_order_no = ?1 and purchase_order_version = ?2 and company_id = ?3", nativeQuery = true)
	void deletePurchaseOrderTermsTrading(String purchase_order_no, int purchase_order_version,
	                                     int company_id, String user_name);

	@Query(value = "from CPtPurchaseOrderTermsModel model where model.is_delete = 0 and model.purchase_order_no = ?1 and model.purchase_order_version = ?2 and model.company_id = ?3")
	List<CPtPurchaseOrderTermsModel> FnShowPurchaseOrderTermsTrading(String purchase_order_no,
	                                                                 Integer purchase_order_version, int company_id);

}
