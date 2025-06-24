package com.erp.HmShiftManagement.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ht_attendance_monthly_job_type_new")
public class CHtAttendanceMonthlyJobTypeNewModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "attendance_job_type_wise_process_id")
	private int attendance_job_type_wise_process_id;

	private Integer company_id;
	private Integer company_branch_id;
	private Integer attendance_process_id;
	private Date process_date;
	private String employee_type;
	private Integer employee_id;
	private String employee_code;
	private Integer attendance_month;
	private Integer attendance_year;
	private Integer department_id;
	private Integer sub_department_id;
	private Integer job_type_id;
	private String job_type_short_name;
	private double job_type_total_days;
	private double job_type_night_total_days;
	private double job_type_attendance_total_days;
	private double job_type_attendance_total_hours;
	private double job_type_rate;
	private double job_type_night_allowance;
	private double job_type_attendance_allowance;
	private Double job_type_total_salary = 0.0;
	private boolean is_active;
	private boolean is_delete;
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


