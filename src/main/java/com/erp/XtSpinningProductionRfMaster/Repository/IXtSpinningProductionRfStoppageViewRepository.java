package com.erp.XtSpinningProductionRfMaster.Repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.erp.XtSpinningProductionRfMaster.Model.CXtSpinningProductionRfStoppageViewModel;

public interface IXtSpinningProductionRfStoppageViewRepository extends JpaRepository<CXtSpinningProductionRfStoppageViewModel, Integer>{

	
//	@Query(value = "FROM CXtSpinningProductionRfStoppageViewModel  model where model.is_delete = false and model.spinning_production_rf_master_id = ?1 and model.company_id = ?2")
	@Query(value = "SELECT * FROM xtv_spinning_production_rf_stoppage  where is_delete =0 and spinning_production_rf_master_id = ?1 and company_id = ?2", nativeQuery = true)
	List<Map<String, Object>> FnShowspinningProductionRfStoppageRecord(
			int spinning_production_rf_master_id, int company_id);

}
