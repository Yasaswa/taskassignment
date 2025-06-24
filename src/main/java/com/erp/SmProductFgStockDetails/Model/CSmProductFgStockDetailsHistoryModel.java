package com.erp.SmProductFgStockDetails.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Immutable
@Entity
@Table(name = "sm_product_fg_stock_details_history")
public class CSmProductFgStockDetailsHistoryModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "stock_transaction_id")
	private int stock_transaction_id;
	private int company_id;
	private Integer company_branch_id;
	private String financial_year;
	private String stock_date;
	private boolean day_closed = Boolean.FALSE;
	private String batch_no;
	private String batch_expiry_date;
	private Integer customer_id;
	private String sales_order_no;
	private Integer sales_order_version;
	private String goods_receipt_no;
	private Integer goods_receipt_version;
	private String so_sr_no;
	private String customer_order_no;
	private String product_type_group;
	private Integer product_type_id;
	private String product_fg_id;
	private Integer product_material_unit_id;
	private Integer product_material_packing_id;
	private double order_quantity;
	private Integer order_weight;
	private double batch_rate;
	private double pending_quantity;
	private double pending_weight;
	private double opening_quantity;
	private double opening_weight;
	private double reserve_quantity;
	private double reserve_weight;
	private double excess_quantity;
	private double excess_weight;
	private double pree_closed_quantity;
	private double pree_closed_weight;
	private double purchase_quantity;
	private double purchase_weight;
	private double purchase_return_quantity;
	private double purchase_return_weight;
	private double purchase_rejection_quantity;
	private double purchase_rejection_weight;
	private double jobcard_quantity;
	private double jobcard_weight;
	private double production_issue_quantity;
	private double production_issue_weight;
	private double production_issue_return_quantity;
	private double production_issue_return_weight;
	private double production_issue_rejection_quantity;
	private double production_issue_rejection_weight;
	private double production_quantity;
	private double production_weight;
	private double production_return_quantity;
	private double production_return_weight;
	private double production_rejection_quantity;
	private double production_rejection_weight;
	private double assembly_production_issue_quantity;
	private double assembly_production_issue_weight;
	private double sales_quantity;
	private double sales_weight;
	private double sales_return_quantity;
	private double sales_return_weight;
	private double sales_rejection_quantity;
	private double sales_rejection_weight;
	private double transfer_issue_quantity;
	private double transfer_issue_weight;
	private double transfer_receipt_quantity;
	private double transfer_receipt_weight;
	private double outsources_out_quantity;
	private double outsources_out_weight;
	private double outsources_in_quantity;
	private double outsources_in_weight;
	private double outsources_rejection_quantity;
	private double outsources_rejection_weight;
	private double loan_receipt_quantity;
	private double loan_receipt_weight;
	private double loan_issue_quantity;
	private double loan_issue_weight;
	private double cancel_quantity;
	private double cancel_weight;
	private double difference_quantity;
	private double difference_weight;
	private Integer godown_id;
	private Integer godown_section_id;
	private Integer godown_section_beans_id;
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
	private double closing_balance_quantity;
	private double closing_balance_weight;

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
