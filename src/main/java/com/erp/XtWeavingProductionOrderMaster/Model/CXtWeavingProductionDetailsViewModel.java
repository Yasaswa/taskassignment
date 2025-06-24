package com.erp.XtWeavingProductionOrderMaster.Model;

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
@Immutable
@Entity
@Subselect(value = "SELECT * FROM xtv_weaving_production_details")
public class CXtWeavingProductionDetailsViewModel {
	

	@Id
	private Integer weaving_production_details_id;
	private String sales_order_no;
	private String set_no;
	private String product_material_name;
	private String product_unit_name;
	private String production_section_name;
	private double schedule_quantity;
	private String schedule_date;
	private double prodcut_1000pick;
	private double loom_product_in_meter;
	private double inspection_product_in_meter;
	private double inspection_mtr;
	private double product_pick;
	private double inspection_weight;
	private double sizing_length;
	private double sizing_net_weight;
	private double size_waste;
	private double unsize_waste;
	private double creel_waste;
	private double warping_length;
	private double warping_weight;
	private String company_name;
	private String company_branch_name;
	private String financial_year;
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
	private int company_id;
	private Integer company_branch_id;
	private String product_material_id;
	private Integer production_section_id;
	private Integer product_unit_id;
	
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
