package com.erp.Employee.Employee_Grade.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Immutable
@Table(name = "amv_modules_forms_grade_access")
public class CAmModulesFormsGradeAccessViewModel {

	@Id
	private Integer modules_forms_grade_access_id;
	private String modules_forms_name;
	private String employee_grade_name;
	private String short_name;
	private String parent_module_name;
	private String sub_module_name;
	private boolean all_access;
	private boolean read_access;
	private boolean add_access;
	private boolean modify_access;
	private boolean delete_access;
	private boolean approve_access;
	private boolean special_access;
	private String access_control;
	private Integer module_form_display_sequence;
	private Integer module_display_sequence;
	private Integer sub_module_display_sequence;
//	private String company_name;
//	private String company_branch_name;
	private String Active;
	private String Deleted;
	private boolean is_active= Boolean.TRUE;
	private boolean is_delete= Boolean.FALSE;
	private Integer company_id;
	private Integer company_branch_id;
	private Integer modules_forms_id;
	private Integer parent_module_id;
	private Integer sub_module_id;
	private Integer grade_id;
	
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