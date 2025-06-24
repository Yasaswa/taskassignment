//package com.erp.SmProductTypeGroupParameters.Model;
//
//import java.util.Date;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;
//
//import org.hibernate.annotations.CreationTimestamp;
//import org.hibernate.annotations.UpdateTimestamp;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//
//@AllArgsConstructor
//@NoArgsConstructor
//@Data
//@Entity
//@Table(name = "sm_product_type_group_parameters")
//public class CSmProductTypeGroupParametersModel {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "product_type_group_parameters_id")
//	private int product_type_group_parameters_id;
//	private Integer company_id;
//	private Integer product_type_id;
//	private String product_type_group_parameters_name;
//	private String product_type_group_parameters_code;
//	private String product_type_group;
//	private String remark;
//	private boolean is_active = Boolean.TRUE;
//	private boolean is_delete = Boolean.FALSE;
//	private String created_by;
//	@CreationTimestamp
//	@Column(name = "created_on", updatable = false)
//	private Date created_on;
//	private String modified_by;
//	@UpdateTimestamp
//	private Date modified_on;
//	private String deleted_by;
//	private Date deleted_on;
//	
//	public boolean isIs_active() {
//		return is_active;
//	}
//	public void setIs_active(boolean is_active) {
//		this.is_active = is_active;
//	}
//	public boolean isIs_delete() {
//		return is_delete;
//	}
//	public void setIs_delete(boolean is_delete) {
//		this.is_delete = is_delete;
//	}
//
//	
//
//}
