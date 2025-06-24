package com.erp.MtSalesOrderMasterTrading.Model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
@Immutable
@Subselect("select * from  mtv_sales_order_tax_summary")
public class CMtSalesOrderMasterTaxSummaryViewModel {

	@Id
	private int sales_order_tax_summary_transaction_id;
	private String financial_year;
	private String sales_order_type;
	private String sales_order_no;
	private String sales_order_date;
	private Integer sales_order_version;
	private double summary_taxable_amount;
	private double summary_cgst_percent;
	private double summary_cgst_total;
	private double summary_sgst_percent;
	private double summary_sgst_total;
	private double summary_igst_percent;
	private double summary_igst_total;
	private double summary_total_amount;
	private String hsn_sac_type;
	private String hsn_sac_code;
	private String hsn_sac_description;
	private String hsn_sac_rate;
	private String remark;
	private boolean is_active;
	private boolean is_delete;
	private String created_by;
	@CreationTimestamp
	@Column(name = "created_on", updatable = false)
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;
	;
	private Integer expected_branch_id;
	private Integer expected_branch_state_id;
	private Integer expected_branch_city_id;
	private Integer sales_order_master_transaction_id;
	private Integer company_id;
	private Integer company_branch_id;
	private Integer sales_order_type_id;
	private int hsn_code_id;


	public int getSales_order_tax_summary_transaction_id() {
		return sales_order_tax_summary_transaction_id;
	}


	public void setSales_order_tax_summary_transaction_id(int sales_order_tax_summary_transaction_id) {
		this.sales_order_tax_summary_transaction_id = sales_order_tax_summary_transaction_id;
	}


	public String getFinancial_year() {
		return financial_year;
	}


	public void setFinancial_year(String financial_year) {
		this.financial_year = financial_year;
	}


	public String getSales_order_type() {
		return sales_order_type;
	}


	public void setSales_order_type(String sales_order_type) {
		this.sales_order_type = sales_order_type;
	}


	public String getSales_order_no() {
		return sales_order_no;
	}


	public void setSales_order_no(String sales_order_no) {
		this.sales_order_no = sales_order_no;
	}


	public String getSales_order_date() {
		return sales_order_date;
	}


	public void setSales_order_date(String sales_order_date) {
		if (sales_order_date == null || sales_order_date.isEmpty()) {
			this.sales_order_date = null;
		} else {
			this.sales_order_date = sales_order_date;
		}
	}


	public Integer getSales_order_version() {
		return sales_order_version;
	}


	public void setSales_order_version(Integer sales_order_version) {
		this.sales_order_version = sales_order_version;
	}


	public double getSummary_taxable_amount() {
		return summary_taxable_amount;
	}


	public void setSummary_taxable_amount(double summary_taxable_amount) {
		this.summary_taxable_amount = summary_taxable_amount;
	}


	public double getSummary_cgst_percent() {
		return summary_cgst_percent;
	}


	public void setSummary_cgst_percent(double summary_cgst_percent) {
		this.summary_cgst_percent = summary_cgst_percent;
	}


	public double getSummary_cgst_total() {
		return summary_cgst_total;
	}


	public void setSummary_cgst_total(double summary_cgst_total) {
		this.summary_cgst_total = summary_cgst_total;
	}


	public double getSummary_sgst_percent() {
		return summary_sgst_percent;
	}


	public void setSummary_sgst_percent(double summary_sgst_percent) {
		this.summary_sgst_percent = summary_sgst_percent;
	}


	public double getSummary_sgst_total() {
		return summary_sgst_total;
	}


	public void setSummary_sgst_total(double summary_sgst_total) {
		this.summary_sgst_total = summary_sgst_total;
	}


	public double getSummary_igst_percent() {
		return summary_igst_percent;
	}


	public void setSummary_igst_percent(double summary_igst_percent) {
		this.summary_igst_percent = summary_igst_percent;
	}


	public double getSummary_igst_total() {
		return summary_igst_total;
	}


	public void setSummary_igst_total(double summary_igst_total) {
		this.summary_igst_total = summary_igst_total;
	}


	public double getSummary_total_amount() {
		return summary_total_amount;
	}


	public void setSummary_total_amount(double summary_total_amount) {
		this.summary_total_amount = summary_total_amount;
	}


	public String getHsn_sac_type() {
		return hsn_sac_type;
	}


	public void setHsn_sac_type(String hsn_sac_type) {
		this.hsn_sac_type = hsn_sac_type;
	}


	public String getHsn_sac_code() {
		return hsn_sac_code;
	}


	public void setHsn_sac_code(String hsn_sac_code) {
		this.hsn_sac_code = hsn_sac_code;
	}


	public String getHsn_sac_description() {
		return hsn_sac_description;
	}


	public void setHsn_sac_description(String hsn_sac_description) {
		this.hsn_sac_description = hsn_sac_description;
	}


	public String getHsn_sac_rate() {
		return hsn_sac_rate;
	}


	public void setHsn_sac_rate(String hsn_sac_rate) {
		this.hsn_sac_rate = hsn_sac_rate;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


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


	public String getCreated_by() {
		return created_by;
	}


	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}


	public Date getCreated_on() {
		return created_on;
	}


	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
	}


	public String getModified_by() {
		return modified_by;
	}


	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}


	public Date getModified_on() {
		return modified_on;
	}


	public void setModified_on(Date modified_on) {
		this.modified_on = modified_on;
	}


	public String getDeleted_by() {
		return deleted_by;
	}


	public void setDeleted_by(String deleted_by) {
		this.deleted_by = deleted_by;
	}


	public Date getDeleted_on() {
		return deleted_on;
	}


	public void setDeleted_on(Date deleted_on) {
		this.deleted_on = deleted_on;
	}


	public Integer getExpected_branch_id() {
		return expected_branch_id;
	}


	public void setExpected_branch_id(Integer expected_branch_id) {
		this.expected_branch_id = expected_branch_id;
	}


	public Integer getExpected_branch_state_id() {
		return expected_branch_state_id;
	}


	public void setExpected_branch_state_id(Integer expected_branch_state_id) {
		this.expected_branch_state_id = expected_branch_state_id;
	}


	public Integer getExpected_branch_city_id() {
		return expected_branch_city_id;
	}


	public void setExpected_branch_city_id(Integer expected_branch_city_id) {
		this.expected_branch_city_id = expected_branch_city_id;
	}


	public Integer getSales_order_master_transaction_id() {
		return sales_order_master_transaction_id;
	}


	public void setSales_order_master_transaction_id(Integer sales_order_master_transaction_id) {
		this.sales_order_master_transaction_id = sales_order_master_transaction_id;
	}


	public Integer getCompany_id() {
		return company_id;
	}


	public void setCompany_id(Integer company_id) {
		this.company_id = company_id;
	}


	public Integer getCompany_branch_id() {
		return company_branch_id;
	}


	public void setCompany_branch_id(Integer company_branch_id) {
		this.company_branch_id = company_branch_id;
	}


	public Integer getSales_order_type_id() {
		return sales_order_type_id;
	}


	public void setSales_order_type_id(Integer sales_order_type_id) {
		this.sales_order_type_id = sales_order_type_id;
	}


	public int getHsn_code_id() {
		return hsn_code_id;
	}


	public void setHsn_code_id(int hsn_code_id) {
		this.hsn_code_id = hsn_code_id;
	}

	public CMtSalesOrderMasterTaxSummaryViewModel(int sales_order_tax_summary_transaction_id, String financial_year,
	                                              String sales_order_type, String sales_order_no, String sales_order_date, Integer sales_order_version,
	                                              double summary_taxable_amount, double summary_cgst_percent, double summary_cgst_total,
	                                              double summary_sgst_percent, double summary_sgst_total, double summary_igst_percent,
	                                              double summary_igst_total, double summary_total_amount, String hsn_sac_type, String hsn_sac_code,
	                                              String hsn_sac_description, String hsn_sac_rate, String remark, boolean is_active, boolean is_delete,
	                                              String created_by, Date created_on, String modified_by, Date modified_on, String deleted_by,
	                                              Date deleted_on, Integer expected_branch_id, Integer expected_branch_state_id,
	                                              Integer expected_branch_city_id, Integer sales_order_master_transaction_id, Integer company_id,
	                                              Integer company_branch_id, Integer sales_order_type_id, int hsn_code_id) {
		super();
		this.sales_order_tax_summary_transaction_id = sales_order_tax_summary_transaction_id;
		this.financial_year = financial_year;
		this.sales_order_type = sales_order_type;
		this.sales_order_no = sales_order_no;
		this.sales_order_date = sales_order_date;
		this.sales_order_version = sales_order_version;
		this.summary_taxable_amount = summary_taxable_amount;
		this.summary_cgst_percent = summary_cgst_percent;
		this.summary_cgst_total = summary_cgst_total;
		this.summary_sgst_percent = summary_sgst_percent;
		this.summary_sgst_total = summary_sgst_total;
		this.summary_igst_percent = summary_igst_percent;
		this.summary_igst_total = summary_igst_total;
		this.summary_total_amount = summary_total_amount;
		this.hsn_sac_type = hsn_sac_type;
		this.hsn_sac_code = hsn_sac_code;
		this.hsn_sac_description = hsn_sac_description;
		this.hsn_sac_rate = hsn_sac_rate;
		this.remark = remark;
		this.is_active = is_active;
		this.is_delete = is_delete;
		this.created_by = created_by;
		this.created_on = created_on;
		this.modified_by = modified_by;
		this.modified_on = modified_on;
		this.deleted_by = deleted_by;
		this.deleted_on = deleted_on;
		this.expected_branch_id = expected_branch_id;
		this.expected_branch_state_id = expected_branch_state_id;
		this.expected_branch_city_id = expected_branch_city_id;
		this.sales_order_master_transaction_id = sales_order_master_transaction_id;
		this.company_id = company_id;
		this.company_branch_id = company_branch_id;
		this.sales_order_type_id = sales_order_type_id;
		this.hsn_code_id = hsn_code_id;
	}


	public CMtSalesOrderMasterTaxSummaryViewModel() {
		super();

	}


}
