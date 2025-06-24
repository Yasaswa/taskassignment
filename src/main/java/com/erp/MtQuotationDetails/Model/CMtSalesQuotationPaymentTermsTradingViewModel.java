package com.erp.MtQuotationDetails.Model;

import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
@Immutable
@Subselect("select * from mtv_sales_quotation_payment_terms_trading")
public class CMtSalesQuotationPaymentTermsTradingViewModel {

	@Id
	private int quotation_payment_terms_transaction_id;
	private String quotation_no;
	private String quotation_date;
	private Integer quotation_version;
	private String payment_terms_name;
	private String payment_terms_days;
	private Integer payment_terms_grace_days;
	private String payment_terms_Milestome;
	private String payment_percent;
	private String remark;
	private String company_name;
	private String company_branch_name;
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
	private Integer quotation_master_transaction_id;
	private Integer quotation_details_transaction_id;
	private Integer payment_terms_id;

	public int getQuotation_payment_terms_transaction_id() {
		return quotation_payment_terms_transaction_id;
	}

	public void setQuotation_payment_terms_transaction_id(int quotation_payment_terms_transaction_id) {
		this.quotation_payment_terms_transaction_id = quotation_payment_terms_transaction_id;
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

	public String getPayment_terms_name() {
		return payment_terms_name;
	}

	public void setPayment_terms_name(String payment_terms_name) {
		this.payment_terms_name = payment_terms_name;
	}

	public String getPayment_terms_days() {
		return payment_terms_days;
	}

	public void setPayment_terms_days(String payment_terms_days) {
		this.payment_terms_days = payment_terms_days;
	}

	public Integer getPayment_terms_grace_days() {
		return payment_terms_grace_days;
	}

	public void setPayment_terms_grace_days(Integer payment_terms_grace_days) {
		this.payment_terms_grace_days = payment_terms_grace_days;
	}

	public String getPayment_terms_Milestome() {
		return payment_terms_Milestome;
	}

	public void setPayment_terms_Milestome(String payment_terms_Milestome) {
		this.payment_terms_Milestome = payment_terms_Milestome;
	}

	public String getPayment_percent() {
		return payment_percent;
	}

	public void setPayment_percent(String payment_percent) {
		this.payment_percent = payment_percent;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getCompany_branch_name() {
		return company_branch_name;
	}

	public void setCompany_branch_name(String company_branch_name) {
		this.company_branch_name = company_branch_name;
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

	public Integer getPayment_terms_id() {
		return payment_terms_id;
	}

	public void setPayment_terms_id(Integer payment_terms_id) {
		this.payment_terms_id = payment_terms_id;
	}


	public CMtSalesQuotationPaymentTermsTradingViewModel(int quotation_payment_terms_transaction_id,
	                                                     String quotation_no, String quotation_date, Integer quotation_version, String payment_terms_name,
	                                                     String payment_terms_days, Integer payment_terms_grace_days, String payment_terms_Milestome,
	                                                     String payment_percent, String remark, String company_name, String company_branch_name, boolean is_active,
	                                                     boolean is_delete, String created_by, Date created_on, String modified_by, Date modified_on,
	                                                     String deleted_by, Date deleted_on, Integer company_id, Integer company_branch_id,
	                                                     Integer quotation_master_transaction_id, Integer quotation_details_transaction_id,
	                                                     Integer payment_terms_id) {
		super();
		this.quotation_payment_terms_transaction_id = quotation_payment_terms_transaction_id;
		this.quotation_no = quotation_no;
		this.quotation_date = quotation_date;
		this.quotation_version = quotation_version;
		this.payment_terms_name = payment_terms_name;
		this.payment_terms_days = payment_terms_days;
		this.payment_terms_grace_days = payment_terms_grace_days;
		this.payment_terms_Milestome = payment_terms_Milestome;
		this.payment_percent = payment_percent;
		this.remark = remark;
		this.company_name = company_name;
		this.company_branch_name = company_branch_name;
		this.is_active = is_active;
		this.is_delete = is_delete;
		this.created_by = created_by;
		this.created_on = created_on;
		this.modified_by = modified_by;
		this.modified_on = modified_on;
		this.deleted_by = deleted_by;
		this.deleted_on = deleted_on;
		this.company_id = company_id;
		this.company_branch_id = company_branch_id;
		this.quotation_master_transaction_id = quotation_master_transaction_id;
		this.quotation_details_transaction_id = quotation_details_transaction_id;
		this.payment_terms_id = payment_terms_id;
	}

	public CMtSalesQuotationPaymentTermsTradingViewModel() {
		super();
	}


}
