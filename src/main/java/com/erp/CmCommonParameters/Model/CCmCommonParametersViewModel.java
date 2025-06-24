package com.erp.CmCommonParameters.Model;

import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
@Immutable
@Subselect("select * from  cmv_common_parameters")
public class CCmCommonParametersViewModel {

	@Id

	private int common_parameters_id;
	private int company_id;
	private String common_parameters_master_name;
	private String common_parameters_name;
	private String common_parameters_value;
	private boolean is_active;
	private boolean is_delete;
	private Date created_on;
	private Date modified_on;
	private Date deleted_on;
	private String created_by;
	private String modified_by;
	private String deleted_by;
	private Integer field_id;
	private String field_name;

	public int getcommon_parameters_id() {
		return common_parameters_id;
	}

	public void setcommon_parameters_id(int common_parameters_id) {
		this.common_parameters_id = common_parameters_id;
	}

	public int getcompany_id() {
		return company_id;
	}

	public void setcompany_id(int company_id) {
		this.company_id = company_id;
	}

	public String getcommon_parameters_master_name() {
		return common_parameters_master_name;
	}

	public void setcommon_parameters_master_name(String common_parameters_master_name) {
		this.common_parameters_master_name = common_parameters_master_name;
	}

	public String getcommon_parameters_name() {
		return common_parameters_name;
	}

	public void setcommon_parameters_name(String common_parameters_name) {
		this.common_parameters_name = common_parameters_name;
	}

	public String getcommon_parameters_value() {
		return common_parameters_value;
	}

	public void setcommon_parameters_value(String common_parameters_value) {
		this.common_parameters_value = common_parameters_value;
	}

	public boolean getis_active() {
		return is_active;
	}

	public void setis_active(boolean is_active) {
		this.is_active = is_active;
	}

	public boolean getis_delete() {
		return is_delete;
	}

	public void setis_delete(boolean is_delete) {
		this.is_delete = is_delete;
	}

	public Date getcreated_on() {
		return created_on;
	}

	public void setcreated_on(Date created_on) {
		this.created_on = created_on;
	}

	public Date getmodified_on() {
		return modified_on;
	}

	public void setmodified_on(Date modified_on) {
		this.modified_on = modified_on;
	}

	public Date getdeleted_on() {
		return deleted_on;
	}

	public void setdeleted_on(Date deleted_on) {
		this.deleted_on = deleted_on;
	}

	public String getcreated_by() {
		return created_by;
	}

	public void setcreated_by(String created_by) {
		this.created_by = created_by;
	}

	public String getmodified_by() {
		return modified_by;
	}

	public void setmodified_by(String modified_by) {
		this.modified_by = modified_by;
	}

	public String getdeleted_by() {
		return deleted_by;
	}

	public void setdeleted_by(String deleted_by) {
		this.deleted_by = deleted_by;
	}

	public Integer getfield_id() {
		return field_id;
	}

	public void setfield_id(Integer field_id) {
		this.field_id = field_id;
	}

	public String getfield_name() {
		return field_name;
	}

	public void setfield_name(String field_name) {
		this.field_name = field_name;
	}

	public CCmCommonParametersViewModel(int common_parameters_id, int company_id, String common_parameters_master_name,
	                                    String common_parameters_name, String common_parameters_value, boolean is_active, boolean is_delete,
	                                    Date created_on, Date modified_on, Date deleted_on, String created_by, String modified_by,
	                                    String deleted_by, Integer field_id, String field_name) {
		super();
		this.common_parameters_id = common_parameters_id;
		this.company_id = company_id;
		this.common_parameters_master_name = common_parameters_master_name;
		this.common_parameters_name = common_parameters_name;
		this.common_parameters_value = common_parameters_value;
		this.is_active = is_active;
		this.is_delete = is_delete;
		this.created_on = created_on;
		this.modified_on = modified_on;
		this.deleted_on = deleted_on;
		this.created_by = created_by;
		this.modified_by = modified_by;
		this.deleted_by = deleted_by;
		this.field_id = field_id;
		this.field_name = field_name;
	}

	public CCmCommonParametersViewModel() {
		super();
	}

}
