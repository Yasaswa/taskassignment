package com.erp.XtWeavingProductionInspectionMaster.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.erp.XtWeavingProductionInspectionMaster.Model.CXtWeavingProductionInspectionWastageModel;

public interface IXtWeavingProductionInspectionWastageRepository extends JpaRepository<CXtWeavingProductionInspectionWastageModel, Integer>{

	
	@Modifying
    @Transactional
    @Query(value = "UPDATE xt_weaving_production_inspection_wastage SET is_delete = 1, deleted_on = CURRENT_TIMESTAMP() WHERE weaving_production_inspection_wastage_id NOT IN (?1) AND inspection_production_code = ?2", nativeQuery = true)
    void updateWeavingProductionInspectionWastageDetails(List<Integer> distinctwvProductionInspectionWastageIds,String inspection_production_code);

	@Modifying
	@Transactional
	@Query(value = "update CXtWeavingProductionInspectionWastageModel model SET model.is_delete = 1 , model.deleted_by = ?3, model.deleted_on = CURRENT_TIMESTAMP() WHERE model.weaving_production_inspection_master_id = ?1 and model.company_id = ?2")
	void FnDeleteRecordWVProductionInspectionWastageRecord(int weaving_production_inspection_master_id, int company_id,String deleted_by);

	@Modifying
    @Transactional
    @Query(value = "UPDATE xt_weaving_production_inspection_wastage SET is_delete = 1, deleted_on = CURRENT_TIMESTAMP() WHERE inspection_production_code = ?1", nativeQuery = true)
    void updateWeavingProductionAllInspectionWastageDetails(String inspection_production_code);
	

}
