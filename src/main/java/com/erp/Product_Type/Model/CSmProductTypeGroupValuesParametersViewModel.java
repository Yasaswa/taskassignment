package com.erp.Product_Type.Model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Subselect;
import org.springframework.data.annotation.Immutable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Immutable
@Data
@Subselect("Select * From smv_product_type_group_values_parameter")
public class CSmProductTypeGroupValuesParametersViewModel {
	
	@Id
	private int product_type_group_parameters_id;
	private String product_type_group_parameters_values_name;
	private String product_type_group_parameters_values_code;
	private String product_type_group_parameters_name;
	private String product_type_group_parameters_code;
	private String product_type_name;
	private String product_type_short_name;
	private String product_type_group;
	private String company_name;
	private String remark;
	private String active;
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
	private Integer product_type_id;
	private String field_name;
	private Integer field_id;
	private Integer product_type_group_parameters_values_id;
	
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
