package com.erp.StIndentIssueMaster.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Immutable;

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
@Table(name = "stv_indent_material_issue_summary")
public class CStIndentMaterialIssueMasterViewModel {

	@Id
	private int issue_master_transaction_id;
	private String indent_no;
	private String indent_date;
	private Integer indent_version;
	private String issue_no;
	private String issue_date;
	private Integer issue_version;
	private String set_no;
	private String issue_status_desc;
	private String indent_source_desc;
	private String indent_issue_type;
	private String department_name;
	private String requisition_by_name;
	private String approved_date;
	private String customer_name;
	private String customer_order_no;
	private String customer_order_date;
	private String customer_state_name;
	private String expected_schedule_date;
	private String Active;
	private String Deleted;
	private String company_name;
	private String company_branch_name;
	private boolean is_active;
	private boolean is_delete;
	private String created_by;
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;
	private String remark;
	private Integer indent_issue_type_id;
	private Integer product_category1_id;
	private Integer product_category2_id;
	private Integer customer_id;
	private String cust_branch_gst_no;
	private Integer department_id;
	private Integer issued_by_id;
	private Integer accepted_by_id;
	private String indent_status;
	private Integer company_id;
	private Integer company_branch_id;
	private String financial_year;
	private String issue_status;
	private String field_name;
	private Integer field_id;
	private String sub_department_name;
	private String approved_by_name;
	private String issued_by_name;
	private Integer sub_department_id;
	private Integer requisition_by_id;
	private Date requisition_date;
	private Integer approved_by_id;
	private String issue_group_type;
	private String sales_type;
	private Integer godown_issuer_id;

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
