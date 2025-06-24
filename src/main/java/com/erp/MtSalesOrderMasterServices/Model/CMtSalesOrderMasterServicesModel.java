package com.erp.MtSalesOrderMasterServices.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "mt_sales_order_master_services")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CMtSalesOrderMasterServicesModel {

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
	private String sales_order_no;
	private String sales_order_date;
	private Integer sales_order_version;
	private String sales_order_creation_type;
	private Integer customer_id;
	private Integer customer_state_id;
	private Integer customer_city_id;
	private Integer consignee_id;
	private Integer consignee_state_id;
	private Integer consignee_city_id;
	private String customer_order_no;
	private String customer_order_Date;
	private Integer department_id;
	private Integer approved_by_id;
	private String approved_date;
	private String overall_schedule_date;
	private double basic_total;
	private double transport_amount;
	private double freight_amount;
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
	@UpdateTimestamp
	private Date deleted_on;
	private String customer_contacts_ids;

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
