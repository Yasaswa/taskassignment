package com.erp.MtQuotationDetails.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "mt_sales_quotation_terms_trading")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CMtSalesQuotationTermsTradingModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "quotation_terms_transaction_id")
	private int quotation_terms_transaction_id;
	private Integer company_id;
	private Integer company_branch_id;
	private Integer quotation_master_transaction_id;
	private Integer quotation_details_transaction_id;
	private String quotation_no;
	private String quotation_date;
	private Integer quotation_version;
	private Integer quotation_terms_parameters_id;
	private String quotation_terms_parameters_name;
	private String quotation_terms_parameters_value;
	private String quotation_terms_parameters_expected_value;
	private String remark;
	private boolean is_active = Boolean.TRUE;
	private boolean is_delete = Boolean.FALSE;
	@Column(name = "created_by", updatable = false)
	private String created_by;
	@CreationTimestamp
	@Column(name = "created_on", updatable = false)
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;

	public int getQuotation_terms_transaction_id() {
		return quotation_terms_transaction_id;
	}

	public void setQuotation_terms_transaction_id(int quotation_terms_transaction_id) {
		this.quotation_terms_transaction_id = quotation_terms_transaction_id;
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

	public Integer getQuotation_master_transaction_id() {
		return quotation_master_transaction_id;
	}

	public void setQuotation_master_transaction_id(Integer quotation_master_transaction_id) {
		this.quotation_master_transaction_id = quotation_master_transaction_id;
	}

	public Integer getQuotation_details_transaction_id() {
		return quotation_details_transaction_id;
	}

	public void setQuotation_details_transaction_id(Integer quotation_details_transaction_id) {
		this.quotation_details_transaction_id = quotation_details_transaction_id;
	}

	public String getQuotation_no() {
		return quotation_no;
	}

	public void setQuotation_no(String quotation_no) {
		this.quotation_no = quotation_no;
	}

	public String getQuotation_date() {
		return quotation_date;
	}

	public void setQuotation_date(String quotation_date) {
		this.quotation_date = quotation_date;
	}

	public Integer getQuotation_version() {
		return quotation_version;
	}

	public void setQuotation_version(Integer quotation_version) {
		this.quotation_version = quotation_version;
	}

	public Integer getQuotation_terms_parameters_id() {
		return quotation_terms_parameters_id;
	}

	public void setQuotation_terms_parameters_id(Integer quotation_terms_parameters_id) {
		this.quotation_terms_parameters_id = quotation_terms_parameters_id;
	}

	public String getQuotation_terms_parameters_name() {
		return quotation_terms_parameters_name;
	}

	public void setQuotation_terms_parameters_name(String quotation_terms_parameters_name) {
		this.quotation_terms_parameters_name = quotation_terms_parameters_name;
	}

	public String getQuotation_terms_parameters_value() {
		return quotation_terms_parameters_value;
	}

	public void setQuotation_terms_parameters_value(String quotation_terms_parameters_value) {
		this.quotation_terms_parameters_value = quotation_terms_parameters_value;
	}

	public String getQuotation_terms_parameters_expected_value() {
		return quotation_terms_parameters_expected_value;
	}

	public void setQuotation_terms_parameters_expected_value(String quotation_terms_parameters_expected_value) {
		this.quotation_terms_parameters_expected_value = quotation_terms_parameters_expected_value;
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

	public CMtSalesQuotationTermsTradingModel(int quotation_terms_transaction_id, Integer company_id,
	                                          Integer company_branch_id, Integer quotation_master_transaction_id,
	                                          Integer quotation_details_transaction_id, String quotation_no, String quotation_date,
	                                          Integer quotation_version, Integer quotation_terms_parameters_id, String quotation_terms_parameters_name,
	                                          String quotation_terms_parameters_value, String quotation_terms_parameters_expected_value, String remark,
	                                          boolean is_active, boolean is_delete, String created_by, Date created_on, String modified_by,
	                                          Date modified_on, String deleted_by, Date deleted_on) {
		super();
		this.quotation_terms_transaction_id = quotation_terms_transaction_id;
		this.company_id = company_id;
		this.company_branch_id = company_branch_id;
		this.quotation_master_transaction_id = quotation_master_transaction_id;
		this.quotation_details_transaction_id = quotation_details_transaction_id;
		this.quotation_no = quotation_no;
		this.quotation_date = quotation_date;
		this.quotation_version = quotation_version;
		this.quotation_terms_parameters_id = quotation_terms_parameters_id;
		this.quotation_terms_parameters_name = quotation_terms_parameters_name;
		this.quotation_terms_parameters_value = quotation_terms_parameters_value;
		this.quotation_terms_parameters_expected_value = quotation_terms_parameters_expected_value;
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

	public CMtSalesQuotationTermsTradingModel() {
		super();

	}
}
