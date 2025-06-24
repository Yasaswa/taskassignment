package com.erp.SmProductSrActivities.Repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.erp.SmProductSrActivities.Model.CSmProductSrActivitiesModel;


public interface ISmProductSrActivitiesRepository extends JpaRepository<CSmProductSrActivitiesModel, Integer> {

	@Query(value = "FROM CSmProductSrActivitiesModel model where model.is_delete =0 and model.product_sr_activity_id = ?1 and model.company_id = ?2")
	CSmProductSrActivitiesModel FnShowParticularRecordForUpdate(int product_sr_activity_id, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update CSmProductSrActivitiesModel model set model.is_delete = 1, model.deleted_on = CURRENT_TIMESTAMP() where model.product_sr_id=?1 and model.company_branch_id = ?2 and model.company_id = ?3")
	int updateStatus(String product_sr_id, int company_branch_id, int company_id);
	
	@Modifying
	@Transactional
	@Query(value = "update CSmProductSrActivitiesModel model set model.is_delete = 1, model.deleted_by = ?3 , model.deleted_on = CURRENT_TIMESTAMP() where model.product_sr_id=?1 and model.company_id = ?2")
	void deleteproductSrActivitiesRecords(String product_sr_id, int company_id, String deleted_by);
}
