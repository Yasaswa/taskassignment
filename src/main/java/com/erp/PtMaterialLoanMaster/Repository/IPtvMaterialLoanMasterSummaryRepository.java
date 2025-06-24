package com.erp.PtMaterialLoanMaster.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.erp.PtMaterialLoanMaster.Model.CPtvMaterialLoanMasterSummaryModel;

import java.util.Map;


public interface IPtvMaterialLoanMasterSummaryRepository extends JpaRepository<CPtvMaterialLoanMasterSummaryModel, Integer> {

	@Query(value = "FROM CPtvMaterialLoanMasterSummaryModel model where model.is_delete =0 and model.loan_no = ?1")
	CPtvMaterialLoanMasterSummaryModel FnShowParticularRecordForUpdate(int loan_no
			, int company_id
	);

	@Query(value = "SELECT * FROM ptv_material_loan_master_summary  WHERE loan_no = ?1 and loan_version = ?2 and financial_year = ?3", nativeQuery = true)
	Map<String, Object> FnShowLoanIssueMasterRecord(String loanNo, int loanVersion, String financialYear
//			, int companyId
	);
}
