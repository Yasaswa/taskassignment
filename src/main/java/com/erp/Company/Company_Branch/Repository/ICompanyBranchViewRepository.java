package com.erp.Company.Company_Branch.Repository;

import com.erp.Company.Company_Branch.Model.CCompanyBranchViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface ICompanyBranchViewRepository extends JpaRepository<CCompanyBranchViewModel, Long> {

	@Query(value = "FROM CCompanyBranchViewModel cbvm order by cbvm.company_branch_id Desc")
	Page<CCompanyBranchViewModel> FnShowAllRecords(Pageable pageable);

	@Query(value = "FROM CCompanyBranchViewModel cbvm where cbvm.is_delete =0  order by cbvm.company_branch_id Desc")
	Page<CCompanyBranchViewModel> FnShowAllActiveRecords(Pageable pageable);

	@Query(value = "FROM CCompanyBranchViewModel cbvm where cbvm.is_delete =0 and cbvm.company_id = ?1")
	Page<CCompanyBranchViewModel> FnShowParticularRecord(int company_id, Pageable pageable);

	@Query(value = "SELECT * FROM cmv_company_branch_rpt", nativeQuery = true)
	Page<List<Map<String, Object>>> FnShowAllReportRecords(Pageable pageable);


}
