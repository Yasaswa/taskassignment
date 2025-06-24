package com.erp.PtGoodsReceiptDetails.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "pt_goods_receipt_payment_terms")
public class CPtGoodsReceiptPaymentTermsModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "goods_receipt_payment_terms_transaction_id")
	private int goods_receipt_payment_terms_transaction_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String financial_year;
	private Integer goods_receipt_master_transaction_id;
	private Integer goods_receipt_details_transaction_id;
	private String goods_receipt_no;
	private String goods_receipt_date;
	private Integer goods_receipt_version;
	private String purchase_order_no;
	private String purchase_order_date;
	private Integer purchase_order_version;
	private Integer payment_terms_id;
	private String payment_terms_name;
	private String payment_terms_days;
	private Integer payment_terms_grace_days;
	private String payment_terms_Milestome;
	private String payment_percent;
	private double payment_expected_value;
	private String payment_expected_date;
	private String payment_paid_flag;
	private String payment_paid_transaction_id;
	private String payment_paid_date;
	private String remark;
	private boolean is_active = Boolean.TRUE;
	private boolean is_delete = Boolean.FALSE;
	@Column(name = "created_by", updatable = false)
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

	public void setPayment_expected_date(String payment_expected_date) {
		if (payment_expected_date == null || payment_expected_date.isEmpty()) {
			this.payment_expected_date = null;
		} else {
			this.payment_expected_date = payment_expected_date;
		}
	}

}
