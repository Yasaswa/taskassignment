package com.erp.XmProductionProcess.Repository;

import com.erp.XmProductionProcess.Model.CXmProductionProcessViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;


public interface IXmProductionProcessViewRepository extends JpaRepository<CXmProductionProcessViewModel, Integer> {

	@Query(value = "FROM CXmProductionProcessViewModel model where model.is_delete = 0 and model.company_id = ?1 order by model.production_process_id Desc")
	Page<CXmProductionProcessViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id);

	@Query(value = "FROM CXmProductionProcessViewModel model where model.is_delete =0 and model.production_process_id = ?1 and model.company_id = ?2 order by model.production_process_id Desc")
	Page<CXmProductionProcessViewModel> FnShowParticularRecord(int production_process_id, Pageable pageable, int company_id);


	@Query(value = "Select * From xmv_production_holiday_rpt", nativeQuery = true)
	Page<List<Map<String, Object>>> FnShowAllReportRecords(Pageable pageable);


}
