package com.erp.Department.Repository;

import com.erp.Department.Model.CDepartmentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface IDepartmentRepository extends JpaRepository<CDepartmentModel, Integer> {

	@Modifying
	@Transactional
	@Query(value = "update cm_department set is_delete = 1 ,deleted_by = ?2, deleted_on = CURRENT_TIMESTAMP() where department_id = ?1", nativeQuery = true)
	Object FnDeleteRecord(int department_id, String deleted_by);

	@Query(nativeQuery = true, value = "SELECT * FROM cm_department WHERE department_name = ?1")
	CDepartmentModel CheckIfExist(String department_name);

	@Query(value = "Select * FROM cm_department where is_delete =0 and department_id = ?1 and company_id = ?2", nativeQuery = true)
	CDepartmentModel FnShowParticularRecordForUpdate(int department_id, int company_id);

	
	@Query(nativeQuery = true, value = "SELECT * FROM cm_department WHERE (department_name = ?1 OR (?2 IS NOT NULL AND department_short_name = ?2)) and department_type = ?3 and company_id = ?4 and is_delete = 0")
	CDepartmentModel getCheck(String department_name, String department_short_name, String department_type, Integer company_id);


}
