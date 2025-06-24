package com.erp.Contractors.Model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "cm_contractor_banks")
public class CContractorBankModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "contractor_bank_id")
	private int contractor_bank_id;
	private Integer company_id;
	private Integer company_branch_id;
	
	private Integer contractor_id;
	private Integer bank_id;
	private String contractor_bank_name;
	private String contractor_bank_branch_name;
	private String contractor_bank_account_type;
	private String contractor_bank_account_no;
	private String contractor_bank_ifsc_code;
	private String contractor_bank_swift_code;
	private String currency_type;
 	private String contractor_bank_gst_no;
	private String contractor_bank_gl_codes;
	private String contractor_bank_accounts_id;
 	private boolean is_active = Boolean.TRUE;
	private boolean is_delete = Boolean.FALSE;
	@Column(name = "created_by", updatable = false)
	private String created_by;
	@CreationTimestamp
	@Column(name = "created_on", updatable = false)
	private Date created_on;
	private String modified_by;
	@UpdateTimestamp
	private Date modified_on;
	private Date deleted_on;
	private String deleted_by;
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