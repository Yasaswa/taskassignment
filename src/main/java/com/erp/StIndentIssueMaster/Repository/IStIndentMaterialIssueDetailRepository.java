package com.erp.StIndentIssueMaster.Repository;

import com.erp.StIndentIssueMaster.Model.CStIndentIssueDetailsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface IStIndentMaterialIssueDetailRepository extends JpaRepository<CStIndentIssueDetailsModel, Integer> {

	@Modifying
	@Transactional
	@Query(value = "update st_indent_material_issue_details set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where issue_no = ?1 and financial_year = ?2 and company_id = ?3 and is_delete =0", nativeQuery = true)
	void updateStatus(String issue_no, String financial_year, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update st_indent_material_issue_details set is_delete = 1 ,deleted_by =?4, deleted_on = CURRENT_TIMESTAMP() where issue_no = ?1 and issue_version = ?2 and company_id = ?3", nativeQuery = true)
	void FnDeleteIndentIssueDetailsRecord(String issue_no, int issue_version, int company_id, String deleted_by);

	@Query(value = "select * from st_indent_material_issue_details where indent_no = ?1 and is_delete = 0 and company_id = ?2", nativeQuery = true)
	List<CStIndentIssueDetailsModel> getAllIssueDetails(String indentNo, int companyId);

	@Modifying
	@Transactional
	@Query(value = "update st_indent_material_issue_details set material_batch_rate = ?1 where issue_details_transaction_id = ?2 and company_id = ?3", nativeQuery = true)
	void updateMaterialRate(double batchRate, int issueDetailsTransactionId, int company_id);

	@Query(value= "FROM CStIndentIssueDetailsModel model where model.issue_no = ?1 and model.company_id = ?2 and model.is_delete = false")
	List<CStIndentIssueDetailsModel> FnShowIndentMaterialIssueDetailRecords(String issue_no, int companyId);

//	@Query(value = "SELECT simid.product_material_issue_quantity, simid.issue_batch_no, smr.actual_count, cc.customer_name, smr.product_rm_name, xwpsm.actual_bottom_kg, pgrd.remark, xwpo.set_length, simid.product_material_id,  SUM(simid.product_material_issue_weight) OVER () AS total_issued_weight, SUM(simid.product_material_issue_quantity) OVER () AS total_cone_used FROM st_indent_material_issue_details simid LEFT JOIN st_indent_material_issue_master simim ON simim.set_no = simid.set_no AND simim.department_id = ?3 AND simim.sub_department_id = ?4 AND simim.is_delete = 0 LEFT JOIN xt_warping_production_order xwpo ON xwpo.set_no = simid.set_no AND xwpo.is_delete = 0 LEFT JOIN xt_weaving_production_warping_master xwpsm ON xwpsm.set_no = simid.set_no LEFT JOIN cm_customer cc ON cc.customer_id = xwpo.customer_id AND cc.is_delete = 0 LEFT JOIN sm_product_rm smr ON smr.product_rm_id = simid.product_material_id AND smr.is_delete = 0 LEFT JOIN pt_goods_receipt_details pgrd ON pgrd.batch_no = simid.issue_batch_no AND pgrd.product_material_id = simid.product_material_id AND pgrd.is_delete = 0 AND simid.goods_receipt_no = pgrd.goods_receipt_no WHERE simid.set_no = ?1 AND simid.company_id = ?2 AND simid.issue_item_status IN ('MI', 'I') limit 1", nativeQuery = true)
//	Map<String, Object> FnGetSizingProductionMasterdata(String set_no, int companyId, int departmentId, int subdepartmentId);

//	@Query(value = "SELECT simid.product_material_issue_quantity, simid.issue_batch_no, smr.actual_count, cc.customer_name, smr.product_rm_name, xwpsm.actual_bottom_kg, pgrd.remark, xwpo.set_length, simid.product_material_id,  simid.product_material_issue_weight AS total_issued_weight, simid.product_material_issue_quantity AS total_cone_used FROM st_indent_material_issue_details simid LEFT JOIN st_indent_material_issue_master simim ON simim.set_no = simid.set_no AND simim.department_id = ?3 AND simim.sub_department_id = ?4 AND simim.is_delete = 0 LEFT JOIN xt_warping_production_order xwpo ON xwpo.set_no = simid.set_no AND xwpo.is_delete = 0 LEFT JOIN xt_weaving_production_warping_master xwpsm ON xwpsm.set_no = simid.set_no LEFT JOIN cm_customer cc ON cc.customer_id = xwpo.customer_id AND cc.is_delete = 0 LEFT JOIN sm_product_rm smr ON smr.product_rm_id = simid.product_material_id AND smr.is_delete = 0 LEFT JOIN pt_goods_receipt_details pgrd ON pgrd.batch_no = simid.issue_batch_no AND pgrd.product_material_id = simid.product_material_id AND pgrd.is_delete = 0 AND simid.goods_receipt_no = pgrd.goods_receipt_no WHERE simid.set_no = ?1 AND simid.company_id = ?2 AND simid.issue_item_status IN ('MI', 'I')", nativeQuery = true)
//	Map<String, Object> FnGetSizingProductionMasterdata(String set_no, int companyId, int departmentId, int subdepartmentId);

//	@Query(value = "SELECT simid.product_material_issue_quantity, simid.issue_batch_no, smr.actual_count, cc.customer_name, smr.product_rm_name, xwpsm.actual_bottom_kg, pgrd.remark, xwpo.set_length, simid.product_material_id, simid.product_material_issue_weight AS total_issued_weight, simid.product_material_issue_quantity AS total_cone_used, AVG(xwd.breaks_per_million) OVER () AS avg_breaks_per_million FROM st_indent_material_issue_details simid LEFT JOIN st_indent_material_issue_master simim ON simim.set_no = simid.set_no AND simim.department_id = ?3 AND simim.sub_department_id = ?4 AND simim.is_delete = 0 LEFT JOIN xt_warping_production_order xwpo ON xwpo.set_no = simid.set_no AND xwpo.is_delete = 0 LEFT JOIN xt_weaving_production_warping_master xwpsm ON xwpsm.set_no = simid.set_no LEFT JOIN cm_customer cc ON cc.customer_id = xwpo.customer_id AND cc.is_delete = 0 LEFT JOIN sm_product_rm smr ON smr.product_rm_id = simid.product_material_id AND smr.is_delete = 0 LEFT JOIN xt_weaving_production_warping_details xwd ON xwd.weaving_production_set_no = simid.set_no AND xwd.product_rm_id = simid.product_material_id AND xwd.is_delete = 0 LEFT JOIN pt_goods_receipt_details pgrd ON pgrd.batch_no = simid.issue_batch_no AND pgrd.product_material_id = simid.product_material_id AND pgrd.is_delete = 0 AND simid.goods_receipt_no = pgrd.goods_receipt_no WHERE simid.set_no = ?1 AND simid.company_id = ?2 AND simid.issue_item_status IN ('MI', 'I')", nativeQuery = true)
//	List<Map<String, Object>> FnGetSizingProductionMasterdata(String set_no, int companyId, int departmentId, int subdepartmentId);

	@Query(value = "SELECT " +
			"simid.set_no, " +
			"simid.product_material_id, " +
			"SUM(simid.issue_quantity) AS total_cone_used, " +
			"SUM(simid.issue_weight) AS total_issued_weight, " +
			"MIN(simid.batch_no) AS issue_batch_no, " +
			"MIN(smr.actual_count) AS actual_count, " +
			"MIN(cc.customer_name) AS customer_name, " +
			"MIN(smr.product_rm_name) AS product_rm_name, " +
			"MIN(xwpsm.actual_bottom_kg) AS actual_bottom_kg, " +
			"MIN(pgrd.remark) AS remark, " +
			"MIN(xwpo.set_length) AS set_length, " +
			"(SELECT AVG(xwd.breaks_per_million) " +
			" FROM xt_weaving_production_warping_details xwd " +
			" WHERE xwd.is_delete = 0 AND xwd.weaving_production_set_no = simid.set_no) AS avg_breaks_per_million " +
			"FROM st_material_issue_batch_wise simid " +
			"LEFT JOIN st_indent_material_issue_master simim ON simim.set_no = simid.set_no " +
			"AND simim.department_id = ?3 AND simim.sub_department_id = ?4 AND simim.is_delete = 0 " +
			"LEFT JOIN xt_warping_production_order xwpo ON xwpo.set_no = simid.set_no AND xwpo.is_delete = 0 " +
			"LEFT JOIN xt_weaving_production_warping_master xwpsm ON xwpsm.set_no = simid.set_no " +
			"LEFT JOIN cm_customer cc ON cc.customer_id = xwpo.customer_id AND cc.is_delete = 0 " +
			"LEFT JOIN sm_product_rm smr ON smr.product_rm_id = simid.product_material_id AND smr.is_delete = 0 " +
			"LEFT JOIN pt_goods_receipt_details pgrd ON pgrd.batch_no = simid.batch_no " +
			"AND pgrd.product_material_id = simid.product_material_id AND pgrd.is_delete = 0 " +
			"AND simid.goods_receipt_no = pgrd.goods_receipt_no " +
			"WHERE simid.set_no = ?1 AND simid.company_id = ?2 AND simid.issue_status IN ('MI', 'I') and simid.issue_return_status = 'P' and simid.is_delete = 0 " +
			"GROUP BY simid.set_no, simid.product_material_id",
			nativeQuery = true)
	List<Map<String, Object>> FnGetSizingProductionMasterdata(String setNo, int companyId, int departmentId, int subDepartmentId);

	@Query(value = "SELECT DISTINCT simid.creel_no, (SELECT SUM(calculated_bottom) FROM xt_warping_production_order_creels xwpoc WHERE xwpoc.set_no = ?1) AS calculated_bottom_total, (SELECT SUM(no_of_beams) FROM xt_warping_production_order_creels xwpoc WHERE xwpoc.set_no = ?1) AS no_of_beams, xwpo.no_of_creels, simid.product_material_id, simid.cone_per_wt, simid.goods_receipt_no as goods_receipt_no, simid.issue_batch_no AS batch_no, spr.product_rm_name AS yarn_count, spr.actual_count, xwpo.product_material_name, xwpo.t_ends, xwpo.warping_order_no, xwpo.schedule_quantity, xwpo.product_material_style, xwpo.set_length, xwposd.supplier_id, SUM(simid.product_material_issue_weight) OVER () AS total_issued_weight, SUM(simid.product_material_issue_quantity) OVER () AS total_issued_quantity, SUM(simid.product_material_issue_boxes) OVER () AS total_issued_boxes FROM st_indent_material_issue_details simid LEFT JOIN xtv_warping_production_order xwpo ON xwpo.is_delete = 0 and xwpo.set_no = simid.set_no LEFT JOIN xt_warping_production_order_stock_details xwposd ON xwposd.is_delete = 0 and xwposd.set_no = simid.set_no AND xwposd.product_material_id = simid.product_material_id AND xwposd.cone_per_wt = simid.cone_per_wt LEFT JOIN sm_product_rm spr ON spr.product_rm_id = simid.product_material_id AND spr.is_delete = 0 WHERE simid.set_no = ?1 AND simid.company_id = ?2 and simid.is_delete = 0", nativeQuery = true)
	ArrayList<Map<String, Object>> FnGetWarpingMasterData(String setNo, int companyId);


	@Query(value = "Select distinct simid.goods_receipt_no from st_indent_material_issue_details simid Where simid.set_no = ?1 and simid.company_id = ?2 and simid.is_delete = 0", nativeQuery = true)
	List<String> FnGetGRNumbers(String set_no, Integer company_id);
}
