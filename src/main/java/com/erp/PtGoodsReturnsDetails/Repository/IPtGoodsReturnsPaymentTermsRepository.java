package com.erp.PtGoodsReturnsDetails.Repository;

import com.erp.PtGoodsReceiptDetails.Model.CPtGoodsReceiptPaymentTermsModel;
import com.erp.PtGoodsReturnsDetails.Model.CPtGoodsReturnsPaymentTermsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface IPtGoodsReturnsPaymentTermsRepository extends JpaRepository<CPtGoodsReturnsPaymentTermsModel,Integer> {



    @Modifying
    @Transactional
    @Query(value = "update pt_goods_return_payment_terms set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where goods_return_master_id= ?1 and goods_version = ?2 and company_id = ?3", nativeQuery = true)
    void updatePaymentTermsStatus(int goodsReturnMasterId, int goodsVersion, int companyId);
    @Modifying
    @Transactional
    @Query(value = "Update CPtGoodsReturnsPaymentTermsModel model  SET  model.is_delete = true, model.deleted_by = ?3, model.deleted_on = CURRENT_TIMESTAMP() WHERE model.goods_return_master_id = ?1 and model.company_id = ?2")
    void FnDeleteGoodsReturnsDetailsRecord(int goodsReturnMasterId, int companyId, String deletedBy);
    @Query(value = "FROM CPtGoodsReturnsPaymentTermsModel model where model.is_delete = 0 and model.goods_return_master_id = ?1 and model.company_id = ?2")
    List<CPtGoodsReturnsPaymentTermsModel> FnShowGoodsReturnPaymentTerms(int goodsReturnMasterId, int companyId);
}
