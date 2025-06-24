package com.erp.PtGoodsReceiptDetails.Repository;

import com.erp.PtGoodsReceiptDetails.Model.CPtGoodsReceiptsNotesTaxSummaryViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IPtGoodsReceiptsNotesTaxSummaryViewRepository extends JpaRepository<CPtGoodsReceiptsNotesTaxSummaryViewModel, Integer> {


	@Query(value = "from CPtGoodsReceiptsNotesTaxSummaryViewModel model where model.is_delete = 0 and model.goods_receipt_no = ?1 and model.goods_receipt_version = ?2 and model.company_id = ?3 and model.is_delete = 0")
	List<CPtGoodsReceiptsNotesTaxSummaryViewModel> FnShowGoodsReceiptNoteTaxSummary(String goods_receipt_no,
	                                                                                Integer goods_receipt_version, int company_id);


	@Query(value = "from CPtGoodsReceiptsNotesTaxSummaryViewModel model where model.is_delete = 0 and model.goods_receipt_no = ?1 and model.goods_receipt_version = ?2 and model.financial_year = ?3 and model.company_id = ?4 and model.is_delete = 0")
	List<CPtGoodsReceiptsNotesTaxSummaryViewModel> FnShowGoodsReceiptNotesTaxSummaryRecords(String goods_receipt_no,
	                                                                                        int goods_receipt_version, String financial_year, int company_id);

	@Query(value = "FROM CPtGoodsReceiptsNotesTaxSummaryViewModel model where model.is_delete=0 and model.company_id = ?1 and model.expected_branch_id = ?2 and model.supplier_id = ?3 AND model.goods_receipt_no IN ?4")
	List<CPtGoodsReceiptsNotesTaxSummaryViewModel> FnShowGoodReceiptTaxSummaryViewData(int company_id, int expected_branch_id,
	                                                                                   int supplier_id, List<String> goodReceiptNosList);

}
