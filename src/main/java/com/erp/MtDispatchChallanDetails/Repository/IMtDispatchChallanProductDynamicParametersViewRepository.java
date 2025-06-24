package com.erp.MtDispatchChallanDetails.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.erp.MtDispatchChallanDetails.Model.CMtDispatchChallanProductDynamicParametersViewModel;


public interface IMtDispatchChallanProductDynamicParametersViewRepository extends JpaRepository<CMtDispatchChallanProductDynamicParametersViewModel, Integer> {

	
	@Query(value = "FROM CMtDispatchChallanProductDynamicParametersViewModel model WHERE model.is_delete = 0 and model.dispatch_challan_no = ?1 and model.dispatch_challan_version = ?2 and model.company_id = ?3")
	List<CMtDispatchChallanProductDynamicParametersViewModel> FnShowProductParametersRecords(String dispatch_challan_no,
			int dispatch_challan_version, int company_id);

}
