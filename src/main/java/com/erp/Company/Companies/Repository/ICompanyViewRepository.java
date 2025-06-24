package com.erp.Company.Companies.Repository;

import com.erp.Company.Companies.Model.CCompanyViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface ICompanyViewRepository extends JpaRepository<CCompanyViewModel, Long> {

	@Query(value = "FROM CCompanyViewModel cvm")
	Page<CCompanyViewModel> findAllCompany(Pageable pageable);

	@Query(value = "FROM CCompanyViewModel cvm where cvm.is_delete = 0 order by cvm.company_id Desc")
	Page<CCompanyViewModel> FnShowAllActiveRecords(Pageable pageable);

	@Query(value = "FROM CCompanyViewModel cvm  where cvm.is_delete =0 and cvm.company_id = ?1")
	CCompanyViewModel FnShowParticularRecord(int company_id);

	@Query(value = "FROM CCompanyViewModel cvm order by cvm.company_id Desc")
	ArrayList<CCompanyViewModel> FnShowAllRecordsToExport();

	@Query(value = "Select * From cmv_company_rpt", nativeQuery = true)
	Page<List<Map<String, Object>>> FnShowAllReportRecords(Pageable pageable);

}
