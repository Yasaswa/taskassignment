package com.erp.Bank.Model;

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
@Table(name = "cm_banks")
public class CBankModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bank_id")
	private int bank_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String account_type;
	private String account_category;
	private Integer bank_list_id;
	private String bank_name;
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
	private boolean is_active = Boolean.TRUE;
	private boolean is_delete = Boolean.FALSE;
	@Column(name = "created_by", updatable = false)
	private String created_by;
	@CreationTimestamp
	@Column(name = "created_on", updatable = false)
	private Date created_on;
	@UpdateTimestamp
	private Date modified_on;
	private String modified_by;
	private String deleted_by;
	private Date deleted_on;

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
