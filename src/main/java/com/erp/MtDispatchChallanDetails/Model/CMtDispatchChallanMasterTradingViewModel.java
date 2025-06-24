package com.erp.MtDispatchChallanDetails.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Immutable
@Table(name ="mtv_dispatch_challan_master_trading")
public class CMtDispatchChallanMasterTradingViewModel {

	@Id
	private int dispatch_challan_master_transaction_id;
	private String company_branch_name;
	private String dispatch_challan_no;
	private String dispatch_challan_date;
	private Integer dispatch_challan_version;
	private String dispatch_challan_creation_type;
	private String mail_sent_status_desc;
	private String customer_name;
	private String customer_state_name;
	private String consignee_name;
	private String consignee_state_name;
	private String approved_date;
	private String dispatch_challan_remark;
	private String other_terms_conditions;
	private String customer_gst_no;
	private String consignee_gst_no;
	private String customer_pan_no;
	private String consignee_pan_no;
	private String transporter_gst_no;
	private String vehicle_no;
	private String vehicle_type;
	private String transporter_challan_no;
	private String transporter_challan_date;
	private String loading_time;
	private String transport_mode;
	private String transaction_type;
	private String is_service;
	private String is_sez;
	private String interim;
	private String overall_schedule_date;
	private double basic_total;
	private double transport_amount;
	private double freight_amount;
	private boolean is_freight_taxable;
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
	private String agent_name;
	private double agent_percent;
	private double agent_amount;
	private String agent_paid_status;
	private String dispatch_challan_status_desc;
	private String dispatch_challan_status;
	private String dispatch_challan_type;
	private String mail_sent_status;
	private String remark;
	private String company_name;
	private String financial_year;
	private String customer_email;
	private String customer_city_name;
	private String consignee_email;
	private String consignee_city_name;
	private String customer_address1;
	private String consignee_address1;
	private boolean is_active;
	private boolean is_delete;
	private String created_by;
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;
	private Integer company_id;
	private Integer company_branch_id;
	private Integer expected_branch_id;
	private Integer dispatch_challan_type_id;
	private Integer customer_id;
	private String customer_contacts_ids;
	private Integer customer_state_id;
	private Integer customer_city_id;
	private Integer consignee_id;
	private Integer consignee_state_id;
	private Integer consignee_city_id;
	private Integer transporter_id;
	private Integer approved_by_id;
	private Double total_dispatch_quantity;
	private Double total_dispatch_weight;
	private String dispatch_type;


	private String sales_dispatch_type;
	private String dispatch_hsnTax_type;
	private String dispatch_sales_type;
	private String dispatch_voucher_type;
     private String job_type;
	private String driver_name;
	private String dispatch_payment_terms;

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
