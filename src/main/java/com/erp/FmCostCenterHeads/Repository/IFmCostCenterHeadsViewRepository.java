package com.erp.FmCostCenterHeads.Repository;


import com.erp.FmCostCenterHeads.Model.CFmCostCenterHeadsViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface IFmCostCenterHeadsViewRepository extends JpaRepository<CFmCostCenterHeadsViewModel, Integer> {


	@Query(value = "FROM CFmCostCenterHeadsViewModel model where model.is_delete = false and model.cost_center_heads_id = ?1")
	CFmCostCenterHeadsViewModel FnShowParticularRecordForUpdate(int cost_center_heads_id);
	

}
