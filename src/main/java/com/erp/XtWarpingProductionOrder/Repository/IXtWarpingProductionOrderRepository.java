package com.erp.XtWarpingProductionOrder.Repository;

import com.erp.XtWarpingProductionOrder.Model.CXtWarpingProductionOrderModel;
import com.erp.XtWarpingProductionOrder.Model.CxtWarpingProductionOrderDetailsViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public interface IXtWarpingProductionOrderRepository extends JpaRepository<CXtWarpingProductionOrderModel, Integer> {

	@Query(value = "FROM CxtWarpingProductionOrderDetailsViewModel model WHERE model.is_delete = 0 and model.warping_production_order_id = ?1 and model.company_id = ?2")
	List<CxtWarpingProductionOrderDetailsViewModel> FnWarpingProductionDetailsRecordForUpdate(int warping_production_order_id, int companyId);

	@Modifying
	@Transactional
	@Query(value = "update xt_warping_production_order_details set is_delete = 1, deleted_by = ?2, deleted_on = CURRENT_TIMESTAMP() where warping_production_order_id=?1", nativeQuery = true)
	void FnDeleteDetailsRecord(int warpingProductionOrderId, String deleted_by, int companyId);

	@Modifying
	@Transactional
	@Query(value = "update xt_warping_production_order set is_delete = 1, deleted_by= ?2, deleted_on = CURRENT_TIMESTAMP() where warping_production_order_id=?1", nativeQuery = true)
	void FnDeleteRecord(int warping_production_order_id, String deleted_by, int company_id);

	@Query(value = "SELECT CASE WHEN mt.product_type_id IN (2, 11, 12, 13) THEN spr.product_rm_name ELSE spf.product_fg_name END AS product_material_name, CASE WHEN mt.product_type_id IN (2, 11, 12, 13) THEN spr.product_rm_code ELSE spf.product_fg_code END AS product_material_code, CASE WHEN mt.product_type_id IN (2, 11, 12, 13) THEN spr.product_rm_id ELSE spf.product_fg_id END AS product_material_id, mt.material_quantity, mt.material_weight, mt.material_style, mt.material_style_value, mt.material_style_abbrevation FROM mt_sales_order_details_trading mt LEFT JOIN sm_product_rm spr ON mt.product_material_id = spr.product_rm_id AND mt.product_type_id IN (2, 11, 12, 13) AND spr.is_delete = 0 LEFT JOIN sm_product_fg spf ON mt.product_material_id = spf.product_fg_id AND mt.product_type_id NOT IN (2, 11, 12, 13) AND spf.is_delete = 0 WHERE mt.customer_order_no = ?1 AND mt.company_id = ?2 AND mt.product_type_id = ?3", nativeQuery = true)
	ArrayList<Map<String, Object>> FnGetCustomerMaterialData(String customer_order_no, int product_type_id, int company_id);

	//Updating set no in sales order
	@Modifying
	@Transactional
	@Query(value = "UPDATE mt_sales_order_details_trading SET set_no = :setNo WHERE customer_order_no = :customerOrderNo AND product_material_id = :productMaterialId AND company_id = :companyId AND is_delete = 0", nativeQuery = true)
	void updateSetNoInSalesOrder(@Param("customerOrderNo") String customerOrderNo, @Param("setNo") String setNo, @Param("productMaterialId") String productMaterialId, @Param("companyId") Integer companyId);




	@Query(value = "SELECT xwpo.set_no FROM xt_warping_production_order xwpo WHERE xwpo.set_no = ?1 AND xwpo.company_id = ?2 AND xwpo.is_delete = 0 LIMIT 1", nativeQuery = true)
	Optional<String> FnCheckSetNoExists(String set_no, Integer company_id);



	//Function to get Customer Order & product Materials against Set No
	@Query(value = "SELECT wo.customer_order_no, wo.product_material_id, wo.t_ends FROM xt_warping_production_order wo WHERE wo.set_no = ?1 AND wo.is_delete = 0 AND wo.company_id = ?2", nativeQuery = true)
	Map<String, Object> FetchWarpingOrderData(String set_no, Integer company_id);


	//Function To Update Production Status
	@Modifying
	@Transactional
	@Query(value = "UPDATE xt_warping_production_order xwpo SET xwpo.warping_order_status = 'X' WHERE xwpo.set_no = ?1 AND xwpo.company_id = ?2 AND xwpo.is_delete = 0", nativeQuery = true)
	void FnUpdateWarpingModalStatus(String set_no, Integer company_id);

}
