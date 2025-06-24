package com.erp.XmProductionHoliday.Model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "xm_production_holiday")
public class CXmProductionHolidayModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "production_holiday_id")
	private int production_holiday_id;
	private String production_holiday_date;
	private String production_holiday_name;
	private String production_holiday_remark;
	private boolean is_active = Boolean.TRUE;
	private boolean is_delete = Boolean.FALSE;
	@Column(name = "created_by", updatable = false)
	private String created_by;
	@CreationTimestamp
	@Column(name = "created_on", updatable = false)
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;
	private Integer company_branch_id;
	private Integer company_id;

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

	public Integer getCompany_branch_id() {
		return company_branch_id;
	}

	public void setCompany_branch_id(Integer company_branch_id) {
		this.company_branch_id = company_branch_id;
	}

	public Integer getProduction_holiday_id() {
		return production_holiday_id;
	}

	public void setProduction_holiday_id(Integer production_holiday_id) {
		this.production_holiday_id = production_holiday_id;
	}

	public String getProduction_holiday_date() {
		return production_holiday_date;
	}

	public void setProduction_holiday_date(String production_holiday_date) {
		this.production_holiday_date = production_holiday_date;
	}

	public String getProduction_holiday_name() {
		return production_holiday_name;
	}

	public void setProduction_holiday_name(String production_holiday_name) {
		this.production_holiday_name = production_holiday_name;
	}

	public String getProduction_holiday_remark() {
		return production_holiday_remark;
	}

	public void setProduction_holiday_remark(String production_holiday_remark) {
		this.production_holiday_remark = production_holiday_remark;
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

	public CXmProductionHolidayModel(int production_holiday_id, String production_holiday_date,
	                                 String production_holiday_name, String production_holiday_remark, boolean is_active, boolean is_delete,
	                                 String created_by, Date created_on, String modified_by, Date modified_on, String deleted_by,
	                                 Date deleted_on, Integer company_branch_id, Integer company_id) {
		super();
		this.production_holiday_id = production_holiday_id;
		this.production_holiday_date = production_holiday_date;
		this.production_holiday_name = production_holiday_name;
		this.production_holiday_remark = production_holiday_remark;
		this.is_active = is_active;
		this.is_delete = is_delete;
		this.created_by = created_by;
		this.created_on = created_on;
		this.modified_by = modified_by;
		this.modified_on = modified_on;
		this.deleted_by = deleted_by;
		this.deleted_on = deleted_on;
		this.company_branch_id = company_branch_id;
		this.company_id = company_id;
	}

	public CXmProductionHolidayModel() {
		super();
	}

}
