package com.erp.MtEnquiryMasterServices.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "mt_enquiry_master_services")
public class CMtEnquiryMasterServicesModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "enquiry_master_transaction_id")
	private int enquiry_master_transaction_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String financial_year;
	private Integer enquiry_type_id;
	private String enquiry_type;
	private String enquiry_life;
	private String enquiry_no;
	private String enquiry_date;
	private Integer enquiry_version;
	private Integer customer_id;
	private String customer_contacts_ids;
	private Integer customer_state_id;
	private Integer customer_city_id;
	private Integer expected_branch_id;
	private Integer expected_branch_state_id;
	private Integer expected_branch_city_id;
	private Integer enquiry_by_id;
	private Integer assign_to_head_id;
	private Integer assign_to_id;
	private Integer department_id;
	private Integer approved_by_id;
	private String approved_date;
	private String overall_schedule_date;
	private Integer agent_id;
	private double agent_percent;
	private String enquiry_mail_sent_status;
	private String enquiry_status;
	private String status_remark;
	private String other_terms_conditions;
	private String remark;
	private boolean is_active = Boolean.TRUE;
	private boolean is_delete = Boolean.FALSE;
	private String created_by;
	@CreationTimestamp
	@Column(name = "created_on", updatable = false)
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;

	public String getEnquiry_date() {
		return enquiry_date;
	}

	public void setEnquiry_date(String enquiry_date) {
		if (enquiry_date == null || enquiry_date.isEmpty()) {

			this.enquiry_date = null;
		} else {
			this.enquiry_date = enquiry_date;

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

	public String getOverall_schedule_date() {
		return overall_schedule_date;
	}

	public void setOverall_schedule_date(String overall_schedule_date) {

		if (overall_schedule_date == null || overall_schedule_date.isEmpty()) {
			this.overall_schedule_date = null;
		} else {
			this.overall_schedule_date = overall_schedule_date;

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
