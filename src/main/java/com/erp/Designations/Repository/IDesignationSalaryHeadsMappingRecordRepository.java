package com.erp.Designations.Repository;

import com.erp.Designations.Model.CDesignationSalaryHeadsMappingViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IDesignationSalaryHeadsMappingRecordRepository extends JpaRepository<CDesignationSalaryHeadsMappingViewModel, Integer> {


	@Query(value = "FROM  CDesignationSalaryHeadsMappingViewModel model where model.is_delete = 0 and model.designation_salary_heads_mapping_id = ?1 and model.company_id = ?2")
	List<CDesignationSalaryHeadsMappingViewModel> FnShowDesignationSalaryHeadsMappingRecord(int designation_salary_heads_mapping_id,
	                                                                                        int company_id);


}
