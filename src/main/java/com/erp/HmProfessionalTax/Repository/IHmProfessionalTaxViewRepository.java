package com.erp.HmProfessionalTax.Repository;

import com.erp.HmProfessionalTax.Model.CHmProfessionalTaxViewModel;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IHmProfessionalTaxViewRepository extends JpaRepository<CHmProfessionalTaxViewModel, Integer> {

//	@Query(value = "FROM CHmProfessionalTaxViewModel model where model.is_delete = 0 and model.company_id = ?1 order by model.professional_tax_id Desc")
//	Page<CHmProfessionalTaxViewModel> FnShowAllActiveRecords(Pageable pageable, int professional_tax_id);
//
//	@Query(value = "FROM CHmProfessionalTaxViewModel model where model.is_delete =0 and model.company_id = ?1 and model.professional_tax_id = ?2 order by model.professional_tax_id Desc")
//	Page<CHmProfessionalTaxViewModel> FnShowParticularRecord(int company_id, Pageable pageable, int professional_tax_id);
//	

}
