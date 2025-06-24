package com.erp.Agents.Agent.Model;

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
@Table(name = "cm_agent")
public class CAgentModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "agent_id")
	private int agent_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String agent_name;
	private String agent_short_name;
	private String agent_vendor_code;
	private String agent_address1;
	private String agent_address2;
	private Integer agent_city_id;
	private String agent_pincode;
	private Integer agent_state_id;
	private Integer agent_district_id;
	private Integer agent_country_id;
	private String agent_region;
	private String agent_phone_no;
	private String agent_cell_no;
	private String agent_EmailId;
	private String agent_website;
	private String agent_gst_no;
	private String agent_gst_division;
	private String agent_gst_range;
	private String agent_pan_no;
	private String agent_udyog_adhar_no;
	private String agent_vat_no;
	private String agent_service_tax_no;
	private String agent_excise_no;
	private String agent_cst_no;
	private String agent_bst_no;
	private Integer agent_payment_term_id;
	private String agent_gl_codes;
	private String agent_accounts_id;
	private boolean agent_blocked = Boolean.FALSE;
	private String agent_blocked_remark;
	private double agent_std_percent;
	private boolean agent_payment_blocked = Boolean.FALSE;
	private String agent_payment_blocked_remark;
	private String agent_ratings;
	private String agent_tally_id;
	private String username;
	private String password;
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

	public boolean isAgent_payment_blocked() {
		return agent_payment_blocked;
	}

	public void setAgent_payment_blocked(boolean agent_payment_blocked) {
		this.agent_payment_blocked = agent_payment_blocked;
	}

	public boolean isAgent_blocked() {
		return agent_blocked;
	}

	public void setAgent_blocked(boolean agent_blocked) {
		this.agent_blocked = agent_blocked;
	}

}
