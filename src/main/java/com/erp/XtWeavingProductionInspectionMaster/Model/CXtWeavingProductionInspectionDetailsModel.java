package com.erp.XtWeavingProductionInspectionMaster.Model;

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
@Table(name = "xt_weaving_production_inspection_details")
public class CXtWeavingProductionInspectionDetailsModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "weaving_production_inspection_details_id")
	private Integer weaving_production_inspection_details_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String financial_year;
	private Integer book_type_id;

	private Integer weaving_production_inspection_master_id;
	private String inspection_order_no;
	private String inspection_production_code;
	private String inspection_production_date;
	private Integer prod_month;
	private Integer prod_year;
//	private String total_no_of_ends;
	private Integer plant_id;
	private String shift;
	private String inspection_production_set_no;
	private String sizing_beam_no;
	private double product_in_meter;
	private double inspection_mtr;
	private double product_pick;
	private double difference;
	private double width;
	private String sort_no;
	private Integer roll_no;
	private double average;
	private double weight;
	private String inspection_production_status;
	private String status_remark;
	private String production_operator_name;
	private String product_material_id;
	private String product_material_name;
	private String style;
	private Integer section_id;
	private Integer godown_id;
	private Integer godown_section_id;
	private Integer godown_section_beans_id;
	private Integer sub_section_id;
	private Integer machine_id;
	private Double dispatch_quantity;
	private Double dispatch_weight;
	private String dispatch_date;
	private String dispatch_challan_no;
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
	private String stock_status;
	private String stock_status_description;
	private String approved_date;
	
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


	public void setApproved_date(String approved_date) {
		if (approved_date == null || approved_date.isEmpty()) {
			this.approved_date = null;
		} else {
			this.approved_date = approved_date;
		}
	}

}