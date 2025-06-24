package com.erp.Customer_Contacts.Model;

import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@Immutable
@Subselect("select * from cmv_customer_contacts")
public class CCustomerContactsViewModel {

	@Id
	private int customer_contact_id;
	private String company_name;
	private String company_branch_name;
	private String customer_name;
	private String customer_branch_name;
	private String cust_contact_person;
	private String cust_designation;
	private String cust_contact_no;
	private String cust_email_id;
	private String cust_alternate_contact;
	private String cust_alternate_EmailId;
	private String escalation_level;
	private boolean is_delete = Boolean.FALSE;
	private boolean is_active = Boolean.TRUE;
	private String created_by;
	private String modified_by;
	private String deleted_by;
	private Integer company_branch_id;
	private Integer customer_id;
	private Integer company_id;
	private Integer cust_branch_id;

	public int getCustomer_contact_id() {
		return customer_contact_id;
	}

	public void setCustomer_contact_id(int customer_contact_id) {
		this.customer_contact_id = customer_contact_id;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getCompany_branch_name() {
		return company_branch_name;
	}

	public void setCompany_branch_name(String company_branch_name) {
		this.company_branch_name = company_branch_name;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public String getCustomer_branch_name() {
		return customer_branch_name;
	}

	public void setCustomer_branch_name(String customer_branch_name) {
		this.customer_branch_name = customer_branch_name;
	}

	public String getCust_contact_person() {
		return cust_contact_person;
	}

	public void setCust_contact_person(String cust_contact_person) {
		this.cust_contact_person = cust_contact_person;
	}

	public String getCust_designation() {
		return cust_designation;
	}

	public void setCust_designation(String cust_designation) {
		this.cust_designation = cust_designation;
	}

	public String getCust_contact_no() {
		return cust_contact_no;
	}

	public void setCust_contact_no(String cust_contact_no) {
		this.cust_contact_no = cust_contact_no;
	}

	public String getCust_email_id() {
		return cust_email_id;
	}

	public void setCust_email_id(String cust_email_id) {
		this.cust_email_id = cust_email_id;
	}

	public String getCust_alternate_contact() {
		return cust_alternate_contact;
	}

	public void setCust_alternate_contact(String cust_alternate_contact) {
		this.cust_alternate_contact = cust_alternate_contact;
	}

	public String getCust_alternate_EmailId() {
		return cust_alternate_EmailId;
	}

	public void setCust_alternate_EmailId(String cust_alternate_EmailId) {
		this.cust_alternate_EmailId = cust_alternate_EmailId;
	}

	public String getEscalation_level() {
		return escalation_level;
	}

	public void setEscalation_level(String escalation_level) {
		this.escalation_level = escalation_level;
	}

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

	public Integer getCompany_branch_id() {
		return company_branch_id;
	}

	public void setCompany_branch_id(Integer company_branch_id) {
		this.company_branch_id = company_branch_id;
	}

	public Integer getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}

	public Integer getCompany_id() {
		return company_id;
	}

	public void setCompany_id(Integer company_id) {
		this.company_id = company_id;
	}

	public Integer getCust_branch_id() {
		return cust_branch_id;
	}

	public void setCust_branch_id(Integer cust_branch_id) {
		this.cust_branch_id = cust_branch_id;
	}

	public CCustomerContactsViewModel(int customer_contact_id, String company_name, String company_branch_name,
	                                  String customer_name, String customer_branch_name, String cust_contact_person, String cust_designation,
	                                  String cust_contact_no, String cust_email_id, String cust_alternate_contact, String cust_alternate_EmailId,
	                                  String escalation_level, boolean is_delete, boolean is_active, String created_by, String modified_by,
	                                  String deleted_by, Integer company_branch_id, Integer customer_id, Integer company_id,
	                                  Integer cust_branch_id) {
		super();
		this.customer_contact_id = customer_contact_id;
		this.company_name = company_name;
		this.company_branch_name = company_branch_name;
		this.customer_name = customer_name;
		this.customer_branch_name = customer_branch_name;
		this.cust_contact_person = cust_contact_person;
		this.cust_designation = cust_designation;
		this.cust_contact_no = cust_contact_no;
		this.cust_email_id = cust_email_id;
		this.cust_alternate_contact = cust_alternate_contact;
		this.cust_alternate_EmailId = cust_alternate_EmailId;
		this.escalation_level = escalation_level;
		this.is_delete = is_delete;
		this.is_active = is_active;
		this.created_by = created_by;
		this.modified_by = modified_by;
		this.deleted_by = deleted_by;
		this.company_branch_id = company_branch_id;
		this.customer_id = customer_id;
		this.company_id = company_id;
		this.cust_branch_id = cust_branch_id;
	}

	public CCustomerContactsViewModel() {
		super();
		// TODO Auto-generated constructor stub
	}


}
