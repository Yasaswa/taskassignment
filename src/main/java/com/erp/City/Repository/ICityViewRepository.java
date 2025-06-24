package com.erp.City.Repository;

import com.erp.City.Model.CCityViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;

public interface ICityViewRepository extends JpaRepository<CCityViewModel, Long> {

	@Query(value = "Select * FROM cmv_city order by city_id Desc", nativeQuery = true)
	Page<CCityViewModel> FnShowAllRecords(Pageable pageable);

	@Query(value = "Select * FROM cmv_city where is_delete =0  order by city_id Desc", nativeQuery = true)
	Page<CCityViewModel> FnShowAllActiveRecords(Pageable pageable);

	@Query(value = "Select * FROM cmv_city where is_delete =0 and city_id = ?1", nativeQuery = true)
	CCityViewModel FnShowParticularRecordForUpdate(int city_id);

//	@Query(value = "Select * FROM cmv_city where is_delete =0 and company_id = ?1", nativeQuery = true)
//	Page<CCityViewModel> FnShowParticularRecord(int company_id, Pageable pageable);


//	@Query(nativeQuery = true, value = "Select * FROM cmv_city where is_delete =0 and company_id = ?1 and city_id = ?2")
//	CCityViewModel FnShowParticularRecord(int company_id, int city_id);

	/* Added By Dipti DB Testing 1.1 */

	@Query(value = "Select * FROM cmv_city where is_delete =0", nativeQuery = true)
	Page<CCityViewModel> FnShowParticularRecord(int company_id, Pageable pageable);

	@Query(nativeQuery = true, value = "Select * FROM cmv_city where is_delete =0 and city_id = ?2")
	CCityViewModel FnShowParticularRecord(int city_id);

	/* Added By Dipti DB Testing 1.1 */


	@Query(value = "SELECT * FROM cmv_city_rpt", nativeQuery = true)
	Page<Map<String, Object>> FnShowAllReportRecords(Pageable pageable);

}
