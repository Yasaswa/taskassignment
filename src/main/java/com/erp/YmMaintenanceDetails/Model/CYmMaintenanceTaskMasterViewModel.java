package com.erp.YmMaintenanceDetails.Model;

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
@Subselect("select * from  ymv_maintenance_task_master")
public class CYmMaintenanceTaskMasterViewModel {

	@Id
	private int maintenance_task_master_id;
	private String company_legal_name;
	private String company_branch_name;
	private String department_name;
	//	private String sub_department_name;
//	private String maintenance_task_type_desc;
	private String maintenance_task_type;
	private String maintenance_task_name;
	private String maintenance_task_Description;
	private double std_task_man_hour;
	private Integer std_task_frequency;
	private Integer std_task_tollerance;
	// added by mohit use for db 1.1
	private String parent_department;
	private String Active;
	private String Deleted;
	//remove maintenance_task_type_desc, sub_department_name 
	// added by mohit use for db 1.1
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
