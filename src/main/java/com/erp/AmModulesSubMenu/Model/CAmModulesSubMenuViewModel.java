package com.erp.AmModulesSubMenu.Model;

import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@Immutable
@Subselect("select * from amv_modules_sub_menu")
public class CAmModulesSubMenuViewModel {

	@Id
	private int modules_sub_menu_id;
	private String module_type;
	private String modules_sub_menu_name;
	private String modules_menu_name;
	private String modules_name;
	private String sub_modules_name;
	private int display_sequence;
//	private String company_name;
	private String Active;
	private String Deleted;
	private boolean is_active;
	private boolean is_delete;
	private int company_id;
	private Integer modules_id;
	private Integer sub_modules_id;
	private Integer modules_menu_id;
	private Integer field_id;
	private String field_name;

	public int getModules_sub_menu_id() {
		return modules_sub_menu_id;
	}

	public void setModules_sub_menu_id(int modules_sub_menu_id) {
		this.modules_sub_menu_id = modules_sub_menu_id;
	}

	public String getModule_type() {
		return module_type;
	}

	public void setModule_type(String module_type) {
		this.module_type = module_type;
	}

	public String getModules_sub_menu_name() {
		return modules_sub_menu_name;
	}

	public void setModules_sub_menu_name(String modules_sub_menu_name) {
		this.modules_sub_menu_name = modules_sub_menu_name;
	}

	public String getModules_menu_name() {
		return modules_menu_name;
	}

	public void setModules_menu_name(String modules_menu_name) {
		this.modules_menu_name = modules_menu_name;
	}

	public String getModules_name() {
		return modules_name;
	}

	public void setModules_name(String modules_name) {
		this.modules_name = modules_name;
	}

	public String getSub_modules_name() {
		return sub_modules_name;
	}

	public void setSub_modules_name(String sub_modules_name) {
		this.sub_modules_name = sub_modules_name;
	}

	public int getDisplay_sequence() {
		return display_sequence;
	}

	public void setDisplay_sequence(int display_sequence) {
		this.display_sequence = display_sequence;
	}

	public String getActive() {
		return Active;
	}

	public void setActive(String active) {
		Active = active;
	}

	public String getDeleted() {
		return Deleted;
	}

	public void setDeleted(String deleted) {
		Deleted = deleted;
	}

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

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

	public Integer getModules_id() {
		return modules_id;
	}

	public void setModules_id(Integer modules_id) {
		this.modules_id = modules_id;
	}

	public Integer getSub_modules_id() {
		return sub_modules_id;
	}

	public void setSub_modules_id(Integer sub_modules_id) {
		this.sub_modules_id = sub_modules_id;
	}

	public Integer getModules_menu_id() {
		return modules_menu_id;
	}

	public void setModules_menu_id(Integer modules_menu_id) {
		this.modules_menu_id = modules_menu_id;
	}

	public Integer getField_id() {
		return field_id;
	}

	public void setField_id(Integer field_id) {
		this.field_id = field_id;
	}

	public String getField_name() {
		return field_name;
	}

	public void setField_name(String field_name) {
		this.field_name = field_name;
	}

	public CAmModulesSubMenuViewModel(int modules_sub_menu_id, String module_type, String modules_sub_menu_name,
	                                  String modules_menu_name, String modules_name, String sub_modules_name, int display_sequence,
	                                  String active, String deleted, boolean is_active, boolean is_delete, int company_id,
	                                  Integer modules_id, Integer sub_modules_id, Integer modules_menu_id, Integer field_id, String field_name) {
		super();
		this.modules_sub_menu_id = modules_sub_menu_id;
		this.module_type = module_type;
		this.modules_sub_menu_name = modules_sub_menu_name;
		this.modules_menu_name = modules_menu_name;
		this.modules_name = modules_name;
		this.sub_modules_name = sub_modules_name;
		this.display_sequence = display_sequence;
		Active = active;
		Deleted = deleted;
		this.is_active = is_active;
		this.is_delete = is_delete;
		this.company_id = company_id;
		this.modules_id = modules_id;
		this.sub_modules_id = sub_modules_id;
		this.modules_menu_id = modules_menu_id;
		this.field_id = field_id;
		this.field_name = field_name;
	}

	public CAmModulesSubMenuViewModel() {
		super();
		// TODO Auto-generated constructor stub
	}


}
