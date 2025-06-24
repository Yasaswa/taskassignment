package com.erp.XtWeavingProductionLoomMaster.Model;

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
@Immutable
@Data
@Entity
@Subselect(value = "SELECT * FROM xtv_weaving_production_loom_stoppage")
public class CXtWeavingProductionLoomStoppageViewModel {

	@Id
	private Integer weaving_production_loom_stoppage_id;
	private String loom_production_code;
	private String loom_production_date;
	private String shift;
	private Integer prod_month;
	private Integer prod_year;

	private String machine_name;
	private String machine_short_name;
	private String production_stoppage_reasons_name;
	private String production_stoppage_reasons_type;
	private double std_stoppage_minutes;
	private double std_stoppage_loss_per_hour;
	private String loss_type;
	private double stoppage_time;
	private double std_stoppage_loss_kg;
	private double stoppage_production_loss_kg;
	private double actual_production_loss_kg;
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
	private Integer weaving_production_loom_master_id;
	private Integer plant_id;
	private Integer section_id;
	private Integer sub_section_id;
	private Integer production_supervisor_id;
	private Integer machine_id;
	private Integer production_stoppage_reasons_id;
	
	
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