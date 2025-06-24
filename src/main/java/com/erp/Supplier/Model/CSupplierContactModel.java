package com.erp.Supplier.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "cm_supplier_contacts")
public class CSupplierContactModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "supplier_contact_id")
	private int supplier_contact_id;
	private Integer company_id;
	private Integer company_branch_id;
	private Integer supp_branch_id;
	private Integer supplier_id;
	private String supp_contact_person;
	private String supp_designation;
	private String supp_contact_no;
	private String supp_email_id;
	private String supp_alternate_contact;
	private String supp_alternate_EmailId;
	private String escalation_level;
	private boolean is_active = Boolean.TRUE;
	private boolean is_delete = Boolean.FALSE;
	@CreationTimestamp
	@Column(name = "created_on", updatable = false)
	private Date created_on;
	@UpdateTimestamp
	private Date modified_on;
	private Date deleted_on;
	@Column(name = "created_by", updatable = false)
	private String created_by;
	private String modified_by;
	private String deleted_by;


}
