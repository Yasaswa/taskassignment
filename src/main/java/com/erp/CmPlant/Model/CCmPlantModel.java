package com.erp.CmPlant.Model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cm_plant")
public class CCmPlantModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "plant_id")
	private int plant_id;
	private Integer company_branch_id;
	private Integer company_id;
	private String plant_name;

	private String plant_short_name;
	private double plant_area;
	private double plant_production_capacity;
	private String plant_start_date;
	private Integer plant_head_id;
	private String plant_status;
	private boolean is_active = Boolean.TRUE;
	private boolean is_delete = Boolean.FALSE;
	@Column(name = "created_by", updatable = false)
	private String created_by;
	@CreationTimestamp
	@Column(name = "created_on", updatable = false)
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;

	public CCmPlantModel(int company_id, Integer company_branch_id, Integer plant_id, String plant_name, String plant_short_name,
	                     double plant_area, double plant_production_capacity, String plant_start_date, Integer plant_head_id,
	                     String plant_status, boolean is_active, boolean is_delete, String created_by, Date created_on,
	                     String modified_by, Date modified_on, String deleted_by, Date deleted_on) {
		super();
		this.company_id = company_id;
		this.company_branch_id = company_branch_id;
		this.plant_id = plant_id;
		this.plant_name = plant_name;
		this.plant_area = plant_area;
		this.plant_short_name = plant_short_name;
		this.plant_production_capacity = plant_production_capacity;
		this.plant_start_date = plant_start_date;
		this.plant_head_id = plant_head_id;
		this.plant_status = plant_status;
		this.is_active = is_active;
		this.is_delete = is_delete;
		this.created_by = created_by;
		this.created_on = created_on;
		this.modified_by = modified_by;
		this.modified_on = modified_on;
		this.deleted_by = deleted_by;
		this.deleted_on = deleted_on;
	}

	public CCmPlantModel() {
		super();

	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

	public Integer getCompany_branch_id() {
		return company_branch_id;
	}

	public void setCompany_branch_id(Integer company_branch_id) {
		this.company_branch_id = company_branch_id;
	}

	public Integer getPlant_id() {
		return plant_id;
	}

	public void setPlant_id(Integer plant_id) {
		this.plant_id = plant_id;
	}

	public String getPlant_name() {
		return plant_name;
	}

	public void setPlant_name(String plant_name) {
		this.plant_name = plant_name;
	}


	public String getPlant_short_name(){return plant_short_name;}

	public void setPlant_short_name(String plant_short_name){this.plant_short_name = plant_short_name;}

	public double getPlant_area() {
		return plant_area;
	}

	public void setPlant_area(double plant_area) {
		this.plant_area = plant_area;
	}

	public double getPlant_production_capacity() {
		return plant_production_capacity;
	}

	public void setPlant_production_capacity(double plant_production_capacity) {
		this.plant_production_capacity = plant_production_capacity;
	}

	public String getPlant_start_date() {
		return plant_start_date;
	}

	public void setPlant_start_date(String plant_start_date) {
		this.plant_start_date = plant_start_date;
	}

	public Integer getPlant_head_id() {
		return plant_head_id;
	}

	public void setPlant_head_id(Integer plant_head_id) {
		this.plant_head_id = plant_head_id;
	}

	public String getPlant_status() {
		return plant_status;
	}

	public void setPlant_status(String plant_status) {
		this.plant_status = plant_status;
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

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public Date getCreated_on() {
		return created_on;
	}

	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
	}

	public String getModified_by() {
		return modified_by;
	}

	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}

	public Date getModified_on() {
		return modified_on;
	}

	public void setModified_on(Date modified_on) {
		this.modified_on = modified_on;
	}

	public String getDeleted_by() {
		return deleted_by;
	}

	public void setDeleted_by(String deleted_by) {
		this.deleted_by = deleted_by;
	}

	public Date getDeleted_on() {
		return deleted_on;
	}

	public void setDeleted_on(Date deleted_on) {
		this.deleted_on = deleted_on;
	}
}
