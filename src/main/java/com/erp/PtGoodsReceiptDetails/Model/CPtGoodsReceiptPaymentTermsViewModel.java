package com.erp.PtGoodsReceiptDetails.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Immutable
@Subselect("select * from ptv_goods_receipt_payment_terms")
public class CPtGoodsReceiptPaymentTermsViewModel {

	@Id
	private int goods_receipt_payment_terms_transaction_id;
	private String goods_receipt_no;
	private String goods_receipt_date;
	private Integer goods_receipt_version;
	private String purchase_order_no;
	private String purchase_order_date;
	private Integer purchase_order_version;
	private String payment_terms_name;
	private String payment_terms_days;
	private String financial_year;
	private Integer payment_terms_grace_days;
	private String payment_terms_Milestome;
	private String payment_percent;
	private double payment_expected_value;
	private String payment_expected_date;
	private String payment_paid_flag;
	private String payment_paid_transaction_id;
	private String payment_paid_date;
	private String remark;
	private String company_name;
	private String company_branch_name;
	private boolean is_active = Boolean.TRUE;
	private boolean is_delete = Boolean.FALSE;
	private String created_by;
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;
	private Integer company_id;
	private Integer company_branch_id;
	private Integer goods_receipt_master_transaction_id;
	private Integer goods_receipt_details_transaction_id;
	private Integer payment_terms_id;

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
