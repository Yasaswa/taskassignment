package com.erp.XtWarpingProductionOrder.Repository;

import com.erp.XtWarpingProductionOrder.Model.CXtWarpingProductionOrderBeamDetailsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface IXtWarpingProductionBeamDetailsRepository extends JpaRepository<CXtWarpingProductionOrderBeamDetailsModel , Integer> {

    @Query(value = "FROM CXtWarpingProductionOrderBeamDetailsModel model where model.is_delete =0 and model.warping_production_order_id = ?1 and model.company_id = ?2")
    List<CXtWarpingProductionOrderBeamDetailsModel> FnShowParticularRecordForUpdate(int warping_production_order_id, int company_id);

    @Modifying
    @Transactional
    @Query(value = "update xt_warping_production_order_beam_details set is_delete = 1, deleted_by = ?2, deleted_on = CURRENT_TIMESTAMP() where warping_production_order_id=?1", nativeQuery = true)
    void FnDeleteBeamDetailsRecord(int warping_production_order_id, String deleted_by, int company_id);

    //Function To Update Production Status
    @Modifying
    @Transactional
    @Query(value = "UPDATE xt_warping_production_order_beam_details xwpobd SET xwpobd.warping_order_status = 'X' WHERE xwpobd.set_no = ?1 AND xwpobd.company_id = ?2 AND xwpobd.is_delete = 0", nativeQuery = true)
    void FnUpdateWarpingBeamWiseModalStatus(String set_no, Integer company_id);
}
