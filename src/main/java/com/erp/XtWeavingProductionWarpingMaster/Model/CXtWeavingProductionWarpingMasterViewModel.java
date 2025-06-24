package com.erp.XtWeavingProductionWarpingMaster.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Immutable
@Entity
@Table(name = "xtv_weaving_production_warping_master")
public class CXtWeavingProductionWarpingMasterViewModel {

	@Id
	private int weaving_production_warping_master_id;
	private String warping_production_date;
	private String warping_production_code;
	private String plant_name;
	private Integer prod_month;
	private Integer prod_year;
	private String production_supervisor_name;
	private String production_section_name;
	private String production_sub_section_name;
	private String warping_production_master_status_desc;
	private String warping_production_master_status;
//	private double total_actual_count;
//	private double total_yarn_count;
//	private double total_no_of_creels;
//	private double total_creel_ends;
//	private double total_net_weight;
//	private double total_weight_issue_to_warping;
//	private double total_pkg_used;
//	private double total_weight_per_pkg;
//	private double total_t_ends;
//	private double total_length;
//	private double total_exp_bottom;
//	private double total_breaks_per_million;
//	private double total_act_bottom;
//	private double total_bottom_percent;
//	private String Active;
//	private String Deleted;
	private boolean is_active;
	private boolean is_delete;
	private String created_by;
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;
	private String company_name;
	private String company_branch_name;
	private String financial_year;
	private Integer company_id;
	private Integer company_branch_id;
	private Integer supplier_id;
	private String supplier_name;
//	private Integer weaving_production_warping_stoppage_id;
	private Integer plant_id;
	private Integer section_id;
	private Integer sub_section_id;
	private Integer production_supervisor_id;
	private String warping_order_no;
	private String product_material_id;
	private String product_material_style;
	private double schedule_quantity;
	private String sort_no;
	private double no_of_creels;
	private double set_length;
	private String product_material_name;
	private double no_of_beams;
	private String set_no;
	private float calculative_bottom_kg;
	private float calculative_bottom_percent;
	private float actual_bottom_kg;
	private float actual_bottom_percent;
	private float difference_bottom_kg;
	private float difference_bottom_percent;
	private float warping_issue_kg;
	private float warping_issue_quantity;
	private float warping_issue_boxes;
	private String batch_no;
	private Integer print_status;

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
