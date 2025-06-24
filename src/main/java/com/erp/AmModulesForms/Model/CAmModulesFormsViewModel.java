package com.erp.AmModulesForms.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Immutable
@Table(name = "amv_modules_forms")
public class CAmModulesFormsViewModel {

	@Id
	private int modules_forms_id;
	private String modules_forms_name;
	private String parent_module_name;
	private String sub_module_name;
	private String display_name;
	private String icon_class;
	private String menu_type;
	private String listing_component_name;
	private String listing_component_import_path;
	private String listing_navigation_url;
	private String url_parameter;
	private String form_component_name;
	private String form_component_import_path;
	private String form_navigation_url;
	private String page_header;
	private Boolean is_selected;
	private int module_form_display_sequence;
	private int module_display_sequence;
	private int sub_module_display_sequence;
	private String modules_menu_name;
	private String modules_sub_menu_name;
//	private String company_name;
//	private String company_branch_name;
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
	private int company_id;
	private Integer company_branch_id;
	private Integer parent_module_id;
	private Integer sub_module_id;
	private Integer modules_sub_menu_id;
	private Integer modules_menu_id;
	private Integer field_id;
	private String field_name;
	private Boolean is_protected = Boolean.FALSE;
	private Boolean header = Boolean.TRUE;
	private Boolean footer = Boolean.TRUE;

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

	public Boolean getIs_protected() {
		return is_protected;
	}

	public void setIs_protected(Boolean is_protected) {
		this.is_protected = is_protected;
	}

	public Boolean getHeader() {
		return header;
	}

	public void setHeader(Boolean header) {
		this.header = header;
	}

	public Boolean getFooter() {
		return footer;
	}

	public void setFooter(Boolean footer) {
		this.footer = footer;
	}
}
