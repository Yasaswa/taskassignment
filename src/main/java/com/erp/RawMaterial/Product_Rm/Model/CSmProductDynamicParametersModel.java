package com.erp.RawMaterial.Product_Rm.Model;

import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "sm_product_dynamic_parameters")
public class CSmProductDynamicParametersModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_parameter_id")
	private int product_parameter_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String product_id;
	private Integer product_type_id;
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
	private Integer product_type_dynamic_controls_id;
	
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
