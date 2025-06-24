package com.erp.PtGoodsReturnsDetails.Repository;

import com.erp.PtGoodsReturnsDetails.Model.CPtGoodsReturnBottomDetailsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface IPtGoodsReturnBottomDetailsRepository  extends JpaRepository<CPtGoodsReturnBottomDetailsModel,Integer> {
    @Modifying
    @Transactional
    @Query(value = "update pt_goods_return_bottom_details set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where goods_return_master_id= ?1 and company_id = ?2", nativeQuery = true)
    void updateWarpingBottomStatus(int goodsReturnMasterId, int companyId);
    @Query(value = "FROM CPtGoodsReturnBottomDetailsModel model where model.is_delete = 0 and model.goods_return_master_id = ?1 and model.company_id = ?2")
    List<CPtGoodsReturnBottomDetailsModel> FnShowGoodsReturnBottomDetails(int goodsReturnMasterId, int companyId);
}
