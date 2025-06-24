package com.erp.PtSupplierBillBookingMaster.Model;

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
@Table(name = "ft_supplier_bill_booking_details")
public class CPtSupplierBillBookingDetailsModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "supplier_bill_booking_details_transaction_id")
	private int supplier_bill_booking_details_transaction_id;
	private Integer supplier_bill_booking_master_transaction_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String financial_year;
	private String supplier_bill_booking_no;
	private String supplier_bill_booking_date;
	private Integer supplier_bill_booking_version;
	private String purchase_order_no;
	private String goods_receipt_no;
	private String supplier_challan_no;
	private String supplier_challan_date;
	private String supplier_invoice_no;
	private String supplier_invoice_date;
	private double bill_total;
	private String expected_schedule_date;
	private String payment_due_date;
	private double basic_total;
	private double freight_amount;
	private double packing_amount;
	private double discount_amount;
	private double other_amount;
	private double taxable_total;
	private double cgst_total;
	private double sgst_total;
	private double igst_total;
	private double roundoff;
	private String purchase_order_date;
	private Integer purchase_order_version;
	private String goods_receipt_date;
	private Integer goods_receipt_version;
	private String supplier_bill_item_booking_status;
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

	public String getSupplier_challan_date() {
		return supplier_challan_date;
	}

	public void setSupplier_challan_date(String supplier_challan_date) {
		if (supplier_challan_date == null || supplier_challan_date.isEmpty()) {
			this.supplier_challan_date = null;
		} else {
			this.supplier_challan_date = supplier_challan_date;
		}
	}

	public String getSupplier_invoice_date() {
		return supplier_invoice_date;
	}

	public void setSupplier_invoice_date(String supplier_invoice_date) {
		if (supplier_invoice_date == null || supplier_invoice_date.isEmpty()) {
			this.supplier_invoice_date = null;
		} else {
			this.supplier_invoice_date = supplier_invoice_date;
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

	public String getPurchase_order_date() {
		return purchase_order_date;
	}

	public void setPurchase_order_date(String purchase_order_date) {
		if (purchase_order_date == null || purchase_order_date.isEmpty()) {
			this.purchase_order_date = null;
		} else {
			this.purchase_order_date = purchase_order_date;
		}
	}

	public String getGoods_receipt_date() {
		return goods_receipt_date;
	}

	public void setGoods_receipt_date(String goods_receipt_date) {
		if (goods_receipt_date == null || goods_receipt_date.isEmpty()) {
			this.goods_receipt_date = null;
		} else {
			this.goods_receipt_date = goods_receipt_date;
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
