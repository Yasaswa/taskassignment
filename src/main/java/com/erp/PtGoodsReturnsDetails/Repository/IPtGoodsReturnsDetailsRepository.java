package com.erp.PtGoodsReturnsDetails.Repository;

import com.erp.PtGoodsReturnsDetails.Model.CPtGoodsReturnsDetailsModel;
import com.erp.PtGoodsReturnsDetails.Model.CPtGoodsReturnsDetailsViewModel;
import com.erp.XmWeavingProductionPlanMaster.Model.CXmWeavingProductionPlanSummaryViewModel;
import com.erp.XmWeavingProductionPlanMaster.Model.CXmWeavingProductionSpecSheetDetailsViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface IPtGoodsReturnsDetailsRepository extends JpaRepository<CPtGoodsReturnsDetailsModel, Integer> {

    @Modifying
    @Transactional
    @Query(value = "Update CPtGoodsReturnsDetailsModel model  SET  model.is_delete = true, model.deleted_by = ?3, model.deleted_on = CURRENT_TIMESTAMP() WHERE model.goods_return_master_id = ?1 and model.company_id = ?2")
    void FnDeleteGoodsReturnsDetailsRecord(int goodsReturnMasterId, int companyId, String deletedBy);


    @Query(value = "FROM CPtGoodsReturnsDetailsViewModel model where model.is_delete = 0 and model.goods_return_master_id = ?1 and model.company_id = ?2")
    List<CPtGoodsReturnsDetailsViewModel> FnShowGoodsReturnsDetailRecord(int goodsReturnMasterId, int companyId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE pt_goods_return_details SET is_delete = 1, deleted_on = CURRENT_TIMESTAMP() WHERE goods_return_master_id IN (?1) ", nativeQuery = true)
    void updateGoodsReturnRecords(List<Integer> distinctGoodsReturnMasterIds);

    @Query(value= "FROM CPtGoodsReturnsDetailsModel model where model.goods_receipt_no = ?1 and model.company_id = ?2 and model.is_delete = false")
    List<CPtGoodsReturnsDetailsModel> FnShowIndentMaterialIssueDetailRecords(String goodsReturnNo, Integer companyId);
    @Query(value = "SELECT mdcsym.goods_return_no FROM pt_goods_return_master mdcsym WHERE mdcsym.company_id = ?1 AND mdcsym.sales_type_id = ?2 ORDER BY mdcsym.goods_return_master_id DESC LIMIT 1", nativeQuery = true)
    String FnGenerateYarnSaleChallanNo(Integer companyId, Integer jobTypeId);
}
