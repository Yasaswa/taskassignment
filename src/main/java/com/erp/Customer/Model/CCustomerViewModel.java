package com.erp.Customer.Model;

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
@Subselect("select * from cmv_customer")
public class CCustomerViewModel {

	@Id
	private int customer_id;
	private Integer company_id;
	private String company_name;
	private String company_branch_name;
	private String customer_type;
	private String customer_code;
	private String customer_name;
	private String customer_short_name;
	private String customer_sector;
	private String nature_of_business;
	private String customer_payment_terms;
	private String customer_rating;
	private String customer_gl_codes;
	private String customer_accounts_id;
	private String customer_history;
	private String cust_branch_short_name;
	private String cust_branch_address1;
	private String cust_branch_address2;
	private String cust_branch_pincode;
	private String state_name;
	private String city_name;
	private String branch_type;
	private String cust_branch_region;
	private String cust_branch_phone_no;
	private String cust_branch_cell_no;
	private String cust_branch_EmailId;
	private String cust_branch_website;
	private String cust_branch_linkedin_profile;
	private String cust_branch_twitter_profile;
	private String cust_branch_facebook_profile;
	private String cust_branch_gst_no;
	private String cust_branch_gst_division;
	private String cust_branch_gst_range;
	private String cust_branch_pan_no;
	private String cust_branch_udyog_adhar_no;
	private String cust_branch_vat_no;
	private String cust_branch_service_tax_no;
	private String cust_branch_excise_no;
	private String cust_branch_cst_no;
	private String cust_branch_bst_no;
	private String cust_branch_tally_id;
	private String cust_branch_rating;
	private String cust_branch_gl_codes;
	private String cust_branch_accounts_id;
	private Boolean is_sez = Boolean.FALSE;
	private String sez_name;
	private Integer pysical_distance;
	private String customer_username;
	private String customer_password;
	private Boolean is_delete = Boolean.FALSE;
	private Boolean is_active = Boolean.TRUE;
	private String Active;
	private String Deleted;
	private String created_by;
	private String modified_by;
	private String deleted_by;
	private Integer company_branch_id;
	private Integer cust_branch_city_id;
	private Integer cust_branch_district_id;
	private Integer cust_branch_state_id;
	private Integer cust_branch_country_id;
	private Integer field_id;
	private Integer payment_term_id;
	private String field_name;
	private String remark;


	public Boolean getIs_delete() {
		return is_delete;
	}

	public void setIs_delete(Boolean is_delete) {
		this.is_delete = is_delete;
	}

	public Boolean getIs_active() {
		return is_active;
	}

	public void setIs_active(Boolean is_active) {
		this.is_active = is_active;
	}
}
