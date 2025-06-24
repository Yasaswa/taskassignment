package com.erp.weeklyoff.Repository;

import com.erp.weeklyoff.Model.CWeeklyoffViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IWeeklyoffViewRepository extends JpaRepository<CWeeklyoffViewModel, Long> {

	@Query(value = "Select * FROM  hmv_weeklyoff order by weeklyoff_id Desc", nativeQuery = true)
	Page<CWeeklyoffViewModel> FnShowAllRecords(Pageable pageable);

	@Query(value = "Select * FROM  hmv_weeklyoff where is_delete =0  order by weeklyoff_id Desc", nativeQuery = true)
	Page<CWeeklyoffViewModel> FnShowAllActiveRecords(Pageable pageable);

	@Query(value = "Select * FROM  hmv_weeklyoff where is_delete =0 and weeklyoff_id = ?1", nativeQuery = true)
	CWeeklyoffViewModel FnShowParticularRecordForUpdate(int weeklyoff_id);

	@Query(nativeQuery = true, value = "Select * FROM  hmv_weeklyoff where is_delete =0 and company_id = ?1 and company_branch_id = ?2 and weeklyoff_id = ?3")
	CWeeklyoffViewModel FnShowParticularRecord(int company_id, int company_branch_id, int weeklyoff_id);
}
