package com.erp.XmWeavingProductionPlanMaster.Model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Table(name = "xmv_weaving_production_spec_sheet_summary")
public class CXmWeavingProductionPlanSummaryViewModel {
	
	@Id
	private int weaving_production_plan_master_id;
	private String product_type_name;
	private String product_type_group;
	private String product_fg_name;
	private String product_fg_tech_spect;
	private String product_fg_stock_unit_name;
	private double warp_yarn_count;
	private double weft_yarn_count;
	private String warp_yarn_count_name;
	private String weft_yarn_count_name;
	private double warp_crimp;
	private double weft_crimp;
	private double warp_waste;
	private double weft_waste;
	private double glm_without_size;
	private Integer epi;
	private Integer ppi;
	private double warp_req_per_mtr;
	private double weft_req_per_mtr;
	private double gsm;
	private double glm;
	private double warp_glm;
	private double weft_glm;
	private double target_efficiency;
	private double target_rpm;
	private Integer width;
	private Integer no_of_ends;
	private Integer sort_no;
	private String company_name;
	private String Active;
	private String Deleted;
	private boolean is_active;
	private boolean is_delete;
	private String created_by;
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private Date deleted_on;
	private Integer company_id;
	private String product_id;
	private Integer warp_yarn_count_id;
	private Integer weft_yarn_count_id;
	private String machine_type_id;
	private Integer product_fg_stock_unit_id;
	private Integer field_id;
	private Integer field_name;
	
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
