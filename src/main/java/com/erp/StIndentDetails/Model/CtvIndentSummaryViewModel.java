package com.erp.StIndentDetails.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Immutable
@Subselect("select * from  stv_indent_master_summary")
public class CtvIndentSummaryViewModel {

	@Id
	private String indent_no;
	private String indent_date;
	private Integer indent_version;
	private String indent_source_desc;
	private String department_name;
	private String indented_by_name;
	private String approved_by_name;
	private String indent_status_desc;
	private String customer_name;
	private String customer_state_name;
	private String customer_order_no;
	private String customer_order_date;
	private String expected_schedule_date;
	private String cost_center_name;
	private String Active;
	private String Deleted;
	private String remark;
	private String field_name;
	private Integer field_id;
	private boolean is_active;
	private boolean is_delete;
	private String created_by;
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;
	private String company_name;
	private String company_branch_name;
	private String financial_year;
	private Integer indent_master_id;
	private Integer indent_type_id;
	private Integer customer_id;
	private Integer department_id;
	private Integer indented_by_id;
	private String indent_status;
	private Integer company_id;
	private Integer company_branch_id;
	private String indent_transaction_type;
	private String indent_type;
	private String indent_source;
	private Integer sub_department_id;
	private Integer cost_center_id;
	private String sub_department_name;
	private String indent_priority;
	private String indent_priority_desc;


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
