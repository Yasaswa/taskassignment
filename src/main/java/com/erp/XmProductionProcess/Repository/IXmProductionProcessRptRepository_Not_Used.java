package com.erp.XmProductionProcess.Repository;

import com.erp.XmProductionProcess.Model.CXmProductionProcessRptModel_Not_Used;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IXmProductionProcessRptRepository_Not_Used extends JpaRepository<CXmProductionProcessRptModel_Not_Used, String> {

//	 @Query(value ="select * FROM xmv_production_process_rpt", nativeQuery = true)
//	 Page<CXmProductionProcessRptModel> FnShowAllReportRecords(Pageable pageable,  int company_id);


}
