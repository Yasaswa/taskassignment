package com.erp.MtDispatchScheduleDetails.Model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "mt_dispatch_schedule_inspection_details_trading")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CMtDispatchScheduleInspectionDetailsTradingModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "dispatch_schedule_inspection_details_transaction_id")
	private int dispatch_schedule_inspection_details_transaction_id;
	private Integer dispatch_schedule_master_transaction_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String financial_year;
	private String dispatch_schedule_no;
	private String dispatch_schedule_date;
	private Integer dispatch_schedule_version;
	private String sales_order_no;
	private String sales_order_Date;
	private String sales_order_version;
	private Integer customer_id;
	private String customer_order_no;
	private String customer_order_Date;
	private Integer sales_order_details_transaction_id;
	private String product_material_id;
	private Integer product_material_unit_id;
	private String inspection_production_code;
	private String inspection_order_no;
	private Date inspection_production_date;
	private Integer weaving_production_inspection_details_id;
	private String inspection_production_set_no;
	private double inspection_mtr;
	private double dispatch_quantity;
	private String sort_no;
	private Integer roll_no;
	private Integer godown_id;
	private String remark;
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

	public String getDispatch_schedule_date() {
		return dispatch_schedule_date;
	}

	public void setDispatch_schedule_date(String dispatch_schedule_date) {
		if (dispatch_schedule_date == null || dispatch_schedule_date.isEmpty()) {
			this.dispatch_schedule_date = null;
		} else {
			this.dispatch_schedule_date = dispatch_schedule_date;
		}
	}

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
