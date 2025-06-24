package com.erp.XtWeavingProductionInspectionMaster.Model;

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
@Subselect("select * from  xtv_weaving_production_inspection_master")
public class CXtWeavingProductionInspectionMasterViewModel {

	@Id
	private int weaving_production_inspection_master_id;
	private String inspection_production_date;
	private String inspection_production_code;
	private String inspection_production_set_no;
	private String plant_name;
	private Integer prod_month;
	private Integer prod_year;
	private String approved_by_name;
	private String production_section_name;
	private String production_sub_section_name;
	private String inspection_production_master_status_desc;
	private String inspection_production_master_status;
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
	private Integer plant_id;
	private Integer section_id;
	private Integer sub_section_id;
	private Integer approved_by_id;
	private String approved_date;
	private String stock_status;
	private String stock_status_description;


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
