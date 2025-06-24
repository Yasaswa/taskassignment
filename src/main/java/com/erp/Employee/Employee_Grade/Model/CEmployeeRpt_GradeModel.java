package com.erp.Employee.Employee_Grade.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Immutable
@Subselect("select * from cmv_employee_grade_rpt")
public class CEmployeeRpt_GradeModel {
	@Id
	private String employee_grade_id;
	private String employee_grade_name;
	private String company_name;
	private String is_delete;
	private String is_active;
	private String created_by;
	private String created_on;
	private String modified_by;
	private String modified_on;
	private String deleted_by;
	private String deleted_on;
	private String company_id;
	private String field_name;
	private String field_id;

}
