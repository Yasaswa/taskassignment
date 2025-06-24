package com.erp.PtCustomerSalesReceiptMaster.Model;

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
@Table(name = "ft_customer_sales_receipt_master")
public class CPtCustomerSalesReceiptMasterModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_sales_receipt_master_transaction_id")
	private int customer_sales_receipt_master_transaction_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String financial_year;
	private String customer_sales_receipt_no;
	private String customer_sales_receipt_date;
	private Integer customer_sales_receipt_version;
	private Integer customer_sales_receipt_type_id;
	private String customer_sales_receipt_type;
	private Integer customer_id;
	private String customer_contacts_ids;
	private Integer expected_branch_id;
	private Integer approved_by_id;
	private String approved_date;
	private Integer bank_id;
	private String receipt_type;
	private String customer_bank_details;
	private Integer customer_bank_id;
	private String customer_bank_account_number;
	private String customer_bank_ifsc_code;
	private double total_billing_amount;
	private double total_billing_deduction;
	private Integer tax1_id;
	private double tax1_percent;
	private double tax1_amount;
	private Integer tax2_id;
	private double tax2_percent;
	private double tax2_amount;
	private double total_receipt_amount;
	private String customer_receipt_mail_sent_status;
	private String customer_sales_receipt_status;
	private String receipt_transaction_details;
	private String receipt_transaction_date;
	private String deduction_remark;
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

	public String getReceiptt_transaction_date() {
		return receipt_transaction_date;
	}

	public void setReceiptt_transaction_date(String receipt_transaction_date) {
		if (receipt_transaction_date == null || receipt_transaction_date.isEmpty()) {
			this.receipt_transaction_date = null;
		} else {
			this.receipt_transaction_date = receipt_transaction_date;
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
