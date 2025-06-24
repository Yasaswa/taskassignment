package com.erp.AmModulesSubMenu.Model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "am_modules_sub_menu")
public class CAmModulesSubMenuModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "modules_sub_menu_id")
	private int modules_sub_menu_id;
	private int company_id;
	private Integer company_branch_id;
	private Integer modules_id;
	private Integer sub_modules_id;
	private String modules_type;
	private Integer modules_menu_id;
	private String modules_sub_menu_name;
	private int display_sequence;
	private boolean is_active;
	private boolean is_delete;
	private String created_by;
	@CreationTimestamp
	@Column(name = "created_on", updatable = false)
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;

	public int getModules_sub_menu_id() {
		return modules_sub_menu_id;
	}

	public void setModules_sub_menu_id(int modules_sub_menu_id) {
		this.modules_sub_menu_id = modules_sub_menu_id;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

	public Integer getCompany_branch_id() {
		return company_branch_id;
	}

	public void setCompany_branch_id(Integer company_branch_id) {
		this.company_branch_id = company_branch_id;
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

	public String getModules_type() {
		return modules_type;
	}

	public void setModules_type(String modules_type) {
		this.modules_type = modules_type;
	}

	public Integer getModules_menu_id() {
		return modules_menu_id;
	}

	public void setModules_menu_id(Integer modules_menu_id) {
		this.modules_menu_id = modules_menu_id;
	}

	public String getModules_sub_menu_name() {
		return modules_sub_menu_name;
	}

	public void setModules_sub_menu_name(String modules_sub_menu_name) {
		this.modules_sub_menu_name = modules_sub_menu_name;
	}

	public int getDisplay_sequence() {
		return display_sequence;
	}

	public void setDisplay_sequence(int display_sequence) {
		this.display_sequence = display_sequence;
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

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public Date getCreated_on() {
		return created_on;
	}

	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
	}

	public String getModified_by() {
		return modified_by;
	}

	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}

	public Date getModified_on() {
		return modified_on;
	}

	public void setModified_on(Date modified_on) {
		this.modified_on = modified_on;
	}

	public String getDeleted_by() {
		return deleted_by;
	}

	public void setDeleted_by(String deleted_by) {
		this.deleted_by = deleted_by;
	}

	public Date getDeleted_on() {
		return deleted_on;
	}

	public void setDeleted_on(Date deleted_on) {
		this.deleted_on = deleted_on;
	}

	public CAmModulesSubMenuModel(int modules_sub_menu_id, int company_id, Integer company_branch_id,
	                              Integer modules_id, Integer sub_modules_id, String modules_type, Integer modules_menu_id,
	                              String modules_sub_menu_name, int display_sequence, boolean is_active, boolean is_delete, String created_by,
	                              Date created_on, String modified_by, Date modified_on, String deleted_by, Date deleted_on) {
		super();
		this.modules_sub_menu_id = modules_sub_menu_id;
		this.company_id = company_id;
		this.company_branch_id = company_branch_id;
		this.modules_id = modules_id;
		this.sub_modules_id = sub_modules_id;
		this.modules_type = modules_type;
		this.modules_menu_id = modules_menu_id;
		this.modules_sub_menu_name = modules_sub_menu_name;
		this.display_sequence = display_sequence;
		this.is_active = is_active;
		this.is_delete = is_delete;
		this.created_by = created_by;
		this.created_on = created_on;
		this.modified_by = modified_by;
		this.modified_on = modified_on;
		this.deleted_by = deleted_by;
		this.deleted_on = deleted_on;
	}

	public CAmModulesSubMenuModel() {
		super();
		// TODO Auto-generated constructor stub
	}


}
