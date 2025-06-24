package com.erp.StIndentIssueMaster.Repository;

import com.erp.PtPurchaseDetails.Model.CPtPurchaseOrderDetailsViewModel;
import com.erp.StIndentIssueMaster.Model.CStIndentIssueDetailsViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface IStIndentMaterialIssueDetailViewRepository extends JpaRepository<CStIndentIssueDetailsViewModel, Integer> {


	@Query(value = "SELECT * FROM stv_indent_material_issue_details WHERE issue_no = ?1 and financial_year = ?2 and company_id= ?3 and is_delete = 0", nativeQuery = true)
	List<Map<String, Object>> FnShowIndentMaterialIssueDetailRecords(String issue_no,
	                                                                 String financial_year, int company_id);

	
	@Query(value = "Select * FROM stv_indent_material_issue_details where is_delete =0 and issue_master_transaction_id = ?1", nativeQuery = true)
	List<CStIndentIssueDetailsViewModel> getAllDetails(int issue_master_transaction_id);

	@Query(value = "FROM CStIndentIssueDetailsViewModel model where model.is_delete = 0 and model.indent_no IN ?1 and model.company_id = ?2")
	List<CStIndentIssueDetailsViewModel> getAllExistingIndentDetails(List<String> getDistinctIndentNos, int companyId);
}
