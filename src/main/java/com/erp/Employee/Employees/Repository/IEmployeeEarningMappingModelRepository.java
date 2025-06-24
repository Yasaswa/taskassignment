package com.erp.Employee.Employees.Repository;

import com.erp.Employee.Employees.Model.CEmployeeEarningMappingModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface IEmployeeEarningMappingModelRepository extends JpaRepository<CEmployeeEarningMappingModel, Integer> {

	@Modifying
	@Transactional
	@Query(value = "update hm_employee_earning_mapping set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where company_id = ?1 and employee_id = ?2 and is_delete = 0", nativeQuery = true)
	void updateEarningRecord(int company_id, int employee_id);

	@Query(value = "FROM CEmployeeEarningMappingModel model where model.is_delete = 0 and model.employee_id = ?1")
	List<CEmployeeEarningMappingModel> FnShowErningMapingRecords(int employee_id);

	@Modifying
	@Transactional
	@Query(value = "update CEmployeeEarningMappingModel model set model.is_delete = 1, model.deleted_on = CURRENT_TIMESTAMP(), model.modified_by = ?3 where model.company_id = ?2 and model.employee_id = ?1 and model.is_delete = 0")
	void updateEarningStatus(int employee_id, int company_id, String created_by);

}
