package com.erp.PtGoodsReturnFabricDetails.Repository;

import com.erp.PtGoodsReceiptDetails.Model.CPtGoodsReceiptsNotesTaxSummaryViewModel;
import com.erp.PtGoodsReturnFabricDetails.Model.CPtGoodsReturnFabricTaxSummaryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface IPtGoodsReturnFabricTaxSummaryRepository extends JpaRepository<CPtGoodsReturnFabricTaxSummaryModel,Integer> {

    @Modifying
    @Transactional
    @Query(value = "update pt_goods_return_fabric_tax_summary set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where goods_return_fabric_no = ?1 and goods_return_fabric_version = ?2 and financial_year = ?3 and company_id = ?4", nativeQuery = true)
    void updateStatus(String goods_return_fabric_no, Integer goods_return_fabric_version, String financial_year, int company_id);
    @Query(value = "from CPtGoodsReturnFabricTaxSummaryModel model where model.goods_return_fabric_no = ?1 and model.goods_return_fabric_version = ?2 and model.financial_year = ?3 and model.company_id = ?4 and model.is_delete = 0")
    List<CPtGoodsReturnFabricTaxSummaryModel> FnShowGoodsReturnTaxSummaryRecords(String goods_return_fabric_no,
                                                                                            int goods_return_fabric_version, String financial_year, int company_id);


    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "UPDATE pt_goods_return_fabric_tax_summary pgrfts SET pgrfts.is_delete = 1, pgrfts.deleted_on = CURRENT_TIMESTAMP(), pgrfts.deleted_by = :deletedBy WHERE pgrfts.company_id = :companyId AND pgrfts.goods_return_fabric_no = :goods_return_fabric_no")
    void deleteRecords(@Param("companyId") Integer companyId, @Param("deletedBy") String deletedBy, @Param("goods_return_fabric_no") String goodsReturnFabricNo);

}
