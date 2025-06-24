package com.erp.RawMaterial.Product_Rm.Model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Immutable
@Table(name = "smv_product_dynamic_parameters")
public class CSmProductDynamicParametersViewModel {
	
	@Id
	private Integer product_parameter_id;
	private String product_type_name;
	private String product_type_group;
	private String product_material_name;
	private String product_material_code;
	private String product_parameter_name;
	private String product_parameter_value;
	private String product_parameter_prefix;
	private String product_parameter_sufix;
	private String product_parameter_calculation_type;
	private String product_parameter_from_value;
	private String product_parameter_to_value;
	private String product_parameter_formula;
	private String remark;
	private String control_master;
	private String company_name;
	private String company_branch_name;
	private String Active;
	private String Deleted;
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
	private Integer product_type_dynamic_controls_id;
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
