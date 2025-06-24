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
@Data
@Entity
@Immutable
@Subselect(value = "SELECT * FROM xtv_weaving_production_loom_master")
public class CXtWeavingProductionLoomMasterViewModel {

	@Id
	private Integer weaving_production_loom_master_id;
	private String loom_production_code;
	private String loom_production_date;
	private String data_import_date;
	private String loom_production_shift;
	private String data_import_time;
	private String data_import_file;
	private String plant_name;
	private Integer prod_month;
	private Integer prod_year;
	private double total_prodcut_1000pick;
	private double total_product_in_meter;
	private double total_run_in_min;
	private double total_stop_in_min;
	private String production_supervisor_name;
	private String production_section_name;
	private String production_sub_section_name;
	private String loom_production_master_status_desc;
	private String loom_production_master_status;
	private String loom_production_remark;
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
	private Integer plant_id;
	private Integer section_id;
	private Integer sub_section_id;
	private Integer production_supervisor_id;
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
