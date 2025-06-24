package com.erp.MtDispatchChallanDetails.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.erp.MtDispatchChallanDetails.Model.CMtDispatchInspectionDetailsTradingViewModel;

public interface IMtDispatchInspectionDetailsTradingViewRepository extends JpaRepository<CMtDispatchInspectionDetailsTradingViewModel, Integer>{

	@Query(value = "FROM CMtDispatchInspectionDetailsTradingViewModel model WHERE model.is_delete = 0 and model.dispatch_challan_no = ?1 and model.dispatch_challan_version = ?2 and model.company_id = ?3")
	List<CMtDispatchInspectionDetailsTradingViewModel> FnShowInspectionDetailsTradingRecords(String dispatch_challan_no,
			int dispatch_challan_version, int company_id);

}
