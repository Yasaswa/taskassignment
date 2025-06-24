package com.erp.XmProductionStoppageReasons.Repository;

import com.erp.XmProductionStoppageReasons.Model.CXmProductionStoppageReasonsRptMode_Not_Used;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IXmProductionStoppageReasonsRptRepository_Not_Used extends JpaRepository<CXmProductionStoppageReasonsRptMode_Not_Used, String> {

//     @Query(value ="select * FROM xmv_production_stoppage_reasons_rpt", nativeQuery = true)
//	 Page<CXmProductionStoppageReasonsRptModel> FnShowAllReportRecords(Pageable pageable, int company_id);


}
