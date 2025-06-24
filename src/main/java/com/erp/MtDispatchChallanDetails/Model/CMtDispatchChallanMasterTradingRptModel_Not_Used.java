package com.erp.MtDispatchChallanDetails.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Immutable
@Subselect("select * from  mtv_dispatch_challan_master_trading_rpt")
public class CMtDispatchChallanMasterTradingRptModel_Not_Used {

	@Id
	private String dispatch_challan_master_transaction_id;
	private String company_name;
	private String company_branch_name;
	private String financial_year;
	private String dispatch_challan_creation_type_desc;
	private String dispatch_challan_type;
	private String dispatch_challan_no;
	private String dispatch_challan_date;
	private String dispatch_challan_version;
	private String customer_name;
	private String customer_email;
	private String customer_city_name;
	private String customer_state_name;
	private String consignee_name;
	private String consignee_email;
	private String consignee_city_name;
	private String consignee_state_name;
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
	private String basic_total;
	private String transport_amount;
	private String freight_amount;
	private String is_freight_taxable;
	private String freight_hsn_code_id;
	private String packing_amount;
	private String discount_percent;
	private String discount_amount;
	private String other_amount;
	private String taxable_total;
	private String cgst_percent;
	private String cgst_total;
	private String sgst_percent;
	private String sgst_total;
	private String igst_percent;
	private String igst_total;
	private String roundoff;
	private String grand_total;
	private String agent_id;
	private String agent_percent;
	private String agent_amount;
	private String agent_paid_status;
	private String dispatch_challan_remark;
	private String remark;
	private String is_active;
	private String is_delete;
	private String created_by;
	private String created_on;
	private String modified_by;
	private String modified_on;
	private String deleted_by;
	private String deleted_on;
	private String company_id;
	private String company_branch_id;
	private String customer_id;
	private String customer_contacts_ids;
	private String customer_state_id;
	private String customer_city_id;
	private String consignee_id;
	private String consignee_state_id;
	private String consignee_city_id;
	private String transporter_id;
	private String transporter_state_id;
	private String transporter_city_id;
	private String dispatch_challan_type_id;
	private String approved_by_id;

}
