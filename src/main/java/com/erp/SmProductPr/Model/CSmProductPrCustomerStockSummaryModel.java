package com.erp.SmProductPr.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sm_product_pr_stock_summary_customer")
public class CSmProductPrCustomerStockSummaryModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int stock_transaction_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String financial_year;
	private String product_type_group;
	private Integer product_type_id;
	private String product_pr_id;
	private Integer supplier_id;
	private Integer product_material_unit_id;
	private Integer product_material_packing_id;
	private Double order_quantity;
	private Double order_weight;
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
	private Double customer_receipt_quantity;
	private Double customer_receipt_weight;
	private Double customer_return_quantity;
	private Double customer_return_weight;
	private Double customer_rejection_quantity;
	private Double customer_rejection_weight;
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
	private String remark;
	private Boolean is_active = Boolean.TRUE;
	private Boolean is_delete = Boolean.FALSE;
	@Column(name = "created_by", updatable = false)
	private String created_by;
	private Date created_on;
	private String modified_by;
	@UpdateTimestamp
	@Column(name = "modified_on", updatable = false)
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;
	private Double closing_balance_quantity;
	private Double closing_balance_weight;
	private Integer customer_id;

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

}
