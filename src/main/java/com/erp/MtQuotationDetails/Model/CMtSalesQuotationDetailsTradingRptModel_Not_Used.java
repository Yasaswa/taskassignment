package com.erp.MtQuotationDetails.Model;

import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@Immutable
@Subselect("select * from  mtv_sales_quotation_trading_details_rpt")
public class CMtSalesQuotationDetailsTradingRptModel_Not_Used {

	@Id
	private String financial_year;
	private String quotation_type;
	private String quotation_transaction_type_desc;
	private String quotation_no;
	private String quotation_version;
	private String quotation_date;
	private String department_name;
	private String quotation_by_name;
	private String enquiry_by_name;
	private String assign_to_head_name;
	private String assign_to_name;
	private String approved_by_name;
	private String agent_name;
	private String agent_percent;
	private String quotation_material_cost;
	private String quotation_process_cost;
	private String quotation_labour_cost;
	private String quotation_other_cost;
	private String quotation_other_cost_remark;
	private String quotation_administration_percent;
	private String quotation_administration_cost;
	private String quotation_profit_percent;
	private String quotation_profit_cost;
	private String quotation_discount_percent;
	private String quotation_discount_cost;
	private String quotation_taxable_cost;
	private String quotation_cgst_cost;
	private String quotation_sgst_cost;
	private String quotation_igst_cost;
	private String quotation_total_cost;
	private String overall_schedule_date;
	private String quotation_status_desc;
	private String enquiry_no;
	private String enquiry_version;
	private String enquiry_date;
	private String sr_no;
	private String enquiry_type_short_name;
	private String product_material_code;
	private String product_material_name;
	private String product_material_std_weight;
	private String product_material_unit_name;
	private String product_material_hsn_sac_code;
	private String hsn_sac_percent;
	private String product_material_tech_spect;
	private String product_packing_name;
	private String product_material_quotation_quantity;
	private String product_material_quotation_weight;
	private String product_material_moq_quantity;
	private String material_std_rate;
	private String item_material_cost;
	private String item_process_cost;
	private String item_labour_cost;
	private String item_other_cost;
	private String item_other_cost_remark;
	private String item_administration_percent;
	private String item_administration_cost;
	private String item_profit_percent;
	private String item_profit_cost;
	private String item_discount_percent;
	private String item_discount_cost;
	private String item_taxable_cost;
	private String item_cgst_cost;
	private String item_sgst_cost;
	private String item_igst_cost;
	private String item_total_cost;
	private String quotation_item_status_desc;
	private String product_material_notes;
	private String product_material_conversion_factor;
	private String expected_lead_time;
	private String material_expected_schedule_date;
	private String quotation_item_status;
	private String material_item_approval_remark;
	private String product_fg_bom_no;
	private String product_fg_bom_version;
	private String product_costing_no;
	private String item_dispatch_note_nos;
	private String item_dispatch_challan_nos;
	private String item_invoice_nos;
	private String tax_applicable_desc;
	private String tax_applicable;
	private String transportation_applicable_desc;
	private String transportation_applicable;
	private String bom_applicable_desc;
	private String bom_applicable;
	private String costing_applicable_desc;
	private String costing_applicable;
	private String quotation_life_desc;
	private String quotation_mail_sent_status_desc;
	private String approved_date;
	private String quotation_type_short_name;
	private String expected_branch_name;
	private String expected_branch_address1;
	private String expected_branch_pincode;
	private String expected_branch_city_name;
	private String expected_branch_district_name;
	private String expected_branch_state_name;
	private String expected_branch_country_name;
	private String expected_branch_phone_no;
	private String expected_branch_EmailId;
	private String status_remark;
	private String company_name;
	private String company_branch_name;
	private String other_terms_conditions;
	private String remark;
	private String item_remark;
	private String quotation_life;
	private String quotation_transaction_type;
	private String quotation_mail_sent_status;
	private String customer_name;
	private String customer_state_name;
	private String customer_email;
	private String customer_country_name;
	private String customer_city_name;
	private String customer_district_name;
	private String is_active;
	private String is_delete;
	private String created_by;
	private String created_on;
	private String modified_by;
	private String modified_on;
	private String deleted_by;
	private String deleted_on;
	private String company_id;
	private String department_id;
	private String company_branch_id;
	private String quotation_type_id;
	private String customer_id;
	private String customer_contacts_ids;
	private String customer_state_id;
	private String customer_city_id;
	private String expected_branch_id;
	private String expected_branch_state_id;
	private String expected_branch_city_id;
	private String quotation_by_id;
	private String assign_to_head_id;
	private String assign_to_id;
	private String approved_by_id;
	private String product_material_unit_id;
	private String product_material_packing_id;
	private String agent_id;
	private String quotation_master_transaction_id;
	private String quotation_details_transaction_id;
	private String hsn_sac_id;
	private String product_material_id;

	public String getFinancial_year() {
		return financial_year;
	}

	public void setFinancial_year(String financial_year) {
		this.financial_year = financial_year;
	}

	public String getQuotation_type() {
		return quotation_type;
	}

	public void setQuotation_type(String quotation_type) {
		this.quotation_type = quotation_type;
	}

	public String getQuotation_transaction_type_desc() {
		return quotation_transaction_type_desc;
	}

	public void setQuotation_transaction_type_desc(String quotation_transaction_type_desc) {
		this.quotation_transaction_type_desc = quotation_transaction_type_desc;
	}

	public String getQuotation_no() {
		return quotation_no;
	}

	public void setQuotation_no(String quotation_no) {
		this.quotation_no = quotation_no;
	}

	public String getQuotation_version() {
		return quotation_version;
	}

	public void setQuotation_version(String quotation_version) {
		this.quotation_version = quotation_version;
	}

	public String getQuotation_date() {
		return quotation_date;
	}

	public void setQuotation_date(String quotation_date) {
		this.quotation_date = quotation_date;
	}

	public String getDepartment_name() {
		return department_name;
	}

	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}

	public String getQuotation_by_name() {
		return quotation_by_name;
	}

	public void setQuotation_by_name(String quotation_by_name) {
		this.quotation_by_name = quotation_by_name;
	}

	public String getEnquiry_by_name() {
		return enquiry_by_name;
	}

	public void setEnquiry_by_name(String enquiry_by_name) {
		this.enquiry_by_name = enquiry_by_name;
	}

	public String getAssign_to_head_name() {
		return assign_to_head_name;
	}

	public void setAssign_to_head_name(String assign_to_head_name) {
		this.assign_to_head_name = assign_to_head_name;
	}

	public String getAssign_to_name() {
		return assign_to_name;
	}

	public void setAssign_to_name(String assign_to_name) {
		this.assign_to_name = assign_to_name;
	}

	public String getApproved_by_name() {
		return approved_by_name;
	}

	public void setApproved_by_name(String approved_by_name) {
		this.approved_by_name = approved_by_name;
	}

	public String getAgent_name() {
		return agent_name;
	}

	public void setAgent_name(String agent_name) {
		this.agent_name = agent_name;
	}

	public String getAgent_percent() {
		return agent_percent;
	}

	public void setAgent_percent(String agent_percent) {
		this.agent_percent = agent_percent;
	}

	public String getQuotation_material_cost() {
		return quotation_material_cost;
	}

	public void setQuotation_material_cost(String quotation_material_cost) {
		this.quotation_material_cost = quotation_material_cost;
	}

	public String getQuotation_process_cost() {
		return quotation_process_cost;
	}

	public void setQuotation_process_cost(String quotation_process_cost) {
		this.quotation_process_cost = quotation_process_cost;
	}

	public String getQuotation_labour_cost() {
		return quotation_labour_cost;
	}

	public void setQuotation_labour_cost(String quotation_labour_cost) {
		this.quotation_labour_cost = quotation_labour_cost;
	}

	public String getQuotation_other_cost() {
		return quotation_other_cost;
	}

	public void setQuotation_other_cost(String quotation_other_cost) {
		this.quotation_other_cost = quotation_other_cost;
	}

	public String getQuotation_other_cost_remark() {
		return quotation_other_cost_remark;
	}

	public void setQuotation_other_cost_remark(String quotation_other_cost_remark) {
		this.quotation_other_cost_remark = quotation_other_cost_remark;
	}

	public String getQuotation_administration_percent() {
		return quotation_administration_percent;
	}

	public void setQuotation_administration_percent(String quotation_administration_percent) {
		this.quotation_administration_percent = quotation_administration_percent;
	}

	public String getQuotation_administration_cost() {
		return quotation_administration_cost;
	}

	public void setQuotation_administration_cost(String quotation_administration_cost) {
		this.quotation_administration_cost = quotation_administration_cost;
	}

	public String getQuotation_profit_percent() {
		return quotation_profit_percent;
	}

	public void setQuotation_profit_percent(String quotation_profit_percent) {
		this.quotation_profit_percent = quotation_profit_percent;
	}

	public String getQuotation_profit_cost() {
		return quotation_profit_cost;
	}

	public void setQuotation_profit_cost(String quotation_profit_cost) {
		this.quotation_profit_cost = quotation_profit_cost;
	}

	public String getQuotation_discount_percent() {
		return quotation_discount_percent;
	}

	public void setQuotation_discount_percent(String quotation_discount_percent) {
		this.quotation_discount_percent = quotation_discount_percent;
	}

	public String getQuotation_discount_cost() {
		return quotation_discount_cost;
	}

	public void setQuotation_discount_cost(String quotation_discount_cost) {
		this.quotation_discount_cost = quotation_discount_cost;
	}

	public String getQuotation_taxable_cost() {
		return quotation_taxable_cost;
	}

	public void setQuotation_taxable_cost(String quotation_taxable_cost) {
		this.quotation_taxable_cost = quotation_taxable_cost;
	}

	public String getQuotation_cgst_cost() {
		return quotation_cgst_cost;
	}

	public void setQuotation_cgst_cost(String quotation_cgst_cost) {
		this.quotation_cgst_cost = quotation_cgst_cost;
	}

	public String getQuotation_sgst_cost() {
		return quotation_sgst_cost;
	}

	public void setQuotation_sgst_cost(String quotation_sgst_cost) {
		this.quotation_sgst_cost = quotation_sgst_cost;
	}

	public String getQuotation_igst_cost() {
		return quotation_igst_cost;
	}

	public void setQuotation_igst_cost(String quotation_igst_cost) {
		this.quotation_igst_cost = quotation_igst_cost;
	}

	public String getQuotation_total_cost() {
		return quotation_total_cost;
	}

	public void setQuotation_total_cost(String quotation_total_cost) {
		this.quotation_total_cost = quotation_total_cost;
	}

	public String getOverall_schedule_date() {
		return overall_schedule_date;
	}

	public void setOverall_schedule_date(String overall_schedule_date) {
		this.overall_schedule_date = overall_schedule_date;
	}

	public String getQuotation_status_desc() {
		return quotation_status_desc;
	}

	public void setQuotation_status_desc(String quotation_status_desc) {
		this.quotation_status_desc = quotation_status_desc;
	}

	public String getEnquiry_no() {
		return enquiry_no;
	}

	public void setEnquiry_no(String enquiry_no) {
		this.enquiry_no = enquiry_no;
	}

	public String getEnquiry_version() {
		return enquiry_version;
	}

	public void setEnquiry_version(String enquiry_version) {
		this.enquiry_version = enquiry_version;
	}

	public String getEnquiry_date() {
		return enquiry_date;
	}

	public void setEnquiry_date(String enquiry_date) {
		this.enquiry_date = enquiry_date;
	}

	public String getSr_no() {
		return sr_no;
	}

	public void setSr_no(String sr_no) {
		this.sr_no = sr_no;
	}

	public String getEnquiry_type_short_name() {
		return enquiry_type_short_name;
	}

	public void setEnquiry_type_short_name(String enquiry_type_short_name) {
		this.enquiry_type_short_name = enquiry_type_short_name;
	}

	public String getProduct_material_code() {
		return product_material_code;
	}

	public void setProduct_material_code(String product_material_code) {
		this.product_material_code = product_material_code;
	}

	public String getProduct_material_name() {
		return product_material_name;
	}

	public void setProduct_material_name(String product_material_name) {
		this.product_material_name = product_material_name;
	}

	public String getProduct_material_std_weight() {
		return product_material_std_weight;
	}

	public void setProduct_material_std_weight(String product_material_std_weight) {
		this.product_material_std_weight = product_material_std_weight;
	}

	public String getProduct_material_unit_name() {
		return product_material_unit_name;
	}

	public void setProduct_material_unit_name(String product_material_unit_name) {
		this.product_material_unit_name = product_material_unit_name;
	}

	public String getProduct_material_hsn_sac_code() {
		return product_material_hsn_sac_code;
	}

	public void setProduct_material_hsn_sac_code(String product_material_hsn_sac_code) {
		this.product_material_hsn_sac_code = product_material_hsn_sac_code;
	}

	public String getHsn_sac_percent() {
		return hsn_sac_percent;
	}

	public void setHsn_sac_percent(String hsn_sac_percent) {
		this.hsn_sac_percent = hsn_sac_percent;
	}

	public String getProduct_material_tech_spect() {
		return product_material_tech_spect;
	}

	public void setProduct_material_tech_spect(String product_material_tech_spect) {
		this.product_material_tech_spect = product_material_tech_spect;
	}

	public String getProduct_packing_name() {
		return product_packing_name;
	}

	public void setProduct_packing_name(String product_packing_name) {
		this.product_packing_name = product_packing_name;
	}

	public String getProduct_material_quotation_quantity() {
		return product_material_quotation_quantity;
	}

	public void setProduct_material_quotation_quantity(String product_material_quotation_quantity) {
		this.product_material_quotation_quantity = product_material_quotation_quantity;
	}

	public String getProduct_material_quotation_weight() {
		return product_material_quotation_weight;
	}

	public void setProduct_material_quotation_weight(String product_material_quotation_weight) {
		this.product_material_quotation_weight = product_material_quotation_weight;
	}

	public String getProduct_material_moq_quantity() {
		return product_material_moq_quantity;
	}

	public void setProduct_material_moq_quantity(String product_material_moq_quantity) {
		this.product_material_moq_quantity = product_material_moq_quantity;
	}

	public String getMaterial_std_rate() {
		return material_std_rate;
	}

	public void setMaterial_std_rate(String material_std_rate) {
		this.material_std_rate = material_std_rate;
	}

	public String getItem_material_cost() {
		return item_material_cost;
	}

	public void setItem_material_cost(String item_material_cost) {
		this.item_material_cost = item_material_cost;
	}

	public String getItem_process_cost() {
		return item_process_cost;
	}

	public void setItem_process_cost(String item_process_cost) {
		this.item_process_cost = item_process_cost;
	}

	public String getItem_labour_cost() {
		return item_labour_cost;
	}

	public void setItem_labour_cost(String item_labour_cost) {
		this.item_labour_cost = item_labour_cost;
	}

	public String getItem_other_cost() {
		return item_other_cost;
	}

	public void setItem_other_cost(String item_other_cost) {
		this.item_other_cost = item_other_cost;
	}

	public String getItem_other_cost_remark() {
		return item_other_cost_remark;
	}

	public void setItem_other_cost_remark(String item_other_cost_remark) {
		this.item_other_cost_remark = item_other_cost_remark;
	}

	public String getItem_administration_percent() {
		return item_administration_percent;
	}

	public void setItem_administration_percent(String item_administration_percent) {
		this.item_administration_percent = item_administration_percent;
	}

	public String getItem_administration_cost() {
		return item_administration_cost;
	}

	public void setItem_administration_cost(String item_administration_cost) {
		this.item_administration_cost = item_administration_cost;
	}

	public String getItem_profit_percent() {
		return item_profit_percent;
	}

	public void setItem_profit_percent(String item_profit_percent) {
		this.item_profit_percent = item_profit_percent;
	}

	public String getItem_profit_cost() {
		return item_profit_cost;
	}

	public void setItem_profit_cost(String item_profit_cost) {
		this.item_profit_cost = item_profit_cost;
	}

	public String getItem_discount_percent() {
		return item_discount_percent;
	}

	public void setItem_discount_percent(String item_discount_percent) {
		this.item_discount_percent = item_discount_percent;
	}

	public String getItem_discount_cost() {
		return item_discount_cost;
	}

	public void setItem_discount_cost(String item_discount_cost) {
		this.item_discount_cost = item_discount_cost;
	}

	public String getItem_taxable_cost() {
		return item_taxable_cost;
	}

	public void setItem_taxable_cost(String item_taxable_cost) {
		this.item_taxable_cost = item_taxable_cost;
	}

	public String getItem_cgst_cost() {
		return item_cgst_cost;
	}

	public void setItem_cgst_cost(String item_cgst_cost) {
		this.item_cgst_cost = item_cgst_cost;
	}

	public String getItem_sgst_cost() {
		return item_sgst_cost;
	}

	public void setItem_sgst_cost(String item_sgst_cost) {
		this.item_sgst_cost = item_sgst_cost;
	}

	public String getItem_igst_cost() {
		return item_igst_cost;
	}

	public void setItem_igst_cost(String item_igst_cost) {
		this.item_igst_cost = item_igst_cost;
	}

	public String getItem_total_cost() {
		return item_total_cost;
	}

	public void setItem_total_cost(String item_total_cost) {
		this.item_total_cost = item_total_cost;
	}

	public String getQuotation_item_status_desc() {
		return quotation_item_status_desc;
	}

	public void setQuotation_item_status_desc(String quotation_item_status_desc) {
		this.quotation_item_status_desc = quotation_item_status_desc;
	}

	public String getProduct_material_notes() {
		return product_material_notes;
	}

	public void setProduct_material_notes(String product_material_notes) {
		this.product_material_notes = product_material_notes;
	}

	public String getProduct_material_conversion_factor() {
		return product_material_conversion_factor;
	}

	public void setProduct_material_conversion_factor(String product_material_conversion_factor) {
		this.product_material_conversion_factor = product_material_conversion_factor;
	}

	public String getExpected_lead_time() {
		return expected_lead_time;
	}

	public void setExpected_lead_time(String expected_lead_time) {
		this.expected_lead_time = expected_lead_time;
	}

	public String getMaterial_expected_schedule_date() {
		return material_expected_schedule_date;
	}

	public void setMaterial_expected_schedule_date(String material_expected_schedule_date) {
		this.material_expected_schedule_date = material_expected_schedule_date;
	}

	public String getQuotation_item_status() {
		return quotation_item_status;
	}

	public void setQuotation_item_status(String quotation_item_status) {
		this.quotation_item_status = quotation_item_status;
	}

	public String getMaterial_item_approval_remark() {
		return material_item_approval_remark;
	}

	public void setMaterial_item_approval_remark(String material_item_approval_remark) {
		this.material_item_approval_remark = material_item_approval_remark;
	}

	public String getProduct_fg_bom_no() {
		return product_fg_bom_no;
	}

	public void setProduct_fg_bom_no(String product_fg_bom_no) {
		this.product_fg_bom_no = product_fg_bom_no;
	}

	public String getProduct_fg_bom_version() {
		return product_fg_bom_version;
	}

	public void setProduct_fg_bom_version(String product_fg_bom_version) {
		this.product_fg_bom_version = product_fg_bom_version;
	}

	public String getProduct_costing_no() {
		return product_costing_no;
	}

	public void setProduct_costing_no(String product_costing_no) {
		this.product_costing_no = product_costing_no;
	}

	public String getItem_dispatch_note_nos() {
		return item_dispatch_note_nos;
	}

	public void setItem_dispatch_note_nos(String item_dispatch_note_nos) {
		this.item_dispatch_note_nos = item_dispatch_note_nos;
	}

	public String getItem_dispatch_challan_nos() {
		return item_dispatch_challan_nos;
	}

	public void setItem_dispatch_challan_nos(String item_dispatch_challan_nos) {
		this.item_dispatch_challan_nos = item_dispatch_challan_nos;
	}

	public String getItem_invoice_nos() {
		return item_invoice_nos;
	}

	public void setItem_invoice_nos(String item_invoice_nos) {
		this.item_invoice_nos = item_invoice_nos;
	}

	public String getTax_applicable_desc() {
		return tax_applicable_desc;
	}

	public void setTax_applicable_desc(String tax_applicable_desc) {
		this.tax_applicable_desc = tax_applicable_desc;
	}

	public String getTax_applicable() {
		return tax_applicable;
	}

	public void setTax_applicable(String tax_applicable) {
		this.tax_applicable = tax_applicable;
	}

	public String getTransportation_applicable_desc() {
		return transportation_applicable_desc;
	}

	public void setTransportation_applicable_desc(String transportation_applicable_desc) {
		this.transportation_applicable_desc = transportation_applicable_desc;
	}

	public String getTransportation_applicable() {
		return transportation_applicable;
	}

	public void setTransportation_applicable(String transportation_applicable) {
		this.transportation_applicable = transportation_applicable;
	}

	public String getBom_applicable_desc() {
		return bom_applicable_desc;
	}

	public void setBom_applicable_desc(String bom_applicable_desc) {
		this.bom_applicable_desc = bom_applicable_desc;
	}

	public String getBom_applicable() {
		return bom_applicable;
	}

	public void setBom_applicable(String bom_applicable) {
		this.bom_applicable = bom_applicable;
	}

	public String getCosting_applicable_desc() {
		return costing_applicable_desc;
	}

	public void setCosting_applicable_desc(String costing_applicable_desc) {
		this.costing_applicable_desc = costing_applicable_desc;
	}

	public String getCosting_applicable() {
		return costing_applicable;
	}

	public void setCosting_applicable(String costing_applicable) {
		this.costing_applicable = costing_applicable;
	}

	public String getQuotation_life_desc() {
		return quotation_life_desc;
	}

	public void setQuotation_life_desc(String quotation_life_desc) {
		this.quotation_life_desc = quotation_life_desc;
	}

	public String getQuotation_mail_sent_status_desc() {
		return quotation_mail_sent_status_desc;
	}

	public void setQuotation_mail_sent_status_desc(String quotation_mail_sent_status_desc) {
		this.quotation_mail_sent_status_desc = quotation_mail_sent_status_desc;
	}

	public String getApproved_date() {
		return approved_date;
	}

	public void setApproved_date(String approved_date) {
		this.approved_date = approved_date;
	}

	public String getQuotation_type_short_name() {
		return quotation_type_short_name;
	}

	public void setQuotation_type_short_name(String quotation_type_short_name) {
		this.quotation_type_short_name = quotation_type_short_name;
	}

	public String getExpected_branch_name() {
		return expected_branch_name;
	}

	public void setExpected_branch_name(String expected_branch_name) {
		this.expected_branch_name = expected_branch_name;
	}

	public String getExpected_branch_address1() {
		return expected_branch_address1;
	}

	public void setExpected_branch_address1(String expected_branch_address1) {
		this.expected_branch_address1 = expected_branch_address1;
	}

	public String getExpected_branch_pincode() {
		return expected_branch_pincode;
	}

	public void setExpected_branch_pincode(String expected_branch_pincode) {
		this.expected_branch_pincode = expected_branch_pincode;
	}

	public String getExpected_branch_city_name() {
		return expected_branch_city_name;
	}

	public void setExpected_branch_city_name(String expected_branch_city_name) {
		this.expected_branch_city_name = expected_branch_city_name;
	}

	public String getExpected_branch_district_name() {
		return expected_branch_district_name;
	}

	public void setExpected_branch_district_name(String expected_branch_district_name) {
		this.expected_branch_district_name = expected_branch_district_name;
	}

	public String getExpected_branch_state_name() {
		return expected_branch_state_name;
	}

	public void setExpected_branch_state_name(String expected_branch_state_name) {
		this.expected_branch_state_name = expected_branch_state_name;
	}

	public String getExpected_branch_country_name() {
		return expected_branch_country_name;
	}

	public void setExpected_branch_country_name(String expected_branch_country_name) {
		this.expected_branch_country_name = expected_branch_country_name;
	}

	public String getExpected_branch_phone_no() {
		return expected_branch_phone_no;
	}

	public void setExpected_branch_phone_no(String expected_branch_phone_no) {
		this.expected_branch_phone_no = expected_branch_phone_no;
	}

	public String getExpected_branch_EmailId() {
		return expected_branch_EmailId;
	}

	public void setExpected_branch_EmailId(String expected_branch_EmailId) {
		this.expected_branch_EmailId = expected_branch_EmailId;
	}

	public String getStatus_remark() {
		return status_remark;
	}

	public void setStatus_remark(String status_remark) {
		this.status_remark = status_remark;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getCompany_branch_name() {
		return company_branch_name;
	}

	public void setCompany_branch_name(String company_branch_name) {
		this.company_branch_name = company_branch_name;
	}

	public String getOther_terms_conditions() {
		return other_terms_conditions;
	}

	public void setOther_terms_conditions(String other_terms_conditions) {
		this.other_terms_conditions = other_terms_conditions;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getItem_remark() {
		return item_remark;
	}

	public void setItem_remark(String item_remark) {
		this.item_remark = item_remark;
	}

	public String getQuotation_life() {
		return quotation_life;
	}

	public void setQuotation_life(String quotation_life) {
		this.quotation_life = quotation_life;
	}

	public String getQuotation_transaction_type() {
		return quotation_transaction_type;
	}

	public void setQuotation_transaction_type(String quotation_transaction_type) {
		this.quotation_transaction_type = quotation_transaction_type;
	}

	public String getQuotation_mail_sent_status() {
		return quotation_mail_sent_status;
	}

	public void setQuotation_mail_sent_status(String quotation_mail_sent_status) {
		this.quotation_mail_sent_status = quotation_mail_sent_status;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public String getCustomer_state_name() {
		return customer_state_name;
	}

	public void setCustomer_state_name(String customer_state_name) {
		this.customer_state_name = customer_state_name;
	}

	public String getCustomer_email() {
		return customer_email;
	}

	public void setCustomer_email(String customer_email) {
		this.customer_email = customer_email;
	}

	public String getCustomer_country_name() {
		return customer_country_name;
	}

	public void setCustomer_country_name(String customer_country_name) {
		this.customer_country_name = customer_country_name;
	}

	public String getCustomer_city_name() {
		return customer_city_name;
	}

	public void setCustomer_city_name(String customer_city_name) {
		this.customer_city_name = customer_city_name;
	}

	public String getCustomer_district_name() {
		return customer_district_name;
	}

	public void setCustomer_district_name(String customer_district_name) {
		this.customer_district_name = customer_district_name;
	}

	public String getIs_active() {
		return is_active;
	}

	public void setIs_active(String is_active) {
		this.is_active = is_active;
	}

	public String getIs_delete() {
		return is_delete;
	}

	public void setIs_delete(String is_delete) {
		this.is_delete = is_delete;
	}

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public String getCreated_on() {
		return created_on;
	}

	public void setCreated_on(String created_on) {
		this.created_on = created_on;
	}

	public String getModified_by() {
		return modified_by;
	}

	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}

	public String getModified_on() {
		return modified_on;
	}

	public void setModified_on(String modified_on) {
		this.modified_on = modified_on;
	}

	public String getDeleted_by() {
		return deleted_by;
	}

	public void setDeleted_by(String deleted_by) {
		this.deleted_by = deleted_by;
	}

	public String getDeleted_on() {
		return deleted_on;
	}

	public void setDeleted_on(String deleted_on) {
		this.deleted_on = deleted_on;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public String getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(String department_id) {
		this.department_id = department_id;
	}

	public String getCompany_branch_id() {
		return company_branch_id;
	}

	public void setCompany_branch_id(String company_branch_id) {
		this.company_branch_id = company_branch_id;
	}

	public String getQuotation_type_id() {
		return quotation_type_id;
	}

	public void setQuotation_type_id(String quotation_type_id) {
		this.quotation_type_id = quotation_type_id;
	}

	public String getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}

	public String getCustomer_contacts_ids() {
		return customer_contacts_ids;
	}

	public void setCustomer_contacts_ids(String customer_contacts_ids) {
		this.customer_contacts_ids = customer_contacts_ids;
	}

	public String getCustomer_state_id() {
		return customer_state_id;
	}

	public void setCustomer_state_id(String customer_state_id) {
		this.customer_state_id = customer_state_id;
	}

	public String getCustomer_city_id() {
		return customer_city_id;
	}

	public void setCustomer_city_id(String customer_city_id) {
		this.customer_city_id = customer_city_id;
	}

	public String getExpected_branch_id() {
		return expected_branch_id;
	}

	public void setExpected_branch_id(String expected_branch_id) {
		this.expected_branch_id = expected_branch_id;
	}

	public String getExpected_branch_state_id() {
		return expected_branch_state_id;
	}

	public void setExpected_branch_state_id(String expected_branch_state_id) {
		this.expected_branch_state_id = expected_branch_state_id;
	}

	public String getExpected_branch_city_id() {
		return expected_branch_city_id;
	}

	public void setExpected_branch_city_id(String expected_branch_city_id) {
		this.expected_branch_city_id = expected_branch_city_id;
	}

	public String getQuotation_by_id() {
		return quotation_by_id;
	}

	public void setQuotation_by_id(String quotation_by_id) {
		this.quotation_by_id = quotation_by_id;
	}

	public String getAssign_to_head_id() {
		return assign_to_head_id;
	}

	public void setAssign_to_head_id(String assign_to_head_id) {
		this.assign_to_head_id = assign_to_head_id;
	}

	public String getAssign_to_id() {
		return assign_to_id;
	}

	public void setAssign_to_id(String assign_to_id) {
		this.assign_to_id = assign_to_id;
	}

	public String getApproved_by_id() {
		return approved_by_id;
	}

	public void setApproved_by_id(String approved_by_id) {
		this.approved_by_id = approved_by_id;
	}

	public String getProduct_material_unit_id() {
		return product_material_unit_id;
	}

	public void setProduct_material_unit_id(String product_material_unit_id) {
		this.product_material_unit_id = product_material_unit_id;
	}

	public String getProduct_material_packing_id() {
		return product_material_packing_id;
	}

	public void setProduct_material_packing_id(String product_material_packing_id) {
		this.product_material_packing_id = product_material_packing_id;
	}

	public String getAgent_id() {
		return agent_id;
	}

	public void setAgent_id(String agent_id) {
		this.agent_id = agent_id;
	}

	public String getQuotation_master_transaction_id() {
		return quotation_master_transaction_id;
	}

	public void setQuotation_master_transaction_id(String quotation_master_transaction_id) {
		this.quotation_master_transaction_id = quotation_master_transaction_id;
	}

	public String getQuotation_details_transaction_id() {
		return quotation_details_transaction_id;
	}

	public void setQuotation_details_transaction_id(String quotation_details_transaction_id) {
		this.quotation_details_transaction_id = quotation_details_transaction_id;
	}

	public String getHsn_sac_id() {
		return hsn_sac_id;
	}

	public void setHsn_sac_id(String hsn_sac_id) {
		this.hsn_sac_id = hsn_sac_id;
	}

	public String getProduct_material_id() {
		return product_material_id;
	}

	public void setProduct_material_id(String product_material_id) {
		this.product_material_id = product_material_id;
	}

	public CMtSalesQuotationDetailsTradingRptModel_Not_Used(String financial_year, String quotation_type,
	                                                        String quotation_transaction_type_desc, String quotation_no, String quotation_version,
	                                                        String quotation_date, String department_name, String quotation_by_name, String enquiry_by_name,
	                                                        String assign_to_head_name, String assign_to_name, String approved_by_name, String agent_name,
	                                                        String agent_percent, String quotation_material_cost, String quotation_process_cost,
	                                                        String quotation_labour_cost, String quotation_other_cost, String quotation_other_cost_remark,
	                                                        String quotation_administration_percent, String quotation_administration_cost,
	                                                        String quotation_profit_percent, String quotation_profit_cost, String quotation_discount_percent,
	                                                        String quotation_discount_cost, String quotation_taxable_cost, String quotation_cgst_cost,
	                                                        String quotation_sgst_cost, String quotation_igst_cost, String quotation_total_cost,
	                                                        String overall_schedule_date, String quotation_status_desc, String enquiry_no, String enquiry_version,
	                                                        String enquiry_date, String sr_no, String enquiry_type_short_name, String product_material_code,
	                                                        String product_material_name, String product_material_std_weight, String product_material_unit_name,
	                                                        String product_material_hsn_sac_code, String hsn_sac_percent, String product_material_tech_spect,
	                                                        String product_packing_name, String product_material_quotation_quantity,
	                                                        String product_material_quotation_weight, String product_material_moq_quantity, String material_std_rate,
	                                                        String item_material_cost, String item_process_cost, String item_labour_cost, String item_other_cost,
	                                                        String item_other_cost_remark, String item_administration_percent, String item_administration_cost,
	                                                        String item_profit_percent, String item_profit_cost, String item_discount_percent,
	                                                        String item_discount_cost, String item_taxable_cost, String item_cgst_cost, String item_sgst_cost,
	                                                        String item_igst_cost, String item_total_cost, String quotation_item_status_desc,
	                                                        String product_material_notes, String product_material_conversion_factor, String expected_lead_time,
	                                                        String material_expected_schedule_date, String quotation_item_status, String material_item_approval_remark,
	                                                        String product_fg_bom_no, String product_fg_bom_version, String product_costing_no,
	                                                        String item_dispatch_note_nos, String item_dispatch_challan_nos, String item_invoice_nos,
	                                                        String tax_applicable_desc, String tax_applicable, String transportation_applicable_desc,
	                                                        String transportation_applicable, String bom_applicable_desc, String bom_applicable,
	                                                        String costing_applicable_desc, String costing_applicable, String quotation_life_desc,
	                                                        String quotation_mail_sent_status_desc, String approved_date, String quotation_type_short_name,
	                                                        String expected_branch_name, String expected_branch_address1, String expected_branch_pincode,
	                                                        String expected_branch_city_name, String expected_branch_district_name, String expected_branch_state_name,
	                                                        String expected_branch_country_name, String expected_branch_phone_no, String expected_branch_EmailId,
	                                                        String status_remark, String company_name, String company_branch_name, String other_terms_conditions,
	                                                        String remark, String item_remark, String quotation_life, String quotation_transaction_type,
	                                                        String quotation_mail_sent_status, String customer_name, String customer_state_name, String customer_email,
	                                                        String customer_country_name, String customer_city_name, String customer_district_name, String is_active,
	                                                        String is_delete, String created_by, String created_on, String modified_by, String modified_on,
	                                                        String deleted_by, String deleted_on, String company_id, String department_id, String company_branch_id,
	                                                        String quotation_type_id, String customer_id, String customer_contacts_ids, String customer_state_id,
	                                                        String customer_city_id, String expected_branch_id, String expected_branch_state_id,
	                                                        String expected_branch_city_id, String quotation_by_id, String assign_to_head_id, String assign_to_id,
	                                                        String approved_by_id, String product_material_unit_id, String product_material_packing_id, String agent_id,
	                                                        String quotation_master_transaction_id, String quotation_details_transaction_id, String hsn_sac_id,
	                                                        String product_material_id) {
		super();
		this.financial_year = financial_year;
		this.quotation_type = quotation_type;
		this.quotation_transaction_type_desc = quotation_transaction_type_desc;
		this.quotation_no = quotation_no;
		this.quotation_version = quotation_version;
		this.quotation_date = quotation_date;
		this.department_name = department_name;
		this.quotation_by_name = quotation_by_name;
		this.enquiry_by_name = enquiry_by_name;
		this.assign_to_head_name = assign_to_head_name;
		this.assign_to_name = assign_to_name;
		this.approved_by_name = approved_by_name;
		this.agent_name = agent_name;
		this.agent_percent = agent_percent;
		this.quotation_material_cost = quotation_material_cost;
		this.quotation_process_cost = quotation_process_cost;
		this.quotation_labour_cost = quotation_labour_cost;
		this.quotation_other_cost = quotation_other_cost;
		this.quotation_other_cost_remark = quotation_other_cost_remark;
		this.quotation_administration_percent = quotation_administration_percent;
		this.quotation_administration_cost = quotation_administration_cost;
		this.quotation_profit_percent = quotation_profit_percent;
		this.quotation_profit_cost = quotation_profit_cost;
		this.quotation_discount_percent = quotation_discount_percent;
		this.quotation_discount_cost = quotation_discount_cost;
		this.quotation_taxable_cost = quotation_taxable_cost;
		this.quotation_cgst_cost = quotation_cgst_cost;
		this.quotation_sgst_cost = quotation_sgst_cost;
		this.quotation_igst_cost = quotation_igst_cost;
		this.quotation_total_cost = quotation_total_cost;
		this.overall_schedule_date = overall_schedule_date;
		this.quotation_status_desc = quotation_status_desc;
		this.enquiry_no = enquiry_no;
		this.enquiry_version = enquiry_version;
		this.enquiry_date = enquiry_date;
		this.sr_no = sr_no;
		this.enquiry_type_short_name = enquiry_type_short_name;
		this.product_material_code = product_material_code;
		this.product_material_name = product_material_name;
		this.product_material_std_weight = product_material_std_weight;
		this.product_material_unit_name = product_material_unit_name;
		this.product_material_hsn_sac_code = product_material_hsn_sac_code;
		this.hsn_sac_percent = hsn_sac_percent;
		this.product_material_tech_spect = product_material_tech_spect;
		this.product_packing_name = product_packing_name;
		this.product_material_quotation_quantity = product_material_quotation_quantity;
		this.product_material_quotation_weight = product_material_quotation_weight;
		this.product_material_moq_quantity = product_material_moq_quantity;
		this.material_std_rate = material_std_rate;
		this.item_material_cost = item_material_cost;
		this.item_process_cost = item_process_cost;
		this.item_labour_cost = item_labour_cost;
		this.item_other_cost = item_other_cost;
		this.item_other_cost_remark = item_other_cost_remark;
		this.item_administration_percent = item_administration_percent;
		this.item_administration_cost = item_administration_cost;
		this.item_profit_percent = item_profit_percent;
		this.item_profit_cost = item_profit_cost;
		this.item_discount_percent = item_discount_percent;
		this.item_discount_cost = item_discount_cost;
		this.item_taxable_cost = item_taxable_cost;
		this.item_cgst_cost = item_cgst_cost;
		this.item_sgst_cost = item_sgst_cost;
		this.item_igst_cost = item_igst_cost;
		this.item_total_cost = item_total_cost;
		this.quotation_item_status_desc = quotation_item_status_desc;
		this.product_material_notes = product_material_notes;
		this.product_material_conversion_factor = product_material_conversion_factor;
		this.expected_lead_time = expected_lead_time;
		this.material_expected_schedule_date = material_expected_schedule_date;
		this.quotation_item_status = quotation_item_status;
		this.material_item_approval_remark = material_item_approval_remark;
		this.product_fg_bom_no = product_fg_bom_no;
		this.product_fg_bom_version = product_fg_bom_version;
		this.product_costing_no = product_costing_no;
		this.item_dispatch_note_nos = item_dispatch_note_nos;
		this.item_dispatch_challan_nos = item_dispatch_challan_nos;
		this.item_invoice_nos = item_invoice_nos;
		this.tax_applicable_desc = tax_applicable_desc;
		this.tax_applicable = tax_applicable;
		this.transportation_applicable_desc = transportation_applicable_desc;
		this.transportation_applicable = transportation_applicable;
		this.bom_applicable_desc = bom_applicable_desc;
		this.bom_applicable = bom_applicable;
		this.costing_applicable_desc = costing_applicable_desc;
		this.costing_applicable = costing_applicable;
		this.quotation_life_desc = quotation_life_desc;
		this.quotation_mail_sent_status_desc = quotation_mail_sent_status_desc;
		this.approved_date = approved_date;
		this.quotation_type_short_name = quotation_type_short_name;
		this.expected_branch_name = expected_branch_name;
		this.expected_branch_address1 = expected_branch_address1;
		this.expected_branch_pincode = expected_branch_pincode;
		this.expected_branch_city_name = expected_branch_city_name;
		this.expected_branch_district_name = expected_branch_district_name;
		this.expected_branch_state_name = expected_branch_state_name;
		this.expected_branch_country_name = expected_branch_country_name;
		this.expected_branch_phone_no = expected_branch_phone_no;
		this.expected_branch_EmailId = expected_branch_EmailId;
		this.status_remark = status_remark;
		this.company_name = company_name;
		this.company_branch_name = company_branch_name;
		this.other_terms_conditions = other_terms_conditions;
		this.remark = remark;
		this.item_remark = item_remark;
		this.quotation_life = quotation_life;
		this.quotation_transaction_type = quotation_transaction_type;
		this.quotation_mail_sent_status = quotation_mail_sent_status;
		this.customer_name = customer_name;
		this.customer_state_name = customer_state_name;
		this.customer_email = customer_email;
		this.customer_country_name = customer_country_name;
		this.customer_city_name = customer_city_name;
		this.customer_district_name = customer_district_name;
		this.is_active = is_active;
		this.is_delete = is_delete;
		this.created_by = created_by;
		this.created_on = created_on;
		this.modified_by = modified_by;
		this.modified_on = modified_on;
		this.deleted_by = deleted_by;
		this.deleted_on = deleted_on;
		this.company_id = company_id;
		this.department_id = department_id;
		this.company_branch_id = company_branch_id;
		this.quotation_type_id = quotation_type_id;
		this.customer_id = customer_id;
		this.customer_contacts_ids = customer_contacts_ids;
		this.customer_state_id = customer_state_id;
		this.customer_city_id = customer_city_id;
		this.expected_branch_id = expected_branch_id;
		this.expected_branch_state_id = expected_branch_state_id;
		this.expected_branch_city_id = expected_branch_city_id;
		this.quotation_by_id = quotation_by_id;
		this.assign_to_head_id = assign_to_head_id;
		this.assign_to_id = assign_to_id;
		this.approved_by_id = approved_by_id;
		this.product_material_unit_id = product_material_unit_id;
		this.product_material_packing_id = product_material_packing_id;
		this.agent_id = agent_id;
		this.quotation_master_transaction_id = quotation_master_transaction_id;
		this.quotation_details_transaction_id = quotation_details_transaction_id;
		this.hsn_sac_id = hsn_sac_id;
		this.product_material_id = product_material_id;
	}

	public CMtSalesQuotationDetailsTradingRptModel_Not_Used() {
		super();

	}
}
