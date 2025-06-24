package com.erp.XtWeavingProductionWarpingMaster.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.erp.XtWeavingProductionWarpingMaster.Model.CXtWeavingProductionWarpingDetailsViewModel;

public interface IXtWeavingProductionWarpingDetailsViewRepository extends JpaRepository<CXtWeavingProductionWarpingDetailsViewModel, Integer>{

	@Query(value = "FROM CXtWeavingProductionWarpingDetailsViewModel model WHERE model.is_delete = 0 and model.weaving_production_warping_master_id = ?1 and model.company_id = ?2 ORDER BY model.weaving_production_warping_master_id DESC, model.company_id ASC")
	List<CXtWeavingProductionWarpingDetailsViewModel> FnShowWeavingProductionWarpingDetailsRecordForUpdate(
			int weaving_production_warping_master_id, int company_id);

//	@Query(value = "SELECT sum(stoppage_time) from xtv_weaving_production_warping_details where warping_production_date = ?1",nativeQuery = true)
//	double FnShowStoppageTimeForShiftSummary(String warping_production_date);

	@Query(value = "SELECT * FROM xtv_weaving_production_warping_details where is_delete = 0 and  warping_production_date = ?1 and warping_production_status = 'A' and company_id = ?2",nativeQuery = true)
	List<CXtWeavingProductionWarpingDetailsViewModel> FnShowParticularWarpingShiftSummary(
			String warping_production_date, int company_id);
	
	@Query(value = "SELECT * FROM xtv_weaving_production_warping_details where is_delete = 0 and warping_production_date <= ?1 AND warping_production_status = 'A' and company_id = ?2",nativeQuery = true)
	List<CXtWeavingProductionWarpingDetailsViewModel> FnShowParticularWarpingPreviousShiftSummary(
			String warping_production_date, int company_id);
	
}
