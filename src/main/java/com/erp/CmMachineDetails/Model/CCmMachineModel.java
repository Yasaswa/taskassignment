package com.erp.CmMachineDetails.Model;

import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "cm_machine")
public class CCmMachineModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "machine_id")
	private int machine_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String machine_name;
	private String machine_short_name;
	private String machine_mapping_code;
	private String technical_specification;
	private String machine_serial_no;
	private String machine_make;
	private String machine_model;
	private String machine_manufacture_date;
	private String machine_expiry_date;
	private String machine_erection_date;
	private String machine_production_date;
	private String machine_scrape_date;
	private String machine_maintenance_schedule_date;
	private Integer plant_id;
	private Integer department_id;
	private Integer sub_department_id;
	private Integer section_id;
	private Integer sub_section_id;
	private double machine_production_capacity_per_hour;
	private double machine_production_capacity_per_day;
	private double machine_depreciation_rate;
	private double machine_install_capacity;
	private double machine_working_capacity;
	private double machine_stopage_capacity;
	private String machine_status;
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
