package com.erp.MtDispatchChallanDetails.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "mt_dispatch_challan_master_trading")
public class CMtDispatchChallanMasterTradingModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "dispatch_challan_master_transaction_id")
	private int dispatch_challan_master_transaction_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String financial_year;
	private String file_name;
	private Integer dispatch_challan_type_id;
	private String dispatch_challan_type;
	private String dispatch_challan_creation_type;
	private String dispatch_challan_no;
	private String dispatch_challan_date;
	private Integer dispatch_challan_version;
	private Integer customer_id;
	private String customer_contacts_ids;
	private Integer customer_state_id;
	private Integer customer_city_id;
	private String customer_gst_no;
	private String customer_pan_no;
	private Integer consignee_id;
	private Integer consignee_state_id;
	private Integer consignee_city_id;
	private String consignee_gst_no;
	private String consignee_pan_no;
	private Integer transporter_id;
	private Integer transporter_state_id;
	private String transporter_gst_no;
	private Integer expected_branch_id;
	private Integer expected_branch_state_id;
	private Integer expected_branch_city_id;
	private String vehicle_no;
	private String vehicle_type;
	private String transporter_challan_no;
	private String transporter_challan_date;
	private String transport_mode;
	private String transaction_type;
	private String is_service;
	private String is_sez;
	private String interim;
	private String loading_time;
	private Integer approved_by_id;
	private String approved_date;
	private String dispatch_challan_status;
	private String overall_schedule_date;
	private double basic_total;
	private double transport_amount;
	private double freight_amount;
	private boolean is_freight_taxable = Boolean.FALSE;
	private Integer freight_hsn_code_id;
	private double packing_amount;
	private double discount_percent;
	private double discount_amount;
	private double other_amount;
	private double taxable_total;
	private double cgst_percent;
	private double cgst_total;
	private double sgst_percent;
	private double sgst_total;
	private double igst_percent;
	private double igst_total;
	private double roundoff;
	private double grand_total;
	private Integer agent_id;
	private double agent_percent;
	private double agent_amount;
	private String agent_paid_status;
	private String dispatch_challan_remark;
	private String other_terms_conditions;
	private String mail_sent_status;
	private String remark;
	private String driver_name;
	private boolean is_active = Boolean.TRUE;
	private boolean is_delete = Boolean.FALSE;
	@Column(name = "created_by", updatable = false)
	private String created_by;
	@CreationTimestamp
	@Column(name = "created_on", updatable = false)
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;
	private String dispatch_type;

	private String sales_dispatch_type;
	private String dispatch_hsnTax_type;
	private String dispatch_sales_type;
	private String dispatch_voucher_type;
	private String job_type;
	private String dispatch_payment_terms;



	public String getDispatch_challan_date() {
		return dispatch_challan_date;
	}

	public void setDispatch_challan_date(String dispatch_challan_date) {
		if (dispatch_challan_date == null || dispatch_challan_date.isEmpty()) {
			this.dispatch_challan_date = null;
		} else {
			this.dispatch_challan_date = dispatch_challan_date;
		}
	}

	public String getTransporter_challan_date() {
		return transporter_challan_date;
	}

	public void setTransporter_challan_date(String transporter_challan_date) {
		if (transporter_challan_date == null || transporter_challan_date.isEmpty()) {
			this.transporter_challan_date = null;
		} else {
			this.transporter_challan_date = transporter_challan_date;
		}
	}

	public String getApproved_date() {
		return approved_date;
	}

	public void setApproved_date(String approved_date) {
		if (approved_date == null || approved_date.isEmpty()) {
			this.approved_date = null;
		} else {
			this.approved_date = approved_date;
		}
	}

	public String getOverall_schedule_date() {
		return overall_schedule_date;
	}

	public void setOverall_schedule_date(String overall_schedule_date) {
		if (overall_schedule_date == null || overall_schedule_date.isEmpty()) {
			this.overall_schedule_date = null;
		} else {
			this.overall_schedule_date = overall_schedule_date;
		}
	}

	public boolean isIs_freight_taxable() {
		return is_freight_taxable;
	}

	public void setIs_freight_taxable(boolean is_freight_taxable) {
		this.is_freight_taxable = is_freight_taxable;
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
