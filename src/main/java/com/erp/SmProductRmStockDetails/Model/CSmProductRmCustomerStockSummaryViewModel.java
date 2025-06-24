package com.erp.SmProductRmStockDetails.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import io.swagger.models.auth.In;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Immutable
@Subselect("select * from smv_product_rm_stock_summary_customer")
public class CSmProductRmCustomerStockSummaryViewModel {


	@Id
	private int stock_transaction_id;
	private String financial_year;
	private String supplier_name;
	private String customer_name;
	private String product_type_name;
	private String product_type_group;
	private String product_rm_name;
	private String product_unit_name;
	private String product_packing_name;
	private double order_quantity;
	private double order_weight;
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
	private double customer_receipt_quantity;
	private double customer_receipt_weight;
	private double customer_return_quantity;
	private double customer_return_weight;
	private double customer_rejection_weight;
	private double customer_rejection_quantity;
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
	private double closing_balance_quantity;
	private double closing_balance_weight;
	private String godown_name;
	private String godown_short_name;
	private Integer godown_section_count;
	private String godown_section_name;
	private String godown_section_short_name;
	private String godown_section_beans_name;
	private String godown_section_beans_short_name;
	private String remark;
	private String company_name;
	private String company_branch_name;
	private String product_type_short_name;
	private double quantity_per_packing;
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
	private Integer company_id;
	private Integer company_branch_id;
	private Integer supplier_id;
	private Integer customer_id;
	private Integer product_type_id;
	private String product_rm_id;
	private Integer product_material_unit_id;
	private Integer product_material_packing_id;
	private Integer godown_id;
	private Integer godown_section_id;
	private Integer godown_section_beans_id;


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
