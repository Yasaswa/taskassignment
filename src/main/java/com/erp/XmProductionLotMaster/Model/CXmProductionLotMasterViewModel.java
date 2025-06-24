package com.erp.XmProductionLotMaster.Model;

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
@Entity
@Immutable
@Subselect("select * from  xmv_production_lot_master")
public class CXmProductionLotMasterViewModel {

	@Id
	private Integer lot_id;
	private String lot_no;
	private String sub_lot_no;
	private String lot_date;
	private String lot_start_date;
	private String lot_end_date;
	private Integer lot_quantity;
	private Integer yarn_packing_types_id; 
	private String sales_type;
	private String product_type_name;
	private String production_count_name;
	private double production_actual_count;
	private String product_unit_name;
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
	private String sales_type_desc;
	private String lot_status_desc;
	private String lot_status;
	private String Active;
	private String Deleted;
	private String company_name;
	private String company_branch_name;
	private boolean is_active;
	private boolean is_delete;
	private String created_by;
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;
	private String financial_year;
	private int company_id;
	private Integer company_branch_id;
	private Integer production_count_id;
	private Integer product_type_id;
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
