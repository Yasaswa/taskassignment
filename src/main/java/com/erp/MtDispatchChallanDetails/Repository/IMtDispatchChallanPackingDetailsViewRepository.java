package com.erp.MtDispatchChallanDetails.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.erp.MtDispatchChallanDetails.Model.CMtDispatchChallanPackingDetailsViewModel;

public interface IMtDispatchChallanPackingDetailsViewRepository extends JpaRepository<CMtDispatchChallanPackingDetailsViewModel, Integer>{

	
	@Query(value = "FROM CMtDispatchChallanPackingDetailsViewModel model WHERE model.is_delete = false and model.dispatch_challan_no = ?1 and model.dispatch_challan_version = ?2 and model.company_id = ?3")
	List<CMtDispatchChallanPackingDetailsViewModel> FnShowDispatchChallanPackingDetailsRecords(
			String dispatch_challan_no, int dispatch_challan_version, int company_id);

}
