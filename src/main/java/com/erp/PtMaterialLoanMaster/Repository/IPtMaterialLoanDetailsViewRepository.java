package com.erp.PtMaterialLoanMaster.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.erp.PtMaterialLoanMaster.Model.CPtMaterialLoanDetailsViewModel;

import java.util.List;
import java.util.Map;


public interface IPtMaterialLoanDetailsViewRepository extends JpaRepository<CPtMaterialLoanDetailsViewModel, Integer> {

	@Query(value = "FROM CPtMaterialLoanDetailsViewModel model where model.is_delete = 0 and model.company_id = ?1 order by model.material_loan_details_id Desc")
	Page<CPtMaterialLoanDetailsViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id);

	@Query(value = "FROM CPtMaterialLoanDetailsViewModel model where model.is_delete =0 and model.material_loan_details_id = ?1 and model.company_id = ?2 order by model.material_loan_details_id Desc")
	Page<CPtMaterialLoanDetailsViewModel> FnShowParticularRecord(int material_loan_details_id, Pageable pageable, int company_id);


	@Query(value = "SELECT * FROM ptv_material_loan_details WHERE loan_no = ?1 and loan_version = ?2 and financial_year = ?3", nativeQuery = true)
	List<Map<String, Object>> FnShowLoanIssueDetailsRecords(String loanNo, int loanVersion, String financialYear
//			, int companyId
	);
}
