package com.erp.State.Model;

import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@Immutable
@Subselect("select * from cmv_state_rpt")
public class CStateRptModel {

	@Id
	private String state_id;
	private String country_name;
	private String state_name;
	private String state_short_name;
	private String state_code;
	private String company_name;
	private String country_code;
	private String is_active;
	private String is_delete;
	private String created_by;
	private String created_on;
	private String modified_by;
	private String modified_on;
	private String deleted_by;
	private String deleted_on;
	private String company_id;
	private String country_id;
	private String field_id;
	private String field_name;

	public String getState_id() {
		return state_id;
	}

	public void setState_id(String state_id) {
		this.state_id = state_id;
	}

	public String getCountry_name() {
		return country_name;
	}

	public void setCountry_name(String country_name) {
		this.country_name = country_name;
	}

	public String getState_name() {
		return state_name;
	}

	public void setState_name(String state_name) {
		this.state_name = state_name;
	}

	public String getState_short_name() {
		return state_short_name;
	}

	public void setState_short_name(String state_short_name) {
		this.state_short_name = state_short_name;
	}

	public String getState_code() {
		return state_code;
	}

	public void setState_code(String state_code) {
		this.state_code = state_code;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getCountry_code() {
		return country_code;
	}

	public void setCountry_code(String country_code) {
		this.country_code = country_code;
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

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public String getCountry_id() {
		return country_id;
	}

	public void setCountry_id(String country_id) {
		this.country_id = country_id;
	}

	public String getField_id() {
		return field_id;
	}

	public void setField_id(String field_id) {
		this.field_id = field_id;
	}

	public String getField_name() {
		return field_name;
	}

	public void setField_name(String field_name) {
		this.field_name = field_name;
	}

	public CStateRptModel(String state_id, String country_name, String state_name, String state_short_name,
	                      String state_code, String company_name, String country_code, String is_active, String is_delete,
	                      String company_id, String country_id, String field_id, String field_name) {
		super();
		this.state_id = state_id;
		this.country_name = country_name;
		this.state_name = state_name;
		this.state_short_name = state_short_name;
		this.state_code = state_code;
		this.company_name = company_name;
		this.country_code = country_code;
		this.is_active = is_active;
		this.is_delete = is_delete;
		this.company_id = company_id;
		this.country_id = country_id;
		this.field_id = field_id;
		this.field_name = field_name;
	}

	public CStateRptModel() {
		super();
		// TODO Auto-generated constructor stub
	}

}
