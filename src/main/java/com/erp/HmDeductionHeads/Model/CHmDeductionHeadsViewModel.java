package com.erp.HmDeductionHeads.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Immutable
@Subselect("select * from hmv_deduction_heads ")
public class CHmDeductionHeadsViewModel {

	@Id
	private int deduction_heads_id;
	private String company_name;
	private String company_branch_name;
	private String deduction_head_name;
	private String deduction_type;
	private String deduction_code;
	private String deduction_head_short_name;
	private Integer deduction_head_version;
	private Integer head_position;
	private String calculation_type;
	private double calculation_value;
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
	// Added by mohit use for 1.1
	private String Active;
	private String Deleted;
	private String remark;
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
	private Integer field_id;
	private String field_name;

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
