package com.erp.Common.AmListingFiltersPreferances.Model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Immutable
@Data
@Entity
@Subselect(value = "SELECT * FROM amv_listing_filters_preferances")
public class CAmListingFiltersPreferancesViewModel {
	
	@Id
	private int listing_filters_preferances_id;
	private String display_name;
	private String listing_navigation_url;
	private String report_duration;
	private String column_list;
	private String where_condition;
	private String report_type;
	private String company_name;
	private String Active;
	private String Deleted;
	private boolean is_active;
	private boolean is_delete;
	private Integer company_id;
	private Integer user_id;
	private Integer modules_forms_id;
	
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
