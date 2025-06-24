package com.erp.PtPurchaseDetails.Repository;

import com.erp.PtPurchaseDetails.Model.CPtPurchaseOrderDetailsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface IPtPurchaseOrderDetailsRepository extends JpaRepository<CPtPurchaseOrderDetailsModel, Integer> {

	@Query(value = "FROM CPtPurchaseOrderDetailsModel model where model.is_delete =0 and model.purchase_order_details_transaction_id = ?1 and model.company_id = ?2")
	CPtPurchaseOrderDetailsModel FnShowParticularRecordForUpdate(int purchase_order_details_transaction_id,
	                                                             int company_id);

	@Modifying
	@Transactional
	@Query(value = "update pt_purchase_order_details set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where purchase_order_no = ?1 and purchase_order_version = ?2 and financial_year = ?3 and company_id = ?4", nativeQuery = true)
	void updateStatus(String purchase_order_no, Integer purchase_order_version, String financial_year, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update pt_purchase_order_details set is_delete = 1, deleted_on = CURRENT_TIMESTAMP(), deleted_by= ?4 where purchase_order_no = ?1 and purchase_order_version = ?2 and company_id = ?3", nativeQuery = true)
	void deletePurchaseDetails(String purchase_order_no, int purchase_order_version,
	                           int company_id, String user_name);

	@Query(value = "SELECT * FROM ptv_purchase_order_details  WHERE purchase_order_no = ?1 and purchase_order_version = ?2 and financial_year = ?3 and company_id= ?4 and is_delete = 0", nativeQuery = true)
	List<Map<String, Object>> FnShowPurchaseOrderDetailsRecords(String purchase_order_no, int purchase_order_version,
	                                                            String financial_year, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update pt_purchase_order_details set purchase_order_item_status = 'B' where purchase_order_no IN ?1 and is_delete = 0 and company_id = ?2", nativeQuery = true)
	void FnUpdatePurchaseOrderDetailsRecord(List<String> purchaseOrderNos, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update pt_purchase_order_details set purchase_order_item_status = 'A' where purchase_order_no IN ?1 and is_delete = 0 and company_id = ?2", nativeQuery = true)
	void FnUpdatePurchaseOrderDetailsStatus(List<String> purchaseOrderNos, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update st_indent_details set po_item_status = ?1 where indent_no IN ?2 and product_material_id = ?3 and is_delete = 0 and company_id = ?4", nativeQuery = true)
	void FnUpdateIndentDetailsStatus(String poStatus, List<String> distinctIndentNumbers, String productMaterialId, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update st_indent_master set po_status = ?1 where indent_no IN ?2 and is_delete = 0 and company_id = ?3", nativeQuery = true)
	void FnUpdateIndentMasterStatus(String indentStatus, List<String> distinctIndentNumbers, int companyId);

	@Query(value = "SELECT SUM(closing_balance_quantity) as closing_balance_quantity ,SUM(closing_balance_weight) as closing_balance_weight, product_rm_id FROM sm_product_rm_stock_summary WHERE product_rm_id IN ?1 and company_id= ?2 and is_delete = 0 GROUP BY product_rm_id", nativeQuery = true)
	List<Map<String, Object>> FnGetQtyProductRmStockSummary(List<String> distinctMaterialIds, int companyId);

	@Query(value = "SELECT COUNT(*) FROM pt_purchase_order_details WHERE purchase_order_no <> ?1 and indent_no = ?2 and company_id= ?3 and is_active = 1 and is_delete = 0 ", nativeQuery = true)
	long checkPOAgainstIndent(String purchaseOrderNo, String indentNumber, int companyId);

	@Query(value = "SELECT purchase_order_details_transaction_id, purchase_order_no, product_material_id, purchase_order_item_status, grn_item_status FROM pt_purchase_order_details WHERE purchase_order_master_transaction_id = ?1 and is_delete = 0 ", nativeQuery = true)
	List<Map<String, Object>> getPODetails(Integer poMasterTransactionId);

	@Modifying
	@Transactional
	@Query(value = "update pt_purchase_order_details set purchase_order_item_status = 'Z' , pree_closed_quantity = ?1, pre_closed_remark = ?2 where is_delete = 0 and purchase_order_details_transaction_id = ?3", nativeQuery = true)
	void updatePreClosedDetailsRecord(double preeClosedQuantity, String preClosedRemark, int purchaseOrderDetailsTransactionId);



	//Cotton Bales GRN
	@Query("SELECT model FROM CPtPurchaseOrderDetailsModel model WHERE model.purchase_order_no = ?1 AND model.company_id = ?2 AND model.is_delete = 0")
	ArrayList<CPtPurchaseOrderDetailsModel> fnGetPoModelsAgainstPONo(String purchase_order_no, Integer company_id);

}
