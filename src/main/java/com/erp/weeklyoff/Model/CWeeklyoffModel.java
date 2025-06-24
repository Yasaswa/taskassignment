package com.erp.weeklyoff.Model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "hm_weeklyoff")
public class CWeeklyoffModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "weeklyoff_id")
	private int weeklyoff_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String weeklyoff_name;
	private boolean is_delete = Boolean.FALSE;
	private boolean is_active = Boolean.TRUE;
	@CreationTimestamp
	@Column(name = "created_on", updatable = false)
	private Date created_on;
	@UpdateTimestamp
	private Date modified_on;
	private Date deleted_on;
	private String created_by;
	private String modified_by;
	private String deleted_by;

	public int getWeeklyoff_id() {
		return weeklyoff_id;
	}

	public void setWeeklyoff_id(int weeklyoff_id) {
		this.weeklyoff_id = weeklyoff_id;
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

	public String getWeeklyoff_name() {
		return weeklyoff_name;
	}

	public void setWeeklyoff_name(String weeklyoff_name) {
		this.weeklyoff_name = weeklyoff_name;
	}

	public boolean isIs_delete() {
		return is_delete;
	}

	public void setIs_delete(boolean is_delete) {
		this.is_delete = is_delete;
	}

	public boolean isIs_active() {
		return is_active;
	}

	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
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

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
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

	public CWeeklyoffModel(int weeklyoff_id, Integer company_id, Integer company_branch_id, String weeklyoff_name,
	                       boolean is_delete, boolean is_active, Date created_on, Date modified_on, Date deleted_on, String created_by,
	                       String modified_by, String deleted_by) {
		super();
		this.weeklyoff_id = weeklyoff_id;
		this.company_id = company_id;
		this.company_branch_id = company_branch_id;
		this.weeklyoff_name = weeklyoff_name;
		this.is_delete = is_delete;
		this.is_active = is_active;
		this.created_on = created_on;
		this.modified_on = modified_on;
		this.deleted_on = deleted_on;
		this.created_by = created_by;
		this.modified_by = modified_by;
		this.deleted_by = deleted_by;
	}

	public CWeeklyoffModel() {
		super();
		// TODO Auto-generated constructor stub
	}


}
