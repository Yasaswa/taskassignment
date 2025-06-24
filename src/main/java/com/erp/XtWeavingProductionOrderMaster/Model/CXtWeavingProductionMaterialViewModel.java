package com.erp.XtWeavingProductionOrderMaster.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Subselect;
import org.springframework.data.annotation.Immutable;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Immutable
@Entity
@Subselect("SELECT * FROM xtv_weaving_production_material")
public class CXtWeavingProductionMaterialViewModel {

	@Id
	private Integer production_order_material_id;
	private String production_order_no;
	private String production_order_date;
	private String production_sub_section_name;
	private String production_section_name;
	private String product_material_name;
	private String product_material_unit_name;
	private String product_material_code;
	private String product_material_tech_spect;
	private String set_no;
	private double product_material_quantity;
	private String production_section_short_name;
	private String production_sub_section_short_name;
	private double stock_quantity;
	private double product_material_balance_quantity;
	private double product_material_sz_balance_quantity;
	private double product_inspection_material_balance_quantity;
	private double product_loom_material_balance_quantity;
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
	private String company_name;
	private String company_branch_name;
	private String financial_year;
	private Integer company_id;
	private Integer company_branch_id;
	private Integer production_section_id;
	private Integer production_sub_section_id;
	private String product_material_id;
	private Integer weaving_production_order_id;
	private Integer product_material_unit_id;
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
