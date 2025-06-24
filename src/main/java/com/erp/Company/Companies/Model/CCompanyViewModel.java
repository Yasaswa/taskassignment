package com.erp.Company.Companies.Model;

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
@Subselect("select * from cmv_company")
public class CCompanyViewModel {

	@Id
	private long company_id;

	private String company_type_name;

	private String company_legal_name;

	private String company_trade_name;

	private String company_branch_type;

	private String company_short_name;

	private String company_sector;

	private String nature_of_business;

	private String registration_no;

	private String registration_date;

	private String establish_date;

	private String company_branch_name;

	private String company_address1;

	private String company_address2;

	private String company_pincode;

	private String state_name;

	private String city_name;

	private String company_phone_no;

	private String company_cell_no;

	private String company_EmailId;

	private String company_website;

	private String company_linkedin_profile;

	private String company_twitter_profile;

	private String company_gst_no;

	private String company_gst_division;

	private String company_gst_range;

	private String company_pan_no;

	private String company_udyog_adhar_no;

	private String company_vat_no;

	private String company_service_tax_no;

	private String company_excise_no;

	private String company_cst_no;

	private String company_bst_no;

	private String company_tally_id;

	private String company_head_name;

	private String company_sub_head_name;

	private Boolean is_sez = Boolean.TRUE;

	private Boolean is_active = Boolean.TRUE;

	private Boolean is_delete = Boolean.FALSE;

	private String created_on;

	private String modified_on;

	private String deleted_on;

	private long company_state_id;

	private long company_city_id;

	private long company_head_id;

	private long company_subhead_id;

	private String created_by;

	private String modified_by;

	private String deleted_by;

	private long company_type_id;

	private long company_branch_id;


	public Boolean getIs_sez() {
		return is_sez;
	}

	public void setIs_sez(Boolean is_sez) {
		this.is_sez = is_sez;
	}

	public Boolean getIs_active() {
		return is_active;
	}

	public void setIs_active(Boolean is_active) {
		this.is_active = is_active;
	}

	public Boolean getIs_delete() {
		return is_delete;
	}

	public void setIs_delete(Boolean is_delete) {
		this.is_delete = is_delete;
	}
}
