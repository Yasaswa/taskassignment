package com.erp.SmProductPr.Model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "sm_product_pr_stock_details_history")
public class CSmProductPrStockDetailsHistoryModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "stock_transaction_id")
	private int stock_transaction_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String financial_year;
	private String stock_date;
	private Boolean day_closed = Boolean.FALSE;
	private String batch_no;
	private String batch_expiry_date;
	private Integer supplier_id;
	private String goods_receipt_no;
	private String goods_receipt_version;
	private Integer customer_id;
	private String project_name;
	private String project_referance_number;
	private String sales_order_no;
	private Integer sales_order_version;
	private String so_sr_no;
	private String customer_order_no;
	private String product_type_group;
	private Integer product_type_id;
	private String product_pr_id;
	private Integer product_material_unit_id;
	private Integer product_material_packing_id;
	private Double order_quantity;
	private Double order_weight;
	private Double batch_rate;
	private Double pending_quantity;
	private Double pending_weight;
	private Double opening_quantity;
	private Double opening_weight;
	private Double reserve_quantity;
	private Double reserve_weight;
	private Double excess_quantity;
	private Double excess_weight;
	private Double pree_closed_quantity;
	private Double pree_closed_weight;
	private Double purchase_quantity;
	private Double purchase_weight;
	private Double purchase_return_quantity;
	private Double purchase_return_weight;
	private Double purchase_rejection_quantity;
	private Double purchase_rejection_weight;
	private Double jobcard_quantity;
	private Double jobcard_weight;
	private Double production_issue_quantity;
	private Double production_issue_weight;
	private Double production_issue_return_quantity;
	private Double production_issue_return_weight;
	private Double production_issue_rejection_quantity;
	private Double production_issue_rejection_weight;
	private Double production_quantity;
	private Double production_weight;
	private Double production_return_quantity;
	private Double production_return_weight;
	private Double production_rejection_quantity;
	private Double production_rejection_weight;
	private Double assembly_production_issue_quantity;
	private Double assembly_production_issue_weight;
	private Double sales_quantity;
	private Double sales_weight;
	private Double sales_return_quantity;
	private Double sales_return_weight;
	private Double sales_rejection_quantity;
	private Double sales_rejection_weight;
	private Double transfer_issue_quantity;
	private Double transfer_issue_weight;
	private Double transfer_receipt_quantity;
	private Double transfer_receipt_weight;
	private Double outsources_out_quantity;
	private Double outsources_out_weight;
	private Double outsources_in_quantity;
	private Double outsources_in_weight;
	private Double outsources_rejection_quantity;
	private Double outsources_rejection_weight;
	private Double loan_receipt_quantity;
	private Double loan_receipt_weight;
	private Double loan_issue_quantity;
	private Double loan_issue_weight;
	private Double cancel_quantity;
	private Double cancel_weight;
	private Double difference_quantity;
	private Double difference_weight;
	private Double closing_balance_quantity;
	private Double closing_balance_weight;
	private String godown_id;
	private String godown_section_id;
	private String godown_section_beans_id;
	private String remark;
	private Boolean is_active = Boolean.TRUE;
	private Boolean is_delete = Boolean.FALSE;
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
	private String customer_goods_receipt_no;
	private String customer_goods_receipt_version;
	private Double customer_receipt_quantity;
	private Double customer_receipt_weight;
	private Double customer_return_quantity;
	private Double customer_return_weight;
	private Double customer_rejection_quantity;
	private Double customer_rejection_weight;
	
	public Boolean getIs_active() {
		return is_active;
	}

	public void setIs_active(Boolean is_active) {
		this.is_active = is_active;
	}

	public Boolean getIs_delete() {
		return is_delete;
	}

	public void setIs_delete(Boolean is_delete) {
		this.is_delete = is_delete;
	}

	public Boolean getDay_closed() {
		return day_closed;
	}

	public void setDay_closed(Boolean day_closed) {
		this.day_closed = day_closed;
	}

}
