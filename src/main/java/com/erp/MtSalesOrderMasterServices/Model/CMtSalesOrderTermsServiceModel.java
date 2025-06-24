package com.erp.MtSalesOrderMasterServices.Model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "mt_sales_order_terms_services")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CMtSalesOrderTermsServiceModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sales_order_terms_transaction_id")
	private int sales_order_terms_transaction_id;
	private Integer company_id;
	private Integer company_branch_id;
	private Integer sales_order_master_transaction_id;
	private Integer sales_order_details_transaction_id;
	private String sales_order_no;
	private String sales_order_date;
	private Integer sales_order_version;
	private String customer_order_no;
	private String customer_order_Date;
	private Integer sales_order_terms_parameters_id;
	private String sales_order_terms_parameters_name;
	private String sales_order_terms_parameters_value;
	private String sales_order_terms_parameters_expected_value;
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


}
