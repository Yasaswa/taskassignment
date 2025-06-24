package com.erp.StRawMaterialReturnMaster.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "st_indent_material_issue_return_master")
public class CStRawMaterialReturnMasterModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "issue_return_master_transaction_id")
	private Integer issue_return_master_transaction_id;

	@Column(name = "company_id")
	private Integer company_id;

	@Column(name = "company_branch_id")
	private Integer company_branch_id;

	@Column(name = "financial_year")
	private String financial_year;

	@Column(name = "issue_return_no")
	private String issue_return_no;

	@Column(name = "issue_return_date")
	private String issue_return_date;

	@Column(name = "indent_issue_return_type_id")
	private Integer indent_issue_return_type_id;

	@Column(name = "indent_issue_return_type")
	private String indent_issue_return_type;

	@Column(name = "department_id")
	private Integer department_id;

	@Column(name = "sub_department_id")
	private Integer sub_department_id;

	@Column(name = "issue_no")
	private String issue_no;

	@Column(name = "return_by_id")
	private Integer return_by_id;

	@Column(name = "issue_return_status")
	private String issue_return_status;

	@Column(name = "received_by_id")
	private Integer received_by_id;

	@Column(name = "received_date")
	private String received_date;

	@Column(name = "remark")
	private String remark;

	@Column(name = "material_type")
	private String material_type;

		@Column(name = "product_category1_id")
	private Integer product_category1_id;

	@Column(name = "product_category2_id")
	private Integer product_category2_id;

	@Column(name = "is_active", columnDefinition = "boolean default true")
	private boolean is_active = true;

	@Column(name = "is_delete", columnDefinition = "boolean default false")
	private boolean is_delete = false;

	@Column(name = "created_by", updatable = false)
	private String created_by;

	@CreationTimestamp
	@Column(name = "created_on", updatable = false)
	private Date created_on;

	@Column(name = "modified_by")
	private String modified_by;

	@UpdateTimestamp
	@Column(name = "modified_on")
	private Date modified_on;

	@Column(name = "deleted_by")
	private String deleted_by;

	@Column(name = "deleted_on")
	private Date deleted_on;

	@Column(name = "sales_type")
	private String sales_type;

	@Transient
	private String set_no;


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


	public String getIssue_return_date() {
		return issue_return_date;
	}

	public void setIssue_return_date(String issue_return_date) {
		if (issue_return_date == null || issue_return_date.isEmpty()) {
			this.issue_return_date = null;
		} else {
			this.issue_return_date = issue_return_date;
		}
	}

	public String getReceived_date() {
		return received_date;
	}

	public void setReceived_date(String received_date) {
		if (received_date == null || received_date.isEmpty()) {
			this.received_date = null;
		} else {
			this.received_date = received_date;
		}
	}
}
