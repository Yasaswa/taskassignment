package com.erp.MtDispatchChallanDetails.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "mt_dispatch_challan_details_trading")
public class CMtDispatchChallanDetailsTradingModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "dispatch_challan_details_transaction_id")
	private int dispatch_challan_details_transaction_id;
	private Integer dispatch_challan_master_transaction_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String financial_year;
	private Integer dispatch_challan_type_id;
	private String dispatch_challan_type;
	private String dispatch_challan_no;
	private String dispatch_challan_date;
	private Integer dispatch_challan_version;
	private String customer_order_no;
	private String customer_order_Date;
	private String dispatch_schedule_no;
	private String dispatch_schedule_date;
	private Integer dispatch_schedule_version;
	private String sales_order_no;
	private String sales_order_Date;
	private Integer sales_order_version;
	private Integer sr_no;
	@Transient
	private String product_type_group;
	private String product_material_id;
	private String so_sr_no;
	private String product_material_print_name;
	private String product_material_tech_spect;
	private Integer product_material_unit_id;
	private Integer product_material_packing_id;
	private double dispatch_quantity;
	private double dispatch_weight;
	private Integer hsn_sac_id;
	private double hsn_sac_percent;
	private double item_rate;
	private double item_amount;
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
	private double item_freight_amount;
	private double dispatch_return_quantity;
	private double dispatch_return_weight;
	private double pending_quantity;
	private double pending_weight;
	private String expected_schedule_date;
	private Integer delayed_days;
	private double invoice_quantity;
	private double invoice_weight;
	private boolean pree_close_flag = Boolean.FALSE;
	private Double pree_close_quantity;
	private String dispatch_challan_item_status;
	private String dispatch_challan_remark;
	private String remark;
	private String set_no;
	private boolean is_active = Boolean.TRUE;
	private boolean is_delete = Boolean.FALSE;
	@Column(name = "created_by", updatable = false)
	private String created_by;
	@CreationTimestamp
	@Column(name = "created_on", updatable = false)
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;
	private Double dispatch_schedule_quantity ;


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
