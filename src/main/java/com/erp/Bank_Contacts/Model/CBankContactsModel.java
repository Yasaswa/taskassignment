package com.erp.Bank_Contacts.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "cm_banks_contacts")
public class CBankContactsModel {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bank_contact_id")
	private int bank_contact_id;
	private Integer company_id;
	private Integer company_branch_id;
	private Integer bank_id;
	private String bank_contact_person;
	private String designation;
	private String contact_no;
	private String email_id;
	private String alternate_contact;
	private String alternate_EmailId;
	private boolean is_delete = Boolean.FALSE;
	private boolean is_active = Boolean.TRUE;
	@Column(name = "created_by", updatable = false)
	private String created_by;
	@CreationTimestamp
	@Column(name = "created_on", updatable = false)
	private Date created_on;
	@UpdateTimestamp
	private Date modified_on;
	private Date deleted_on;
	private String modified_by;
	private String deleted_by;

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
