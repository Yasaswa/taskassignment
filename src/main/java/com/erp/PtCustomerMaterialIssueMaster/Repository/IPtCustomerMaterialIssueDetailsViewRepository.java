package com.erp.PtCustomerMaterialIssueMaster.Repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.erp.PtCustomerMaterialIssueMaster.Model.CPtCustomerMaterialIssueDetailsViewModel;

public interface IPtCustomerMaterialIssueDetailsViewRepository extends JpaRepository<CPtCustomerMaterialIssueDetailsViewModel, Integer>{

	
	
	@Query(value = "SELECT * FROM ptv_customer_material_issue_details WHERE customer_material_issue_no = ?1 and customer_material_issue_version = ?2 and financial_year = ?3 and company_id= ?4 and is_delete = 0", nativeQuery = true)
	List<Map<String, Object>> FnShowCustomerMaterialIssueDetailRecords(String customer_material_issue_no,
			int customer_material_issue_version, String financial_year, int company_id);

	
	
//	@Query(value = "SELECT * FROM ptv_customer_material_issue_details WHERE customer_material_issue_no = ?1 and customer_material_issue_version = ?2 and financial_year = ?3 and company_id= ?4 and is_delete = 0", nativeQuery = true)
	
	@Query(value = "FROM CPtCustomerMaterialIssueDetailsViewModel model WHERE model.customer_material_issue_no = ?1 and model.customer_material_issue_version = ?2 and model.financial_year = ?3 and model.company_id= ?4 and model.is_delete = 0")
	List<CPtCustomerMaterialIssueDetailsViewModel> FnShowCustomerMaterialIssueDetailsRecords(String customer_material_issue_no,
			int customer_material_issue_version, String financial_year, int company_id);

	@Query(value = "Select * FROM ptv_customer_material_issue_details where is_delete =0 and customer_material_issue_master_transaction_id = ?1", nativeQuery = true)
	List<CPtCustomerMaterialIssueDetailsViewModel> getAllDetails(int customer_material_issue_master_transaction_id);

}
