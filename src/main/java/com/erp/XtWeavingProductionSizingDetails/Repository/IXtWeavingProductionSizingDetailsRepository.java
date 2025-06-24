package com.erp.XtWeavingProductionSizingDetails.Repository;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.erp.XtWeavingProductionSizingDetails.Model.CXtWeavingProductionSizingDetailsModel;
import org.springframework.data.repository.query.Param;


public interface IXtWeavingProductionSizingDetailsRepository extends JpaRepository<CXtWeavingProductionSizingDetailsModel, Integer> {

    @Modifying
    @Transactional
    @Query(value = "update CXtWeavingProductionSizingDetailsModel model SET model.is_delete = 1 , model.deleted_by = ?2, model.deleted_on = CURRENT_TIMESTAMP() WHERE model.weaving_production_sizing_master_id = ?1")
    void FnDeleteProductionSizingDetailsRecord(int weaving_production_sizing_master_id, String deleted_by);


    @Modifying
    @Transactional
    @Query(value = "update CXtWeavingProductionSizingDetailsModel model SET model.is_delete = 1 , model.deleted_on = CURRENT_TIMESTAMP() WHERE model.weaving_production_sizing_master_id = ?1 AND company_id = ?2")
    void updateWeavingProductionSizingDetails(int weaving_production_sizing_master_id, int company_id);


    @Query(value = "FROM CXtWeavingProductionSizingDetailsModel model WHERE model.is_delete = 0 and weaving_production_set_no IN ?1")
    List<CXtWeavingProductionSizingDetailsModel> getAllWeavingProductionSizingDetails(List<String> weavingProductionSetNumbers);

    @Modifying
    @Transactional
    @Query(value = "update CXtBeamInwardsModel model SET model.beam_status = 'C', model.modified_on = CURRENT_TIMESTAMP(), model.modified_by = ?2 WHERE model.beam_inwards_id IN ?1")
    void FnUpdateBeamInwordStatusRecord(List<Integer> sizingBeamIds, String modifiedBy);


    @Query(value = "SELECT xwpsd.*, ce.employee_name as operator_name, xbit.beam_inward_type as sizing_beam_name FROM xt_weaving_production_sizing_details xwpsd left join cm_employee ce on ce.employee_id = xwpsd.production_operator_id and ce.is_delete = 0 left join xt_beam_inwards_table xbit on xbit.beam_inwards_id = xwpsd.sizing_beam_no and xbit.is_delete = 0 WHERE xwpsd.is_delete = 0 AND (:set_no IS NULL OR xwpsd.weaving_production_set_no = :set_no) AND (:yarn_count IS NULL OR xwpsd.yarn_count = :yarn_count) AND (:customer_order_no IS NULL OR xwpsd.customer_order_no = :customer_order_no) AND xwpsd.company_id = :company_id AND xwpsd.sizing_production_date BETWEEN :from_date AND :to_date ORDER BY xwpsd.sizing_production_date", nativeQuery = true)
    List<Map<String, Object>> FetchSizingProdData(@Param("set_no") String set_no, @Param("company_id") Integer company_id, @Param("from_date") String from_date, @Param("to_date") String to_date, @Param("yarn_count") String yarn_count, @Param("customer_order_no") String customer_order_no);


    @Query(value = "SELECT msomt.job_type, msomt.customer_order_no, cc.customer_name FROM mt_sales_order_master_trading msomt LEFT JOIN cm_customer cc ON cc.customer_id = msomt.customer_id AND cc.is_delete = 0 WHERE msomt.is_delete = 0 AND msomt.customer_order_no IN (:customerOrderNos) AND msomt.company_id = :company_id", nativeQuery = true)
    List<Map<String, Object>> FnGetJobType(@Param("customerOrderNos") List<String> customerOrderNos, @Param("company_id") Integer company_id);

}
