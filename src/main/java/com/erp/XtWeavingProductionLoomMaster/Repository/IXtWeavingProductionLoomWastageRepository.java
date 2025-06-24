package com.erp.XtWeavingProductionLoomMaster.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.erp.XtWeavingProductionLoomMaster.Model.CXtWeavingProductionLoomWastageModel;

public interface IXtWeavingProductionLoomWastageRepository extends JpaRepository<CXtWeavingProductionLoomWastageModel, Integer>{


	@Modifying
    @Transactional	
    @Query(value = "UPDATE xt_weaving_production_loom_wastage SET is_delete = 1, deleted_on = CURRENT_TIMESTAMP() WHERE weaving_production_loom_wastage_id NOT IN (?1) AND loom_production_code = ?2", nativeQuery = true)
	void updateLoomProductionWastageDetails(List<Integer> distinctloomProductionWastageIds,
			String loom_production_code);

	@Modifying
	@Transactional
	@Query(value = "update CXtWeavingProductionLoomWastageModel model SET model.is_delete = 1 ,model.deleted_by = ?3, model.deleted_on = CURRENT_TIMESTAMP() WHERE model.weaving_production_loom_master_id = ?1 and model.company_id = ?2")
	void FnDeleteWeavingProductionLoomWastageRecord(int weaving_production_loom_master_id, int company_id,
			String deleted_by);

	@Modifying
    @Transactional	
    @Query(value = "UPDATE xt_weaving_production_loom_wastage SET is_delete = 1, deleted_on = CURRENT_TIMESTAMP() WHERE loom_production_code = ?1", nativeQuery = true)
	void updateLoomProductionAllWastageDetails(String loom_production_code);

}
