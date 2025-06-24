package com.erp.Common.Documents.Model;

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
@Table(name = "am_documents")
public class CDocumentsModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "document_id")
	private int document_id;
	private Integer company_id;
	private String group_id;
	private String document_group;
	private String document_name;
	private String document_registration_no;
	private String document_registration_date;
	private String document_renewal_date;
	private String document_path;
	private String remark;
	private boolean is_delete = Boolean.FALSE;
	@Column(name = "is_active")
	private boolean is_active = Boolean.TRUE;
	private String created_by;
	@CreationTimestamp
	@Column(name = "created_on", updatable = false)
	private Date created_on;
	@UpdateTimestamp
	private Date modified_on;
	private String modified_by;
	private String deleted_by;
	private Date deleted_on;
	private String file_name;


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

	public String getDocument_registration_date() {
		return document_registration_date;
	}

	public void setDocument_registration_date(String document_registration_date) {
		if (document_registration_date == null || document_registration_date.isEmpty()) {
			this.document_registration_date = null;
		} else {
			this.document_registration_date = document_registration_date;
		}
	}

	public String getDocument_renewal_date() {
		return document_renewal_date;
	}

	public void setDocument_renewal_date(String document_renewal_date) {
		if (document_renewal_date == null || document_renewal_date.isEmpty()) {
			this.document_renewal_date = null;
		} else {
			this.document_renewal_date = document_renewal_date;
		}
	}


}
