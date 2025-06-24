package com.erp.XtWeavingProductionSizingDetails.Repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.erp.XtWeavingProductionSizingDetails.Model.CXtWeavingProductionSizingDetailsViewModel;

public interface IXtWeavingProductionSizingDetailsViewRepository extends JpaRepository<CXtWeavingProductionSizingDetailsViewModel, Integer> {


	@Query(value = "FROM CXtWeavingProductionSizingDetailsViewModel model WHERE model.is_delete = 0 and model.weaving_production_sizing_master_id = ?1 and model.company_id = ?2")
	List<CXtWeavingProductionSizingDetailsViewModel> FnShowWVProductionSizingDetailsRecordForUpdate(
			int weaving_production_sizing_master_id, int company_id);

	
	
	@Query(value = "SELECT * FROM xtv_weaving_production_sizing_details where is_delete = 0 and  sizing_production_date = ?1 and sizing_production_status = 'A' and company_id = ?2",nativeQuery = true)
	List<CXtWeavingProductionSizingDetailsViewModel> FnShowParticularSizingShiftSummary(String sizing_production_date,
			int company_id);


	@Query(value = "SELECT * FROM xtv_weaving_production_sizing_details where is_delete = 0 and sizing_production_date <= ?1 AND sizing_production_status ='A' and company_id = ?2",nativeQuery = true)
	List<CXtWeavingProductionSizingDetailsViewModel> FnShowParticularSizingPreviousShiftSummary(
			String sizing_production_date, int company_id);



	@Query(value = "SELECT * FROM xtv_weaving_production_sizing_details where is_delete = 0 and weaving_production_set_no = ?1 AND company_id = ?2",nativeQuery = true)
	ArrayList<CXtWeavingProductionSizingDetailsViewModel> FnGetSizingProductionRecords(String set_no, int company_id);

}
