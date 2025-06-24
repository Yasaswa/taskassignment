package com.erp.Company.Company_Branch.Model;

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
@Subselect("select * from cmv_company_branch_rpt")
public class CCompanyBranchRptModel_Not_Used {
	@Id
	private String company_branch_id;
	private String company_branch_name;
	//field Added by Dipti (Testing 1.1)
	private String branch_type;
	private String company_branch_type;

	//field Added by Dipti (Testing 1.1)
	private String branch_short_name;
	private String branch_address1;
	private String branch_address2;
	private String branch_pincode;
	private String state_name;
	private String district_name;
	private String country_name;
	private String city_name;
	private String branch_phone_no;
	private String branch_cell_no;
	private String branch_EmailId;
	private String branch_website;
	private String branch_linkedin_profile;
	private String branch_twitter_profile;
	private String branch_facebook_profile;
	private String branch_gst_no;
	private String branch_gst_division;
	private String branch_gst_range;
	private String branch_pan_no;
	private String branch_udyog_adhar_no;
	private String branch_vat_no;
	private String branch_service_tax_no;
	private String branch_excise_no;
	private String branch_cst_no;
	private String branch_bst_no;
	private String branch_tally_id;
	private String branch_head_name;
	private String branch_sub_head_name;
	private String is_sez;
	private String is_active;
	private String is_delete;
	private String company_id;
	private String company_name;
	private String branch_country_id;
	private String branch_state_id;
	private String branch_district_id;
	private String branch_city_id;
	private String branch_head_id;
	private String branch_subhead_id;
	private String created_by;
	private String modified_by;
	private String deleted_by;
	private String field_id;
	private String field_name;

}
