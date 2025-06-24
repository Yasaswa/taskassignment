package com.erp.PtCottonBalesGRN.Repository;


import com.erp.PtGoodsReceiptDetails.Model.CPtGoodsReceiptsNotesTaxSummaryViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.erp.PtCottonBalesGRN.Model.CPtGrnCottonBalesOrderTaxSummaryModel;

import javax.transaction.Transactional;
import java.util.List;


public interface IPtGrnCottonBalesOrderTaxSummaryRepository extends JpaRepository<CPtGrnCottonBalesOrderTaxSummaryModel, Integer> {

	@Query(value = "FROM CPtGrnCottonBalesOrderTaxSummaryModel model where model.is_delete =0 and model.grn_cotton_bales_tax_summary_transaction_id = ?1 and model.company_id = ?2")
	CPtGrnCottonBalesOrderTaxSummaryModel FnShowParticularRecordForUpdate(int grn_cotton_bales_tax_summary_transaction_id, int company_id);

//	@Modifying
//	@Transactional
//	@Query(value = "update pt_grn_cotton_bales_order_tax_summary set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where goods_receipt_no = ?1 and goods_receipt_version = ?2 and financial_year = ?3 and company_id = ?4", nativeQuery = true)
//	void updateStatus(String goodsReceiptNo, int goodsReceiptVersion, String financialYear, int companyId);

	@Modifying
	@Transactional
	@Query(value = "update pt_grn_cotton_bales_order_tax_summary set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where goods_receipt_no = ?1 and  financial_year = ?2 and company_id = ?3", nativeQuery = true)
	void updateStatus(String goodsReceiptNo,  String financialYear, int companyId);

	@Modifying
	@Transactional
	@Query(value = "update pt_grn_cotton_bales_order_tax_summary set is_delete = 1, deleted_on = CURRENT_TIMESTAMP(),  deleted_by = ?2 where grn_cotton_bales_master_transaction_id = ?1 and is_delete=0", nativeQuery = true)
	void deleteGRNCottonBalesNotesTaxSummary(int goodsReceiptMasterTransactionId, String userName);

	@Query(value = "from CPtGrnCottonBalesOrderTaxSummaryModel model where model.is_delete = 0 and model.goods_receipt_no = ?1 and model.goods_receipt_version = ?2 and model.financial_year = ?3 and model.company_id = ?4 and model.is_delete = 0")
	List<CPtGrnCottonBalesOrderTaxSummaryModel> FnShowGRNCottonBalesTaxSummaryRecords(String goodsReceiptNo, int goodsReceiptVersion, String financialYear, int companyId);

}
