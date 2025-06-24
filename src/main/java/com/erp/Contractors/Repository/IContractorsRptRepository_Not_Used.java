package com.erp.Contractors.Repository;

import com.erp.Contractors.Model.CContractorsRptModel_Not_Used;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IContractorsRptRepository_Not_Used extends JpaRepository<CContractorsRptModel_Not_Used, String> {

//	@Query(value = "SELECT * FROM cmv_contractor_rpt", nativeQuery = true)
//	Page<CContractorsRptModel> FnShowAllReportRecords(Pageable pageable);

}
