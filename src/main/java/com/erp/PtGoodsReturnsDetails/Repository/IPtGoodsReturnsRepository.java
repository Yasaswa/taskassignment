package com.erp.PtGoodsReturnsDetails.Repository;

import com.erp.PtGoodsReturnsDetails.Model.CPtGoodsReturnsDetailsViewModel;
import com.erp.PtGoodsReturnsDetails.Model.CPtGoodsReturnsMasterModel;
import com.erp.PtGoodsReturnsDetails.Model.CPtGoodsReturnsMasterViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface IPtGoodsReturnsRepository extends JpaRepository<CPtGoodsReturnsMasterModel,Integer> {

    @Modifying
    @Transactional
    @Query(value = "Update CPtGoodsReturnsMasterModel model SET model.is_delete = 1, model.deleted_by = ?3, model.deleted_on = CURRENT_TIMESTAMP() WHERE model.goods_return_master_id = ?1 and model.company_id = ?2")
    void FnDeleteGoodsReturnsMasterRecord(int goodsReturnMasterId, int companyId, String deletedBy);

    @Query(value = "FROM CPtGoodsReturnsMasterViewModel model where model.is_delete = 0 and model.goods_return_master_id = ?1 and model.company_id = ?2")
    CPtGoodsReturnsMasterViewModel FnShowParticularRecordForUpdate(int goodsReturnMasterId, int companyId);
}
