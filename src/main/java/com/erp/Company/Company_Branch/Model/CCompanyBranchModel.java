package com.erp.Company.Company_Branch.Model;

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
@Table(name = "cm_company_branch")
public class CCompanyBranchModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "company_branch_id")
	private int company_branch_id;
	//field Added by Dipti (Testing 1.1)
	private String branch_type;
	private String company_branch_type;
	//field Added by Dipti (Testing 1.1)
	private String company_branch_name;
	private String branch_short_name;
	private String branch_address1;
	private String branch_address2;
	private String branch_pincode;
	private String branch_phone_no;
	private String branch_cell_no;
	@Column(name = "branch_EmailId")
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
	private Boolean is_sez = Boolean.FALSE;
	private boolean is_active = Boolean.TRUE;
	private boolean is_delete = Boolean.FALSE;
	private int company_id;
	private long branch_city_id;
	private long branch_country_id;
	private long branch_district_id;
	private long branch_state_id;
	private Integer branch_head_id;
	private Integer branch_subhead_id;
	private String created_by;
	@CreationTimestamp
	@Column(name = "created_on", updatable = false)
	private Date created_on;
	private String modified_by;
	@UpdateTimestamp
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;


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
