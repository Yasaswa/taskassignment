package com.erp.XtWeavingProductionOrderMaster.Repository;

import com.erp.XtWeavingProductionOrderMaster.Model.CXtWeavingProductionOrderStyleDetailsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IXtWeavingProductionOrderStyleDetailsModelRepository extends JpaRepository<CXtWeavingProductionOrderStyleDetailsModel, Integer> {

	@Query(value = "FROM CXtWeavingProductionOrderStyleDetailsModel model where model.is_delete = false and model.weaving_production_order_id = ?1 and model.company_id = ?2")
	List<CXtWeavingProductionOrderStyleDetailsModel> getSalesOrderStyleDetails(int weaving_production_order_id, int company_id);
}