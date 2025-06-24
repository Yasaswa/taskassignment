package com.erp.HmLeavesApplications.Model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "hm_leaves_applications")
public class CHmLeavesApplicationsModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "leaves_transaction_id")
	private int leaves_transaction_id;
	private String leaves_applications_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String leaves_application_date;
	private String financial_year;
	private String employee_type;
	private Integer employee_id;
	private Integer reporting_to;
	private String employee_code;
	private Integer department_id;
	private Integer sub_department_id;
	private Integer leave_type_id;
	private String leaves_requested_from_date;
	private String leaves_requested_to_date;
	private String leaves_approved_from_date;
	private String leaves_approved_to_date;
	private double leaves_applied_days;
	private double leaves_sanction_days;
	private double leaves_rejection_days;
	private String work_handover_id;
	private String approved_date;
	private String applied_by_id;
	private String leave_status;
	private String leave_reason;
	private String leave_approve_remark;
	private String leave_sandwich;
	private String punch_code;
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
	private Integer approved_by_id;


	@Transient
	private String short_financial_year;
	@Transient
	private String short_company;

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

	public String getLeaves_application_date() {
		return leaves_application_date;
	}

	public void setLeaves_application_date(String leaves_application_date) {
		if (leaves_application_date == null || leaves_application_date.isEmpty()) {
			this.leaves_application_date = null;
		} else {
			this.leaves_application_date = leaves_application_date;
		}
	}

	public String getLeaves_requested_from_date() {
		return leaves_requested_from_date;
	}

	public void setLeaves_requested_from_date(String leaves_requested_from_date) {
		if (leaves_requested_from_date == null || leaves_requested_from_date.isEmpty()) {
			this.leaves_requested_from_date = null;
		} else {
			this.leaves_requested_from_date = leaves_requested_from_date;
		}
	}

	public String getLeaves_requested_to_date() {
		return leaves_requested_to_date;
	}

	public void setLeaves_requested_to_date(String leaves_requested_to_date) {
		if (leaves_requested_to_date == null || leaves_requested_to_date.isEmpty()) {
			this.leaves_requested_to_date = null;
		} else {
			this.leaves_requested_to_date = leaves_requested_to_date;
		}
	}

	public String getLeaves_approved_from_date() {
		return leaves_approved_from_date;
	}

	public void setLeaves_approved_from_date(String leaves_approved_from_date) {
		if (leaves_approved_from_date == null || leaves_approved_from_date.isEmpty()) {
			this.leaves_approved_from_date = null;
		} else {
			this.leaves_approved_from_date = leaves_approved_from_date;
		}
	}

	public String getLeaves_approved_to_date() {
		return leaves_approved_to_date;
	}

	public void setLeaves_approved_to_date(String leaves_approved_to_date) {
		if (leaves_approved_to_date == null || leaves_approved_to_date.isEmpty()) {
			this.leaves_approved_to_date = null;
		} else {
			this.leaves_approved_to_date = leaves_approved_to_date;
		}
	}

	public String getApproved_date() {
		return approved_date;
	}

	public void setApproved_date(String approved_date) {
		if (approved_date == null || approved_date.isEmpty()) {
			this.approved_date = null;
		} else {
			this.approved_date = approved_date;
		}
	}

}
