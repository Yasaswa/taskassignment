package com.erp.XtWeavingProductionWarpingMaster.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.erp.XtWeavingProductionWarpingMaster.Model.CXtWeavingProductionWarpingMaterialModel;

public interface IXtWeavingProductionWarpingMaterialRepository extends JpaRepository<CXtWeavingProductionWarpingMaterialModel, Integer>{

	@Modifying
	@Transactional
	@Query(value = "update CXtWeavingProductionWarpingMaterialModel model SET model.is_delete = 1 , model.deleted_by = ?2 , model.deleted_on = CURRENT_TIMESTAMP() WHERE model.weaving_production_warping_master_id = ?1")
	void FnDeleteProductionWarpingMaterialRecord(int weaving_production_warping_master_id, String deleted_by);

    @Modifying
    @Transactional
    @Query(value = "UPDATE xt_weaving_production_warping_material SET is_delete = 1, deleted_on = CURRENT_TIMESTAMP() WHERE weaving_production_warping_material_id NOT IN (?1) AND warping_production_code = ?2", nativeQuery = true)
    void updateWeavingProductionWarpingMaterial(List<Integer> distinctwvProductionWarpingMaterialIds,
			String warping_production_code);

    @Modifying
    @Transactional
    @Query(value = "UPDATE xt_weaving_production_warping_material SET is_delete = 1, deleted_on = CURRENT_TIMESTAMP() WHERE  warping_production_code = ?1", nativeQuery = true)
    void updateAllWarpingMaterial(String warping_production_code);

}
