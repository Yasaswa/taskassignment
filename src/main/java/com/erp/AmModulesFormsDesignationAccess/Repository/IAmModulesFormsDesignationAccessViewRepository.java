package com.erp.AmModulesFormsDesignationAccess.Repository;

import com.erp.AmModulesFormsDesignationAccess.Model.CAmModulesFormsDesignationAccessViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IAmModulesFormsDesignationAccessViewRepository
		extends JpaRepository<CAmModulesFormsDesignationAccessViewModel, Integer> {

	@Query(value = "FROM CAmModulesFormsDesignationAccessViewModel model where model.is_delete = 0 and model.designation_id =?1 and model.company_id = ?2 order by model.modules_forms_designation_access_id Desc")
	List<CAmModulesFormsDesignationAccessViewModel> FnShowAllActiveRecords(int designation_id, int company_id);

	@Query(value = "FROM CAmModulesFormsDesignationAccessViewModel model where model.is_delete =0 and model.modules_forms_designation_access_id = ?1 and model.company_id = ?2 order by model.modules_forms_designation_access_id Desc")
	Page<CAmModulesFormsDesignationAccessViewModel> FnShowParticularRecord(int modules_forms_designation_access_id,
	                                                                       Pageable pageable, int company_id);

}
