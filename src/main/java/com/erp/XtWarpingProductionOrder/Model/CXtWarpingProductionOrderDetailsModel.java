package com.erp.XtWarpingProductionOrder.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "xt_warping_production_order_details")
public class CXtWarpingProductionOrderDetailsModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "warping_order_details_id")
	private int warping_order_details_id;
	private Integer warping_production_order_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String product_material_id;
	private Integer product_material_unit_id;
//	private String good_receipt_no;
//	private Integer supplier_id;
	private double cone_per_wt;
	private double warping_quantity;
	private double no_of_cones;
	private Boolean is_delete = Boolean.FALSE;
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

	public boolean isIs_delete() {
		return is_delete;
	}
	public void setIs_delete(boolean is_delete) {
		this.is_delete = is_delete;
	}
}

