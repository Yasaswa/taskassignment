package com.erp.Employee.Employees.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Immutable
@Table(name = "cmv_employee")
public class CEmployeesViewModel {

	@Id
	private int employee_id;
	private String employee_type_group;
	private String employee_code;
	private String employee_type;
	private String salutation;
	private String employee_name;
	private String employee_status;
	private String department_name;
	private String sub_department_name;
	private String designation_name;
	private String reporting_to_name;
	private String shift_name;
	private String current_shift_name;
	private String shift_start_end_time;
	private String current_shift_start_end_time;
	private String weeklyoff_name;
	private String email_id1;
	private String cell_no1;
	private String date_joining;
	private String date_exit;
	private String contractor_name;
	private String field_name;
	private Integer field_id;
	private Double ctc;
	private Double salary;
	private Boolean ot_flag;
	private String gender;
	private String aadhar_card_no;
	private String passport_no;
	private String pan_no;
	private String driving_licence;
	private String current_address;
	private String current_pincode;
	private String city_name;
	private String district_name;
	private String state_name;
	private String country;
	private String permanant_address;
	private String permanant_pincode;
	private String date_of_birth;
	private String email_id2;
	private String phone_no;
	private String cell_no2;
	private String bank_name1;
	// private String bank_branch_name1;
	private String account_no1;
	private String account_name1;
	private String ifsc_code1;
	private String marital_status;
	private String reference;
	private String religion;
	private String employee_caste;
	private String employee_category;
	private String destination_name;
	private String blood_group;
	private String finance_account_no;
	private String contract_startdate;
	private String contract_enddate;
	private String department_group;
	private String employee_band_name;
	private String employee_grade_name;
	private String cost_center_name;
	private Boolean bond_applicable = Boolean.TRUE;
	private String current_job;

	// Added JobType's related fields; on 30/07/24;
	private Integer job_type_id;
	private String job_type_short_name;
	private Double job_type_rate;
	private Double job_type_attendance_allowance;
	private Double job_type_night_allowance;
	private Double job_type_special_allowance;

	private String machine_employee_code;
	private String username;
	private String password;
	private String last_name;
	private String first_name;
	private String middle_name;
	private String bank_id2;
	private String bank_name2;
	// private String bank_branch_name2;
	private String account_no2;
	private String account_name2;
	private String ifsc_code2;
	private String company_name;
	private String company_branch_name;
	private Boolean is_active = Boolean.TRUE;
	private Boolean is_delete = Boolean.FALSE;
	private Date created_on;
	private String created_by;
	private Date modified_on;
	private String modified_by;
	private Date deleted_on;
	private String deleted_by;
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
	//	private Integer subdepartment_group_id;
	private Integer sub_department_id;
	private Integer designation_id;
	private Integer weeklyoff;
	private Integer shift_id;
	private Integer current_shift_id;
	private Integer cost_center_id;
	private Integer profit_center_id;
	private Integer reporting_to;
	private Integer region_id;
	private Integer employee_salary_id;
	private Integer band_id;
	private Integer grade_id;
	private String left_suspended_date;
	private String father_name;
	private String mother_name;
	private String spouse_name;
	private String son_name;
	private String daughter_name;
	private String father_dob;
	private String mother_dob;
	private String spouse_dob;
	private String son_dob;
	private String daughter_dob;
	private String old_employee_code;
	private String resident_type;
	/*
	 * START Removed this field if used 1.1 DB changes made By Dipti (ERP Testing
	 * 1.1)
	 */
	private String bank_id1;
	// private String pf_no;
//	private String esic_no;
	/*
	 * END Removed this field if used 1.1 DB changes made By Dipti (ERP Testing 1.1)
	 */
	private Double ot_amount;
	private Boolean gratuity_applicable;
	private Boolean pf_flag;
	private String uan_no;
	private Date pf_date;
	private Boolean esic_flag;
	private Date esic_date;
	private Boolean mlwf_flag;
	private String mlwf_no;
	private String image_path;
	private Integer shift_grace_hours_min;
	private Integer shift_grace_hours_max;
	private Double halfday_hours;
	private Double fullday_hours;
	private Double grace_early_time;
	private Double grace_late_time;
	private boolean two_day_shift = Boolean.FALSE;
	private Integer current_shift_grace_hours_min;
	private Integer current_shift_grace_hours_max;
	private Double current_halfday_hours;
	private Double current_fullday_hours;
	private Double current_grace_early_time;
	private Double current_grace_late_time;
	private boolean current_two_day_shift = Boolean.FALSE;
	private boolean attendance_exclude_flag = Boolean.FALSE;

	@Transient
	private String encodedImage = null;

	public boolean isBond_applicable() {
		return bond_applicable;
	}

	public void setBond_applicable(boolean bond_applicable) {
		this.bond_applicable = bond_applicable;
	}

	public Boolean getIs_active() {
		return is_active;
	}

	public void setIs_active(Boolean is_active) {
		this.is_active = is_active;
	}

	public Boolean getIs_delete() {
		return is_delete;
	}

	public void setIs_delete(Boolean is_delete) {
		this.is_delete = is_delete;
	}

}
