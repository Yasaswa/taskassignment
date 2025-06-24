package com.erp.District.Model;

import lombok.Data;
import org.hibernate.annotations.Immutable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@Immutable
public class CDistrictRptModel_Not_Used {


	@Id
	private String district_id;
	private String country_name;
	private String state_name;
	private String district_name;
	private String district_short_name;
	private String district_code;
	private String company_name;
	private String country_code;
	private String state_code;
	private String is_active;
	private String is_delete;
	private String company_id;
	private String country_id;
	private String state_id;
	private String field_id;
	private String field_name;

	public String getDistrict_id() {
		return district_id;
	}

	public void setDistrict_id(String district_id) {
		this.district_id = district_id;
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

	public String getDistrict_name() {
		return district_name;
	}

	public void setDistrict_name(String district_name) {
		this.district_name = district_name;
	}

	public String getDistrict_short_name() {
		return district_short_name;
	}

	public void setDistrict_short_name(String district_short_name) {
		this.district_short_name = district_short_name;
	}

	public String getDistrict_code() {
		return district_code;
	}

	public void setDistrict_code(String district_code) {
		this.district_code = district_code;
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

	public String getState_code() {
		return state_code;
	}

	public void setState_code(String state_code) {
		this.state_code = state_code;
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

	public String getState_id() {
		return state_id;
	}

	public void setState_id(String state_id) {
		this.state_id = state_id;
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

	public CDistrictRptModel_Not_Used(String district_id, String country_name, String state_name, String district_name,
	                                  String district_short_name, String district_code, String company_name, String country_code,
	                                  String state_code, String is_active, String is_delete, String company_id, String country_id,
	                                  String state_id, String field_id, String field_name) {
		super();
		this.district_id = district_id;
		this.country_name = country_name;
		this.state_name = state_name;
		this.district_name = district_name;
		this.district_short_name = district_short_name;
		this.district_code = district_code;
		this.company_name = company_name;
		this.country_code = country_code;
		this.state_code = state_code;
		this.is_active = is_active;
		this.is_delete = is_delete;
		this.company_id = company_id;
		this.country_id = country_id;
		this.state_id = state_id;
		this.field_id = field_id;
		this.field_name = field_name;
	}

	public CDistrictRptModel_Not_Used() {
		super();
		// TODO Auto-generated constructor stub
	}


}
