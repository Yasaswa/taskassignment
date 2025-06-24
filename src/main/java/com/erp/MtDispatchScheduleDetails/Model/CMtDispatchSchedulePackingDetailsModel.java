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
@Table(name = "mt_dispatch_schedule_packing_details")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CMtDispatchSchedulePackingDetailsModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "dispatch_schedule_packing_id")
	private Integer dispatch_schedule_packing_id;
	private Integer dispatch_schedule_master_transaction_id;
	private Integer dispatch_schedule_details_transaction_id;
	private Integer yarn_packing_details_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String financial_year;
	private String dispatch_schedule_no;
	private String dispatch_schedule_date;
	private Integer dispatch_schedule_version;
	private String customer_order_no;
	private Integer sales_order_details_transaction_id;
	private String product_material_id;
	private String so_sr_no;
	private Integer sr_no;
	private Integer product_unit_id;
	private double item_dispatch_quantity;
	private double item_dispatch_weight;
	private double cone_weight;
	private double cone_quantity;
	private double per_packing_weight;
	private double packing_gross_weight;
	private double packing_net_weight;
	private double packing_quantity;
	private Integer packing_starting_no;
	private Integer packing_ending_no;
	private String lot_no;
	private String sub_lot_no;
	private Date yarn_packing_date;
	private Integer yarn_packing_types_id;
	private Integer production_count_id;
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
		}else {
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
