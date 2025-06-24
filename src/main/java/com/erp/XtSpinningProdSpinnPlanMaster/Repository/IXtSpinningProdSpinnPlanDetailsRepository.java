package com.erp.XtSpinningProdSpinnPlanMaster.Repository;

import com.erp.XtSpinningProdSpinnPlanMaster.Model.CXtSpinningProdSpinnPlanDetailsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface IXtSpinningProdSpinnPlanDetailsRepository extends JpaRepository<CXtSpinningProdSpinnPlanDetailsModel, Integer> {

	@Modifying
	@Transactional
	@Query(value = "update xt_spinning_prod_spinn_plan_details set is_delete = 1 ,deleted_by=?2 , deleted_on = CURRENT_TIMESTAMP() where spinn_plan_id = ?1", nativeQuery = true)
	void FnDeleteSpinningProdSpinnPlanDetailsRecord(int spinn_plan_id, String deleted_by);

	@Query(value = "FROM CXtSpinningProdSpinnPlanDetailsModel model where model.is_delete =0 and model.spinn_plan_id = ?1 and model.company_id = ?2")
	List<CXtSpinningProdSpinnPlanDetailsModel> FnShowSpinningProdSpinnPlanDetailsForUpdate(int spinn_plan_id,
	                                                                                       int company_id);

}
	