package com.erp.XmSpinningProdCount.Repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.erp.XmSpinningProdCount.Model.CXmSpinningProdCountLevelsModel;

public interface IXmSpinningProdCountLevelsRepository extends JpaRepository<CXmSpinningProdCountLevelsModel, Integer>{

	
	@Modifying
	@Transactional
	@Query(value = "update CXmSpinningProdCountLevelsModel model SET model.is_delete = 1 , model.deleted_on = CURRENT_TIMESTAMP() WHERE model.production_count_id = ?1 AND company_id = ?2")
	void updateSpinningProdCountLevelsDetails(int production_count_id, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update CXmSpinningProdCountLevelsModel model set model.is_delete = 1 , model.deleted_by = ?2 , model.deleted_on = CURRENT_TIMESTAMP() where model.production_count_id = ?1 and model.company_id = ?3")
	void FnDeleteSpinningProdCountLevelsRecord(int production_count_id, String deleted_by, int company_id);

	
}
