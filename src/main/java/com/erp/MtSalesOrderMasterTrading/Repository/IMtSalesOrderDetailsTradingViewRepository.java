package com.erp.MtSalesOrderMasterTrading.Repository;

import com.erp.MtSalesOrderMasterTrading.Model.CMtSalesOrderDetailsTradingViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;


public interface IMtSalesOrderDetailsTradingViewRepository extends JpaRepository<CMtSalesOrderDetailsTradingViewModel, Integer> {

	@Query(value = "FROM CMtSalesOrderDetailsTradingViewModel model where model.is_delete = 0 and model.company_id = ?1 order by model.company_id Desc")
	Page<CMtSalesOrderDetailsTradingViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id);

	@Query(value = "FROM CMtSalesOrderDetailsTradingViewModel model where model.is_delete =0 and model.sales_order_details_transaction_id = ?1 and model.company_id = ?2 order by model.sales_order_details_transaction_id Desc")
	Page<CMtSalesOrderDetailsTradingViewModel> FnShowParticularRecord(int sales_order_details_transaction_id, Pageable pageable, int company_id);


	@Query(value = "FROM CMtSalesOrderDetailsTradingViewModel model where model.is_delete =0 and model.customer_order_no = ?1 and model.company_id = ?2 order by model.customer_order_no Desc")
	Page<CMtSalesOrderDetailsTradingViewModel> FnShowSalesOrderDetailsTradingCustomerRecord(String customer_order_no, Pageable pageable, int company_id);

	@Query(value = "FROM CMtSalesOrderDetailsTradingViewModel model where model.sales_order_no = ?1 and model.sales_order_version = ?2 and model.company_id = ?3 order by model.sr_no asc")
//	@Query(value = "SELECT * FROM mtv_sales_order_details_trading WHERE sales_order_no = ?1 and sales_order_version = ?2 and company_id= ?3 order by sr_no asc", nativeQuery = true)
	List<CMtSalesOrderDetailsTradingViewModel> FnSalesOrderDetailsRecords(String sales_order_no, int sales_order_version,
	                                                                      int company_id);

	@Query(value = "FROM CMtSalesOrderDetailsTradingViewModel model where model.is_delete =0 and model.customer_order_no = ?1 and model.company_id = ?2 order by model.sales_order_details_transaction_id Desc")
	Page<CMtSalesOrderDetailsTradingViewModel> FnShowParticularRecord(String customer_order_no, Pageable pageable,
	                                                                  int company_id);

//	@Query(value = "FROM CMtSalesOrderDetailsTradingViewModel model where model.is_delete=0 and model.company_id = ?1  AND model.customer_order_no IN ?2 AND model.sales_order_item_status NOT IN ('Z', 'C', 'R')")
//	List<CMtSalesOrderDetailsTradingViewModel> FnShowSalesOrderDetails(int company_id, List<String> customerOrderNosList);

	@Query(value = "FROM CMtSalesOrderDetailsTradingViewModel model where model.is_delete=0 and  model.customer_order_no IN ?1 and model.company_id = ?2 AND model.sales_order_item_status NOT IN ('Z', 'C', 'R')")
	List<CMtSalesOrderDetailsTradingViewModel> FnShowSalesOrderDetailsForDispatch(List<String> orderNumbersList, int company_id);

	@Query(value = "SELECT somt.*, sotd.agent_id , pac.product_packing_name as product_material_packing_name,pac.product_packing_id, " +
			"hsn.hsn_sac_code as product_material_hsn_sac_code,smp.product_fg_code As product_material_code, hsn.hsn_sac_rate as product_material_hsn_sac_rate," +
			"smc.product_rm_std_weight as product_material_std_weight " +
			"FROM mt_sales_order_details_trading somt " +
			"LEFT JOIN mt_sales_order_master_trading sotd " +
			"ON sotd.is_delete = 0 AND somt.customer_order_no = sotd.customer_order_no " +
			"LEFT JOIN cm_hsn_sac hsn " +
			"ON hsn.is_delete = 0 AND somt.product_material_hsn_code_id = hsn.hsn_sac_id " +
			"LEFT JOIN sm_product_rm_commercial smc " +
			"ON smc.is_delete = 0 AND somt.product_material_id = smc.product_rm_id " +
			"LEFT JOIN sm_product_fg smp " +
			"ON smp.is_delete = 0 AND somt.product_material_id = smp.product_fg_id " +
			"LEFT JOIN sm_product_packing pac " +
			"ON pac.is_delete = 0 AND smp.product_fg_packing_id = pac.product_packing_id " +
			"WHERE somt.company_id = 2 " +
			"AND somt.is_delete = 0 " +
			"AND somt.customer_order_no IN (:customerOrderNos)", nativeQuery = true)
	List<Map<String, Object>> FnShowSalesOrderDetails(
			@Param("customerOrderNos") List<String> customerOrderNos
	);


}
