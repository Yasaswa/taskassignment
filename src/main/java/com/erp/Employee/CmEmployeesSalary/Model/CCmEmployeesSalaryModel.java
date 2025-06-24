package com.erp.Employee.CmEmployeesSalary.Model;

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
@Table(name = "cm_employees_salary")
public class CCmEmployeesSalaryModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "employee_salary_id")
	private int employee_salary_id;
	private int company_id;
	private int company_branch_id;
	private Integer employee_id;
	private Integer grade_id;
	private Integer band_id;
	private double ctc;
	private double salary;
	private boolean ot_flag;
	private double ot_amount;
	private boolean pf_flag;
	private String pf_no;
	private String pf_date;
	private boolean esic_flag;
	private String esic_no;
	private String esic_date;
	private String uan_no;
	private boolean mlwf_flag;
	private String mlwf_no;
	private boolean gratuity_applicable;
	private boolean is_active;
	private boolean is_delete;
	private String created_by;
	@CreationTimestamp
	@Column(name = "created_on", updatable = false)
	private Date created_on;
	private String modified_by;
	@UpdateTimestamp
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;

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


	public String getPf_date() {
		return pf_date;
	}

	public void setPf_date(String pf_date) {
		if (pf_date == null || pf_date.isEmpty()) {
			this.pf_date = null;
		} else {
			this.pf_date = pf_date;
		}

	}

	public String getEsic_date() {
		return esic_date;
	}

	public void setEsic_date(String esic_date) {
		if (esic_date == null || esic_date.isEmpty()) {
			this.esic_date = null;
		} else {
			this.esic_date = esic_date;
		}
	}
}
