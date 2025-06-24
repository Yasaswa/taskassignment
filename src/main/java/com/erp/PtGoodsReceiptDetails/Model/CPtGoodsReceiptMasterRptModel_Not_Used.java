package com.erp.PtGoodsReceiptDetails.Model;

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
@Subselect("select * from  ptv_goods_receipt_summary_rpt")
public class CPtGoodsReceiptMasterRptModel_Not_Used {

	@Id
	private String goods_receipt_master_transaction_id;
	private String supplier_name;
	private String goods_receipt_no;
	private String goods_receipt_date;
	private String goods_receipt_version;
	private String goods_receipt_status_desc;
	private String purchase_order_no;
	private String purchase_order_date;
	private String purchase_order_version;
	private String goods_receipt_type;
	private String purchase_order_life;
	private String supplier_challan_no;
	private String supplier_challan_date;
	private String supplier_invoice_no;
	private String supplier_invoice_date;
	private String expected_schedule_date;
	private String agent_name;
	private String agent_percent;
	private String agent_paid_status;
	private String qa_by_name;
	private String approved_date;
	private String qa_date;
	private String basic_total;
	private String transport_amount;
	private String freight_amount;
	private String is_freight_taxable;
	private String packing_amount;
	private String goods_receipt_discount_percent;
	private String goods_receipt_discount_amount;
	private String other_amount;
	private String taxable_total;
	private String cgst_total;
	private String sgst_total;
	private String igst_total;
	private String grand_total;
	private String roundoff;
	private String goods_receipt_status;
	private String lr_no;
	private String lr_date;
	private String vehicle_no;
	private String other_terms_conditions;
	private String remark;
	private String is_sez;
	private String company_name;
	private String company_branch_name;
	private String financial_year;
	private String supplier_city_name;
	private String agent_id;
	private String is_active;
	private String is_delete;
	private String created_by;
	private String created_on;
	private String modified_by;
	private String modified_on;
	private String deleted_by;
	private String deleted_on;
	private String company_id;
	private String supplier_id;
	private String qa_by_id;
	private String approved_by_id;
	private String supplier_state_id;
	private String supplier_city_id;
	private String supplier_contacts_ids;
	private String expected_branch_id;
	private String expected_branch_state_id;
	private String expected_branch_city_id;
	private String goods_receipt_type_id;
	private String supplier_accounts_id;
	private String payment_term_id;

}
