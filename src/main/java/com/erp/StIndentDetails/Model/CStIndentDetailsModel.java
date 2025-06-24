package com.erp.StIndentDetails.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "st_indent_details")
public class CStIndentDetailsModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "indent_details_id")
	private int indent_details_id;
	private Integer indent_master_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String financial_year;
	private String indent_no;
	private String indent_date;
	private Integer indent_version;
	private String customer_order_no;
	private Integer so_sr_no;
	private String sales_order_details_transaction_id;
	private String product_material_id;
	private Integer product_material_unit_id;
	private String product_fg_id;
	private double product_fg_material_quantity;
	private double product_fg_material_weight;
	private double product_material_quantity;
	private double product_material_weight;
	private int lead_time;
	private double product_material_stock_quantity;
	private double product_material_stock_weight;
	private double product_material_reserve_quantity;
	private double product_material_reserve_weight;
	private double product_material_approved_quantity;
	private double product_material_approved_weight;
	private double product_material_rejected_quantity;
	private double product_material_rejected_weight;
	private double product_material_issue_quantity;
	private double product_material_issue_weight;
	private double product_material_receipt_quantity;
	private double product_material_receipt_weight;
	private double product_material_return_quantity;
	private double product_material_return_weight;
	private double product_material_grn_accepted_quantity;
	private double product_material_grn_accepted_weight;
	private double product_material_preclosed_quantity;
	private double product_material_preclosed_weight;
	private double product_child_bom_quantity;
	private double product_child_bom_weight;
	private String approval_remark;
	private String indent_item_status;
	private Integer issued_by_id;
	private String issued_date;
	private String remark;
	private String preclosed_remark;
	private Integer cost_center_id;
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
	private String po_item_status;
	private String grn_item_status;
	private String issue_item_status;


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

	public String getIssue_date() {
		return issued_date;
	}
	public void setIssue_date(String issued_date) {
		if (issued_date == null || issued_date.isEmpty()) {
			this.issued_date = null;
		} else {
			this.issued_date = issued_date;
		}
	}
}
