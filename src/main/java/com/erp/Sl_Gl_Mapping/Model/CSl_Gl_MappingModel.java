package com.erp.Sl_Gl_Mapping.Model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "fm_sl_gl_mapping")
public class CSl_Gl_MappingModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sl_gl_mapping_id")
	private int sl_gl_mapping_id;
	private Integer company_id;
	private Integer company_branch_id;
	private Integer schedule_ledger_id;
	private Integer general_ledger_id;
	private boolean is_active = Boolean.TRUE;
	private boolean is_delete = Boolean.FALSE;
	@CreationTimestamp
	@Column(name = "created_on", updatable = false)
	private Date created_on;
	@UpdateTimestamp
	private Date modified_on;
	private Date deleted_on;
	private String created_by;
	private String modified_by;
	private String deleted_by;


	public int getSl_gl_mapping_id() {
		return sl_gl_mapping_id;
	}

	public void setSl_gl_mapping_id(int sl_gl_mapping_id) {
		this.sl_gl_mapping_id = sl_gl_mapping_id;
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

	public Integer getSchedule_ledger_id() {
		return schedule_ledger_id;
	}

	public void setSchedule_ledger_id(Integer schedule_ledger_id) {
		this.schedule_ledger_id = schedule_ledger_id;
	}

	public Integer getGeneral_ledger_id() {
		return general_ledger_id;
	}

	public void setGeneral_ledger_id(Integer general_ledger_id) {
		this.general_ledger_id = general_ledger_id;
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


	public CSl_Gl_MappingModel(int sl_gl_mapping_id, Integer company_id, Integer company_branch_id,
	                           Integer schedule_ledger_id, Integer general_ledger_id, boolean is_active, boolean is_delete,
	                           String created_by, Date created_on, String modified_by, Date modified_on, String deleted_by,
	                           Date deleted_on) {
		super();
		this.sl_gl_mapping_id = sl_gl_mapping_id;
		this.company_id = company_id;
		this.company_branch_id = company_branch_id;
		this.schedule_ledger_id = schedule_ledger_id;
		this.general_ledger_id = general_ledger_id;
		this.is_active = is_active;
		this.is_delete = is_delete;
		this.created_by = created_by;
		this.created_on = created_on;
		this.modified_by = modified_by;
		this.modified_on = modified_on;
		this.deleted_by = deleted_by;
		this.deleted_on = deleted_on;
	}

	public CSl_Gl_MappingModel() {
		super();
		// TODO Auto-generated constructor stub
	}

}
