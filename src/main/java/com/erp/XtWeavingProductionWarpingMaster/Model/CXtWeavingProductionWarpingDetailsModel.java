package com.erp.XtWeavingProductionWarpingMaster.Model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.*;
import org.apache.lucene.queryparser.surround.query.SrndPrefixQuery;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.carrotsearch.hppc.Generated;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Entity
@Table(name = "xt_weaving_production_warping_details")
public class CXtWeavingProductionWarpingDetailsModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "weaving_production_warping_details_id")
	private int weaving_production_warping_details_id;
	private Integer company_id;
	private Integer company_branch_id;
	private Integer weaving_production_warping_master_id;
	private String warping_order_no;
	private String warping_production_date;
	private String warping_production_code;
	private String financial_year;
	private Integer prod_month;
	private Integer prod_year;
	private Integer plant_id;
	private String shift;
	private String weaving_production_set_no;
	private double actual_count;
	private double creel_ends;
//	private double no_of_creels;
//	private double total_pkg_used;
	private double weight_per_pkg;
	private double total_weight_issue_to_warping;
	private double t_ends;
	private double length;
//	private double net_weight;
//	private double exp_bottom;
//	private double act_bottom;
	private double breaks_per_million;
//	private double bottom_percent;
	private String warping_production_status;
	private String product_rm_id;
	private String yarn_count;
	private Integer production_operator_id;
	private Integer production_supervisor_id;
	private Integer section_id;
	private Integer sub_section_id;
	private Integer machine_id;
	private Integer godown_id;
	private String status_remark;
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
	private String beam_no;
	private int speed;
	private float cut_cones;
	private float guccha;
	private float thin_places;
	private float week_places;
	private float week_splice;
	private float sluff_off;
	private float slub_yarn;
	private float total_breaks;

	
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
	
	public String getWarping_production_date() {
		return warping_production_date;
	}
	public void setWarping_production_date(String warping_production_date) {
		if(warping_production_date == null || warping_production_date.isEmpty()) {
			this.warping_production_date = null;
		}else {
			this.warping_production_date = warping_production_date;	
		}
	}
	
	
	

}
