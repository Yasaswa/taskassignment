package com.erp.XtWeavingProductionWarpingOrderMaster.Repository;

import com.erp.XtWeavingProductionOrderMaster.Model.CXtWeavingProductionOrderStyleDetailsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IxtWeavingProductionWarpingOrderRepository extends JpaRepository<CXtWeavingProductionOrderStyleDetailsModel,Integer> {

    @Query(value = "FROM CXtWeavingProductionOrderStyleDetailsModel model where model.is_delete = false and model.set_no = ?1 and model.company_id = ?2")
    List<CXtWeavingProductionOrderStyleDetailsModel> getSalesOrderStyleDetails(String set_no, int company_id);
}

