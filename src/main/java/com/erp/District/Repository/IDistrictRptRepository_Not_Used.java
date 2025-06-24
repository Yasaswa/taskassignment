package com.erp.District.Repository;

import com.erp.District.Model.CDistrictRptModel_Not_Used;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDistrictRptRepository_Not_Used extends JpaRepository<CDistrictRptModel_Not_Used, String> {

//	@Query(value = "SELECT * FROM cmv_district_rpt", nativeQuery = true)
//	Page<Map<String, Object>> FnShowAllReportRecords(Pageable pageable);

}
