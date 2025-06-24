package com.erp.FinishGoods.SmProductFgCommercial.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Immutable
@Subselect("select * from  smv_product_fg_commercial")
public class CSmProductFgCommercialViewModel {

	@Id
	private Integer product_fg_commercial_id;
	private String product_fg_id;
	private int company_name;
	private String company_branch_name;
	private String product_fg_code;
	private String product_fg_name;
	private double product_fg_gross_weight;
	private double product_fg_net_weight;
	private double product_fg_std_weight;
	private double product_fg_volume;
	private double product_fg_mrp;
	private double product_fg_landed_price;
	private double product_fg_avg_price;
	private double product_fg_std_profit_percent;
	private double product_fg_std_discount_percent;
	private double product_fg_moq;
	private double product_fg_mpq;
	private double product_fg_mov;
	private double product_fg_eoq;
	private double product_fg_min_stock_level;
	private double product_fg_max_stock_level;
	private double product_fg_reorder_stock_level;
	private double product_fg_depriciation_percent;
	private double sales_tolerance_percent;
	private String product_fg_prifg_type;
	private String product_fg_abc_analysis;
	private boolean qa_required;
	private boolean test_certificate_required;
	private String routing_code;
	private String profit_center_name;
	private String profit_center_short_name;
	private String cost_center_name;
	private String cost_center_short_name;
	private String remark;
	//  Added by mohit and use for db 1.1
//	private String Active;
//	private String Deleted;
	private boolean is_active;
	private boolean is_delete;
	private String created_by;
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;
	private Integer company_id;
	private Integer company_branch_id;
	private int profit_center_id;
	private int cost_center_id;
	private String field_name;
	private Integer field_id;

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
