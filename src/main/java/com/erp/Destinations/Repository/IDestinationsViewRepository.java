package com.erp.Destinations.Repository;

import com.erp.Destinations.Model.CDestinationsViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;

public interface IDestinationsViewRepository extends JpaRepository<CDestinationsViewModel, Long> {

	@Query(value = "Select * FROM  cmv_destination order by destination_id Desc", nativeQuery = true)
	Page<CDestinationsViewModel> FnShowAllRecords(Pageable pageable);

	@Query(value = "Select * FROM  cmv_destination where is_delete =0  order by destination_id Desc", nativeQuery = true)
	Page<CDestinationsViewModel> FnShowAllActiveRecords(Pageable pageable);

	@Query(value = "Select * FROM  cmv_destination where is_delete =0 and destination_id = ?1", nativeQuery = true)
	CDestinationsViewModel FnShowParticularRecordForUpdate(int destination_id);

	@Query(nativeQuery = true, value = "Select * FROM  cmv_destination where is_delete =0 and company_id = ?1  and destination_id = ?2")
	CDestinationsViewModel FnShowParticularRecord(int company_id, int destination_id);

	@Query(value = "SELECT * FROM cmv_destination_rpt", nativeQuery = true)
	Page<Map<String, Object>> FnShowAllReportRecords(Pageable pageable);
}
