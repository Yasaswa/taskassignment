package com.erp.HmEmployeeTypeEarningAndTypeDeduction.Repository;

import com.erp.HmEmployeeTypeEarningAndTypeDeduction.Model.CHmEmployeeTypeEarningMappingModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;


public interface IHmEmployeeTypeEarningMappingRepository extends JpaRepository<CHmEmployeeTypeEarningMappingModel, Integer> {

	@Modifying
	@Transactional
	@Query(value = "update hm_employee_type_earning_mapping set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where company_id = ?1 and employee_type_name = ?2 and employee_type_group_name = ?3 and is_delete = 0", nativeQuery = true)
	void updateEmployeeEarningRecord(int company_id, String employee_type_name, String employee_type_group_name);

	@Modifying
	@Transactional
	@Query(value = "update hm_employee_type_earning_mapping set is_delete = 1,  deleted_by = ?3 , deleted_on = CURRENT_TIMESTAMP() where employee_type_id = ?1 and company_id = ?2", nativeQuery = true)
	void deleteEmployeeTypeEarningMapping(int employee_type_id, int company_id, String deleted_by);

	@Query(value = "FROM CHmEmployeeTypeEarningMappingModel model where model.is_delete = 0 and model.employee_type_name = ?1 and model.employee_type_group_name = ?2 and model.company_id = ?3")
	List<CHmEmployeeTypeEarningMappingModel> FnShowErningMapingRecords(String employee_type_name,
	                                                                   String employee_type_group_name, int company_id);

	@Query(value = "FROM CHmEmployeeTypeEarningMappingModel model where model.is_delete = 0 and model.employee_type_name = ?1 and model.company_id = ?2")
	List<CHmEmployeeTypeEarningMappingModel> FnShowEmployeeEarningMappingRecords(String employee_type_name,
	                                                                             int company_id);

	@Query(value = "FROM CHmEmployeeTypeEarningMappingModel model where model.is_delete=0 and model.company_id = ?1 and model.employee_type_id = ?2")
	List<CHmEmployeeTypeEarningMappingModel> FnShowAllEmployeeTypeEarningRecords(int company_id, int common_id);


}
