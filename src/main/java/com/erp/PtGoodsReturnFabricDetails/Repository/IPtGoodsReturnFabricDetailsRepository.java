package com.erp.PtGoodsReturnFabricDetails.Repository;

import com.erp.PtGoodsReturnFabricDetails.Model.CPtGoodsReturnFabricDetailsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

public interface IPtGoodsReturnFabricDetailsRepository extends JpaRepository<CPtGoodsReturnFabricDetailsModel,Integer> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE pt_goods_return_fabric_details SET is_delete = 1, deleted_on = CURRENT_TIMESTAMP() WHERE goods_return_fabric_master_id IN (?1) ", nativeQuery = true)
    void updateGoodsReturnFabricRecords(List<Integer> distinctGoodsReturnFabricMasterIds);


    @Query(value = "SELECT * FROM ptv_goods_return_fabric_details WHERE goods_return_fabric_no = ?1 and goods_return_fabric_version = ?2 and financial_year = ?3 and company_id= ?4", nativeQuery = true)
    List<Map<String, Object>> FnShowGoodsReturnDetailsRecords(String goods_return_fabric_no, int goods_return_fabric_version,
                                                               String financial_year, int company_id);


    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "UPDATE pt_goods_return_fabric_details pgrfd SET pgrfd.is_delete = 1, pgrfd.deleted_on = CURRENT_TIMESTAMP(), pgrfd.deleted_by = :deletedBy WHERE pgrfd.company_id = :companyId AND pgrfd.goods_return_fabric_no = :goods_return_fabric_no")
    void deleteRecords(@Param("companyId") Integer companyId, @Param("deletedBy") String deletedBy, @Param("goods_return_fabric_no") String goodsReturnFabricNo);

}
