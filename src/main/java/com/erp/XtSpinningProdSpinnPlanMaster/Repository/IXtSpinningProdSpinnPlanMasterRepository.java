package com.erp.XtSpinningProdSpinnPlanMaster.Repository;

import com.erp.XtSpinningProdSpinnPlanMaster.Model.CXtSpinningProdSpinnPlanMasterModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Map;

public interface IXtSpinningProdSpinnPlanMasterRepository
		extends JpaRepository<CXtSpinningProdSpinnPlanMasterModel, Integer> {

//	@Modifying
//	@Transactional
//	@Query(value = "update xt_spinning_prod_spinn_plan_master set is_delete = 1 ,deleted_by=?2 , deleted_on = CURRENT_TIMESTAMP() where spinn_plan_id = ?1", nativeQuery = true)
//	void FnDeleteRecord(int spinn_plan_id, String deleted_by);

	@Query(value = "SELECT * FROM xt_spinning_prod_spinn_plan_master WHERE is_delete =0 and spinn_plan_id = ?1 and company_id = ?2", nativeQuery = true)
	Map<String, Object> FnShowParticularRecordForUpdate(int spinn_plan_id, int company_id);


	@Modifying
	@Transactional
	@Query(value = "update xt_spinning_prod_spinn_plan_master set is_delete = 1 ,deleted_by=?2 , deleted_on = CURRENT_TIMESTAMP() where spinn_plan_id = ?1", nativeQuery = true)
	void FnDeleteSpinningProdSpinnPlanMasterRecords(int spinn_plan_id, String deleted_by);

}
