package com.erp.XtWarpingBottomReturn.Repository;

import com.erp.XtWarpingBottomReturn.Model.CXtWarpingBottomStockDetailsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IXtWarpingBottomStockRepository extends JpaRepository<CXtWarpingBottomStockDetailsModel, Integer> {

    @Modifying
    @Query("UPDATE CXtWeavingProductionWarpingBottomDetailsModel model SET model.material_status = 'Returned' WHERE model.weaving_production_warping_bottom_details_id IN :warpingBottomDetailsIds AND model.company_id = :companyId")
    void updateWarpingBottomDetailsStatus(List<Integer> warpingBottomDetailsIds, int companyId);

    @Modifying
    @Query("UPDATE CXtWarpingBottomStockDetailsModel model SET model.stock_status = 'D', model.dispatch_challan_no = :goods_return_no , model.dispatch_challan_date = :goods_return_date WHERE model.warping_bottom_stock_id IN :warpingBottomStockDetailsIds AND model.company_id = :companyId")
    void updateWarpingBottomStockDetailsStatus(List<Integer> warpingBottomStockDetailsIds, String goods_return_no, String goods_return_date, int companyId);
}
