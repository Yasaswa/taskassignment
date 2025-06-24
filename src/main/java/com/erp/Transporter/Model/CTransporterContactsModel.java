package com.erp.Transporter.Model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "cm_transporter_contacts")
public class CTransporterContactsModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "transporter_contact_id")
	private int transporter_contact_id;
	private Integer company_id;
	private Integer company_branch_id;
	private Integer transporter_id;
	private String transporter_contact_person;
	private String transporter_designation;
	private String transporter_contact_no;
	private String transporter_email_id;
	private String transporter_alternate_contact;
	private String transporter_alternate_EmailId;
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

	public int getTransporter_contact_id() {
		return transporter_contact_id;
	}

	public void setTransporter_contact_id(int transporter_contact_id) {
		this.transporter_contact_id = transporter_contact_id;
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

	public Integer getTransporter_id() {
		return transporter_id;
	}

	public void setTransporter_id(Integer transporter_id) {
		this.transporter_id = transporter_id;
	}

	public String getTransporter_contact_person() {
		return transporter_contact_person;
	}

	public void setTransporter_contact_person(String transporter_contact_person) {
		this.transporter_contact_person = transporter_contact_person;
	}

	public String getTransporter_designation() {
		return transporter_designation;
	}

	public void setTransporter_designation(String transporter_designation) {
		this.transporter_designation = transporter_designation;
	}

	public String getTransporter_contact_no() {
		return transporter_contact_no;
	}

	public void setTransporter_contact_no(String transporter_contact_no) {
		this.transporter_contact_no = transporter_contact_no;
	}

	public String getTransporter_email_id() {
		return transporter_email_id;
	}

	public void setTransporter_email_id(String transporter_email_id) {
		this.transporter_email_id = transporter_email_id;
	}

	public String getTransporter_alternate_contact() {
		return transporter_alternate_contact;
	}

	public void setTransporter_alternate_contact(String transporter_alternate_contact) {
		this.transporter_alternate_contact = transporter_alternate_contact;
	}

	public String getTransporter_alternate_EmailId() {
		return transporter_alternate_EmailId;
	}

	public void setTransporter_alternate_EmailId(String transporter_alternate_EmailId) {
		this.transporter_alternate_EmailId = transporter_alternate_EmailId;
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

	public Date getCreated_on() {
		return created_on;
	}

	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
	}

	public Date getModified_on() {
		return modified_on;
	}

	public void setModified_on(Date modified_on) {
		this.modified_on = modified_on;
	}

	public Date getDeleted_on() {
		return deleted_on;
	}

	public void setDeleted_on(Date deleted_on) {
		this.deleted_on = deleted_on;
	}

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public String getModified_by() {
		return modified_by;
	}

	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}

	public String getDeleted_by() {
		return deleted_by;
	}

	public void setDeleted_by(String deleted_by) {
		this.deleted_by = deleted_by;
	}

	public CTransporterContactsModel(int transporter_contact_id, Integer company_id, Integer company_branch_id,
	                                 Integer transporter_id, String transporter_contact_person, String transporter_designation,
	                                 String transporter_contact_no, String transporter_email_id, String transporter_alternate_contact,
	                                 String transporter_alternate_EmailId, boolean is_active, boolean is_delete, Date created_on,
	                                 Date modified_on, Date deleted_on, String created_by, String modified_by, String deleted_by) {
		super();
		this.transporter_contact_id = transporter_contact_id;
		this.company_id = company_id;
		this.company_branch_id = company_branch_id;
		this.transporter_id = transporter_id;
		this.transporter_contact_person = transporter_contact_person;
		this.transporter_designation = transporter_designation;
		this.transporter_contact_no = transporter_contact_no;
		this.transporter_email_id = transporter_email_id;
		this.transporter_alternate_contact = transporter_alternate_contact;
		this.transporter_alternate_EmailId = transporter_alternate_EmailId;
		this.is_active = is_active;
		this.is_delete = is_delete;
		this.created_on = created_on;
		this.modified_on = modified_on;
		this.deleted_on = deleted_on;
		this.created_by = created_by;
		this.modified_by = modified_by;
		this.deleted_by = deleted_by;
	}

	public CTransporterContactsModel() {
		super();
		// TODO Auto-generated constructor stub
	}


}
