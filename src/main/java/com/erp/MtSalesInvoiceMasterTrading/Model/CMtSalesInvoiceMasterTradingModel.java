package com.erp.MtSalesInvoiceMasterTrading.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "ft_sales_invoice_master_trading")
public class CMtSalesInvoiceMasterTradingModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sales_invoice_master_transaction_id")
	private int sales_invoice_master_transaction_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String financial_year;
	private Integer sales_invoice_type_id;
	private String sales_invoice_type;
	private String sales_invoice_creation_type;
	private String sales_invoice_no;
	private String sales_invoice_date;
	private Integer sales_invoice_version;
	private Integer customer_id;
	private String customer_contacts_ids;
	private Integer customer_state_id;
	private Integer customer_city_id;
	private String customer_gst_no;
	private String customer_pan_no;
	private Integer consignee_id;
	private Integer consignee_state_id;
	private Integer consignee_city_id;
	private String consignee_gst_no;
	private String consignee_pan_no;
	private String transporter_name;
	private Integer transporter_id;
	private Integer transporter_state_id;
	private String transporter_gst_no;
	private Integer expected_branch_id;
	private Integer expected_branch_state_id;
	private Integer expected_branch_city_id;
	private String vehicle_no;
	private String vehicle_type;
	private String transporter_challan_no;
	private String transporter_challan_date;
	private String transport_mode;
	private String transaction_type;
	private String is_service;
	private String is_sez;
	private String interim;
	private String loading_time;
	private Integer approved_by_id;
	private String approved_date;
	private String sales_invoice_status;
	private String payment_schedule_date;
	private double basic_total;
	private double transport_amount;
	private double freight_amount;
	private boolean is_freight_taxable = Boolean.FALSE;
	private Integer freight_hsn_code_id;
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
	private double agent_amount;
	private String agent_paid_status;
	private String sales_invoice_remark;
	private String other_terms_conditions;
	private double stcess_amount;
	private double tcs_amount;
	private double cess_amount;
	private String e_transaction_type;
	private double cess_advance_amount;
	private String e_inv_irn_no;
	private String e_inv_irn_date;
	private String e_inv_ack_no;
	private String e_inv_ack_date;
	private String e_inv_ewb_no;
	private String e_inv_ewb_valid_till_date;
	private String e_inv_qr_code;
	private String remark;
	private boolean is_active = Boolean.TRUE;
	private boolean is_delete = Boolean.FALSE;
	private String created_by;
	@CreationTimestamp
	@Column(name = "created_on", updatable = false)
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;

	public boolean isIs_freight_taxable() {
		return is_freight_taxable;
	}

	public void setIs_freight_taxable(boolean is_freight_taxable) {
		this.is_freight_taxable = is_freight_taxable;
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

	public String getSales_invoice_date() {
		return sales_invoice_date;
	}

	public void setSales_invoice_date(String sales_invoice_date) {

		if (sales_invoice_date == null || sales_invoice_date.isEmpty()) {
			this.sales_invoice_date = null;
		} else {
			this.sales_invoice_date = sales_invoice_date;
		}
	}

	public String getTransporter_challan_date() {
		return transporter_challan_date;
	}

	public void setTransporter_challan_date(String transporter_challan_date) {
		if (transporter_challan_date == null || transporter_challan_date.isEmpty()) {
			this.transporter_challan_date = null;
		} else {
			this.transporter_challan_date = transporter_challan_date;
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

	public String getPayment_schedule_date() {
		return payment_schedule_date;
	}

	public void setPayment_schedule_date(String payment_schedule_date) {
		if (payment_schedule_date == null || payment_schedule_date.isEmpty()) {
			this.payment_schedule_date = null;
		} else {
			this.payment_schedule_date = payment_schedule_date;
		}
	}

	public String getE_inv_irn_date() {
		return e_inv_irn_date;
	}

	public void setE_inv_irn_date(String e_inv_irn_date) {
		if (e_inv_irn_date == null || e_inv_irn_date.isEmpty()) {
			this.e_inv_irn_date = null;
		} else {
			this.e_inv_irn_date = e_inv_irn_date;
		}
	}

	public String getE_inv_ack_date() {
		return e_inv_ack_date;
	}

	public void setE_inv_ack_date(String e_inv_ack_date) {
		if (e_inv_ack_date == null || e_inv_ack_date.isEmpty()) {
			this.e_inv_ack_date = null;
		} else {
			this.e_inv_ack_date = e_inv_ack_date;
		}
	}

	public String getE_inv_ewb_valid_till_date() {
		return e_inv_ewb_valid_till_date;
	}

	public void setE_inv_ewb_valid_till_date(String e_inv_ewb_valid_till_date) {
		if (e_inv_ewb_valid_till_date == null || e_inv_ewb_valid_till_date.isEmpty()) {
			this.e_inv_ewb_valid_till_date = null;
		} else {
			this.e_inv_ewb_valid_till_date = e_inv_ewb_valid_till_date;
		}
	}


}
