package com.erp.XtSpinningYarnPackingMaster.Repository;

import java.util.List;

import javax.transaction.Transactional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.erp.XtSpinningYarnPackingMaster.Model.CXtSpinningYarnPackingMasterModel;


public interface IXtSpinningYarnPackingMasterRepository extends JpaRepository<CXtSpinningYarnPackingMasterModel, Integer> {

	@Modifying
	@Transactional
	@Query(value = "update CXtSpinningYarnPackingMasterModel model SET model.is_delete = 1 ,model.deleted_by = ?3, model.deleted_on = CURRENT_TIMESTAMP() WHERE model.yarn_packing_master_id = ?1 and model.company_id = ?2")
	void FnDeleteSpinningYarnPackingMasterRecord(int yarn_packing_master_id, int company_id, String deleted_by);



}
