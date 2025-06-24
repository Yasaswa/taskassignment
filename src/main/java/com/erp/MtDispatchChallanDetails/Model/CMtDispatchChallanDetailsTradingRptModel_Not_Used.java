package com.erp.MtDispatchChallanDetails.Model;

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
@Subselect("select * from  mtv_dispatch_challan_details_trading_rpt")
public class CMtDispatchChallanDetailsTradingRptModel_Not_Used {

	@Id
	private String dispatch_challan_details_transaction_id;
	private String dispatch_challan_no;
	private String dispatch_challan_date;
	private String dispatch_challan_version;
	private String dispatch_challan_status_desc;
	private String company_branch_name;
	private String consignee_name;
	private String consignee_state_name;
	private String approved_date;
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
	private String agent_name;
	private String agent_percent;
	private String agent_amount;
	private String agent_paid_status;
	private String dispatch_challan_creation_type_desc;
	private String dispatch_challan_item_status_desc;
	private String sales_order_no;
	private String sales_order_Date;
	private String sales_order_version;
	private String customer_order_no;
	private String customer_order_Date;
	private String customer_order_version;
	private String dispatch_schedule_no;
	private String dispatch_schedule_date;
	private String dispatch_schedule_version;
	private String sr_no;
	private String product_material_id;
	private String so_sr_no;
	private String product_material_print_name;
	private String product_material_tech_spect;
	private String product_material_unit_id;
	private String product_unit_name;
	private String product_material_packing_id;
	private String product_packing_name;
	private String dispatch_quantity;
	private String dispatch_weight;
	private String hsn_sac_id;
	private String hsn_sac_code;
	private String hsn_sac_rate;
	private String hsn_sac_percent;
	private String item_rate;
	private String item_amount;
	private String item_discount_percent;
	private String item_discount_amount;
	private String item_taxable_amount;
	private String item_cgst_percent;
	private String item_cgst_amount;
	private String item_sgst_percent;
	private String item_sgst_amount;
	private String item_igst_percent;
	private String item_igst_amount;
	private String item_total_amount;
	private String dispatch_return_quantity;
	private String dispatch_return_weight;
	private String pending_quantity;
	private String pending_weight;
	private String expected_schedule_date;
	private String delayed_days;
	private String invoice_quantity;
	private String invoice_weight;
	private String dispatch_challan_item_status;
	private String dispatch_challan_type;
	private String remark;
	private String company_name;
	private String financial_year;
	private String customer_email;
	private String customer_city_name;
	private String consignee_email;
	private String consignee_city_name;
	private String is_active;
	private String is_delete;
	private String created_by;
	private String created_on;
	private String modified_by;
	private String modified_on;
	private String deleted_by;
	private String deleted_on;
	private String company_id;
	private String dispatch_challan_master_transaction_id;
	private String company_branch_id;
	private String expected_branch_id;
	private String dispatch_challan_type_id;
	private String customer_id;
	private String customer_contacts_ids;
	private String customer_state_id;
	private String customer_city_id;
	private String consignee_id;
	private String consignee_state_id;
	private String consignee_city_id;
	private String transporter_id;
	private String approved_by_id;


}
