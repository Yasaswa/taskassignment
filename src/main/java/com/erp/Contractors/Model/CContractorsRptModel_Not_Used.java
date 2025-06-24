package com.erp.Contractors.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Immutable
@Subselect("Select * From cmv_contractor_rpt")
public class CContractorsRptModel_Not_Used {

	@Id
	private String contractor_id;
	private String company_name;
	private String company_branch_name;
	private String contractor_name;
	private String contractor_short_name;
	private String contractor_payment_terms;
	private String contractor_ratings;
	private String contractor_gl_codes;
	private String contractor_accounts_id;
	private String contractor_vendor_code;
	private String contractor_address1;
	private String contractor_address2;
	private String contractor_pincode;
	private String state_name;
	private String city_name;
	private String contractor_phone_no;
	private String contractor_cell_no;
	private String contractor_EmailId;
	private String contractor_website;
	private String contractor_gst_no;
	private String contractor_pan_no;
	private String contractor_udyog_adhar_no;
	private String contractor_vat_no;
	private String contractor_service_tax_no;
	private String contractor_excise_no;
	private String contractor_cst_no;
	private String contractor_bst_no;
	private String bank_name1;
	private String contractor_bank_account_type;
	private String contractor_bank_account_no;
	private String contractor_bank_ifsc_code;
	private String contractor_tally_id;
	private String contractor_blocked;
	private String contractor_blocked_remark;
	private String contractor_payment_blocked;
	private String contractor_payment_blocked_remark;
	private String is_active;
	private String is_delete;
	private String created_by;
	private String created_on;
	private String modified_by;
	private String modified_on;
	private String deleted_by;
	private String deleted_on;
	private String company_id;
	private String company_branch_id;
}
