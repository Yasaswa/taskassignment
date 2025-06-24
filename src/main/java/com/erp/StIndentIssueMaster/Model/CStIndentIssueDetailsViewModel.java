package com.erp.StIndentIssueMaster.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Subselect;
import org.springframework.data.annotation.Immutable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Immutable
@Table(name = "stv_indent_material_issue_details")
public class CStIndentIssueDetailsViewModel {

	@Id
	private int issue_details_transaction_id;
	private String indent_no;
	private String indent_date;
	private Integer indent_version;
	private String issue_no;
	private String issue_date;
	private Integer issue_version;
	private String issue_item_status_desc;
	private String department_name;
	private String indented_by_name;
	private String customer_name;
	private String expected_schedule_date;
	private String customer_order_no;
	private String issue_remark;
	private Integer product_category1_id;
	private Integer product_category2_id;
	private String product_material_id;
	private String product_material_name;
	private String product_type_group;
	private double product_material_indent_quantity;
	private double product_material_indent_weight;
	private double product_material_issue_quantity;
	private double product_material_issue_weight;
	private Double product_material_issue_boxes;
	private double product_material_receipt_quantity;
	private double product_material_receipt_weight;
	private String product_rm_name;
	private String product_rm_drawing_no;
	private String product_rm_tech_spect;
	private double product_material_issue_return_quantity;
	private String product_type_name;
//	private String product_make_name;
//	private String product_material_type_name;
	private double product_material_stock_quantity;
	private double product_material_stock_weight;
	private double closing_balance_quantity;
	private double closing_balance_weight;
//	private double prev_issue_quantity;
//	private double prev_issue_weight;
	private double product_material_approved_quantity;
	private double product_material_approved_weight;
	private double product_material_rejected_quantity;
	private double product_material_rejected_weight;
	private double product_material_issue_return_weight;
	private double product_std_weight;
	private String issue_item_status;
	private Integer issued_by_id;
	private String company_name;
	private String company_branch_name;
	private String financial_year;
	private String godown_name;
	private String godown_section_name;
	private String godown_section_beans_name;
	private String Active;
	private boolean is_active= Boolean.TRUE;
	private String field_name;
	private Integer field_id;
	private String Deleted;
	private boolean is_delete = Boolean.FALSE;
	private String created_by;
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;
	private Integer product_material_unit_id;
	private Integer customer_id;
	private Integer department_id;
	private Integer sub_department_id;
	private Integer indented_by_id;
	private Integer company_id;
	private Integer company_branch_id;
	private Integer product_type_id;
	private Integer issue_master_transaction_id;
	private Integer product_material_type_id;
	private Integer product_material_grade_id;
	private Integer product_material_packing_id;
	private Integer indent_details_id;
	private String godown_id;
	private String godown_section_id;
	private String godown_section_beans_id;
	private String issue_batch_no;
	private double material_batch_rate;
	private double material_issue_amount;
	private String sub_department_name;
	private int product_lead_time;
	private Integer cost_center_id;
	private Integer profit_center_id;
	private String routing_code;
	private double closing_no_of_boxes;
	private String creel_no;
	private String set_no;
	private String issue_requisition_type;
	private Double cone_per_wt;
	private String product_material_code;



	public boolean isIs_delete() {
		return is_delete;
	}

	public void setIs_delete(boolean is_delete) {
		this.is_delete = is_delete;
	}

	public boolean isIs_active() {
		return is_active;
	}

	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
	}


}
