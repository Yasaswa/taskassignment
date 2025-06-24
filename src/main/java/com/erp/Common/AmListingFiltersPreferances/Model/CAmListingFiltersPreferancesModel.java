package com.erp.Common.AmListingFiltersPreferances.Model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "am_listing_filters_preferances")
public class CAmListingFiltersPreferancesModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "listing_filters_preferances_id")
	private int listing_filters_preferances_id;
	private Integer company_id;
	private Integer user_id;
	private Integer modules_forms_id;
	private String report_duration;
	private String column_list;
	private String where_condition;
	private String report_type;
	private boolean is_active = Boolean.TRUE;
	private boolean is_delete =  Boolean.FALSE;
	private String remark;
	@Column(name = "created_by", updatable = false)
	private String created_by;
	@CreationTimestamp
	@Column(name = "created_on", updatable = false)
	private Date created_on;
	private String modified_by;
	@UpdateTimestamp
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;
	
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
