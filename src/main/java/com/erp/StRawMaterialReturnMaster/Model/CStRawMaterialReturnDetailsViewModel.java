package com.erp.StRawMaterialReturnMaster.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Immutable
@Table(name = "stv_indent_material_issue_return_details")
public class CStRawMaterialReturnDetailsViewModel {

	@Id
	private int issue_return_details_transaction_id;
	private Integer issue_return_master_transaction_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String financial_year;
	private String issue_return_no;
	private String product_material_id;
	private String product_material_code;
	private String issue_batch_no;
	private Double product_material_issue_return_quantity;
	private Double product_material_issue_return_weight;
	private Integer product_material_issue_return_boxes;
	private Double product_material_receipt_quantity;
	private Double product_material_receipt_weight;
	private Integer product_material_receipt_boxes;
	private String product_material_name;
	private Double product_material_indent_quantity;
	private Double product_material_indent_weight;
	private Double product_material_approved_quantity;
	private Double product_material_approved_weight;
	private Double product_material_issue_quantity;
	private Double product_material_issue_weight;
	private Double product_material_issue_boxes;
	private String issue_return_item_status;
	private String godown_id;
	private String godown_section_id;
	private String godown_section_beans_id;
	private String issue_return_remark;
	private Double material_batch_rate;
	private Integer product_category1_id;
	private Integer product_category2_id;
	private Integer product_material_unit_id;
	private Boolean is_active;
	private Boolean is_delete;
	private String created_by;
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;
	private Double cone_per_wt;
	private String goods_receipt_no;
	private Double closing_balance_weight;
	private Double closing_balance_quantity;
	private String creel_no;
	private Integer supplier_id;
	private String godown_name;
	private String godown_section_name;
	private String godown_section_beans_name;
	private String issue_return_date;
	private String material_type;

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

	public void set(String issue_return_date) {
		if (issue_return_date == null || issue_return_date.isEmpty()) {
			this.issue_return_date = null;
		} else {
			this.issue_return_date = issue_return_date;
		}
	}
}