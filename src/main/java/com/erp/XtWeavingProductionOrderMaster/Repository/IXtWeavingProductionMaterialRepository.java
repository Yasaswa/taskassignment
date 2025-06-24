package com.erp.XtWeavingProductionOrderMaster.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.erp.XtWeavingProductionOrderMaster.Model.CXtWeavingProductionMaterialModel;

public interface IXtWeavingProductionMaterialRepository extends JpaRepository<CXtWeavingProductionMaterialModel, Integer> {

	@Modifying
	@Transactional
	@Query(value = "update CXtWeavingProductionMaterialModel model set model.is_delete = 1 , model.deleted_by = ?2 , model.deleted_on = CURRENT_TIMESTAMP() where model.weaving_production_order_id = ?1")
	void FnDeleteWVProductionMaterialRecord(int weaving_production_order_id, String deleted_by);


	@Modifying
	@Transactional
	@Query(value = "update CXtWeavingProductionMaterialModel model set model.is_delete = 1 , model.deleted_by = ?2 , model.deleted_on = CURRENT_TIMESTAMP() where model.production_order_material_id = ?1")
	void FnDeletePerticularMaterialRecord(int production_order_material_id, String deleted_by);

	@Modifying
    @Transactional
    @Query(value = "UPDATE xt_weaving_production_material SET is_delete = 1, deleted_on = CURRENT_TIMESTAMP() WHERE production_order_material_id NOT IN (?1) AND set_no = ?2", nativeQuery = true)
    void updateProductMaterialsRecords(List<Integer> production_order_material_ids, String set_no);
}
