package com.erp.SmProductRmStockDetails.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sm_product_rm_stock_details_customer")
public class CSmProductRmCustomerStockDetailsModel {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "stock_transaction_id")
	private int stock_transaction_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String financial_year;
	private String stock_date;
	private boolean day_closed = Boolean.FALSE;
	private String batch_no;
	private String batch_expiry_date;
	private Integer supplier_id;
	private String goods_receipt_no = null;
	private String goods_receipt_version;
	private Integer customer_id;
	private String sales_order_no = null;
	private Integer sales_order_version;
	private String so_sr_no;
	private String customer_order_no;
	private String customer_goods_receipt_no;
	private Integer customer_goods_receipt_version;
	private String product_type_group;
	private Integer product_type_id;
	private String product_rm_id;
	private Integer product_material_unit_id;
	private Integer product_material_packing_id;
	private double order_quantity;
	private double order_weight;
	private double batch_rate;
	private double pending_quantity = 0;
	private double pending_weight = 0;
	private double opening_quantity = 0;
	private double opening_weight = 0;
	private double reserve_quantity = 0;
	private double reserve_weight = 0;
	private double excess_quantity = 0;
	private double excess_weight = 0;
	private double pree_closed_quantity = 0;
	private double pree_closed_weight = 0;
	private double purchase_quantity = 0;
	private double purchase_weight = 0;
	private double purchase_return_quantity = 0;
	private double purchase_return_weight = 0;
	private double purchase_rejection_quantity = 0;
	private double purchase_rejection_weight = 0;
	private double jobcard_quantity = 0;
	private double jobcard_weight = 0;
	private double production_issue_quantity = 0;
	private double production_issue_weight = 0;
	private double production_issue_return_quantity = 0;
	private double production_issue_return_weight = 0;
	private double production_issue_rejection_quantity = 0;
	private double production_issue_rejection_weight = 0;
	private double production_quantity = 0;
	private double production_weight = 0;
	private double production_return_quantity = 0;
	private double production_return_weight = 0;
	private double production_rejection_quantity = 0;
	private double production_rejection_weight = 0;
	private double assembly_production_issue_quantity = 0;
	private double assembly_production_issue_weight = 0;
	private double sales_quantity = 0;
	private double sales_weight = 0;
	private double sales_return_quantity = 0;
	private double sales_return_weight = 0;
	private double sales_rejection_quantity = 0;
	private double sales_rejection_weight = 0;
	private double customer_receipt_quantity = 0;
	private double customer_receipt_weight = 0;
	private double customer_return_quantity = 0;
	private double customer_return_weight = 0;
	private double customer_rejection_weight = 0;
	private double customer_rejection_quantity = 0;
	private double transfer_issue_quantity = 0;
	private double transfer_issue_weight = 0;
	private double transfer_receipt_quantity = 0;
	private double transfer_receipt_weight = 0;
	private double outsources_out_quantity = 0;
	private double outsources_out_weight = 0;
	private double outsources_in_quantity = 0;
	private double outsources_in_weight = 0;
	private double outsources_rejection_quantity = 0;
	private double outsources_rejection_weight = 0;
	private double loan_receipt_quantity = 0;
	private double loan_receipt_weight = 0;
	private double loan_issue_quantity = 0;
	private double loan_issue_weight = 0;
	private double cancel_quantity = 0;
	private double cancel_weight = 0;
	private double difference_quantity = 0;
	private double difference_weight = 0;
	private double closing_balance_quantity;
	private double closing_balance_weight;
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
	@UpdateTimestamp
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;

	public boolean isDay_closed() {
		return day_closed;
	}

	public void setDay_closed(boolean day_closed) {
		this.day_closed = day_closed;
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

	public String getStock_date() {
		return stock_date;
	}

	public void setStock_date(String stock_date) {
		if (stock_date == null || stock_date.isEmpty()) {
			this.stock_date = null;
		} else {
			this.stock_date = stock_date;
		}
	}

	public String getBatch_expiry_date() {
		return batch_expiry_date;
	}

	public void setBatch_expiry_date(String batch_expiry_date) {

		if (batch_expiry_date == null || batch_expiry_date.isEmpty()) {
			this.batch_expiry_date = null;
		} else {
			this.batch_expiry_date = batch_expiry_date;
		}
	}
}
