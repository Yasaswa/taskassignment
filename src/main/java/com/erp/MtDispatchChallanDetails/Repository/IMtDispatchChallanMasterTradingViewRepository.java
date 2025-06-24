package com.erp.MtDispatchChallanDetails.Repository;

import com.erp.MtDispatchChallanDetails.Model.CMtDispatchChallanBatchwiseTradingViewModel;
import com.erp.MtDispatchChallanDetails.Model.CMtDispatchChallanMasterTradingViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface IMtDispatchChallanMasterTradingViewRepository extends JpaRepository<CMtDispatchChallanMasterTradingViewModel, Integer> {


	@Query(value = "select * FROM mtv_dispatch_challan_master_trading_rpt", nativeQuery = true)
	Map<String, Object> FnShowAllSummaryReportRecords();
	
	
	@Query(value = "from CMtDispatchChallanMasterTradingViewModel model where model.is_delete = false and model.dispatch_challan_no = ?1 and model.dispatch_challan_version = ?2 and model.financial_year = ?3 and model.company_id = ?4")
	CMtDispatchChallanMasterTradingViewModel FnShowDispatchChallanMasterRecord(String dispatch_challan_no, int dispatch_challan_version, String financial_year, int company_id);

}
