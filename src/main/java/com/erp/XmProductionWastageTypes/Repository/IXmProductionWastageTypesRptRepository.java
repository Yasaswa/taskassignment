package com.erp.XmProductionWastageTypes.Repository;

import com.erp.XmProductionWastageTypes.Model.CXmProductionWastageTypesRptModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface IXmProductionWastageTypesRptRepository extends JpaRepository<CXmProductionWastageTypesRptModel, String> {

	@Query(value = "select * FROM xmv_production_wastage_types_rpt", nativeQuery = true)
	Page<CXmProductionWastageTypesRptModel> FnShowAllReportRecords(Pageable pageable, int company_id);
}
