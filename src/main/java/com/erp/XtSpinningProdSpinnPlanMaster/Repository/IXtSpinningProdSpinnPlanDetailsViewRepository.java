package com.erp.XtSpinningProdSpinnPlanMaster.Repository;

import com.erp.XtSpinningProdSpinnPlanMaster.Model.CXtSpinningProdSpinnPlanDetailsViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IXtSpinningProdSpinnPlanDetailsViewRepository extends JpaRepository<CXtSpinningProdSpinnPlanDetailsViewModel, Integer> {

//	@Query(value = "FROM CXtSpinningProdSpinnPlanDetailsModel model where model.is_delete =0 and model.plant_id = ?1 and model.spinn_plan_code = ?2")
//	List<CXtSpinningProdSpinnPlanDetailsViewModel> FnShowSpinningProdPlanDetailsRecord(int plant_id,
//			String spinn_plan_code);

	@Query(value = "FROM CXtSpinningProdSpinnPlanDetailsViewModel model where model.is_delete = 0 and model.plant_id = ?1 and model.spinn_plan_code = ?2 and model.company_id = ?3")
	List<CXtSpinningProdSpinnPlanDetailsViewModel> FnShowSpinningProdPlanDetailsRecord(int plant_id,
	                                                                                   String spinn_plan_code, int company_id);

}
