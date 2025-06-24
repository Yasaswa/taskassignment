package com.erp.Agents.Agent.Model;

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
@Subselect("select * from cmv_agent")
public class CAgentViewModel {

	@Id
	private int agent_id;
	private String company_name;
	private String company_branch_name;
	private String agent_name;
	private String agent_short_name;
	private String agent_payment_terms;
	private String agent_gl_codes;
	private String agent_accounts_id;
	private String agent_vendor_code;
	private String agent_address1;
	private String agent_address2;
	private String agent_pincode;
	private String state_name;
	private String city_name;
	private String agent_phone_no;
	private String agent_cell_no;
	private String agent_EmailId;
	private String agent_website;
	private String agent_gst_no;
	private String agent_gst_division;
	// Added by mohit and use for db 1.1
	private String agent_region;
	private boolean agent_blocked;
	private String agent_blocked_remark;
	private boolean agent_payment_blocked;
	private String agent_payment_blocked_remark;
	private double agent_std_percent;
	private String agent_ratings;
// Added by mohit and use for db 1.1

	private String agent_gst_range;
	private String agent_pan_no;
	private String agent_udyog_adhar_no;
	private String agent_vat_no;
	private String agent_service_tax_no;
	private String agent_excise_no;
	private String agent_cst_no;
	private String agent_bst_no;
	private String agent_tally_id;
	private String agent_username;
	private String agent_password;
	private String Active;
	private String Deleted;
	private boolean is_delete;
	private boolean is_active;
	private String created_by;
	private String modified_by;
	private String deleted_by;
	private Integer company_id;
	private Integer agent_city_id;
	private Integer agent_state_id;
	private Integer agent_district_id;
	private Integer agent_country_id;
	private Integer company_branch_id;
	private Integer field_id;
	private String field_name;

	public boolean isIs_delete() {
		return is_delete;
	}

	public void setIs_delete(boolean is_delete) {
		this.is_delete = is_delete;
	}

	public boolean isIs_active() {
		return is_active;
	}

	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
	}


}
