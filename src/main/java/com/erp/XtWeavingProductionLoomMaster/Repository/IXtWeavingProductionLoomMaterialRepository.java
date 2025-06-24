package com.erp.XtWeavingProductionLoomMaster.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.erp.XtWeavingProductionLoomMaster.Model.CXtWeavingProductionLoomMaterialModel;


public interface IXtWeavingProductionLoomMaterialRepository extends JpaRepository<CXtWeavingProductionLoomMaterialModel, Integer> {

	@Modifying
    @Transactional	
    @Query(value = "UPDATE xt_weaving_production_loom_material SET is_delete = 1, deleted_on = CURRENT_TIMESTAMP() WHERE weaving_production_loom_material_id NOT IN (?1) AND loom_production_code = ?2", nativeQuery = true)
	void updateLoomProductionMaterialDetails(List<Integer> distinctloomProductionMaterialIds,String loom_production_code);

	@Modifying
	@Transactional
	@Query(value = "update CXtWeavingProductionLoomMaterialModel model SET model.is_delete = 1 ,model.deleted_by = ?3, model.deleted_on = CURRENT_TIMESTAMP() WHERE model.weaving_production_loom_master_id = ?1 and model.company_id = ?2")
	void FnDeleteWeavingProductionLoomMaterialRecord(int weaving_production_loom_master_id, int company_id,
			String deleted_by);

	
	@Modifying
    @Transactional	
    @Query(value = "UPDATE CXtWeavingProductionLoomMaterialModel model SET model.is_delete = 1, model.deleted_on = CURRENT_TIMESTAMP() WHERE  model.loom_production_code = ?1")
	void updateLoomProductionAllMaterialDetails(String loom_production_code);


}
