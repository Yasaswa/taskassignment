package com.erp.Godown_Section.Repository;

import com.erp.Godown_Section.Model.CGodownSectionViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;


public interface IGodownSectionViewRepository extends JpaRepository<CGodownSectionViewModel, Long> {

	@Query(value = "Select * FROM  cmv_godown_section order by godown_section_id Desc", nativeQuery = true)
	Page<CGodownSectionViewModel> FnShowAllRecords(Pageable pageable);

	@Query(value = "Select * FROM  cmv_godown_section where is_delete =0  order by godown_section_id Desc", nativeQuery = true)
	Page<CGodownSectionViewModel> FnShowAllActiveRecords(Pageable pageable);

	@Query(value = "Select * FROM  cmv_godown_section where is_delete =0 and godown_section_id = ?1", nativeQuery = true)
	CGodownSectionViewModel FnShowParticularRecordForUpdate(int godown_section_id);

	@Query(nativeQuery = true, value = "Select * FROM  cmv_godown_section where is_delete =0 and company_id = ?1 and godown_section_id = ?2")
	CGodownSectionViewModel FnShowParticularRecord(int company_id, int godown_section_id);

	@Query(value = "SELECT * FROM cmv_godown_section_rpt", nativeQuery = true)
	Page<List<Map<String, Object>>> FnShowAllReportRecords(Pageable pageable);

}
