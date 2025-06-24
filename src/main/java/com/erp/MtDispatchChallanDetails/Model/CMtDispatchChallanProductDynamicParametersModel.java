package com.erp.MtDispatchChallanDetails.Model;

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
@Table(name = "mt_dispatch_challan_product_dynamic_parameters")
public class CMtDispatchChallanProductDynamicParametersModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "dispatch_challan_parameter_id")
	private int dispatch_challan_parameter_id;
	private Integer company_id;
	private Integer company_branch_id;
	private Integer product_parameter_id;
	private Integer dispatch_challan_master_transaction_id;
	private Integer dispatch_challan_details_transaction_id;
	private String dispatch_challan_no;
	private String dispatch_challan_date;
	private Integer dispatch_challan_version;
	private String customer_order_no;
	private String product_id;
	private Integer product_type_id;
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
