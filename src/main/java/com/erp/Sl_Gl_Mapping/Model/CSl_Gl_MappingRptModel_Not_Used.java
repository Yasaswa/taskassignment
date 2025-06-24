package com.erp.Sl_Gl_Mapping.Model;

import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@Immutable
@Subselect("select * from hmv_sl_gl_mapping_rpt")
public class CSl_Gl_MappingRptModel_Not_Used {
	@Id
	private String sl_gl_mapping_id;
	private String company_name;
	private String company_branch_name;
	private String schedule_ledger_name;
	private String general_ledger_name;
	private String is_active;
	private String is_delete;
	private String created_by;
	private String created_on;
	private String modified_by;
	private String modified_on;
	private String deleted_by;
	private String deleted_on;
	private String schedule_ledger_id;
	private String general_ledger_id;
	private String company_id;
	private String company_branch_id;

	public String getSl_gl_mapping_id() {
		return sl_gl_mapping_id;
	}

	public void setSl_gl_mapping_id(String sl_gl_mapping_id) {
		this.sl_gl_mapping_id = sl_gl_mapping_id;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getCompany_branch_name() {
		return company_branch_name;
	}

	public void setCompany_branch_name(String company_branch_name) {
		this.company_branch_name = company_branch_name;
	}

	public String getSchedule_ledger_name() {
		return schedule_ledger_name;
	}

	public void setSchedule_ledger_name(String schedule_ledger_name) {
		this.schedule_ledger_name = schedule_ledger_name;
	}

	public String getGeneral_ledger_name() {
		return general_ledger_name;
	}

	public void setGeneral_ledger_name(String general_ledger_name) {
		this.general_ledger_name = general_ledger_name;
	}

	public String getIs_active() {
		return is_active;
	}

	public void setIs_active(String is_active) {
		this.is_active = is_active;
	}

	public String getIs_delete() {
		return is_delete;
	}

	public void setIs_delete(String is_delete) {
		this.is_delete = is_delete;
	}

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public String getCreated_on() {
		return created_on;
	}

	public void setCreated_on(String created_on) {
		this.created_on = created_on;
	}

	public String getModified_by() {
		return modified_by;
	}

	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}

	public String getModified_on() {
		return modified_on;
	}

	public void setModified_on(String modified_on) {
		this.modified_on = modified_on;
	}

	public String getDeleted_by() {
		return deleted_by;
	}

	public void setDeleted_by(String deleted_by) {
		this.deleted_by = deleted_by;
	}

	public String getDeleted_on() {
		return deleted_on;
	}

	public void setDeleted_on(String deleted_on) {
		this.deleted_on = deleted_on;
	}

	public String getSchedule_ledger_id() {
		return schedule_ledger_id;
	}

	public void setSchedule_ledger_id(String schedule_ledger_id) {
		this.schedule_ledger_id = schedule_ledger_id;
	}

	public String getGeneral_ledger_id() {
		return general_ledger_id;
	}

	public void setGeneral_ledger_id(String general_ledger_id) {
		this.general_ledger_id = general_ledger_id;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public String getCompany_branch_id() {
		return company_branch_id;
	}

	public void setCompany_branch_id(String company_branch_id) {
		this.company_branch_id = company_branch_id;
	}

	@Override
	public String toString() {
		return "CSl_Gl_MappingRptModel [sl_gl_mapping_id=" + sl_gl_mapping_id + ", company_name=" + company_name
				+ ", company_branch_name=" + company_branch_name + ", schedule_ledger_name=" + schedule_ledger_name
				+ ", general_ledger_name=" + general_ledger_name + ", is_active=" + is_active + ", is_delete="
				+ is_delete + ", created_by=" + created_by + ", created_on=" + created_on + ", modified_by="
				+ modified_by + ", modified_on=" + modified_on + ", deleted_by=" + deleted_by + ", deleted_on="
				+ deleted_on + ", schedule_ledger_id=" + schedule_ledger_id + ", general_ledger_id=" + general_ledger_id
				+ ", company_id=" + company_id + ", company_branch_id=" + company_branch_id + "]";
	}

	public CSl_Gl_MappingRptModel_Not_Used(String sl_gl_mapping_id, String company_name, String company_branch_name,
	                                       String schedule_ledger_name, String general_ledger_name, String is_active, String is_delete,
	                                       String created_by, String created_on, String modified_by, String modified_on, String deleted_by,
	                                       String deleted_on, String schedule_ledger_id, String general_ledger_id, String company_id,
	                                       String company_branch_id) {
		super();
		this.sl_gl_mapping_id = sl_gl_mapping_id;
		this.company_name = company_name;
		this.company_branch_name = company_branch_name;
		this.schedule_ledger_name = schedule_ledger_name;
		this.general_ledger_name = general_ledger_name;
		this.is_active = is_active;
		this.is_delete = is_delete;
		this.created_by = created_by;
		this.created_on = created_on;
		this.modified_by = modified_by;
		this.modified_on = modified_on;
		this.deleted_by = deleted_by;
		this.deleted_on = deleted_on;
		this.schedule_ledger_id = schedule_ledger_id;
		this.general_ledger_id = general_ledger_id;
		this.company_id = company_id;
		this.company_branch_id = company_branch_id;
	}

	public CSl_Gl_MappingRptModel_Not_Used() {
		super();
		// TODO Auto-generated constructor stub
	}

}
