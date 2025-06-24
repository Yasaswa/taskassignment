package com.erp.HmShiftManagement.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Duration;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ht_attendance_daily")
public class CHtAttendanceDailyModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "daily_attendance_id")
	private Integer daily_attendance_id;
	private int company_id;
	private Integer company_branch_id;
	private Integer employee_id;
	private String employee_code;
	private String employee_type;
	private Integer attendance_date;
	private Integer attendance_month;
	private Integer attendance_year;
	private Integer shift_id;
	private String shift_scheduled;
	private String shift_present;
	private Integer job_type_id;
	private String job_type;
	private String in_time;
	private String out_time;
	private String working_minutes;
	private String late_mark_flag;
	private String early_go_flag;
	private String gate_pass_flag;
	private String week_off_present_flag;
	private String night_shift_present_flag;
	private boolean is_active = Boolean.TRUE;
	private boolean is_delete = Boolean.FALSE;
	@Column(name = "created_by", updatable = false)
	private String created_by;
	@CreationTimestamp
	@Column(name = "created_on", updatable = false)
	private Date created_on;
	private String modified_by;
	@UpdateTimestamp
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;
	private String remark;
	private String att_date_time;
	private String present_flag;
	private String attendance_flag;

	// Required Fields Employee WorkerForJobType;
	private Integer job_type_code_id;
	private String job_type_code_short_name;
	private Double current_day_salary;

	// Required Fields but don't store it;
	@Transient
	private Boolean already_added_attendance;
	@Transient
	private String already_added_present_shift;
	@Transient
	private Integer already_added_job_type_code_id;
	@Transient
	private String already_added_job_type_code_short_name;
	@Transient
	private String already_added_worked_hours;
	@Transient
	private Double job_type_code_perhour_rate;
	@Transient
	private Double job_type_code_perday_rate;

	@Transient
	private String machine_employee_code;
	@Transient
	private String old_employee_code;
	@Transient
	private Integer department_id;
	@Transient
	private Integer sub_department_id;
	@Transient
	private String weeklyoff_name;
	@Transient
	private Double halfday_hours;
	@Transient
	private Double fullday_hours;
	@Transient
	private Double grace_early_time;
	@Transient
	private Double grace_late_time;
	@Transient
	private String shift_start_end_time;
	@Transient
	private boolean two_day_shift = Boolean.FALSE;
	@Transient
	private boolean attendance_exclude_flag = Boolean.FALSE;
	@Transient
	private String employee_type_group;
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

