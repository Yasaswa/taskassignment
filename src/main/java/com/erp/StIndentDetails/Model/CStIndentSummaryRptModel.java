package com.erp.StIndentDetails.Model;

import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@Immutable
@Subselect("select * from stv_indent_summary_rpt")
public class CStIndentSummaryRptModel {

	@Id
	private String company_id;
	private String company_name;
	private String company_branch_name;
	private String financial_year;
	private String indent_no;
	private String indent_date;
	private String indent_version;
	private String indent_type;
	private String indent_source;
	private String indent_source_desc;
	private String department_name;
	private String indented_by_name;
	private String approved_by_name;
	private String indent_status_desc;
	private String customer_name;
	private String customer_order_no;
	private String customer_order_date;
	private String expected_schedule_date;
	private String indent_status;
	private String remark;
	private String field_name;
	private String indent_transaction_type;
	private String field_id;
	private String is_active;
	private String is_delete;
	private String created_by;
	private String created_on;
	private String modified_by;
	private String modified_on;
	private String deleted_by;
	private String deleted_on;
	private String indent_master_id;
	private String indent_type_id;
	private String customer_id;
	private String department_id;
	private String indented_by_id;
	private String company_branch_id;

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
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

	public String getFinancial_year() {
		return financial_year;
	}

	public void setFinancial_year(String financial_year) {
		this.financial_year = financial_year;
	}

	public String getIndent_no() {
		return indent_no;
	}

	public void setIndent_no(String indent_no) {
		this.indent_no = indent_no;
	}

	public String getIndent_date() {
		return indent_date;
	}

	public void setIndent_date(String indent_date) {
		this.indent_date = indent_date;
	}

	public String getIndent_version() {
		return indent_version;
	}

	public void setIndent_version(String indent_version) {
		this.indent_version = indent_version;
	}

	public String getIndent_type() {
		return indent_type;
	}

	public void setIndent_type(String indent_type) {
		this.indent_type = indent_type;
	}

	public String getIndent_source_desc() {
		return indent_source_desc;
	}

	public void setIndent_source_desc(String indent_source_desc) {
		this.indent_source_desc = indent_source_desc;
	}

	public String getIndent_source() {
		return indent_source;
	}

	public void setIndent_source(String indent_source) {
		this.indent_source = indent_source;
	}

	public String getDepartment_name() {
		return department_name;
	}

	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}

	public String getIndented_by_name() {
		return indented_by_name;
	}

	public void setIndented_by_name(String indented_by_name) {
		this.indented_by_name = indented_by_name;
	}

	public String getApproved_by_name() {
		return approved_by_name;
	}

	public void setApproved_by_name(String approved_by_name) {
		this.approved_by_name = approved_by_name;
	}

	public String getIndent_status_desc() {
		return indent_status_desc;
	}

	public void setIndent_status_desc(String indent_status_desc) {
		this.indent_status_desc = indent_status_desc;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public String getCustomer_order_no() {
		return customer_order_no;
	}

	public void setCustomer_order_no(String customer_order_no) {
		this.customer_order_no = customer_order_no;
	}

	public String getCustomer_order_date() {
		return customer_order_date;
	}

	public void setCustomer_order_date(String customer_order_date) {
		this.customer_order_date = customer_order_date;
	}

	public String getExpected_schedule_date() {
		return expected_schedule_date;
	}

	public void setExpected_schedule_date(String expected_schedule_date) {
		this.expected_schedule_date = expected_schedule_date;
	}

	public String getIndent_status() {
		return indent_status;
	}

	public void setIndent_status(String indent_status) {
		this.indent_status = indent_status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getField_name() {
		return field_name;
	}

	public void setField_name(String field_name) {
		this.field_name = field_name;
	}

	public String getField_id() {
		return field_id;
	}

	public void setField_id(String field_id) {
		this.field_id = field_id;
	}

	public String getIs_active() {
		return is_active;
	}

	public void setIs_active(String is_active) {
		this.is_active = is_active;
	}

	public String getIs_delete() {
		return is_delete;
	}

	public void setIs_delete(String is_delete) {
		this.is_delete = is_delete;
	}

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public String getCreated_on() {
		return created_on;
	}

	public void setCreated_on(String created_on) {
		this.created_on = created_on;
	}

	public String getModified_by() {
		return modified_by;
	}

	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}

	public String getModified_on() {
		return modified_on;
	}

	public void setModified_on(String modified_on) {
		this.modified_on = modified_on;
	}

	public String getDeleted_by() {
		return deleted_by;
	}

	public void setDeleted_by(String deleted_by) {
		this.deleted_by = deleted_by;
	}

	public String getDeleted_on() {
		return deleted_on;
	}

	public void setDeleted_on(String deleted_on) {
		this.deleted_on = deleted_on;
	}

	public String getIndent_master_id() {
		return indent_master_id;
	}

	public void setIndent_master_id(String indent_master_id) {
		this.indent_master_id = indent_master_id;
	}

	public String getIndent_type_id() {
		return indent_type_id;
	}

	public void setIndent_type_id(String indent_type_id) {
		this.indent_type_id = indent_type_id;
	}

	public String getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}

	public String getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(String department_id) {
		this.department_id = department_id;
	}

	public String getIndented_by_id() {
		return indented_by_id;
	}

	public void setIndented_by_id(String indented_by_id) {
		this.indented_by_id = indented_by_id;
	}

	public String getCompany_branch_id() {
		return company_branch_id;
	}

	public void setCompany_branch_id(String company_branch_id) {
		this.company_branch_id = company_branch_id;
	}

	public String getIndent_transaction_type() {
		return indent_transaction_type;
	}

	public void setIndent_transaction_type(String indent_transaction_type) {
		this.indent_transaction_type = indent_transaction_type;
	}

	public CStIndentSummaryRptModel(String company_id, String company_name, String company_branch_name,
	                                String financial_year, String indent_no, String indent_date, String indent_version, String indent_type,
	                                String indent_source, String indent_source_desc, String department_name, String indented_by_name, String approved_by_name,
	                                String indent_status_desc, String customer_name, String customer_order_no, String customer_order_date,
	                                String expected_schedule_date, String indent_status, String remark, String field_name,
	                                String indent_transaction_type, String field_id, String is_active, String is_delete, String created_by,
	                                String created_on, String modified_by, String modified_on, String deleted_by, String deleted_on,
	                                String indent_master_id, String indent_type_id, String customer_id, String department_id,
	                                String indented_by_id, String company_branch_id) {
		super();
		this.company_id = company_id;
		this.company_name = company_name;
		this.company_branch_name = company_branch_name;
		this.financial_year = financial_year;
		this.indent_no = indent_no;
		this.indent_date = indent_date;
		this.indent_version = indent_version;
		this.indent_type = indent_type;
		this.indent_source = indent_source;
		this.indent_source_desc = indent_source_desc;
		this.department_name = department_name;
		this.indented_by_name = indented_by_name;
		this.approved_by_name = approved_by_name;
		this.indent_status_desc = indent_status_desc;
		this.customer_name = customer_name;
		this.customer_order_no = customer_order_no;
		this.customer_order_date = customer_order_date;
		this.expected_schedule_date = expected_schedule_date;
		this.indent_status = indent_status;
		this.remark = remark;
		this.field_name = field_name;
		this.indent_transaction_type = indent_transaction_type;
		this.field_id = field_id;
		this.is_active = is_active;
		this.is_delete = is_delete;
		this.created_by = created_by;
		this.created_on = created_on;
		this.modified_by = modified_by;
		this.modified_on = modified_on;
		this.deleted_by = deleted_by;
		this.deleted_on = deleted_on;
		this.indent_master_id = indent_master_id;
		this.indent_type_id = indent_type_id;
		this.customer_id = customer_id;
		this.department_id = department_id;
		this.indented_by_id = indented_by_id;
		this.company_branch_id = company_branch_id;
	}

	public CStIndentSummaryRptModel() {
		super();

	}

}
