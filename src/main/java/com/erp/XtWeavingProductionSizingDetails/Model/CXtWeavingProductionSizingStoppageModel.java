package com.erp.XtWeavingProductionSizingDetails.Model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "xt_weaving_production_sizing_stoppage")
public class CXtWeavingProductionSizingStoppageModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "weaving_production_sizing_stoppage_id")
	private int weaving_production_sizing_stoppage_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String financial_year;
	private Integer weaving_production_sizing_master_id;
	private String sizing_production_code;
	private String sizing_production_date;
	private Integer prod_month;
	private Integer prod_year;
	private Integer plant_id;
	private Integer section_id;
	private Integer sub_section_id;
	private String shift;
	private Integer machine_id;
	private Integer production_stoppage_reasons_id;
	private double std_stoppage_loss_per_hour;
	private double stoppage_time;
	private String loss_type;
	private double std_stoppage_loss_kg;
	private double stoppage_production_loss_kg;
	private double actual_production_loss_kg;
	private Integer production_supervisor_id;
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
