package com.erp.XtWeavingProductionInspectionMaster.Repository;

import java.util.List;


import javax.transaction.Transactional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.erp.XtWeavingProductionInspectionMaster.Model.CXtWeavingProductionInspectionMasterModel;


public interface IXtWeavingProductionInspectionMasterRepository extends JpaRepository<CXtWeavingProductionInspectionMasterModel, Integer> {

	@Modifying
	@Transactional
	@Query(value = "update CXtWeavingProductionInspectionMasterModel model SET model.is_delete = 1 , model.deleted_by = ?3, model.deleted_on = CURRENT_TIMESTAMP() WHERE model.weaving_production_inspection_master_id = ?1 and model.company_id = ?2")
	void FnDeleteRecordWVProductionInspectionMasterRecord(int weaving_production_inspection_master_id, int company_id, String deleted_by);

	
	@Query(value = "FROM CXtWeavingProductionInspectionMasterModel model WHERE model.is_delete = 0 and model.weaving_production_inspection_master_id = ?1 and model.company_id = ?2")
	CXtWeavingProductionInspectionMasterModel FnShowWVProductionInspectionMasterRecordForUpdate(
			int weaving_production_inspection_master_id, int company_id);

}
