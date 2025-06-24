package com.erp.SmProductSrActivities.Model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Immutable
@Subselect("select * from  smv_product_sr_activities")
public class CSmProductSrActivitiesViewModel {

	@Id
	private Integer product_sr_activity_id;
	private String product_sr_name;
	private String product_sr_activity_name;
	private String product_sr_activity_description;
	private Double product_sr_activity_std_hour;
	private Integer product_service_activity_master_id;
	private String product_sr_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String field_id;
	private String field_name;
	private String Active;
	private String Deleted;
	private boolean is_active;
	private String created_by;
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private boolean is_delete;
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
