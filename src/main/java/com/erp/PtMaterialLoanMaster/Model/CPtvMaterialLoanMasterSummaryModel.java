package com.erp.PtMaterialLoanMaster.Model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Immutable
@Subselect("select * from  ptv_material_loan_master_summary")
public class CPtvMaterialLoanMasterSummaryModel {
@Id
private  int  material_loan_master_id;
private  String loan_no;
private  Integer  loan_version;
private  Date  loan_date;
private  String  from_company_name;
private  String  to_company_name;
private  String  from_department_name;
private  String  from_sub_department_name;
private  String  to_department_name;
private  String  to_sub_department_name;
private  String  requested_by_name;
private  String  received_by_name;
private  String  material_loan_status_desc;
private  String  from_cost_center_name;
private  String  to_cost_center_name;
private  String  requested_by_email;
private  String  received_by_email;
private  String  Active;
private  String  Deleted;
private  boolean  is_active;
private  boolean  is_delete;
private  String  created_by;
private  Date  created_on;
private  String  modified_by;
private  Date  modified_on;
private  String  deleted_by;
private  Date  deleted_on;
private  String  financial_year;
private  Integer  from_cost_center_id;
private  Integer  to_cost_center_id;
private  String  material_loan_status;
private  int  from_company_id;
private  Integer  from_company_branch_id;
private  Integer  from_department_id;
private  Integer  from_sub_department_id;
private  Integer  requested_by_id;
private  int  to_company_id;
private  Integer  to_company_branch_id;
private  Integer  to_department_id;
private  Integer  to_sub_department_id;
private  Integer  received_by_id;
private  int company_id;
private  String  field_name;
private  Integer  field_id;

 
 } 

