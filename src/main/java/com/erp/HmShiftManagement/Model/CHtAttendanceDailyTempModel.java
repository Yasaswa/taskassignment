package com.erp.HmShiftManagement.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Transient;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CHtAttendanceDailyTempModel {
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
	private String created_by;
	private Date created_on;
	private String modified_by;
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

	private String shift_start_end_time;
	private Integer department_id;
	private Integer sub_department_id;

}
