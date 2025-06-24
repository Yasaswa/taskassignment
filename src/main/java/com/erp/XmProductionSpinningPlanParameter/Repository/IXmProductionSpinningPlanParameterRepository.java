package com.erp.XmProductionSpinningPlanParameter.Repository;

import com.erp.XmProductionSpinningPlanParameter.Model.CXmProductionSpinningPlanParameterModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface IXmProductionSpinningPlanParameterRepository extends JpaRepository<CXmProductionSpinningPlanParameterModel, Integer> {

	@Query(value = "FROM CXmProductionSpinningPlanParameterModel model where model.is_delete =0 and model.production_spinning_plan_parameter_id = ?1 and model.company_id = ?2")
	CXmProductionSpinningPlanParameterModel FnShowParticularRecordForUpdate(int production_spinning_plan_parameter_id, int company_id);

	@Query(value = "FROM CXmProductionSpinningPlanParameterModel model where model.is_delete =0 and model.production_spinning_plan_parameter_name = ?1")
	CXmProductionSpinningPlanParameterModel checkIfNameExist(String production_spinning_plan_parameter_name);


}
