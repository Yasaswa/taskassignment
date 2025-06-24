package com.erp.XtWeavingProductionSizingDetails.Repository;

import com.erp.XtWeavingProductionSizingDetails.Model.CXtSizingProductionStockDetailViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface IXtSizingProductionStockDetailsViewRepository extends JpaRepository<CXtSizingProductionStockDetailViewModel, Integer> {

    @Query("FROM CXtSizingProductionStockDetailViewModel xspsd WHERE xspsd.is_delete = 0 AND xspsd.set_no = :setNo AND xspsd.company_id = :companyId AND xspsd.sized_beam_status_desc = 'Available'")
    List<CXtSizingProductionStockDetailViewModel> FnGetDispatchSizedYarnDetailsData(@Param("companyId") int companyId, @Param("setNo") String setNo);

    @Query("FROM CXtSizingProductionStockDetailViewModel xspsd  WHERE xspsd.is_delete = 0 AND xspsd.set_no = :setNo AND xspsd.company_id = :companyId AND (xspsd.sized_beam_status_desc = 'Available' OR (xspsd.sized_beam_status_desc = 'Dispatched'AND xspsd.beam_no IN :approvedBeamIds))")
    List<CXtSizingProductionStockDetailViewModel> FnGetDispatchSizedYarnDetailsAllData(int companyId, String setNo,List<String> approvedBeamIds);

}
