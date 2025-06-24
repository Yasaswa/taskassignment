package com.erp.PtGoodsReceiptMasterCustomer.Model;

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
@Table(name = "pt_goods_receipt_master_customer")
public class CPtGoodsReceiptMasterCustomerModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_goods_receipt_master_transaction_id")
	private int customer_goods_receipt_master_transaction_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String financial_year;
	private String customer_goods_receipt_no;
	private String customer_goods_receipt_date;
	private Integer customer_goods_receipt_version;
	private Integer customer_id;
	private Integer customer_state_id;
	private Integer customer_city_id;
	private Integer expected_branch_id;
	private String customer_order_no;
	private String customer_order_date;
	private Integer customer_goods_receipt_type_id;
	private String customer_goods_receipt_type;
	private String customer_challan_no;
	private String customer_challan_Date;
	private Integer approved_by_id;
	private String approved_date;
	private Integer qa_by_id;
	private String qa_date;
	private String customer_goods_receipt_status;
	private String lr_no;
	private String lr_date;
	private String vehicle_no;
	private String ewb_no;
	private String ewb_valid_till;
	private String remark;
	private Double grand_total;
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

	public String getlr_date() {
		return customer_goods_receipt_date;
	}

	public void setlr_date(String lr_date) {
		if (lr_date == null || lr_date.isEmpty()) {
			this.lr_date = null;
		} else {
			this.lr_date = lr_date;
		}
	}

	public String getCustomer_goods_receipt_date() {
		return customer_goods_receipt_date;
	}

	public void setCustomer_goods_receipt_date(String customer_goods_receipt_date) {
		if (customer_goods_receipt_date == null || customer_goods_receipt_date.isEmpty()) {
			this.customer_goods_receipt_date = null;
		} else {
			this.customer_goods_receipt_date = customer_goods_receipt_date;
		}
	}

	public String getCustomer_order_date() {
		return customer_order_date;
	}

	public void setCustomer_order_date(String customer_order_date) {
		if (customer_order_date == null || customer_order_date.isEmpty()) {
			this.customer_order_date = null;
		} else {
			this.customer_order_date = customer_order_date;
		}
	}

	public String getCustomer_challan_Date() {
		return customer_challan_Date;
	}

	public void setCustomer_challan_Date(String customer_challan_Date) {
		if (customer_challan_Date == null || customer_challan_Date.isEmpty()) {
			this.customer_challan_Date = null;
		} else {
			this.customer_challan_Date = customer_challan_Date;
		}
	}

	public String getEwb_valid_till() {
		return ewb_valid_till;
	}

	public void setEwb_valid_till(String customer_challan_Date) {
		if (ewb_valid_till == null || ewb_valid_till.isEmpty()) {
			this.ewb_valid_till = null;
		} else {
			this.ewb_valid_till = ewb_valid_till;
		}
	}

	public String getQa_date() {
		return qa_date;
	}

	public void setQa_date(String qa_date) {
		if (qa_date == null || qa_date.isEmpty()) {
			this.qa_date = null;
		} else {
			this.qa_date = qa_date;
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
