package com.erp.AmModulesFormsUserAccess.Model;

import lombok.*;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Immutable
@Table(name = "amv_modules_forms_user_access")
public class CAmModulesFormsUserAccessViewModel {

	@Id
	private int modules_forms_user_access_id;
	private String user_type;
	private String user_name;
	private String login_name;
	private String login_password;
	private String modules_forms_name;
	private String parent_module_name;
	private String sub_module_name;
	private String modules_menu_name;
	private String modules_sub_menu_name;
	private Boolean all_access;
	private Boolean read_access;
	private Boolean add_access;
	private Boolean modify_access;
	private Boolean delete_access;
	private Boolean approve_access;
	private Boolean special_access;
	private String access_control;
	private Integer module_form_display_sequence;
	private Integer module_display_sequence;
	private Integer sub_module_display_sequence;
	private Integer menu_display_sequence;
	private Integer sub_menu_display_sequence;
	private String sub_menu_icon_class;
//	private String company_name;
//	private String company_branch_name;
	private String Active;
	private String Deleted;
	private Boolean is_active = Boolean.TRUE;
	private Boolean is_delete = Boolean.FALSE;
	private int company_id;
	private Integer user_id;
	private Integer modules_forms_id;
	private Integer company_branch_id;
	private Integer parent_module_id;
	private Integer sub_module_id;
	private Integer field_id;
	private String field_name;
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
	private Boolean is_protected = Boolean.FALSE;
	private Boolean header = Boolean.TRUE;
	private Boolean footer = Boolean.TRUE;
	private String user_code;
	private Integer modules_sub_menu_id;
	private Integer modules_menu_id;
	private String company_access;

	public Boolean getIs_active() {
		return is_active;
	}

	public void setIs_active(Boolean is_active) {
		this.is_active = is_active;
	}

	public Boolean getIs_delete() {
		return is_delete;
	}

	public void setIs_delete(Boolean is_delete) {
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
