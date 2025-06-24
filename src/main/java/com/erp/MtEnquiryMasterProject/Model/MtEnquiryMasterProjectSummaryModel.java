package com.erp.MtEnquiryMasterProject.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Immutable
@Subselect("select * from mtv_enquiry_master_project_summary")
public class MtEnquiryMasterProjectSummaryModel {

	@Id
	private int enquiry_master_transaction_id;
	private String enquiry_type;
	private String enquiry_life_desc;
	private String enquiry_no;
	private String enquiry_date;
	private int enquiry_version;
	private String enquiry_status_desc;
	private String enquiry_mail_sent_status_desc;
	private String department_name;
	private String customer_name;
	private String customer_state_name;
	private String project_name;
	private String project_referance_number;
	private String project_start_date;
	private String project_end_date;
	private String enquiry_by_name;
	private String assign_to_head_name;
	private String assign_to_name;
	private String approved_by_name;
	private String approved_date;
	private String agent_name;
	private String agent_percent;
	private String overall_schedule_date;
	private String expected_branch_state_name;
	private String expected_branch_name;
	private String enquiry_type_short_name;
	private String expected_branch_address1;
	private String expected_branch_pincode;
	private String expected_branch_city_name;
	private String expected_branch_district_name;
	private String expected_branch_country_name;
	private String cust_branch_pan_no;
	private String cust_branch_phone_no;
	private String expected_branch_phone_no;
	private String cust_branch_address1;
	private String customer_city_name;
	private String expected_branch_EmailId;
	private String customer_district_name;
	private boolean is_active = Boolean.TRUE;
	private boolean is_delete = Boolean.FALSE;
	private String status_remark;
	private String created_by;
	private String other_terms_conditions;
	private Date created_on;
	private String modified_by;
	private String remark;
	private Date modified_on;
	private String deleted_by;
	private String company_name;
	private Date deleted_on;
	private String enquiry_life;
	private String company_branch_name;
	private int company_id;
	private int company_branch_id;
	private String company_address1;
	private int enquiry_type_id;
	private int customer_id;
	private String financial_year;
	private int customer_contacts_ids;
	private int customer_state_id;
	private String enquiry_status;
	private int customer_city_id;
	private int expected_branch_id;
	private String enquiry_mail_sent_status;
	private String cust_branch_gst_no;
	private int expected_branch_state_id;
	private int expected_branch_city_id;
	private String customer_email;
	//	y_na
//			enquiry_by_id
//	assign_to_head_id
//	private String customer_countr
//	assign_to_id
//			approved_by_id
	private String cust_branc;
	private String agent_id;
	private int department_id;
	private Integer field_id;
	private String field_name;

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
