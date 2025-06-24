package com.erp.Customer_Contacts.Model;

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
@Table(name = "cm_customer_contacts")
public class CCustomerContactsModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_contact_id")
	private int customer_contact_id;
	private Integer company_id;
	private Integer company_branch_id;
	private Integer customer_id;
	private Integer cust_branch_id;
	private String cust_contact_person;
	private String cust_designation;
	private String cust_contact_no;
	private String cust_email_id;
	private String cust_alternate_contact;
	private String cust_alternate_EmailId;
	private String escalation_level;
	private boolean is_delete = Boolean.FALSE;
	private boolean is_active = Boolean.TRUE;
	@CreationTimestamp
	@Column(name = "created_on", updatable = false)
	private Date created_on;
	@UpdateTimestamp
	private Date modified_on;
	private Date deleted_on;
	private String created_by;
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
