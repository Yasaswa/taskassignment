package com.erp.Employee.Employees.Model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cm_employee")
public class CEmployeesModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "employee_id")
	private int employee_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String employee_code;
	private String machine_employee_code;
	private String employee_type_group;
	private String employee_type;
	private String salutation;
	private String last_name;
	private String first_name;
	private String middle_name;
	private String employee_name;
	private String aadhar_card_no;
	private String passport_no;
	private String pan_no;
	private String current_address;
	private String current_pincode;
	private Integer city_id;
	private Integer district_id;
	private Integer state_id;
	private String country;
	private String permanant_address;
	private String permanant_pincode;
	private String date_of_birth;
	private String email_id1;
	private String email_id2;
	private String phone_no;
	private String cell_no1;
	private String cell_no2;
	private String bank_id1;
	private String account_no1;
	private String account_name1;
	private String ifsc_code1;
	private String bank_id2;
	private String account_no2;
	private String account_name2;
	private String ifsc_code2;
	private String marital_status;
	private String reference;
	private Integer destination_id;
	private String religion;
	private String category;
	private String caste;
	private String gender;
	private String blood_group;
	private String driving_licence;
	private String finance_account_no;
	private String username;
	private String password;
	private String image_path;
	private String employee_status;
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

	public void setDate_of_birth(String date_of_birth) {
		if (date_of_birth == null || date_of_birth.isEmpty()) {
			this.date_of_birth = null;
		} else {
			this.date_of_birth = date_of_birth;
		}
	}

	public String getLeft_suspended_date() {
		return left_suspended_date;
	}

	public void setLeft_suspended_date(String left_suspended_date) {
		if (left_suspended_date == null || left_suspended_date.isEmpty()) {
			this.left_suspended_date = null;
		} else {
			this.left_suspended_date = left_suspended_date;
		}
	}

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
