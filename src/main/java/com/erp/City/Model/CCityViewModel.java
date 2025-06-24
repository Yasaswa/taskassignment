package com.erp.City.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Immutable
@Subselect("select * from cmv_city")
public class CCityViewModel {


	//Testing 1.1 Create by Dipti (changed column sequence and added some fields)
	@Id
	private int city_id;
	private String city_name;
	private String city_short_name;
	private String city_pincode;
	private String region_name;
	private String district_name;
	private String district_short_name;
	private String district_code;
	private String State_name;
	private String State_short_name;
	private String state_code;
	private String country_name;
	private String country_short_name;
	private String country_code;
	private String Active;
	private String Deleted;
	private Boolean is_active = Boolean.TRUE;
	private Boolean is_delete = Boolean.FALSE;
	private String created_by;
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;
	private Integer country_id;
	private Integer state_id;
	private Integer district_id;
	private Integer field_id;
	private String field_name;

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


	//Testing 1.1 Create by Dipti (changed column sequence and added some fields)


}
