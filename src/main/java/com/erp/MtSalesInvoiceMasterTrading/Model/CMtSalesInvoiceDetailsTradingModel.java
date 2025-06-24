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
@Table(name = "ft_sales_invoice_details_trading")
public class CMtSalesInvoiceDetailsTradingModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sales_invoice_details_transaction_id")
	private int sales_invoice_details_transaction_id;
	private Integer sales_invoice_master_transaction_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String financial_year;
	private Integer sales_invoice_type_id;
	private String sales_invoice_type;
	private String sales_invoice_creation_type;
	private String sales_invoice_no;
	private String sales_invoice_date;
	private Integer sales_invoice_version;
	private String dispatch_challan_no;
	private String dispatch_challan_date;
	private Integer dispatch_challan_version;
	private String sales_order_no;
	private String sales_order_Date;
	private String sales_order_version;
	private String customer_order_no;
	private String customer_order_Date;
	private String dispatch_schedule_no;
	private String dispatch_schedule_date;
	private String dispatch_schedule_version;
	private Integer sr_no;
	private String product_material_id;
	private String so_sr_no;
	private String product_material_print_name;
	private String product_material_tech_spect;
	private Integer product_material_unit_id;
	private Integer product_material_packing_id;
	private double invoice_quantity;
	private double invoice_weight;
	private Integer hsn_sac_id;
	private double hsn_sac_percent;
	private double item_rate;
	private double item_amount;
	private double item_freight_amount;
	private double item_discount_percent;
	private double item_discount_amount;
	private double item_taxable_amount;
	private double item_cgst_percent;
	private double item_cgst_amount;
	private double item_sgst_percent;
	private double item_sgst_amount;
	private double item_igst_percent;
	private double item_igst_amount;
	private double item_total_amount;
	private double invoice_return_quantity;
	private double invoice_return_weight;
	private String item_payment_schedule_date;
	private String sales_invoice_item_status;
	private String sales_invoice_remark;
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
	private double item_tcs_amount;
	private double item_cess_amount;
	private double item_stcess_amount;
	private double item_cess_advance_amount;
	private int dispatch_challan_details_transaction_id;

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

	public String getSales_order_Date() {
		return sales_order_Date;
	}

	public void setSales_order_Date(String sales_order_Date) {
		if (sales_order_Date == null || sales_order_Date.isEmpty()) {
			this.sales_order_Date = null;
		} else {
			this.sales_order_Date = sales_order_Date;
		}

	}

	public String getCustomer_order_Date() {
		return customer_order_Date;
	}

	public void setCustomer_order_Date(String customer_order_Date) {
		if (customer_order_Date == null || customer_order_Date.isEmpty()) {
			this.customer_order_Date = null;
		} else {
			this.customer_order_Date = customer_order_Date;
		}
	}

	public String getDispatch_schedule_date() {
		return dispatch_schedule_date;
	}

	public void setDispatch_schedule_date(String dispatch_schedule_date) {
		if (dispatch_schedule_date == null || dispatch_schedule_date.isEmpty()) {
			this.dispatch_schedule_date = null;
		} else {
			this.dispatch_schedule_date = dispatch_schedule_date;
		}
	}

	public String getItem_payment_schedule_date() {
		return item_payment_schedule_date;
	}

	public void setItem_payment_schedule_date(String item_payment_schedule_date) {
		if (item_payment_schedule_date == null || item_payment_schedule_date.isEmpty()) {
			this.item_payment_schedule_date = null;
		} else {
			this.item_payment_schedule_date = item_payment_schedule_date;
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
