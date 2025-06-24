package com.erp.Agents.Agent_Bank.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Immutable
@Subselect("select * from cmv_agent_banks")
public class CAgentBankViewModel {

	@Id
	private int agent_bank_id;
	private String agent_name;
	private String agent_bank_name;
	private String agent_bank_branch_name;
	private String agent_bank_account_type;
	private String agent_bank_account_no;
	private String agent_bank_ifsc_code;
	private String agent_bank_swift_code;
	private String currency_type;
	private String company_name;
	private String company_branch_name;
	private String Active;
	private String Deleted;
	private boolean is_delete;
	private boolean is_active;
	private String created_by;
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;
	private Integer company_id;
	private Integer company_branch_id;
	private Integer agent_id;
	private Integer bank_id;
	private Integer field_id;
	private String field_name;

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


}
