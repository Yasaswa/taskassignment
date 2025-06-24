package com.erp.Agents.Agent_Bank.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "cm_agent_banks")
public class CAgentBankModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "agent_bank_id")
	private int agent_bank_id;
	private Integer company_id;
	private Integer company_branch_id;
	private Integer agent_id;
	private Integer bank_id;
	private String agent_bank_name;
	private String agent_bank_branch_name;
	private String agent_bank_account_type;
	private String agent_bank_account_no;
	private String agent_bank_ifsc_code;
	private String agent_bank_swift_code;
	private String currency_type;
	//remove this field as per 1.1 DB
	private String agent_bank_gst_no;
	private String agent_bank_gl_codes;
	private String agent_bank_accounts_id;
	//remove this field as per 1.1 DB
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
	private Date deleted_on;

	private String deleted_by;

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


}
