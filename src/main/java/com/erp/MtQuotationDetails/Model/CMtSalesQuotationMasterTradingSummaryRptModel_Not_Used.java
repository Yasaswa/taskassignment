package com.erp.MtQuotationDetails.Model;

import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@Immutable
@Subselect("select * from  mtv_sales_quotation_trading_summary_rpt")
public class CMtSalesQuotationMasterTradingSummaryRptModel_Not_Used {

	@Id
	private String quotation_type;
	private String quotation_transaction_type_desc;
	private String quotation_no;
	private String quotation_version;
	private String quotation_date;
	private String department_name;
	private String quotation_by_name;
	private String enquiry_by_name;
	private String assign_to_head_name;
	private String assign_to_name;
	private String approved_by_name;
	private String agent_name;
	private String agent_percent;
	private String quotation_material_cost;
	private String quotation_process_cost;
	private String quotation_labour_cost;
	private String quotation_other_cost;
	private String quotation_other_cost_remark;
	private String quotation_administration_percent;
	private String quotation_administration_cost;
	private String quotation_profit_percent;
	private String quotation_profit_cost;
	private String quotation_discount_percent;
	private String quotation_discount_cost;
	private String quotation_taxable_cost;
	private String quotation_cgst_cost;
	private String quotation_sgst_cost;
	private String quotation_igst_cost;
	private String quotation_total_cost;
	private String overall_schedule_date;
	private String tax_applicable_desc;
	private String tax_applicable;
	private String transportation_applicable_desc;
	private String transportation_applicable;
	private String bom_applicable_desc;
	private String bom_applicable;
	private String costing_applicable_desc;
	private String costing_applicable;
	private String quotation_life_desc;
	private String quotation_status_desc;
	private String quotation_mail_sent_status_desc;
	private String approved_date;
	private String quotation_type_short_name;
	private String expected_branch_name;
	private String expected_branch_address1;
	private String expected_branch_pincode;
	private String expected_branch_city_name;
	private String expected_branch_district_name;
	private String expected_branch_state_name;
	private String expected_branch_country_name;
	private String expected_branch_phone_no;
	private String expected_branch_EmailId;
	private String status_remark;
	private String company_name;
	private String company_branch_name;
	private String financial_year;
	private String other_terms_conditions;
	private String remark;
	private String quotation_status;
	private String quotation_life;
	private String quotation_transaction_type;
	private String quotation_mail_sent_status;
	private String customer_name;
	private String customer_state_name;
	private String customer_email;
	private String customer_country_name;
	private String customer_city_name;
	private String customer_district_name;
	private String is_active;
	private String is_delete;
	private String created_by;
	private String created_on;
	private String modified_by;
	private String modified_on;
	private String deleted_by;
	private String deleted_on;
	private String company_id;
	private String department_id;
	private String company_branch_id;
	private String quotation_type_id;
	private String customer_id;
	private String customer_contacts_ids;
	private String customer_state_id;
	private String customer_city_id;
	private String expected_branch_id;
	private String expected_branch_state_id;
	private String expected_branch_city_id;
	private String quotation_by_id;
	private String enquiry_by_id;
	private String assign_to_head_id;
	private String assign_to_id;
	private String approved_by_id;
	private String agent_id;
	private String quotation_master_transaction_id;

	public String getQuotation_type() {
		return quotation_type;
	}

	public void setQuotation_type(String quotation_type) {
		this.quotation_type = quotation_type;
	}

	public String getQuotation_transaction_type_desc() {
		return quotation_transaction_type_desc;
	}

	public void setQuotation_transaction_type_desc(String quotation_transaction_type_desc) {
		this.quotation_transaction_type_desc = quotation_transaction_type_desc;
	}

	public String getQuotation_no() {
		return quotation_no;
	}

	public void setQuotation_no(String quotation_no) {
		this.quotation_no = quotation_no;
	}

	public String getQuotation_version() {
		return quotation_version;
	}

	public void setQuotation_version(String quotation_version) {
		this.quotation_version = quotation_version;
	}

	public String getQuotation_date() {
		return quotation_date;
	}

	public void setQuotation_date(String quotation_date) {
		this.quotation_date = quotation_date;
	}

	public String getDepartment_name() {
		return department_name;
	}

	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}

	public String getQuotation_by_name() {
		return quotation_by_name;
	}

	public void setQuotation_by_name(String quotation_by_name) {
		this.quotation_by_name = quotation_by_name;
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

	public String getQuotation_material_cost() {
		return quotation_material_cost;
	}

	public void setQuotation_material_cost(String quotation_material_cost) {
		this.quotation_material_cost = quotation_material_cost;
	}

	public String getQuotation_process_cost() {
		return quotation_process_cost;
	}

	public void setQuotation_process_cost(String quotation_process_cost) {
		this.quotation_process_cost = quotation_process_cost;
	}

	public String getQuotation_labour_cost() {
		return quotation_labour_cost;
	}

	public void setQuotation_labour_cost(String quotation_labour_cost) {
		this.quotation_labour_cost = quotation_labour_cost;
	}

	public String getQuotation_other_cost() {
		return quotation_other_cost;
	}

	public void setQuotation_other_cost(String quotation_other_cost) {
		this.quotation_other_cost = quotation_other_cost;
	}

	public String getQuotation_other_cost_remark() {
		return quotation_other_cost_remark;
	}

	public void setQuotation_other_cost_remark(String quotation_other_cost_remark) {
		this.quotation_other_cost_remark = quotation_other_cost_remark;
	}

	public String getQuotation_administration_percent() {
		return quotation_administration_percent;
	}

	public void setQuotation_administration_percent(String quotation_administration_percent) {
		this.quotation_administration_percent = quotation_administration_percent;
	}

	public String getQuotation_administration_cost() {
		return quotation_administration_cost;
	}

	public void setQuotation_administration_cost(String quotation_administration_cost) {
		this.quotation_administration_cost = quotation_administration_cost;
	}

	public String getQuotation_profit_percent() {
		return quotation_profit_percent;
	}

	public void setQuotation_profit_percent(String quotation_profit_percent) {
		this.quotation_profit_percent = quotation_profit_percent;
	}

	public String getQuotation_profit_cost() {
		return quotation_profit_cost;
	}

	public void setQuotation_profit_cost(String quotation_profit_cost) {
		this.quotation_profit_cost = quotation_profit_cost;
	}

	public String getQuotation_discount_percent() {
		return quotation_discount_percent;
	}

	public void setQuotation_discount_percent(String quotation_discount_percent) {
		this.quotation_discount_percent = quotation_discount_percent;
	}

	public String getQuotation_discount_cost() {
		return quotation_discount_cost;
	}

	public void setQuotation_discount_cost(String quotation_discount_cost) {
		this.quotation_discount_cost = quotation_discount_cost;
	}

	public String getQuotation_taxable_cost() {
		return quotation_taxable_cost;
	}

	public void setQuotation_taxable_cost(String quotation_taxable_cost) {
		this.quotation_taxable_cost = quotation_taxable_cost;
	}

	public String getQuotation_cgst_cost() {
		return quotation_cgst_cost;
	}

	public void setQuotation_cgst_cost(String quotation_cgst_cost) {
		this.quotation_cgst_cost = quotation_cgst_cost;
	}

	public String getQuotation_sgst_cost() {
		return quotation_sgst_cost;
	}

	public void setQuotation_sgst_cost(String quotation_sgst_cost) {
		this.quotation_sgst_cost = quotation_sgst_cost;
	}

	public String getQuotation_igst_cost() {
		return quotation_igst_cost;
	}

	public void setQuotation_igst_cost(String quotation_igst_cost) {
		this.quotation_igst_cost = quotation_igst_cost;
	}

	public String getQuotation_total_cost() {
		return quotation_total_cost;
	}

	public void setQuotation_total_cost(String quotation_total_cost) {
		this.quotation_total_cost = quotation_total_cost;
	}

	public String getOverall_schedule_date() {
		return overall_schedule_date;
	}

	public void setOverall_schedule_date(String overall_schedule_date) {
		this.overall_schedule_date = overall_schedule_date;
	}

	public String getTax_applicable_desc() {
		return tax_applicable_desc;
	}

	public void setTax_applicable_desc(String tax_applicable_desc) {
		this.tax_applicable_desc = tax_applicable_desc;
	}

	public String getTax_applicable() {
		return tax_applicable;
	}

	public void setTax_applicable(String tax_applicable) {
		this.tax_applicable = tax_applicable;
	}

	public String getTransportation_applicable_desc() {
		return transportation_applicable_desc;
	}

	public void setTransportation_applicable_desc(String transportation_applicable_desc) {
		this.transportation_applicable_desc = transportation_applicable_desc;
	}

	public String getTransportation_applicable() {
		return transportation_applicable;
	}

	public void setTransportation_applicable(String transportation_applicable) {
		this.transportation_applicable = transportation_applicable;
	}

	public String getBom_applicable_desc() {
		return bom_applicable_desc;
	}

	public void setBom_applicable_desc(String bom_applicable_desc) {
		this.bom_applicable_desc = bom_applicable_desc;
	}

	public String getBom_applicable() {
		return bom_applicable;
	}

	public void setBom_applicable(String bom_applicable) {
		this.bom_applicable = bom_applicable;
	}

	public String getCosting_applicable_desc() {
		return costing_applicable_desc;
	}

	public void setCosting_applicable_desc(String costing_applicable_desc) {
		this.costing_applicable_desc = costing_applicable_desc;
	}

	public String getCosting_applicable() {
		return costing_applicable;
	}

	public void setCosting_applicable(String costing_applicable) {
		this.costing_applicable = costing_applicable;
	}

	public String getQuotation_life_desc() {
		return quotation_life_desc;
	}

	public void setQuotation_life_desc(String quotation_life_desc) {
		this.quotation_life_desc = quotation_life_desc;
	}

	public String getQuotation_status_desc() {
		return quotation_status_desc;
	}

	public void setQuotation_status_desc(String quotation_status_desc) {
		this.quotation_status_desc = quotation_status_desc;
	}

	public String getQuotation_mail_sent_status_desc() {
		return quotation_mail_sent_status_desc;
	}

	public void setQuotation_mail_sent_status_desc(String quotation_mail_sent_status_desc) {
		this.quotation_mail_sent_status_desc = quotation_mail_sent_status_desc;
	}

	public String getApproved_date() {
		return approved_date;
	}

	public void setApproved_date(String approved_date) {
		this.approved_date = approved_date;
	}

	public String getQuotation_type_short_name() {
		return quotation_type_short_name;
	}

	public void setQuotation_type_short_name(String quotation_type_short_name) {
		this.quotation_type_short_name = quotation_type_short_name;
	}

	public String getExpected_branch_name() {
		return expected_branch_name;
	}

	public void setExpected_branch_name(String expected_branch_name) {
		this.expected_branch_name = expected_branch_name;
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

	public String getStatus_remark() {
		return status_remark;
	}

	public void setStatus_remark(String status_remark) {
		this.status_remark = status_remark;
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

	public String getQuotation_status() {
		return quotation_status;
	}

	public void setQuotation_status(String quotation_status) {
		this.quotation_status = quotation_status;
	}

	public String getQuotation_life() {
		return quotation_life;
	}

	public void setQuotation_life(String quotation_life) {
		this.quotation_life = quotation_life;
	}

	public String getQuotation_transaction_type() {
		return quotation_transaction_type;
	}

	public void setQuotation_transaction_type(String quotation_transaction_type) {
		this.quotation_transaction_type = quotation_transaction_type;
	}

	public String getQuotation_mail_sent_status() {
		return quotation_mail_sent_status;
	}

	public void setQuotation_mail_sent_status(String quotation_mail_sent_status) {
		this.quotation_mail_sent_status = quotation_mail_sent_status;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public String getCustomer_state_name() {
		return customer_state_name;
	}

	public void setCustomer_state_name(String customer_state_name) {
		this.customer_state_name = customer_state_name;
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

	public String getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(String department_id) {
		this.department_id = department_id;
	}

	public String getCompany_branch_id() {
		return company_branch_id;
	}

	public void setCompany_branch_id(String company_branch_id) {
		this.company_branch_id = company_branch_id;
	}

	public String getQuotation_type_id() {
		return quotation_type_id;
	}

	public void setQuotation_type_id(String quotation_type_id) {
		this.quotation_type_id = quotation_type_id;
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

	public String getQuotation_by_id() {
		return quotation_by_id;
	}

	public void setQuotation_by_id(String quotation_by_id) {
		this.quotation_by_id = quotation_by_id;
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

	public String getQuotation_master_transaction_id() {
		return quotation_master_transaction_id;
	}

	public void setQuotation_master_transaction_id(String quotation_master_transaction_id) {
		this.quotation_master_transaction_id = quotation_master_transaction_id;
	}

	public CMtSalesQuotationMasterTradingSummaryRptModel_Not_Used(String quotation_type, String quotation_transaction_type_desc,
	                                                              String quotation_no, String quotation_version, String quotation_date, String department_name,
	                                                              String quotation_by_name, String enquiry_by_name, String assign_to_head_name, String assign_to_name,
	                                                              String approved_by_name, String agent_name, String agent_percent, String quotation_material_cost,
	                                                              String quotation_process_cost, String quotation_labour_cost, String quotation_other_cost,
	                                                              String quotation_other_cost_remark, String quotation_administration_percent,
	                                                              String quotation_administration_cost, String quotation_profit_percent, String quotation_profit_cost,
	                                                              String quotation_discount_percent, String quotation_discount_cost, String quotation_taxable_cost,
	                                                              String quotation_cgst_cost, String quotation_sgst_cost, String quotation_igst_cost,
	                                                              String quotation_total_cost, String overall_schedule_date, String tax_applicable_desc,
	                                                              String tax_applicable, String transportation_applicable_desc, String transportation_applicable,
	                                                              String bom_applicable_desc, String bom_applicable, String costing_applicable_desc,
	                                                              String costing_applicable, String quotation_life_desc, String quotation_status_desc,
	                                                              String quotation_mail_sent_status_desc, String approved_date, String quotation_type_short_name,
	                                                              String expected_branch_name, String expected_branch_address1, String expected_branch_pincode,
	                                                              String expected_branch_city_name, String expected_branch_district_name, String expected_branch_state_name,
	                                                              String expected_branch_country_name, String expected_branch_phone_no, String expected_branch_EmailId,
	                                                              String status_remark, String company_name, String company_branch_name, String financial_year,
	                                                              String other_terms_conditions, String remark, String quotation_status, String quotation_life,
	                                                              String quotation_transaction_type, String quotation_mail_sent_status, String customer_name,
	                                                              String customer_state_name, String customer_email, String customer_country_name, String customer_city_name,
	                                                              String customer_district_name, String is_active, String is_delete, String created_by, String created_on,
	                                                              String modified_by, String modified_on, String deleted_by, String deleted_on, String company_id,
	                                                              String department_id, String company_branch_id, String quotation_type_id, String customer_id,
	                                                              String customer_contacts_ids, String customer_state_id, String customer_city_id, String expected_branch_id,
	                                                              String expected_branch_state_id, String expected_branch_city_id, String quotation_by_id,
	                                                              String enquiry_by_id, String assign_to_head_id, String assign_to_id, String approved_by_id, String agent_id,
	                                                              String quotation_master_transaction_id) {
		super();
		this.quotation_type = quotation_type;
		this.quotation_transaction_type_desc = quotation_transaction_type_desc;
		this.quotation_no = quotation_no;
		this.quotation_version = quotation_version;
		this.quotation_date = quotation_date;
		this.department_name = department_name;
		this.quotation_by_name = quotation_by_name;
		this.enquiry_by_name = enquiry_by_name;
		this.assign_to_head_name = assign_to_head_name;
		this.assign_to_name = assign_to_name;
		this.approved_by_name = approved_by_name;
		this.agent_name = agent_name;
		this.agent_percent = agent_percent;
		this.quotation_material_cost = quotation_material_cost;
		this.quotation_process_cost = quotation_process_cost;
		this.quotation_labour_cost = quotation_labour_cost;
		this.quotation_other_cost = quotation_other_cost;
		this.quotation_other_cost_remark = quotation_other_cost_remark;
		this.quotation_administration_percent = quotation_administration_percent;
		this.quotation_administration_cost = quotation_administration_cost;
		this.quotation_profit_percent = quotation_profit_percent;
		this.quotation_profit_cost = quotation_profit_cost;
		this.quotation_discount_percent = quotation_discount_percent;
		this.quotation_discount_cost = quotation_discount_cost;
		this.quotation_taxable_cost = quotation_taxable_cost;
		this.quotation_cgst_cost = quotation_cgst_cost;
		this.quotation_sgst_cost = quotation_sgst_cost;
		this.quotation_igst_cost = quotation_igst_cost;
		this.quotation_total_cost = quotation_total_cost;
		this.overall_schedule_date = overall_schedule_date;
		this.tax_applicable_desc = tax_applicable_desc;
		this.tax_applicable = tax_applicable;
		this.transportation_applicable_desc = transportation_applicable_desc;
		this.transportation_applicable = transportation_applicable;
		this.bom_applicable_desc = bom_applicable_desc;
		this.bom_applicable = bom_applicable;
		this.costing_applicable_desc = costing_applicable_desc;
		this.costing_applicable = costing_applicable;
		this.quotation_life_desc = quotation_life_desc;
		this.quotation_status_desc = quotation_status_desc;
		this.quotation_mail_sent_status_desc = quotation_mail_sent_status_desc;
		this.approved_date = approved_date;
		this.quotation_type_short_name = quotation_type_short_name;
		this.expected_branch_name = expected_branch_name;
		this.expected_branch_address1 = expected_branch_address1;
		this.expected_branch_pincode = expected_branch_pincode;
		this.expected_branch_city_name = expected_branch_city_name;
		this.expected_branch_district_name = expected_branch_district_name;
		this.expected_branch_state_name = expected_branch_state_name;
		this.expected_branch_country_name = expected_branch_country_name;
		this.expected_branch_phone_no = expected_branch_phone_no;
		this.expected_branch_EmailId = expected_branch_EmailId;
		this.status_remark = status_remark;
		this.company_name = company_name;
		this.company_branch_name = company_branch_name;
		this.financial_year = financial_year;
		this.other_terms_conditions = other_terms_conditions;
		this.remark = remark;
		this.quotation_status = quotation_status;
		this.quotation_life = quotation_life;
		this.quotation_transaction_type = quotation_transaction_type;
		this.quotation_mail_sent_status = quotation_mail_sent_status;
		this.customer_name = customer_name;
		this.customer_state_name = customer_state_name;
		this.customer_email = customer_email;
		this.customer_country_name = customer_country_name;
		this.customer_city_name = customer_city_name;
		this.customer_district_name = customer_district_name;
		this.is_active = is_active;
		this.is_delete = is_delete;
		this.created_by = created_by;
		this.created_on = created_on;
		this.modified_by = modified_by;
		this.modified_on = modified_on;
		this.deleted_by = deleted_by;
		this.deleted_on = deleted_on;
		this.company_id = company_id;
		this.department_id = department_id;
		this.company_branch_id = company_branch_id;
		this.quotation_type_id = quotation_type_id;
		this.customer_id = customer_id;
		this.customer_contacts_ids = customer_contacts_ids;
		this.customer_state_id = customer_state_id;
		this.customer_city_id = customer_city_id;
		this.expected_branch_id = expected_branch_id;
		this.expected_branch_state_id = expected_branch_state_id;
		this.expected_branch_city_id = expected_branch_city_id;
		this.quotation_by_id = quotation_by_id;
		this.enquiry_by_id = enquiry_by_id;
		this.assign_to_head_id = assign_to_head_id;
		this.assign_to_id = assign_to_id;
		this.approved_by_id = approved_by_id;
		this.agent_id = agent_id;
		this.quotation_master_transaction_id = quotation_master_transaction_id;
	}

	public CMtSalesQuotationMasterTradingSummaryRptModel_Not_Used() {
		super();
	}
}
