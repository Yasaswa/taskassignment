package com.erp.Supplier.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Immutable
@Subselect("select * from cmv_supplier")
public class CSupplierViewModel {

	@Id
	private int supplier_id;
	//Added By Dipti Testing 1.1
	//private String branch_type;
	//Added By Dipti Testing 1.1
	private String supplier_type;
	private String supplier_code;
	private String supplier_name;
	private String supplier_short_name;
	private String supplier_sector;
	private String nature_of_business;
	private String supplier_payment_terms;
	private String supplier_rating;
	private String supplier_gl_codes;
	private String supplier_accounts_id;
	private String supplier_history;
	private String supp_branch_short_name;
	private String supp_branch_address1;
	private String supp_branch_address2;
	private String supp_branch_pincode;
	private String city_name;
	private String district_name;
	private String state_name;
	private String country_name;
	private String supp_branch_region;
	private String supp_branch_phone_no;
	private String supp_branch_cell_no;
	private String supp_branch_EmailId;
	private String supp_branch_website;
	private String supp_branch_linkedin_profile;
	private String supp_branch_twitter_profile;
	private String supp_branch_facebook_profile;
	private String supp_branch_gst_no;
	private String supp_branch_gst_division;
	private String supp_branch_gst_range;
	private String supp_branch_pan_no;
	private String supp_branch_udyog_adhar_no;
	private String supp_branch_vat_no;
	private String supp_branch_service_tax_no;
	private String supp_branch_excise_no;
	private String supp_branch_cst_no;
	private String supp_branch_bst_no;
	private String supp_branch_tally_id;
	private String supp_branch_rating;
	private String supp_branch_gl_codes;
	private String supp_branch_accounts_id;
	private Boolean is_sez = Boolean.TRUE;
	private String sez_name;
	private String supplier_username;
	private String supplier_password;
	private String Active;
	private String Deleted;
	private String remark;
	private String company_name;
	private String company_branch_name;
	private Boolean is_active = Boolean.TRUE;
	private Boolean is_delete = Boolean.FALSE;
	private String created_by;
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String deleted_by;
	private String deleted_on;
	private Integer company_id;
	private Integer payment_term_id;
	private Integer company_branch_id;
	private Integer supp_branch_city_id;
	private Integer supp_branch_district_id;
	private Integer supp_branch_state_id;
	private Integer supp_branch_country_id;
	private Integer field_id;
	private String field_name;

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
