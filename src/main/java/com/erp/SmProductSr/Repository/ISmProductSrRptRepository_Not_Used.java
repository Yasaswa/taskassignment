package com.erp.SmProductSr.Repository;

import com.erp.SmProductSr.Model.CSmProductSrRptModel_Not_Used;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISmProductSrRptRepository_Not_Used extends JpaRepository<CSmProductSrRptModel_Not_Used, String> {

//	@Query(value = "from CSmProductSrRptModel")
//	Page<CSmProductSrRptModel> FnShowAllReportRecords(Pageable pageable);

}
