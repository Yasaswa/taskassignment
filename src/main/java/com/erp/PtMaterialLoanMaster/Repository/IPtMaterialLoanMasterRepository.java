package com.erp.PtMaterialLoanMaster.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.erp.PtMaterialLoanMaster.Model.CPtMaterialLoanMasterModel;

import javax.transaction.Transactional;


public interface IPtMaterialLoanMasterRepository extends JpaRepository<CPtMaterialLoanMasterModel, Integer> {

	@Query(value = "FROM CPtMaterialLoanMasterModel model where model.is_delete =0 and model.material_loan_master_id = ?1 and model.company_id = ?2")
	CPtMaterialLoanMasterModel FnShowParticularRecordForUpdate(int material_loan_master_id, int company_id);


	@Query(value = "SELECT * FROM pt_material_loan_master WHERE loan_no = ?1 and loan_version = ?2 and financial_year = ?3 and company_id= ?4 and is_delete = 0", nativeQuery = true)
	CPtMaterialLoanMasterModel getExistingRecord(String loanNo, Integer integer, String financialYear, int companyId);


	@Modifying
	@Transactional
	@Query(value = "update pt_material_loan_master set is_delete = 1, deleted_on = CURRENT_TIMESTAMP(), deleted_by = ?2 where loan_master_transaction_id = ?1 and is_delete = 0", nativeQuery = true)
	void deleteLoanIssueMaster(int loanMasterTransactionId, String userName);
}
