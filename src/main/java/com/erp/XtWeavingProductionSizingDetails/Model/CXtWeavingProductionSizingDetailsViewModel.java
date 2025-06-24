package com.erp.XtWeavingProductionSizingDetails.Model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.*;
import org.hibernate.annotations.Subselect;
import org.springframework.data.annotation.Immutable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Immutable
@Table(name = "xtv_weaving_production_sizing_details")
public class CXtWeavingProductionSizingDetailsViewModel {
	
	
	@Id
	private int weaving_production_sizing_details_id;
	private String weaving_production_set_no;
	private String sizing_production_order_no;
	private String sizing_production_code;
	private String sizing_production_date;
	private String yarn_count;
	private String customer_order_no;
	private String sizing_beam_name;
	private String machine_name;
	private String plant_name;
	private Integer prod_month;
	private Integer prod_year;
	private double size_waste;
	private double unsize_waste;
	private double creel_waste;
	private String godown_name;

	private Integer speed;

	private double calculative_net_weight;

	private double rf;

	private double visc;

	private double creel_a_tension;

	private double creel_b_tension;

	private double sq_pres_max;

	private double sq_pres_min;

	private double saw_box_a_temp;

	private double saw_box_b_temp;

	private double strech;

	private double moisture;

	private double after_waxing;

	private double leasing_tension;

	private double t_1;

	private double t_2;

	private double t_3;

	private double t_4;

	private double winding_tension;

	private double beam_pressing_tension;

	private double lappers;

	private double comb_breaks;
	private String status_remark;
	private String sizing_production_status_desc;
	private String sizing_production_status;
	private double actual_count;
	private String production_operator_name;
	private String production_supervisor_name;
	private String shift;
	private String sizing_beam_no;
//	private String beam_no;
	private double warping_length;
	private double sizing_length;
	private double net_weight;
	private double gross_weight;
	private double tare_weight;
	private double total_ends;
	private double unsize_beam_weight;
	private double size_pickup;
	private double sizing_rs;
	private double rate;
	private double amount;
	private String production_section_name;
	private String production_sub_section_name;
//	private String order_type_desc;
//	private String order_type;
//	private String sales_order_no;
//	private String customer_name;
	private String set_no;
//	private String so_item_name;
	private String sizing_production_master_status_desc;
	private String sizing_production_master_status;
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
	private String company_name;
	private String company_branch_name;
	private String company_address;
	private String company_pincode;
	private String company_cell_no;
	private String company_EmailId;
	private String company_website;
	private String company_gst_no;
	private String company_pan_no;
	private String financial_year;
	private Integer company_id;
	private Integer company_branch_id;
	private Integer weaving_production_sizing_master_id;
	private Integer plant_id;
	private Integer section_id;
	private Integer sub_section_id;
	private Integer godown_id;
	private String production_operator_id;
//	private String product_material_id;
	private Integer production_supervisor_id;
	private Integer machine_id;
	private Integer no_of_creels;
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
