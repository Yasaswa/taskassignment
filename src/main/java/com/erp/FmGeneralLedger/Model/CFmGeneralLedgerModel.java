package com.erp.FmGeneralLedger.Model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "fm_general_ledger")
public class CFmGeneralLedgerModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "general_ledger_id")
	private int general_ledger_id;
	private int company_id;
	private int company_branch_id;
	private String general_ledger_name;
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

	public int getgeneral_ledger_id() {
		return general_ledger_id;
	}

	public void setgeneral_ledger_id(int general_ledger_id) {
		this.general_ledger_id = general_ledger_id;
	}

	public int getcompany_id() {
		return company_id;
	}

	public void setcompany_id(int company_id) {
		this.company_id = company_id;
	}

	public int getcompany_branch_id() {
		return company_branch_id;
	}

	public void setcompany_branch_id(int company_branch_id) {
		this.company_branch_id = company_branch_id;
	}

	public String getgeneral_ledger_name() {
		return general_ledger_name;
	}

	public void setgeneral_ledger_name(String general_ledger_name) {
		this.general_ledger_name = general_ledger_name;
	}

	public boolean getis_active() {
		return is_active;
	}

	public void setis_active(boolean is_active) {
		this.is_active = is_active;
	}

	public boolean getis_delete() {
		return is_delete;
	}

	public void setis_delete(boolean is_delete) {
		this.is_delete = is_delete;
	}

	public String getcreated_by() {
		return created_by;
	}

	public void setcreated_by(String created_by) {
		this.created_by = created_by;
	}

	public Date getcreated_on() {
		return created_on;
	}

	public void setcreated_on(Date created_on) {
		this.created_on = created_on;
	}

	public String getmodified_by() {
		return modified_by;
	}

	public void setmodified_by(String modified_by) {
		this.modified_by = modified_by;
	}

	public Date getmodified_on() {
		return modified_on;
	}

	public void setmodified_on(Date modified_on) {
		this.modified_on = modified_on;
	}

	public String getdeleted_by() {
		return deleted_by;
	}

	public void setdeleted_by(String deleted_by) {
		this.deleted_by = deleted_by;
	}

	public Date getdeleted_on() {
		return deleted_on;
	}

	public void setdeleted_on(Date deleted_on) {
		this.deleted_on = deleted_on;
	}

	public CFmGeneralLedgerModel(int general_ledger_id, int company_id, int company_branch_id,
	                             String general_ledger_name, boolean is_active, boolean is_delete, String created_by, Date created_on,
	                             String modified_by, Date modified_on, String deleted_by, Date deleted_on) {
		super();
		this.general_ledger_id = general_ledger_id;
		this.company_id = company_id;
		this.company_branch_id = company_branch_id;
		this.general_ledger_name = general_ledger_name;
		this.is_active = is_active;
		this.is_delete = is_delete;
		this.created_by = created_by;
		this.created_on = created_on;
		this.modified_by = modified_by;
		this.modified_on = modified_on;
		this.deleted_by = deleted_by;
		this.deleted_on = deleted_on;
	}

	public CFmGeneralLedgerModel() {
		super();
	}

}
