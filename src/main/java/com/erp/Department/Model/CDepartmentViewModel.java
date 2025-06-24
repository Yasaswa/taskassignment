package com.erp.Department.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Immutable
@Subselect("select * from cmv_department")
public class CDepartmentViewModel {
	@Id
	private int department_id;
	private String department_group;
	private String department_cost_center_name;
	private String department_profit_center_name;
	private String parent_department;
	private String department_name;
	private String department_head;
	private String department_subhead;
	private double department_std_staff_strength;
	private double department_std_worker_strength;

	private double worker_perday_salary;
	private double trainee_worker_perday_salary;
	private double semiskillled_worker_perday_salary;
	private double worker_perday_attendance_allowance;
	private double worker_perday_night_allowance;

	private String department_short_name;
	private String department_type_desc;
	private String Active;
	private String Deleted;
	private String company_name;
	private String company_branch_name;
	private String department_type;
	private String cost_center_short_name;
	private String profit_center_short_name;
	private boolean is_delete = Boolean.FALSE;
	private boolean is_active = Boolean.TRUE;
	private Date created_on;
	private String created_by;
	private Date modified_on;
	private String modified_by;
	private Date deleted_on;
	private String deleted_by;
	private Integer company_id;
	private Integer company_branch_id;
	private Integer parent_department_id;
	private String cost_center_group;
	private Integer department_head_id;
	private Integer department_subhead_id;
	private String field_name;
	private Integer field_id;
	private double department_req_std_staff_strength;
	private double department_req_std_worker_strength;
	private double general_worker;
	private double trainee_worker;
	private double semi_skilled_worker;
	private double skilled_worker;
	private double sr_semi_skilled_worker;
	private double helper_worker;
	private Integer godown_name;
	private Integer godown_section_name;
	private Integer godown_section_beans_name;
	private Integer godown_id;
	private Integer godown_section_id;
	private Integer godown_section_beans_id;


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
