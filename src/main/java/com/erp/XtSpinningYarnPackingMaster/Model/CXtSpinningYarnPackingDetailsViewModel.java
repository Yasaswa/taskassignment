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
@Table(name  = "xtv_spinning_yarn_packing_details")
public class CXtSpinningYarnPackingDetailsViewModel {

	@Id
	private int yarn_packing_details_id;
	private String yarn_packing_date;
	private String product_material_name;
	private String product_type_group;
	private Integer sr_no;
	private String customer_order_no;
	private String lot_no;
	private String sub_lot_no;
	private String production_count_name;
	private double production_actual_count;
	private String product_unit_name;
	private String yarn_packing_types_name;
	private double cone_weight;
	private double cone_quantity;
	private double per_packing_weight;
	private double packing_gross_weight;
	private double packing_net_weight;
	private double packing_quantity;
	private Integer remening_quantity;
	private Integer packing_starting_no;
	private Integer packing_ending_no;
	private String godown_name;
	private String shipping_mark;
	private String packing_details_status_desc;
	private String packing_details_status;
	private double item_dispatch_quantity;
	private double item_dispatch_weight;
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
	private String product_material_id;
	private Integer production_count_id;
	private Integer yarn_packing_master_id;
	private Integer product_unit_id;
	private Integer godown_id;
	private Integer yarn_packing_types_id;
	private Integer product_type_id;

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
