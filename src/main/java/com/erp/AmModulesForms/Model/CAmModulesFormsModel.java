package com.erp.AmModulesForms.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "am_modules_forms")
public class CAmModulesFormsModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "modules_forms_id")
	private int modules_forms_id;
	private int company_id;
	private Integer company_branch_id;
	private Integer module_id;
	private Integer modules_menu_id;
	private Integer sub_module_id;
	private Integer modules_sub_menu_id;
	private String modules_forms_name;
	private int display_sequence;
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
	private boolean is_active = Boolean.TRUE;
	private boolean is_delete = Boolean.FALSE;
	@Column(name = "created_by", updatable = false)
	private String created_by;
	@CreationTimestamp
	@Column(name = "created_on", updatable = false)
	private Date created_on;
	private String modified_by;
	@UpdateTimestamp
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;
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
