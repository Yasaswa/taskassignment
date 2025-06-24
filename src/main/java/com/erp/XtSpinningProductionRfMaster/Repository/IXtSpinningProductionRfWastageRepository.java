package com.erp.XtSpinningProductionRfMaster.Repository;

import com.erp.XtSpinningProductionRfMaster.Model.CXtSpinningProductionRfWastageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface IXtSpinningProductionRfWastageRepository extends JpaRepository<CXtSpinningProductionRfWastageModel, Integer> {


	@Modifying
	@Transactional
	@Query(value = "update xt_spinning_production_rf_wastage set is_delete = 1 ,deleted_by=?2 , deleted_on = CURRENT_TIMESTAMP() where spinning_production_rf_master_id = ?1", nativeQuery = true)
	void FnDeleteSpinningProductionRfWastageRecord(int spinning_production_rf_master_id, String deleted_by);

//	@Query(value = "FROM CXtSpinningProductionRfWastageModel  model where model.is_delete = 0 and model.spinning_production_rf_master_id = ?1 and model.company_id = ?2")
//	List<CXtSpinningProductionRfWastageModel> FnShowspinningProductionRfWastageRecord(
//			int spinning_production_rf_master_id, int company_id);

}
