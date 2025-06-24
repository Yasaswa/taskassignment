package com.erp.XtWeavingProductionSizingDetails.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.erp.XtWeavingProductionSizingDetails.Model.CXtWeavingProductionSizingStoppageModel;

public interface IXtWeavingProductionSizingStoppageRepository extends JpaRepository<CXtWeavingProductionSizingStoppageModel, Integer>{

	@Modifying
	@Transactional
	@Query(value = "update CXtWeavingProductionSizingStoppageModel model SET model.is_delete = 1 , model.deleted_by = ?2, model.deleted_on = CURRENT_TIMESTAMP() WHERE model.weaving_production_sizing_master_id = ?1")
	void FnDeleteProductionSizingStoppageRecord(int weaving_production_sizing_master_id, String deleted_by);

	@Modifying
	@Transactional
	@Query(value = "UPDATE xt_weaving_production_sizing_stoppage SET is_delete = 1, deleted_on = CURRENT_TIMESTAMP() WHERE weaving_production_sizing_stoppage_id NOT IN (?1) AND sizing_production_code = ?2 AND company_id = ?3", nativeQuery = true)
	void updateWeavingProductionSizingStoppageDetails(List<Integer> distinctwvProductionSizingStoppageIds, String sizing_production_code, int company_id);

	
	@Modifying
	@Transactional
	@Query(value = "UPDATE xt_weaving_production_sizing_stoppage SET is_delete = 1, deleted_on = CURRENT_TIMESTAMP() WHERE sizing_production_code = ?1 AND company_id = ?2", nativeQuery = true)
	void updateWeavingProductionAllSizingStoppageDetails(String sizing_production_code, int company_id);

}
