package com.erp.Supplier.Model;

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
@Subselect("select * from cmv_supplier_branch")
public class CSupplierBranchViewModel {

	@Id
	private long supp_branch_id;
	private Integer supplier_id;
	private Integer company_id;
	private Integer company_branch_id;
	private Integer supp_branch_city_id;
	private Integer supp_branch_district_id;
	private Integer supp_branch_state_id;
	private Integer supp_branch_country_id;
	private Integer supp_branch_payment_term_id;
	private String company_name;
	private String company_branch_name;
	private String supplier_type;
	private String supplier_code;
	private String branch_type;
	private String supp_branch_type;
	private String supplier_name;
	private String supplier_short_name;
	private String supplier_sector;
	private String nature_of_business;
	private String supplier_payment_terms;
	private String supplier_rating;
	private String supplier_gl_codes;
	private String supplier_accounts_id;
	private String supplier_history;
	private String supp_branch_name;
	private String supp_branch_vendor_code;
	private String supp_branch_short_name;
	private String supp_branch_address1;
	private String supp_branch_address2;
	private String supp_branch_pincode;
	private String state_name;
	private String city_name;
	private String district_name;
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
	private String supp_branch_payment_terms;
	private boolean supp_branch_blocked = Boolean.FALSE;
	private String supp_branch_blocked_remark;
	private boolean supp_branch_payment_blocked = Boolean.FALSE;
	private String supp_branch_payment_blocked_remark;
	private boolean is_sez = Boolean.FALSE;
	private String sez_name;
	private String Active;
	private String Deleted;
	private boolean is_active = Boolean.TRUE;
	private boolean is_delete = Boolean.FALSE;
	private String created_by;
	private String modified_by;
	private String deleted_by;
	private Integer field_id;
	private String field_name;

	public boolean isIs_sez() {
		return is_sez;
	}

	public void setIs_sez(boolean is_sez) {
		this.is_sez = is_sez;
	}

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

	public boolean isSupp_branch_blocked() {
		return supp_branch_blocked;
	}

	public void setSupp_branch_blocked(boolean supp_branch_blocked) {
		this.supp_branch_blocked = supp_branch_blocked;
	}

	public boolean isSupp_branch_payment_blocked() {
		return supp_branch_payment_blocked;
	}

	public void setSupp_branch_payment_blocked(boolean supp_branch_payment_blocked) {
		this.supp_branch_payment_blocked = supp_branch_payment_blocked;
	}


}
