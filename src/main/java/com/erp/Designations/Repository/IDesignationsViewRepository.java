package com.erp.Designations.Repository;

import com.erp.Designations.Model.CDesignationsViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;

public interface IDesignationsViewRepository extends JpaRepository<CDesignationsViewModel, Long> {

	@Query(value = "Select * FROM  cmv_designation order by designation_id Desc", nativeQuery = true)
	Page<CDesignationsViewModel> FnShowAllRecords(Pageable pageable);

	@Query(value = "Select * FROM  cmv_designation where is_delete =0  order by designation_id Desc", nativeQuery = true)
	Page<CDesignationsViewModel> FnShowAllActiveRecords(Pageable pageable);

	@Query(value = "Select * FROM  cmv_designation where is_delete =0 and designation_id = ?1", nativeQuery = true)
	CDesignationsViewModel FnShowParticularRecordForUpdate(int designation_id);

	@Query(nativeQuery = true, value = "Select * FROM  cmv_designation where is_delete =0 and company_id = ?1 and designation_id = ?2")
	CDesignationsViewModel FnShowParticularRecordForUpdate(int company_id, int designation_id);

	@Query(value = "SELECT * FROM cmv_designation_rpt", nativeQuery = true)
	Page<Map<String, Object>> FnShowAllReportRecords(Pageable pageable);
}
