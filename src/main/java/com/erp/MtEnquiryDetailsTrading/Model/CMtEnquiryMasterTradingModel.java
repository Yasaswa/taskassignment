package com.erp.MtEnquiryDetailsTrading.Model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "mt_enquiry_master_trading")
public class CMtEnquiryMasterTradingModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "enquiry_master_transaction_id")
	private int enquiry_master_transaction_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String financial_year;
	private Integer enquiry_type_id;
	private String enquiry_type;
	private String enquiry_life;
	private String enquiry_no;
	private Date enquiry_date;
	private Integer enquiry_version;
	private Integer customer_id;
	private String customer_contacts_ids;
	private Integer customer_state_id;
	private Integer customer_city_id;
	private Integer expected_branch_id;
	private Integer expected_branch_state_id;
	private Integer expected_branch_city_id;
	private Integer enquiry_by_id;
	private Integer assign_to_head_id;
	private Integer assign_to_id;
	private Integer department_id;
	private Integer approved_by_id;
	private Date approved_date;
	private Date overall_schedule_date;
	private Integer agent_id;
	private double agent_percent;
	private String enquiry_mail_sent_status;
	private String enquiry_status;
	private String status_remark;
	private String other_terms_conditions;
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

	public int getEnquiry_master_transaction_id() {
		return enquiry_master_transaction_id;
	}

	public void setEnquiry_master_transaction_id(int enquiry_master_transaction_id) {
		this.enquiry_master_transaction_id = enquiry_master_transaction_id;
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

	public String getFinancial_year() {
		return financial_year;
	}

	public void setFinancial_year(String financial_year) {
		this.financial_year = financial_year;
	}

	public Integer getEnquiry_type_id() {
		return enquiry_type_id;
	}

	public void setEnquiry_type_id(Integer enquiry_type_id) {
		this.enquiry_type_id = enquiry_type_id;
	}

	public String getEnquiry_type() {
		return enquiry_type;
	}

	public void setEnquiry_type(String enquiry_type) {
		this.enquiry_type = enquiry_type;
	}

	public String getEnquiry_life() {
		return enquiry_life;
	}

	public void setEnquiry_life(String enquiry_life) {
		this.enquiry_life = enquiry_life;
	}

	public String getEnquiry_no() {
		return enquiry_no;
	}

	public void setEnquiry_no(String enquiry_no) {
		this.enquiry_no = enquiry_no;
	}

	public Date getEnquiry_date() {
		return enquiry_date;
	}

	public void setEnquiry_date(Date enquiry_date) {
		this.enquiry_date = enquiry_date;
	}

	public Integer getEnquiry_version() {
		return enquiry_version;
	}

	public void setEnquiry_version(Integer enquiry_version) {
		this.enquiry_version = enquiry_version;
	}

	public Integer getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}

	public String getCustomer_contacts_ids() {
		return customer_contacts_ids;
	}

	public void setCustomer_contacts_ids(String customer_contacts_ids) {
		this.customer_contacts_ids = customer_contacts_ids;
	}

	public Integer getCustomer_state_id() {
		return customer_state_id;
	}

	public void setCustomer_state_id(Integer customer_state_id) {
		this.customer_state_id = customer_state_id;
	}

	public Integer getCustomer_city_id() {
		return customer_city_id;
	}

	public void setCustomer_city_id(Integer customer_city_id) {
		this.customer_city_id = customer_city_id;
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

	public Integer getEnquiry_by_id() {
		return enquiry_by_id;
	}

	public void setEnquiry_by_id(Integer enquiry_by_id) {
		this.enquiry_by_id = enquiry_by_id;
	}

	public Integer getAssign_to_head_id() {
		return assign_to_head_id;
	}

	public void setAssign_to_head_id(Integer assign_to_head_id) {
		this.assign_to_head_id = assign_to_head_id;
	}

	public Integer getAssign_to_id() {
		return assign_to_id;
	}

	public void setAssign_to_id(Integer assign_to_id) {
		this.assign_to_id = assign_to_id;
	}

	public Integer getApproved_by_id() {
		return approved_by_id;
	}

	public void setApproved_by_id(Integer approved_by_id) {
		this.approved_by_id = approved_by_id;
	}

	public Integer getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(Integer department_id) {
		this.department_id = department_id;
	}

	public Date getApproved_date() {
		return approved_date;
	}

	public void setApproved_date(Date approved_date) {
		this.approved_date = approved_date;
	}

	public Date getOverall_schedule_date() {
		return overall_schedule_date;
	}

	public void setOverall_schedule_date(Date overall_schedule_date) {
		this.overall_schedule_date = overall_schedule_date;
	}

	public Integer getAgent_id() {
		return agent_id;
	}

	public void setAgent_id(Integer agent_id) {
		this.agent_id = agent_id;
	}

	public double getAgent_percent() {
		return agent_percent;
	}

	public void setAgent_percent(double agent_percent) {
		this.agent_percent = agent_percent;
	}

	public String getEnquiry_mail_sent_status() {
		return enquiry_mail_sent_status;
	}

	public void setEnquiry_mail_sent_status(String enquiry_mail_sent_status) {
		this.enquiry_mail_sent_status = enquiry_mail_sent_status;
	}

	public String getEnquiry_status() {
		return enquiry_status;
	}

	public void setEnquiry_status(String enquiry_status) {
		this.enquiry_status = enquiry_status;
	}

	public String getStatus_remark() {
		return status_remark;
	}

	public void setStatus_remark(String status_remark) {
		this.status_remark = status_remark;
	}

	public String getOther_terms_conditions() {
		return other_terms_conditions;
	}

	public void setOther_terms_conditions(String other_terms_conditions) {
		this.other_terms_conditions = other_terms_conditions;
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

	public CMtEnquiryMasterTradingModel(int enquiry_master_transaction_id, Integer company_id,
	                                    Integer company_branch_id, String financial_year, Integer enquiry_type_id, String enquiry_type,
	                                    String enquiry_life, String enquiry_no, Date enquiry_date, Integer enquiry_version, Integer customer_id,
	                                    String customer_contacts_ids, Integer customer_state_id, Integer customer_city_id,
	                                    Integer expected_branch_id, Integer expected_branch_state_id, Integer expected_branch_city_id,
	                                    Integer enquiry_by_id, Integer assign_to_head_id, Integer assign_to_id, Integer department_id,
	                                    Integer approved_by_id, Date approved_date, Date overall_schedule_date, Integer agent_id,
	                                    double agent_percent, String enquiry_mail_sent_status, String enquiry_status, String status_remark,
	                                    String other_terms_conditions, String remark, boolean is_active, boolean is_delete, String created_by,
	                                    Date created_on, String modified_by, Date modified_on, String deleted_by, Date deleted_on) {
		super();
		this.enquiry_master_transaction_id = enquiry_master_transaction_id;
		this.company_id = company_id;
		this.company_branch_id = company_branch_id;
		this.financial_year = financial_year;
		this.enquiry_type_id = enquiry_type_id;
		this.enquiry_type = enquiry_type;
		this.enquiry_life = enquiry_life;
		this.enquiry_no = enquiry_no;
		this.enquiry_date = enquiry_date;
		this.enquiry_version = enquiry_version;
		this.customer_id = customer_id;
		this.customer_contacts_ids = customer_contacts_ids;
		this.customer_state_id = customer_state_id;
		this.customer_city_id = customer_city_id;
		this.expected_branch_id = expected_branch_id;
		this.expected_branch_state_id = expected_branch_state_id;
		this.expected_branch_city_id = expected_branch_city_id;
		this.enquiry_by_id = enquiry_by_id;
		this.assign_to_head_id = assign_to_head_id;
		this.assign_to_id = assign_to_id;
		this.department_id = department_id;
		this.approved_by_id = approved_by_id;
		this.approved_date = approved_date;
		this.overall_schedule_date = overall_schedule_date;
		this.agent_id = agent_id;
		this.agent_percent = agent_percent;
		this.enquiry_mail_sent_status = enquiry_mail_sent_status;
		this.enquiry_status = enquiry_status;
		this.status_remark = status_remark;
		this.other_terms_conditions = other_terms_conditions;
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

	public CMtEnquiryMasterTradingModel() {
		super();

	}


}
