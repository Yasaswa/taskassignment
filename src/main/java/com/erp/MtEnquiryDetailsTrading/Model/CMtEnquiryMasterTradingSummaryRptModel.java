package com.erp.MtEnquiryDetailsTrading.Model;

import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@Immutable
@Subselect("select * from  mtv_enquiry_master_trading_summary_rpt")
public class CMtEnquiryMasterTradingSummaryRptModel {


	@Id
	private String enquiry_master_transaction_id;
	private String company_name;
	private String company_branch_name;
	private String financial_year;
	private String enquiry_type;
	private String enquiry_type_short_name;
	private String enquiry_life_desc;
	private String enquiry_no;
	private String enquiry_date;
	private String enquiry_version;
	private String customer_name;
	private String customer_email;
	private String customer_country_name;
	private String customer_state_name;
	private String customer_city_name;
	private String customer_district_name;
	private String expected_branch_name;
	private String expected_branch_short_name;
	private String expected_branch_address1;
	private String expected_branch_pincode;
	private String expected_branch_city_name;
	private String expected_branch_district_name;
	private String expected_branch_state_name;
	private String expected_branch_country_name;
	private String expected_branch_phone_no;
	private String expected_branch_EmailId;
	private String enquiry_by_name;
	private String assign_to_head_name;
	private String assign_to_name;
	private String approved_by_name;
	private String approved_date;
	private String overall_schedule_date;
	private String agent_name;
	private String agent_percent;
	private String enquiry_mail_sent_status;
	private String enquiry_mail_sent_status_desc;
	private String enquiry_status;
	private String enquiry_status_desc;
	private String status_remark;
	private String other_terms_conditions;
	private String remark;
	private String is_active;
	private String is_delete;
	private String created_by;
	private String created_on;
	private String modified_by;
	private String modified_on;
	private String deleted_by;
	private String deleted_on;
	private String company_id;
	private String company_branch_id;
	private String enquiry_type_id;
	private String customer_id;
	private String customer_contacts_ids;
	private String customer_state_id;
	private String customer_city_id;
	private String expected_branch_id;
	private String expected_branch_state_id;
	private String expected_branch_city_id;
	private String enquiry_by_id;
	private String assign_to_head_id;
	private String assign_to_id;
	private String approved_by_id;
	private String agent_id;

	public String getEnquiry_master_transaction_id() {
		return enquiry_master_transaction_id;
	}

	public void setEnquiry_master_transaction_id(String enquiry_master_transaction_id) {
		this.enquiry_master_transaction_id = enquiry_master_transaction_id;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getCompany_branch_name() {
		return company_branch_name;
	}

	public void setCompany_branch_name(String company_branch_name) {
		this.company_branch_name = company_branch_name;
	}

	public String getFinancial_year() {
		return financial_year;
	}

	public void setFinancial_year(String financial_year) {
		this.financial_year = financial_year;
	}

	public String getEnquiry_type() {
		return enquiry_type;
	}

	public void setEnquiry_type(String enquiry_type) {
		this.enquiry_type = enquiry_type;
	}

	public String getEnquiry_type_short_name() {
		return enquiry_type_short_name;
	}

	public void setEnquiry_type_short_name(String enquiry_type_short_name) {
		this.enquiry_type_short_name = enquiry_type_short_name;
	}

	public String getEnquiry_life_desc() {
		return enquiry_life_desc;
	}

	public void setEnquiry_life_desc(String enquiry_life_desc) {
		this.enquiry_life_desc = enquiry_life_desc;
	}

	public String getEnquiry_no() {
		return enquiry_no;
	}

	public void setEnquiry_no(String enquiry_no) {
		this.enquiry_no = enquiry_no;
	}

	public String getEnquiry_date() {
		return enquiry_date;
	}

	public void setEnquiry_date(String enquiry_date) {
		this.enquiry_date = enquiry_date;
	}

	public String getEnquiry_version() {
		return enquiry_version;
	}

	public void setEnquiry_version(String enquiry_version) {
		this.enquiry_version = enquiry_version;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public String getCustomer_email() {
		return customer_email;
	}

	public void setCustomer_email(String customer_email) {
		this.customer_email = customer_email;
	}

	public String getCustomer_country_name() {
		return customer_country_name;
	}

	public void setCustomer_country_name(String customer_country_name) {
		this.customer_country_name = customer_country_name;
	}

	public String getCustomer_state_name() {
		return customer_state_name;
	}

	public void setCustomer_state_name(String customer_state_name) {
		this.customer_state_name = customer_state_name;
	}

	public String getCustomer_city_name() {
		return customer_city_name;
	}

	public void setCustomer_city_name(String customer_city_name) {
		this.customer_city_name = customer_city_name;
	}

	public String getCustomer_district_name() {
		return customer_district_name;
	}

	public void setCustomer_district_name(String customer_district_name) {
		this.customer_district_name = customer_district_name;
	}

	public String getExpected_branch_name() {
		return expected_branch_name;
	}

	public void setExpected_branch_name(String expected_branch_name) {
		this.expected_branch_name = expected_branch_name;
	}

	public String getExpected_branch_short_name() {
		return expected_branch_short_name;
	}

	public void setExpected_branch_short_name(String expected_branch_short_name) {
		this.expected_branch_short_name = expected_branch_short_name;
	}

	public String getExpected_branch_address1() {
		return expected_branch_address1;
	}

	public void setExpected_branch_address1(String expected_branch_address1) {
		this.expected_branch_address1 = expected_branch_address1;
	}

	public String getExpected_branch_pincode() {
		return expected_branch_pincode;
	}

	public void setExpected_branch_pincode(String expected_branch_pincode) {
		this.expected_branch_pincode = expected_branch_pincode;
	}

	public String getExpected_branch_city_name() {
		return expected_branch_city_name;
	}

	public void setExpected_branch_city_name(String expected_branch_city_name) {
		this.expected_branch_city_name = expected_branch_city_name;
	}

	public String getExpected_branch_district_name() {
		return expected_branch_district_name;
	}

	public void setExpected_branch_district_name(String expected_branch_district_name) {
		this.expected_branch_district_name = expected_branch_district_name;
	}

	public String getExpected_branch_state_name() {
		return expected_branch_state_name;
	}

	public void setExpected_branch_state_name(String expected_branch_state_name) {
		this.expected_branch_state_name = expected_branch_state_name;
	}

	public String getExpected_branch_country_name() {
		return expected_branch_country_name;
	}

	public void setExpected_branch_country_name(String expected_branch_country_name) {
		this.expected_branch_country_name = expected_branch_country_name;
	}

	public String getExpected_branch_phone_no() {
		return expected_branch_phone_no;
	}

	public void setExpected_branch_phone_no(String expected_branch_phone_no) {
		this.expected_branch_phone_no = expected_branch_phone_no;
	}

	public String getExpected_branch_EmailId() {
		return expected_branch_EmailId;
	}

	public void setExpected_branch_EmailId(String expected_branch_EmailId) {
		this.expected_branch_EmailId = expected_branch_EmailId;
	}

	public String getEnquiry_by_name() {
		return enquiry_by_name;
	}

	public void setEnquiry_by_name(String enquiry_by_name) {
		this.enquiry_by_name = enquiry_by_name;
	}

	public String getAssign_to_head_name() {
		return assign_to_head_name;
	}

	public void setAssign_to_head_name(String assign_to_head_name) {
		this.assign_to_head_name = assign_to_head_name;
	}

	public String getAssign_to_name() {
		return assign_to_name;
	}

	public void setAssign_to_name(String assign_to_name) {
		this.assign_to_name = assign_to_name;
	}

	public String getApproved_by_name() {
		return approved_by_name;
	}

	public void setApproved_by_name(String approved_by_name) {
		this.approved_by_name = approved_by_name;
	}

	public String getApproved_date() {
		return approved_date;
	}

	public void setApproved_date(String approved_date) {
		this.approved_date = approved_date;
	}

	public String getOverall_schedule_date() {
		return overall_schedule_date;
	}

	public void setOverall_schedule_date(String overall_schedule_date) {
		this.overall_schedule_date = overall_schedule_date;
	}

	public String getAgent_name() {
		return agent_name;
	}

	public void setAgent_name(String agent_name) {
		this.agent_name = agent_name;
	}

	public String getAgent_percent() {
		return agent_percent;
	}

	public void setAgent_percent(String agent_percent) {
		this.agent_percent = agent_percent;
	}

	public String getEnquiry_mail_sent_status() {
		return enquiry_mail_sent_status;
	}

	public void setEnquiry_mail_sent_status(String enquiry_mail_sent_status) {
		this.enquiry_mail_sent_status = enquiry_mail_sent_status;
	}

	public String getEnquiry_mail_sent_status_desc() {
		return enquiry_mail_sent_status_desc;
	}

	public void setEnquiry_mail_sent_status_desc(String enquiry_mail_sent_status_desc) {
		this.enquiry_mail_sent_status_desc = enquiry_mail_sent_status_desc;
	}

	public String getEnquiry_status_desc() {
		return enquiry_status_desc;
	}

	public void setEnquiry_status_desc(String enquiry_status_desc) {
		this.enquiry_status_desc = enquiry_status_desc;
	}

	public String getStatus_remark() {
		return status_remark;
	}

	public void setStatus_remark(String status_remark) {
		this.status_remark = status_remark;
	}

	public String getOther_terms_conditions() {
		return other_terms_conditions;
	}

	public void setOther_terms_conditions(String other_terms_conditions) {
		this.other_terms_conditions = other_terms_conditions;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getIs_active() {
		return is_active;
	}

	public void setIs_active(String is_active) {
		this.is_active = is_active;
	}

	public String getIs_delete() {
		return is_delete;
	}

	public void setIs_delete(String is_delete) {
		this.is_delete = is_delete;
	}

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public String getCreated_on() {
		return created_on;
	}

	public void setCreated_on(String created_on) {
		this.created_on = created_on;
	}

	public String getModified_by() {
		return modified_by;
	}

	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}

	public String getModified_on() {
		return modified_on;
	}

	public void setModified_on(String modified_on) {
		this.modified_on = modified_on;
	}

	public String getDeleted_by() {
		return deleted_by;
	}

	public void setDeleted_by(String deleted_by) {
		this.deleted_by = deleted_by;
	}

	public String getDeleted_on() {
		return deleted_on;
	}

	public void setDeleted_on(String deleted_on) {
		this.deleted_on = deleted_on;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public String getCompany_branch_id() {
		return company_branch_id;
	}

	public void setCompany_branch_id(String company_branch_id) {
		this.company_branch_id = company_branch_id;
	}

	public String getEnquiry_type_id() {
		return enquiry_type_id;
	}

	public void setEnquiry_type_id(String enquiry_type_id) {
		this.enquiry_type_id = enquiry_type_id;
	}

	public String getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}

	public String getCustomer_contacts_ids() {
		return customer_contacts_ids;
	}

	public void setCustomer_contacts_ids(String customer_contacts_ids) {
		this.customer_contacts_ids = customer_contacts_ids;
	}

	public String getCustomer_state_id() {
		return customer_state_id;
	}

	public void setCustomer_state_id(String customer_state_id) {
		this.customer_state_id = customer_state_id;
	}

	public String getCustomer_city_id() {
		return customer_city_id;
	}

	public void setCustomer_city_id(String customer_city_id) {
		this.customer_city_id = customer_city_id;
	}

	public String getExpected_branch_id() {
		return expected_branch_id;
	}

	public void setExpected_branch_id(String expected_branch_id) {
		this.expected_branch_id = expected_branch_id;
	}

	public String getExpected_branch_state_id() {
		return expected_branch_state_id;
	}

	public void setExpected_branch_state_id(String expected_branch_state_id) {
		this.expected_branch_state_id = expected_branch_state_id;
	}

	public String getExpected_branch_city_id() {
		return expected_branch_city_id;
	}

	public void setExpected_branch_city_id(String expected_branch_city_id) {
		this.expected_branch_city_id = expected_branch_city_id;
	}

	public String getEnquiry_by_id() {
		return enquiry_by_id;
	}

	public void setEnquiry_by_id(String enquiry_by_id) {
		this.enquiry_by_id = enquiry_by_id;
	}

	public String getAssign_to_head_id() {
		return assign_to_head_id;
	}

	public void setAssign_to_head_id(String assign_to_head_id) {
		this.assign_to_head_id = assign_to_head_id;
	}

	public String getAssign_to_id() {
		return assign_to_id;
	}

	public void setAssign_to_id(String assign_to_id) {
		this.assign_to_id = assign_to_id;
	}

	public String getApproved_by_id() {
		return approved_by_id;
	}

	public void setApproved_by_id(String approved_by_id) {
		this.approved_by_id = approved_by_id;
	}

	public String getAgent_id() {
		return agent_id;
	}

	public void setAgent_id(String agent_id) {
		this.agent_id = agent_id;
	}


	public CMtEnquiryMasterTradingSummaryRptModel() {
		super();

	}

	public String getEnquiry_status() {
		return enquiry_status;
	}

	public void setEnquiry_status(String enquiry_status) {
		this.enquiry_status = enquiry_status;
	}

	public CMtEnquiryMasterTradingSummaryRptModel(String enquiry_master_transaction_id, String company_name,
	                                              String company_branch_name, String financial_year, String enquiry_type, String enquiry_type_short_name,
	                                              String enquiry_life_desc, String enquiry_no, String enquiry_date, String enquiry_version,
	                                              String customer_name, String customer_email, String customer_country_name, String customer_state_name,
	                                              String customer_city_name, String customer_district_name, String expected_branch_name,
	                                              String expected_branch_short_name, String expected_branch_address1, String expected_branch_pincode,
	                                              String expected_branch_city_name, String expected_branch_district_name, String expected_branch_state_name,
	                                              String expected_branch_country_name, String expected_branch_phone_no, String expected_branch_EmailId,
	                                              String enquiry_by_name, String assign_to_head_name, String assign_to_name, String approved_by_name,
	                                              String approved_date, String overall_schedule_date, String agent_name, String agent_percent,
	                                              String enquiry_mail_sent_status, String enquiry_mail_sent_status_desc, String enquiry_status,
	                                              String enquiry_status_desc, String status_remark, String other_terms_conditions, String remark,
	                                              String is_active, String is_delete, String created_by, String created_on, String modified_by,
	                                              String modified_on, String deleted_by, String deleted_on, String company_id, String company_branch_id,
	                                              String enquiry_type_id, String customer_id, String customer_contacts_ids, String customer_state_id,
	                                              String customer_city_id, String expected_branch_id, String expected_branch_state_id,
	                                              String expected_branch_city_id, String enquiry_by_id, String assign_to_head_id, String assign_to_id,
	                                              String approved_by_id, String agent_id) {
		super();
		this.enquiry_master_transaction_id = enquiry_master_transaction_id;
		this.company_name = company_name;
		this.company_branch_name = company_branch_name;
		this.financial_year = financial_year;
		this.enquiry_type = enquiry_type;
		this.enquiry_type_short_name = enquiry_type_short_name;
		this.enquiry_life_desc = enquiry_life_desc;
		this.enquiry_no = enquiry_no;
		this.enquiry_date = enquiry_date;
		this.enquiry_version = enquiry_version;
		this.customer_name = customer_name;
		this.customer_email = customer_email;
		this.customer_country_name = customer_country_name;
		this.customer_state_name = customer_state_name;
		this.customer_city_name = customer_city_name;
		this.customer_district_name = customer_district_name;
		this.expected_branch_name = expected_branch_name;
		this.expected_branch_short_name = expected_branch_short_name;
		this.expected_branch_address1 = expected_branch_address1;
		this.expected_branch_pincode = expected_branch_pincode;
		this.expected_branch_city_name = expected_branch_city_name;
		this.expected_branch_district_name = expected_branch_district_name;
		this.expected_branch_state_name = expected_branch_state_name;
		this.expected_branch_country_name = expected_branch_country_name;
		this.expected_branch_phone_no = expected_branch_phone_no;
		this.expected_branch_EmailId = expected_branch_EmailId;
		this.enquiry_by_name = enquiry_by_name;
		this.assign_to_head_name = assign_to_head_name;
		this.assign_to_name = assign_to_name;
		this.approved_by_name = approved_by_name;
		this.approved_date = approved_date;
		this.overall_schedule_date = overall_schedule_date;
		this.agent_name = agent_name;
		this.agent_percent = agent_percent;
		this.enquiry_mail_sent_status = enquiry_mail_sent_status;
		this.enquiry_mail_sent_status_desc = enquiry_mail_sent_status_desc;
		this.enquiry_status = enquiry_status;
		this.enquiry_status_desc = enquiry_status_desc;
		this.status_remark = status_remark;
		this.other_terms_conditions = other_terms_conditions;
		this.remark = remark;
		this.is_active = is_active;
		this.is_delete = is_delete;
		this.created_by = created_by;
		this.created_on = created_on;
		this.modified_by = modified_by;
		this.modified_on = modified_on;
		this.deleted_by = deleted_by;
		this.deleted_on = deleted_on;
		this.company_id = company_id;
		this.company_branch_id = company_branch_id;
		this.enquiry_type_id = enquiry_type_id;
		this.customer_id = customer_id;
		this.customer_contacts_ids = customer_contacts_ids;
		this.customer_state_id = customer_state_id;
		this.customer_city_id = customer_city_id;
		this.expected_branch_id = expected_branch_id;
		this.expected_branch_state_id = expected_branch_state_id;
		this.expected_branch_city_id = expected_branch_city_id;
		this.enquiry_by_id = enquiry_by_id;
		this.assign_to_head_id = assign_to_head_id;
		this.assign_to_id = assign_to_id;
		this.approved_by_id = approved_by_id;
		this.agent_id = agent_id;
	}

}
