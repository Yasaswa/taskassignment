package com.erp.HmEmployeeTypeEarningAndTypeDeduction.Repository;

import com.erp.HmEmployeeTypeEarningAndTypeDeduction.Model.CHmEmployeeTypeDeductionMappingModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface IHmEmployeeTypeDeductionMappingRepository extends JpaRepository<CHmEmployeeTypeDeductionMappingModel, Integer> {

	@Modifying
	@Transactional
	@Query(value = "update hm_employee_type_deduction_mapping set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where company_id = ?1 and employee_type_name = ?2 and employee_type_group_name = ?3 and is_delete = 0", nativeQuery = true)
	void updateDeductionRecord(int company_id, String employee_type_name, String employee_type_group_name);

	@Modifying
	@Transactional
	@Query(value = "update hm_employee_type_deduction_mapping set is_delete = 1,  deleted_by = ?3 , deleted_on = CURRENT_TIMESTAMP() where employee_type_id=?1  and company_id = ?2", nativeQuery = true)
	void deleteEmployeeTypeDeductionMapping(int employee_type_id, int company_id, String deleted_by);

	@Query(value = "FROM CHmEmployeeTypeDeductionMappingModel model where model.is_delete = 0 and model.employee_type_name = ?1 and model.employee_type_group_name = ?2 and model.company_id = ?3")
	List<CHmEmployeeTypeDeductionMappingModel> FnShowdeductionMappingRecords(String employee_type_name,
	                                                                         String employee_type_group_name, int company_id);

	@Query(value = "FROM CHmEmployeeTypeDeductionMappingModel model where model.is_delete = 0 and model.employee_type_name = ?1 and model.company_id = ?2")
	List<CHmEmployeeTypeDeductionMappingModel> FnShowEmployeeDeductionMappingRecords(String employee_type_name,
	                                                                                 int company_id);

	@Query(value = "FROM CHmEmployeeTypeDeductionMappingModel model where model.is_delete=0 and model.company_id = ?1 and model.employee_type_id = ?2")
	List<CHmEmployeeTypeDeductionMappingModel> FnShowAllEmployeeTypeDeductionRecords(int company_id, int common_id);

}
