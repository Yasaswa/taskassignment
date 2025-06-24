package com.erp.XtSpinningProdSpinnPlanMaster.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "xt_spinning_prod_spinn_plan_master")
public class CXtSpinningProdSpinnPlanMasterModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "spinn_plan_id")
	private int spinn_plan_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String financial_year;
	private Integer plant_id;
	private String spinn_plan_code;
	private String spinn_plan_month;
	private String spinn_plan_year;
	private String spinn_plan_start_date;
	private String spinn_plan_end_date;
	private Integer customer_id;
	private Integer spinn_plan_created_by_id;
	private Integer spinn_plan_approved_by_id;
	private String spinn_plan_description;
	private String spinn_plan_avg_count;
	private String spinn_plan_status;
	private boolean is_active = Boolean.TRUE;
	private boolean is_delete = Boolean.FALSE;
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

	public String getSpinn_plan_start_date() {
		return spinn_plan_start_date;
	}

	public void setSpinn_plan_start_date(String spinn_plan_start_date) {

		if (spinn_plan_start_date == null || spinn_plan_start_date.isEmpty()) {
			this.spinn_plan_start_date = null;
		} else {
			this.spinn_plan_start_date = spinn_plan_start_date;
		}
	}

	public String getSpinn_plan_end_date() {
		return spinn_plan_end_date;
	}

	public void setSpinn_plan_end_date(String spinn_plan_end_date) {

		if (spinn_plan_end_date == null || spinn_plan_end_date.isEmpty()) {
			this.spinn_plan_end_date = null;
		} else {
			this.spinn_plan_end_date = spinn_plan_end_date;
		}
	}

}
