package com.erp.CmPlant.Model;

import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
@Immutable
@Subselect("select * from  cmv_plant")
public class CCmPlantViewModel {

	@Id
	private int plant_id;
	private String plant_name;
	private String plant_short_name;
	private String plant_status;
	private double plant_area;
	private String plant_start_date;
	private double plant_production_capacity;
	private String plant_head_name;
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
	private Integer company_id;
	private Integer company_branch_id;
	private Integer plant_head_id;
	private Integer field_id;
	private String field_name;

	public int getPlant_id() {
		return plant_id;
	}

	public void setPlant_id(int plant_id) {
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

	public String getPlant_status() {
		return plant_status;
	}

	public void setPlant_status(String plant_status) {
		this.plant_status = plant_status;
	}

	public double getPlant_area() {
		return plant_area;
	}

	public void setPlant_area(double plant_area) {
		this.plant_area = plant_area;
	}

	public String getPlant_start_date() {
		return plant_start_date;
	}

	public void setPlant_start_date(String plant_start_date) {
		this.plant_start_date = plant_start_date;
	}

	public double getPlant_production_capacity() {
		return plant_production_capacity;
	}

	public void setPlant_production_capacity(double plant_production_capacity) {
		this.plant_production_capacity = plant_production_capacity;
	}

	public String getPlant_head_name() {
		return plant_head_name;
	}

	public void setPlant_head_name(String plant_head_name) {
		this.plant_head_name = plant_head_name;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getCompany_branch_name() {
		return company_branch_name;
	}

	public void setCompany_branch_name(String company_branch_name) {
		this.company_branch_name = company_branch_name;
	}

	public String getActive() {
		return Active;
	}

	public void setActive(String active) {
		Active = active;
	}

	public String getDeleted() {
		return Deleted;
	}

	public void setDeleted(String deleted) {
		Deleted = deleted;
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

	public Integer getCompany_id() {
		return company_id;
	}

	public void setCompany_id(Integer company_id) {
		this.company_id = company_id;
	}

	public Integer getCompany_branch_id() {
		return company_branch_id;
	}

	public void setCompany_branch_id(Integer company_branch_id) {
		this.company_branch_id = company_branch_id;
	}

	public Integer getPlant_head_id() {
		return plant_head_id;
	}

	public void setPlant_head_id(Integer plant_head_id) {
		this.plant_head_id = plant_head_id;
	}

	public Integer getField_id() {
		return field_id;
	}

	public void setField_id(Integer field_id) {
		this.field_id = field_id;
	}

	public String getField_name() {
		return field_name;
	}

	public void setField_name(String field_name) {
		this.field_name = field_name;
	}

	public CCmPlantViewModel(int plant_id, String plant_name, String plant_status, double plant_area,
	                         String plant_start_date, double plant_production_capacity, String plant_head_name, String company_name,
	                         String company_branch_name, String active, String deleted, boolean is_active, boolean is_delete,
	                         String created_by, Date created_on, String modified_by, Date modified_on, String deleted_by,
	                         Date deleted_on, Integer company_id, Integer company_branch_id, Integer plant_head_id, Integer field_id,
	                         String field_name) {
		super();
		this.plant_id = plant_id;
		this.plant_name = plant_name;
		this.plant_status = plant_status;
		this.plant_area = plant_area;
		this.plant_start_date = plant_start_date;
		this.plant_production_capacity = plant_production_capacity;
		this.plant_head_name = plant_head_name;
		this.company_name = company_name;
		this.company_branch_name = company_branch_name;
		Active = active;
		Deleted = deleted;
		this.is_active = is_active;
		this.is_delete = is_delete;
		this.created_by = created_by;
		this.created_on = created_on;
		this.modified_by = modified_by;
		this.modified_on = modified_on;
		this.deleted_by = deleted_by;
		this.deleted_on = deleted_on;
		this.company_id = company_id;
		this.company_branch_id = company_branch_id;
		this.plant_head_id = plant_head_id;
		this.field_id = field_id;
		this.field_name = field_name;
	}

	public CCmPlantViewModel() {
		super();
	}
}
