package com.erp.MtDispatchChallanDetails.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "mt_dispatch_challan_batchwise_trading")
public class CMtDispatchChallanBatchwiseTradingModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "dispatch_challan__batchwise_transaction_id")
	private int dispatch_challan__batchwise_transaction_id;
	private Integer dispatch_challan_details_transaction_id;
	private Integer dispatch_challan_master_transaction_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String financial_year;
	private String dispatch_challan_no;
	private String dispatch_challan_date;
	private Integer dispatch_challan_version;
	private String sales_order_no;
	private String sales_order_Date;
	private Integer sales_order_version;
	private String customer_order_no;
	private String customer_order_Date;
	private String dispatch_schedule_no;
	private String dispatch_schedule_date;
	private Integer dispatch_schedule_version;
	private String product_material_id;
	@Transient
	private String product_type_group;
	private String so_sr_no;
	private String batch_no;
	private double batch_dispatch_quantity;
	private double batch_dispatch_weight;
	private Integer batch_dispatch_roll;
	private String dispatch_challan_batch_status;
	private String dispatch_challan_batch_remark;
	private String goods_receipt_no;
	private Integer godown_id;
	private Integer godown_section_id;
	private Integer godown_section_beans_id;
	private String remark;
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
