package com.erp.FmCostCenterHeads.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.erp.FmCostCenterHeads.Model.CFmCostCenterHeadsModel;

import javax.transaction.Transactional;


public interface IFmCostCenterHeadsRepository extends JpaRepository<CFmCostCenterHeadsModel, Integer> {



	@Modifying
	@Transactional
	@Query(value = "UPDATE CFmCostCenterHeadsModel cch SET cch.is_delete = true,cch.deleted_by = ?3, cch.deleted_on = CURRENT_TIMESTAMP() WHERE cch.cost_center_heads_id  = ?1 and cch.company_id = ?2")
	void FnDeleteCostCenterHead(int cost_center_heads_id, int company_id, String deleted_by);

	@Query(value = "FROM CFmCostCenterHeadsModel model where model.is_delete = false and model.cost_center_heads_name = ?1 and model.cost_center_id = ?2")
	CFmCostCenterHeadsModel checkIsCostCenterHeadExist(String cost_center_heads_name, Integer cost_center_id);
}
