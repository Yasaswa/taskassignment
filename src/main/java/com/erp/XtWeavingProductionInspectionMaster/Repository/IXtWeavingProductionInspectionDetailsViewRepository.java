package com.erp.XtWeavingProductionInspectionMaster.Repository;

import com.erp.XtWeavingProductionInspectionMaster.Model.CXtWeavingProductionInspectionDetailsViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IXtWeavingProductionInspectionDetailsViewRepository extends JpaRepository<CXtWeavingProductionInspectionDetailsViewModel, Integer> {

	@Query(value = "FROM CXtWeavingProductionInspectionDetailsViewModel model WHERE model.is_delete = 0 and model.weaving_production_inspection_master_id = ?1 and model.company_id = ?2 order by model.roll_no asc")
	List<CXtWeavingProductionInspectionDetailsViewModel> FnShowWVProductionInspectionDetailsRecordForUpdate(
			int weaving_production_inspection_master_id, int company_id);

	@Query(value = "SELECT * FROM xtv_weaving_production_inspection_details where is_delete = 0 and  inspection_production_date = ?1 and inspection_production_status = 'A' and company_id = ?2", nativeQuery = true)
	List<CXtWeavingProductionInspectionDetailsViewModel> FnShowParticularInspectionShiftSummary(
			String inspection_production_date, int company_id);

	@Query(value = "SELECT * FROM xtv_weaving_production_inspection_details where is_delete = 0 and  inspection_production_date <= ?1 and inspection_production_status = 'A' and company_id = ?2", nativeQuery = true)
	List<CXtWeavingProductionInspectionDetailsViewModel> FnShowPreviousInspectionShiftSummary(
			String inspection_production_date, int company_id);

	@Query(value = "select * FROM xtv_weaving_production_inspection_details model WHERE model.is_delete = 0 and model.weaving_production_inspection_master_id = ?1", nativeQuery = true)
	List<CXtWeavingProductionInspectionDetailsViewModel> FnShowWvProductionInspectionApprovedRecords(int weaving_production_inspection_master_id);


}
