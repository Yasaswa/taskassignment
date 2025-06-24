package com.erp.Department.Repository;

import com.erp.Department.Model.CDepartmentModel;
import com.erp.Department.Model.CDepartmentViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface IDepartmentViewRepository extends JpaRepository<CDepartmentViewModel, Long> {

	@Query(value = "Select * FROM  cmv_department order by department_id Desc", nativeQuery = true)
	Page<CDepartmentViewModel> FnShowAllRecords(Pageable pageable);

	@Query(value = "Select * FROM  cmv_department where is_delete =0  order by department_id Desc", nativeQuery = true)
	Page<CDepartmentViewModel> FnShowAllActiveRecords(Pageable pageable);


	@Query(nativeQuery = true, value = "Select * FROM  cmv_department where is_delete =0 and company_id = ?1 and company_branch_id = ?2 and department_id = ?3")
	CDepartmentViewModel FnShowParticularRecord(int company_id, int company_branch_id, int department_id);

	@Query(value = "Select * FROM  cmv_department where is_delete =0 and department_id = ?1", nativeQuery = true)
	CDepartmentModel FnShowParticularRecordForUpdate(int department_id);

	@Query(value = "SELECT * FROM cmv_department_rpt", nativeQuery = true)
	Page<Map<String, Object>> FnShowAllReportRecords(Pageable pageable);

	@Query(value = "Select * FROM cmv_department where is_delete =0 ", nativeQuery = true)
	List<CDepartmentViewModel> FnGetAllDepartments();
}
