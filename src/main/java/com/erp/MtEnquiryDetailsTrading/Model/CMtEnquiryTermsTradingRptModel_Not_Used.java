package com.erp.MtEnquiryDetailsTrading.Model;

import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@Immutable
@Subselect("select * from  mtv_enquiry_terms_trading_rpt")
public class CMtEnquiryTermsTradingRptModel_Not_Used {

	@Id
	private String enquiry_terms_transaction_id;
	private String company_name;
	private String company_branch_name;
	private String enquiry_no;
	private String enquiry_date;
	private String enquiry_version;
	private String enquiry_terms_parameters_name;
	private String enquiry_terms_parameters_value;
	private String enquiry_terms_parameters_expected_value;
	private String financial_year;
	private String enquiry_life;
	private String other_terms_conditions;
	private String approved_date;
	private String overall_schedule_date;
	private String agent_percent;
	private String enquiry_status;
	private String enquiry_mail_sent_status;
	private String status_remark;
	private String remark;
	private String created_by;
	private String created_on;
	private String modified_by;
	private String modified_on;
	private String deleted_by;
	private String deleted_on;
	private String is_active;
	private String is_delete;
	private String enquiry_terms_parameters_id;
	private String company_id;
	private String company_branch_id;
	private String enquiry_master_transaction_id;
	private String customer_contacts_ids;
	private String customer_state_id;
	private String customer_city_id;
	private String expected_branch_id;
	private String enquiry_details_transaction_id;
	private String expected_branch_city_id;
	private String enquiry_by_id;
	private String assign_to_head_id;
	private String assign_to_id;
	private String approved_by_id;
	private String agent_id;
	private String enquiry_type_id;
	private String customer_id;

	public String getEnquiry_terms_transaction_id() {
		return enquiry_terms_transaction_id;
	}

	public void setEnquiry_terms_transaction_id(String enquiry_terms_transaction_id) {
		this.enquiry_terms_transaction_id = enquiry_terms_transaction_id;
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

	public String getEnquiry_terms_parameters_name() {
		return enquiry_terms_parameters_name;
	}

	public void setEnquiry_terms_parameters_name(String enquiry_terms_parameters_name) {
		this.enquiry_terms_parameters_name = enquiry_terms_parameters_name;
	}

	public String getEnquiry_terms_parameters_value() {
		return enquiry_terms_parameters_value;
	}

	public void setEnquiry_terms_parameters_value(String enquiry_terms_parameters_value) {
		this.enquiry_terms_parameters_value = enquiry_terms_parameters_value;
	}

	public String getEnquiry_terms_parameters_expected_value() {
		return enquiry_terms_parameters_expected_value;
	}

	public void setEnquiry_terms_parameters_expected_value(String enquiry_terms_parameters_expected_value) {
		this.enquiry_terms_parameters_expected_value = enquiry_terms_parameters_expected_value;
	}

	public String getFinancial_year() {
		return financial_year;
	}

	public void setFinancial_year(String financial_year) {
		this.financial_year = financial_year;
	}

	public String getEnquiry_life() {
		return enquiry_life;
	}

	public void setEnquiry_life(String enquiry_life) {
		this.enquiry_life = enquiry_life;
	}

	public String getOther_terms_conditions() {
		return other_terms_conditions;
	}

	public void setOther_terms_conditions(String other_terms_conditions) {
		this.other_terms_conditions = other_terms_conditions;
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

	public String getAgent_percent() {
		return agent_percent;
	}

	public void setAgent_percent(String agent_percent) {
		this.agent_percent = agent_percent;
	}

	public String getEnquiry_status() {
		return enquiry_status;
	}

	public void setEnquiry_status(String enquiry_status) {
		this.enquiry_status = enquiry_status;
	}

	public String getEnquiry_mail_sent_status() {
		return enquiry_mail_sent_status;
	}

	public void setEnquiry_mail_sent_status(String enquiry_mail_sent_status) {
		this.enquiry_mail_sent_status = enquiry_mail_sent_status;
	}

	public String getStatus_remark() {
		return status_remark;
	}

	public void setStatus_remark(String status_remark) {
		this.status_remark = status_remark;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public String getEnquiry_terms_parameters_id() {
		return enquiry_terms_parameters_id;
	}

	public void setEnquiry_terms_parameters_id(String enquiry_terms_parameters_id) {
		this.enquiry_terms_parameters_id = enquiry_terms_parameters_id;
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

	public String getEnquiry_master_transaction_id() {
		return enquiry_master_transaction_id;
	}

	public void setEnquiry_master_transaction_id(String enquiry_master_transaction_id) {
		this.enquiry_master_transaction_id = enquiry_master_transaction_id;
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

	public String getEnquiry_details_transaction_id() {
		return enquiry_details_transaction_id;
	}

	public void setEnquiry_details_transaction_id(String enquiry_details_transaction_id) {
		this.enquiry_details_transaction_id = enquiry_details_transaction_id;
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

	@Override
	public String toString() {
		return "CMtEnquiryTermsTradingRptModel [enquiry_terms_transaction_id=" + enquiry_terms_transaction_id
				+ ", company_name=" + company_name + ", company_branch_name=" + company_branch_name + ", enquiry_no="
				+ enquiry_no + ", enquiry_date=" + enquiry_date + ", enquiry_version=" + enquiry_version
				+ ", enquiry_terms_parameters_name=" + enquiry_terms_parameters_name
				+ ", enquiry_terms_parameters_value=" + enquiry_terms_parameters_value
				+ ", enquiry_terms_parameters_expected_value=" + enquiry_terms_parameters_expected_value
				+ ", financial_year=" + financial_year + ", enquiry_life=" + enquiry_life + ", other_terms_conditions="
				+ other_terms_conditions + ", approved_date=" + approved_date + ", overall_schedule_date="
				+ overall_schedule_date + ", agent_percent=" + agent_percent + ", enquiry_status=" + enquiry_status
				+ ", enquiry_mail_sent_status=" + enquiry_mail_sent_status + ", status_remark=" + status_remark
				+ ", remark=" + remark + ", created_by=" + created_by + ", created_on=" + created_on + ", modified_by="
				+ modified_by + ", modified_on=" + modified_on + ", deleted_by=" + deleted_by + ", deleted_on="
				+ deleted_on + ", is_active=" + is_active + ", is_delete=" + is_delete
				+ ", enquiry_terms_parameters_id=" + enquiry_terms_parameters_id + ", company_id=" + company_id
				+ ", company_branch_id=" + company_branch_id + ", enquiry_master_transaction_id="
				+ enquiry_master_transaction_id + ", customer_contacts_ids=" + customer_contacts_ids
				+ ", customer_state_id=" + customer_state_id + ", customer_city_id=" + customer_city_id
				+ ", expected_branch_id=" + expected_branch_id + ", enquiry_details_transaction_id="
				+ enquiry_details_transaction_id + ", expected_branch_city_id=" + expected_branch_city_id
				+ ", enquiry_by_id=" + enquiry_by_id + ", assign_to_head_id=" + assign_to_head_id + ", assign_to_id="
				+ assign_to_id + ", approved_by_id=" + approved_by_id + ", agent_id=" + agent_id + ", enquiry_type_id="
				+ enquiry_type_id + ", customer_id=" + customer_id + "]";
	}

	public CMtEnquiryTermsTradingRptModel_Not_Used() {
		super();
		// TODO Auto-generated constructor stub
	}

}
