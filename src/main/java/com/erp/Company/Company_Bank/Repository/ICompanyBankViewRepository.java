package com.erp.Company.Company_Bank.Repository;

import com.erp.Company.Company_Bank.Model.CCompanyBankViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ICompanyBankViewRepository extends JpaRepository<CCompanyBankViewModel, Long> {

	@Query(nativeQuery = true, value = "Select * FROM cmv_company_banks  where is_delete =0 and company_bank_id = ?1")
	CCompanyBankViewModel FnShowParticularRecord(int bank_contact_id);


	@Query(value = "Select * FROM cmv_company_banks order by company_bank_id Desc", nativeQuery = true)
	Page<CCompanyBankViewModel> FnShowAllRecords(Pageable pageable);

	@Query(value = "Select * FROM cmv_company_banks where is_delete =0  order by company_bank_id Desc", nativeQuery = true)
	Page<CCompanyBankViewModel> FnShowAllActiveRecords(Pageable pageable);

	@Query(value = "Select * FROM cmv_company_banks where is_delete =0 and is_active = 1 and company_branch_id = ?1 and company_id = ?2", nativeQuery = true)
	List<CCompanyBankViewModel> FnShowParticularRecordForUpdate(int company_branch_id, int company_id, Pageable pageable);

	@Query(value = "Select * FROM cmv_company_banks where company_id =?1 and company_branch_id =?2", nativeQuery = true)
	List<CCompanyBankViewModel> checkRecordIfExist(int company_id, int company_branch_id);


}
