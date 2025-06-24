package com.erp.XtSpinningProductionRfMaster.Repository;


import com.erp.XtSpinningProductionRfMaster.Model.CXtSpinningProductionRfMasterModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Map;


public interface IXtSpinningProductionRfMasterRepository extends JpaRepository<CXtSpinningProductionRfMasterModel, Integer> {


	@Query(value = "SELECT * FROM xt_spinning_production_rf_master  where is_delete =0 and spinning_production_rf_master_id = ?1 and company_id = ?2", nativeQuery = true)
	Map<String, Object> FnShowspinningProductionRfMasterRecord(int spinning_production_rf_master_id,
	                                                           int company_id);


	@Modifying
	@Transactional
	@Query(value = "update xt_spinning_production_rf_master set is_delete = 1 ,deleted_by=?2 , deleted_on = CURRENT_TIMESTAMP() where spinning_production_rf_master_id = ?1", nativeQuery = true)
	void FnDeleteSpinningProductionRfMasterRecord(int spinning_production_rf_master_id, String deleted_by);


}
