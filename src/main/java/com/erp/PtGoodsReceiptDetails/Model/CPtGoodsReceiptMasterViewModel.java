package com.erp.PtGoodsReceiptDetails.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Immutable
@Subselect("select * from ptv_goods_receipt_summary")
public class CPtGoodsReceiptMasterViewModel {

	@Id
	private int goods_receipt_master_transaction_id;
	private String supplier_name;
	private String goods_receipt_no;
	private String goods_receipt_date;
	private Integer goods_receipt_version;
	private String goods_receipt_status_desc;
	private String purchase_order_no;
	private String purchase_order_date;
	private Integer purchase_order_version;
	private String goods_receipt_type;
	private String purchase_order_life;
	private String supplier_invoice_no;
	private String supplier_invoice_date;
	private String supplier_challan_no;
	private String supplier_challan_date;
	private String expected_schedule_date;
	private String agent_name;
	private double agent_percent;
	private String agent_paid_status;
	private String qa_by_name;
	private String approved_date;
	private String qa_date;
	private double basic_total;
	private double transport_amount;
	private double freight_amount;
	private boolean is_freight_taxable;
	private double packing_amount;
	private double goods_receipt_discount_percent;
	private double goods_receipt_discount_amount;
	private double other_amount;
	private double taxable_total;
	private double cgst_total;
	private double sgst_total;
	private double igst_total;
	private double grand_total;
	private double roundoff;
	private String goods_receipt_status;
	private String lr_no;
	private String lr_date;
	private String vehicle_no;
	private String other_terms_conditions;
	private String supp_branch_phone_no;
	private String supp_branch_EmailId;
	private String supp_branch_address1;
	private String supp_branch_pincode;
	private String supp_branch_gst_no;
	private String supp_branch_pan_no;
	private String remark;
	private boolean is_sez;
	private String company_name;
	private String company_branch_name;
	private String company_pincode;
	private String company_phone_no;
	private String company_branch_state;
	private String company_branch_city;
	private String company_cell_no;
	private String company_EmailId;
	private String company_gst_no;
	private String company_pan_no;
	private String company_address1;
	private String company_address2;
	private String supplier_city_name;
	private String supplier_state_name;
	private String financial_year;
	private boolean is_active;
	private boolean is_delete;
	private boolean is_preeclosed;
	private String created_by;
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;
	private Integer company_id;
	private Integer company_branch_id;
	private Integer qa_by_id;
	private Integer agent_id;
	private Integer supplier_id;
	private Integer approved_by_id;
	private Integer supplier_state_id;
	private Integer supplier_city_id;
	private String supplier_contacts_ids;
	private Integer expected_branch_id;
	private Integer expected_branch_state_id;
	private Integer expected_branch_city_id;
	private Integer goods_receipt_type_id;
	private Integer freight_hsn_code_id;
	private Integer payment_term_id;
	private Integer indent_by_id;
	private String approved_by_name;
	private String ev_bill_no;
	private String ev_bill_date;
}
