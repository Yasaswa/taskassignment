package com.erp.PtCustomerMaterialIssueMaster.Model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "pt_customer_material_issue_master")
public class CPtCustomerMaterialIssueMasterModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_material_issue_master_transaction_id")
	private int customer_material_issue_master_transaction_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String financial_year;
	private String customer_material_issue_no;
	private Date customer_material_issue_date;
	private Integer customer_material_issue_version;
	private Integer customer_material_issue_type_id;
	private String customer_material_issue_type;
	private Integer customer_id;
	private Integer customer_state_id;
	private Integer expected_branch_id;
	private String customer_order_no;
	private Date customer_order_date;
	private String customer_challan_no;
	private Date customer_challan_Date;
	private Integer qa_by_id;
	private Date qa_date;
	private String customer_material_issue_status;
	private double grand_total;
	private String lr_no;
	private Date lr_date;
	private String vehicle_no;
	private String ewb_no;
	private Date ewb_valid_till;
	private String remark;
	private boolean is_active;
	private boolean is_delete;
	private String created_by;
	@CreationTimestamp
	@Column(name = "created_on", updatable = false)
	private Date created_on;
	private String modified_by;
	@UpdateTimestamp
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;
	
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
