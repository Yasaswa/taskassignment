package com.erp.Company.Companies.Model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "cm_company")
public class CCompanyModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "company_id")
	private int company_id;
	private Integer company_type_id;
	private String company_legal_name;
	private String company_trade_name;
	private String company_short_name;
	private String company_sector;
	private String nature_of_business;
	private String registration_no;
	private String registration_date;
	private String establish_date;
	private String company_logo_document_path;
	private String logo_file_name;
	private boolean is_active = Boolean.TRUE;
	private boolean is_delete = Boolean.FALSE;
	@Column(name = "created_by", updatable = false)
	private String created_by;
	@CreationTimestamp
	@Column(name = "created_on", updatable = false)
	private Date created_on;
	@UpdateTimestamp
	private Date modified_on;
	private Date deleted_on;
	private String modified_by;
	private String deleted_by;
	private String remark;

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

	public Integer getCompany_type_id() {
		return company_type_id;
	}

	public void setCompany_type_id(Integer company_type_id) {
		this.company_type_id = company_type_id;
	}

	public String getCompany_legal_name() {
		return company_legal_name;
	}

	public void setCompany_legal_name(String company_legal_name) {
		this.company_legal_name = company_legal_name;
	}

	public String getCompany_trade_name() {
		return company_trade_name;
	}

	public void setCompany_trade_name(String company_trade_name) {
		this.company_trade_name = company_trade_name;
	}

	public String getCompany_short_name() {
		return company_short_name;
	}

	public void setCompany_short_name(String company_short_name) {
		this.company_short_name = company_short_name;
	}

	public String getCompany_sector() {
		return company_sector;
	}

	public void setCompany_sector(String company_sector) {
		this.company_sector = company_sector;
	}

	public String getNature_of_business() {
		return nature_of_business;
	}

	public void setNature_of_business(String nature_of_business) {
		this.nature_of_business = nature_of_business;
	}

	public String getRegistration_no() {
		return registration_no;
	}

	public void setRegistration_no(String registration_no) {
		this.registration_no = registration_no;
	}

	public String getRegistration_date() {
		return registration_date;
	}

	public void setRegistration_date(String registration_date) {
		if (registration_date == null || registration_date.isEmpty()) {
			this.registration_date = null;
		} else {
			this.registration_date = registration_date;
		}
	}

	public String getEstablish_date() {
		return establish_date;
	}

	public void setEstablish_date(String establish_date) {
		if (establish_date == null || establish_date.isEmpty()) {
			this.establish_date = null;
		} else {
			this.establish_date = establish_date;
		}
	}

	public String getCompany_logo_document_path() {
		return company_logo_document_path;
	}

	public void setCompany_logo_document_path(String company_logo_document_path) {
		this.company_logo_document_path = company_logo_document_path;
	}

	public String getLogo_file_name() {
		return logo_file_name;
	}

	public void setLogo_file_name(String logo_file_name) {
		this.logo_file_name = logo_file_name;
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

	public Date getModified_on() {
		return modified_on;
	}

	public void setModified_on(Date modified_on) {
		this.modified_on = modified_on;
	}

	public Date getDeleted_on() {
		return deleted_on;
	}

	public void setDeleted_on(Date deleted_on) {
		this.deleted_on = deleted_on;
	}

	public String getModified_by() {
		return modified_by;
	}

	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}

	public String getDeleted_by() {
		return deleted_by;
	}

	public void setDeleted_by(String deleted_by) {
		this.deleted_by = deleted_by;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public CCompanyModel(int company_id, Integer company_type_id, String company_legal_name, String company_trade_name,
	                     String company_short_name, String company_sector, String nature_of_business, String registration_no,
	                     String registration_date, String establish_date, String company_logo_document_path, String logo_file_name,
	                     boolean is_active, boolean is_delete, String created_by, Date created_on, Date modified_on, Date deleted_on,
	                     String modified_by, String deleted_by, String remark) {
		super();
		this.company_id = company_id;
		this.company_type_id = company_type_id;
		this.company_legal_name = company_legal_name;
		this.company_trade_name = company_trade_name;
		this.company_short_name = company_short_name;
		this.company_sector = company_sector;
		this.nature_of_business = nature_of_business;
		this.registration_no = registration_no;
		this.registration_date = registration_date;
		this.establish_date = establish_date;
		this.company_logo_document_path = company_logo_document_path;
		this.logo_file_name = logo_file_name;
		this.is_active = is_active;
		this.is_delete = is_delete;
		this.created_by = created_by;
		this.created_on = created_on;
		this.modified_on = modified_on;
		this.deleted_on = deleted_on;
		this.modified_by = modified_by;
		this.deleted_by = deleted_by;
		this.remark = remark;
	}

	public CCompanyModel() {
		super();
	}

}
