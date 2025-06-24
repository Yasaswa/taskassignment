package com.erp.SmProductRmStockDetails.Repository;

import com.erp.SmProductRmStockDetails.Model.CSmProductStockTrackingViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ISmProductStockTrackingViewModelRepository extends JpaRepository<CSmProductStockTrackingViewModel, Integer> {
	@Query("FROM CSmProductStockTrackingViewModel sm where sm.consumption_no = ?1 and sm.company_id = ?2 and sm.is_delete = false")
	List<CSmProductStockTrackingViewModel> FnShowStockRecords(String warping_production_code, int company_id);
}