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
@Table(name = "ht_attendance_monthly")
public class CHtAttendanceMonthlyModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "attendance_process_id")
	private Integer attendance_process_id;
	private int company_id;
	private Integer company_branch_id;
	private Date process_date;
	private String employee_type;
	private Integer employee_id;
	private String employee_code;
	private Integer attendance_month;
	private Integer attendance_year;
	private Integer department_id;
	private Integer sub_department_id;
	private String in_out_time1;
	private String presenty1;
	private String in_out_time2;
	private String presenty2;
	private String in_out_time3;
	private String presenty3;
	private String in_out_time4;
	private String presenty4;
	private String in_out_time5;
	private String presenty5;
	private String in_out_time6;
	private String presenty6;
	private String in_out_time7;
	private String presenty7;
	private String in_out_time8;
	private String presenty8;
	private String in_out_time9;
	private String presenty9;
	private String in_out_time10;
	private String presenty10;
	private String in_out_time11;
	private String presenty11;
	private String in_out_time12;
	private String presenty12;
	private String in_out_time13;
	private String presenty13;
	private String in_out_time14;
	private String presenty14;
	private String in_out_time15;
	private String presenty15;
	private String in_out_time16;
	private String presenty16;
	private String in_out_time17;
	private String presenty17;
	private String in_out_time18;
	private String presenty18;
	private String in_out_time19;
	private String presenty19;
	private String in_out_time20;
	private String presenty20;
	private String in_out_time21;
	private String presenty21;
	private String in_out_time22;
	private String presenty22;
	private String in_out_time23;
	private String presenty23;
	private String in_out_time24;
	private String presenty24;
	private String in_out_time25;
	private String presenty25;
	private String in_out_time26;
	private String presenty26;
	private String in_out_time27;
	private String presenty27;
	private String in_out_time28;
	private String presenty28;
	private String in_out_time29;
	private String presenty29;
	private String in_out_time30;
	private String presenty30;
	private String in_out_time31;
	private String presenty31;
	private double month_days;
	private double present_days;
	private double ot_days;
	private double leaves_days;
	private double od_days;
	private double half_days;
	private double holiday_days;
	private double week_off_days;
	private double coff_days;
	private double absent_days;
	private double total_salary_days;
	private double monthly_hours;
	private double night_days;
	private String attendance_flag;
	private double  total_latemarks;
	private double total_latemarks_days;

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

	//	SALARY CALCULATIONS BY USING PRESENT_DAYS, HALF_DAYS, HOLIDAY_DAYS, COFF_DAYS, WEEK_OFF_DAYS

//	public double calculateTotalSalaryDays(boolean attendance_exclude_flag) {
//		return this.present_days + this.half_days / 2 + this.holiday_days + this.coff_days;
////				+ this.week_off_days;
//	}

	public double calculateTotalSalaryDays(boolean attendance_exclude_flag) {
		double totalSalaryDays = this.present_days + this.od_days + this.leaves_days;

		// if latemark then for 3 latemark deduct 1 day;
		if(totalSalaryDays > 0 && !attendance_exclude_flag){
			totalSalaryDays -= this.total_latemarks_days;
		}

		if (attendance_exclude_flag) {
			totalSalaryDays += this.half_days + this.absent_days;
		} else {
			totalSalaryDays += this.half_days / 2;
		}

		totalSalaryDays += this.holiday_days;
//				+ this.coff_days

		return totalSalaryDays;
	}


}
