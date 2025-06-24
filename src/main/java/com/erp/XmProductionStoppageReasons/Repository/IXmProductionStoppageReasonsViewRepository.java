package com.erp.XmProductionStoppageReasons.Repository;

import com.erp.XmProductionStoppageReasons.Model.CXmProductionStoppageReasonsViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;


public interface IXmProductionStoppageReasonsViewRepository extends JpaRepository<CXmProductionStoppageReasonsViewModel, Integer> {

	@Query(value = "FROM CXmProductionStoppageReasonsViewModel model where model.is_delete = 0 and model.company_id = ?1 order by model.production_stoppage_reasons_id Desc")
	Page<CXmProductionStoppageReasonsViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id);

	@Query(value = "FROM CXmProductionStoppageReasonsViewModel model where model.is_delete =0 and model.production_stoppage_reasons_id = ?1 and model.company_id = ?2 order by model.production_stoppage_reasons_id Desc")
	Page<CXmProductionStoppageReasonsViewModel> FnShowParticularRecord(int production_stoppage_reasons_id, Pageable pageable, int company_id);


	@Query(value = "SELECT * FROM xmv_production_stoppage_reasons_rpt", nativeQuery = true)
	Page<List<Map<String, Object>>> FnShowAllReportRecords(Pageable pageable);


}
