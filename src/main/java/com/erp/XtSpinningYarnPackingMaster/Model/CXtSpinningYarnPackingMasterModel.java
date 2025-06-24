package com.erp.XtSpinningYarnPackingMaster.Model;

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
@Table(name = "xt_spinning_yarn_packing_master")
public class CXtSpinningYarnPackingMasterModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "yarn_packing_master_id")
	private int yarn_packing_master_id;
	private Integer company_id;
	private Integer company_branch_id;
	private Integer lot_id;
	private String financial_year;
	private String yarn_packing_date;
	private boolean lot_type;
	private String sub_lot_no;
	private Integer plant_id;
	private String yarn_packing_start_date;
	private String yarn_packing_end_date;
	private Integer spinn_plan_id;
	private Integer customer_id;
	private String customer_order_no;
	private String sales_type;
	private Integer country_id;
	private String production_type;
	private String packing_nature;
	private Integer remening_quantity;
	private double total_cones;
	private double total_packages;
	private double gross_weight;
	private double net_weight;
	private double dispatched_packages;
	private double dispatched_weight;
	private String packing_status;
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

	public String getYarn_packing_date() {
		return yarn_packing_date;
	}
	
	public boolean isLot_type() {
		return lot_type;
	}

	public void setLot_type(boolean lot_type) {
		this.lot_type = lot_type;
	}

	public void setYarn_packing_date(String yarn_packing_date) {
		if (yarn_packing_date == null || yarn_packing_date.isEmpty()) {
			this.yarn_packing_date = null;
		} else {
			this.yarn_packing_date = yarn_packing_date;
		}

	}

	public String getYarn_packing_start_date() {
		return yarn_packing_start_date;
	}

	public void setYarn_packing_start_date(String yarn_packing_start_date) {
		if (yarn_packing_start_date ==  null || yarn_packing_start_date.isEmpty()) {
			this.yarn_packing_start_date = null;
		}else {
			this.yarn_packing_start_date = yarn_packing_start_date;

		}
		
	}

	public String getYarn_packing_end_date() {
		return yarn_packing_end_date;
	}

	public void setYarn_packing_end_date(String yarn_packing_end_date) {
		if (yarn_packing_end_date == null || yarn_packing_end_date.isEmpty()) {
			this.yarn_packing_end_date = null;
		}else {
			this.yarn_packing_end_date = yarn_packing_end_date;
		}
	}


}
