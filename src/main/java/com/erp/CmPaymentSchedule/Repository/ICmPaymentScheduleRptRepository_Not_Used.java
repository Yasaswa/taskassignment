package com.erp.CmPaymentSchedule.Repository;

import com.erp.CmPaymentSchedule.Model.CCmPaymentScheduleRptModel_Not_Used;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ICmPaymentScheduleRptRepository_Not_Used extends JpaRepository<CCmPaymentScheduleRptModel_Not_Used, String> {

//	 @Query(value ="FROM CCmPaymentScheduleRptModel model")
//	 Page<List<Map<String, Object>>> FnShowAllReportRecords(Pageable pageable, int company_id);


}
