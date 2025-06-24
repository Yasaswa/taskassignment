package com.erp.PtGoodsReturnFabricDetails.Repository;

import com.erp.PtGoodsReceiptDetails.Model.CptGoodsReceiptIndentDetailsViewModel;
import com.erp.PtGoodsReturnFabricDetails.Model.CPtGoodsReturnFabricRollsDatailsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface IPtGoodsReturnFabricRollsDetailsRepository extends JpaRepository<CPtGoodsReturnFabricRollsDatailsModel,Integer> {


    @Query(value = "from CPtGoodsReturnFabricRollsDatailsModel model where model.goods_return_fabric_no = ?1 and model.goods_return_fabric_version = ?2 and model.financial_year = ?3 and model.company_id = ?4 and model.is_delete = 0")
    List<CPtGoodsReturnFabricRollsDatailsModel> FnShowGoodsReturnRollDetailsRecords(String goods_return_fabric_no,
                                                                                    int goods_return_fabric_version, String financial_year, int company_id);



    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "UPDATE pt_goods_return_fabric_rolls_details pgrfrd SET pgrfrd.is_delete = 1, pgrfrd.deleted_on = CURRENT_TIMESTAMP(), pgrfrd.deleted_by = :deletedBy WHERE pgrfrd.company_id = :companyId AND pgrfrd.goods_return_fabric_no = :goods_return_fabric_no")
    void deleteRecords(@Param("companyId") Integer companyId, @Param("deletedBy") String deletedBy, @Param("goods_return_fabric_no") String goodsReturnFabricNo);


    @Modifying
    @Transactional
    @Query(value = "update pt_goods_return_fabric_rolls_details set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where goods_return_fabric_no = ?1 and goods_return_fabric_version = ?2 and financial_year = ?3 and company_id = ?4", nativeQuery = true)
    void updateStatus(String goods_return_fabric_no, Integer goods_return_fabric_version, String financial_year, int company_id);
}
