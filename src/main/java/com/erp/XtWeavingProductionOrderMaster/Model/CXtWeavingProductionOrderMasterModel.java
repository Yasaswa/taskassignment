package com.erp.XtWeavingProductionOrderMaster.Model;

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
@Table(name = "xt_weaving_production_order")
public class CXtWeavingProductionOrderMasterModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "weaving_production_order_id")
	private int weaving_production_order_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String financial_year;
	private String product_material_id;
	private Integer production_section_id;
	private String sales_order_no;
	private Integer product_unit_id;
	private String set_no;
	private Integer no_of_ends;
	private double order_quantity;
	private double schedule_quantity;
	private String schedule_date;
	private String start_date;
	private String end_date;
	private String warping_order_no;
	private String sizing_order_no;
	private String weaving_order_no;
	private double actual_count;
	private String yarn_count;
	private String sort_no;
	private String weaving_order_status;
	private String status_remark;
	private String other_terms_conditions;
	private Integer approved_by_id;
	private String approved_date;
	private String order_type;
	private double sizing_job_rate;
	private String remark;
	private Double glm;
	private Double gsm;
	private boolean is_active = Boolean.TRUE;
	private boolean is_delete = Boolean.FALSE;
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

	public String getSchedule_date() {
		return schedule_date;
	}

	public void setSchedule_date(String schedule_date) {
		if (schedule_date == null || schedule_date.isEmpty()) {
			this.schedule_date = null;
		} else {
			this.schedule_date = schedule_date;
		}
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		if (start_date == null || start_date.isEmpty()) {
			this.start_date = null;
		} else {
			this.start_date = start_date;
		}
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		if (end_date == null || end_date.isEmpty()) {
			this.end_date = null;
		} else {
			this.end_date = end_date;
		}
	}

	public String getApproved_date() {
		return approved_date;
	}

	public void setApproved_date(String approved_date) {
		if (approved_date == null || approved_date.isEmpty()) {
			this.approved_date = null;
		} else {
			this.approved_date = approved_date;
		}

	}

}
