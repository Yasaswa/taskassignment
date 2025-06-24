package com.erp.AmApplicationErrorLogs.Model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "am_application_error_logs")
public class CAmApplicationErrorLogsModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "application_error_id")
	private int application_error_id;
	private int company_id;
	private Date application_error_generated_on;
	private String error_source;
	private String error_source_name;
	private Integer error_code;
	private String error_meassage;
	private String remark;
	private boolean is_active;
	private boolean is_delete;
	private String created_by;
	@CreationTimestamp
	@Column(name = "created_on", updatable = false)
	private Date created_on;

	public int getApplication_error_id() {
		return application_error_id;
	}

	public void setApplication_error_id(int application_error_id) {
		this.application_error_id = application_error_id;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

	public Date getApplication_error_generated_on() {
		return application_error_generated_on;
	}

	public void setApplication_error_generated_on(Date application_error_generated_on) {
		this.application_error_generated_on = application_error_generated_on;
	}

	public String getError_source() {
		return error_source;
	}

	public void setError_source(String error_source) {
		this.error_source = error_source;
	}

	public String getError_source_name() {
		return error_source_name;
	}

	public void setError_source_name(String error_source_name) {
		this.error_source_name = error_source_name;
	}

	public Integer getError_code() {
		return error_code;
	}

	public void setError_code(Integer error_code) {
		this.error_code = error_code;
	}

	public String getError_meassage() {
		return error_meassage;
	}

	public void setError_meassage(String error_meassage) {
		this.error_meassage = error_meassage;
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

	public CAmApplicationErrorLogsModel(int application_error_id, int company_id, Date application_error_generated_on,
	                                    String error_source, String error_source_name, Integer error_code, String error_meassage, String remark,
	                                    boolean is_active, boolean is_delete, String created_by, Date created_on) {
		super();
		this.application_error_id = application_error_id;
		this.company_id = company_id;
		this.application_error_generated_on = application_error_generated_on;
		this.error_source = error_source;
		this.error_source_name = error_source_name;
		this.error_code = error_code;
		this.error_meassage = error_meassage;
		this.remark = remark;
		this.is_active = is_active;
		this.is_delete = is_delete;
		this.created_by = created_by;
		this.created_on = created_on;
	}

	public CAmApplicationErrorLogsModel() {
		super();
		// TODO Auto-generated constructor stub
	}


}
