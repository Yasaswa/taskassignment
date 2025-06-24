package com.erp.District.Model;

import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@Immutable
@Subselect("select * from cmv_district")
public class CDistrictViewModel {

	@Id
	private int district_id;
	private String country_name;
	private String state_name;
	private String district_name;
	private String district_short_name;
	private String district_code;
	private String company_name;
	private String country_code;
	private String state_code;
	private boolean is_delete = Boolean.FALSE;
	private boolean is_active = Boolean.TRUE;
	private Integer company_id;
	private Integer country_id;
	private Integer state_id;
	private Integer field_id;
	private String field_name;

	public int getDistrict_id() {
		return district_id;
	}

	public void setDistrict_id(int district_id) {
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

	public boolean isIs_delete() {
		return is_delete;
	}

	public void setIs_delete(boolean is_delete) {
		this.is_delete = is_delete;
	}

	public boolean isIs_active() {
		return is_active;
	}

	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
	}

	public Integer getCompany_id() {
		return company_id;
	}

	public void setCompany_id(Integer company_id) {
		this.company_id = company_id;
	}

	public Integer getCountry_id() {
		return country_id;
	}

	public void setCountry_id(Integer country_id) {
		this.country_id = country_id;
	}

	public Integer getState_id() {
		return state_id;
	}

	public void setState_id(Integer state_id) {
		this.state_id = state_id;
	}

	public Integer getField_id() {
		return field_id;
	}

	public void setField_id(Integer field_id) {
		this.field_id = field_id;
	}

	public String getField_name() {
		return field_name;
	}

	public void setField_name(String field_name) {
		this.field_name = field_name;
	}

	public CDistrictViewModel(int district_id, String country_name, String state_name, String district_name,
	                          String district_short_name, String district_code, String company_name, String country_code,
	                          String state_code, boolean is_delete, boolean is_active, Integer company_id, Integer country_id,
	                          Integer state_id, Integer field_id, String field_name) {
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
		this.is_delete = is_delete;
		this.is_active = is_active;
		this.company_id = company_id;
		this.country_id = country_id;
		this.state_id = state_id;
		this.field_id = field_id;
		this.field_name = field_name;
	}

	public CDistrictViewModel() {
		super();
		// TODO Auto-generated constructor stub
	}


}
