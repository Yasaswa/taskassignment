package com.erp.PtPurchaseDetails.Model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "pt_purchase_order_terms")
public class CPtPurchaseOrderTermsModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "purchase_order_terms_transaction_id")
	private int purchase_order_terms_transaction_id;
	private Integer company_id;
	private Integer company_branch_id;
	private Integer purchase_order_master_transaction_id;
	private Integer purchase_order_details_transaction_id;
	private String purchase_order_no;
	private String purchase_order_date;
	private Integer purchase_order_version;
	private String customer_order_no;
	private String customer_order_Date;
	private Integer purchase_order_terms_parameters_id;
	private String purchase_order_terms_parameters_name;
	private String purchase_order_terms_parameters_value;
	private String purchase_order_terms_parameters_expected_value;
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

	public int getPurchase_order_terms_transaction_id() {
		return purchase_order_terms_transaction_id;
	}

	public void setPurchase_order_terms_transaction_id(int purchase_order_terms_transaction_id) {
		this.purchase_order_terms_transaction_id = purchase_order_terms_transaction_id;
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

	public Integer getPurchase_order_master_transaction_id() {
		return purchase_order_master_transaction_id;
	}

	public void setPurchase_order_master_transaction_id(Integer purchase_order_master_transaction_id) {
		this.purchase_order_master_transaction_id = purchase_order_master_transaction_id;
	}

	public Integer getPurchase_order_details_transaction_id() {
		return purchase_order_details_transaction_id;
	}

	public void setPurchase_order_details_transaction_id(Integer purchase_order_details_transaction_id) {
		this.purchase_order_details_transaction_id = purchase_order_details_transaction_id;
	}

	public String getPurchase_order_no() {
		return purchase_order_no;
	}

	public void setPurchase_order_no(String purchase_order_no) {
		this.purchase_order_no = purchase_order_no;
	}

	public String getPurchase_order_date() {
		return purchase_order_date;
	}

	public void setPurchase_order_date(String purchase_order_date) {
		if (purchase_order_date == null || purchase_order_date.isEmpty()) {
			this.purchase_order_date = null;
		} else {
			this.purchase_order_date = purchase_order_date;
		}
	}

	public Integer getPurchase_order_version() {
		return purchase_order_version;
	}

	public void setPurchase_order_version(Integer purchase_order_version) {
		this.purchase_order_version = purchase_order_version;
	}

	public String getCustomer_order_no() {
		return customer_order_no;
	}

	public void setCustomer_order_no(String customer_order_no) {
		this.customer_order_no = customer_order_no;
	}

	public String getCustomer_order_Date() {
		return customer_order_Date;
	}

	public void setCustomer_order_Date(String customer_order_Date) {
		if (customer_order_Date == null || customer_order_Date.isEmpty()) {
			this.customer_order_Date = null;
		} else {
			this.customer_order_Date = customer_order_Date;
		}
	}

	public Integer getPurchase_order_terms_parameters_id() {
		return purchase_order_terms_parameters_id;
	}

	public void setPurchase_order_terms_parameters_id(Integer purchase_order_terms_parameters_id) {
		this.purchase_order_terms_parameters_id = purchase_order_terms_parameters_id;
	}

	public String getPurchase_order_terms_parameters_name() {
		return purchase_order_terms_parameters_name;
	}

	public void setPurchase_order_terms_parameters_name(String purchase_order_terms_parameters_name) {
		this.purchase_order_terms_parameters_name = purchase_order_terms_parameters_name;
	}

	public String getPurchase_order_terms_parameters_value() {
		return purchase_order_terms_parameters_value;
	}

	public void setPurchase_order_terms_parameters_value(String purchase_order_terms_parameters_value) {
		this.purchase_order_terms_parameters_value = purchase_order_terms_parameters_value;
	}

	public String getPurchase_order_terms_parameters_expected_value() {
		return purchase_order_terms_parameters_expected_value;
	}

	public void setPurchase_order_terms_parameters_expected_value(
			String purchase_order_terms_parameters_expected_value) {
		this.purchase_order_terms_parameters_expected_value = purchase_order_terms_parameters_expected_value;
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

	public CPtPurchaseOrderTermsModel(int purchase_order_terms_transaction_id, Integer company_id,
	                                  Integer company_branch_id, Integer purchase_order_master_transaction_id,
	                                  Integer purchase_order_details_transaction_id, String purchase_order_no, String purchase_order_date,
	                                  Integer purchase_order_version, String customer_order_no, String customer_order_Date,
	                                  Integer purchase_order_terms_parameters_id, String purchase_order_terms_parameters_name,
	                                  String purchase_order_terms_parameters_value, String purchase_order_terms_parameters_expected_value,
	                                  String remark, boolean is_active, boolean is_delete, String created_by, Date created_on, String modified_by,
	                                  Date modified_on, String deleted_by, Date deleted_on) {
		super();
		this.purchase_order_terms_transaction_id = purchase_order_terms_transaction_id;
		this.company_id = company_id;
		this.company_branch_id = company_branch_id;
		this.purchase_order_master_transaction_id = purchase_order_master_transaction_id;
		this.purchase_order_details_transaction_id = purchase_order_details_transaction_id;
		this.purchase_order_no = purchase_order_no;
		this.purchase_order_date = purchase_order_date;
		this.purchase_order_version = purchase_order_version;
		this.customer_order_no = customer_order_no;
		this.customer_order_Date = customer_order_Date;
		this.purchase_order_terms_parameters_id = purchase_order_terms_parameters_id;
		this.purchase_order_terms_parameters_name = purchase_order_terms_parameters_name;
		this.purchase_order_terms_parameters_value = purchase_order_terms_parameters_value;
		this.purchase_order_terms_parameters_expected_value = purchase_order_terms_parameters_expected_value;
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

	public CPtPurchaseOrderTermsModel() {
		super();

	}
}
