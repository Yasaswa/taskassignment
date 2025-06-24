package com.erp.Agents.Agent_Contacts.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Immutable
@Subselect("select * from cmv_agent_contacts")
public class CAgentContactsViewModel {


	@Id
	private int agent_contact_id;
	private String company_name;
	private String company_branch_name;
	private String agent_name;
	private String agent_contact_person;
	private String agent_designation;
	private String agent_contact_no;
	private String agent_email_id;
	private String agent_alternate_contact;
	private String agent_alternate_EmailId;
	private String Active;
	private String Deleted;
	private boolean is_delete = Boolean.FALSE;
	private boolean is_active = Boolean.TRUE;
	private String created_by;
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;
	private Integer company_id;
	private Integer company_branch_id;
	private Integer agent_id;

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
