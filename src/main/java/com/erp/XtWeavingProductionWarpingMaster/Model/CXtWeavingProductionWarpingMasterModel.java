package com.erp.XtWeavingProductionWarpingMaster.Model;

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
@Data
@Getter
@Setter
@Entity
@Table(name = "xt_weaving_production_warping_master")
public class CXtWeavingProductionWarpingMasterModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "weaving_production_warping_master_id")
	private int weaving_production_warping_master_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String financial_year;
	private String set_no;
	private String warping_production_code;
	private String warping_production_date;
	private Integer prod_month;
	private Integer prod_year;
	private String warping_production_master_status;
	private Integer plant_id;
	private float calculative_bottom_kg;
	private float calculative_bottom_percent;
	private float actual_bottom_kg;
	private float actual_bottom_percent;
	private float difference_bottom_kg;
	private float difference_bottom_percent;
	private float warping_issue_kg;
	private float warping_issue_quantity;
	private String batch_no;
	private Integer section_id;
	private Integer sub_section_id;
	private Integer supplier_id;
	private Integer production_supervisor_id;
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
	private Integer print_status;

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
