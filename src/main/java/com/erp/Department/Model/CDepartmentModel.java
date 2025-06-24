package com.erp.Department.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cm_department")
public class CDepartmentModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "department_id")
	private int department_id;
	private Integer company_id;
	private Integer company_branch_id;
	private Integer parent_department_id;
	private String cost_center_group;
	private String department_type;
	private String department_name;
	private String department_short_name;
	private String department_group;
	private int department_head_id;
	private int department_subhead_id;
	private double department_std_staff_strength;
	private double department_std_worker_strength;

	private double worker_perday_salary;
	private double trainee_worker_perday_salary;
	private double semiskillled_worker_perday_salary;
	private double worker_perday_attendance_allowance;
	private double worker_perday_night_allowance;
	private double department_req_std_staff_strength;
	private double department_req_std_worker_strength;
	private double general_worker;
	private double trainee_worker;
	private double semi_skilled_worker;
	private double skilled_worker;
	private double sr_semi_skilled_worker;
	private double helper_worker;

	private boolean is_active = Boolean.TRUE;
	private boolean is_delete = Boolean.FALSE;
	@Column(name = "created_by", updatable = false)
	private String created_by;
	@CreationTimestamp
	private Date created_on;
	private String modified_by;
	@UpdateTimestamp
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;
	private Integer godown_id;
	private Integer godown_section_id;
	private Integer godown_section_beans_id;


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
