package com.erp.Transporter.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Immutable
@Subselect("select * from  cmv_transporter_contacts")
public class CTransporterContactsViewModel {

	@Id
	private int transporter_contact_id;
	private Integer company_id;
	private Integer company_branch_id;
	private Integer transporter_id;
	private String company_name;
	private String company_branch_name;
	private String transporter_name;
	private String transporter_contact_person;
	private String transporter_designation;
	private String transporter_contact_no;
	private String transporter_email_id;
	private String transporter_alternate_contact;
	private String transporter_alternate_EmailId;
	//	Added by Mohit
//	private String Active;
//	private String Deleted;
	private boolean is_active = Boolean.TRUE;
	private boolean is_delete = Boolean.FALSE;
	private String created_by;
	private Date created_on;
	private String modified_by;
	private Date modified_on;
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
