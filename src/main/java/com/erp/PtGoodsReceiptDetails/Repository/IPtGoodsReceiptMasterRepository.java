package com.erp.PtGoodsReceiptDetails.Repository;

import com.erp.PtGoodsReceiptDetails.Model.CPtGoodsReceiptMasterModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

public interface IPtGoodsReceiptMasterRepository extends JpaRepository<CPtGoodsReceiptMasterModel, Integer> {

	@Modifying
	@Transactional
	@Query(value = "update pt_goods_receipt_master set is_delete = 1, deleted_on = CURRENT_TIMESTAMP(), deleted_by = ?2 where goods_receipt_master_transaction_id = ?1 and is_delete = 0", nativeQuery = true)
	void deleteGoodsReceiptMaster(int goods_receipt_master_transaction_id, String UserName);

	@Query(value = "Select * from pt_goods_receipt_master where is_delete = 0 and goods_receipt_no = ?1 and company_id = ?2 ORDER BY goods_receipt_master_transaction_id DESC LIMIT 1", nativeQuery = true)
	CPtGoodsReceiptMasterModel getLastGoodsReceiptVersion(String goods_receipt_no, String company_id);

	@Query(value = "SELECT * FROM ptv_goods_receipt_summary  WHERE goods_receipt_no = ?1 and goods_receipt_version = ?2 and financial_year = ?3 and company_id= ?4", nativeQuery = true)
	Map<String, Object> FnShowGoodsReceiptMasterRecord(String goods_receipt_no, int goods_receipt_version,
	                                                   String financial_year, int company_id);

	@Query(value = "SELECT * FROM pt_goods_receipt_master WHERE goods_receipt_no = ?1 and goods_receipt_version = ?2 and financial_year = ?3 and company_id= ?4 and is_delete = 0", nativeQuery = true)
	CPtGoodsReceiptMasterModel getExistingRecord(String goods_receipt_no, Integer goods_receipt_version,
	                                             String financial_year, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update pt_goods_receipt_master set goods_receipt_status = 'B' WHERE goods_receipt_no IN ?1 AND is_delete = 0  AND company_id = ?2", nativeQuery = true)
	void FnUpdateGoodsReceiptMasterRecord(List<String> goodReceiptNos, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update pt_goods_receipt_master set goods_receipt_status = 'G' WHERE goods_receipt_no IN ?1 AND is_delete = 0  AND company_id = ?2", nativeQuery = true)
	void FnUpdateGoodsReceiptMasterStatus(List<String> goodReceiptNos, int company_id);


}
