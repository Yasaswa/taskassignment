package com.erp.SmProductStockAdjustment.Repository;

import com.erp.SmProductStockAdjustment.Model.CSmProductStockAdjustmentDetailsModel;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ISmProductStockAdjustmentDetailsModelRepository extends JpaRepository<CSmProductStockAdjustmentDetailsModel, Integer> {

	@Transactional
	@Modifying
	@Query(value = "UPDATE CSmProductStockAdjustmentDetailsModel psad SET psad.is_delete = true, psad.deleted_by = ?3, psad.deleted_on = CURRENT_TIMESTAMP() WHERE psad.stock_adjustment_transaction_id  = ?1 and psad.company_id = ?2")
	void FnDeleteStockAdjustmentDetailsRecord(int stock_adjustment_transaction_id, int company_id, String deleted_by);


	@Query(value = "SELECT * FROM smv_product_rm_fg_sr where product_material_id IN ?1 and is_delete = 0", nativeQuery = true)
	List<Map<String, Object>> getMaterialsData(List<String> distinctMaterialIds);

	@Modifying
	@Transactional
	@Query(value = "UPDATE sm_product_stock_adjustment_details SET material_rate = ?1 where product_material_id = ?2 and company_id = ?3", nativeQuery = true)
	void updateMaterialAdjustmentRate(double productRmMrp, String productRmId, Integer companyId);
}