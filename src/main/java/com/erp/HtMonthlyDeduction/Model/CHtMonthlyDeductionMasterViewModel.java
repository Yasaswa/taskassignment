package com.erp.HtMonthlyDeduction.Model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Immutable
@Table(name = "htv_monthly_deduction_master")
public class CHtMonthlyDeductionMasterViewModel {

	@Id
	private Integer monthly_deduction_master_transaction_id;
	private String employee_type;
	private Integer deduction_type_id;
	private String deduction_type_name;
	private int company_id;
	private Integer company_branch_id;
	private Integer salary_year;
	private String salary_month;
	private boolean is_active = Boolean.TRUE;
	private boolean is_delete = Boolean.FALSE;
	private String created_by;
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;

}
