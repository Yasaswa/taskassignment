package com.erp.XtWarpingProductionOrder.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Immutable
@Entity
@Table(name = "xtv_warping_production_order_stock_details")
public class CXtvWarpingProductionOrderStockDetailsModel {

	@Id
	private int warping_order_stock_details_id;
	private String goods_receipt_no;
//	private String customer_goods_receipt_no;
	private String supplier_name;
	private String customer_name;
//	private String indent_no;
	private String product_material_name;
	private String remark;
//	private double weight_per_packing;
	private double actual_count;
//	private double closing_balance_quantity;
//	private double closing_balance_weight;
	private Integer no_of_cones;
	private double cone_per_wt;
	private double warping_quantity;
	private boolean is_delete;
	private String created_by;
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;
	private Integer warping_production_order_id;
	private String set_no;
	private Integer company_id;
	private Integer company_branch_id;
	private String product_material_id;
	private String product_material_code;
	private Integer supplier_id;
	private String batch_no;
	private String issue_requisition_type;
	private Integer product_material_unit_id;
	private String godown_id;
	private String godown_section_id;
	private String godown_section_beans_id;

}

