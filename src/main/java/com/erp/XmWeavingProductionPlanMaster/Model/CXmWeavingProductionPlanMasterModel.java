package com.erp.XmWeavingProductionPlanMaster.Model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "xm_weaving_production_spec_sheet_master")
public class CXmWeavingProductionPlanMasterModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "weaving_production_plan_master_id")
	private int weaving_production_plan_master_id;
	private String machine_type_id;
	private String product_id;
	private Integer product_fg_stock_unit_id;
	private Integer warp_yarn_count_id;
	private Integer weft_yarn_count_id;
	private double warp_yarn_count;
	private double weft_yarn_count;
	private double warp_crimp;
	private double weft_crimp;
	private double warp_waste;
	private double weft_waste;
	private Integer epi;
	private Integer ppi;
	private Integer width;
	private double warp_glm;
	private double weft_glm;
	private double warp_req_per_mtr;
	private double weft_req_per_mtr;
	private double gsm;
	private double glm;
	private double glm_without_size;
	private double target_efficiency;
	private double target_rpm;
	private Integer no_of_ends;
	private Integer sort_no;
	private Integer company_id;
	private boolean is_active = Boolean.TRUE;
	private boolean is_delete =  Boolean.FALSE;
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

	

}
