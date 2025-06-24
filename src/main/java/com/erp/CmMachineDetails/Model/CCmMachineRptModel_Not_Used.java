package com.erp.CmMachineDetails.Model;

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
@Subselect("select * from  cmv_machine_rpt")
public class CCmMachineRptModel_Not_Used {

	@Id
	private String machine_name;
	private String machine_short_name;
	private String machine_serial_no;
	private String department_name;
	private String sub_department_name;
	private String department_type;
	private String sub_department_type;
	private String sub_department_short_name;
	private String machine_make;
	private String machine_model;
	private String technical_specification;
	private String machine_depreciation_rate;
	private String machine_manufacture_date;
	private String machine_expiry_date;
	private String machine_erection_date;
	private String machine_production_date;
	private String machine_scrape_date;
	private String machine_maintenance_schedule_date;
	private String machine_production_capacity_per_hour;
	private String machine_production_capacity_per_day;
	private String machine_working_capacity;
	private String machine_stopage_capacity;
	private String machine_status;
	private String company_name;
	private String company_branch_name;
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
	private String machine_id;
	private String plant_id;
	private String department_id;
	private String sub_department_id;
	private String department_head_id;
	private Integer field_id;
	private String field_name;

}
