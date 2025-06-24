package com.erp.MtSalesOrderMasterServices.Repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.erp.MtSalesOrderMasterServices.Model.CMtSalesOrderServiceActivitiesModel;

public interface IMtSalesOrderServiceActivitiesRepository extends JpaRepository<CMtSalesOrderServiceActivitiesModel, Integer>{

	@Modifying
	@Transactional
	@Query(value = "update CMtSalesOrderServiceActivitiesModel model set model.is_delete = 1, model.deleted_on = CURRENT_TIMESTAMP() where model.sales_order_no = ?1 and model.sales_order_version = ?2 and model.company_id = ?3")
	void updateServiceActivitiesDetails(String sales_order_no, int int1, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update CMtSalesOrderServiceActivitiesModel model set model.is_delete = 1, model.deleted_by = ?4,  model.deleted_on = CURRENT_TIMESTAMP() where model.sales_order_no = ?1 and model.sales_order_version = ?2 and model.company_id = ?3")
	void deleteSalesOrderTermsServiceRecords(String sales_order_no, int sales_order_version, int company_id,
			String deleted_by);

	
	
}
