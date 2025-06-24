package com.erp.District.Repository;

import com.erp.District.Model.CDistrictViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;


public interface IDistrictViewRepository extends JpaRepository<CDistrictViewModel, Long> {

	@Query(value = "Select * FROM  cmv_district order by district_id Desc", nativeQuery = true)
	Page<CDistrictViewModel> FnShowAllRecords(Pageable pageable);

	@Query(value = "Select * FROM  cmv_district where is_delete =0  order by district_id Desc", nativeQuery = true)
	Page<CDistrictViewModel> FnShowAllActiveRecords(Pageable pageable);

	@Query(value = "Select * FROM  cmv_district where is_delete =0 and district_id = ?1", nativeQuery = true)
	CDistrictViewModel FnShowParticularRecordForUpdate(int district_id);

	@Query(nativeQuery = true, value = "Select * FROM  cmv_district where is_delete =0 and company_id = ?1 and district_id = ?2")
	CDistrictViewModel FnShowParticularRecord(int company_id, int district_id);

	@Query(value = "SELECT * FROM cmv_district_rpt", nativeQuery = true)
	Page<Map<String, Object>> FnShowAllReportRecords(Pageable pageable);

}
