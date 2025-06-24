package com.erp.CmPlant.Repository;

import com.erp.CmPlant.Model.CCmPlantRptModel_Not_Used;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICmPlantRptRepository_Not_Used extends JpaRepository<CCmPlantRptModel_Not_Used, Integer> {

//    @Query(value ="select * FROM cmv_plant_rpt", nativeQuery = true)
//	Page<List<Map<String, Object>>> FnShowAllReportRecords(Pageable pageable, int company_id);

}
	