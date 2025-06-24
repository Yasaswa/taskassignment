package com.erp.XmWeavingProductionPlanMaster.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.erp.XmWeavingProductionPlanMaster.Model.CXmWeavingProductionPlanSummaryViewModel;

public interface IXmWeavingProductionPlanSummaryViewRepository extends JpaRepository<CXmWeavingProductionPlanSummaryViewModel, Integer>{
	
	@Query(value = "FROM CXmWeavingProductionPlanSummaryViewModel model where model.is_delete = 0 and model.weaving_production_plan_master_id = ?1 and model.company_id = ?2")
	CXmWeavingProductionPlanSummaryViewModel FnShowParticularRecordForUpdate(int weaving_production_plan_master_id,
			int company_id);

}
