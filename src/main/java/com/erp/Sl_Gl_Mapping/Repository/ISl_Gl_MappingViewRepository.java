package com.erp.Sl_Gl_Mapping.Repository;

import com.erp.Sl_Gl_Mapping.Model.CSl_Gl_MappingViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;

public interface ISl_Gl_MappingViewRepository extends JpaRepository<CSl_Gl_MappingViewModel, Integer> {

	@Query(value = "Select * FROM  fmv_sl_gl_mapping order by sl_gl_mapping_id Desc", nativeQuery = true)
	Page<CSl_Gl_MappingViewModel> FnShowAllRecords(Pageable pageable);

	@Query(value = "Select * FROM  fmv_sl_gl_mapping where is_delete =0  order by sl_gl_mapping_id Desc", nativeQuery = true)
	Page<CSl_Gl_MappingViewModel> FnShowAllActiveRecords(Pageable pageable);

	@Query(value = "Select * FROM  fmv_sl_gl_mapping where is_delete =0 and sl_gl_mapping_id = ?1", nativeQuery = true)
	CSl_Gl_MappingViewModel FnShowParticularRecordForUpdate(int sl_gl_mapping_id);

	@Query(nativeQuery = true, value = "Select * FROM  fmv_sl_gl_mapping where is_delete =0 and company_id = ?1 and schedule_ledger_id = ?2")
	Page<CSl_Gl_MappingViewModel> FnShowParticularRecord(int company_id, int schedule_ledger_id, Pageable pageable);

	@Query(value = "SELECT * FROM hmv_sl_gl_mapping_rpt", nativeQuery = true)
	Map<String, Object> FnShowAllReportRecords();
}
