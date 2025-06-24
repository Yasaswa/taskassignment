package com.erp.PtMaterialLoanMaster.Model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;



@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pt_material_loan_master")
public class CPtMaterialLoanMasterModel {

@Id	
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "material_loan_master_id")
private   int material_loan_master_id;
private  String  financial_year;
private  String  loan_no;
private  Integer  loan_version;
private  Date  loan_date;
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
private  String  material_loan_status;
private  Integer  from_cost_center_id;
private  Integer  to_cost_center_id;
private  boolean  is_active = Boolean.TRUE;
private  boolean  is_delete = Boolean.FALSE;
 @Column(name = "created_by", updatable = false)
private  String  created_by;
@CreationTimestamp
@Column(name = "created_on", updatable = false)
private  Date  created_on;
private  String  modified_by;
@UpdateTimestamp
private  Date  modified_on;
private  String  deleted_by;
private  Date  deleted_on;
private  int company_id;




 }

