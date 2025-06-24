package com.erp.MtSalesOrderMasterTrading.Model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "mt_sales_order_payment_terms_trading")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CMtSalesOrderPaymentTermsTradingModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sales_order_payment_terms_transaction_id")
	private int sales_order_payment_terms_transaction_id;
	private Integer company_id;
	private Integer company_branch_id;
	private Integer sales_order_master_transaction_id;
	private Integer sales_order_details_transaction_id;
	private String sales_order_no;
	private String sales_order_date;
	private Integer sales_order_version;
	private String customer_order_no;
	private String customer_order_Date;
	private Integer payment_terms_id;
	private String payment_terms_name;
	private String payment_terms_days;
	private Integer payment_terms_grace_days;
	private String payment_terms_Milestome;
	private String payment_percent;
	private double payment_expected_value;
	private String payment_expected_date;
	private String payment_receipt_flag;
	private String payment_receipt_transaction_id;
	private String payment_receipt_date;
	private String remark;
	private boolean is_active;
	private boolean is_delete;
	@Column(name = "created_by", updatable = false)
	private String created_by;
	@CreationTimestamp
	@Column(name = "created_on", updatable = false)
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;


	public int getSales_order_payment_terms_transaction_id() {
		return sales_order_payment_terms_transaction_id;
	}

	public void setSales_order_payment_terms_transaction_id(int sales_order_payment_terms_transaction_id) {
		this.sales_order_payment_terms_transaction_id = sales_order_payment_terms_transaction_id;
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

	public Integer getSales_order_master_transaction_id() {
		return sales_order_master_transaction_id;
	}

	public void setSales_order_master_transaction_id(Integer sales_order_master_transaction_id) {
		this.sales_order_master_transaction_id = sales_order_master_transaction_id;
	}

	public Integer getSales_order_details_transaction_id() {
		return sales_order_details_transaction_id;
	}

	public void setSales_order_details_transaction_id(Integer sales_order_details_transaction_id) {
		this.sales_order_details_transaction_id = sales_order_details_transaction_id;
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

	public Integer getPayment_terms_id() {
		return payment_terms_id;
	}

	public void setPayment_terms_id(Integer payment_terms_id) {
		this.payment_terms_id = payment_terms_id;
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

	public double getPayment_expected_value() {
		return payment_expected_value;
	}

	public void setPayment_expected_value(double payment_expected_value) {
		this.payment_expected_value = payment_expected_value;
	}

	public String getPayment_expected_date() {
		return payment_expected_date;
	}

	public void setPayment_expected_date(String payment_expected_date) {

		if (payment_expected_date == null || payment_expected_date.isEmpty()) {
			this.payment_expected_date = null;
		} else {
			this.payment_expected_date = payment_expected_date;
		}
	}

	public String getPayment_receipt_flag() {
		return payment_receipt_flag;
	}

	public void setPayment_receipt_flag(String payment_receipt_flag) {
		this.payment_receipt_flag = payment_receipt_flag;
	}

	public String getPayment_receipt_transaction_id() {
		return payment_receipt_transaction_id;
	}

	public void setPayment_receipt_transaction_id(String payment_receipt_transaction_id) {
		this.payment_receipt_transaction_id = payment_receipt_transaction_id;
	}

	public String getPayment_receipt_date() {
		return payment_receipt_date;
	}

	public void setPayment_receipt_date(String payment_receipt_date) {
		if (payment_receipt_date == null || payment_receipt_date.isEmpty()) {
			this.payment_receipt_date = null;
		} else {
			this.payment_receipt_date = payment_receipt_date;
		}
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


	public CMtSalesOrderPaymentTermsTradingModel(int sales_order_payment_terms_transaction_id, Integer company_id,
	                                             Integer company_branch_id, Integer sales_order_master_transaction_id,
	                                             Integer sales_order_details_transaction_id, String sales_order_no, String sales_order_date,
	                                             Integer sales_order_version, String customer_order_no, String customer_order_Date, Integer payment_terms_id,
	                                             String payment_terms_name, String payment_terms_days, Integer payment_terms_grace_days,
	                                             String payment_terms_Milestome, String payment_percent, double payment_expected_value,
	                                             String payment_expected_date, String payment_receipt_flag, String payment_receipt_transaction_id,
	                                             String payment_receipt_date, String remark, boolean is_active, boolean is_delete, String created_by,
	                                             Date created_on, String modified_by, Date modified_on, String deleted_by, Date deleted_on) {
		super();
		this.sales_order_payment_terms_transaction_id = sales_order_payment_terms_transaction_id;
		this.company_id = company_id;
		this.company_branch_id = company_branch_id;
		this.sales_order_master_transaction_id = sales_order_master_transaction_id;
		this.sales_order_details_transaction_id = sales_order_details_transaction_id;
		this.sales_order_no = sales_order_no;
		this.sales_order_date = sales_order_date;
		this.sales_order_version = sales_order_version;
		this.customer_order_no = customer_order_no;
		this.customer_order_Date = customer_order_Date;
		this.payment_terms_id = payment_terms_id;
		this.payment_terms_name = payment_terms_name;
		this.payment_terms_days = payment_terms_days;
		this.payment_terms_grace_days = payment_terms_grace_days;
		this.payment_terms_Milestome = payment_terms_Milestome;
		this.payment_percent = payment_percent;
		this.payment_expected_value = payment_expected_value;
		this.payment_expected_date = payment_expected_date;
		this.payment_receipt_flag = payment_receipt_flag;
		this.payment_receipt_transaction_id = payment_receipt_transaction_id;
		this.payment_receipt_date = payment_receipt_date;
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

	public CMtSalesOrderPaymentTermsTradingModel() {
		super();
	}

}
