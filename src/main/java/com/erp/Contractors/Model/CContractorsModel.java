package com.erp.Contractors.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "cm_contractor")
public class CContractorsModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "contractor_id")
	private Integer contractor_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String contractor_name;
	private String contractor_short_name;
	private String contractor_vendor_code;
	private String contractor_address1;
	private String contractor_address2;
	private Integer contractor_city_id;
	private String contractor_pincode;
	private Integer contractor_state_id;
	private Integer contractor_district_id;
	private Integer contractor_country_id;
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
	private String contractor_bank_id;
	private String contractor_bank_account_type;
	private String contractor_bank_account_no;
	private String contractor_bank_ifsc_code;
	private Integer contractor_payment_term_id;
	private String contractor_gl_codes;
	private String contractor_accounts_id;
	private boolean contractor_blocked = Boolean.FALSE;
	private String contractor_blocked_remark;
	private boolean contractor_payment_blocked = Boolean.FALSE;
	private String contractor_payment_blocked_remark;
	private String contractor_ratings;
	private String contractor_tally_id;
	private boolean is_active = Boolean.TRUE;
	private boolean is_delete = Boolean.FALSE;
	@Column(name = "created_by", updatable = false)
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
