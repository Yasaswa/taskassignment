package com.erp.PtGoodsReceiptDetails.Repository;

import com.erp.PtGoodsReceiptDetails.Model.CptGoodsReceiptIndentDetailsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface IPtGoodsReceiptIndentDetailsRepository extends JpaRepository<CptGoodsReceiptIndentDetailsModel, Integer> {

    @Modifying
    @Transactional
    @Query(value = "update pt_goods_receipt_payment_terms set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where goods_receipt_no = ?1 and goods_receipt_version = ?2 and financial_year = ?3 and company_id = ?4", nativeQuery = true)
    void updateStatus(String goods_receipt_no, Integer goods_receipt_version, String financial_year, int company_id);

    @Modifying
    @Transactional
    @Query(value = "update pt_goods_receipts_indent_details set is_delete = 1, deleted_on = CURRENT_TIMESTAMP(), deleted_by = ?2 where goods_receipt_master_transaction_id=?1 and is_delete=0", nativeQuery = true)
    void deleteIndentGRNDetails(int goods_receipt_master_transaction_id, String UserName);

}

