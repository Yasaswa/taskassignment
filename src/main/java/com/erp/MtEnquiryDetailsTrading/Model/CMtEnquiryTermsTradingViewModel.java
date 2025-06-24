package com.erp.MtEnquiryDetailsTrading.Model;

import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
@Immutable
@Subselect("select * from  mtv_enquiry_terms_trading")
public class CMtEnquiryTermsTradingViewModel {

	@Id
	private int enquiry_terms_transaction_id;
	private String company_name;
	private String company_branch_name;
	private String enquiry_no;
	private String enquiry_date;
	private Integer enquiry_version;
	private String enquiry_terms_parameters_name;
	private String enquiry_terms_parameters_value;
	private String enquiry_terms_parameters_expected_value;
	private String remark;
	private boolean is_active;
	private boolean is_delete;
	private String created_by;
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;
	private Integer enquiry_terms_parameters_id;
	private Integer company_id;
	private Integer company_branch_id;
	private Integer enquiry_master_transaction_id;

	public int getEnquiry_terms_transaction_id() {
		return enquiry_terms_transaction_id;
	}

	public void setEnquiry_terms_transaction_id(int enquiry_terms_transaction_id) {
		this.enquiry_terms_transaction_id = enquiry_terms_transaction_id;
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

	public String getEnquiry_no() {
		return enquiry_no;
	}

	public void setEnquiry_no(String enquiry_no) {
		this.enquiry_no = enquiry_no;
	}

	public String getEnquiry_date() {
		return enquiry_date;
	}

	public void setEnquiry_date(String enquiry_date) {
		this.enquiry_date = enquiry_date;
	}

	public Integer getEnquiry_version() {
		return enquiry_version;
	}

	public void setEnquiry_version(Integer enquiry_version) {
		this.enquiry_version = enquiry_version;
	}

	public String getEnquiry_terms_parameters_name() {
		return enquiry_terms_parameters_name;
	}

	public void setEnquiry_terms_parameters_name(String enquiry_terms_parameters_name) {
		this.enquiry_terms_parameters_name = enquiry_terms_parameters_name;
	}

	public String getEnquiry_terms_parameters_value() {
		return enquiry_terms_parameters_value;
	}

	public void setEnquiry_terms_parameters_value(String enquiry_terms_parameters_value) {
		this.enquiry_terms_parameters_value = enquiry_terms_parameters_value;
	}

	public String getEnquiry_terms_parameters_expected_value() {
		return enquiry_terms_parameters_expected_value;
	}

	public void setEnquiry_terms_parameters_expected_value(String enquiry_terms_parameters_expected_value) {
		this.enquiry_terms_parameters_expected_value = enquiry_terms_parameters_expected_value;
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

	public Integer getEnquiry_terms_parameters_id() {
		return enquiry_terms_parameters_id;
	}

	public void setEnquiry_terms_parameters_id(Integer enquiry_terms_parameters_id) {
		this.enquiry_terms_parameters_id = enquiry_terms_parameters_id;
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

	public Integer getEnquiry_master_transaction_id() {
		return enquiry_master_transaction_id;
	}

	public void setEnquiry_master_transaction_id(Integer enquiry_master_transaction_id) {
		this.enquiry_master_transaction_id = enquiry_master_transaction_id;
	}

	public CMtEnquiryTermsTradingViewModel() {
		super();
	}

	public CMtEnquiryTermsTradingViewModel(int enquiry_terms_transaction_id, String company_name,
	                                       String company_branch_name, String enquiry_no, String enquiry_date, Integer enquiry_version,
	                                       String enquiry_terms_parameters_name, String enquiry_terms_parameters_value,
	                                       String enquiry_terms_parameters_expected_value, String remark, boolean is_active, boolean is_delete,
	                                       String created_by, Date created_on, String modified_by, Date modified_on, String deleted_by,
	                                       Date deleted_on, Integer enquiry_terms_parameters_id, Integer company_id, Integer company_branch_id,
	                                       Integer enquiry_master_transaction_id) {
		super();
		this.enquiry_terms_transaction_id = enquiry_terms_transaction_id;
		this.company_name = company_name;
		this.company_branch_name = company_branch_name;
		this.enquiry_no = enquiry_no;
		this.enquiry_date = enquiry_date;
		this.enquiry_version = enquiry_version;
		this.enquiry_terms_parameters_name = enquiry_terms_parameters_name;
		this.enquiry_terms_parameters_value = enquiry_terms_parameters_value;
		this.enquiry_terms_parameters_expected_value = enquiry_terms_parameters_expected_value;
		this.remark = remark;
		this.is_active = is_active;
		this.is_delete = is_delete;
		this.created_by = created_by;
		this.created_on = created_on;
		this.modified_by = modified_by;
		this.modified_on = modified_on;
		this.deleted_by = deleted_by;
		this.deleted_on = deleted_on;
		this.enquiry_terms_parameters_id = enquiry_terms_parameters_id;
		this.company_id = company_id;
		this.company_branch_id = company_branch_id;
		this.enquiry_master_transaction_id = enquiry_master_transaction_id;
	}
}
