package com.erp.SmProductServiceActivityMaster.Repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.erp.SmProductServiceActivityMaster.Model.CSmProductServiceActivityMasterModel;


public interface ISmProductServiceActivityMasterRepository extends JpaRepository<CSmProductServiceActivityMasterModel, Integer> {

	@Query(value = "FROM CSmProductServiceActivityMasterModel model where model.is_delete = 0 and model.activity_name = ?1")
	CSmProductServiceActivityMasterModel checkIfNameExist(String activity_name);
	
	@Modifying
	@Transactional
	@Query(value = "update CSmProductServiceActivityMasterModel model set model.is_delete = 1 , model.deleted_by = ?2 , model.deleted_on = CURRENT_TIMESTAMP() where model.product_service_activity_master_id = ?1")
	void FnDeleteProductServiceActivityMasterRecord(int product_service_activity_master_id, String deleted_by);

}
