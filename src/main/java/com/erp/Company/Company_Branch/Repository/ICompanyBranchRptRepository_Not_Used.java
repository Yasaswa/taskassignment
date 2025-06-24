package com.erp.Company.Company_Branch.Repository;

import com.erp.Company.Company_Branch.Model.CCompanyBranchRptModel_Not_Used;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ICompanyBranchRptRepository_Not_Used extends JpaRepository<CCompanyBranchRptModel_Not_Used, String> {

//	 @Query(value =" FROM CCompanyBranchRptModel cbrm")
//	 Page<CCompanyBranchRptModel> FnShowAllReportRecords(Pageable pageable);

}
