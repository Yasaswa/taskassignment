package com.erp.Transporter.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Immutable
@Subselect("select * from cmv_transporter")
public class CTransporterViewModel {

	@Id
	private int transporter_id;
	private Integer company_id;
	private String company_name;
	private String company_branch_name;
	private String transporter_name;
	private String transporter_short_name;
	private String transporter_payment_terms;
	private String transporter_gl_codes;
	private String transporter_accounts_id;
	private String transporter_vendor_code;
	private String transporter_address1;
	private String transporter_address2;
	private String transporter_pincode;
	private String country_name;
	private String state_name;
	private String district_name;
	private String city_name;
	private String transporter_region;
	private String transporter_phone_no;
	private String transporter_cell_no;
	private String transporter_EmailId;
	private String transporter_website;
	private String transporter_gst_no;
	private String transporter_gst_division;
	private String transporter_gst_range;
	private String transporter_pan_no;
	private String transporter_udyog_adhar_no;
	private String transporter_service_tax_no;
	private String transporter_tally_id;
	private String transporter_username;
	private String transporter_password;
	//  Added By Mohit
//	private String Active;
//	private String Deleted;
	private boolean is_active = Boolean.TRUE;
	private boolean is_delete = Boolean.FALSE;
	private String created_by;
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;
	private Integer transporter_state_id;
	private Integer transporter_payment_term_id;
	private Integer transporter_city_id;
	private Integer transporter_district_id;
	private Integer transporter_country_id;
	private Integer company_branch_id;
	private Integer field_id;
	private String field_name;
	private boolean transporter_blocked = Boolean.FALSE;
	private String transporter_blocked_remark;
	private boolean transporter_payment_blocked = Boolean.FALSE;
	private String transporter_payment_blocked_remark;
	private String transporter_ratings;

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
