package com.erp.RawMaterial.Product_Rm_Commercial.Model;

import com.google.errorprone.annotations.Immutable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Immutable
@Entity
@Subselect("select * from smv_product_rm_commercial")
public class CProductRmCommercialViewModel {

	@Id
	private String company_name;
	private String company_branch_name;
	private String product_rm_id;
	private String product_rm_name;
	private String product_rm_code;
	private Double product_rm_gross_weight;
	private Double product_rm_net_weight;
	private Double product_rm_std_weight;
	private Double product_rm_volume;
	private Double product_rm_mrp;
	private Double product_rm_landed_price;
	private Integer lead_time;
	private Double product_rm_avg_price;
	private Double product_rm_std_profit_percent;
	private Double product_rm_std_discount_percent;
	private Double product_rm_moq;
	private Double product_rm_mpq;
	private Double product_rm_mov;
	private Double product_rm_eoq;
	private Double product_rm_min_stock_level;
	private Double product_rm_max_stock_level;
	private Double product_rm_reorder_stock_level;
	private Double product_rm_depreciation_percent;
	private Double purchase_tolerance_percent;
	private String product_rm_price_type;
	private String product_rm_abc_analysis;
	private boolean qa_required;
	private boolean test_certificate_required;
	private String routing_code;
	private String profit_center_name;
	private String profit_center_short_name;
	private String cost_center_name;
	private String cost_center_heads_name;
	private String cost_center_short_name;
	private String remark;
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
	private Integer cost_center_heads_id;
	private int profit_center_id;
	private String cost_center_id;

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

	public boolean isQa_required() {
		return qa_required;
	}

	public void setQa_required(boolean qa_required) {
		this.qa_required = qa_required;
	}

	public boolean isTest_certificate_required() {
		return test_certificate_required;
	}

	public void setTest_certificate_required(boolean test_certificate_required) {
		this.test_certificate_required = test_certificate_required;
	}

	/* Added By Dipti (ERP DB Testing 1.1) */

	//	@Id
	//	private int product_rm_commercial_id;
	//	private String product_rm_name;
	//	private String product_rm_code;
	//	private String company_name;
	//	private String company_branch_name;
	//	private Double product_rm_gross_weight;
	//	private Double product_rm_net_weight;
	//	private Double product_rm_std_weight;
	//	private Double product_rm_volume;
	//	private Double product_rm_mrp;
	//	private Double product_rm_landed_price;
	//	private Integer lead_time;
	//	private Double product_rm_avg_price;
	//	private Double product_rm_std_profit_percent;
	//	private Double product_rm_std_discount_percent;
	//	private Double product_rm_moq;
	//	private Double product_rm_mpq;
	//	private Double product_rm_mov;
	//	private Double product_rm_eoq;
	//	private Double product_rm_min_stock_level;
	//	private Double product_rm_max_stock_level;
	//	private Double product_rm_reorder_stock_level;
	//	private Double product_rm_depreciation_percent;
	//	private Double purchase_tolerance_percent;
	//	private String product_rm_price_type;
	//	private String product_rm_abc_analysis;
	//	private boolean qa_required;
	//	private boolean test_certificate_required;
	//	private String remark;
	//	private String Active;
	//	private String Deleted;
	//	private boolean is_active;	
	//	private boolean is_delete;
	//	private String created_by;
	//	private Date created_on;
	//	private String modified_by;
	//	private Date modified_on;
	//	private String deleted_by;
	//	private Date deleted_on;
	//	private Integer company_id;
	//	private Integer company_branch_id;
	//	private Integer product_rm_id;

	/* Added By Dipti (ERP DB Testing 1.1) */


}
