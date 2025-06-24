package com.erp.PtPurchaseDetails.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pt_purchase_order_master")
public class CPtPurchaseOrderMasterModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "purchase_order_master_transaction_id")
	private int purchase_order_master_transaction_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String financial_year;
	private Integer purchase_order_type_id;
	private String purchase_order_type;
	private String purchase_order_life;
	private String product_category2_id;
	private String product_category2_name;
	private String purchase_process_entry;
	private String purchase_order_creation_type;
	private String purchase_order_no;
	private String purchase_order_date;
	private Integer purchase_order_version;
	private String customer_id;
	private String customer_order_no;
	private String customer_order_Date;
	private Integer supplier_id;
	private Integer supplier_state_id;
	private Integer supplier_city_id;
	private String supplier_contacts_ids;
	private Integer expected_branch_id;
	private Integer expected_branch_state_id;
	private Integer expected_branch_city_id;
	private Integer approved_by_id;
	private String approved_date;
	private String expected_schedule_date;
	private double basic_total;
	private double transport_amount;
	private double freight_amount;
	private boolean is_freight_taxable = Boolean.FALSE;
	private Integer freight_hsn_code_id;
	private double packing_amount;
	private double po_discount_percent;
	private double po_discount_amount;
	private double other_amount;
	private double taxable_total;
	private double cgst_total;
	private double sgst_total;
	private double igst_total;
	private double roundoff;
	private double grand_total;
	private Integer agent_id;
	private double agent_percent;
	private String agent_paid_status;
	private String purchase_order_acceptance_status;
	private String purchase_order_mail_sent_status;
	private String purchase_order_status;
	private String status_remark;
	private String other_terms_conditions;
	private String gate_pass_no;
	private String gate_pass_date;
	private String vehicale_no;
	private String vehicale_type;
	private double gross_weight;
	private double tare_weight;
	private double variation_weight;
	private double net_weight;
	private Integer deduction1_id;
	private String	deduction1_name;
	private double deduction1_percent;
	private double 	deduction1_amount;
	private Integer deduction2_id;
	private String	deduction2_name;
	private double deduction2_percent;
	private double 	deduction2_amount;
	private Integer deduction3_id;
	private String	deduction3_name;
	private double deduction3_percent;
	private double 	deduction3_amount;
	private Integer deduction4_id;
	private String	deduction4_name;
	private double deduction4_percent;
	private double 	deduction4_amount;
	private Integer deduction5_id;
	private String	deduction5_name;
	private double deduction5_percent;
	private double 	deduction5_amount;
	private String	grader_by_id;
	private String remark;
	private Integer consignee_id;
	private Integer consignee_state_id;
	private Integer consignee_city_id;
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
	private String grn_status;
	private String quotation_no;
	private String quotation_date;
	private String sales_type;



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

	public boolean isIs_freight_taxable() {
		return is_freight_taxable;
	}

	public void setIs_freight_taxable(boolean is_freight_taxable) {
		this.is_freight_taxable = is_freight_taxable;
	}

	public void setPurchase_order_date(String purchase_order_date) {
		if (purchase_order_date == null || purchase_order_date.isEmpty()) {
			this.purchase_order_date = null;
		} else {
			this.purchase_order_date = purchase_order_date;

		}
	}

	public void setCustomer_order_Date(String customer_order_Date) {
		if (customer_order_Date == null || customer_order_Date.isEmpty()) {
			this.customer_order_Date = null;
		} else {
			this.customer_order_Date = customer_order_Date;
		}
	}
	public void setApproved_date(String approved_date) {
		if (approved_date == null || approved_date.isEmpty()) {
			this.approved_date = null;
		} else {
			this.approved_date = approved_date;
		}

	}

	public void setExpected_schedule_date(String expected_schedule_date) {
		if (expected_schedule_date == null || expected_schedule_date.isEmpty()) {
			this.expected_schedule_date = null;
		} else {
			this.expected_schedule_date = expected_schedule_date;
		}
	}

	public void setGate_pass_date(String gate_pass_date) {
		if (gate_pass_date == null || gate_pass_date.isEmpty()) {
			this.gate_pass_date = null;
		} else {
			this.gate_pass_date = gate_pass_date;
		}
	}

	public void setQuotation_date(String quotation_date) {
		if (quotation_date == null || quotation_date.isEmpty()) {
			this.quotation_date = null;
		} else {
			this.quotation_date = quotation_date;
		}
	}
}
