package com.erp.YmMaintenanceTaskActivity.Model;

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
@Subselect("select * from  ymv_maintenance_task_activity")
public class CYmMaintenanceTaskActivityViewModel {
	@Id
	private int maintenance_task_activity_id;
	private String company_name;
	private String company_branch_name;
	private String department_name;
	private String sub_department_name;
	private String task_activity_functionality;
	private String task_activity_results;
	private double std_task_activity_loss_per_hour;
	private double std_task_activity_minutes;
	//Added By Mohit
	private String Active;
	private String Deleted;
	//when we use 1.1 then remove sub_department_name 
	//	private String parent_department;	
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
	private Integer production_department_id;
	private Integer production_sub_department_id;
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
