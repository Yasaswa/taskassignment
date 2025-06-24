package com.erp.DispatchSizedYarn.Repository;

import com.erp.DispatchSizedYarn.Model.CDtSizedYarnMasterModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface IDtSizedYarnModelRepository extends JpaRepository<CDtSizedYarnMasterModel, Integer> {

//    @Query(value = "select wpo.sales_order_no,wpo.customer_order_no ,wpo.product_material_id ,wpo.t_ends, wpo.product_material_name, somt.customer_id,somt.agent_id, somt.customer_contacts_ids,somt.customer_state_id,somt.customer_city_id,somt.consignee_id,somt.consignee_state_id,somt.consignee_city_id,sotd.material_weight,sotd.material_rate from xtv_warping_production_order wpo left join mt_sales_order_master_trading somt on somt.sales_order_no = wpo.sales_order_no and somt.customer_order_no = wpo.customer_order_no and somt.is_delete = 0 left join mt_sales_order_details_trading sotd on sotd.sales_order_no = wpo.sales_order_no and sotd.customer_order_no = wpo.customer_order_no and sotd.product_material_id = wpo.product_material_id and sotd.is_delete = 0 where wpo.company_id = :companyId and wpo.set_no = :setNo and wpo.is_delete = 0", nativeQuery = true)
//    Map<String, Object> FnGetDispatchSizedYarnMasterData(@Param("companyId") int companyId, @Param("setNo") String setNo);

    @Query(value = "SELECT somt.customer_id, somt.agent_id, somt.customer_contacts_ids, somt.customer_state_id, somt.customer_city_id, somt.consignee_id, somt.consignee_state_id, somt.consignee_city_id, sotd.material_weight, sotd.material_rate, sotd.product_material_id FROM mt_sales_order_master_trading somt LEFT JOIN mt_sales_order_details_trading sotd ON sotd.is_delete = 0 AND somt.customer_order_no = sotd.customer_order_no WHERE somt.company_id = 2 AND somt.is_delete = 0 AND sotd.customer_order_no IN (:customerOrderNos) AND sotd.product_material_id IN (:productMaterialIds)", nativeQuery = true)
    List<Map<String, Object>> FnGetDispatchSizedYarnMasterData(
            @Param("productMaterialIds") List<String> productMaterialIds,
            @Param("customerOrderNos") List<String> customerOrderNos
    );

    @Query(value = "Select payment_terms_name from mt_sales_order_payment_terms_trading msoptt where msoptt.customer_order_no = ?1 AND msoptt.is_delete = 0 AND msoptt.company_id = ?2", nativeQuery = true)
    List<String> FNGetPaymentTermDetails(String customer_order_no, Integer company_id);

    @Query(value = "SELECT mdcsym.dispatch_challan_no FROM mt_dispatch_challan_sized_yarn_master mdcsym WHERE mdcsym.company_id = ?1 AND mdcsym.job_type_id = ?2 ORDER BY mdcsym.dispatch_challan_sized_yarn_id DESC LIMIT 1", nativeQuery = true)
    String FnGenerateSizedYarnChallanNo(Integer company_id, Integer job_type_id);


    //    @Query(value = "SELECT SUM(COALESCE(simid.issue_weight, 0)) AS issued_weight, SUM(COALESCE(simid.issue_quantity, 0)) AS issued_quantity, (SELECT SUM(COALESCE(net_weight, 0)) FROM xt_weaving_production_warping_bottom_details WHERE set_no = :set_no AND is_delete = 0) AS net_weight FROM st_material_issue_batch_wise simid WHERE simid.set_no = :set_no AND simid.company_id = :company_id AND simid.is_delete = 0 AND simid.issue_status IN ('MI', 'I')", nativeQuery = true)
//    Map<String, Object> FnGetIssuedWtQty(@Param("company_id") Integer company_id, @Param("set_no") String set_no);
    @Query(value = "SELECT SUM(COALESCE(simid.issue_weight, 0)) AS issued_weight, SUM(COALESCE(simid.issue_quantity, 0)) AS issued_quantity, (SELECT SUM(COALESCE(net_weight, 0)) FROM xt_weaving_production_warping_bottom_details WHERE set_no = :set_no AND is_delete = 0) AS net_weight FROM st_material_issue_batch_wise simid WHERE simid.set_no = :set_no AND simid.company_id = :company_id AND simid.is_delete = 0 AND simid.issue_status IN ('MI', 'I') AND simid.issue_return_status = 'P' ", nativeQuery = true)
    Map<String, Object> FnGetIssuedWtQty(@Param("company_id") Integer company_id, @Param("set_no") String set_no);

    @Query(value = "SELECT DISTINCT payment_terms_name " +
            "FROM mtv_sales_order_payment_terms_trading " +
            "WHERE customer_order_no IN (:customer_order_nos) " +
            "AND is_delete = 0", nativeQuery = true)
    List<String> findPaymentTermsByCustomerOrderNos(@Param("customer_order_nos") List<String> customerOrderNos);


}
