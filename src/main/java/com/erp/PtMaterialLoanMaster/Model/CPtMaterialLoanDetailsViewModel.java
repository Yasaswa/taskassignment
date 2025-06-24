package com.erp.PtMaterialLoanMaster.Model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import lombok.Data;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Immutable
@Subselect("select * from  ptv_material_loan_details")
public class CPtMaterialLoanDetailsViewModel  {
	
@Id
private  int  material_loan_details_id;
private  String loan_no;
private  Date  loan_date;
private  Integer  loan_version;
private  String  indent_no;
private  Date  indent_date;
private  Integer  indent_version;
private  String  product_material_name;
private  double  product_material_balance_quantity;
private  double  product_material_balance_weight;
private  double  product_material_requested_quantity;
private  double  product_material_requested_weight;
private  double  product_material_issue_quantity;
private  double  product_material_issue_weight;
private double product_material_return_quantity;
private double product_material_return_weight;
private  String  product_unit_name;
private  String  issue_remark;
private  String  loan_item_status_desc;
private  String  Deleted;
private  boolean  is_delete;
private  String  created_by;
private  Date  created_on;
private  String  modified_by;
private  Date  modified_on;
private  String  deleted_by;
private  Date  deleted_on;
private  String  financial_year;
private  String  loan_item_status;
private  Integer  company_id;
private  Integer  company_branch_id;
private  Integer  product_material_unit_id;
private  String  product_material_id;
private  Integer  material_loan_master_id;
private  Integer  indent_details_id;
 private double product_material_std_weight;
 private  String godown_id;


 } 

	


