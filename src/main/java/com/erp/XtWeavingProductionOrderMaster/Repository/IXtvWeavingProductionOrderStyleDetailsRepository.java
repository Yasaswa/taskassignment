package com.erp.XtWeavingProductionOrderMaster.Repository;


import com.erp.XtWeavingProductionOrderMaster.Model.CXtWeavingProductionOrderStyleDetailsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.erp.XtWeavingProductionOrderMaster.Model.CXtvWeavingProductionOrderStyleDetailsModel;

import java.util.List;


public interface IXtvWeavingProductionOrderStyleDetailsRepository extends JpaRepository<CXtvWeavingProductionOrderStyleDetailsModel, Integer> {

	@Query(value = "FROM CXtvWeavingProductionOrderStyleDetailsModel model where model.is_delete = false and model.set_no = ?1 and model.company_id = ?2")
	List<CXtvWeavingProductionOrderStyleDetailsModel> getSalesOrderStyleDetails(String set_no, int company_id);

	@Query(value = "FROM CXtvWeavingProductionOrderStyleDetailsModel model where model.is_delete = false and model.weaving_production_order_id = ?1 and model.company_id = ?2")
	List<CXtvWeavingProductionOrderStyleDetailsModel> getSalesOrderStyleViewDetails(Integer weaving_production_order_id, int company_id);
}
