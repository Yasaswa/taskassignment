package com.erp.XmWeavingProductionPlanMaster.Repository;



import javax.transaction.Transactional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.erp.XmWeavingProductionPlanMaster.Model.CXmWeavingProductionPlanMasterModel;


public interface IXmWeavingProductionPlanMasterRepository extends JpaRepository<CXmWeavingProductionPlanMasterModel, Integer> {
	
	@Modifying
	@Transactional
	@Query(value = "Update CXmWeavingProductionPlanMasterModel model SET model.is_delete = 1, model.deleted_by = ?3, model.deleted_on = CURRENT_TIMESTAMP() WHERE model.weaving_production_plan_master_id = ?1 and model.company_id = ?2")
	void FnDeleteWVProductionSpecSheetMasterRecord(int weaving_production_plan_master_id, int company_id,
			String deleted_by);


}
