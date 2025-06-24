package com.erp.Employee.Employees.Model;

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
@Subselect("select * from cmv_employee_summary")
public class CEmployeesSummaryModel {

	@Id
	private int employee_id;
	private String employee_code;
	private String employee_type;
	private String salutation;
	private String employee_name;
	private String employee_status;
	private String username;
	private String password;
	private String department_name;
	private String designation_name;
	private String reporting_to_name;
	private String shift_name;
	private String weeklyoff_name;
	private String contractor_name;
	private String machine_employee_code;
	private String employee_type_group;
	private boolean is_active;
	private boolean is_delete;
	private Integer company_id;
	private Integer company_branch_id;
	private Integer city_id;
	private Integer district_id;
	private Integer state_id;
	private Integer destination_id;
	private Integer employee_workprofile_id;
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


}
