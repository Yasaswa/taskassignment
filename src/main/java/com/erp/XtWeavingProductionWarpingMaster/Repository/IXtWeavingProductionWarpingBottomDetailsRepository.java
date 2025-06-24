package com.erp.XtWeavingProductionWarpingMaster.Repository;

import com.erp.XtWarpingProductionOrder.Model.CXtWarpingProductionOrderCreelsModel;
import com.erp.XtWeavingProductionSizingDetails.Model.CXtWeavingProductionSizingDetailsViewModel;
import com.erp.XtWeavingProductionWarpingMaster.Model.CXtWeavingProductionWarpingBottomDetailsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface IXtWeavingProductionWarpingBottomDetailsRepository extends JpaRepository<CXtWeavingProductionWarpingBottomDetailsModel, Integer> {
    @Modifying
    @Transactional
    @Query(value = "FROM CXtWeavingProductionWarpingBottomDetailsModel model WHERE model.is_delete = 0 AND model.weaving_production_warping_master_id = ?1 AND model.company_id = ?2")
    List<CXtWeavingProductionWarpingBottomDetailsModel> FnShowWarpingBottomDetails(int weavingProductionWarpingMasterId, int companyId);

    @Modifying
    @Transactional
    @Query("UPDATE CXtWeavingProductionWarpingBottomDetailsModel model "
            + "SET model.is_delete = 1, model.deleted_on = CURRENT_TIMESTAMP "
            + "WHERE model.weaving_production_warping_bottom_details_id NOT IN ?1 "
            + "AND model.warping_production_code = ?2 "
            + "AND model.company_id = ?3")
    void deleteBottomDetails(List<Integer> distinctBottomsIds, String warpingProdcode, int companyId);


//    @Query(value = "SELECT * FROM xt_weaving_production_warping_bottom_details where is_delete = 0 and set_no = ?1 AND company_id = ?2",nativeQuery = true)
//    ArrayList<CXtWeavingProductionWarpingBottomDetailsModel> FnGetWarpingProductionBottomRecords(String set_no, int company_id);


    @Query(value = "SELECT btm.no_of_package, btm.gross_weight, btm.tare_weight, btm.net_weight, smr.product_rm_name " +
            "FROM xt_weaving_production_warping_bottom_details btm " +
            "LEFT JOIN sm_product_rm smr ON smr.product_rm_id = btm.product_rm_id " +
            "WHERE btm.is_delete = 0 AND btm.set_no = ?1 AND btm.company_id = ?2",
            nativeQuery = true)
    List<Object[]> FnGetWarpingProductionBottomRecords(String set_no, int company_id);

    @Query(value = "SELECT sr_no FROM xt_weaving_production_warping_bottom_details WHERE warping_bottom_details_production_date LIKE ?1 AND company_id = ?2 AND is_delete = 0", nativeQuery = true)
    List<String> FnGetSetSrNo(String sr_no_dt, int company_id);

//    @Query("FROM CXtWarpingProductionOrderCreelsModel btm WHERE btm.is_delete = 0 AND btm.set_no = ?1 AND btm.company_id = ?2")
//    List<CXtWarpingProductionOrderCreelsModel> FnGetWarpingCreelRecords(String setNo, int companyId);

}
