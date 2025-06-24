package com.erp.PtGoodsReturnFabricDetails.Repository;

import com.erp.PtGoodsReceiptDetails.Model.CPtGoodsReceiptPaymentTermsViewModel;
import com.erp.PtGoodsReturnFabricDetails.Model.CPtGoodsReturnFabricPaymentTermModel;
import com.erp.PtGoodsReturnsDetails.Model.CPtGoodsReturnsPaymentTermsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface IPtGoodsReturnFabricPaymentTermRepository extends JpaRepository<CPtGoodsReturnFabricPaymentTermModel,Integer> {


    @Modifying
    @Transactional
    @Query(value = "update pt_goods_return_fabric_payment_terms set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where goods_return_fabric_master_id= ?1 and goods_return_fabric_version = ?2 and company_id = ?3", nativeQuery = true)
    void updatePaymentTermsStatus(int goodsReturnFabricMasterId, int goods_return_fabric_version, int companyId);


    @Query(value = "from CPtGoodsReturnFabricPaymentTermModel model where model.goods_return_fabric_no = ?1 and model.goods_return_fabric_version = ?2 and model.financial_year = ?3 and model.company_id = ?4 and model.is_delete = 0")
    List<CPtGoodsReturnFabricPaymentTermModel> FnShowGoodsReturnPaymentTermsRecords(String goods_return_fabric_no,
                                                                                          int goods_return_fabric_version, String financial_year, int company_id);


    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "UPDATE pt_goods_return_fabric_payment_terms pgrfpt SET pgrfpt.is_delete = 1, pgrfpt.deleted_on = CURRENT_TIMESTAMP(), pgrfpt.deleted_by = :deletedBy WHERE pgrfpt.company_id = :companyId AND pgrfpt.goods_return_fabric_no = :goods_return_fabric_no")
    void deleteRecords(@Param("companyId") Integer companyId, @Param("deletedBy") String deletedBy, @Param("goods_return_fabric_no") String goodsReturnFabricNo);

}
