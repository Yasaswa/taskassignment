package com.erp.XtWeavingProductionLoomMaster.Model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "xt_weaving_production_loom_material")
public class CXtWeavingProductionLoomMaterialModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "weaving_production_loom_material_id")
	private Integer weaving_production_loom_material_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String financial_year;
	private Integer weaving_production_loom_details_id;
	private Integer weaving_production_loom_master_id;
	private Integer plant_id;
	private Integer section_id;
	private Integer sub_section_id;
	private String loom_production_code;
	private String loom_production_order_no;
	private String loom_production_date;
	private Integer prod_month;
	private Integer prod_year;
	private String shift;
	private String loom_production_set_no;
	private String product_material_id;
	private Integer product_material_unit_id;
	private double product_material_quantity;
	private double consumption_quantity;
	private String material_status;
	private String material_status_remark;
	private boolean is_active;
	private boolean is_delete;
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
	
	@Transient
	private List<Object> consumptionQtyInfo;
	
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
