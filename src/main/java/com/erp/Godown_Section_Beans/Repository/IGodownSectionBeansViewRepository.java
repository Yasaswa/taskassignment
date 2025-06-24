package com.erp.Godown_Section_Beans.Repository;

import com.erp.Godown_Section_Beans.Model.CGodownSectionBeansViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;


public interface IGodownSectionBeansViewRepository extends JpaRepository<CGodownSectionBeansViewModel, Long> {


	@Query(value = "Select * FROM  cmv_godown_section_beans order by godown_section_beans_id Desc", nativeQuery = true)
	Page<CGodownSectionBeansViewModel> FnShowAllRecords(Pageable pageable);

	@Query(value = "Select * FROM  cmv_godown_section_beans where is_delete =0  order by godown_section_beans_id Desc", nativeQuery = true)
	Page<CGodownSectionBeansViewModel> FnShowAllActiveRecords(Pageable pageable);

	@Query(value = "Select * FROM  cmv_godown_section_beans where is_delete =0 and godown_section_beans_id = ?1", nativeQuery = true)
	CGodownSectionBeansViewModel FnShowParticularRecordForUpdate(int godown_section_beans_id);

	@Query(nativeQuery = true, value = "Select * FROM  cmv_godown_section_beans where is_delete =0 and company_id = ?1 and godown_section_beans_id = ?2")
	CGodownSectionBeansViewModel FnShowParticularRecord(int company_id, int godown_section_beans_id);

	@Query(value = "SELECT * FROM cmv_godown_section_beans_rpt", nativeQuery = true)
	Page<List<Map<String, Object>>> FnShowAllReportRecords(Pageable pageable);

}
