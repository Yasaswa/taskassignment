package com.erp.PtCustomerMaterialIssueMaster.Model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Subselect;
import org.springframework.data.annotation.Immutable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Immutable
@Subselect("Select * From ptv_customer_material_issue_summary")
public class CPtCustomerMaterialIssueMasterViewModel {

	@Id
	private Integer customer_material_issue_master_transaction_id;
	private String customer_material_issue_no;
	private String customer_material_issue_date;
	private Integer customer_material_issue_version;
	private String customer_material_issue_status_desc;
	private String customer_name;
	private String customer_order_no;
	private String customer_order_date;
	private String customer_material_issue_type;
	private String customer_challan_no;
	private String customer_challan_Date;
	private String ewb_no;
	private String ewb_valid_till;
	private String qa_by_name;
	private String qa_date;
	private String customer_material_issue_status;
	private String grand_total;
	private String lr_no;
	private String lr_date;
	private String vehicle_no;
	private String customer_state_name;
	private String customer_city_name;
	private String remark;
	private String company_name;
	private String company_branch_name;
	private String financial_year;
	private String Active;
	private String Deleted;
	private boolean is_active;
	private boolean is_delete;
	private String created_by;
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;
	private Integer company_id;
	private Integer company_branch_id;
	private Integer customer_id;
	private Integer customer_state_id;
	private Integer qa_by_id;
	private Integer customer_material_issue_type_id;
	private Integer expected_branch_id;
	private Integer field_id;
	private String field_name;
	
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
