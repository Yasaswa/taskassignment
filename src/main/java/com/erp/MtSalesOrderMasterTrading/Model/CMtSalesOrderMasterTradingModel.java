package com.erp.MtSalesOrderMasterTrading.Model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "mt_sales_order_master_trading")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CMtSalesOrderMasterTradingModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sales_order_master_transaction_id")
	private int sales_order_master_transaction_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String financial_year;
	private Integer sales_order_type_id;
	private String sales_order_type;
	private String sales_order_life;
	private String sales_order_creation_type;
	private String sales_order_no;
	private String job_type;
	private String sales_order_date;
	private Integer sales_order_version;
	private String approved_date;
	private String overall_schedule_date;
	private double basic_total;
	private Integer supplier_branch_id;
	private String lot_no;

	public String getJob_type() {
		return job_type;
	}

	public void setJob_type(String job_type) {
		this.job_type = job_type;
	}

	private double transport_amount;
	private double freight_amount;
	private boolean is_freight_taxable = Boolean.FALSE;
	private double packing_amount;
	private double discount_percent;
	private double discount_amount;
	private double other_amount;
	private double taxable_total;
	private double cgst_percent;
	private double cgst_total;
	private double sgst_percent;
	private double sgst_total;
	private double igst_percent;
	private double igst_total;
	private double roundoff;
	private double grand_total;
	private Integer agent_id;
	private double agent_percent;
	private String agent_paid_status;
	private String sales_order_acceptance_status;
	private String sales_order_mail_sent_status;
	private String sales_order_status;
	private String status_remark;
	private String sales_quotation_no;
	private String sales_quotation_date;
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
	private String customer_contacts_ids;
	private Integer freight_hsn_code_id;
	private Integer customer_id;
	private Integer customer_state_id;
	private Integer customer_city_id;
	private Integer consignee_id;
	private Integer consignee_state_id;
	private Integer consignee_city_id;
	private String customer_order_no;
	private String customer_order_Date;

	public Integer getSales_type() {
		return sales_type;
	}

	public void setSales_type(Integer sales_type) {
		this.sales_type = sales_type;
	}

	private Integer department_id;
	private Integer approved_by_id;
	private Integer sub_department_id;
	private Integer sales_type;


	public int getSales_order_master_transaction_id() {
		return sales_order_master_transaction_id;
	}


	public void setSales_order_master_transaction_id(int sales_order_master_transaction_id) {
		this.sales_order_master_transaction_id = sales_order_master_transaction_id;
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


	public Integer getSales_order_type_id() {
		return sales_order_type_id;
	}


	public void setSales_order_type_id(Integer sales_order_type_id) {
		this.sales_order_type_id = sales_order_type_id;
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


	public String getSales_order_creation_type() {
		return sales_order_creation_type;
	}


	public void setSales_order_creation_type(String sales_order_creation_type) {
		this.sales_order_creation_type = sales_order_creation_type;
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
		if (sales_order_date == null || sales_order_date.isEmpty()) {
			this.sales_order_date = null;
		} else {
			this.sales_order_date = sales_order_date;
		}
	}


	public Integer getSales_order_version() {
		return sales_order_version;
	}


	public void setSales_order_version(Integer sales_order_version) {
		this.sales_order_version = sales_order_version;
	}


	public Integer getCustomer_id() {
		return customer_id;
	}


	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
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


	public Integer getConsignee_id() {
		return consignee_id;
	}


	public void setConsignee_id(Integer consignee_id) {
		this.consignee_id = consignee_id;
	}


	public Integer getConsignee_state_id() {
		return consignee_state_id;
	}


	public void setConsignee_state_id(Integer consignee_state_id) {
		this.consignee_state_id = consignee_state_id;
	}


	public Integer getConsignee_city_id() {
		return consignee_city_id;
	}


	public void setConsignee_city_id(Integer consignee_city_id) {
		this.consignee_city_id = consignee_city_id;
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
		if (customer_order_Date == null || customer_order_Date.isEmpty()) {
			this.customer_order_Date = null;
		} else {
			this.customer_order_Date = customer_order_Date;
		}
	}


	public Integer getDepartment_id() {
		return department_id;
	}


	public void setDepartment_id(Integer department_id) {
		this.department_id = department_id;
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


	public double getBasic_total() {
		return basic_total;
	}


	public void setBasic_total(double basic_total) {
		this.basic_total = basic_total;
	}


	public double getTransport_amount() {
		return transport_amount;
	}


	public void setTransport_amount(double transport_amount) {
		this.transport_amount = transport_amount;
	}


	public double getFreight_amount() {
		return freight_amount;
	}


	public void setFreight_amount(double freight_amount) {
		this.freight_amount = freight_amount;
	}


	public double getPacking_amount() {
		return packing_amount;
	}


	public void setPacking_amount(double packing_amount) {
		this.packing_amount = packing_amount;
	}


	public double getDiscount_percent() {
		return discount_percent;
	}


	public void setDiscount_percent(double discount_percent) {
		this.discount_percent = discount_percent;
	}


	public double getDiscount_amount() {
		return discount_amount;
	}


	public void setDiscount_amount(double discount_amount) {
		this.discount_amount = discount_amount;
	}


	public double getOther_amount() {
		return other_amount;
	}


	public void setOther_amount(double other_amount) {
		this.other_amount = other_amount;
	}


	public double getTaxable_total() {
		return taxable_total;
	}


	public void setTaxable_total(double taxable_total) {
		this.taxable_total = taxable_total;
	}


	public double getCgst_percent() {
		return cgst_percent;
	}


	public void setCgst_percent(double cgst_percent) {
		this.cgst_percent = cgst_percent;
	}


	public double getCgst_total() {
		return cgst_total;
	}


	public void setCgst_total(double cgst_total) {
		this.cgst_total = cgst_total;
	}


	public double getSgst_percent() {
		return sgst_percent;
	}


	public void setSgst_percent(double sgst_percent) {
		this.sgst_percent = sgst_percent;
	}


	public double getSgst_total() {
		return sgst_total;
	}


	public void setSgst_total(double sgst_total) {
		this.sgst_total = sgst_total;
	}


	public double getIgst_percent() {
		return igst_percent;
	}


	public void setIgst_percent(double igst_percent) {
		this.igst_percent = igst_percent;
	}


	public double getIgst_total() {
		return igst_total;
	}


	public void setIgst_total(double igst_total) {
		this.igst_total = igst_total;
	}


	public double getRoundoff() {
		return roundoff;
	}


	public void setRoundoff(double roundoff) {
		this.roundoff = roundoff;
	}


	public double getGrand_total() {
		return grand_total;
	}


	public void setGrand_total(double grand_total) {
		this.grand_total = grand_total;
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

		if (sales_quotation_date == null || sales_quotation_date.isEmpty()) {
			this.sales_quotation_date = null;
		} else {
			this.sales_quotation_date = sales_quotation_date;
		}
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


	public String getCustomer_contacts_ids() {
		return customer_contacts_ids;
	}


	public void setCustomer_contacts_ids(String customer_contacts_ids) {
		this.customer_contacts_ids = customer_contacts_ids;
	}

	public boolean isIs_freight_taxable() {
		return is_freight_taxable;
	}


	public void setIs_freight_taxable(boolean is_freight_taxable) {
		this.is_freight_taxable = is_freight_taxable;
	}


	public Integer getFreight_hsn_code_id() {
		return freight_hsn_code_id;
	}


	public void setFreight_hsn_code_id(Integer freight_hsn_code_id) {
		this.freight_hsn_code_id = freight_hsn_code_id;
	}

	public Integer getSub_department_id() {
		return sub_department_id;
	}

	public void setSub_department_id(Integer sub_department_id) {
		this.sub_department_id = sub_department_id;
	}

	public CMtSalesOrderMasterTradingModel(int sales_order_master_transaction_id, Integer company_id, Integer company_branch_id, String financial_year, Integer sales_order_type_id, String sales_order_type, String sales_order_life, String sales_order_creation_type, String sales_order_no, String job_type, String sales_order_date, Integer sales_order_version, String approved_date, String overall_schedule_date, double basic_total, double transport_amount, double freight_amount, boolean is_freight_taxable, double packing_amount, double discount_percent, double discount_amount, double other_amount, double taxable_total, double cgst_percent, double cgst_total, double sgst_percent, double sgst_total, double igst_percent, double igst_total, double roundoff, double grand_total, Integer agent_id, double agent_percent, String agent_paid_status, String sales_order_acceptance_status, String sales_order_mail_sent_status, String sales_order_status, String status_remark, String sales_quotation_no, String sales_quotation_date, String other_terms_conditions, String remark, boolean is_active, boolean is_delete, String created_by, Date created_on, String modified_by, Date modified_on, String deleted_by, Date deleted_on, String customer_contacts_ids, Integer freight_hsn_code_id, Integer customer_id, Integer customer_state_id, Integer customer_city_id, Integer consignee_id, Integer consignee_state_id, Integer consignee_city_id, String customer_order_no, String customer_order_Date, Integer department_id, Integer approved_by_id, Integer sub_department_id, Integer sales_type) {
		this.sales_order_master_transaction_id = sales_order_master_transaction_id;
		this.company_id = company_id;
		this.company_branch_id = company_branch_id;
		this.financial_year = financial_year;
		this.sales_order_type_id = sales_order_type_id;
		this.sales_order_type = sales_order_type;
		this.sales_order_life = sales_order_life;
		this.sales_order_creation_type = sales_order_creation_type;
		this.sales_order_no = sales_order_no;
		this.job_type = job_type;
		this.sales_order_date = sales_order_date;
		this.sales_order_version = sales_order_version;
		this.approved_date = approved_date;
		this.overall_schedule_date = overall_schedule_date;
		this.basic_total = basic_total;
		this.transport_amount = transport_amount;
		this.freight_amount = freight_amount;
		this.is_freight_taxable = is_freight_taxable;
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
		this.agent_id = agent_id;
		this.agent_percent = agent_percent;
		this.agent_paid_status = agent_paid_status;
		this.sales_order_acceptance_status = sales_order_acceptance_status;
		this.sales_order_mail_sent_status = sales_order_mail_sent_status;
		this.sales_order_status = sales_order_status;
		this.status_remark = status_remark;
		this.sales_quotation_no = sales_quotation_no;
		this.sales_quotation_date = sales_quotation_date;
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
		this.customer_contacts_ids = customer_contacts_ids;
		this.freight_hsn_code_id = freight_hsn_code_id;
		this.customer_id = customer_id;
		this.customer_state_id = customer_state_id;
		this.customer_city_id = customer_city_id;
		this.consignee_id = consignee_id;
		this.consignee_state_id = consignee_state_id;
		this.consignee_city_id = consignee_city_id;
		this.customer_order_no = customer_order_no;
		this.customer_order_Date = customer_order_Date;
		this.department_id = department_id;
		this.approved_by_id = approved_by_id;
		this.sub_department_id = sub_department_id;
		this.sales_type = sales_type;
	}

	public CMtSalesOrderMasterTradingModel() {
		super();
	}

}
