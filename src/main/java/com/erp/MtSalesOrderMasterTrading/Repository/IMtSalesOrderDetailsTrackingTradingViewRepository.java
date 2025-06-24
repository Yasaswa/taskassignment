package com.erp.MtSalesOrderMasterTrading.Repository;

import com.erp.MtSalesOrderMasterTrading.Model.CMtSalesOrderDetailsTrackingTradingViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface IMtSalesOrderDetailsTrackingTradingViewRepository extends JpaRepository<CMtSalesOrderDetailsTrackingTradingViewModel, Integer> {

	@Query(value = "SELECT * FROM mtv_sales_order_details_tracking_trading  WHERE sales_order_no = ?1 and sales_order_version = ?2  and company_id= ?3 order by sr_no asc", nativeQuery = true)
	List<Map<String, Object>> FnSalesOrderDetailsTrackingRecords(String sales_order_no, int sales_order_version,
	                                                             int company_id);

	@Query(value = "from CMtSalesOrderDetailsTrackingTradingViewModel model where model.is_delete = 0 and model.sales_order_no = ?1 and model.sales_order_version = ?2 and model.company_id = ?3 order by sr_no asc")
	List<CMtSalesOrderDetailsTrackingTradingViewModel> FnShowSalesOrderTrackingTrading(String sales_order_no,
	                                                                                   int sales_order_version, int company_id);

}
