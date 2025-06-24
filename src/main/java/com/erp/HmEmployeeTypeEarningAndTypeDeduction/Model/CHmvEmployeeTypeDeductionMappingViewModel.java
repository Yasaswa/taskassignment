package com.erp.HmEmployeeTypeEarningAndTypeDeduction.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Immutable
@Subselect("select * from hmv_employee_type_deduction_mapping")
public class CHmvEmployeeTypeDeductionMappingViewModel {

	@Id
	private int employee_type_deduction_mapping_id;
	private Date effective_date;
	private String employee_type_name;
	private String employee_type_short_name;
	private String employee_type_group_name;
	private String employee_type_group_short_name;
	private String deduction_code;
	private String deduction_head_name;
	private String deduction_type;
	private String deduction_head_short_name;
	private String calculation_type;
	private double calculation_value;
	private String formula;
	private String Active;
	private String Deleted;
	private boolean is_active = Boolean.TRUE;
	private boolean is_delete = Boolean.FALSE;
	private String created_by;
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;
	private Integer company_id;
	private Integer employee_type_id;
	private Integer deduction_heads_id;

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
