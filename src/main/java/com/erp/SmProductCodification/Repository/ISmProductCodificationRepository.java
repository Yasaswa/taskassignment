package com.erp.SmProductCodification.Repository;

import java.util.List;

import javax.transaction.Transactional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.erp.SmProductCodification.Model.CSmProductCodificationModel;


public interface ISmProductCodificationRepository extends JpaRepository<CSmProductCodificationModel, Integer> {


	@Query(value = "FROM CSmProductCodificationModel model where model.is_delete =0 and model.product_specification_name = ?1 and model.company_id = ?2")
	CSmProductCodificationModel checkIfNameExist(String product_specification_name, int company_id);

	
	@Modifying
	@Transactional
	@Query(value = "update CSmProductCodificationModel model set model.is_delete = 1 , model.deleted_by = ?2 , model.deleted_on = CURRENT_TIMESTAMP() where model.product_codification_id = ?1")
	void FnDeleteProductCodificationRecord(int product_codification_id, String deleted_by);

	@Query(value = "FROM CSmProductCodificationModel model where model.is_delete =0 and model.product_codification_id = ?1 and model.company_id = ?2")
	CSmProductCodificationModel FnShowParticularRecordForUpdate(int product_codification_id, int company_id);


}
