package com.erp.PtCustomerSalesReceiptMaster.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ft_customer_sales_receipt_details")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CPtCustomerSalesReceiptDetailsModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_sales_receipt_details_transaction_id")
	private int customer_sales_receipt_details_transaction_id;
	private Integer customer_sales_receipt_master_transaction_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String financial_year;
	private String customer_sales_receipt_no;
	private String customer_sales_receipt_date;
	private Integer customer_sales_receipt_version;
	private String sales_order_no;
	private String sales_invoice_no;
	private String dispatch_challan_no;
	private String expected_schedule_date;
	private String payment_due_date;
	private double item_billing_amount;
	private double item_billing_deduction;
	private double item_paid_amount;
	private String sales_order_date;
	private String sales_order_version;
	private String sales_invoice_date;
	private Integer sales_invoice_version;
	private String dispatch_challan_date;
	private Integer dispatch_challan_version;
	private String customer_sales_item_receipt_status;
	@JsonIgnore
	@Transient
	private String sales_invoice_item_status;

	private String remark;
	private boolean is_active;
	private boolean is_delete;
	private String created_by;
	@CreationTimestamp
	@Column(name = "created_on", updatable = false)
	private Date created_on;
	private String modified_by;
	@UpdateTimestamp
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;
	private Integer sales_invoice_details_transaction_id;

	public String getCustomer_sales_receipt_date() {
		return customer_sales_receipt_date;
	}

	public void setCustomer_sales_receipt_date(String customer_sales_receipt_date) {
		if (customer_sales_receipt_date == null || customer_sales_receipt_date.isEmpty()) {
			this.customer_sales_receipt_date = null;
		} else {
			this.customer_sales_receipt_date = customer_sales_receipt_date;
		}
	}

	public String getExpected_schedule_date() {
		return expected_schedule_date;
	}

	public void setExpected_schedule_date(String expected_schedule_date) {
		if (expected_schedule_date == null || expected_schedule_date.isEmpty()) {
			this.expected_schedule_date = null;
		} else {
			this.expected_schedule_date = expected_schedule_date;
		}
	}

	public String getPayment_due_date() {
		return payment_due_date;
	}

	public void setPayment_due_date(String payment_due_date) {
		if (payment_due_date == null || payment_due_date.isEmpty()) {
			this.payment_due_date = null;
		} else {
			this.payment_due_date = payment_due_date;
		}
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

	public String getDispatch_challan_date() {
		return dispatch_challan_date;
	}

	public void setDispatch_challan_date(String dispatch_challan_date) {

		if (dispatch_challan_date == null || dispatch_challan_date.isEmpty()) {
			this.dispatch_challan_date = null;
		} else {
			this.dispatch_challan_date = dispatch_challan_date;

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
