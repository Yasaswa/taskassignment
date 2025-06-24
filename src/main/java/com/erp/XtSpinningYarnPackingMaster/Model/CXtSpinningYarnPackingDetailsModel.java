package com.erp.XtSpinningYarnPackingMaster.Model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "xt_spinning_yarn_packing_details")
public class CXtSpinningYarnPackingDetailsModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "yarn_packing_details_id")
	private int yarn_packing_details_id;
	private Integer company_id;
	private Integer company_branch_id;
	private Integer lot_id;
	private String financial_year;
	private Integer yarn_packing_master_id;
	private String yarn_packing_date;
	private Integer sr_no;
	private String product_material_id;
	private Integer production_count_id;
	private double production_actual_count;
	private Integer product_unit_id;
	private Integer yarn_packing_types_id;
	private Integer godown_id;
	private double cone_weight;
	private double cone_quantity;
	private double per_packing_weight;
	private double packing_quantity;
	private double packing_gross_weight;
	private double packing_net_weight;
	private Integer packing_starting_no;
	private Integer packing_ending_no;
	private String shipping_mark;
	private String packing_details_status;
	private double item_dispatch_quantity;
	private double item_dispatch_weight;
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