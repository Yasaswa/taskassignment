package com.erp.XmProductionProcess.Model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "xm_production_process")
public class CXmProductionProcessModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "production_process_id")
	private int production_process_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String production_process_type;
	private Integer production_parent_process_id;
	private String production_process_work;
	private String production_process_name;
	private String production_process_short_name;
	private double production_process_std_scrap_percent;
	private double production_process_std_production_hrs;
	private Integer production_process_sequance;
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

	public int getProduction_process_id() {
		return production_process_id;
	}

	public void setProduction_process_id(int production_process_id) {
		this.production_process_id = production_process_id;
	}

	public String getProduction_process_type() {
		return production_process_type;
	}

	public void setProduction_process_type(String production_process_type) {
		this.production_process_type = production_process_type;
	}

	public Integer getProduction_parent_process_id() {
		return production_parent_process_id;
	}

	public void setProduction_parent_process_id(Integer production_parent_process_id) {
		this.production_parent_process_id = production_parent_process_id;
	}

	public String getProduction_process_work() {
		return production_process_work;
	}

	public void setProduction_process_work(String production_process_work) {
		this.production_process_work = production_process_work;
	}

	public String getProduction_process_name() {
		return production_process_name;
	}

	public void setProduction_process_name(String production_process_name) {
		this.production_process_name = production_process_name;
	}

	public String getProduction_process_short_name() {
		return production_process_short_name;
	}

	public void setProduction_process_short_name(String production_process_short_name) {
		this.production_process_short_name = production_process_short_name;
	}

	public double getProduction_process_std_scrap_percent() {
		return production_process_std_scrap_percent;
	}

	public void setProduction_process_std_scrap_percent(double production_process_std_scrap_percent) {
		this.production_process_std_scrap_percent = production_process_std_scrap_percent;
	}

	public double getProduction_process_std_production_hrs() {
		return production_process_std_production_hrs;
	}

	public void setProduction_process_std_production_hrs(double production_process_std_production_hrs) {
		this.production_process_std_production_hrs = production_process_std_production_hrs;
	}

	public Integer getProduction_process_sequance() {
		return production_process_sequance;
	}

	public void setProduction_process_sequance(Integer production_process_sequance) {
		this.production_process_sequance = production_process_sequance;
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

	public CXmProductionProcessModel(int company_id, Integer company_branch_id, Integer production_process_id,
	                                 String production_process_type, Integer production_parent_process_id, String production_process_work,
	                                 String production_process_name, String production_process_short_name,
	                                 double production_process_std_scrap_percent, double production_process_std_production_hrs,
	                                 Integer production_process_sequance, boolean is_active, boolean is_delete, String created_by,
	                                 Date created_on, String modified_by, Date modified_on, String deleted_by, Date deleted_on) {
		super();
		this.company_id = company_id;
		this.company_branch_id = company_branch_id;
		this.production_process_id = production_process_id;
		this.production_process_type = production_process_type;
		this.production_parent_process_id = production_parent_process_id;
		this.production_process_work = production_process_work;
		this.production_process_name = production_process_name;
		this.production_process_short_name = production_process_short_name;
		this.production_process_std_scrap_percent = production_process_std_scrap_percent;
		this.production_process_std_production_hrs = production_process_std_production_hrs;
		this.production_process_sequance = production_process_sequance;
		this.is_active = is_active;
		this.is_delete = is_delete;
		this.created_by = created_by;
		this.created_on = created_on;
		this.modified_by = modified_by;
		this.modified_on = modified_on;
		this.deleted_by = deleted_by;
		this.deleted_on = deleted_on;
	}

	public CXmProductionProcessModel() {
		super();

	}
}
