package com.erp.Company.Company_Bank.Model;

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
@Subselect("select * from cmv_company_banks")
public class CCompanyBankViewModel {

	@Id
	private long company_bank_id;
	private String currency_type;
	private String company_branch_bank_name;
	private String company_branch_bank_branch_name;
	private String company_branch_bank_account_type;
	private String company_branch_bank_account_no;
	private String company_branch_bank_ifsc_code;
	private String company_branch_bank_swift_code;
	private String company_branch_bank_gst_no;
	private String bank_name;
	private String bank_branch_name;
	private String bank_account_no;
	private String bank_ifsc_code;
	private String bank_swift_code;
	private Double opening_balance;
	private Double closing_balance;
	private String bank_branch_address;
	private String bank_branch_email_id;
	private String bank_branch_contact_no;
	private String bank_gst_no;
	private String account_type;
	private String account_category;
	private Integer authorized_person_count;
	private String authorized_person1;
	private String authorized_person2;
	private String authorized_person3;
	private String bank_gl_codes;
	private String bank_accounts_id;
	private String Active;
	private String Deleted;
	private boolean is_active;
	private boolean is_delete;
	private String created_by;
	private String modified_by;
	private String deleted_by;
	private Integer company_id;
	private String company_name;
	private Integer company_branch_id;
	private String company_branch_name;
	private Integer bank_id;


}
