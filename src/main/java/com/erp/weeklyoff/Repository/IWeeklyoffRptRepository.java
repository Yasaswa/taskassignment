package com.erp.weeklyoff.Repository;

import com.erp.weeklyoff.Model.CWeeklyoffRptModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IWeeklyoffRptRepository extends JpaRepository<CWeeklyoffRptModel, String> {

	@Query(value = "SELECT * FROM hmv_weeklyoff_rpt", nativeQuery = true)
	Page<CWeeklyoffRptModel> FnShowAllReportRecords(Pageable pageable);
}
