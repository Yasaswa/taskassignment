package com.erp.Customer_Branch.Model;

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
@Table(name = "cm_customer_branch")
public class CCustomerBranchModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cust_branch_id")
	private int cust_branch_id;
	private Integer company_id;
	//field Added By Dipti (Testing 1.1)
	private String branch_type;
	//field Added By Dipti(Testing 1.1)
	private Integer company_branch_id;
	private Integer cust_branch_district_id;
	private Integer cust_branch_country_id;
	private Integer cust_branch_city_id;
	private Integer cust_branch_state_id;
	private String cust_branch_type;
	private Integer customer_id;
	private String cust_branch_name;
	private String cust_branch_short_name;
	private String cust_branch_vendor_code;
	private String cust_branch_address1;
	private String cust_branch_address2;
	private String cust_branch_pincode;
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
	private Integer cust_branch_payment_term_id;
	private String cust_branch_rating;
	private String cust_branch_gl_codes;
	private String cust_branch_accounts_id;
	private boolean cust_branch_blocked = Boolean.FALSE;
	private String cust_branch_blocked_remark;
	private boolean cust_branch_payment_blocked = Boolean.FALSE;
	private String cust_branch_payment_blocked_remark;
	private Integer pysical_distance;
	private boolean is_sez = Boolean.FALSE;
	private String sez_name;
	private String cust_branch_tally_id;
	private boolean is_active = Boolean.TRUE;
	private boolean is_delete = Boolean.FALSE;
	@CreationTimestamp
	@Column(name = "created_on", updatable = false)
	private Date created_on;
	@UpdateTimestamp
	private Date modified_on;
	private Date deleted_on;
	private String created_by;
	private String modified_by;
	private String deleted_by;

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


	public boolean isCust_branch_blocked() {
		return cust_branch_blocked;
	}

	public void setCust_branch_blocked(boolean cust_branch_blocked) {
		this.cust_branch_blocked = cust_branch_blocked;
	}

	public boolean isCust_branch_payment_blocked() {
		return cust_branch_payment_blocked;
	}

	public void setCust_branch_payment_blocked(boolean cust_branch_payment_blocked) {
		this.cust_branch_payment_blocked = cust_branch_payment_blocked;
	}
}
