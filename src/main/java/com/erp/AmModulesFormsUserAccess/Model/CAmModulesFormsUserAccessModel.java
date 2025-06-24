package com.erp.AmModulesFormsUserAccess.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "am_modules_forms_user_access")
public class CAmModulesFormsUserAccessModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "modules_forms_user_access_id")
	private int modules_forms_user_access_id;
	private int company_id;
	private Integer company_branch_id;
	private String user_type;
	private Integer user_id;
	private Integer modules_forms_id;
	private Integer module_id;
	private Integer sub_module_id;
	private boolean all_access;
	private boolean read_access;
	private boolean add_access;
	private boolean modify_access;
	private boolean delete_access;
	private boolean approve_access;
	private boolean special_access;
	private boolean is_active = Boolean.TRUE;
	private boolean is_delete = Boolean.FALSE;
	private String created_by;
	@CreationTimestamp
	@Column(name = "created_on", updatable = false)
	private Date created_on;
	private String modified_by;
	@UpdateTimestamp
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;
	private String access_control;
	private String user_code;
	private String company_access;

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
