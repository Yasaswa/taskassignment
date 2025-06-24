package com.erp.Designations.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Subselect;
import org.springframework.data.annotation.Immutable;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Immutable
@Data
@Subselect("SELECT * FROM hmv_salary_heads")
public class CSalaryHeadsViewModel {

	@Id
	private int salary_heads_id;
	private String salary_heads_name;
	private String salary_heads_type;
	private String salary_heads_code;
	private Integer salary_heads_version;
	private String salary_heads_short_name;
	private Integer salary_heads_position;
	private String calculation_type;
	private Double calculation_value;
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
	private String company_name;
	private String company_branch_name;
	private String remark;
	private String Active;
	private String Deleted;
	private boolean is_active;
	private boolean is_delete;
	private String created_by;
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;
	private Integer company_id;
	private Integer company_branch_id;
	private String field_name;
	private String field_id;

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
