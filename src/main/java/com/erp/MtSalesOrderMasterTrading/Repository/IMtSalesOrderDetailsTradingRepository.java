package com.erp.MtSalesOrderMasterTrading.Repository;

import com.erp.MtSalesOrderMasterTrading.Model.CMtSalesOrderDetailsTradingModel;
import com.erp.MtSalesOrderMasterTrading.Model.CMtSalesOrderDetailsTradingViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

public interface IMtSalesOrderDetailsTradingRepository extends JpaRepository<CMtSalesOrderDetailsTradingModel, Integer> {


    @Modifying
    @Transactional
    @Query(value = "update mt_sales_order_details_trading set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where  sales_order_no= ?1 and sales_order_version = ?2 and company_id = ?3 ", nativeQuery = true)
    void updateStatus(String sales_order_no, Integer sales_order_version, int company_id);

    @Query(value = "FROM CMtSalesOrderDetailsTradingModel model where model.sales_order_no = ?1 and model.sales_order_version = ?2 and model.company_id = ?3 and is_delete = 0 order by model.sr_no asc")
    List<CMtSalesOrderDetailsTradingModel> FnSalesOrderDetailsRecords(String sales_order_no, int sales_order_version, int company_id);

    @Query(value = "FROM CMtSalesOrderDetailsTradingModel model where model.is_delete=0 and model.company_id = ?1 AND model.sales_order_no IN ?2")
    List<CMtSalesOrderDetailsTradingModel> FnShowSalesOrderDetailsBySONos(int company_id, List<String> salesOrderNosList);

    @Modifying
    @Transactional
    @Query(value = "UPDATE sm_product_fg SET product_fg_hsn_sac_code_id = ?2 WHERE product_fg_id = ?1 and is_delete = 0", nativeQuery = true)
    void updateHsnCodeForProductIds(String productMaterialId, Integer productMaterialHsnCodeId);

//	@Query(value = "FROM CMtSalesOrderDetailsTradingModel model where model.is_delete=0 and  model.customer_order_no IN ?1 and model.company_id = ?2 AND model.sales_order_item_status NOT IN ('Z', 'C', 'R')")
//	List<CMtSalesOrderDetailsTradingModel> FnShowSalesOrderDetailsForDispatch(List<String> orderNumbersList, int company_id);


    //	@Query(value = "SELECT somt.*,sotd.agent_id FROM mt_sales_order_details_trading somt" +
//			" LEFT JOIN mt_sales_order_master_trading sotd ON sotd.is_delete = 0 AND " +
//			"somt.customer_order_no = sotd.customer_order_no WHERE somt.company_id = 2" +
//			" AND somt.is_delete = 0 AND sotd.customer_order_no IN (:customerOrderNos) ", nativeQuery = true)
//	List<Map<String, Object>> FnShowSalesOrderDetailsForDispatch(
//			@Param("customerOrderNos") List<String> customerOrderNos
//
//	);
    @Query(value = "SELECT somt.*, sotd.agent_id " +
            "FROM mt_sales_order_details_trading somt " +
            "LEFT JOIN mt_sales_order_master_trading sotd " +
            "ON sotd.is_delete = 0 AND somt.customer_order_no = sotd.customer_order_no " +
            "WHERE somt.company_id = 2 " +
            "AND somt.is_delete = 0 " +
            "AND sotd.customer_order_no IN (:customerOrderNos)", nativeQuery = true)
    List<Map<String, Object>> FnShowSalesOrderDetailsForDispatch(
            @Param("customerOrderNos") List<String> customerOrderNos
    );

    @Modifying
    @Transactional
    @Query(value = "update mt_sales_order_details_trading set sales_order_item_status  = 'X',cancel_quantity = material_quantity, cancel_weight = material_weight, modified_by = ?4 , modified_on = CURRENT_TIMESTAMP() where sales_order_no= ?1 and sales_order_version = ?2 and company_id = ?3", nativeQuery = true)
    void cancelSalesDetails(String sales_order_no, int sales_order_version, int company_id, String deleted_by);
    @Query(value = "FROM CMtSalesOrderDetailsTradingModel model where model.is_delete=0 and model.company_id = ?1 AND model.customer_order_no = ?2")
    List<CMtSalesOrderDetailsTradingModel> FnShowSalesOrderDetailsByCustomerOrderNo(int companyId, String customerOrderNo);
}
