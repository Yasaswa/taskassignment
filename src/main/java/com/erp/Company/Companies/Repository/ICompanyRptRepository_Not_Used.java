package com.erp.Company.Companies.Repository;

import com.erp.Company.Companies.Model.CCompanyRptModel_Not_Used;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICompanyRptRepository_Not_Used extends JpaRepository<CCompanyRptModel_Not_Used, String> {

//	 @Query(value ="FROM CCompanyRptModel crm")
//	 Page<CCompanyRptModel> FnShowAllReportRecords(Pageable pageable);
}
