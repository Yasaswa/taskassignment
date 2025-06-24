package com.erp.Company.Company_Bank.Model;

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
@Table(name = "cm_company_banks")
public class CCompanyBankModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "company_bank_id")
	private int company_bank_id;
	private Integer company_id;
	private Integer company_branch_id;
	private Integer bank_id;
	private String currency_type;
	private String company_branch_bank_name;
	private String company_branch_bank_branch_name;
	private String company_branch_bank_account_type;
	private String company_branch_bank_account_no;
	private String company_branch_bank_ifsc_code;
	private String company_branch_bank_swift_code;
	private String company_branch_bank_gst_no;
	private Double opening_balance;
	private Double closing_balance;
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
