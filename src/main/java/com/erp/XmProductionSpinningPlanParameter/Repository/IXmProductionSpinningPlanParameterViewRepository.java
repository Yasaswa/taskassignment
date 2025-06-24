package com.erp.XmProductionSpinningPlanParameter.Repository;

import com.erp.XmProductionSpinningPlanParameter.Model.CXmProductionSpinningPlanParameterViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface IXmProductionSpinningPlanParameterViewRepository
		extends JpaRepository<CXmProductionSpinningPlanParameterViewModel, Integer> {

	@Query(value = "FROM CXmProductionSpinningPlanParameterViewModel model where model.is_delete = 0 and model.company_id = ?1 order by model.production_spinning_plan_parameter_id Desc")
	Page<CXmProductionSpinningPlanParameterViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id);

	@Query(value = "FROM CXmProductionSpinningPlanParameterViewModel model where model.is_delete =0 and model.production_spinning_plan_parameter_id = ?1 and model.company_id = ?2 order by model.production_spinning_plan_parameter_id Desc")
	Page<CXmProductionSpinningPlanParameterViewModel> FnShowParticularRecord(int production_spinning_plan_parameter_id,
	                                                                         Pageable pageable, int company_id);

	@Query(value = "select * FROM xmv_production_spinning_plan_parameter_rpt", nativeQuery = true)
	Page<List<Map<String, Object>>> FnShowAllReportRecords(Pageable pageable);

}
