package com.erp.PtPurchaseDetails.Model;

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
@Subselect("select * from ptv_purchase_order_master_summary")
public class CPtPurchaseOrderMasterViewModel {

	@Id
	private int purchase_order_master_transaction_id;
	private String purchase_order_creation_type_desc;
	private String purchase_order_no;
	private String purchase_order_date;
	private Integer purchase_order_version;
	private String company_name;
	private String company_branch_name;
	private String financial_year;
	private String purchase_order_status;
	private String product_type_name;
	private String product_type_short_name;
	private String product_type_group;
	private String purchase_order_type;
	private String purchase_order_life;
	private String product_category2_id;
	private String product_category2_name;
	private String purchase_process_entry;
	private String purchase_process_entry_desc;
	private String purchase_order_creation_type;
	private String supplier_type;
	private String supplier_code;
	private String supplier_name;
	private String supplier_short_name;
	private String supp_branch_address1;
	private String supp_branch_pincode;
	private String city_name;
	private String district_name;
	private String state_name;
	private String country_name;
	private String supp_branch_phone_no;
	private String supp_branch_EmailId;
	private String customer_name;
	private String customer_email;
	private String customer_country_name;
	private String customer_state_name;
	private String customer_city_name;
	private String customer_district_name;
	private String customer_order_no;
	private String customer_order_Date;
	private String expected_branch_name;
	private String expected_branch_short_name;
	private String expected_branch_address1;
	private String expected_branch_pincode;
	private String expected_branch_city_name;
	private String expected_branch_district_name;
	private String expected_branch_state_name;
	private String expected_branch_country_name;
	private String expected_branch_phone_no;
	private String expected_branch_EmailId;
	private String approved_by;
	private String approved_date;
	private String expected_schedule_date;
	private String hsn_sac_type;
	private String hsn_sac_code;
	private String hsn_sac_description;
	private double hsn_sac_rate;
	private double basic_total;
	private double transport_amount;
	private double freight_amount;
	private double packing_amount;
	private double po_discount_percent;
	private double po_discount_amount;
	private double other_amount;
	private boolean is_freight_taxable;
	private double taxable_total;
	private double cgst_total;
	private double sgst_total;
	private double igst_total;
	private double roundoff;
	private double grand_total;
	private String agent_name;
	private double agent_percent;
	private String agent_paid_status;
	private String agent_paid_status_desc;
	private String purchase_order_acceptance_status_desc;
	private String purchase_order_acceptance_status;
	private String purchase_order_mail_sent_status;
	private String purchase_order_mail_sent_status_desc;
	private String status_remark;
	private String other_terms_conditions;
	private String gate_pass_no;
	private String gate_pass_date;
	private String vehicale_no;
	private String vehicale_type;
	private double gross_weight;
	private double tare_weight;
	private double net_weight;
	private double variation_weight;
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
	private String remark;
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
	private Integer purchase_order_type_id;
	private String customer_id;
	private Integer supplier_id;
	private Integer supplier_state_id;
	private Integer supplier_city_id;
	private String supplier_contacts_ids;
	private Integer expected_branch_id;
	private Integer expected_branch_state_id;
	private Integer expected_branch_city_id;
	private Integer approved_by_id;
	private Integer agent_id;
	private String	grader_by_id;
	private Integer consignee_id;
	private Integer consignee_state_id;
	private Integer consignee_city_id;
 	private String supplier_cosnignee_name;
  	private String supp_consignee_address1;
 	private String supp_consignee_address2;
 	private String supp_consignee_pincode;
	private String supp_consignee_city_name;
	private String supp_consignee_state_name;
	private String grn_status;
	private String quotation_no;
	private String quotation_date;
	private String sales_type;


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
