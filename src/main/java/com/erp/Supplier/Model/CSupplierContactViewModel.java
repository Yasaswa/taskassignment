package com.erp.Supplier.Model;

import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
@Immutable
@Subselect("select * from cmv_supplier_contacts")
public class CSupplierContactViewModel {

	@Id
	private int supplier_contact_id;
	private String company_name;
	private String company_branch_name;
	private String supplier_name;
	private String supplier_branch_name;
	private String supp_contact_person;
	private String supp_designation;
	private String supp_contact_no;
	private String supp_email_id;
	private String supp_alternate_contact;
	private String supp_alternate_EmailId;
	private String escalation_level;
	private Integer company_id;
	private Integer company_branch_id;
	private Integer supplier_id;
	private Integer supp_branch_id;
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

	public int getSupplier_contact_id() {
		return supplier_contact_id;
	}

	public void setSupplier_contact_id(int supplier_contact_id) {
		this.supplier_contact_id = supplier_contact_id;
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

	public Integer getSupplier_id() {
		return supplier_id;
	}

	public void setSupplier_id(Integer supplier_id) {
		this.supplier_id = supplier_id;
	}

	public Integer getSupp_branch_id() {
		return supp_branch_id;
	}

	public void setSupp_branch_id(Integer supp_branch_id) {
		this.supp_branch_id = supp_branch_id;
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

	public String getSupplier_name() {
		return supplier_name;
	}

	public void setSupplier_name(String supplier_name) {
		this.supplier_name = supplier_name;
	}

	public String getSupplier_branch_name() {
		return supplier_branch_name;
	}

	public void setSupplier_branch_name(String supplier_branch_name) {
		this.supplier_branch_name = supplier_branch_name;
	}

	public String getSupp_contact_person() {
		return supp_contact_person;
	}

	public void setSupp_contact_person(String supp_contact_person) {
		this.supp_contact_person = supp_contact_person;
	}

	public String getSupp_designation() {
		return supp_designation;
	}

	public void setSupp_designation(String supp_designation) {
		this.supp_designation = supp_designation;
	}

	public String getSupp_contact_no() {
		return supp_contact_no;
	}

	public void setSupp_contact_no(String supp_contact_no) {
		this.supp_contact_no = supp_contact_no;
	}

	public String getSupp_email_id() {
		return supp_email_id;
	}

	public void setSupp_email_id(String supp_email_id) {
		this.supp_email_id = supp_email_id;
	}

	public String getSupp_alternate_contact() {
		return supp_alternate_contact;
	}

	public void setSupp_alternate_contact(String supp_alternate_contact) {
		this.supp_alternate_contact = supp_alternate_contact;
	}

	public String getSupp_alternate_EmailId() {
		return supp_alternate_EmailId;
	}

	public void setSupp_alternate_EmailId(String supp_alternate_EmailId) {
		this.supp_alternate_EmailId = supp_alternate_EmailId;
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

	public CSupplierContactViewModel(int supplier_contact_id, Integer company_id, Integer company_branch_id,
	                                 Integer supplier_id, Integer supp_branch_id, String company_name, String company_branch_name,
	                                 String supplier_name, String supplier_branch_name, String supp_contact_person, String supp_designation,
	                                 String supp_contact_no, String supp_email_id, String supp_alternate_contact, String supp_alternate_EmailId,
	                                 String escalation_level, boolean is_delete, boolean is_active, String created_by, String modified_by,
	                                 String deleted_by) {
		super();
		this.supplier_contact_id = supplier_contact_id;
		this.company_id = company_id;
		this.company_branch_id = company_branch_id;
		this.supplier_id = supplier_id;
		this.supp_branch_id = supp_branch_id;
		this.company_name = company_name;
		this.company_branch_name = company_branch_name;
		this.supplier_name = supplier_name;
		this.supplier_branch_name = supplier_branch_name;
		this.supp_contact_person = supp_contact_person;
		this.supp_designation = supp_designation;
		this.supp_contact_no = supp_contact_no;
		this.supp_email_id = supp_email_id;
		this.supp_alternate_contact = supp_alternate_contact;
		this.supp_alternate_EmailId = supp_alternate_EmailId;
		this.escalation_level = escalation_level;
		this.is_delete = is_delete;
		this.is_active = is_active;
		this.created_by = created_by;
		this.modified_by = modified_by;
		this.deleted_by = deleted_by;
	}

	public CSupplierContactViewModel() {
		super();
	}


}
