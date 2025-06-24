package com.erp.XmWeavingProductionPlanMaster.Model;

import java.util.Date;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Immutable
@Data
@Entity
@Table(name = "xmv_weaving_production_spec_sheet_details")
public class CXmWeavingProductionSpecSheetDetailsViewModel {

	@Id
	private int weaving_production_plan_details_id;
	private String product_material_name;
	private String product_material_code;
	private String product_parameter_name;
	private String product_parameter_value;
	private String prod_count_name;
	private double picks_ends_per_mtr;
	private double actual_count;
	private double yarn_req_per_mtr;
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
	private Integer weaving_production_plan_master_id;
	private String product_id;
	private Integer company_id;
	private Integer company_branch_id;
	
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