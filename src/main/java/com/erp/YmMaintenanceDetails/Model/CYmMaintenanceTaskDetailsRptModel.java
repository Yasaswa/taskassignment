package com.erp.YmMaintenanceDetails.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Immutable
@Subselect("select * from  ymv_maintenance_task_details_rpt")
public class CYmMaintenanceTaskDetailsRptModel {

	@Id
	private String company_name;
	private String company_branch_name;
	private String department_name;
	// remove sub_department_name when we use db 1.1
	private String sub_department_name;
	private String maintenance_task_name;
	private String task_activity_functionality;
	private String task_activity_results;
	private String std_task_activity_loss_per_hour;
	private String std_task_activity_minutes;
	// added by mohit
//	private String parent_department;
//	private String maintenance_task_type;
//	private String maintenance_task_Description;
//	private String production_department_id;
//	private String production_sub_department_id;
	private String is_active;
	private String is_delete;
	private String created_by;
	private String created_on;
	private String modified_by;
	private String modified_on;
	private String deleted_by;
	private String deleted_on;
	private String company_id;
	private String company_branch_id;
	private String maintenance_task_master_id;
	private String maintenance_task_details_id;
	private String maintenance_task_activity_id;
	private String field_id;
	private String field_name;


}
