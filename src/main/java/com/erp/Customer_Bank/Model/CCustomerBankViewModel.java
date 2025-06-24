package com.erp.Customer_Bank.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Immutable
@Subselect("select * from cmv_customer_banks")
public class CCustomerBankViewModel {

	@Id
	private int cust_bank_id;
	private String customer_name;
	private String customer_branch_name;
	private String customer_bank_name;
	private String customer_bank_branch_name;
	private String cust_bank_account_no;
	private String cust_bank_ifsc_code;
	private String cust_bank_swift_code;
	private String cust_bank_gst_no;
	private String cust_bank_account_type;
	private String currency_type;
	private String cust_bank_gl_codes;
	private String cust_bank_accounts_id;
	private String company_name;
	private String company_branch_name;
	private String Active;
	private String Deleted;
	private boolean is_delete = Boolean.FALSE;
	private boolean is_active = Boolean.TRUE;
	private String created_by;
	private String modified_by;
	private String deleted_by;
	private Integer company_id;
	private Integer company_branch_id;
	private Integer customer_id;
	private Integer cust_branch_id;
	private Integer bank_id;
	private Integer field_id;
	private String field_name;

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
