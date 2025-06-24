package com.erp.MtServicePlanningMaster.Model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Immutable
@Subselect("select * from mtv_service_planning_activities")
public class CMtServicePlanningActivitiesViewModel {

	
	@Id
	private int service_planning_activities_transaction_id;
	private String product_material_name;
	private String customer_order_no;
	private String product_material_activity_name;
	private String product_material_activity_description;
	private double product_material_activity_std_hour;
	private String from_range;
	private String to_range;
	private String remark;
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
	private Integer service_planning_master_transaction_id;
	private Integer sales_order_activity_transaction_id;
	private Integer service_planning_details_transaction_id;
	private Integer sales_order_schedules_transaction_id;
	private Integer company_id;
	private Integer company_branch_id;
	
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
