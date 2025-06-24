package com.erp.Country.Model;

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
@Subselect("select * from cmv_country")
public class CCountryViewModel {


	@Id
	private int country_id;
	private String country_name;
	private String country_short_name;
	private String country_code;
	private String company_name;
	private String Active;
	private String Deleted;
	private Boolean is_active = Boolean.TRUE;
	private Boolean is_delete = Boolean.FALSE;
	private Integer company_id;
	private Integer field_id;
	private String field_name;

	//Create By Dipti (Testing 1.1 ) Removed company_id and company_name
	//	private int country_id;
	//	private String country_name;
	//	private String country_short_name;
	//	private String country_code;
	//	private String Active;
	//	private String Deleted;
	//	private Boolean is_active = Boolean.TRUE;
	//	private Boolean is_delete = Boolean.FALSE;
	//	private Integer field_id;
	//	private String field_name;
	//Create By Dipti (Testing 1.1 ) Removed company_id and company_name

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


}
