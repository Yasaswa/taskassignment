package com.erp.MtSalesOrderMasterTrading.Model;

import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
@Immutable
@Subselect("select * from  mtv_sales_order_terms_trading")
public class CMtSalesOrderTermsTradingViewModel {

	@Id
	private int sales_order_terms_transaction_id;
	private String company_name;
	private String company_branch_name;
	private String sales_order_no;
	private String sales_order_date;
	private Integer sales_order_version;
	private String customer_order_no;
	private String customer_order_Date;
	private String sales_order_terms_parameters_name;
	private String sales_order_terms_parameters_value;
	private String sales_order_terms_parameters_expected_value;
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
	private Integer sales_order_master_transaction_id;
	private Integer sales_order_details_transaction_id;
	private Integer sales_order_terms_parameters_id;

	public int getSales_order_terms_transaction_id() {
		return sales_order_terms_transaction_id;
	}

	public void setSales_order_terms_transaction_id(int sales_order_terms_transaction_id) {
		this.sales_order_terms_transaction_id = sales_order_terms_transaction_id;
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
		this.sales_order_date = sales_order_date;
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
		this.customer_order_Date = customer_order_Date;
	}

	public String getSales_order_terms_parameters_name() {
		return sales_order_terms_parameters_name;
	}

	public void setSales_order_terms_parameters_name(String sales_order_terms_parameters_name) {
		this.sales_order_terms_parameters_name = sales_order_terms_parameters_name;
	}

	public String getSales_order_terms_parameters_value() {
		return sales_order_terms_parameters_value;
	}

	public void setSales_order_terms_parameters_value(String sales_order_terms_parameters_value) {
		this.sales_order_terms_parameters_value = sales_order_terms_parameters_value;
	}

	public String getSales_order_terms_parameters_expected_value() {
		return sales_order_terms_parameters_expected_value;
	}

	public void setSales_order_terms_parameters_expected_value(String sales_order_terms_parameters_expected_value) {
		this.sales_order_terms_parameters_expected_value = sales_order_terms_parameters_expected_value;
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

	public Integer getSales_order_terms_parameters_id() {
		return sales_order_terms_parameters_id;
	}

	public void setSales_order_terms_parameters_id(Integer sales_order_terms_parameters_id) {
		this.sales_order_terms_parameters_id = sales_order_terms_parameters_id;
	}

	public CMtSalesOrderTermsTradingViewModel(int sales_order_terms_transaction_id, String company_name,
	                                          String company_branch_name, String sales_order_no, String sales_order_date, Integer sales_order_version,
	                                          String customer_order_no, String customer_order_Date, String sales_order_terms_parameters_name,
	                                          String sales_order_terms_parameters_value, String sales_order_terms_parameters_expected_value,
	                                          String remark, boolean is_active, boolean is_delete, String created_by, Date created_on, String modified_by,
	                                          Date modified_on, String deleted_by, Date deleted_on, Integer company_id, Integer company_branch_id,
	                                          Integer sales_order_master_transaction_id, Integer sales_order_details_transaction_id,
	                                          Integer sales_order_terms_parameters_id) {
		super();
		this.sales_order_terms_transaction_id = sales_order_terms_transaction_id;
		this.company_name = company_name;
		this.company_branch_name = company_branch_name;
		this.sales_order_no = sales_order_no;
		this.sales_order_date = sales_order_date;
		this.sales_order_version = sales_order_version;
		this.customer_order_no = customer_order_no;
		this.customer_order_Date = customer_order_Date;
		this.sales_order_terms_parameters_name = sales_order_terms_parameters_name;
		this.sales_order_terms_parameters_value = sales_order_terms_parameters_value;
		this.sales_order_terms_parameters_expected_value = sales_order_terms_parameters_expected_value;
		this.remark = remark;
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
		this.sales_order_master_transaction_id = sales_order_master_transaction_id;
		this.sales_order_details_transaction_id = sales_order_details_transaction_id;
		this.sales_order_terms_parameters_id = sales_order_terms_parameters_id;
	}

	public CMtSalesOrderTermsTradingViewModel() {
		super();

	}

}
