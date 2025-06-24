package com.erp.XtSpinningYarnPackingMaster.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.erp.XtSpinningYarnPackingMaster.Model.CXtSpinningYarnPackingMasterViewModel;

public interface IXtSpinningYarnPackingMasterViewRepository extends JpaRepository<CXtSpinningYarnPackingMasterViewModel, Integer>{

	
	@Query(value = "FROM CXtSpinningYarnPackingMasterViewModel model WHERE model.is_delete = 0 and model.yarn_packing_master_id = ?1 and model.company_id = ?2")
	CXtSpinningYarnPackingMasterViewModel FnShowSpinningYarnPackingMasterRecord(int yarn_packing_master_id,
			int company_id);

}
