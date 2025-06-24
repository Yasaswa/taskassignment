package com.erp.PtGoodsReceiptDetails.Repository;

import com.erp.PtGoodsReceiptDetails.Model.CPtGoodsReceiptMasterViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IPtGoodsReceiptMasterViewRepository extends JpaRepository<CPtGoodsReceiptMasterViewModel, Integer> {

//	@Query(value = "FROM CPtGoodsReceiptMasterViewModel model where model.is_delete=0 and model.company_id = ?1 and model.expected_branch_id = ?2 and model.supplier_id = ?3 and model.goods_receipt_type_id = ?4 AND model.goods_receipt_no IN ?5")
//	List<CPtGoodsReceiptMasterViewModel> FnShowGoodReceiptSummaryDetails(int company_id, int expected_branch_id,
//			int supplier_id, int goods_receipt_type_id, List<String> goodReceiptNosList);

	@Query(value = "FROM CPtGoodsReceiptMasterViewModel model where model.is_delete=0 and model.company_id = ?1 and model.expected_branch_id = ?2 and model.supplier_id = ?3 AND model.goods_receipt_no IN ?4")
	List<CPtGoodsReceiptMasterViewModel> FnShowGoodReceiptSummaryDetails(int company_id, int expected_branch_id,
	                                                                     int supplier_id, List<String> goodReceiptNosList);


}
