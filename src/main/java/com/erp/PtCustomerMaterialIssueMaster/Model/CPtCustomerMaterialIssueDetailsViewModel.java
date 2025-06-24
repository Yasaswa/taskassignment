package com.erp.PtCustomerMaterialIssueMaster.Model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Subselect;
import org.springframework.data.annotation.Immutable;

import com.erp.StIndentIssueMaster.Model.CStIndentIssueDetailsViewModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Immutable
@Subselect("Select * From ptv_customer_material_issue_details")
public class CPtCustomerMaterialIssueDetailsViewModel {
	
	@Id
	private int customer_material_issue_details_transaction_id;
	private String customer_material_issue_no;
	private String customer_material_issue_date;
	private Integer customer_material_issue_version;
	private String customer_goods_receipt_no;
	private String customer_goods_receipt_date;
	private Integer customer_goods_receipt_version;    
	private String customer_item_status_desc;
	private String issue_item_status_desc;
	private String issue_item_status;
	private String customer_order_no;
	private String customer_order_date;
	private String customer_material_issue_type;
	private String customer_challan_no;
	private String customer_challan_Date;
	private String ewb_no;
	private String ewb_valid_till;
	private String qa_by_name;
	private String qa_date;
	private String lr_no;
	private String lr_date;
	private String vehicle_no;
	private String expiry_date;
	private String customer_state_name;
	private String customer_city_name;
	private String product_type_group;
	private String product_material_name;
	private String product_material_std_weight;
	private String customer_batch_no;
	private String customer_material_tech_spect;
	private Integer sr_no;
	private String customer_material_unit_name;
	private String customer_material_packing_name;
	private String customer_material_hsn_code;
	private double customer_material_hsn_code_rate;
	private double product_material_stock_quantity;
	private double product_material_stock_weight;
	private double product_material_issue_quantity;
	private double product_material_issue_weight;
	private double customer_material_order_quantity;
	private double customer_material_order_weight;
	private double customer_material_accepted_quantity;
	private double customer_material_accepted_weight;
	private double customer_material_conversion_factor;
	private double customer_material_rejected_quantity;
	private double customer_material_rejected_weight;
	private double customer_material_rate;
	private double customer_material_item_amount;
	private double customer_material_prev_accepted_quantity;
	private double customer_material_prev_accepted_weight;
	private double pree_closed_customer_quantity;
	private double pree_closed_customer_weight;
	private double customer_return_quantity;
	private double customer_return_weight;
	private double production_issue_quantity;
	private double production_issue_weight;
	private double production_issue_return_quantity;
	private double production_issue_return_weight;
	private double production_issue_rejection_quantity;
	private double production_issue_rejection_weight;
	private double assembly_production_issue_quantity;
	private double assembly_production_issue_weight;
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
	private String customer_item_status;
	private String godown_name;
	private String godown_section_name;
	private String godown_section_beans_name;
	private String remark;
	private String company_name;
	private String company_branch_name;
	private String financial_year;
	private String Active;
	private String Deleted;
	private boolean is_active = Boolean.TRUE;
	private boolean is_delete = Boolean.FALSE;
	private String created_by;
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;
	private Integer company_id;
	private Integer company_branch_id;
	private Integer customer_material_issue_type_id;
	private Integer customer_id;
	private Integer qa_by_id;
	private String customer_material_id;
	private Integer customer_state_id;
	private Integer customer_material_unit_id;
	private Integer customer_material_packing_id;
	private Integer customer_material_hsn_code_id;
	private Integer customer_material_rejection_reason_id;
	private Integer godown_id;
	private Integer godown_section_id;
	private Integer godown_section_beans_id;
	private Integer department_id;
	private Integer expected_branch_id;
	private Integer field_id;
	private String field_name;


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
