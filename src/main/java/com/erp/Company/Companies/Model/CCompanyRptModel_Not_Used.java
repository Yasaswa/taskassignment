package com.erp.Company.Companies.Model;

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
@Subselect("select * from cmv_company_rpt")
public class CCompanyRptModel_Not_Used {

	@Id
	private String company_id;
	private String company_type_name;
	private String company_legal_name;
	private String company_trade_name;
	private String company_short_name;
	private String company_sector;
	private String company_branch_type;
	private String nature_of_business;
	private String registration_no;
	private String registration_date;
	private String establish_date;
	private String company_logo_document_path;
	private String company_branch_name;
	private String company_address1;
	private String company_address2;
	private String company_pincode;
	private String city_name;
	private String district_name;
	private String state_name;
	private String country_name;
	private String company_phone_no;
	private String company_cell_no;
	private String company_EmailId;
	private String company_website;
	private String company_linkedin_profile;
	private String company_twitter_profile;
	private String company_facebook_profile;
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
	private String is_active;
	private String is_delete;
	private String created_by;
	private String created_on;
	private String modified_by;
	private String modified_on;
	private String deleted_by;
	private String deleted_on;
	private String company_city_id;
	private String company_district_id;
	private String company_state_id;
	private String company_country_id;
	private String company_head_id;
	private String company_subhead_id;
	private String company_type_id;
	private String company_branch_id;

}
