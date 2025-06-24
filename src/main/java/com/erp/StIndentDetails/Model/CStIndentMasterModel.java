package com.erp.StIndentDetails.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "st_indent_master")
public class CStIndentMasterModel {

	@Id
	private int indent_master_id;
	private int company_id;
	private int company_branch_id;
	private String financial_year;
	private Integer indent_type_id;
	private String Indent_type;
	private String indent_source;
	private String indent_no;
	private String indent_date;
	private Integer indent_version;
	private Integer customer_id;
	private String customer_order_no;
	private String customer_order_Date;
	private String expected_schedule_date;
	private Integer department_id;
	private Integer indented_by_id;
	private Integer accepted_by_id;
	private Integer approved_by_id;
	private Integer cost_center_id;
	private String approved_date;
	private String indent_status;
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
	private String indent_transaction_type;
	private Integer sub_department_id;
	private String po_status;
	private String grn_status;
	private String issue_status;
	private String indent_priority;
	private Integer category_1;
	private Integer category_2;


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
	public String getApproved_date() {
		return approved_date;
	}

	public void setApproved_date(String approved_date) {
		if (approved_date == null || approved_date.isEmpty()) {
			this.approved_date = null;
		} else {
			this.approved_date = approved_date;
		}
	}



}
