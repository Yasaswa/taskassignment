package com.erp.XtSpinningYarnPackingMaster.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.erp.XtSpinningYarnPackingMaster.Model.CXtSpinningYarnPackingDetailsViewModel;

public interface IXtSpinningYarnPackingDetailsViewRepository extends JpaRepository<CXtSpinningYarnPackingDetailsViewModel, Integer>{

	
	@Query(value = "FROM CXtSpinningYarnPackingDetailsViewModel model WHERE model.is_delete = 0 and model.yarn_packing_master_id = ?1 and model.company_id = ?2")
	List<CXtSpinningYarnPackingDetailsViewModel> FnShowSpinningYarnPackingDetailsRecord(int yarn_packing_master_id,
			int company_id);

	@Query(value = "FROM CXtSpinningYarnPackingDetailsViewModel model WHERE model.is_delete = false and model.yarn_packing_master_id = ?1")
	List<CXtSpinningYarnPackingDetailsViewModel> getAllYarnPackingDetails(int yarn_packing_master_id);
}
