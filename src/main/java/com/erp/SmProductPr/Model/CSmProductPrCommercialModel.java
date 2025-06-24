package com.erp.SmProductPr.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "sm_product_pr_commercial")
public class CSmProductPrCommercialModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_pr_commercial_id")
	private int product_pr_commercial_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String product_pr_id;
	private Double product_pr_gross_weight;
	private Double product_pr_net_weight;
	private Double product_pr_std_weight;
	private Double product_pr_volume;
	private Double product_pr_mrp;
	private Double product_pr_landed_price;
	private Double product_pr_avg_price;
	private Double product_pr_std_profit_percent;
	private Double product_pr_std_discount_percent;
	private Double product_pr_moq;
	private Double product_pr_mov;
	private Double product_pr_eoq;
	private Double product_pr_mpq;
	private Double product_pr_min_stock_level;
	private Double product_pr_max_stock_level;
	private Double product_pr_reorder_stock_level;
	private Double product_pr_depreciation_percent;
	private Double sales_tolerance_percent;
	private String product_pr_price_type;
	private String product_pr_abc_analysis;
	private Boolean qa_required = Boolean.TRUE;
	private Boolean test_certificate_required = Boolean.FALSE;
	private String remark;
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

	public Boolean getQa_required() {
		return qa_required;
	}

	public void setQa_required(Boolean qa_required) {
		this.qa_required = qa_required;
	}

	public Boolean getTest_certificate_required() {
		return test_certificate_required;
	}

	public void setTest_certificate_required(Boolean test_certificate_required) {
		this.test_certificate_required = test_certificate_required;
	}


}
