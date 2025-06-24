package com.erp.Shift.Repository;

import com.erp.Shift.Model.CShiftViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;

public interface IShiftViewRepository extends JpaRepository<CShiftViewModel, Long> {

	@Query(value = "Select * FROM cmv_shift where company_id = ?1  order by shift_id Desc", nativeQuery = true)
	Page<CShiftViewModel> FnShowAllRecords(Pageable pageable, int company_id);

	@Query(value = "Select * FROM  cmv_shift where is_delete =0 and company_id = ?1 order by shift_id Desc", nativeQuery = true)
	Page<CShiftViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id);

	@Query(value = "Select * FROM  cmv_shift where is_delete =0 and shift_id = ?1", nativeQuery = true)
	CShiftViewModel FnShowParticularRecordForUpdate(int shift_id);

	@Query(nativeQuery = true, value = "Select * FROM  cmv_shift where is_delete =0 and shift_id = ?1 and shift_id = ?1 and shift_id = ?1 ")
	CShiftViewModel FnShowParticularRecord(int company_id, int company_branch_id);

	@Query(value = "SELECT * FROM cmv_shift_rpt", nativeQuery = true)
	Page<Map<String, Object>> FnShowAllReportRecords(Pageable pageable);
}
