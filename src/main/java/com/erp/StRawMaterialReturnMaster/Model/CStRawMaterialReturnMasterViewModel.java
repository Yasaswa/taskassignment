package com.erp.StRawMaterialReturnMaster.Model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "stv_indent_material_issue_return_master")
public class CStRawMaterialReturnMasterViewModel {

	@Id
	@Column(name = "issue_return_master_transaction_id")
	private Long issue_return_master_transaction_id;

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

	@Column(name = "issue_date")
	private String issue_date;

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

	@Column(name = "Active")
	private String Active;

	@Column(name = "Deleted")
	private String Deleted;

	@Column(name = "is_active")
	private Boolean is_active;

	@Column(name = "is_delete")
	private Boolean is_delete;

	@Column(name = "created_by")
	private String created_by;


	private String department_name;
	private String sub_department_name;
	private String received_by_name;
	private String return_by_name;
	private String issue_return_status_desc;
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;
	private String set_no;
	private String company_branch_name;
	private String company_name;


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
