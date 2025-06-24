package com.erp.FinishGoods.SmProductFgCommercial.Model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "sm_product_fg_commercial")
public class CSmProductFgCommercialModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_fg_commercial_id")
	private int product_fg_commercial_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String product_fg_id;
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
	private double product_fg_mov;
	private double product_fg_eoq;
	private double product_fg_mpq;
	private double product_fg_min_stock_level;
	private double product_fg_max_stock_level;
	private double product_fg_reorder_stock_level;
	private double product_fg_depriciation_percent;
	private double sales_tolerance_percent;
	private String product_fg_price_type;
	private String product_fg_abc_analysis;
	private boolean qa_required;
	private boolean test_certificate_required;
	private int profit_center_id;
	private String cost_center_id;
	private String routing_code;
	private String remark;
	private boolean is_active;
	private boolean is_delete;
	private String created_by;
	@CreationTimestamp
	@Column(name = "created_on", updatable = false)
	private Date created_on;
	private String modified_by;
	@UpdateTimestamp
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;

	public int getproduct_fg_commercial_id() {
		return product_fg_commercial_id;
	}

	public void setproduct_fg_commercial_id(int product_fg_commercial_id) {
		this.product_fg_commercial_id = product_fg_commercial_id;
	}

	public Integer getcompany_id() {
		return company_id;
	}

	public void setcompany_id(Integer company_id) {
		this.company_id = company_id;
	}

	public Integer getcompany_branch_id() {
		return company_branch_id;
	}

	public void setcompany_branch_id(Integer company_branch_id) {
		this.company_branch_id = company_branch_id;
	}

	public String getproduct_fg_id() {
		return product_fg_id;
	}

	public void setproduct_fg_id(String product_fg_id) {
		this.product_fg_id = product_fg_id;
	}

	public double getproduct_fg_gross_weight() {
		return product_fg_gross_weight;
	}

	public void setproduct_fg_gross_weight(double product_fg_gross_weight) {
		this.product_fg_gross_weight = product_fg_gross_weight;
	}

	public double getproduct_fg_net_weight() {
		return product_fg_net_weight;
	}

	public void setproduct_fg_net_weight(double product_fg_net_weight) {
		this.product_fg_net_weight = product_fg_net_weight;
	}

	public double getproduct_fg_std_weight() {
		return product_fg_std_weight;
	}

	public void setproduct_fg_std_weight(double product_fg_std_weight) {
		this.product_fg_std_weight = product_fg_std_weight;
	}

	public double getproduct_fg_volume() {
		return product_fg_volume;
	}

	public void setproduct_fg_volume(double product_fg_volume) {
		this.product_fg_volume = product_fg_volume;
	}

	public double getproduct_fg_mrp() {
		return product_fg_mrp;
	}

	public void setproduct_fg_mrp(double product_fg_mrp) {
		this.product_fg_mrp = product_fg_mrp;
	}

	public double getproduct_fg_landed_price() {
		return product_fg_landed_price;
	}

	public void setproduct_fg_landed_price(double product_fg_landed_price) {
		this.product_fg_landed_price = product_fg_landed_price;
	}

	public double getproduct_fg_avg_price() {
		return product_fg_avg_price;
	}

	public void setproduct_fg_avg_price(double product_fg_avg_price) {
		this.product_fg_avg_price = product_fg_avg_price;
	}

	public double getproduct_fg_std_profit_percent() {
		return product_fg_std_profit_percent;
	}

	public void setproduct_fg_std_profit_percent(double product_fg_std_profit_percent) {
		this.product_fg_std_profit_percent = product_fg_std_profit_percent;
	}

	public double getproduct_fg_std_discount_percent() {
		return product_fg_std_discount_percent;
	}

	public void setproduct_fg_std_discount_percent(double product_fg_std_discount_percent) {
		this.product_fg_std_discount_percent = product_fg_std_discount_percent;
	}

	public double getproduct_fg_moq() {
		return product_fg_moq;
	}

	public void setproduct_fg_moq(double product_fg_moq) {
		this.product_fg_moq = product_fg_moq;
	}

	public double getproduct_fg_mov() {
		return product_fg_mov;
	}

	public void setproduct_fg_mov(double product_fg_mov) {
		this.product_fg_mov = product_fg_mov;
	}

	public double getproduct_fg_eoq() {
		return product_fg_eoq;
	}

	public void setproduct_fg_eoq(double product_fg_eoq) {
		this.product_fg_eoq = product_fg_eoq;
	}

	public double getproduct_fg_mpq() {
		return product_fg_mpq;
	}

	public void setproduct_fg_mpq(double product_fg_mpq) {
		this.product_fg_mpq = product_fg_mpq;
	}

	public double getproduct_fg_min_stock_level() {
		return product_fg_min_stock_level;
	}

	public void setproduct_fg_min_stock_level(double product_fg_min_stock_level) {
		this.product_fg_min_stock_level = product_fg_min_stock_level;
	}

	public double getproduct_fg_max_stock_level() {
		return product_fg_max_stock_level;
	}

	public void setproduct_fg_max_stock_level(double product_fg_max_stock_level) {
		this.product_fg_max_stock_level = product_fg_max_stock_level;
	}

	public double getproduct_fg_reorder_stock_level() {
		return product_fg_reorder_stock_level;
	}

	public void setproduct_fg_reorder_stock_level(double product_fg_reorder_stock_level) {
		this.product_fg_reorder_stock_level = product_fg_reorder_stock_level;
	}

	public double getproduct_fg_depriciation_percent() {
		return product_fg_depriciation_percent;
	}

	public void setproduct_fg_depriciation_percent(double product_fg_depriciation_percent) {
		this.product_fg_depriciation_percent = product_fg_depriciation_percent;
	}

	public double getsales_tolerance_percent() {
		return sales_tolerance_percent;
	}

	public void setsales_tolerance_percent(double sales_tolerance_percent) {
		this.sales_tolerance_percent = sales_tolerance_percent;
	}

	public String getproduct_fg_price_type() {
		return product_fg_price_type;
	}

	public void setproduct_fg_price_type(String product_fg_price_type) {
		this.product_fg_price_type = product_fg_price_type;
	}

	public String getproduct_fg_abc_analysis() {
		return product_fg_abc_analysis;
	}

	public void setproduct_fg_abc_analysis(String product_fg_abc_analysis) {
		this.product_fg_abc_analysis = product_fg_abc_analysis;
	}

	public boolean getqa_required() {
		return qa_required;
	}

	public void setqa_required(boolean qa_required) {
		this.qa_required = qa_required;
	}

	public boolean gettest_certificate_required() {
		return test_certificate_required;
	}

	public void settest_certificate_required(boolean test_certificate_required) {
		this.test_certificate_required = test_certificate_required;
	}

	public String getremark() {
		return remark;
	}

	public void setremark(String remark) {
		this.remark = remark;
	}

	public boolean getis_active() {
		return is_active;
	}

	public void setis_active(boolean is_active) {
		this.is_active = is_active;
	}

	public boolean getis_delete() {
		return is_delete;
	}

	public void setis_delete(boolean is_delete) {
		this.is_delete = is_delete;
	}

	public String getcreated_by() {
		return created_by;
	}

	public void setcreated_by(String created_by) {
		this.created_by = created_by;
	}

	public Date getcreated_on() {
		return created_on;
	}

	public void setcreated_on(Date created_on) {
		this.created_on = created_on;
	}

	public String getmodified_by() {
		return modified_by;
	}

	public void setmodified_by(String modified_by) {
		this.modified_by = modified_by;
	}

	public Date getmodified_on() {
		return modified_on;
	}

	public void setmodified_on(Date modified_on) {
		this.modified_on = modified_on;
	}

	public String getdeleted_by() {
		return deleted_by;
	}

	public void setdeleted_by(String deleted_by) {
		this.deleted_by = deleted_by;
	}

	public Date getdeleted_on() {
		return deleted_on;
	}

	public void setdeleted_on(Date deleted_on) {
		this.deleted_on = deleted_on;
	}

	public CSmProductFgCommercialModel(int product_fg_commercial_id, Integer company_id, Integer company_branch_id,
	                                   String product_fg_id, double product_fg_gross_weight, double product_fg_net_weight,
	                                   double product_fg_std_weight, double product_fg_volume, double product_fg_mrp,
	                                   double product_fg_landed_price, double product_fg_avg_price, double product_fg_std_profit_percent,
	                                   double product_fg_std_discount_percent, double product_fg_moq, double product_fg_mov, double product_fg_eoq,
	                                   double product_fg_mpq, double product_fg_min_stock_level, double product_fg_max_stock_level,
	                                   double product_fg_reorder_stock_level, double product_fg_depriciation_percent,
	                                   double sales_tolerance_percent, String product_fg_price_type, String product_fg_abc_analysis,
	                                   boolean qa_required, boolean test_certificate_required, String remark, boolean is_active, boolean is_delete,
	                                   String created_by, Date created_on, String modified_by, Date modified_on, String deleted_by,
	                                   Date deleted_on) {
		super();
		this.product_fg_commercial_id = product_fg_commercial_id;
		this.company_id = company_id;
		this.company_branch_id = company_branch_id;
		this.product_fg_id = product_fg_id;
		this.product_fg_gross_weight = product_fg_gross_weight;
		this.product_fg_net_weight = product_fg_net_weight;
		this.product_fg_std_weight = product_fg_std_weight;
		this.product_fg_volume = product_fg_volume;
		this.product_fg_mrp = product_fg_mrp;
		this.product_fg_landed_price = product_fg_landed_price;
		this.product_fg_avg_price = product_fg_avg_price;
		this.product_fg_std_profit_percent = product_fg_std_profit_percent;
		this.product_fg_std_discount_percent = product_fg_std_discount_percent;
		this.product_fg_moq = product_fg_moq;
		this.product_fg_mov = product_fg_mov;
		this.product_fg_eoq = product_fg_eoq;
		this.product_fg_mpq = product_fg_mpq;
		this.product_fg_min_stock_level = product_fg_min_stock_level;
		this.product_fg_max_stock_level = product_fg_max_stock_level;
		this.product_fg_reorder_stock_level = product_fg_reorder_stock_level;
		this.product_fg_depriciation_percent = product_fg_depriciation_percent;
		this.sales_tolerance_percent = sales_tolerance_percent;
		this.product_fg_price_type = product_fg_price_type;
		this.product_fg_abc_analysis = product_fg_abc_analysis;
		this.qa_required = qa_required;
		this.test_certificate_required = test_certificate_required;
		this.remark = remark;
		this.is_active = is_active;
		this.is_delete = is_delete;
		this.created_by = created_by;
		this.created_on = created_on;
		this.modified_by = modified_by;
		this.modified_on = modified_on;
		this.deleted_by = deleted_by;
		this.deleted_on = deleted_on;
	}

	public CSmProductFgCommercialModel() {
		super();
	}

}
