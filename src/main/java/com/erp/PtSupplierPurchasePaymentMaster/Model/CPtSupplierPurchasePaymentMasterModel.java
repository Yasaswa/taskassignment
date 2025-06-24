package com.erp.PtSupplierPurchasePaymentMaster.Model;

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
@Table(name = "ft_supplier_purchase_payment_master")
public class CPtSupplierPurchasePaymentMasterModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "supplier_purchase_payment_master_transaction_id")
	private int supplier_purchase_payment_master_transaction_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String financial_year;
	private String supplier_purchase_payment_no;
	private String supplier_purchase_payment_date;
	private Integer supplier_purchase_payment_version;
	private Integer supplier_purchase_payment_type_id;
	private String supplier_purchase_payment_type;
	private Integer supplier_id;
	private String supplier_contacts_ids;
	private Integer expected_branch_id;
	private Integer approved_by_id;
	private String approved_date;
	private Integer bank_id;
	private String payment_type;
	private String supplier_bank_details;
	private Integer supplier_bank_id;
	private String supplier_bank_account_number;
	private String supplier_bank_ifsc_code;
	private double total_billing_amount;
	private double total_billing_deduction;
	private Integer tax1_id;
	private double tax1_percent;
	private double tax1_amount;
	private Integer tax2_id;
	private double tax2_percent;
	private double tax2_amount;
	private double total_paid_amount;
	private String supplier_purchase_payment_status;
	private String purchase_payment_mail_sent_status;
	private String payment_transaction_details;
	private String payment_transaction_date;
	private String deduction_remark;
	private String remark;
	private boolean is_active = Boolean.TRUE;
	private boolean is_delete = Boolean.FALSE;
	private String created_by;
	@CreationTimestamp
	@Column(name = "created_on", updatable = false)
	private Date created_on;
	private String modified_by;
	@UpdateTimestamp
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;

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

	public String getSupplier_purchase_payment_date() {
		return supplier_purchase_payment_date;
	}

	public void setSupplier_purchase_payment_date(String supplier_purchase_payment_date) {
		if (supplier_purchase_payment_date == null || supplier_purchase_payment_date.isEmpty()) {
			this.supplier_purchase_payment_date = null;
		} else {
			this.supplier_purchase_payment_date = supplier_purchase_payment_date;
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

	public String getPayment_transaction_date() {
		return payment_transaction_date;
	}

	public void setPayment_transaction_date(String payment_transaction_date) {
		if (payment_transaction_date == null || payment_transaction_date.isEmpty()) {
			this.payment_transaction_date = null;
		} else {
			this.payment_transaction_date = payment_transaction_date;
		}
	}

}
