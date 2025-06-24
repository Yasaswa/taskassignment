package com.erp.Employee.Employees_Workprofile.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "cm_employees_workprofile")
public class CEmployeesWorkprofileModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "employee_workprofile_id")
	private int employee_workprofile_id;
	private Integer company_id;
	private Integer company_branch_id;
	private Integer employee_id;
	private String date_joining;
	private String date_exit;
	private Integer contractor_id;
	private String contract_startdate;
	private String contract_enddate;
	private Integer department_group_id;
	private Integer department_id;
	private Integer sub_department_id;
	private Integer designation_id;
	private String designation_name;
	private Integer reporting_to;
	private Integer weeklyoff;
	private Integer shift_id;
	private Integer current_shift_id;
	private Integer region_id;
	private Integer cost_center_id;
	private Integer profit_center_id;
	private boolean bond_applicable = Boolean.TRUE;
	private boolean attendance_exclude_flag = Boolean.FALSE;
	private String current_job;
	private String resident_type;

	// Added JobType's related fields; on 30/07/24;
	private Integer job_type_id;
	private String job_type_short_name;

	private boolean is_delete = Boolean.FALSE;
	private boolean is_active = Boolean.TRUE;
	@CreationTimestamp
	@Column(name = "created_on", updatable = false)
	private Date created_on;
	@UpdateTimestamp
	private Date modified_on;
	private Date deleted_on;
	private String created_by;
	private String modified_by;
	private String deleted_by;

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

	public String getDate_joining() {
		return date_joining;
	}

	public void setDate_joining(String date_joining) {
		if (date_joining == null || date_joining.isEmpty()) {
			this.date_joining = null;
		} else {
			this.date_joining = date_joining;
		}
	}

	public String getDate_exit() {
		return date_exit;
	}


	public void setDate_exit(String date_exit) {
		if (date_exit == null || date_exit.isEmpty()) {
			this.date_exit = null;
		} else {
			this.date_exit = date_exit;
		}
	}

	public String getContract_startdate() {
		return contract_startdate;
	}

	public void setContract_startdate(String contract_startdate) {
		if (contract_startdate == null || contract_startdate.isEmpty()) {
			this.contract_startdate = null;
		} else {
			this.contract_startdate = contract_startdate;
		}
	}

	public String getContract_enddate() {
		return contract_enddate;
	}

	public void setContract_enddate(String contract_enddate) {
		if (contract_enddate == null || contract_enddate.isEmpty()) {
			this.contract_enddate = null;
		} else {
			this.contract_enddate = contract_enddate;
		}
	}

}
