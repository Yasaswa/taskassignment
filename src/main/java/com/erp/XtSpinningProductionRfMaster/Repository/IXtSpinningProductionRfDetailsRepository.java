package com.erp.XtSpinningProductionRfMaster.Repository;

import com.erp.XtSpinningProductionRfMaster.Model.CXtSpinningProductionRfDetailsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface IXtSpinningProductionRfDetailsRepository extends JpaRepository<CXtSpinningProductionRfDetailsModel, Integer> {


//	@Query(value = "FROM CXtSpinningProductionRfDetailsModel  model where model.is_delete = 0 and model.spinning_production_rf_master_id = ?2 and model.company_id = ?3")
//	List<CXtSpinningProductionRfDetailsModel> FnShowspinningProductionRfDetailsRecord(
//			int spinning_production_rf_master_id, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update xt_spinning_production_rf_details set is_delete = 1 ,deleted_by=?2 , deleted_on = CURRENT_TIMESTAMP() where spinning_production_rf_master_id = ?1", nativeQuery = true)
	void FnDeleteSpinningProductionRfDetailsRecord(int spinning_production_rf_master_id, String deleted_by);

}
