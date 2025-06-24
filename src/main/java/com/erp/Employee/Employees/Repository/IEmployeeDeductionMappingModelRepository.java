package com.erp.Employee.Employees.Repository;

import com.erp.Employee.Employees.Model.CEmployeeDeductionMappingModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface IEmployeeDeductionMappingModelRepository extends JpaRepository<CEmployeeDeductionMappingModel, Integer> {

	@Modifying
	@Transactional
	@Query(value = "update hm_employee_deduction_mapping set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where company_id = ?1 and designation_id = ?2 and is_delete = 0", nativeQuery = true)
	void updateDeductionRecord(int company_id, int employee_id);


	@Query(value = "FROM CEmployeeDeductionMappingModel model where model.employee_id = ?1 and model.is_delete = false")
	List<CEmployeeDeductionMappingModel> FnShowDeductionMapingRecords(int employee_id);


	@Modifying
	@Transactional
	@Query(value = "update CEmployeeDeductionMappingModel model set model.is_delete = 1, model.deleted_on = CURRENT_TIMESTAMP(), model.modified_by = ?3 where model.company_id = ?2 and model.employee_id = ?1 and model.is_delete = 0")
	void updateDeductionStatus(int employee_id, int company_id, String created_by);


}
