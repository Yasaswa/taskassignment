package com.erp.Payment_Terms.Model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "cm_payment_terms")
public class CPaymentTermsModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "payment_terms_id")
	private int payment_terms_id;
	private Integer company_id;
	private String payment_terms_type;
	private String payment_terms_name;
	private Integer payment_terms_days;
	private Integer payment_terms_grace_days;
	private String remark;
	private boolean is_active = Boolean.TRUE;
	private boolean is_delete = Boolean.FALSE;
	@Column(name = "created_by", updatable = false)
	private String created_by;
	@CreationTimestamp
	@Column(name = "created_on", updatable = false)
	private Date created_on;
	private String modified_by;
	@UpdateTimestamp
	@Column(name = "modified_on", updatable = false)
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;

	public int getPayment_terms_id() {
		return payment_terms_id;
	}

	public void setPayment_terms_id(int payment_terms_id) {
		this.payment_terms_id = payment_terms_id;
	}

	public Integer getCompany_id() {
		return company_id;
	}

	public void setCompany_id(Integer company_id) {
		this.company_id = company_id;
	}

	public String getPayment_terms_type() {
		return payment_terms_type;
	}

	public void setPayment_terms_type(String payment_terms_type) {
		this.payment_terms_type = payment_terms_type;
	}

	public String getPayment_terms_name() {
		return payment_terms_name;
	}

	public void setPayment_terms_name(String payment_terms_name) {
		this.payment_terms_name = payment_terms_name;
	}

	public Integer getPayment_terms_days() {
		return payment_terms_days;
	}

	public void setPayment_terms_days(Integer payment_terms_days) {
		this.payment_terms_days = payment_terms_days;
	}

	public Integer getPayment_terms_grace_days() {
		return payment_terms_grace_days;
	}

	public void setPayment_terms_grace_days(Integer payment_terms_grace_days) {
		this.payment_terms_grace_days = payment_terms_grace_days;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public Date getCreated_on() {
		return created_on;
	}

	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
	}

	public String getModified_by() {
		return modified_by;
	}

	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}

	public Date getModified_on() {
		return modified_on;
	}

	public void setModified_on(Date modified_on) {
		this.modified_on = modified_on;
	}

	public String getDeleted_by() {
		return deleted_by;
	}

	public void setDeleted_by(String deleted_by) {
		this.deleted_by = deleted_by;
	}

	public Date getDeleted_on() {
		return deleted_on;
	}

	public void setDeleted_on(Date deleted_on) {
		this.deleted_on = deleted_on;
	}

	public CPaymentTermsModel(int payment_terms_id, Integer company_id, String payment_terms_type,
	                          String payment_terms_name, Integer payment_terms_days, Integer payment_terms_grace_days, String remark,
	                          boolean is_active, boolean is_delete, String created_by, Date created_on, String modified_by,
	                          Date modified_on, String deleted_by, Date deleted_on) {
		super();
		this.payment_terms_id = payment_terms_id;
		this.company_id = company_id;
		this.payment_terms_type = payment_terms_type;
		this.payment_terms_name = payment_terms_name;
		this.payment_terms_days = payment_terms_days;
		this.payment_terms_grace_days = payment_terms_grace_days;
		this.remark = remark;
		this.is_active = is_active;
		this.is_delete = is_delete;
		this.created_by = created_by;
		this.created_on = created_on;
		this.modified_by = modified_by;
		this.modified_on = modified_on;
		this.deleted_by = deleted_by;
		this.deleted_on = deleted_on;
	}

	public CPaymentTermsModel() {
		super();
	}

}
