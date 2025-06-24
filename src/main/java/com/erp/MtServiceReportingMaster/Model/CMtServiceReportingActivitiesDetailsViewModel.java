package com.erp.MtServiceReportingMaster.Model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Subselect;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Subselect(value = "mtv_service_reporting_activities_details")
public class CMtServiceReportingActivitiesDetailsViewModel {

	@Id
	private int service_reporting_activities_details_transaction_id;
	private String service_reporting_code;
	private String service_reporting_date;
	private String customer_name;
	private String product_material_name;
	private String product_material_activity_name;
	private double product_material_activity_std_hour;
	private String product_material_activity_description;
	private String from_range;
	private String to_range;
	private String expected_service_schedule_start_date;
	private String expected_service_schedule_end_date;
	private double actual_hour;
	private String extra_activities;
	private boolean is_overdue;
	private String approved_date;
	private String customer_approved_date;
	private Integer assigned_service_engineer_name;
	private Integer service_engineer_reporting_to_name;
	private String approved_by_name;
	private String reporting_status_desc;
	private String operator_status_desc;
	private String customer_status_desc;
	private String department_head_status_desc;
	private String customer_status;
	private String operator_status;
	private String department_head_status;
	private String reporting_status;
	private String approval_remark;
	private String customer_remark;
	private String company_name;
	private String company_branch_name;
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
	private String financial_year;
	private Integer company_id;
	private Integer company_branch_id;
	private Integer activity_id;
	private Integer approved_by_id;
	private String product_material_id;
	private Integer service_reporting_master_transaction_id;
	private Integer customer_id;
	private Integer service_engineer_reporting_to_id;
	private Integer service_planning_activities_transaction_id;
	private Integer service_planning_details_transaction_id;
	
	public boolean isIs_overdue() {
		return is_overdue;
	}
	public void setIs_overdue(boolean is_overdue) {
		this.is_overdue = is_overdue;
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

	

}