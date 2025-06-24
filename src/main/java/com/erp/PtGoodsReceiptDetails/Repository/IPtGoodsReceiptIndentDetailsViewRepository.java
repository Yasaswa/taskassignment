package com.erp.PtGoodsReceiptDetails.Repository;

import com.erp.PtGoodsReceiptDetails.Model.CptGoodsReceiptIndentDetailsViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IPtGoodsReceiptIndentDetailsViewRepository extends JpaRepository<CptGoodsReceiptIndentDetailsViewModel, Integer> {


    @Query(value = "from CptGoodsReceiptIndentDetailsViewModel model where model.goods_receipt_no = ?1 and model.goods_receipt_version = ?2 and model.financial_year = ?3 and model.company_id = ?4 and model.is_delete = 0")
    List<CptGoodsReceiptIndentDetailsViewModel> FnShowGoodsReceiptNoteIndentDetailsRecords(String goods_receipt_no,
                                                                                          int goods_receipt_version, String financial_year, int company_id);

}
