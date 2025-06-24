package com.erp.XtWeavingProductionSizingDetails.Model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Immutable
@Subselect("select * from  xtv_weaving_production_sizing_wastage")
public class CXtWeavingProductionSizingWastageViewModel {

	@Id
	private Integer weaving_production_sizing_wastage_id;
	private String sizing_production_code;
	private String shift;
	private Integer prod_month;
	private Integer prod_year;
	private Date sizing_production_date;
	private String plant_name;
	private String godown_name;
	private String production_sub_section_name;
	private String production_section_name;
	private String production_supervisor_name;
	private String machine_name;
	private String machine_short_name;
	private String production_wastage_types_name;
	private String production_wastage_types_type;
	private double wastage_quantity;
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
	private Integer weaving_production_sizing_master_id;
	private Integer plant_id;
	private Integer section_id;
	private Integer godown_id;
	private Integer sub_section_id;
	private Integer production_supervisor_id;
	private Integer machine_id;
	private Integer production_wastage_types_id;
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