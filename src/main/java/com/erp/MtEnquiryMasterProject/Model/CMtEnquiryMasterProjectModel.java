package com.erp.MtEnquiryMasterProject.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "mt_enquiry_master_project")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CMtEnquiryMasterProjectModel {

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
	private Date enquiry_date;
	private Integer enquiry_version;
	private Integer customer_id;
	private String customer_contacts_ids;
	private Integer customer_state_id;
	private Integer customer_city_id;
	private Integer expected_branch_id;
	private Integer expected_branch_state_id;
	private Integer expected_branch_city_id;
	private String project_name;
	private String project_referance_number;
	private Date project_start_date;
	private Date project_end_date;
	private Integer enquiry_by_id;
	private Integer assign_to_head_id;
	private Integer assign_to_id;
	private Integer department_id;
	private Integer approved_by_id;
	private Date approved_date;
	private Date overall_schedule_date;
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

