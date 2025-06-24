package com.erp.XmProductionHoliday.Repository;

import com.erp.XmProductionHoliday.Model.CXmProductionHolidayRptModel_Not_Used;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IXmProductionHolidayRptRepository_Not_Used extends JpaRepository<CXmProductionHolidayRptModel_Not_Used, Integer> {

//	@Query(value ="select * FROM xmv_production_holiday_rpt", nativeQuery = true)
//	Page<CXmProductionHolidayRptModel> FnShowAllReportRecords(Pageable pageable, int company_id);

}
