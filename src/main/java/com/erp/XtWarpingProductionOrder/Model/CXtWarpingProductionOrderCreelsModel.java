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
@Table(name = "xt_warping_production_order_creels")
public class CXtWarpingProductionOrderCreelsModel  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "warping_production_order_creels_id")
	private int warping_production_order_creels_id;
	private Integer warping_production_order_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String creel_no;
	private String set_no;
	private String product_material_id;
	private Integer production_count;
	private Integer no_of_cones;
	private double per_creel_ends;
	private double creel_bottom;
	private double creel_length;
	private double creel_weight;
	private Integer no_of_beams;
	private Double cone_per_wt;
	private Double set_length;  // Added field
	private Double actual_net_wt;  // Added field
	private Double calculated_bottom;  // Added field
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
	private String yarn_count;
}
