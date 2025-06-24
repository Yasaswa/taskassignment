package com.erp.XmProductionWastageTypes.Repository;

import com.erp.XmProductionWastageTypes.Model.CXmProductionWastageTypesViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;


public interface IXmProductionWastageTypesViewRepository extends JpaRepository<CXmProductionWastageTypesViewModel, Integer> {

	@Query(value = "FROM CXmProductionWastageTypesViewModel model where model.is_delete = 0 and model.company_id = ?1 order by model.production_wastage_types_id Desc")
	Page<CXmProductionWastageTypesViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id);

	@Query(value = "FROM CXmProductionWastageTypesViewModel model where model.is_delete =0 and model.production_wastage_types_id = ?1 and model.company_id = ?2 order by model.production_wastage_types_id Desc")
	Page<CXmProductionWastageTypesViewModel> FnShowParticularRecord(int production_wastage_types_id, Pageable pageable, int company_id);

	@Query(value = "SELECT * FROM xmv_production_wastage_types_rpt", nativeQuery = true)
	Page<List<Map<String, Object>>> FnShowAllReportRecords(Pageable pageable);


}
