package com.erp.Employee.Employees.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;
 
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Immutable
@Subselect("select * from cmv_employees_academic")
public class CEmployeeQualificationViewModel {

	@Id
	private int employee_id;
	private String employee_code;
	private String employee_type;
	private String employee_name;
	private String qualification;
	private String board_university;
	private Integer passing_year;
	private Double mark_percentage;
	private Integer company_id;
	private Integer company_branch_id;
	private boolean is_delete;
	private boolean is_active;
	private Integer field_id;
	private String field_name;

	public boolean isIs_delete() {
		return is_delete;
	}

	public void setIs_delete(boolean is_delete) {
		this.is_delete = is_delete;
	}

	public boolean isIs_active() {
		return is_active;
	}

	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
	}

}
