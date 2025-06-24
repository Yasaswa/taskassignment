package com.erp.PtSupplierPurchasePaymentMaster.Model;

import com.google.errorprone.annotations.Immutable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Immutable
@Entity
@Subselect("select * from ftv_supplier_purchase_payment_details")
public class CPtSupplierPurchasePaymentDetailsViewModel {


	@Id
	private int supplier_purchase_payment_details_transaction_id;
	private String supplier_name;
	private String supplier_purchase_payment_no;
	private String supplier_purchase_payment_date;
	private Integer supplier_purchase_payment_version;
	private String supplier_purchase_payment_type;
	private String supplier_purchase_payment_status_desc;
	private String approved_by_name;
	private Double total_billing_amount;
	private String taxtype_name1;
	private Double tax1_percent;
	private Double tax1_amount;
	private String taxtype_name2;
	private Double tax2_percent;
	private Double tax2_amount;
	private Double total_paid_amount;
	private Double total_billing_deduction;
	private String financial_year;
	private String supplier_bill_booking_no;
	private String supplier_bill_booking_date;
	private Integer supplier_bill_booking_version;
	private String purchase_order_no;
	private String goods_receipt_no;
	private String supplier_challan_no;
	private String supplier_challan_date;
	private String supplier_invoice_date;
	private String expected_schedule_date;
	private String payment_due_date;
	private Double item_billing_amount;
	private Double item_prev_paid_amount;
	private Double item_pending_amount;
	private Double item_billing_deduction;
	private Double item_paid_amount;
	private String purchase_order_date;
	private String purchase_order_version;
	private String goods_receipt_date;
	private Integer goods_receipt_version;
	private String supplier_purchase_item_payment_status_desc;
	private String supplier_purchase_item_payment_status;
	private String remark;
	private String company_name;
	private String company_branch_name;
	private int supplier_bill_booking_details_transaction_id;
	private String Active;
	private String Deleted;
	private boolean is_active;
	private boolean is_delete;
	private String created_by;
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;
	private Integer supplier_purchase_payment_master_transaction_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String product_type_short_name;

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
