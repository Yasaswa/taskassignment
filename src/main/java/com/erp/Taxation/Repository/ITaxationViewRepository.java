package com.erp.Taxation.Repository;

import com.erp.Taxation.Model.CTaxationViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface ITaxationViewRepository extends JpaRepository<CTaxationViewModel, Long> {

	@Query(value = "Select * FROM  cmv_taxation order by taxation_id Desc", nativeQuery = true)
	Page<CTaxationViewModel> FnShowAllRecords(Pageable pageable);

	@Query(value = "Select * FROM  cmv_taxation where is_delete =0  order by taxation_id Desc", nativeQuery = true)
	Page<CTaxationViewModel> FnShowAllActiveRecords(Pageable pageable);

	@Query(value = "Select * FROM  cmv_taxation where is_delete =0 and taxation_id = ?1", nativeQuery = true)
	CTaxationViewModel FnShowParticularRecordForUpdate(int taxation_id);

	@Query(nativeQuery = true, value = "Select * FROM  cmv_taxation where is_delete =0 and company_id = ?1 and taxation_id = ?2")
	CTaxationViewModel FnShowParticularRecord(int company_id, int taxation_id);

	@Query(nativeQuery = true, value = "SELECT * FROM cmv_taxation_rpt")
	Page<List<Map<String, Object>>> FnShowAllReportRecords(Pageable pageable);


}
