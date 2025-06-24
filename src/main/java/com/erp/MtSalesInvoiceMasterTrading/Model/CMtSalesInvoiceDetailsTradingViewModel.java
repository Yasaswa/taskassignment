package com.erp.MtSalesInvoiceMasterTrading.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Immutable
@Subselect("select * from  ftv_sales_invoice_details_trading")
public class CMtSalesInvoiceDetailsTradingViewModel {

	@Id
	private int sales_invoice_details_transaction_id;
	private String company_branch_name;
	private String sales_invoice_no;
	private String sales_invoice_date;
	private Integer sales_invoice_version;
	private String sales_invoice_status_desc;
	private String dispatch_challan_no;
	private String dispatch_challan_date;
	private Integer dispatch_challan_version;
	private String customer_name;
	private String customer_state_name;
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
	private String payment_schedule_date;
	private Double basic_total;
	private Double transport_amount;
	private Double freight_amount;
	private boolean is_freight_taxable;
	private Integer freight_hsn_code_id;
	private String freight_fg_hsn_sac_code;
	private Double freight_fg_hsn_sac_rate;
	private Double packing_amount;
	private Double item_freight_amount;
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
	private Double item_tcs_amount;
	private Double item_cess_amount;
	private Double item_stcess_amount;
	private Double item_cess_advance_amount;
	private Integer agent_id;
	private String agent_name;
	private Double agent_percent;
	private Double agent_amount;
	private String agent_paid_status;
	private String sales_invoice_creation_type_desc;
	private String sales_invoice_item_status_desc;
	@Transient
	private Double item_prev_paid_amount;
	private String sales_order_no;
	private String sales_order_Date;
	private String sales_order_version;
	private String customer_order_no;
	private String customer_order_Date;
	private String dispatch_schedule_no;
	private String dispatch_schedule_date;
	private String dispatch_schedule_version;
	private Integer sr_no;
	private String product_material_id;
	private String product_material_name;
	private Double product_material_std_weight;
	private String so_sr_no;
	private String product_material_print_name;
	private String product_material_tech_spect;
	private Integer product_material_unit_id;
	private String product_unit_name;
	private Integer product_material_packing_id;
	private String product_packing_name;
	private double invoice_quantity;
	private double invoice_weight;
	private Integer hsn_sac_id;
	private String hsn_sac_code;
	private Double hsn_sac_rate;
	private Double hsn_sac_percent;
	private Double item_rate;
	private Double item_amount;
	private Double item_discount_percent;
	private Double item_discount_amount;
	private Double item_taxable_amount;
	private Double item_cgst_percent;
	private Double item_cgst_amount;
	private Double item_sgst_percent;
	private Double item_sgst_amount;
	private Double item_igst_percent;
	private Double item_igst_amount;
	private Double item_total_amount;
	private String item_payment_schedule_date;
	private String sales_invoice_item_status;
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
	private Integer sales_invoice_master_transaction_id;
	private Integer dispatch_challan_details_transaction_id;
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


}
