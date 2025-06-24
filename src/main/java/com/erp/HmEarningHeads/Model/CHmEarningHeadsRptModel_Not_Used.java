package com.erp.HmEarningHeads.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Immutable
@Subselect("select * from hmv_earning_heads_rpt")
public class CHmEarningHeadsRptModel_Not_Used {

	@Id
	private String earning_heads_id;
	private String company_name;
	private String company_branch_name;
	private String earning_head_name;
	private String earning_type;
	private String earning_code;
	private String earning_head_version;
	private String earning_head_short_name;
	private String head_position;
	private String calculation_type;
	private String calculation_value;
	private String salary_parameter1;
	private String salary_parameter2;
	private String salary_parameter3;
	private String salary_parameter4;
	private String salary_parameter5;
	private String salary_parameter6;
	private String salary_parameter7;
	private String salary_parameter8;
	private String salary_parameter9;
	private String salary_parameter10;
	private String formula;
	private String remark;
	private String is_active;
	private String is_delete;
	private String created_by;
	private String created_on;
	private String modified_by;
	private String modified_on;
	private String deleted_by;
	private String deleted_on;
	private String company_id;
	private String company_branch_id;
	private String field_id;
	private String field_name;

}
