package com.erp.CmMachineDetails.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Immutable
@Subselect("select * from  cmv_machine")
public class CCmMachineViewModel {

	@Id
	private int machine_id;
	private String machine_name;
	private String machine_short_name;
	private String machine_mapping_code;
	private String machine_status;
	private Date machine_maintenance_schedule_date;
	private double machine_production_capacity_per_hour;
	private double machine_production_capacity_per_day;
	private double machine_working_capacity;
	private double machine_install_capacity;
	private double machine_stopage_capacity;
	private String machine_serial_no;
	private String machine_make;
	private String machine_model;
	private String technical_specification;
	private double machine_depreciation_rate;
	private Date machine_manufacture_date;
	private Date machine_expiry_date;
	private Date machine_erection_date;
	private Date machine_production_date;
	private Date machine_scrape_date;
	private String department_head;
	private String department_name;
	private String sub_department_name;
	private String section_name;
	private String sub_section_name;
	private String company_name;
	private String company_branch_name;
	private String plant_name;
	private String Active;
	private String Deleted;
	private boolean is_active = Boolean.TRUE;
	private boolean is_delete = Boolean.FALSE;
	private String created_by;
	@CreationTimestamp
	@Column(name = "created_on", updatable = false)
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;
	private Integer company_id;
	private Integer company_branch_id;
	private Integer plant_id;
	private Integer department_id;
	private Integer sub_department_id;
	private Integer department_head_id;
	private Integer section_id;
	private Integer sub_section_id;
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
