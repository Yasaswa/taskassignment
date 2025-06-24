package com.erp.Bank.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Immutable
@Subselect("select * from cmv_banks")
public class CBankViewModel {

	@Id
	private int bank_id;
	private String bank_name;
	private String account_type;
	private String account_category;
	private String bank_branch_name;
	private String bank_branch_address;
	private String bank_branch_email_id;
	private String bank_branch_contact_no;
	private String bank_account_no;
	private String bank_ifsc_code;
	private String bank_swift_code;
	private String bank_gst_no;
	private String currency_type;
	private Integer authorized_person_count;
	private String authorized_person1;
	private String authorized_person2;
	private String authorized_person3;
	private String bank_gl_codes;
	private String bank_accounts_id;
	private String company_name;
	private String company_branch_name;
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
	private Integer company_branch_id;
	private Integer field_id;
	private String field_name;
	private Integer bank_list_id;


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
