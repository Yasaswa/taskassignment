package com.erp.XtWeavingProductionSizingDetails.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.erp.XtWeavingProductionSizingDetails.Model.CXtWeavingProductionSizingWastageModel;

public interface IXtWeavingProductionSizingWastageRepository extends JpaRepository<CXtWeavingProductionSizingWastageModel, Integer>{

	@Modifying
    @Transactional
    @Query(value = "UPDATE xt_weaving_production_sizing_wastage SET is_delete = 1, deleted_on = CURRENT_TIMESTAMP() WHERE weaving_production_sizing_wastage_id NOT IN (?1) AND sizing_production_code = ?2", nativeQuery = true)
    void updateWeavingProductionSZWastageDetails(List<Integer> distinctwvProductionSZWastageIds,
			String sizing_production_code);

	@Modifying
	@Transactional
	@Query(value = "update CXtWeavingProductionSizingWastageModel model SET model.is_delete = 1 , model.deleted_by = ?2, model.deleted_on = CURRENT_TIMESTAMP() WHERE model.weaving_production_sizing_master_id = ?1")
	void FnDeleteProductionSizingWastageRecord(int weaving_production_sizing_master_id, String deleted_by);

	@Modifying
    @Transactional
    @Query(value = "UPDATE xt_weaving_production_sizing_wastage SET is_delete = 1, deleted_on = CURRENT_TIMESTAMP() WHERE sizing_production_code = ?1", nativeQuery = true)
    void updateWeavingProductionAllSZWastageDetails(String sizing_production_code);

}
