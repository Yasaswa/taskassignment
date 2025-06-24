package com.erp.Company.Company_Bank.Repository;

import com.erp.Company.Company_Bank.Model.CCompanyBankModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface ICompanyBankRepository extends JpaRepository<CCompanyBankModel, Integer> {

	@Modifying
	@Transactional
	@Query(value = "update cm_company_banks set is_delete = 1, deleted_on = CURRENT_TIMESTAMP()  where company_bank_id=?1", nativeQuery = true)
	Object FnDeleteRecord(int company_bank_id);

	@Modifying
	@Transactional
	@Query(value = "update cm_company_banks set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where company_id=?1 and company_branch_id=?2 and is_delete = 0", nativeQuery = true)
	void updateCompanyBankActiveStatus(int company_id, int company_branch_id);

}
