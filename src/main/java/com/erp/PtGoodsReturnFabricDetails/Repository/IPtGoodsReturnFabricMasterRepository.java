package com.erp.PtGoodsReturnFabricDetails.Repository;

import com.erp.PtGoodsReturnFabricDetails.Model.CPtGoodsReturnFabricMasterModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

public interface IPtGoodsReturnFabricMasterRepository extends JpaRepository<CPtGoodsReturnFabricMasterModel,Integer> {

    @Query(value = "SELECT * FROM ptv_goods_return_fabric_master  WHERE goods_return_fabric_no = ?1 and goods_return_fabric_version = ?2 and financial_year = ?3 and company_id= ?4", nativeQuery = true)
    Map<String, Object> FnShowGoodsReturnMasterRecord(String goods_return_fabric_no, int goods_return_fabric_version,
                                                       String financial_year, int company_id);


    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "UPDATE pt_goods_return_fabric_master pgrfm SET pgrfm.is_delete = 1, pgrfm.deleted_on = CURRENT_TIMESTAMP(), pgrfm.deleted_by = :deletedBy WHERE pgrfm.company_id = :companyId AND pgrfm.goods_return_fabric_no = :goods_return_fabric_no")
    void deleteRecords(@Param("companyId") Integer companyId, @Param("deletedBy") String deletedBy, @Param("goods_return_fabric_no") String goodsReturnFabricNo);

}
