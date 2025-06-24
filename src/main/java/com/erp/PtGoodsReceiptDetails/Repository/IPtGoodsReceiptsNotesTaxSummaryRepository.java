package com.erp.PtGoodsReceiptDetails.Repository;

import com.erp.PtGoodsReceiptDetails.Model.CPtGoodsReceiptsNotesTaxSummaryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface IPtGoodsReceiptsNotesTaxSummaryRepository
		extends JpaRepository<CPtGoodsReceiptsNotesTaxSummaryModel, Integer> {

	@Modifying
	@Transactional
	@Query(value = "update pt_goods_receipts_notes_tax_summary set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where goods_receipt_no = ?1 and goods_receipt_version = ?2 and financial_year = ?3 and company_id = ?4", nativeQuery = true)
	void updateStatus(String goods_receipt_no, Integer goods_receipt_version, String financial_year, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update pt_goods_receipts_notes_tax_summary set is_delete = 1, deleted_on = CURRENT_TIMESTAMP(),  deleted_by = ?2 where goods_receipt_master_transaction_id=?1 and is_delete=0", nativeQuery = true)
	void deleteGoodsReceiptsNotesTaxSummary(int goods_receipt_master_transaction_id, String Username);

//	@Query(value = "FROM CPtGoodsReceiptsNotesTaxSummaryModel model where model.is_delete =0 and model.goods_receipts_notes_tax_summary_transaction_id = ?1 and model.company_id = ?2")
//	CPtGoodsReceiptsNotesTaxSummaryModel FnShowParticularRecordForUpdate(int goods_receipts_notes_tax_summary_transaction_id, int company_id);

}
