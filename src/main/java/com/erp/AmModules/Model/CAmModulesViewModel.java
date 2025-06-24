package com.erp.AmModules.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Immutable
@Subselect("select * from  amv_modules")
public class CAmModulesViewModel {

	@Id
	private int modules_id;
	private String module_name;
	private String parent_module_name;
	private int display_sequence;
	private String company_name;
	private String Active;
	private String Deleted;
	private boolean is_active = Boolean.TRUE;
	private boolean is_delete = Boolean.FALSE;
	private int company_id;
	private Integer parent_module_id;
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
