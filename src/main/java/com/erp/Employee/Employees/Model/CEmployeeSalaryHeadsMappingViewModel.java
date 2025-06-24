package com.erp.Employee.Employees.Model;

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
@Subselect("SELECT * FROM hmv_employee_salary_heads_mapping")
public class CEmployeeSalaryHeadsMappingViewModel {

	@Id
	private int employee_salary_heads_mapping_id;
	private String head_type;
	private String effective_date;
	private String employee_code;
	private String employee_type;
	private String employee_name;
	private String department_name;
	private String designation_name;
	private String designation_short_name;
	private String salary_heads_code;
	private String salary_heads_head_name;
	private Integer salary_heads_position;
	private String salary_heads_type;
	private String salary_heads_short_name;
	private String calculation_type;
	private Double calculation_value;
	private String formula;
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
	private Integer employee_id;
	private Integer designation_id;
	private Integer salary_heads_id;

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
