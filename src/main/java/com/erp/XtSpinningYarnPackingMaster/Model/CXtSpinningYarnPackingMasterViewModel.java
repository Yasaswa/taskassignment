package com.erp.XtSpinningYarnPackingMaster.Model;

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
@Table(name =  "xtv_spinning_yarn_packing_master")
public class CXtSpinningYarnPackingMasterViewModel {

	@Id
	private int yarn_packing_master_id;
	private String yarn_packing_date;
	private String yarn_packing_start_date;
	private String yarn_packing_end_date;
	private String customer_name;
	private String customer_order_no;
	private String plant_name;
	private String country_name;
	private String spinn_plan_code;
	private String lot_type;
	private String lot_no;
	private String sub_lot_no;
	private Integer lot_starting_no;
	private Integer	lot_ending_no;
	private double total_cones;
	private double total_packages;
	private double net_weight;
	private double gross_weight;
	private double dispatched_packages;
	private double dispatched_weight;
	private Integer remening_quantity;
	private String sales_type_desc;
	private String sales_type;
	private String production_type_desc;
	private String production_type;
	private String packing_nature_desc;
	private String packing_nature;
	private String packing_status_desc;
	private String packing_status;
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
	private Integer company_id;
	private Integer company_branch_id;
	private Integer lot_id;
	private Integer customer_id;
	private Integer spinn_plan_id;
	private Integer plant_id;
	private Integer country_id;
	
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
