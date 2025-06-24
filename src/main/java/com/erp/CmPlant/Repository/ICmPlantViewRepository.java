package com.erp.CmPlant.Repository;

import com.erp.CmPlant.Model.CCmPlantViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface ICmPlantViewRepository extends JpaRepository<CCmPlantViewModel, Integer> {

	@Query(value = "FROM CCmPlantViewModel model where model.is_delete = 0 and model.company_id = ?1 order by model.plant_id Desc")
	Page<CCmPlantViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id);

	@Query(value = "FROM CCmPlantViewModel model where model.is_delete =0 and model.plant_id = ?1 and model.company_id = ?2 order by model.plant_id Desc")
	Page<CCmPlantViewModel> FnShowParticularRecord(int plant_id, int company_id, Pageable pageable);

	@Query(value = "select * FROM cmv_plant_rpt", nativeQuery = true)
	Page<List<Map<String, Object>>> FnShowAllReportRecords(Pageable pageable, int company_id);

}
