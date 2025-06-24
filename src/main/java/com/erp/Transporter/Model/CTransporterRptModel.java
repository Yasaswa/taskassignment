package com.erp.Transporter.Model;

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
@Subselect("select * from cmv_transporter_rpt")
public class CTransporterRptModel {

	@Id
	private String transporter_id;
	private String company_name;
	private String company_branch_name;
	private String transporter_name;
	private String transporter_short_name;
	private String transporter_payment_terms;
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
	private String transporter_blocked;
	private String transporter_blocked_remark;
	private String transporter_payment_blocked;
	private String transporter_payment_blocked_remark;
	private String transporter_ratings;
	private String is_active;
	private String is_delete;
	private String created_by;
	private String created_on;
	private String modified_by;
	private String modified_on;
	private String deleted_by;
	private String deleted_on;
	private String transporter_gl_codes;
	private String transporter_accounts_id;
	private String company_branch_id;
	private String company_id;
	private String transporter_state_id;
	private String transporter_city_id;
	private String transporter_district_id;
	private String transporter_country_id;
	private String field_id;
	private String field_name;

}
