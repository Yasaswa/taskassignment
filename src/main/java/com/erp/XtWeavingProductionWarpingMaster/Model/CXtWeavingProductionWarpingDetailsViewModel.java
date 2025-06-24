package com.erp.XtWeavingProductionWarpingMaster.Model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.*;
import org.hibernate.annotations.Subselect;
import org.springframework.data.annotation.Immutable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Immutable
@Table(name = "xtv_weaving_production_warping_details")
public class CXtWeavingProductionWarpingDetailsViewModel {


	@Id
	private int weaving_production_warping_details_id;
	private String warping_production_date;
	private String weaving_production_set_no;
	private String warping_order_no;
	private String warping_production_code;
	private String machine_name;
	private String godown_name;
	private String product_material_name;
	private String warping_production_status_desc;
	private String warping_production_status;
	private String plant_name;
	private Integer prod_month;
	private Integer prod_year;
	private double actual_count;
	private String production_operator_name;
	private String production_supervisor_name;
	private String shift;
//	private double warping_total_legnth;
//	private double warping_total_act_bottom;
	private double creel_ends;
//	private double total_weight_issue_to_warping;
//	private double no_of_creels;
//	private double net_weight;
//	private double total_pkg_used;
	private double weight_per_pkg;
	private double t_ends;
	private double length;
//	private double exp_bottom;
	private double breaks_per_million;
//	private double act_bottom;
//	private double bottom_percent;
//	private String order_type_desc;
//	private String order_type;
//	private String sales_order_no;
//	private String customer_name;
	private String set_no;
	private String product_material_id;
	private String warping_production_master_status_desc;
	private String warping_production_master_status;
	private String production_section_name;
	private String production_sub_section_name;
	private String status_remark;
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
//	private String company_name;
//	private String company_branch_name;
	private String financial_year;
	private Integer company_id;
	private Integer company_branch_id;
	private Integer weaving_production_warping_master_id;
	private Integer plant_id;
	private Integer section_id;
	private Integer godown_id;
	private Integer sub_section_id;
	private String yarn_count;
	private String product_rm_id;
	private Integer production_operator_id;
	private Integer production_supervisor_id;
	private Integer machine_id;
    private double warping_creels;
    private double warping_set_length;
	private String beam_no;
	private String beam_name;
	private int speed;
	private float cut_cones;
	private float guccha;
	private float thin_places;
	private float week_places;
	private float week_splice;
	private float sluff_off;
	private float slub_yarn;
	private float total_breaks;

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
