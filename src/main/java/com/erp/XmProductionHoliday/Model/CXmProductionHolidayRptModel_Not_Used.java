package com.erp.XmProductionHoliday.Model;

import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@Immutable
@Subselect("select * from xmv_production_holiday_rpt")
public class CXmProductionHolidayRptModel_Not_Used {


	@Id
	private String production_holiday_id;
	private String production_holiday_date;
	private String production_holiday_name;
	private String production_holiday_remark;
	private String company_name;
	private String company_branch_name;
	private String is_active;
	private String is_delete;
	private String created_by;
	private String created_on;
	private String modified_by;
	private String modified_on;
	private String deleted_by;
	private String deleted_on;
	private String company_id;
	private String company_branch_id;
	private String field_name;
	private String field_id;

	public String getProduction_holiday_id() {
		return production_holiday_id;
	}

	public void setProduction_holiday_id(String production_holiday_id) {
		this.production_holiday_id = production_holiday_id;
	}

	public String getProduction_holiday_date() {
		return production_holiday_date;
	}

	public void setProduction_holiday_date(String production_holiday_date) {
		this.production_holiday_date = production_holiday_date;
	}

	public String getProduction_holiday_name() {
		return production_holiday_name;
	}

	public void setProduction_holiday_name(String production_holiday_name) {
		this.production_holiday_name = production_holiday_name;
	}

	public String getProduction_holiday_remark() {
		return production_holiday_remark;
	}

	public void setProduction_holiday_remark(String production_holiday_remark) {
		this.production_holiday_remark = production_holiday_remark;
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

	public String getField_name() {
		return field_name;
	}

	public void setField_name(String field_name) {
		this.field_name = field_name;
	}

	public String getField_id() {
		return field_id;
	}

	public void setField_id(String field_id) {
		this.field_id = field_id;
	}

	public CXmProductionHolidayRptModel_Not_Used(String production_holiday_id, String production_holiday_date,
	                                             String production_holiday_name, String production_holiday_remark, String company_name,
	                                             String company_branch_name, String is_active, String is_delete, String created_by, String created_on,
	                                             String modified_by, String modified_on, String deleted_by, String deleted_on, String company_id,
	                                             String company_branch_id, String field_name, String field_id) {
		super();
		this.production_holiday_id = production_holiday_id;
		this.production_holiday_date = production_holiday_date;
		this.production_holiday_name = production_holiday_name;
		this.production_holiday_remark = production_holiday_remark;
		this.company_name = company_name;
		this.company_branch_name = company_branch_name;
		this.is_active = is_active;
		this.is_delete = is_delete;
		this.created_by = created_by;
		this.created_on = created_on;
		this.modified_by = modified_by;
		this.modified_on = modified_on;
		this.deleted_by = deleted_by;
		this.deleted_on = deleted_on;
		this.company_id = company_id;
		this.company_branch_id = company_branch_id;
		this.field_name = field_name;
		this.field_id = field_id;
	}

	public CXmProductionHolidayRptModel_Not_Used() {
		super();
	}


}
