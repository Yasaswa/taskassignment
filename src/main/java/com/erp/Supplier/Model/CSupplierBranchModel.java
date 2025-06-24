package com.erp.Supplier.Model;

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
@Table(name = "cm_supplier_branch")
public class CSupplierBranchModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "supp_branch_id")
	private int supp_branch_id;
	private Integer company_id;
	private Integer company_branch_id;
	private Integer supplier_id;
	//Added By Dipti Testing 1.1
	private String branch_type;
	//Added By Dipti Testing 1.1
	private String supp_branch_type;
	private String supp_branch_name;
	private String supp_branch_short_name;
	private String supp_branch_vendor_code;
	private String supp_branch_address1;
	private String supp_branch_address2;
	private Integer supp_branch_city_id;
	private String supp_branch_pincode;
	private Integer supp_branch_state_id;
	private Integer supp_branch_district_id;
	private Integer supp_branch_country_id;
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
	private Integer supp_branch_payment_term_id;
	private String supp_branch_rating;
	private String supp_branch_gl_codes;
	private String supp_branch_accounts_id;
	private boolean supp_branch_blocked = Boolean.FALSE;
	private String supp_branch_blocked_remark;
	private boolean supp_branch_payment_blocked = Boolean.FALSE;
	private String supp_branch_payment_blocked_remark;
	private boolean is_sez = Boolean.FALSE;
	private String sez_name;
	private String supp_branch_tally_id;
	private boolean is_active = Boolean.TRUE;
	private boolean is_delete = Boolean.FALSE;
	@CreationTimestamp
	@Column(name = "created_on", updatable = false)
	private Date created_on;
	@UpdateTimestamp
	private Date modified_on;
	private Date deleted_on;
	@Column(name = "created_by", updatable = false)
	private String created_by;
	private String modified_by;
	private String deleted_by;


	public boolean isSupp_branch_payment_blocked() {
		return supp_branch_payment_blocked;
	}

	public void setSupp_branch_payment_blocked(boolean supp_branch_payment_blocked) {
		this.supp_branch_payment_blocked = supp_branch_payment_blocked;
	}

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
}
