package com.erp.HtSalaryRules.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "hmv_salary_rules")
public class CHtvSalaryRulesViewModel {
	@Id
	private Integer salary_rule_id;

	private String property_name;
	private String department_name;
	private String sub_department_name;
	private String job_type_name;
	private String rule_days;
	private String job_type_short_name;
	private Integer company_id;
	private Integer property_id;
	private Integer department_id;
	private Integer sub_department_id;
	private Integer job_type_id;
	private boolean is_delete = Boolean.FALSE;
	private String created_by;
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;
}
