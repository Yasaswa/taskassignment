package com.erp.StIndentDetails.Model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "st_indent_po_terms")
public class CStIndentPOTermsModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "indent_po_terms_id")
	private int indent_po_terms_id;
	private Integer indent_master_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String indent_no;
	private Integer indent_version;
	private Integer common_parameters_id;
	private String common_parameters_name;
	private String common_parameters_value;
	private String common_parameters_expected_value;
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

	public int getIndent_po_terms_id() {
		return indent_po_terms_id;
	}

	public void setIndent_po_terms_id(int indent_po_terms_id) {
		this.indent_po_terms_id = indent_po_terms_id;
	}

	public Integer getIndent_master_id() {
		return indent_master_id;
	}

	public void setIndent_master_id(Integer indent_master_id) {
		this.indent_master_id = indent_master_id;
	}

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
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

	public String getIndent_no() {
		return indent_no;
	}

	public void setIndent_no(String indent_no) {
		this.indent_no = indent_no;
	}

	public Integer getIndent_version() {
		return indent_version;
	}

	public void setIndent_version(Integer indent_version) {
		this.indent_version = indent_version;
	}

	public Integer getCommon_parameters_id() {
		return common_parameters_id;
	}

	public void setCommon_parameters_id(Integer common_parameters_id) {
		this.common_parameters_id = common_parameters_id;
	}

	public String getCommon_parameters_name() {
		return common_parameters_name;
	}

	public void setCommon_parameters_name(String common_parameters_name) {
		this.common_parameters_name = common_parameters_name;
	}

	public String getCommon_parameters_value() {
		return common_parameters_value;
	}

	public void setCommon_parameters_value(String common_parameters_value) {
		this.common_parameters_value = common_parameters_value;
	}

	public String getCommon_parameters_expected_value() {
		return common_parameters_expected_value;
	}

	public void setCommon_parameters_expected_value(String common_parameters_expected_value) {
		this.common_parameters_expected_value = common_parameters_expected_value;
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

	public CStIndentPOTermsModel(int indent_po_terms_id, Integer indent_master_id, Integer company_id,
	                             Integer company_branch_id, String indent_no, Integer indent_version, Integer common_parameters_id,
	                             String common_parameters_name, String common_parameters_value, String common_parameters_expected_value,
	                             String remark, boolean is_active, boolean is_delete, String created_by, Date created_on, String modified_by,
	                             Date modified_on, String deleted_by, Date deleted_on) {
		super();
		this.indent_po_terms_id = indent_po_terms_id;
		this.indent_master_id = indent_master_id;
		this.company_id = company_id;
		this.company_branch_id = company_branch_id;
		this.indent_no = indent_no;
		this.indent_version = indent_version;
		this.common_parameters_id = common_parameters_id;
		this.common_parameters_name = common_parameters_name;
		this.common_parameters_value = common_parameters_value;
		this.common_parameters_expected_value = common_parameters_expected_value;
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

	public CStIndentPOTermsModel() {
		super();

	}

}
