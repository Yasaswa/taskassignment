package com.erp.MtDispatchChallanDetails.Repository;

import com.erp.MtDispatchChallanDetails.Model.CMtDispatchChallanBatchwiseTradingViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IMtDispatchChallanBatchwiseTradingViewRepository extends JpaRepository<CMtDispatchChallanBatchwiseTradingViewModel, Integer> {


	@Query(value = "from CMtDispatchChallanBatchwiseTradingViewModel model where model.is_delete = 0 and model.dispatch_challan_no = ?1 and model.dispatch_challan_version = ?2 and model.company_id = ?3")
	List<CMtDispatchChallanBatchwiseTradingViewModel> FnShowDispatchChallanBatchwise(String dispatch_challan_no,
	                                                                                 Integer dispatch_challan_version, int parseInt);


	@Query(value = "from CMtDispatchChallanBatchwiseTradingViewModel model where model.is_delete = 0 and model.dispatch_challan_no = ?1 and model.dispatch_challan_version = ?2 and model.company_id = ?3")
	List<CMtDispatchChallanBatchwiseTradingViewModel> FnShowDispatchChallanBatchwiseRecords(String dispatch_challan_no,
	                                                                                        int dispatch_challan_version, int company_id);

}
