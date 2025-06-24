package com.erp.XtWeavingProductionLoomMaster.Model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.*;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;


@AllArgsConstructor
@NoArgsConstructor
@Immutable
@Getter
@Setter
@Entity
@Table(name = "xtv_weaving_production_loom_details")
public class CXtWeavingProductionLoomDetailsViewModel {

	@Id
	private Integer weaving_production_loom_details_id;
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
	private String machine_name;
	private String plant_name;
	private String production_operator_name;
	private String production_supervisor_name;
	private String godown_name;
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
	private String production_section_name;
	private String production_sub_section_name;
	private String loom_production_master_status;
//	private String loom_production_master_status_desc;
	private String remarks;
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
	private Integer godown_id;
	private Integer sub_section_id;
	private Integer production_operator_id;
	private Integer production_supervisor_id;
	private Integer machine_id;
	private Integer field_id;
	private String field_name;
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
	
}