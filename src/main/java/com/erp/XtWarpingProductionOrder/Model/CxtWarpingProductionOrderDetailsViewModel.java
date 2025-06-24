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

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Immutable
@Entity
@Table(name = "xtv_warping_production_order_details")
public class CxtWarpingProductionOrderDetailsViewModel {

	@Id
	private Integer warping_order_details_id;
	private Integer warping_production_order_id;
	//	private Integer supplier_id;
	private String cone_per_wt;
	//	private String good_receipt_no;
	private String product_material_name;
	private String product_unit_name;
	private String warping_quantity;
	private Integer product_material_unit_id;
	private String product_material_id;
	private Double no_of_cones;
	private String Deleted;
	//    private boolean is_active;
	private boolean is_delete;
	private String created_by;
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;
	private int company_id;
	private Integer company_branch_id;
//	private Integer production_count_id;
//	private String product_parameter_value;
	private String production_count_name;
	private Double closing_balance_quantity;
	private Double closing_balance_weight;
	private String product_material_code;
	private String product_material_unit_name;
	private String set_no;
	private Double product_material_balance_quantity;

	public boolean isIs_delete() {
		return is_delete;
	}

	public void setIs_delete(boolean is_delete) {
		this.is_delete = is_delete;
	}


}
