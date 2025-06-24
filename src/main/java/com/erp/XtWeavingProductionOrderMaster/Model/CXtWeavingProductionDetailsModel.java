package com.erp.XtWeavingProductionOrderMaster.Model;

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
@Table(name = "xt_weaving_production_details")
public class CXtWeavingProductionDetailsModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "weaving_production_details_id")
	private Integer weaving_production_details_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String financial_year;
	private String product_material_id;
	private Integer production_section_id;
	private Integer product_unit_id;
	private String sales_order_no;
	private String set_no;
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
	
	public String getSchedule_date() {
		return schedule_date;
	}
	public void setSchedule_date(String schedule_date) {
		if (schedule_date == null || schedule_date.isEmpty()) {
			this.schedule_date = null;
		}else {
			this.schedule_date = schedule_date;
		}
		
	}
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
