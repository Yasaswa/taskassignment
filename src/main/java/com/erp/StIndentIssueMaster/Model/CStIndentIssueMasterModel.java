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
@Table(name = "st_indent_material_issue_master")
public class CStIndentIssueMasterModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "issue_master_transaction_id")
	private int issue_master_transaction_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String financial_year;
	private String issue_no;
	private String issue_date;
	private Integer issue_version;
	private String issue_source;
	private Integer indent_issue_type_id;
	private Integer product_category1_id;
	private Integer product_category2_id;
	private String indent_issue_type;
	private String indent_no;
	private String indent_date;
	private Integer indent_version;
	private String set_no;
	private Integer customer_id;
	private String customer_order_no;
	private String customer_order_date;
	private Integer department_id;
	private String expected_schedule_date;
	private String issue_status;
	private Integer issued_by_id;
	private Integer accepted_by_id;
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
	private Integer requisition_by_id;
	private Date requisition_date;
	private Integer approved_by_id;
	private Date approved_date;
	private Date accepted_date;
	private Integer cost_center_id;
	private Integer sub_department_id;
	private String issue_group_type;
	private String sales_type;
	private Integer godown_issuer_id;
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

	public String getIndent_date() {
		return indent_date;
	}

	public void setIndent_date(String indent_date) {
		if (indent_date == null || indent_date.isEmpty()) {
			this.indent_date = null;
		} else {
			this.indent_date = indent_date;
		}
	}

	public String getExpected_schedule_date() {
		return expected_schedule_date;
	}

	public void setExpected_schedule_date(String expected_schedule_date) {
		if (expected_schedule_date == null || expected_schedule_date.isEmpty()) {
			this.expected_schedule_date = null;
		} else {
			this.expected_schedule_date = expected_schedule_date;
		}
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

	public Date getRequisition_date() {
		return requisition_date;
	}

	public void setRequisition_date(Date requisition_date) {
		this.requisition_date = requisition_date;
	}

	public Date getApproved_date() {
		return approved_date;
	}

	public void setApproved_date(Date approved_date) {
		this.approved_date = approved_date;
	}

	public Date getAccepted_date() {
		return accepted_date;
	}

	public void setAccepted_date(Date accepted_date) {
		this.accepted_date = accepted_date;
	}
}
