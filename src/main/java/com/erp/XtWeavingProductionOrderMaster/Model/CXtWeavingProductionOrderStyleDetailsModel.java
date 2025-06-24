package com.erp.XtWeavingProductionOrderMaster.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "xt_weaving_production_order_style_details")
public class CXtWeavingProductionOrderStyleDetailsModel {
	private final boolean is_delete = Boolean.FALSE;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "weaving_production_style_id")
	private Integer weaving_production_style_id;
	private Integer weaving_production_order_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String product_material_id;
	private String sales_order_no;
	private String set_no;
	private String sort_no;
	private Double schedule_quantity;
	private String schedule_date;
	private String style_variant1;
//	private String style_variant2;
//	private String style_variant3;
//	private String style_variant4;
//	private String style_variant5;
	private Double style_variant1_qty;
//	private Double style_variant2_qty;
//	private Double style_variant3_qty;
//	private Double style_variant4_qty;
//	private Double style_variant5_qty;
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
}
