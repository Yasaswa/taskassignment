package com.erp.XmProductionLotMaster.Repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.erp.XmProductionLotMaster.Model.CXmProductionLotMasterModel;


public interface IXmProductionLotMasterRepository extends JpaRepository<CXmProductionLotMasterModel, Integer> {

	
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE CXmProductionLotMasterModel model Set model.is_delete = 1 , model.deleted_by = ?3 , model.deleted_on = CURRENT_TIMESTAMP() WHERE model.lot_id = ?1 AND model.company_id = ?2")
	void FnDeleteProductionLotMasterRecord(int lot_id, int company_id, String deleted_by);





}
