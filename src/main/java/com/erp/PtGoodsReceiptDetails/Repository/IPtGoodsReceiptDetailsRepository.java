package com.erp.PtGoodsReceiptDetails.Repository;

import com.erp.PtGoodsReceiptDetails.Model.CPtGoodsReceiptDetailsModel;
import com.erp.PtGoodsReceiptDetails.Model.CPtGoodsReceiptDetailsViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

public interface IPtGoodsReceiptDetailsRepository extends JpaRepository<CPtGoodsReceiptDetailsModel, Integer> {


	@Modifying
	@Transactional
	@Query(value = "update pt_goods_receipt_details set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where goods_receipt_no = ?1 and goods_receipt_version = ?2 and financial_year = ?3 and company_id = ?4", nativeQuery = true)
	void updateStatus(String goods_receipt_no, Integer goods_receipt_version, String financial_year, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update pt_goods_receipt_details set is_delete = 1, deleted_on = CURRENT_TIMESTAMP(), deleted_by = ?2 where goods_receipt_master_transaction_id = ?1 and is_delete = 0", nativeQuery = true)
	void deleteGoodsReceiptDetails(int goods_receipt_master_transaction_id, String UserName);


	@Query(value = "SELECT * FROM ptv_goods_receipt_details WHERE goods_receipt_no = ?1 and goods_receipt_version = ?2 and financial_year = ?3 and company_id= ?4", nativeQuery = true)
	List<Map<String, Object>> FnShowGoodsReceiptDetailsRecords(String goods_receipt_no, int goods_receipt_version,
	                                                           String financial_year, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update pt_purchase_order_details set grn_item_status = ?1, pending_quantity= ?2, pending_weight = ?3,  modified_on = CURRENT_TIMESTAMP() where purchase_order_details_transaction_id = ?4", nativeQuery = true)
	void FnUpdatePoMaterialStatus(String status, double materialRemainingQty, double materialRemainingWt, Integer purchase_order_details_transaction_id);

	@Modifying
	@Transactional
	@Query(value = "update pt_purchase_order_master set grn_status = ?1, modified_on = CURRENT_TIMESTAMP() where purchase_order_no = ?2 and is_delete = 0", nativeQuery = true)
	void FnUpdatePoStatus(String status, String purchase_order_no);

	@Modifying
	@Transactional
	@Query(value = "update pt_purchase_order_details set grn_item_status = ?1, modified_on = CURRENT_TIMESTAMP() where purchase_order_no = ?2 and is_delete = 0", nativeQuery = true)
	void FnUpdateAllPoMaterialStatus(String status, String purchase_order_no);

	@Modifying
	@Transactional
	@Query(value = "update pt_purchase_order_details set grn_item_status = ?1, pree_closed_quantity = ?2 ,pree_closed_weight = ?3, pending_quantity = 0, pending_weight = 0, modified_on = CURRENT_TIMESTAMP() where purchase_order_details_transaction_id = ?4 and is_delete = 0", nativeQuery = true)
	void FnUpdatePoMaterialStatusAndQty(String status, Double pree_closed_grn_quantity, Double pree_closed_grn_weight, Integer purchase_order_details_transaction_id);

	@Modifying
	@Transactional
	@Query(value = "update pt_goods_receipt_details set grn_item_status = 'B' WHERE goods_receipt_no IN ?1 AND is_delete = 0  AND company_id = ?2", nativeQuery = true)
	void FnUpdateGoodsReceiptDetailsRecord(List<String> goodReceiptNos, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update pt_goods_receipt_details set grn_item_status = 'G' WHERE goods_receipt_no IN ?1 AND is_delete = 0  AND company_id = ?2", nativeQuery = true)
	void FnUpdateGoodsReceiptDetailsStatus(List<String> goodReceiptNos, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update st_indent_details set indent_item_status = ?1, modified_on = CURRENT_TIMESTAMP() where indent_no = ?2 and is_delete = 0", nativeQuery = true)
	void FnUpdateAllIndentDetailsStatus(String Status, String indentNo);

	@Modifying
	@Transactional
	@Query(value = "update st_indent_master set  grn_status= ?1, modified_on = CURRENT_TIMESTAMP() where indent_no IN ?2 and is_delete = 0", nativeQuery = true)
	void FnUpdateIndentStatus(String Status, List<String> indentNo);
//	void FnUpdateIndentStatus(String Status, String indentNo);


	@Query(value = "select DISTINCT goods_receipt_no from pt_goods_receipt_details  WHERE purchase_order_no = ?1 AND purchase_order_version = ?2 and is_delete = 0  AND company_id = ?3", nativeQuery = true)
	String FnGetGoodsReceiptNoteNo(String purchaseOrderNo, int purchaseOrderVersion, int companyId);


}
