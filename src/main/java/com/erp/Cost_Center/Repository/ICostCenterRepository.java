package com.erp.Cost_Center.Repository;

import com.erp.Cost_Center.Model.CCostCenterModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface ICostCenterRepository extends JpaRepository<CCostCenterModel, Integer> {

	@Modifying
	@Transactional
	@Query(value = "update fm_cost_center set is_delete = 1 , is_active = 0 ,  deleted_by = ?3 , deleted_on = CURRENT_TIMESTAMP() where cost_center_id=?1 and company_id = ?2", nativeQuery = true)
	Object FnDeleteRecord(int cost_center_id, int company_id, String deleted_by);

	@Query(nativeQuery = true, value = "SELECT * FROM fm_cost_center WHERE cost_center_name = ?1 and company_id = ?2")
	CCostCenterModel getCheck(String cost_center_name, int company_id);

	@Query(nativeQuery = true, value = "SELECT * FROM fm_cost_center WHERE cost_center_name = ?1 and cost_center_short_name = ?2 and company_id = ?3 and cost_center_group = ?4 AND is_delete = 0 order by cost_center_id Desc limit 1")
	CCostCenterModel getCheck(String cost_center_name, String cost_center_short_name, Integer company_id, String cost_center_group);

}
