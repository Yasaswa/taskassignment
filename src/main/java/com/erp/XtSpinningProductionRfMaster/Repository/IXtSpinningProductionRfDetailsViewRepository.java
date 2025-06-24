package com.erp.XtSpinningProductionRfMaster.Repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.erp.XtSpinningProductionRfMaster.Model.CXtSpinningProductionRfDetailsViewModel;

public interface IXtSpinningProductionRfDetailsViewRepository extends JpaRepository<CXtSpinningProductionRfDetailsViewModel, Integer>{

	
	@Query(value = "FROM CXtSpinningProductionRfDetailsViewModel  model where model.is_delete = 0 and model.spinning_production_rf_master_id = ?1 and model.company_id = ?2")
	List<CXtSpinningProductionRfDetailsViewModel> FnShowspinningProductionRfDetailsRecord(
			int spinning_production_rf_master_id, int company_id);

	@Query(value = "FROM CXtSpinningProductionRfDetailsViewModel model where model.is_delete = false and model.plant_id = ?1 and model.spinning_production_date = ?2 and model.company_id = ?3")
	List<CXtSpinningProductionRfDetailsViewModel> FnShowRingFrameDetailsRecord(int plant_id, String spinning_production_date, int company_id);
}
