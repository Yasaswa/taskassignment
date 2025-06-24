package com.erp.Godown_Section_Beans.Repository;

import com.erp.Godown_Section_Beans.Model.CGodownSectionBeansRptModel_Not_Used;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IGodownSectionBeansRptRepository_Not_Used extends JpaRepository<CGodownSectionBeansRptModel_Not_Used, String> {

//	@Query(value ="SELECT * FROM cmv_godown_section_beans_rpt", nativeQuery = true)
//	Page<List<Map<String, Object>>> FnShowAllReportRecords(Pageable pageable);

}
