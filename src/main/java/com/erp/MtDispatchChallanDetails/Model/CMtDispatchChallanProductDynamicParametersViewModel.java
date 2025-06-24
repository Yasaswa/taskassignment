package com.erp.MtDispatchChallanDetails.Model;

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
@Subselect("select * from  mtv_dispatch_challan_product_dynamic_parameters")
public class CMtDispatchChallanProductDynamicParametersViewModel {

	@Id
	private Integer dispatch_challan_parameter_id;
	private String dispatch_challan_no;
	private String dispatch_challan_date;
	private String product_type_name;
	private String product_type_group;
	private String product_material_name;
	private String product_material_code;
	private String product_parameter_name;
	private String product_parameter_value;
	private String product_parameter_actual_value;
	private String product_parameter_prefix;
	private String product_parameter_sufix;
	private String product_parameter_calculation_type;
	private String product_parameter_from_value;
	private String product_parameter_to_value;
	private String product_parameter_formula;
	private Integer so_sr_no;
	private String customer_order_no;
	private String remark;
	private String control_master;
	private String control_type;
	private String company_name;
	private String company_branch_name;
	private String Active;
	private String Deleted;
	private Integer dispatch_challan_version;
	private boolean is_active;
	private boolean is_delete;
	private String created_by;
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;
	private Integer company_id;
	private Integer company_branch_id;
	private String product_id;
	private Integer product_type_id;
	private Integer product_parameter_id;
	private Integer dispatch_challan_master_transaction_id;
	private Integer field_id;
	private String field_name;
	
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
