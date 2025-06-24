package com.erp.PtCustomerMaterialIssueMaster.Repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.erp.PtCustomerMaterialIssueMaster.Model.CPtCustomerMaterialIssueDetailsModel;

public interface IPtCustomerMaterialIssueDetailsRepository extends JpaRepository<CPtCustomerMaterialIssueDetailsModel, Integer>{

	

	@Modifying
	@Transactional
	@Query(value = "update pt_customer_material_issue_details set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where customer_material_issue_no = ?1 and financial_year = ?2 and company_id = ?3 and is_delete =0", nativeQuery = true)
	void updateStatus(String customer_material_issue_no, String financial_year, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update pt_customer_material_issue_details set is_delete = 1 , deleted_by =?4, deleted_on = CURRENT_TIMESTAMP() where customer_material_issue_no = ?1 and customer_material_issue_version = ?2 and company_id = ?3", nativeQuery = true)
	void FnDeleteCustomerMaterialIssueDetailsRecord(String customer_material_issue_no,
			int customer_material_issue_version, int company_id, String deleted_by);

}
