package com.erp.XmWeavingProductionPlanMaster.Repository;


import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.erp.XmWeavingProductionPlanMaster.Model.CXmWeavingProductionSpecSheetDetailsModel;


public interface IXmWeavingProductionSpecSheetDetailsRepository extends JpaRepository<CXmWeavingProductionSpecSheetDetailsModel, Integer> {

	@Query(value = "FROM CXmWeavingProductionSpecSheetDetailsModel model where model.is_delete =0 and model.weaving_production_plan_master_id = ?1 and model.company_id = ?2")
	CXmWeavingProductionSpecSheetDetailsModel FnShowParticularRecordForUpdate(int weaving_production_plan_master_id, int company_id);

	
	
	@Modifying
	@Transactional
	@Query(value = "Update CXmWeavingProductionSpecSheetDetailsModel model  SET  model.is_delete = true, model.deleted_by = ?3, model.deleted_on = CURRENT_TIMESTAMP() WHERE model.weaving_production_plan_master_id = ?1 and model.company_id = ?2")
	void FnDeleteWVProductionSpecSheetDetailsRecord(int weaving_production_plan_master_id, int company_id,
			String deleted_by);


}
