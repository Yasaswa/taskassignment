package com.erp.MtDispatchChallanDetails.Repository;

import com.erp.MtDispatchChallanDetails.Model.CMtDispatchChallanDetailsTradingViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface IMtDispatchChallanDetailsTradingViewRepository extends JpaRepository<CMtDispatchChallanDetailsTradingViewModel, Integer> {

	@Query(value = "FROM CMtDispatchChallanDetailsTradingViewModel model where model.is_delete =0 and model.dispatch_challan_details_transaction_id = ?1 and model.company_id = ?2 order by model.dispatch_challan_details_transaction_id Desc")
	Page<CMtDispatchChallanDetailsTradingViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id);

	@Query(value = "FROM CMtDispatchChallanDetailsTradingViewModel model where model.is_delete =0 and model.dispatch_challan_details_transaction_id = ?1 and model.company_id = ?2 order by model.dispatch_challan_details_transaction_id Desc")
	Page<CMtDispatchChallanDetailsTradingViewModel> FnShowParticularRecord(int dispatch_challan_details_transaction_id,
	                                                                       Pageable pageable, int company_id);

	@Query(value = "FROM CMtDispatchChallanDetailsTradingViewModel model where model.is_delete =0 and model.dispatch_challan_no IN ?1 and model.company_id = ?2")
	List<CMtDispatchChallanDetailsTradingViewModel> FnShowDispatchChallanDetails(List<String> challanNos, int company_id);

	@Query(value = "select * FROM mtv_dispatch_challan_details_trading_rpt", nativeQuery = true)
	Map<String, Object> FnShowAlldetailsReportRecords();
}
