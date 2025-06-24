package com.erp.MtQuotationDetails.Model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "mt_sales_quotation_master_trading")
public class CMtSalesQuotationMasterTradingModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "quotation_master_transaction_id")
	private int quotation_master_transaction_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String financial_year;
	private Integer quotation_type_id;
	private String quotation_type;
	private String quotation_life;
	private String quotation_transaction_type;
	private String quotation_no;
	private String quotation_date;
	private Integer quotation_version;
	private Integer customer_id;
	private String customer_contacts_ids;
	private Integer department_id;
	private Integer customer_state_id;
	private Integer customer_city_id;
	private Integer expected_branch_id;
	private Integer expected_branch_state_id;
	private Integer expected_branch_city_id;
	private Integer quotation_by_id;
	private Integer enquiry_by_id;
	private Integer assign_to_head_id;
	private Integer assign_to_id;
	private Integer approved_by_id;
	private String approved_date;
	private String overall_schedule_date;
	private String tax_applicable;
	private String transportation_applicable;
	private String bom_applicable;
	private String costing_applicable;
	private double quotation_material_cost;
	private double quotation_process_cost;
	private double quotation_labour_cost;
	private double quotation_other_cost;
	private String quotation_other_cost_remark;
	private double quotation_administration_percent;
	private double quotation_administration_cost;
	private double quotation_profit_percent;
	private double quotation_profit_cost;
	private double quotation_discount_percent;
	private double quotation_discount_cost;
	private double quotation_taxable_cost;
	private double quotation_cgst_cost;
	private double quotation_sgst_cost;
	private double quotation_igst_cost;
	private double quotation_total_cost;
	private Integer agent_id;
	private double agent_percent;
	private String quotation_mail_sent_status;
	private String quotation_status;
	private String status_remark;
	private String other_terms_conditions;
	private String remark;
	private boolean is_active = Boolean.TRUE;
	private boolean is_delete = Boolean.FALSE;
	@Column(name = "created_by", updatable = false)
	private String created_by;
	@CreationTimestamp
	@Column(name = "created_on", updatable = false)
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;

	public int getQuotation_master_transaction_id() {
		return quotation_master_transaction_id;
	}

	public void setQuotation_master_transaction_id(int quotation_master_transaction_id) {
		this.quotation_master_transaction_id = quotation_master_transaction_id;
	}

	public Integer getCompany_id() {
		return company_id;
	}

	public void setCompany_id(Integer company_id) {
		this.company_id = company_id;
	}

	public Integer getCompany_branch_id() {
		return company_branch_id;
	}

	public void setCompany_branch_id(Integer company_branch_id) {
		this.company_branch_id = company_branch_id;
	}

	public String getFinancial_year() {
		return financial_year;
	}

	public void setFinancial_year(String financial_year) {
		this.financial_year = financial_year;
	}

	public Integer getQuotation_type_id() {
		return quotation_type_id;
	}

	public void setQuotation_type_id(Integer quotation_type_id) {
		this.quotation_type_id = quotation_type_id;
	}

	public String getQuotation_type() {
		return quotation_type;
	}

	public void setQuotation_type(String quotation_type) {
		this.quotation_type = quotation_type;
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

	public String getQuotation_no() {
		return quotation_no;
	}

	public void setQuotation_no(String quotation_no) {
		this.quotation_no = quotation_no;
	}

	public String getQuotation_date() {
		return quotation_date;
	}

	public void setQuotation_date(String quotation_date) {
		this.quotation_date = quotation_date;
	}

	public Integer getQuotation_version() {
		return quotation_version;
	}

	public void setQuotation_version(Integer quotation_version) {
		this.quotation_version = quotation_version;
	}

	public Integer getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}

	public String getCustomer_contacts_ids() {
		return customer_contacts_ids;
	}

	public void setCustomer_contacts_ids(String customer_contacts_ids) {
		this.customer_contacts_ids = customer_contacts_ids;
	}

	public Integer getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(Integer department_id) {
		this.department_id = department_id;
	}

	public Integer getCustomer_state_id() {
		return customer_state_id;
	}

	public void setCustomer_state_id(Integer customer_state_id) {
		this.customer_state_id = customer_state_id;
	}

	public Integer getCustomer_city_id() {
		return customer_city_id;
	}

	public void setCustomer_city_id(Integer customer_city_id) {
		this.customer_city_id = customer_city_id;
	}

	public Integer getExpected_branch_id() {
		return expected_branch_id;
	}

	public void setExpected_branch_id(Integer expected_branch_id) {
		this.expected_branch_id = expected_branch_id;
	}

	public Integer getExpected_branch_state_id() {
		return expected_branch_state_id;
	}

	public void setExpected_branch_state_id(Integer expected_branch_state_id) {
		this.expected_branch_state_id = expected_branch_state_id;
	}

	public Integer getExpected_branch_city_id() {
		return expected_branch_city_id;
	}

	public void setExpected_branch_city_id(Integer expected_branch_city_id) {
		this.expected_branch_city_id = expected_branch_city_id;
	}

	public Integer getQuotation_by_id() {
		return quotation_by_id;
	}

	public void setQuotation_by_id(Integer quotation_by_id) {
		this.quotation_by_id = quotation_by_id;
	}

	public Integer getEnquiry_by_id() {
		return enquiry_by_id;
	}

	public void setEnquiry_by_id(Integer enquiry_by_id) {
		this.enquiry_by_id = enquiry_by_id;
	}

	public Integer getAssign_to_head_id() {
		return assign_to_head_id;
	}

	public void setAssign_to_head_id(Integer assign_to_head_id) {
		this.assign_to_head_id = assign_to_head_id;
	}

	public Integer getAssign_to_id() {
		return assign_to_id;
	}

	public void setAssign_to_id(Integer assign_to_id) {
		this.assign_to_id = assign_to_id;
	}

	public Integer getApproved_by_id() {
		return approved_by_id;
	}

	public void setApproved_by_id(Integer approved_by_id) {
		this.approved_by_id = approved_by_id;
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

	public String getTax_applicable() {
		return tax_applicable;
	}

	public void setTax_applicable(String tax_applicable) {
		this.tax_applicable = tax_applicable;
	}

	public String getTransportation_applicable() {
		return transportation_applicable;
	}

	public void setTransportation_applicable(String transportation_applicable) {
		this.transportation_applicable = transportation_applicable;
	}

	public String getBom_applicable() {
		return bom_applicable;
	}

	public void setBom_applicable(String bom_applicable) {
		this.bom_applicable = bom_applicable;
	}

	public String getCosting_applicable() {
		return costing_applicable;
	}

	public void setCosting_applicable(String costing_applicable) {
		this.costing_applicable = costing_applicable;
	}

	public double getQuotation_material_cost() {
		return quotation_material_cost;
	}

	public void setQuotation_material_cost(double quotation_material_cost) {
		this.quotation_material_cost = quotation_material_cost;
	}

	public double getQuotation_process_cost() {
		return quotation_process_cost;
	}

	public void setQuotation_process_cost(double quotation_process_cost) {
		this.quotation_process_cost = quotation_process_cost;
	}

	public double getQuotation_labour_cost() {
		return quotation_labour_cost;
	}

	public void setQuotation_labour_cost(double quotation_labour_cost) {
		this.quotation_labour_cost = quotation_labour_cost;
	}

	public double getQuotation_other_cost() {
		return quotation_other_cost;
	}

	public void setQuotation_other_cost(double quotation_other_cost) {
		this.quotation_other_cost = quotation_other_cost;
	}

	public String getQuotation_other_cost_remark() {
		return quotation_other_cost_remark;
	}

	public void setQuotation_other_cost_remark(String quotation_other_cost_remark) {
		this.quotation_other_cost_remark = quotation_other_cost_remark;
	}

	public double getQuotation_administration_percent() {
		return quotation_administration_percent;
	}

	public void setQuotation_administration_percent(double quotation_administration_percent) {
		this.quotation_administration_percent = quotation_administration_percent;
	}

	public double getQuotation_administration_cost() {
		return quotation_administration_cost;
	}

	public void setQuotation_administration_cost(double quotation_administration_cost) {
		this.quotation_administration_cost = quotation_administration_cost;
	}

	public double getQuotation_profit_percent() {
		return quotation_profit_percent;
	}

	public void setQuotation_profit_percent(double quotation_profit_percent) {
		this.quotation_profit_percent = quotation_profit_percent;
	}

	public double getQuotation_profit_cost() {
		return quotation_profit_cost;
	}

	public void setQuotation_profit_cost(double quotation_profit_cost) {
		this.quotation_profit_cost = quotation_profit_cost;
	}

	public double getQuotation_discount_percent() {
		return quotation_discount_percent;
	}

	public void setQuotation_discount_percent(double quotation_discount_percent) {
		this.quotation_discount_percent = quotation_discount_percent;
	}

	public double getQuotation_discount_cost() {
		return quotation_discount_cost;
	}

	public void setQuotation_discount_cost(double quotation_discount_cost) {
		this.quotation_discount_cost = quotation_discount_cost;
	}

	public double getQuotation_taxable_cost() {
		return quotation_taxable_cost;
	}

	public void setQuotation_taxable_cost(double quotation_taxable_cost) {
		this.quotation_taxable_cost = quotation_taxable_cost;
	}

	public double getQuotation_cgst_cost() {
		return quotation_cgst_cost;
	}

	public void setQuotation_cgst_cost(double quotation_cgst_cost) {
		this.quotation_cgst_cost = quotation_cgst_cost;
	}

	public double getQuotation_sgst_cost() {
		return quotation_sgst_cost;
	}

	public void setQuotation_sgst_cost(double quotation_sgst_cost) {
		this.quotation_sgst_cost = quotation_sgst_cost;
	}

	public double getQuotation_igst_cost() {
		return quotation_igst_cost;
	}

	public void setQuotation_igst_cost(double quotation_igst_cost) {
		this.quotation_igst_cost = quotation_igst_cost;
	}

	public double getQuotation_total_cost() {
		return quotation_total_cost;
	}

	public void setQuotation_total_cost(double quotation_total_cost) {
		this.quotation_total_cost = quotation_total_cost;
	}

	public Integer getAgent_id() {
		return agent_id;
	}

	public void setAgent_id(Integer agent_id) {
		this.agent_id = agent_id;
	}

	public double getAgent_percent() {
		return agent_percent;
	}

	public void setAgent_percent(double agent_percent) {
		this.agent_percent = agent_percent;
	}

	public String getQuotation_mail_sent_status() {
		return quotation_mail_sent_status;
	}

	public void setQuotation_mail_sent_status(String quotation_mail_sent_status) {
		this.quotation_mail_sent_status = quotation_mail_sent_status;
	}

	public String getQuotation_status() {
		return quotation_status;
	}

	public void setQuotation_status(String quotation_status) {
		this.quotation_status = quotation_status;
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

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public Date getCreated_on() {
		return created_on;
	}

	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
	}

	public String getModified_by() {
		return modified_by;
	}

	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}

	public Date getModified_on() {
		return modified_on;
	}

	public void setModified_on(Date modified_on) {
		this.modified_on = modified_on;
	}

	public String getDeleted_by() {
		return deleted_by;
	}

	public void setDeleted_by(String deleted_by) {
		this.deleted_by = deleted_by;
	}

	public Date getDeleted_on() {
		return deleted_on;
	}

	public void setDeleted_on(Date deleted_on) {
		this.deleted_on = deleted_on;
	}

	public CMtSalesQuotationMasterTradingModel(int quotation_master_transaction_id, Integer company_id,
	                                           Integer company_branch_id, String financial_year, Integer quotation_type_id, String quotation_type,
	                                           String quotation_life, String quotation_transaction_type, String quotation_no, String quotation_date,
	                                           Integer quotation_version, Integer customer_id, String customer_contacts_ids, Integer department_id,
	                                           Integer customer_state_id, Integer customer_city_id, Integer expected_branch_id,
	                                           Integer expected_branch_state_id, Integer expected_branch_city_id, Integer quotation_by_id,
	                                           Integer enquiry_by_id, Integer assign_to_head_id, Integer assign_to_id, Integer approved_by_id,
	                                           String approved_date, String overall_schedule_date, String tax_applicable, String transportation_applicable,
	                                           String bom_applicable, String costing_applicable, double quotation_material_cost,
	                                           double quotation_process_cost, double quotation_labour_cost, double quotation_other_cost,
	                                           String quotation_other_cost_remark, double quotation_administration_percent,
	                                           double quotation_administration_cost, double quotation_profit_percent, double quotation_profit_cost,
	                                           double quotation_discount_percent, double quotation_discount_cost, double quotation_taxable_cost,
	                                           double quotation_cgst_cost, double quotation_sgst_cost, double quotation_igst_cost,
	                                           double quotation_total_cost, Integer agent_id, double agent_percent, String quotation_mail_sent_status,
	                                           String quotation_status, String status_remark, String other_terms_conditions, String remark,
	                                           boolean is_active, boolean is_delete, String created_by, Date created_on, String modified_by,
	                                           Date modified_on, String deleted_by, Date deleted_on) {
		super();
		this.quotation_master_transaction_id = quotation_master_transaction_id;
		this.company_id = company_id;
		this.company_branch_id = company_branch_id;
		this.financial_year = financial_year;
		this.quotation_type_id = quotation_type_id;
		this.quotation_type = quotation_type;
		this.quotation_life = quotation_life;
		this.quotation_transaction_type = quotation_transaction_type;
		this.quotation_no = quotation_no;
		this.quotation_date = quotation_date;
		this.quotation_version = quotation_version;
		this.customer_id = customer_id;
		this.customer_contacts_ids = customer_contacts_ids;
		this.department_id = department_id;
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
		this.approved_date = approved_date;
		this.overall_schedule_date = overall_schedule_date;
		this.tax_applicable = tax_applicable;
		this.transportation_applicable = transportation_applicable;
		this.bom_applicable = bom_applicable;
		this.costing_applicable = costing_applicable;
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
		this.agent_id = agent_id;
		this.agent_percent = agent_percent;
		this.quotation_mail_sent_status = quotation_mail_sent_status;
		this.quotation_status = quotation_status;
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
	}

	public CMtSalesQuotationMasterTradingModel() {
		super();
	}
}
