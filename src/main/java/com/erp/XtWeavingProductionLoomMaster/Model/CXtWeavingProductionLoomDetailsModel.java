package com.erp.XtWeavingProductionLoomMaster.Model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import io.swagger.models.auth.In;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "xt_weaving_production_loom_details")
public class CXtWeavingProductionLoomDetailsModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "weaving_production_loom_details_id")
	private Integer weaving_production_loom_details_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String financial_year;
	private Integer weaving_production_loom_master_id;
	private String loom_production_code;
	private String code;
	private String loom_production_date;
	private String loom_production_shift;
	private Integer prod_month;
	private Integer prod_year;
	private String set_no;
	private String sizing_beam_no;
	private String sort_no;
	private double epi;
	private double ppi;
	private double width;
	private double target_rpm;
	private double target_eff;
	private String weave;
	private double act_rmp;
	private double act_eff;
	private double run_in_min;
	private double stop_in_min;
	private double prodcut_1000pick;
	private double product_in_meter;
	private double air_flow1;
	private double air_flow2;
	private double air_flow_cfm;
	private double warp_times;
	private double warp_minutes;
	private double warp_per_hour;
	private double warp_per_day;
	private double warp_per_cmpx;
	private double weft_times;
	private double weft_minutes;
	private double weft_per_hour;
	private double weft_per_day;
	private double weft_per_cmpx;
	private double leno_times;
	private double leno_minutes;
	private double leno_per_day;
	private double leno_per_hour;
	private double leno_per_cmpx;
	private double unselect_times;
	private double unselect_minutes;
	private double unselect2_times;
	private double unselect_per_hour;
	private double unselect_per_day;
	private double unselect_per_cmpx;

	private double false_times;
	private double false_minutes;
	private double false_per_hour;
	private double false_per_day;
	private double false_per_cmpx;

	private double total_times;
	private double total_minutes;
	private double total2_times;
	private double total_per_hour;
	private double total_per_day;
	private double total_per_cmpx;
	private String remarks;
	private Integer production_operator_id;
	private Integer production_supervisor_id;
	private Integer plant_id;
	private Integer machine_id;
	private Integer section_id;
	private Integer sub_section_id;
	private Integer godown_id;
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
	private String style;
	
	
	
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
	

}