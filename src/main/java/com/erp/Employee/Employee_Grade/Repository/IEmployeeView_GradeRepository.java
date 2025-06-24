package com.erp.Employee.Employee_Grade.Repository;

import com.erp.Employee.Employee_Grade.Model.CEmployeeView_GradeModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface IEmployeeView_GradeRepository extends JpaRepository<CEmployeeView_GradeModel, Long> {

	@Query(value = "FROM  CEmployeeView_GradeModel model where model.is_delete = 0 AND model.employee_grade_id = ?1 AND model.company_id = ?2")
	CEmployeeView_GradeModel FnShowParticularRecordForUpdate( int employee_grade_id,int company_id);
}
