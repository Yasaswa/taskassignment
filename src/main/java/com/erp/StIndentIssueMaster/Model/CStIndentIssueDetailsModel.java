package com.erp.StIndentIssueMaster.Model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "st_indent_material_issue_details")
public class CStIndentIssueDetailsModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "issue_details_transaction_id")
	private int issue_details_transaction_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String financial_year;
	private String issue_no;
	private String issue_date;
	private Integer issue_version;
	private String indent_no;
	private String indent_date;
	private String issue_item_status;
	private Integer indent_version;
	private String goods_receipt_no;
	private Integer product_category1_id;
	private Integer product_category2_id;
	private String product_material_id;
	private Integer product_material_unit_id;
	private double product_material_indent_quantity;
	private double product_material_indent_weight;
	private double product_material_approved_quantity;
	private double product_material_approved_weight;
	private double product_material_rejected_quantity;
	private double product_material_rejected_weight;
	private double product_material_issue_quantity;
	private double product_material_issue_weight;
	private Integer product_material_issue_boxes;
	private double product_material_issue_return_quantity;
	private double product_material_issue_return_weight;
	private double product_material_receipt_quantity;
	private double product_material_receipt_weight;
	private double product_std_weight;
	private String issue_remark;
	private String customer_order_no;
	private String creel_no;
	private String set_no;
	private String issue_requisition_type;

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
	private Integer issue_master_transaction_id;
	private Integer indent_details_id;
	private Integer indented_by_id;
	private String godown_id;
	private String godown_section_id;
	private String godown_section_beans_id;
	private String issue_batch_no;
	private double material_batch_rate;
	private Integer cost_center_id;
	private Integer profit_center_id;
	private String routing_code;
	private Double cone_per_wt;
	@Transient
	private double prev_issue_quantity;
	@Transient
	private double prev_issue_weight;
	@Transient
	private double prev_order_quantity;
//	@Transient
//	private Double cone_per_wt;



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
		return issue_date;
	}

	public void setIssue_date(String issue_date) {
		if (issue_date == null || issue_date.isEmpty()) {
			this.issue_date = null;
		} else {
			this.issue_date = issue_date;
		}
	}

}
