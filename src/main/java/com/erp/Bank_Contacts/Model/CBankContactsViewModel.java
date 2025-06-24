package com.erp.Bank_Contacts.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Immutable
@Subselect("select * from cmv_banks_contacts")
public class CBankContactsViewModel {

	@Id
	private int bank_contact_id;
	private String bank_name;
	private String bank_branch_name;
	private String bank_account_no;
	private String bank_contact_person;
	private String designation;
	private String contact_no;
	private String email_id;
	private String alternate_contact;
	private String alternate_EmailId;
	private String Active;
	private String Deleted;
	private Boolean is_active = Boolean.TRUE;
	private Boolean is_delete = Boolean.FALSE;
	private String created_by;
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;
	private Integer company_id;
	private String company_name;
	private Integer company_branch_id;
	private String company_branch_name;
	private Integer bank_id;

	public Boolean getIs_active() {
		return is_active;
	}

	public void setIs_active(Boolean is_active) {
		this.is_active = is_active;
	}

	public Boolean getIs_delete() {
		return is_delete;
	}

	public void setIs_delete(Boolean is_delete) {
		this.is_delete = is_delete;
	}


}
