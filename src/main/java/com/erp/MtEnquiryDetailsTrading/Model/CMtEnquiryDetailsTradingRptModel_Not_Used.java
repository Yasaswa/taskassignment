package com.erp.MtEnquiryDetailsTrading.Model;

import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@Immutable
@Subselect("select * from  mtv_enquiry_details_trading_rpt")
public class CMtEnquiryDetailsTradingRptModel_Not_Used {

	@Id
	private String enquiry_details_transaction_id;
	private String company_name;
	private String company_branch_name;
	private String financial_year;
	private String enquiry_type;
	private String enquiry_type_short_name;
	private String enquiry_no;
	private String enquiry_date;
	private String enquiry_version;
	private String customer_name;
	private String customer_email;
	private String customer_country_name;
	private String customer_state_name;
	private String customer_city_name;
	private String customer_district_name;
	private String sr_no;
	private String enquiry_life;
	private String overall_schedule_date;
	private String product_material_code;
	private String product_material_name;
	private String product_material_stock_unit_name;
	private String product_material_tech_spect;
	private String product_packing_name;
	private String product_material_enquiry_quantity;
	private String product_material_enquiry_weight;
	private String product_material_moq_quantity;
	private String product_material_notes;
	private String product_material_conversion_factor;
	private String expected_lead_time;
	private String material_std_rate;
	private String material_schedule_date;
	private String enquiry_item_status;
	private String enquiry_mail_sent_status;
	private String enquiry_mail_sent_status_desc;
	private String material_enquiry_approval_remark;
	private String department_name;
	private String indented_by_name;
	private String approved_by_name;
	private String approved_date;
	private String remark;
	private String is_active;
	private String is_delete;
	private String created_by;
	private String created_on;
	private String modified_by;
	private String modified_on;
	private String deleted_by;
	private String deleted_on;
	private String enquiry_master_transaction_id;
	private String company_id;
	private String company_branch_id;
	private String enquiry_type_id;
	private String product_material_id;
	private String product_material_unit_id;
	private String product_material_packing_id;
	private String department_id;
	private String indented_by_id;
	private String approved_by_id;
	private String customer_contacts_ids;
	private String customer_state_id;
	private String customer_city_id;
	private String expected_branch_id;
	private String expected_branch_state_id;
	private String expected_branch_city_id;
	private String enquiry_by_id;
	private String assign_to_head_id;
	private String assign_to_id;
	private String customer_id;
	private String agent_id;

	public String getEnquiry_details_transaction_id() {
		return enquiry_details_transaction_id;
	}

	public void setEnquiry_details_transaction_id(String enquiry_details_transaction_id) {
		this.enquiry_details_transaction_id = enquiry_details_transaction_id;
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

	public String getSr_no() {
		return sr_no;
	}

	public void setSr_no(String sr_no) {
		this.sr_no = sr_no;
	}

	public String getEnquiry_life() {
		return enquiry_life;
	}

	public void setEnquiry_life(String enquiry_life) {
		this.enquiry_life = enquiry_life;
	}

	public String getOverall_schedule_date() {
		return overall_schedule_date;
	}

	public void setOverall_schedule_date(String overall_schedule_date) {
		this.overall_schedule_date = overall_schedule_date;
	}

	public String getProduct_material_code() {
		return product_material_code;
	}

	public void setProduct_material_code(String product_material_code) {
		this.product_material_code = product_material_code;
	}

	public String getProduct_material_name() {
		return product_material_name;
	}

	public void setProduct_material_name(String product_material_name) {
		this.product_material_name = product_material_name;
	}

	public String getProduct_material_stock_unit_name() {
		return product_material_stock_unit_name;
	}

	public void setProduct_material_stock_unit_name(String product_material_stock_unit_name) {
		this.product_material_stock_unit_name = product_material_stock_unit_name;
	}

	public String getProduct_material_tech_spect() {
		return product_material_tech_spect;
	}

	public void setProduct_material_tech_spect(String product_material_tech_spect) {
		this.product_material_tech_spect = product_material_tech_spect;
	}

	public String getProduct_packing_name() {
		return product_packing_name;
	}

	public void setProduct_packing_name(String product_packing_name) {
		this.product_packing_name = product_packing_name;
	}

	public String getProduct_material_enquiry_quantity() {
		return product_material_enquiry_quantity;
	}

	public void setProduct_material_enquiry_quantity(String product_material_enquiry_quantity) {
		this.product_material_enquiry_quantity = product_material_enquiry_quantity;
	}

	public String getProduct_material_enquiry_weight() {
		return product_material_enquiry_weight;
	}

	public void setProduct_material_enquiry_weight(String product_material_enquiry_weight) {
		this.product_material_enquiry_weight = product_material_enquiry_weight;
	}

	public String getProduct_material_moq_quantity() {
		return product_material_moq_quantity;
	}

	public void setProduct_material_moq_quantity(String product_material_moq_quantity) {
		this.product_material_moq_quantity = product_material_moq_quantity;
	}

	public String getProduct_material_notes() {
		return product_material_notes;
	}

	public void setProduct_material_notes(String product_material_notes) {
		this.product_material_notes = product_material_notes;
	}

	public String getProduct_material_conversion_factor() {
		return product_material_conversion_factor;
	}

	public void setProduct_material_conversion_factor(String product_material_conversion_factor) {
		this.product_material_conversion_factor = product_material_conversion_factor;
	}

	public String getExpected_lead_time() {
		return expected_lead_time;
	}

	public void setExpected_lead_time(String expected_lead_time) {
		this.expected_lead_time = expected_lead_time;
	}

	public String getMaterial_std_rate() {
		return material_std_rate;
	}

	public void setMaterial_std_rate(String material_std_rate) {
		this.material_std_rate = material_std_rate;
	}

	public String getMaterial_schedule_date() {
		return material_schedule_date;
	}

	public void setMaterial_schedule_date(String material_schedule_date) {
		this.material_schedule_date = material_schedule_date;
	}

	public String getEnquiry_item_status() {
		return enquiry_item_status;
	}

	public void setEnquiry_item_status(String enquiry_item_status) {
		this.enquiry_item_status = enquiry_item_status;
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

	public String getMaterial_enquiry_approval_remark() {
		return material_enquiry_approval_remark;
	}

	public void setMaterial_enquiry_approval_remark(String material_enquiry_approval_remark) {
		this.material_enquiry_approval_remark = material_enquiry_approval_remark;
	}

	public String getDepartment_name() {
		return department_name;
	}

	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}

	public String getIndented_by_name() {
		return indented_by_name;
	}

	public void setIndented_by_name(String indented_by_name) {
		this.indented_by_name = indented_by_name;
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

	public String getEnquiry_master_transaction_id() {
		return enquiry_master_transaction_id;
	}

	public void setEnquiry_master_transaction_id(String enquiry_master_transaction_id) {
		this.enquiry_master_transaction_id = enquiry_master_transaction_id;
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

	public String getProduct_material_id() {
		return product_material_id;
	}

	public void setProduct_material_id(String product_material_id) {
		this.product_material_id = product_material_id;
	}

	public String getProduct_material_unit_id() {
		return product_material_unit_id;
	}

	public void setProduct_material_unit_id(String product_material_unit_id) {
		this.product_material_unit_id = product_material_unit_id;
	}

	public String getProduct_material_packing_id() {
		return product_material_packing_id;
	}

	public void setProduct_material_packing_id(String product_material_packing_id) {
		this.product_material_packing_id = product_material_packing_id;
	}

	public String getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(String department_id) {
		this.department_id = department_id;
	}

	public String getIndented_by_id() {
		return indented_by_id;
	}

	public void setIndented_by_id(String indented_by_id) {
		this.indented_by_id = indented_by_id;
	}

	public String getApproved_by_id() {
		return approved_by_id;
	}

	public void setApproved_by_id(String approved_by_id) {
		this.approved_by_id = approved_by_id;
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

	public String getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}

	public String getAgent_id() {
		return agent_id;
	}

	public void setAgent_id(String agent_id) {
		this.agent_id = agent_id;
	}

	public CMtEnquiryDetailsTradingRptModel_Not_Used(String enquiry_details_transaction_id, String company_name,
	                                                 String company_branch_name, String financial_year, String enquiry_type, String enquiry_type_short_name,
	                                                 String enquiry_no, String enquiry_date, String enquiry_version, String customer_name, String customer_email,
	                                                 String customer_country_name, String customer_state_name, String customer_city_name,
	                                                 String customer_district_name, String sr_no, String enquiry_life, String overall_schedule_date,
	                                                 String product_material_code, String product_material_name, String product_material_stock_unit_name,
	                                                 String product_material_tech_spect, String product_packing_name, String product_material_enquiry_quantity,
	                                                 String product_material_enquiry_weight, String product_material_moq_quantity, String product_material_notes,
	                                                 String product_material_conversion_factor, String expected_lead_time, String material_std_rate,
	                                                 String material_schedule_date, String enquiry_item_status, String enquiry_mail_sent_status,
	                                                 String enquiry_mail_sent_status_desc, String material_enquiry_approval_remark, String department_name,
	                                                 String indented_by_name, String approved_by_name, String approved_date, String remark, String is_active,
	                                                 String is_delete, String created_by, String created_on, String modified_by, String modified_on,
	                                                 String deleted_by, String deleted_on, String enquiry_master_transaction_id, String company_id,
	                                                 String company_branch_id, String enquiry_type_id, String product_material_id,
	                                                 String product_material_unit_id, String product_material_packing_id, String department_id,
	                                                 String indented_by_id, String approved_by_id, String customer_contacts_ids, String customer_state_id,
	                                                 String customer_city_id, String expected_branch_id, String expected_branch_state_id,
	                                                 String expected_branch_city_id, String enquiry_by_id, String assign_to_head_id, String assign_to_id,
	                                                 String customer_id, String agent_id) {
		super();
		this.enquiry_details_transaction_id = enquiry_details_transaction_id;
		this.company_name = company_name;
		this.company_branch_name = company_branch_name;
		this.financial_year = financial_year;
		this.enquiry_type = enquiry_type;
		this.enquiry_type_short_name = enquiry_type_short_name;
		this.enquiry_no = enquiry_no;
		this.enquiry_date = enquiry_date;
		this.enquiry_version = enquiry_version;
		this.customer_name = customer_name;
		this.customer_email = customer_email;
		this.customer_country_name = customer_country_name;
		this.customer_state_name = customer_state_name;
		this.customer_city_name = customer_city_name;
		this.customer_district_name = customer_district_name;
		this.sr_no = sr_no;
		this.enquiry_life = enquiry_life;
		this.overall_schedule_date = overall_schedule_date;
		this.product_material_code = product_material_code;
		this.product_material_name = product_material_name;
		this.product_material_stock_unit_name = product_material_stock_unit_name;
		this.product_material_tech_spect = product_material_tech_spect;
		this.product_packing_name = product_packing_name;
		this.product_material_enquiry_quantity = product_material_enquiry_quantity;
		this.product_material_enquiry_weight = product_material_enquiry_weight;
		this.product_material_moq_quantity = product_material_moq_quantity;
		this.product_material_notes = product_material_notes;
		this.product_material_conversion_factor = product_material_conversion_factor;
		this.expected_lead_time = expected_lead_time;
		this.material_std_rate = material_std_rate;
		this.material_schedule_date = material_schedule_date;
		this.enquiry_item_status = enquiry_item_status;
		this.enquiry_mail_sent_status = enquiry_mail_sent_status;
		this.enquiry_mail_sent_status_desc = enquiry_mail_sent_status_desc;
		this.material_enquiry_approval_remark = material_enquiry_approval_remark;
		this.department_name = department_name;
		this.indented_by_name = indented_by_name;
		this.approved_by_name = approved_by_name;
		this.approved_date = approved_date;
		this.remark = remark;
		this.is_active = is_active;
		this.is_delete = is_delete;
		this.created_by = created_by;
		this.created_on = created_on;
		this.modified_by = modified_by;
		this.modified_on = modified_on;
		this.deleted_by = deleted_by;
		this.deleted_on = deleted_on;
		this.enquiry_master_transaction_id = enquiry_master_transaction_id;
		this.company_id = company_id;
		this.company_branch_id = company_branch_id;
		this.enquiry_type_id = enquiry_type_id;
		this.product_material_id = product_material_id;
		this.product_material_unit_id = product_material_unit_id;
		this.product_material_packing_id = product_material_packing_id;
		this.department_id = department_id;
		this.indented_by_id = indented_by_id;
		this.approved_by_id = approved_by_id;
		this.customer_contacts_ids = customer_contacts_ids;
		this.customer_state_id = customer_state_id;
		this.customer_city_id = customer_city_id;
		this.expected_branch_id = expected_branch_id;
		this.expected_branch_state_id = expected_branch_state_id;
		this.expected_branch_city_id = expected_branch_city_id;
		this.enquiry_by_id = enquiry_by_id;
		this.assign_to_head_id = assign_to_head_id;
		this.assign_to_id = assign_to_id;
		this.customer_id = customer_id;
		this.agent_id = agent_id;
	}

	public CMtEnquiryDetailsTradingRptModel_Not_Used() {
		super();
	}

}
