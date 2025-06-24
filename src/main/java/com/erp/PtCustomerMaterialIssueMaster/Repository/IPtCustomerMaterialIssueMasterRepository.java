package com.erp.PtCustomerMaterialIssueMaster.Repository;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.erp.PtCustomerMaterialIssueMaster.Model.CPtCustomerMaterialIssueMasterModel;


public interface IPtCustomerMaterialIssueMasterRepository extends JpaRepository<CPtCustomerMaterialIssueMasterModel, Integer> {

	@Query(value = "FROM CPtCustomerMaterialIssueMasterModel model where model.is_delete =0 and model.customer_material_issue_master_transaction_id = ?1 and model.company_id = ?2")
	CPtCustomerMaterialIssueMasterModel FnShowParticularRecordForUpdate(int customer_material_issue_master_transaction_id, int company_id);

	
	@Query(value = "FROM CPtCustomerMaterialIssueMasterModel model WHERE model.customer_material_issue_no = ?1 and model.customer_material_issue_version = ?2 and model.financial_year = ?3 and model.company_id= ?4")
	CPtCustomerMaterialIssueMasterModel getExistingRecord(String customer_material_issue_no,
			Integer customer_material_issue_version, String financial_year, int company_id);


	@Modifying
	@Transactional
	@Query(value = "update pt_customer_material_issue_master set is_delete = 1 , deleted_by =?4, deleted_on = CURRENT_TIMESTAMP() where customer_material_issue_no = ?1 and customer_material_issue_version = ?2 and company_id = ?3", nativeQuery = true)
	void FnDeleteCustomerMaterialIssueMasterRecord(String customer_material_issue_no,
			int customer_material_issue_version, int company_id, String deleted_by);


	@Query(value = "FROM CPtCustomerMaterialIssueMasterModel model WHERE model.customer_material_issue_no = ?1 and model.customer_material_issue_version = ?2 and model.financial_year = ?3 and model.company_id= ?4")
	Map<String, Object> FnShowCustomerMaterialIssueMasterRecord(String customer_material_issue_no,
			int customer_material_issue_version, String financial_year, int company_id);

	@Query(value = "SELECT * FROM pt_customer_material_issue_master  WHERE customer_material_issue_no = ?1 and customer_material_issue_version = ?2 and financial_year = ?3 and company_id= ?4 and is_delete = 0", nativeQuery = true)
	Map<String, Object> FnShowCustomerMaterialIssueMasterRecords(String customer_material_issue_no,
			int customer_material_issue_version, String financial_year, int company_id);


}
