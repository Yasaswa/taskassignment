package com.erp.MtSalesInvoiceMasterTrading.Model;

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
@Subselect("select * from  ftv_sales_invoice_master_trading")
public class CMtSalesInvoiceMasterTradingViewModel {

	@Id
	private int sales_invoice_master_transaction_id;
	private String company_branch_name;
	private String sales_invoice_no;
	private String sales_invoice_date;
	private Integer sales_invoice_version;
	private String sales_invoice_status_desc;
	private String sales_order_no;
	private String sales_order_version;
	private String sales_order_Date;
	private String customer_order_no;
	private String customer_order_Date;
	private String customer_name;
	private String customer_state_name;
	private String consignee_name;
	private String consignee_state_name;
	private String approved_date;
	private String sales_invoice_remark;
	private String other_terms_conditions;
	private String customer_gst_no;
	private String consignee_gst_no;
	private String customer_pan_no;
	private String consignee_pan_no;
	private String transporter_gst_no;
	private String vehicle_no;
	private String vehicle_type;
	private String transporter_name;
	private String transporter_challan_no;
	private String transporter_challan_date;
	private String e_inv_irn_no;
	private String e_inv_irn_date;
	private String e_inv_ack_no;
	private String e_inv_ack_date;
	private String e_inv_ewb_no;
	private String e_inv_ewb_valid_till_date;
	private String loading_time;
	private String transport_mode;
	private String transaction_type;
	private String is_service;
	private String is_sez;
	private String interim;
	private Date payment_schedule_date;
	private Double basic_total;
	private Double transport_amount;
	private Double freight_amount;
	private boolean is_freight_taxable;
	private Integer freight_hsn_code_id;
	private String freight_fg_hsn_sac_code;
	private Double freight_fg_hsn_sac_rate;
	private Double packing_amount;
	private Double discount_percent;
	private Double discount_amount;
	private Double other_amount;
	private Double taxable_total;
	private Double cgst_percent;
	private Double cgst_total;
	private Double sgst_percent;
	private Double sgst_total;
	private Double igst_percent;
	private Double igst_total;
	private Double roundoff;
	private Double grand_total;
	private Double e_transaction_type;
	private Double tcs_amount;
	private Double cess_amount;
	private Double stcess_amount;
	private Double cess_advance_amount;
	private Integer agent_id;
	private String agent_name;
	private Double agent_percent;
	private Double agent_amount;
	private String agent_paid_status;
	private String sales_invoice_creation_type_desc;
	private String sales_invoice_creation_type;
	private String sales_invoice_status;
	private String sales_invoice_type;
	private String remark;
	private String company_name;
	private String financial_year;
	private String customer_email;
	private String customer_city_name;
	private String consignee_email;
	private String consignee_city_name;
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
	private Integer sales_invoice_type_id;
	private Integer customer_id;
	private String customer_contacts_ids;
	private Integer customer_state_id;
	private Integer customer_city_id;
	private Integer consignee_id;
	private Integer consignee_state_id;
	private Integer consignee_city_id;
	private Integer transporter_id;
	private Integer approved_by_id;
	private String cust_branch_address;
	private String customer_state_code;
	private String consignee_branch_address;
	private String consignee_state_code;
	private String branch_address1;
	private String branch_address2;
	private String branch_EmailId;
	private String branch_phone_no;
	private String branch_gst_no;
	private String branch_pan_no;
	private String branch_state_code;
	private String branch_state_name;

}
