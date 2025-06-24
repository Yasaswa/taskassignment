package com.erp.StIndentDetails.Repository;

import com.erp.StIndentDetails.Model.CStIndentSchedulesViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IStIndentSchedulesViewRepository extends JpaRepository<CStIndentSchedulesViewModel, Integer> {

	@Query(value = "FROM CStIndentSchedulesViewModel model where model.is_delete = 0 and model.company_id = ?1 order by model.indent_schedule_id Desc")
	Page<CStIndentSchedulesViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id);

	@Query(value = "FROM CStIndentSchedulesViewModel model where model.is_delete =0 and model.indent_schedule_id = ?1 and model.company_id = ?2 order by model.indent_schedule_id Desc")
	Page<CStIndentSchedulesViewModel> FnShowParticularRecord(int indent_schedule_id, Pageable pageable, int company_id);

	@Query(value = "Select * From stv_indent_schedules where is_delete = 0 and indent_no = ?1 and indent_version = ?2 and company_id = ?3", nativeQuery = true)
	List<CStIndentSchedulesViewModel> FnShowIndentSchedules(String indent_no, int indent_version, int company_id);

}
