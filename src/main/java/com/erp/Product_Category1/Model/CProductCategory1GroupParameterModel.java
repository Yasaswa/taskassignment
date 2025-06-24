package com.erp.Product_Category1.Model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "sm_product_category1_group_parameter")
public class CProductCategory1GroupParameterModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_category1_group_parameter_id")
	private int product_category1_group_parameter_id;
	private Integer product_type_group_parameters_id;
	private Integer product_category1_id;
	private String prefix;
	private String suffix;
	private boolean print = Boolean.TRUE;
	private boolean production = Boolean.TRUE;
	private Integer company_id;
	private Integer product_type_id;
	private String remark;
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
	private String calculation_type;
	private String value1;
	private String value2;
	
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
	public boolean isPrint() {
		return print;
	}
	public void setPrint(boolean print) {
		this.print = print;
	}
	public boolean isProduction() {
		return production;
	}
	public void setProduction(boolean production) {
		this.production = production;
	}
	
	

}
