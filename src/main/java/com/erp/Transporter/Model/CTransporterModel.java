package com.erp.Transporter.Model;

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
@Table(name = "cm_transporter")
public class CTransporterModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "transporter_id")
	private int transporter_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String transporter_name;
	private String transporter_short_name;
	private String transporter_vendor_code;
	private String transporter_address1;
	private String transporter_address2;
	private Integer transporter_city_id;
	private String transporter_pincode;
	private Integer transporter_state_id;
	private Integer transporter_district_id;
	private Integer transporter_country_id;
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
	private Integer transporter_payment_term_id;
	private String transporter_gl_codes;
	private String transporter_accounts_id;
	private boolean transporter_blocked = Boolean.FALSE;
	private String transporter_blocked_remark;
	private boolean transporter_payment_blocked = Boolean.FALSE;
	private String transporter_payment_blocked_remark;
	private String transporter_ratings;
	private String transporter_tally_id;
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


}
