package com.erp.MtDispatchScheduleDetails.Model;

import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;


@Data
@Entity
@Immutable
@Subselect("select * from  mtv_dispatch_schedule_master_trading__rpt")
public class CMtDispatchScheduleMasterTradingRptModel_Not_Used {

	@Id
	private String dispatch_schedule_master_transaction_id;
	private String company_name;
	private String company_branch_name;
	private String financial_year;
	private String dispatch_schedule_type;
	private String dispatch_type_short_name;
	private String dispatch_schedule_creation_type;
	private String dispatch_schedule_creation_type_desc;
	private String dispatch_schedule_no;
	private String dispatch_schedule_date;
	private String dispatch_schedule_version;
	private String customer_name;
	private String customer_email;
	private String customer_city_name;
	private String customer_state_name;
	private String consignee_name;
	private String customer_phone;
	private String customer_gst_no;
	private String customer_pan_no;
	private String cust_branch_address1;
	private String consignee_email;
	private String consignee_city_name;
	private String consignee_state_name;
	private String dispatch_supervisor_name;
	private String approved_by_name;
	private String approved_date;
	private String total_quantity;
	private String total_weight;
	private String actual_quantity;
	private String actual_weight;
	private String dispatch_note_status;
	private String dispatch_note_remark;
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
	private String dispatch_schedule_type_id;
	private String customer_id;
	private String customer_contacts_ids;
	private String customer_state_id;
	private String customer_city_id;
	private String consignee_id;
	private String consignee_state_id;
	private String consignee_city_id;
	private String dispatch_supervisor_id;
	private String approved_by_id;

	public String getDispatch_schedule_master_transaction_id() {
		return dispatch_schedule_master_transaction_id;
	}

	public void setDispatch_schedule_master_transaction_id(String dispatch_schedule_master_transaction_id) {
		this.dispatch_schedule_master_transaction_id = dispatch_schedule_master_transaction_id;
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

	public String getDispatch_schedule_type() {
		return dispatch_schedule_type;
	}

	public void setDispatch_schedule_type(String dispatch_schedule_type) {
		this.dispatch_schedule_type = dispatch_schedule_type;
	}

	public String getDispatch_type_short_name() {
		return dispatch_type_short_name;
	}

	public void setDispatch_type_short_name(String dispatch_type_short_name) {
		this.dispatch_type_short_name = dispatch_type_short_name;
	}

	public String getDispatch_schedule_creation_type() {
		return dispatch_schedule_creation_type;
	}

	public void setDispatch_schedule_creation_type(String dispatch_schedule_creation_type) {
		this.dispatch_schedule_creation_type = dispatch_schedule_creation_type;
	}

	public String getDispatch_schedule_creation_type_desc() {
		return dispatch_schedule_creation_type_desc;
	}

	public void setDispatch_schedule_creation_type_desc(String dispatch_schedule_creation_type_desc) {
		this.dispatch_schedule_creation_type_desc = dispatch_schedule_creation_type_desc;
	}

	public String getDispatch_schedule_no() {
		return dispatch_schedule_no;
	}

	public void setDispatch_schedule_no(String dispatch_schedule_no) {
		this.dispatch_schedule_no = dispatch_schedule_no;
	}

	public String getDispatch_schedule_date() {
		return dispatch_schedule_date;
	}

	public void setDispatch_schedule_date(String dispatch_schedule_date) {
		this.dispatch_schedule_date = dispatch_schedule_date;
	}

	public String getDispatch_schedule_version() {
		return dispatch_schedule_version;
	}

	public void setDispatch_schedule_version(String dispatch_schedule_version) {
		this.dispatch_schedule_version = dispatch_schedule_version;
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

	public String getCustomer_city_name() {
		return customer_city_name;
	}

	public void setCustomer_city_name(String customer_city_name) {
		this.customer_city_name = customer_city_name;
	}

	public String getCustomer_state_name() {
		return customer_state_name;
	}

	public void setCustomer_state_name(String customer_state_name) {
		this.customer_state_name = customer_state_name;
	}

	public String getConsignee_name() {
		return consignee_name;
	}

	public void setConsignee_name(String consignee_name) {
		this.consignee_name = consignee_name;
	}

	public String getConsignee_email() {
		return consignee_email;
	}

	public void setConsignee_email(String consignee_email) {
		this.consignee_email = consignee_email;
	}

	public String getConsignee_city_name() {
		return consignee_city_name;
	}

	public void setConsignee_city_name(String consignee_city_name) {
		this.consignee_city_name = consignee_city_name;
	}

	public String getConsignee_state_name() {
		return consignee_state_name;
	}

	public void setConsignee_state_name(String consignee_state_name) {
		this.consignee_state_name = consignee_state_name;
	}

	public String getDispatch_supervisor_name() {
		return dispatch_supervisor_name;
	}

	public void setDispatch_supervisor_name(String dispatch_supervisor_name) {
		this.dispatch_supervisor_name = dispatch_supervisor_name;
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

	public String getTotal_quantity() {
		return total_quantity;
	}

	public void setTotal_quantity(String total_quantity) {
		this.total_quantity = total_quantity;
	}

	public String getTotal_weight() {
		return total_weight;
	}

	public void setTotal_weight(String total_weight) {
		this.total_weight = total_weight;
	}

	public String getActual_quantity() {
		return actual_quantity;
	}

	public void setActual_quantity(String actual_quantity) {
		this.actual_quantity = actual_quantity;
	}

	public String getActual_weight() {
		return actual_weight;
	}

	public void setActual_weight(String actual_weight) {
		this.actual_weight = actual_weight;
	}

	public String getDispatch_note_status() {
		return dispatch_note_status;
	}

	public void setDispatch_note_status(String dispatch_note_status) {
		this.dispatch_note_status = dispatch_note_status;
	}

	public String getDispatch_note_remark() {
		return dispatch_note_remark;
	}

	public void setDispatch_note_remark(String dispatch_note_remark) {
		this.dispatch_note_remark = dispatch_note_remark;
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

	public String getDispatch_schedule_type_id() {
		return dispatch_schedule_type_id;
	}

	public void setDispatch_schedule_type_id(String dispatch_schedule_type_id) {
		this.dispatch_schedule_type_id = dispatch_schedule_type_id;
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

	public String getConsignee_id() {
		return consignee_id;
	}

	public void setConsignee_id(String consignee_id) {
		this.consignee_id = consignee_id;
	}

	public String getConsignee_state_id() {
		return consignee_state_id;
	}

	public void setConsignee_state_id(String consignee_state_id) {
		this.consignee_state_id = consignee_state_id;
	}

	public String getConsignee_city_id() {
		return consignee_city_id;
	}

	public void setConsignee_city_id(String consignee_city_id) {
		this.consignee_city_id = consignee_city_id;
	}

	public String getDispatch_supervisor_id() {
		return dispatch_supervisor_id;
	}

	public void setDispatch_supervisor_id(String dispatch_supervisor_id) {
		this.dispatch_supervisor_id = dispatch_supervisor_id;
	}

	public String getApproved_by_id() {
		return approved_by_id;
	}

	public void setApproved_by_id(String approved_by_id) {
		this.approved_by_id = approved_by_id;
	}

	public String getCustomer_phone() {
		return customer_phone;
	}

	public void setCustomer_phone(String customer_phone) {
		this.customer_phone = customer_phone;
	}

	public String getCustomer_gst_no() {
		return customer_gst_no;
	}

	public void setCustomer_gst_no(String customer_gst_no) {
		this.customer_gst_no = customer_gst_no;
	}

	public String getCustomer_pan_no() {
		return customer_pan_no;
	}

	public void setCustomer_pan_no(String customer_pan_no) {
		this.customer_pan_no = customer_pan_no;
	}

	public String getCust_branch_address1() {
		return cust_branch_address1;
	}

	public void setCust_branch_address1(String cust_branch_address1) {
		this.cust_branch_address1 = cust_branch_address1;
	}

	public CMtDispatchScheduleMasterTradingRptModel_Not_Used(String dispatch_schedule_master_transaction_id, String company_name,
	                                                         String company_branch_name, String financial_year, String dispatch_schedule_type,
	                                                         String dispatch_type_short_name, String dispatch_schedule_creation_type,
	                                                         String dispatch_schedule_creation_type_desc, String dispatch_schedule_no, String dispatch_schedule_date,
	                                                         String dispatch_schedule_version, String customer_name, String customer_email, String customer_city_name,
	                                                         String customer_state_name, String consignee_name, String customer_phone, String customer_gst_no,
	                                                         String customer_pan_no, String cust_branch_address1, String consignee_email, String consignee_city_name,
	                                                         String consignee_state_name, String dispatch_supervisor_name, String approved_by_name, String approved_date,
	                                                         String total_quantity, String total_weight, String actual_quantity, String actual_weight,
	                                                         String dispatch_note_status, String dispatch_note_remark, String other_terms_conditions, String remark,
	                                                         String is_active, String is_delete, String created_by, String created_on, String modified_by,
	                                                         String modified_on, String deleted_by, String deleted_on, String company_id, String company_branch_id,
	                                                         String dispatch_schedule_type_id, String customer_id, String customer_contacts_ids,
	                                                         String customer_state_id, String customer_city_id, String consignee_id, String consignee_state_id,
	                                                         String consignee_city_id, String dispatch_supervisor_id, String approved_by_id) {
		super();
		this.dispatch_schedule_master_transaction_id = dispatch_schedule_master_transaction_id;
		this.company_name = company_name;
		this.company_branch_name = company_branch_name;
		this.financial_year = financial_year;
		this.dispatch_schedule_type = dispatch_schedule_type;
		this.dispatch_type_short_name = dispatch_type_short_name;
		this.dispatch_schedule_creation_type = dispatch_schedule_creation_type;
		this.dispatch_schedule_creation_type_desc = dispatch_schedule_creation_type_desc;
		this.dispatch_schedule_no = dispatch_schedule_no;
		this.dispatch_schedule_date = dispatch_schedule_date;
		this.dispatch_schedule_version = dispatch_schedule_version;
		this.customer_name = customer_name;
		this.customer_email = customer_email;
		this.customer_city_name = customer_city_name;
		this.customer_state_name = customer_state_name;
		this.consignee_name = consignee_name;
		this.customer_phone = customer_phone;
		this.customer_gst_no = customer_gst_no;
		this.customer_pan_no = customer_pan_no;
		this.cust_branch_address1 = cust_branch_address1;
		this.consignee_email = consignee_email;
		this.consignee_city_name = consignee_city_name;
		this.consignee_state_name = consignee_state_name;
		this.dispatch_supervisor_name = dispatch_supervisor_name;
		this.approved_by_name = approved_by_name;
		this.approved_date = approved_date;
		this.total_quantity = total_quantity;
		this.total_weight = total_weight;
		this.actual_quantity = actual_quantity;
		this.actual_weight = actual_weight;
		this.dispatch_note_status = dispatch_note_status;
		this.dispatch_note_remark = dispatch_note_remark;
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
		this.dispatch_schedule_type_id = dispatch_schedule_type_id;
		this.customer_id = customer_id;
		this.customer_contacts_ids = customer_contacts_ids;
		this.customer_state_id = customer_state_id;
		this.customer_city_id = customer_city_id;
		this.consignee_id = consignee_id;
		this.consignee_state_id = consignee_state_id;
		this.consignee_city_id = consignee_city_id;
		this.dispatch_supervisor_id = dispatch_supervisor_id;
		this.approved_by_id = approved_by_id;
	}

	public CMtDispatchScheduleMasterTradingRptModel_Not_Used() {
		super();

	}


}
