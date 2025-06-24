package com.erp.PtMaterialLoanMaster.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.erp.PtMaterialLoanMaster.Model.CPtMaterialLoanDetailsModel;

import javax.transaction.Transactional;


public interface IPtMaterialLoanDetailsRepository extends JpaRepository<CPtMaterialLoanDetailsModel, Integer> {

	@Query(value = "FROM CPtMaterialLoanDetailsModel model where model.is_delete =0 and model.material_loan_details_id = ?1 and model.company_id = ?2")
	CPtMaterialLoanDetailsModel FnShowParticularRecordForUpdate(int material_loan_details_id, int company_id);




	@Modifying
	@Transactional
	@Query(value = "update pt_material_loan_details set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where loan_no = ?1 and loan_version = ?2 and financial_year = ?3 and company_id = ?4", nativeQuery = true)
	void updateStatus(String loanNo, int loanVersion, String financialYear, int companyId);

	@Modifying
	@Transactional
	@Query(value = "update pt_material_loan_details set is_delete = 1, deleted_on = CURRENT_TIMESTAMP(), deleted_by = ?2 where loan_master_transaction_id = ?1 and is_delete = 0", nativeQuery = true)
	void deleteLoanIssueDetails(int loanMasterTransactionId, String userName);
}
