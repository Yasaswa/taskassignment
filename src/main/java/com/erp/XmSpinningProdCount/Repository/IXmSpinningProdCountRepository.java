package com.erp.XmSpinningProdCount.Repository;

import com.erp.XmSpinningProdCount.Model.CxmSpinningProdCountModel;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface IXmSpinningProdCountRepository extends JpaRepository<CxmSpinningProdCountModel, Integer> {

	@Query(value = "FROM CxmSpinningProdCountModel model where model.is_delete =0 and model.production_count_name = ?1 and model.company_id = ?2")
	CxmSpinningProdCountModel checkIfNameExist(String production_count_name, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update CxmSpinningProdCountModel model set model.is_delete = 1 , model.deleted_by = ?2 , model.deleted_on = CURRENT_TIMESTAMP() where model.production_count_id = ?1 and model.company_id = ?3")
	void FnDeleteSpinningProdCountRecord(int production_count_id, String deleted_by, int company_id);

	@Query(value = "FROM CxmSpinningProdCountModel model where model.is_delete = 0 and model.production_count_id = ?1 and model.company_id = ?2")
	CxmSpinningProdCountModel FnShowSpinningProdCountRecordForUpdate(int production_count_id, int company_id);

}
