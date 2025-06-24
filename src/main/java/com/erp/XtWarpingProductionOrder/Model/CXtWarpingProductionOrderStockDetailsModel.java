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
@Table(name = "xt_warping_production_order_stock_details")
public class CXtWarpingProductionOrderStockDetailsModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "warping_order_stock_details_id")
	private int warping_order_stock_details_id;
	private Integer warping_production_order_id;
	private String set_no;
	private Integer company_id;
	private Integer company_branch_id;
	private String product_material_id;
	private String goods_receipt_no;
	private String indent_no;
	private Integer supplier_id;
	private Integer no_of_cones;
	private Double cone_per_wt;
	private Double warping_quantity;
	private String yarn_requsition_status;
	private String issue_requisition_type;
	private String batch_no;
	private Integer product_material_unit_id;
	private String remark;


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
}


