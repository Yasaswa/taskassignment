package com.erp.HmProfessionalTax.Repository;


import com.erp.HmProfessionalTax.Model.CHmProfessionalTaxModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;


public interface IHmProfessionalTaxRepository extends JpaRepository<CHmProfessionalTaxModel, Integer> {


	@Transactional
	@Modifying
	@Query(value = "update hm_professional_tax set is_delete = 1 ,deleted_by=?2 , deleted_on = CURRENT_TIMESTAMP() where professional_tax_id=?1", nativeQuery = true)
	void FnDeleteRecord(int professional_tax_id, String deleted_by);

	@Transactional
	@Modifying
	@Query(value = "update hm_professional_tax set is_delete = 1 where company_id=?1 and gender = ?2", nativeQuery = true)
	void updateProfessionalTaxRecord(int company_id, String gender);

//	@Query(value = "FROM CHmProfessionalTaxModel model where model.is_delete =0 and model.company_id = ?1)
//	CHmProfessionalTaxModel FnShowParticularRecordForUpdate(int company_id);


	@Query(value = "FROM CHmProfessionalTaxModel model where model.is_delete =0 and gender = ?1")
	List<CHmProfessionalTaxModel> FnShowParticularRecordForUpdate(String gender);


}
