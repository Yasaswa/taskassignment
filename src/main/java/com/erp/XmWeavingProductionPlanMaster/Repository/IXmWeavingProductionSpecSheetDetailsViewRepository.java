package com.erp.XmWeavingProductionPlanMaster.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.erp.XmWeavingProductionPlanMaster.Model.CXmWeavingProductionSpecSheetDetailsViewModel;

public interface IXmWeavingProductionSpecSheetDetailsViewRepository extends JpaRepository<CXmWeavingProductionSpecSheetDetailsViewModel, Integer>{

	@Query(value = "FROM CXmWeavingProductionSpecSheetDetailsViewModel model where model.is_delete = 0 and model.weaving_production_plan_master_id = ?1 and model.company_id = ?2")
	List<CXmWeavingProductionSpecSheetDetailsViewModel> FnShowWeavingProductionSpecSheetDetailRecord(
			int weaving_production_plan_master_id, int company_id);
	

}
