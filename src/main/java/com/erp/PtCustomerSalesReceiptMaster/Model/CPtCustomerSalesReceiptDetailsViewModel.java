package com.erp.PtCustomerSalesReceiptMaster.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Immutable
@Entity
@Subselect("SELECT * FROM ftv_customer_sales_receipt_details")
public class CPtCustomerSalesReceiptDetailsViewModel {

	@Id
	private int customer_sales_receipt_details_transaction_id;
	private String customer_name;
	private String customer_sales_receipt_no;
	private String customer_sales_receipt_date;
	private Integer customer_sales_receipt_version;
	private String customer_sales_receipt_type;
	private String customer_sales_receipt_status_desc;
	private String approved_by_name;
	private Double total_billing_amount;
	private Double total_billing_deduction;
	private String taxtype_name1;
	private Double tax1_percent;
	private Double tax1_amount;
	private String taxtype_name2;
	private Double tax2_percent;
	private Double tax2_amount;
	private Double total_receipt_amount;
	private String financial_year;
	private String sales_order_no;
	private String sales_invoice_no;
	private String dispatch_challan_no;
	private String expected_schedule_date;
	private String payment_due_date;
	private Double item_billing_amount;
	private Double item_billing_deduction;
	private Double item_paid_amount;
	private Double item_prev_paid_amount;
	private Double item_pending_amount;
	private String sales_order_date;
	private Integer sales_order_version;
	private String sales_invoice_date;
	private Integer sales_invoice_version;
	private String dispatch_challan_date;
	private String dispatch_challan_version;
	private String customer_sales_item_receipt_status_desc;
	private String customer_sales_item_receipt_status;
	private String remark;
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
	private Integer customer_sales_receipt_master_transaction_id;
	private Integer company_id;
	private Integer company_branch_id;
	private Integer sales_invoice_details_transaction_id;

}
