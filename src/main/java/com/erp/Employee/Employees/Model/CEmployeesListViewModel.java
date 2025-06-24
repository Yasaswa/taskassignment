package com.erp.Employee.Employees.Model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Immutable
@Subselect("Select * From cmv_employee_list")
public class CEmployeesListViewModel {
	
	@Id
	private Integer employee_id;
	private String employee_code;
	private String employee_type;
	private String employee_name;
	private String employee_status;
	private String username;
	private String password;
	private String department_name;
	private String designation_name;
	private String reporting_to_name;
	private boolean is_active;
	private boolean is_delete;
	private Integer company_id;
	private Integer company_branch_id;
	private Integer contractor_id;
	private Integer department_group_id;
	private Integer department_id;
	private Integer subdepartment_group_id;
	private Integer designation_id;
	private Integer weeklyoff;
	private Integer shift_id;
	private Integer reporting_to;
	private String field_name;
	private Integer field_id;
	private String company_name;
	private String company_branch_name;
	
	public boolean isIs_active() {
		return is_active;
	}
	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
	}
	public boolean isIs_delete() {
		return is_delete;
	}
	public void setIs_delete(boolean is_delete) {
		this.is_delete = is_delete;
	}
	
	

}
