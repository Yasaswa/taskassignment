package com.erp.PtGoodsReceiptDetails.Repository;

import com.erp.PtGoodsReceiptDetails.Model.CPtGoodsReceiptPaymentTermsViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface IPtGoodsReceiptPaymentTermsViewRepository extends JpaRepository<CPtGoodsReceiptPaymentTermsViewModel, Integer> {

	//	@Query(value = "from CPtGoodsReceiptPaymentTermsViewModel model where model.is_delete = 0 and model.goods_receipt_no = ?1 and model.goods_receipt_version = ?2 and model.financial_year = ?3 and model.company_id = ?4 and model.is_delete = 0")
//	
	@Query(value = "from CPtGoodsReceiptPaymentTermsViewModel model where model.is_delete = 0 and model.goods_receipt_no = ?1 and model.goods_receipt_version = ?2 and model.financial_year = ?3 and model.company_id = ?4 and model.is_delete = 0")
	List<CPtGoodsReceiptPaymentTermsViewModel> FnShowGoodsReceiptNotesPaymentTermsRecords(String goods_receipt_no,
	                                                                                      int goods_receipt_version, String financial_year, int company_id);


}
