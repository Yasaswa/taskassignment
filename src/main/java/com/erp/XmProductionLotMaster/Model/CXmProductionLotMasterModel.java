package com.erp.XmProductionLotMaster.Model;

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
@Table(name = "xm_production_lot_master")
public class CXmProductionLotMasterModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "lot_id")
	private Integer lot_id;
	private int company_id;
	private Integer company_branch_id;
	private String financial_year;
	private Date lot_date;
	private Integer product_type_id;
	private String lot_no;
	private String sub_lot_no;
	private Date lot_start_date;
	private Date lot_end_date;
	private String sales_type;
	private Integer production_count_id;
	private double production_actual_count;
	private Integer yarn_packing_types_id;
	private Integer lot_quantity;
	private Integer product_unit_id;
	private double cone_weight;
	private double cone_quantity;
	private double per_packing_weight;
	private Integer lot_starting_no;
	private Integer lot_ending_no;
	private double total_cones;
	private double total_packages;
	private double gross_weight;
	private double net_weight;
	private double dispatched_packages;
	private double dispatched_weight;
	private String lot_status;
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
