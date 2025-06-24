package com.erp.MtDispatchChallanDetails.Model;

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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "mt_dispatch_challan_packing_details")
public class CMtDispatchChallanPackingDetailsModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "dispatch_challan_packing_id")
	private Integer dispatch_challan_packing_id;
	private Integer dispatch_challan_master_transaction_id;
	private Integer yarn_packing_details_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String financial_year;
	private String dispatch_challan_no;
	private String dispatch_challan_date;
	private Integer dispatch_challan_version;
	private String customer_order_no;
	private Integer sales_order_details_transaction_id;
	private String product_material_id;
	private String so_sr_no;
	private Integer sr_no;
	private Integer product_unit_id;
	private Double item_dispatch_quantity;
	private Double item_dispatch_weight;
	private Double cone_weight;
	private Double cone_quantity;
	private Double per_packing_weight;
	private Double packing_gross_weight;
	private Double packing_net_weight;
	private Double packing_quantity;
	private Integer packing_starting_no;
	private Integer packing_ending_no;
	private String lot_no;
	private String sub_lot_no;
	private String yarn_packing_date;
	private Integer yarn_packing_types_id;
	private Integer production_count_id;
	private Integer dispatch_schedule_packing_id;
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
	public String getDispatch_challan_date() {
		return dispatch_challan_date;
	}
	public void setDispatch_challan_date(String dispatch_challan_date) {
		
		if (dispatch_challan_date == null || dispatch_challan_date.isEmpty()) {
			this.dispatch_challan_date = null;
		}else {
			this.dispatch_challan_date = dispatch_challan_date;
		}
	}
	
	
	
}
