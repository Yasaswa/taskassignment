package com.erp.XtWeavingProductionWarpingMaster.Model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Subselect;
import org.springframework.data.annotation.Immutable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Immutable
@Entity
@Table(name = "xtv_weaving_production_warping_material")
public class CXtWeavingProductionWarpingMaterialViewModel {

	@Id
	private int weaving_production_warping_material_id;
	private String weaving_production_set_no;
	private String warping_production_order_no;
	private String warping_production_code;
	private String warping_production_date;
	private String product_material_name;
	private String plant_name;
	private String product_material_unit_name;
	private String production_section_name;
	private String production_sub_section_name;
	private String shift;
	private Integer prod_month;
	private Integer prod_year;
	private double product_material_quantity;
	private double consumption_quantity;
	private double stock_quantity;
	private double product_material_balance_quantity;
	private String material_status_remark;
	private String material_status_desc;
	private String material_status;
	private String Active;
	private String Deleted;
	private boolean is_active;
	private boolean is_delete;
	private String created_by;
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;
	private String financial_year;
	private Integer company_id;
	private Integer company_branch_id;
	private Integer weaving_production_warping_details_id;
	private Integer weaving_production_warping_master_id;
	private Integer product_material_unit_id;
	private String product_material_id;
	private Integer plant_id;
	private Integer section_id;
	private Integer sub_section_id;
	@Transient
	private List<Object> consumptionQtyInfo;
	private Integer field_id;
	private String field_name;
	
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
