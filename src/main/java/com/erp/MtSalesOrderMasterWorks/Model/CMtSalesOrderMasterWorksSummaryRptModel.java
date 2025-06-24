package com.erp.MtSalesOrderMasterWorks.Model;

import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@Immutable
@Subselect("select * from  mtv_sales_order_master_works_summary_rpt")
public class CMtSalesOrderMasterWorksSummaryRptModel {

	@Id
	private String sales_order_master_transaction_id;
	private String company_name;
	private String company_branch_name;
	private String financial_year;
	private String sales_order_type;
	private String sales_order_life;
	private String sales_order_no;
	private String sales_order_date;
	private String sales_order_version;
	private String customer_name;
	private String customer_email;
	private String customer_country_name;
	private String customer_state_name;
	private String customer_city_name;
	private String customer_district_name;
	private String consignee_name;
	private String consignee_country_name;
	private String consignee_state_name;
	private String consignee_city_name;
	private String consignee_district_name;
	private String customer_order_no;
	private String customer_order_Date;
	private String department_name;
	private String approved_by_name;
	private String approved_date;
	private String overall_schedule_date;
	private String basic_total;
	private String transport_amount;
	private String freight_amount;
	private String packing_amount;
	private String discount_percent;
	private String discount_amount;
	private String other_amount;
	private String taxable_total;
	private String cgst_percent;
	private String cgst_total;
	private String sgst_percent;
	private String sgst_total;
	private String igst_percent;
	private String igst_total;
	private String roundoff;
	private String grand_total;
	private String agent_name;
	private String agent_percent;
	private String agent_paid_status;
	private String sales_order_acceptance_status;
	private String sales_order_mail_sent_status;
	private String status_remark;
	private String sales_quotation_no;
	private String sales_quotation_date;
	private String other_terms_conditions;
	private String sales_order_status;
	private String sales_order_status_desc;
	private String sales_order_acceptance_status_desc;
	private String sales_order_mail_sent_status_desc;
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
	private String sales_order_type_id;
	private String customer_id;
	private String customer_state_id;
	private String customer_city_id;
	private String consignee_id;
	private String consignee_state_id;
	private String consignee_city_id;
	private String department_id;
	private String approved_by_id;
	private String agent_id;

	public String getSales_order_master_transaction_id() {
		return sales_order_master_transaction_id;
	}

	public void setSales_order_master_transaction_id(String sales_order_master_transaction_id) {
		this.sales_order_master_transaction_id = sales_order_master_transaction_id;
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

	public String getSales_order_type() {
		return sales_order_type;
	}

	public void setSales_order_type(String sales_order_type) {
		this.sales_order_type = sales_order_type;
	}

	public String getSales_order_life() {
		return sales_order_life;
	}

	public void setSales_order_life(String sales_order_life) {
		this.sales_order_life = sales_order_life;
	}

	public String getSales_order_no() {
		return sales_order_no;
	}

	public void setSales_order_no(String sales_order_no) {
		this.sales_order_no = sales_order_no;
	}

	public String getSales_order_date() {
		return sales_order_date;
	}

	public void setSales_order_date(String sales_order_date) {
		this.sales_order_date = sales_order_date;
	}

	public String getSales_order_version() {
		return sales_order_version;
	}

	public void setSales_order_version(String sales_order_version) {
		this.sales_order_version = sales_order_version;
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

	public String getConsignee_name() {
		return consignee_name;
	}

	public void setConsignee_name(String consignee_name) {
		this.consignee_name = consignee_name;
	}

	public String getConsignee_country_name() {
		return consignee_country_name;
	}

	public void setConsignee_country_name(String consignee_country_name) {
		this.consignee_country_name = consignee_country_name;
	}

	public String getConsignee_state_name() {
		return consignee_state_name;
	}

	public void setConsignee_state_name(String consignee_state_name) {
		this.consignee_state_name = consignee_state_name;
	}

	public String getConsignee_city_name() {
		return consignee_city_name;
	}

	public void setConsignee_city_name(String consignee_city_name) {
		this.consignee_city_name = consignee_city_name;
	}

	public String getConsignee_district_name() {
		return consignee_district_name;
	}

	public void setConsignee_district_name(String consignee_district_name) {
		this.consignee_district_name = consignee_district_name;
	}

	public String getCustomer_order_no() {
		return customer_order_no;
	}

	public void setCustomer_order_no(String customer_order_no) {
		this.customer_order_no = customer_order_no;
	}

	public String getCustomer_order_Date() {
		return customer_order_Date;
	}

	public void setCustomer_order_Date(String customer_order_Date) {
		this.customer_order_Date = customer_order_Date;
	}

	public String getDepartment_name() {
		return department_name;
	}

	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
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

	public String getBasic_total() {
		return basic_total;
	}

	public void setBasic_total(String basic_total) {
		this.basic_total = basic_total;
	}

	public String getTransport_amount() {
		return transport_amount;
	}

	public void setTransport_amount(String transport_amount) {
		this.transport_amount = transport_amount;
	}

	public String getFreight_amount() {
		return freight_amount;
	}

	public void setFreight_amount(String freight_amount) {
		this.freight_amount = freight_amount;
	}

	public String getPacking_amount() {
		return packing_amount;
	}

	public void setPacking_amount(String packing_amount) {
		this.packing_amount = packing_amount;
	}

	public String getDiscount_percent() {
		return discount_percent;
	}

	public void setDiscount_percent(String discount_percent) {
		this.discount_percent = discount_percent;
	}

	public String getDiscount_amount() {
		return discount_amount;
	}

	public void setDiscount_amount(String discount_amount) {
		this.discount_amount = discount_amount;
	}

	public String getOther_amount() {
		return other_amount;
	}

	public void setOther_amount(String other_amount) {
		this.other_amount = other_amount;
	}

	public String getTaxable_total() {
		return taxable_total;
	}

	public void setTaxable_total(String taxable_total) {
		this.taxable_total = taxable_total;
	}

	public String getCgst_percent() {
		return cgst_percent;
	}

	public void setCgst_percent(String cgst_percent) {
		this.cgst_percent = cgst_percent;
	}

	public String getCgst_total() {
		return cgst_total;
	}

	public void setCgst_total(String cgst_total) {
		this.cgst_total = cgst_total;
	}

	public String getSgst_percent() {
		return sgst_percent;
	}

	public void setSgst_percent(String sgst_percent) {
		this.sgst_percent = sgst_percent;
	}

	public String getSgst_total() {
		return sgst_total;
	}

	public void setSgst_total(String sgst_total) {
		this.sgst_total = sgst_total;
	}

	public String getIgst_percent() {
		return igst_percent;
	}

	public void setIgst_percent(String igst_percent) {
		this.igst_percent = igst_percent;
	}

	public String getIgst_total() {
		return igst_total;
	}

	public void setIgst_total(String igst_total) {
		this.igst_total = igst_total;
	}

	public String getRoundoff() {
		return roundoff;
	}

	public void setRoundoff(String roundoff) {
		this.roundoff = roundoff;
	}

	public String getGrand_total() {
		return grand_total;
	}

	public void setGrand_total(String grand_total) {
		this.grand_total = grand_total;
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

	public String getAgent_paid_status() {
		return agent_paid_status;
	}

	public void setAgent_paid_status(String agent_paid_status) {
		this.agent_paid_status = agent_paid_status;
	}

	public String getSales_order_acceptance_status() {
		return sales_order_acceptance_status;
	}

	public void setSales_order_acceptance_status(String sales_order_acceptance_status) {
		this.sales_order_acceptance_status = sales_order_acceptance_status;
	}

	public String getSales_order_mail_sent_status() {
		return sales_order_mail_sent_status;
	}

	public void setSales_order_mail_sent_status(String sales_order_mail_sent_status) {
		this.sales_order_mail_sent_status = sales_order_mail_sent_status;
	}

	public String getSales_order_status() {
		return sales_order_status;
	}

	public void setSales_order_status(String sales_order_status) {
		this.sales_order_status = sales_order_status;
	}

	public String getSales_order_status_desc() {
		return sales_order_status_desc;
	}

	public void setSales_order_status_desc(String sales_order_status_desc) {
		this.sales_order_status_desc = sales_order_status_desc;
	}

	public String getSales_order_acceptance_status_desc() {
		return sales_order_acceptance_status_desc;
	}

	public void setSales_order_acceptance_status_desc(String sales_order_acceptance_status_desc) {
		this.sales_order_acceptance_status_desc = sales_order_acceptance_status_desc;
	}

	public String getSales_order_mail_sent_status_desc() {
		return sales_order_mail_sent_status_desc;
	}

	public void setSales_order_mail_sent_status_desc(String sales_order_mail_sent_status_desc) {
		this.sales_order_mail_sent_status_desc = sales_order_mail_sent_status_desc;
	}

	public String getStatus_remark() {
		return status_remark;
	}

	public void setStatus_remark(String status_remark) {
		this.status_remark = status_remark;
	}

	public String getSales_quotation_no() {
		return sales_quotation_no;
	}

	public void setSales_quotation_no(String sales_quotation_no) {
		this.sales_quotation_no = sales_quotation_no;
	}

	public String getSales_quotation_date() {
		return sales_quotation_date;
	}

	public void setSales_quotation_date(String sales_quotation_date) {
		this.sales_quotation_date = sales_quotation_date;
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

	public String getSales_order_type_id() {
		return sales_order_type_id;
	}

	public void setSales_order_type_id(String sales_order_type_id) {
		this.sales_order_type_id = sales_order_type_id;
	}

	public String getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
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

	public String getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(String department_id) {
		this.department_id = department_id;
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

	public CMtSalesOrderMasterWorksSummaryRptModel(String sales_order_master_transaction_id, String company_name,
	                                               String company_branch_name, String financial_year, String sales_order_type, String sales_order_life,
	                                               String sales_order_no, String sales_order_date, String sales_order_version, String customer_name,
	                                               String customer_email, String customer_country_name, String customer_state_name, String customer_city_name,
	                                               String customer_district_name, String consignee_name, String consignee_country_name,
	                                               String consignee_state_name, String consignee_city_name, String consignee_district_name,
	                                               String customer_order_no, String customer_order_Date, String department_name, String approved_by_name,
	                                               String approved_date, String overall_schedule_date, String basic_total, String transport_amount,
	                                               String freight_amount, String packing_amount, String discount_percent, String discount_amount,
	                                               String other_amount, String taxable_total, String cgst_percent, String cgst_total, String sgst_percent,
	                                               String sgst_total, String igst_percent, String igst_total, String roundoff, String grand_total,
	                                               String agent_name, String agent_percent, String agent_paid_status, String sales_order_acceptance_status,
	                                               String sales_order_mail_sent_status, String status_remark, String sales_quotation_no,
	                                               String sales_quotation_date, String other_terms_conditions, String sales_order_status,
	                                               String sales_order_status_desc, String sales_order_acceptance_status_desc,
	                                               String sales_order_mail_sent_status_desc, String remark, String is_active, String is_delete,
	                                               String created_by, String created_on, String modified_by, String modified_on, String deleted_by,
	                                               String deleted_on, String company_id, String company_branch_id, String sales_order_type_id,
	                                               String customer_id, String customer_state_id, String customer_city_id, String consignee_id,
	                                               String consignee_state_id, String consignee_city_id, String department_id, String approved_by_id,
	                                               String agent_id) {
		super();
		this.sales_order_master_transaction_id = sales_order_master_transaction_id;
		this.company_name = company_name;
		this.company_branch_name = company_branch_name;
		this.financial_year = financial_year;
		this.sales_order_type = sales_order_type;
		this.sales_order_life = sales_order_life;
		this.sales_order_no = sales_order_no;
		this.sales_order_date = sales_order_date;
		this.sales_order_version = sales_order_version;
		this.customer_name = customer_name;
		this.customer_email = customer_email;
		this.customer_country_name = customer_country_name;
		this.customer_state_name = customer_state_name;
		this.customer_city_name = customer_city_name;
		this.customer_district_name = customer_district_name;
		this.consignee_name = consignee_name;
		this.consignee_country_name = consignee_country_name;
		this.consignee_state_name = consignee_state_name;
		this.consignee_city_name = consignee_city_name;
		this.consignee_district_name = consignee_district_name;
		this.customer_order_no = customer_order_no;
		this.customer_order_Date = customer_order_Date;
		this.department_name = department_name;
		this.approved_by_name = approved_by_name;
		this.approved_date = approved_date;
		this.overall_schedule_date = overall_schedule_date;
		this.basic_total = basic_total;
		this.transport_amount = transport_amount;
		this.freight_amount = freight_amount;
		this.packing_amount = packing_amount;
		this.discount_percent = discount_percent;
		this.discount_amount = discount_amount;
		this.other_amount = other_amount;
		this.taxable_total = taxable_total;
		this.cgst_percent = cgst_percent;
		this.cgst_total = cgst_total;
		this.sgst_percent = sgst_percent;
		this.sgst_total = sgst_total;
		this.igst_percent = igst_percent;
		this.igst_total = igst_total;
		this.roundoff = roundoff;
		this.grand_total = grand_total;
		this.agent_name = agent_name;
		this.agent_percent = agent_percent;
		this.agent_paid_status = agent_paid_status;
		this.sales_order_acceptance_status = sales_order_acceptance_status;
		this.sales_order_mail_sent_status = sales_order_mail_sent_status;
		this.status_remark = status_remark;
		this.sales_quotation_no = sales_quotation_no;
		this.sales_quotation_date = sales_quotation_date;
		this.other_terms_conditions = other_terms_conditions;
		this.sales_order_status = sales_order_status;
		this.sales_order_status_desc = sales_order_status_desc;
		this.sales_order_acceptance_status_desc = sales_order_acceptance_status_desc;
		this.sales_order_mail_sent_status_desc = sales_order_mail_sent_status_desc;
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
		this.sales_order_type_id = sales_order_type_id;
		this.customer_id = customer_id;
		this.customer_state_id = customer_state_id;
		this.customer_city_id = customer_city_id;
		this.consignee_id = consignee_id;
		this.consignee_state_id = consignee_state_id;
		this.consignee_city_id = consignee_city_id;
		this.department_id = department_id;
		this.approved_by_id = approved_by_id;
		this.agent_id = agent_id;
	}

	public CMtSalesOrderMasterWorksSummaryRptModel() {
		super();
	}

}
