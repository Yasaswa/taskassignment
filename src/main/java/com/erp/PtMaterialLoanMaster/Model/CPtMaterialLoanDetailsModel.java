package com.erp.PtMaterialLoanMaster.Model;

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


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pt_material_loan_details")
public class CPtMaterialLoanDetailsModel {

@Id	
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "material_loan_details_id")
private  int material_loan_details_id;
private  Integer  material_loan_master_id;
private  Integer  company_id;
private  Integer  company_branch_id;
private  String  financial_year;
private  String  loan_no;
private  Integer  loan_version;
private  Date  loan_date;
private  String  indent_no;
private  Date  indent_date;
private  Integer  indent_version;
private  Integer  indent_details_id;
private  String  product_material_id;
private  Integer  product_material_unit_id;
private  double  product_material_balance_quantity;
private  double  product_material_balance_weight;
private  double  product_material_requested_quantity;
private  double  product_material_requested_weight;
private  double  product_material_issue_quantity;
private  double  product_material_issue_weight;
private double product_material_return_quantity;
private double product_material_return_weight;
private  String  loan_item_status;
private  String  issue_remark;
private  boolean  is_active;
private  boolean  is_delete;
private  String  created_by;
@CreationTimestamp
@Column(name = "created_on", updatable = false)
private  Date  created_on;
private  String  modified_by;
@UpdateTimestamp
private  Date  modified_on;
private  String  deleted_by;
private  Date  deleted_on;
private String issue_batch_no;
private double product_material_std_weight;
private String godown_id;

 
 } 

