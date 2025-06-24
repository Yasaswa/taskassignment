package com.erp.RawMaterial.Product_Rm_Commercial.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sm_product_rm_commercial")
public class CProductRmCommercialModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_rm_commercial_id")
	private int product_rm_commercial_id;
	private int company_id;
	private int company_branch_id;
	private String product_rm_id;
	private double product_rm_gross_weight;
	private double product_rm_net_weight;
	private double product_rm_std_weight;
	private double product_rm_volume;
	private double product_rm_landed_price;
	private double product_rm_avg_price;
	private double product_rm_std_profit_percent;
	private double product_rm_std_discount_percent;
	private Integer lead_time;
	private double product_rm_mov;
	private double product_rm_eoq;
	private double product_rm_moq;
	private double product_rm_mpq;
	private double product_rm_min_stock_level;
	private double product_rm_max_stock_level;
	private double product_rm_reorder_stock_level;
	private double product_rm_depriciation_percent;
	private Double purchase_tolerance_percent;
	private Integer cost_center_heads_id;
	private int profit_center_id;
	private String cost_center_id;
	private String routing_code;
	private double product_rm_mrp;
	private String product_rm_price_type;
	private String product_rm_abc_analysis;
	private String remark;
	private boolean qa_required = Boolean.TRUE;
	private boolean test_certificate_required = Boolean.FALSE;
	private boolean is_active = Boolean.TRUE;
	private boolean is_delete = Boolean.FALSE;
	@CreationTimestamp
	@Column(name = "created_on", updatable = false)
	private Date created_on;
	@UpdateTimestamp
	private Date modified_on;
	private Date deleted_on;
	@Column(name = "created_by", updatable = false)
	private String created_by;
	private String modified_by;
	private String deleted_by;

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
}
