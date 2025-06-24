package com.erp.XtWeavingProductionLoomMaster.Model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.stereotype.Component;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "xt_weaving_production_loom_master")
public class CXtWeavingProductionLoomMasterModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "weaving_production_loom_master_id")
	private Integer weaving_production_loom_master_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String financial_year;
	private String loom_production_code;
	private String loom_production_date;
	private String data_import_date;
	private String loom_production_shift;
	private String data_import_time;
	private String data_import_file;
	private Integer prod_month;
	private Integer prod_year;
	private double total_prodcut_1000pick;
	private double total_product_in_meter;
	private double total_run_in_min;
	private double total_stop_in_min;
	private String loom_production_remark;
	private String loom_production_master_status;
	private Integer plant_id;
	private Integer section_id;
	private Integer sub_section_id;
	private Integer production_supervisor_id;
	private boolean is_active =  Boolean.TRUE;
	private boolean is_delete =  Boolean.FALSE;
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
	public String getLoom_production_date() {
		return loom_production_date;
	}
	public void setLoom_production_date(String loom_production_date) {
		if(loom_production_date == null || loom_production_date.isEmpty()) {
			this.loom_production_date = null;
		}else {
			this.loom_production_date = loom_production_date;
		}
	}
	public String getData_import_date() {
		return data_import_date;
	}
	public void setData_import_date(String data_import_date) {
		if(data_import_date == null || data_import_date.isEmpty()) {
			this.data_import_date = null;
		}else {
			this.data_import_date = data_import_date;
		}
	}
	
	

}
