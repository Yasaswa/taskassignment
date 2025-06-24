package com.erp.MtServiceReportingMaster.Model;

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
@Table(name = "mt_service_reporting_activities_details")
public class CMtServiceReportingActivitiesDetailsModel {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "service_reporting_activities_details_transaction_id")
	private int service_reporting_activities_details_transaction_id;
	private Integer service_reporting_master_transaction_id;
	private String service_reporting_code;
	private String service_reporting_date;
	private Integer company_id;
	private Integer company_branch_id;
	private String financial_year;
	private Integer product_material_id;
	private Integer activity_id;
	private double actual_hour;
	private String extra_activities;
	private Integer approved_by_id;
	private String approved_date;
	private String approval_remark;
	private String customer_remark;
	private Date customer_approved_date;
	private Boolean is_overdue;
	private String operator_status;
	private String department_head_status;
	private String customer_status;
	private Integer service_planning_activities_transaction_id;
	private Integer service_planning_details_transaction_id;
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
