package com.erp.XtWeavingProductionLoomMaster.Model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "xt_weaving_production_loom_wastage")
public class CXtWeavingProductionLoomWastageModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "weaving_production_loom_wastage_id")
	private Integer weaving_production_loom_wastage_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String financial_year;
	private Integer weaving_production_loom_master_id;
	private String loom_production_code;
	private Date loom_production_date;
	private Integer prod_month;
	private Integer prod_year;
	private Integer plant_id;
	private Integer section_id;
	private Integer sub_section_id;
	private String shift;
	private Integer machine_id;
	private Integer production_wastage_types_id;
	private Integer godown_id;
	private String production_wastage_types_type;
	private double wastage_quantity;
	private boolean is_active = Boolean.TRUE;
	private boolean is_delete =  Boolean.FALSE;
	private String created_by;
	@CreationTimestamp
	@Column(name = "created_on", updatable = false)
	private Date created_on;
	private String modified_by;
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
