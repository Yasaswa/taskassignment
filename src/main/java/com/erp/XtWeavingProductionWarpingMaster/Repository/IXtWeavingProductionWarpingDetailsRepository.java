package com.erp.XtWeavingProductionWarpingMaster.Repository;


import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.erp.XtWeavingProductionInspectionMaster.Model.CXtWeavingProductionInspectionDetailsModel;
import com.erp.XtWeavingProductionWarpingMaster.Model.CXtWeavingProductionWarpingDetailsModel;
import org.springframework.data.repository.query.Param;

public interface IXtWeavingProductionWarpingDetailsRepository extends JpaRepository<CXtWeavingProductionWarpingDetailsModel, Integer>{

	@Modifying
	@Transactional
	@Query(value = "update CXtWeavingProductionWarpingDetailsModel model set model.is_delete = 1 , model.deleted_by = ?2 , model.deleted_on = CURRENT_TIMESTAMP() where model.weaving_production_warping_master_id = ?1")
	void FnDeleteProductionWarpingDetailsRecord(int weaving_production_warping_master_id, String deleted_by);

	@Modifying
	@Transactional
	@Query(value = "update CXtWeavingProductionWarpingDetailsModel model SET model.is_delete = 1 , model.deleted_on = CURRENT_TIMESTAMP() WHERE model.weaving_production_warping_master_id = ?1 AND company_id = ?2")
	void updateWVProductionWarpingMasterRecord(int weaving_production_warping_master_id, int company_id);

	@Query(value = "FROM CXtWeavingProductionWarpingDetailsModel model WHERE model.is_delete = 0 AND weaving_production_set_no IN ?1")
	List<CXtWeavingProductionWarpingDetailsModel> getAllWeavingProductionWarpingDetails(List<String> weavingProductionWarpingSetNumbers);


	@Query(value = "SELECT xwpwd.weaving_production_set_no as set_no, xwpwd.warping_production_date, xwpwd.yarn_count, xwpwd.actual_count ,  xwpwd.product_rm_id , cm.machine_name, COALESCE(AVG(xwpwd.breaks_per_million), 0) AS breaks_per_million FROM xt_weaving_production_warping_details xwpwd left join cm_machine cm on cm.machine_id = xwpwd.machine_id and cm.is_delete = 0 WHERE xwpwd.is_delete = 0 AND (:set_no IS NULL OR xwpwd.weaving_production_set_no = :set_no) AND xwpwd.company_id = :company_id AND xwpwd.warping_production_date BETWEEN :from_date AND :to_date GROUP BY xwpwd.weaving_production_set_no, xwpwd.warping_production_date, xwpwd.yarn_count ORDER BY xwpwd.warping_production_date", nativeQuery = true)
	List<Map<String, Object>> FnGetBreaksPerMillion(@Param("set_no") String set_no, @Param("company_id") Integer company_id, @Param("from_date") String from_date, @Param("to_date") String to_date);

	@Query(value = "SELECT smibw.set_no, smibw.product_material_id, smibw.batch_no, smibw.issue_quantity, smibw.issue_weight, smibw.cone_per_wt FROM st_material_issue_batch_wise smibw WHERE smibw.is_delete = 0 AND smibw.company_id = :company_id AND smibw.issue_status IN ('MI', 'I') AND smibw.issue_return_status = 'P' AND smibw.set_no IN (:Setnos)", nativeQuery = true)
	List<Map<String, Object>> FnGetBatchWiseData(@Param("Setnos") List<String> Setnos, @Param("company_id") Integer company_id);

	@Query(value = "SELECT xwposd.supplier_id, xwposd.product_material_id, xwpo.set_no , xwposd.batch_no, xwpo.customer_order_no, xwpo.customer_id, cc.customer_name , csb.supp_branch_name as supplier_name FROM xt_warping_production_order_stock_details xwposd LEFT JOIN cm_supplier_branch csb ON csb.supp_branch_id = xwposd.supplier_id AND csb.is_delete = 0 LEFT JOIN xt_warping_production_order xwpo ON xwpo.set_no = xwposd.set_no AND xwpo.is_delete = 0 LEFT JOIN cm_customer cc ON cc.customer_id = xwpo.customer_id AND cc.is_delete = 0 WHERE xwposd.set_no IN (:Setnos) AND xwposd.is_delete = 0 AND xwposd.company_id = :company_id", nativeQuery = true)
	List<Map<String, Object>> FnGetSupplierNames(@Param("Setnos") List<String> Setnos, @Param("company_id") Integer company_id);

	@Query(value = "SELECT msomt.job_type, msomt.customer_order_no FROM mt_sales_order_master_trading msomt WHERE msomt.is_delete = 0 AND msomt.customer_order_no IN (:CustomerOrderNos) AND msomt.company_id = :company_id", nativeQuery = true)
	List<Map<String, Object>> FnGetJobType(@Param("CustomerOrderNos") List<String> CustomerOrderNos, @Param("company_id") Integer company_id);

}
	