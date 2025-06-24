package com.erp.PtPurchaseDetails.Model;

import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@Immutable
@Subselect("select * from ptv_purchase_order_master_summary_rpt")
public class CPtPurchaseOrderMasterSummaryRptModel_Not_Used {

	@Id
	private String purchase_order_master_transaction_id;
	private String company_name;
	private String company_branch_name;
	private String financial_year;
	private String purchase_order_no;
	private String purchase_order_version;
	private String purchase_order_date;
	private String purchase_order_status;
	private String product_type_name;
	private String product_type_short_name;
	private String product_type_group;
	private String purchase_order_type;
	private String purchase_order_life;
	private String purchase_process_entry;
	private String purchase_order_creation_type;
	private String purchase_order_creation_type_desc;
	private String purchase_process_entry_desc;
	private String purchase_order_status_desc;
	private String agent_paid_status_desc;
	private String purchase_order_acceptance_status_desc;
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
	private String basic_total;
	private String transport_amount;
	private String is_freight_taxable;
	private String hsn_sac_type;
	private String hsn_sac_code;
	private String hsn_sac_description;
	private String hsn_sac_rate;
	private String freight_amount;
	private String packing_amount;
	private String po_discount_percent;
	private String po_discount_amount;
	private String other_amount;
	private String taxable_total;
	private String cgst_total;
	private String sgst_total;
	private String igst_total;
	private String roundoff;
	private String grand_total;
	private String agent_name;
	private String agent_percent;
	private String agent_paid_status;
	private String purchase_order_acceptance_status;
	private String purchase_order_mail_sent_status;
	private String status_remark;
	private String other_terms_conditions;
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
	private String purchase_order_type_id;
	private String customer_id;
	private String supplier_id;
	private String supplier_state_id;
	private String supplier_city_id;
	private String supplier_contacts_ids;
	private String expected_branch_id;
	private String expected_branch_state_id;
	private String expected_branch_city_id;
	private String approved_by_id;
	private String agent_id;
	private Integer freight_hsn_code_id;


	public String getPurchase_order_master_transaction_id() {
		return purchase_order_master_transaction_id;
	}

	public void setPurchase_order_master_transaction_id(String purchase_order_master_transaction_id) {
		this.purchase_order_master_transaction_id = purchase_order_master_transaction_id;
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

	public String getFinancial_year() {
		return financial_year;
	}

	public void setFinancial_year(String financial_year) {
		this.financial_year = financial_year;
	}

	public String getProduct_type_name() {
		return product_type_name;
	}

	public void setProduct_type_name(String product_type_name) {
		this.product_type_name = product_type_name;
	}

	public String getProduct_type_short_name() {
		return product_type_short_name;
	}

	public void setProduct_type_short_name(String product_type_short_name) {
		this.product_type_short_name = product_type_short_name;
	}

	public String getProduct_type_group() {
		return product_type_group;
	}

	public void setProduct_type_group(String product_type_group) {
		this.product_type_group = product_type_group;
	}

	public String getPurchase_order_type() {
		return purchase_order_type;
	}

	public void setPurchase_order_type(String purchase_order_type) {
		this.purchase_order_type = purchase_order_type;
	}

	public String getPurchase_order_life() {
		return purchase_order_life;
	}

	public void setPurchase_order_life(String purchase_order_life) {
		this.purchase_order_life = purchase_order_life;
	}

	public String getPurchase_process_entry() {
		return purchase_process_entry;
	}

	public void setPurchase_process_entry(String purchase_process_entry) {
		this.purchase_process_entry = purchase_process_entry;
	}

	public String getPurchase_order_creation_type() {
		return purchase_order_creation_type;
	}

	public void setPurchase_order_creation_type(String purchase_order_creation_type) {
		this.purchase_order_creation_type = purchase_order_creation_type;
	}

	public String getPurchase_order_creation_type_desc() {
		return purchase_order_creation_type_desc;
	}

	public void setPurchase_order_creation_type_desc(String purchase_order_creation_type_desc) {
		this.purchase_order_creation_type_desc = purchase_order_creation_type_desc;
	}

	public String getPurchase_process_entry_desc() {
		return purchase_process_entry_desc;
	}

	public void setPurchase_process_entry_desc(String purchase_process_entry_desc) {
		this.purchase_process_entry_desc = purchase_process_entry_desc;
	}

	public String getPurchase_order_status_desc() {
		return purchase_order_status_desc;
	}

	public void setPurchase_order_status_desc(String purchase_order_status_desc) {
		this.purchase_order_status_desc = purchase_order_status_desc;
	}

	public String getAgent_paid_status_desc() {
		return agent_paid_status_desc;
	}

	public void setAgent_paid_status_desc(String agent_paid_status_desc) {
		this.agent_paid_status_desc = agent_paid_status_desc;
	}

	public String getPurchase_order_no() {
		return purchase_order_no;
	}

	public void setPurchase_order_no(String purchase_order_no) {
		this.purchase_order_no = purchase_order_no;
	}

	public String getPurchase_order_date() {
		return purchase_order_date;
	}

	public void setPurchase_order_date(String purchase_order_date) {
		this.purchase_order_date = purchase_order_date;
	}

	public String getPurchase_order_version() {
		return purchase_order_version;
	}

	public void setPurchase_order_version(String purchase_order_version) {
		this.purchase_order_version = purchase_order_version;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
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

	public String getCustomer_state_name() {
		return customer_state_name;
	}

	public void setCustomer_state_name(String customer_state_name) {
		this.customer_state_name = customer_state_name;
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

	public String getCustomer_order_no() {
		return customer_order_no;
	}

	public void setCustomer_order_no(String customer_order_no) {
		this.customer_order_no = customer_order_no;
	}

	public String getCustomer_order_Date() {
		return customer_order_Date;
	}

	public void setCustomer_order_Date(String customer_order_Date) {
		this.customer_order_Date = customer_order_Date;
	}

	public String getSupplier_type() {
		return supplier_type;
	}

	public void setSupplier_type(String supplier_type) {
		this.supplier_type = supplier_type;
	}

	public String getSupplier_code() {
		return supplier_code;
	}

	public void setSupplier_code(String supplier_code) {
		this.supplier_code = supplier_code;
	}

	public String getSupplier_name() {
		return supplier_name;
	}

	public void setSupplier_name(String supplier_name) {
		this.supplier_name = supplier_name;
	}

	public String getSupplier_short_name() {
		return supplier_short_name;
	}

	public void setSupplier_short_name(String supplier_short_name) {
		this.supplier_short_name = supplier_short_name;
	}

	public String getSupp_branch_address1() {
		return supp_branch_address1;
	}

	public void setSupp_branch_address1(String supp_branch_address1) {
		this.supp_branch_address1 = supp_branch_address1;
	}

	public String getSupp_branch_pincode() {
		return supp_branch_pincode;
	}

	public void setSupp_branch_pincode(String supp_branch_pincode) {
		this.supp_branch_pincode = supp_branch_pincode;
	}

	public String getCity_name() {
		return city_name;
	}

	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}

	public String getDistrict_name() {
		return district_name;
	}

	public void setDistrict_name(String district_name) {
		this.district_name = district_name;
	}

	public String getState_name() {
		return state_name;
	}

	public void setState_name(String state_name) {
		this.state_name = state_name;
	}

	public String getCountry_name() {
		return country_name;
	}

	public void setCountry_name(String country_name) {
		this.country_name = country_name;
	}

	public String getSupp_branch_phone_no() {
		return supp_branch_phone_no;
	}

	public void setSupp_branch_phone_no(String supp_branch_phone_no) {
		this.supp_branch_phone_no = supp_branch_phone_no;
	}

	public String getSupp_branch_EmailId() {
		return supp_branch_EmailId;
	}

	public void setSupp_branch_EmailId(String supp_branch_EmailId) {
		this.supp_branch_EmailId = supp_branch_EmailId;
	}

	public String getExpected_branch_name() {
		return expected_branch_name;
	}

	public void setExpected_branch_name(String expected_branch_name) {
		this.expected_branch_name = expected_branch_name;
	}

	public String getExpected_branch_short_name() {
		return expected_branch_short_name;
	}

	public void setExpected_branch_short_name(String expected_branch_short_name) {
		this.expected_branch_short_name = expected_branch_short_name;
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

	public String getApproved_by() {
		return approved_by;
	}

	public void setApproved_by(String approved_by) {
		this.approved_by = approved_by;
	}

	public String getApproved_date() {
		return approved_date;
	}

	public void setApproved_date(String approved_date) {
		this.approved_date = approved_date;
	}

	public String getExpected_schedule_date() {
		return expected_schedule_date;
	}

	public void setExpected_schedule_date(String expected_schedule_date) {
		this.expected_schedule_date = expected_schedule_date;
	}

	public String getBasic_total() {
		return basic_total;
	}

	public void setBasic_total(String basic_total) {
		this.basic_total = basic_total;
	}

	public String getTransport_amount() {
		return transport_amount;
	}

	public void setTransport_amount(String transport_amount) {
		this.transport_amount = transport_amount;
	}

	public String getFreight_amount() {
		return freight_amount;
	}

	public void setFreight_amount(String freight_amount) {
		this.freight_amount = freight_amount;
	}


	public String getIs_freight_taxable() {
		return is_freight_taxable;
	}

	public void setIs_freight_taxable(String is_freight_taxable) {
		this.is_freight_taxable = is_freight_taxable;
	}

	public String getPacking_amount() {
		return packing_amount;
	}

	public void setPacking_amount(String packing_amount) {
		this.packing_amount = packing_amount;
	}

	public String getPo_discount_percent() {
		return po_discount_percent;
	}

	public void setPo_discount_percent(String po_discount_percent) {
		this.po_discount_percent = po_discount_percent;
	}

	public String getPo_discount_amount() {
		return po_discount_amount;
	}

	public void setPo_discount_amount(String po_discount_amount) {
		this.po_discount_amount = po_discount_amount;
	}

	public String getOther_amount() {
		return other_amount;
	}

	public void setOther_amount(String other_amount) {
		this.other_amount = other_amount;
	}

	public String getTaxable_total() {
		return taxable_total;
	}

	public void setTaxable_total(String taxable_total) {
		this.taxable_total = taxable_total;
	}

	public String getHsn_sac_type() {
		return hsn_sac_type;
	}

	public void setHsn_sac_type(String hsn_sac_type) {
		this.hsn_sac_type = hsn_sac_type;
	}

	public String getHsn_sac_code() {
		return hsn_sac_code;
	}

	public void setHsn_sac_code(String hsn_sac_code) {
		this.hsn_sac_code = hsn_sac_code;
	}

	public String getHsn_sac_description() {
		return hsn_sac_description;
	}

	public void setHsn_sac_description(String hsn_sac_description) {
		this.hsn_sac_description = hsn_sac_description;
	}

	public String getHsn_sac_rate() {
		return hsn_sac_rate;
	}

	public void setHsn_sac_rate(String hsn_sac_rate) {
		this.hsn_sac_rate = hsn_sac_rate;
	}

	public String getCgst_total() {
		return cgst_total;
	}

	public void setCgst_total(String cgst_total) {
		this.cgst_total = cgst_total;
	}

	public String getSgst_total() {
		return sgst_total;
	}

	public void setSgst_total(String sgst_total) {
		this.sgst_total = sgst_total;
	}

	public String getIgst_total() {
		return igst_total;
	}

	public void setIgst_total(String igst_total) {
		this.igst_total = igst_total;
	}

	public String getRoundoff() {
		return roundoff;
	}

	public void setRoundoff(String roundoff) {
		this.roundoff = roundoff;
	}

	public String getGrand_total() {
		return grand_total;
	}

	public void setGrand_total(String grand_total) {
		this.grand_total = grand_total;
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

	public String getAgent_paid_status() {
		return agent_paid_status;
	}

	public void setAgent_paid_status(String agent_paid_status) {
		this.agent_paid_status = agent_paid_status;
	}

	public String getPurchase_order_acceptance_status() {
		return purchase_order_acceptance_status;
	}

	public void setPurchase_order_acceptance_status(String purchase_order_acceptance_status) {
		this.purchase_order_acceptance_status = purchase_order_acceptance_status;
	}

	public String getPurchase_order_mail_sent_status() {
		return purchase_order_mail_sent_status;
	}

	public void setPurchase_order_mail_sent_status(String purchase_order_mail_sent_status) {
		this.purchase_order_mail_sent_status = purchase_order_mail_sent_status;
	}

	public String getPurchase_order_status() {
		return purchase_order_status;
	}

	public void setPurchase_order_status(String purchase_order_status) {
		this.purchase_order_status = purchase_order_status;
	}

	public String getStatus_remark() {
		return status_remark;
	}

	public void setStatus_remark(String status_remark) {
		this.status_remark = status_remark;
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

	public String getCompany_branch_id() {
		return company_branch_id;
	}

	public void setCompany_branch_id(String company_branch_id) {
		this.company_branch_id = company_branch_id;
	}

	public String getPurchase_order_type_id() {
		return purchase_order_type_id;
	}

	public void setPurchase_order_type_id(String purchase_order_type_id) {
		this.purchase_order_type_id = purchase_order_type_id;
	}

	public String getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}

	public String getSupplier_id() {
		return supplier_id;
	}

	public void setSupplier_id(String supplier_id) {
		this.supplier_id = supplier_id;
	}

	public String getSupplier_state_id() {
		return supplier_state_id;
	}

	public void setSupplier_state_id(String supplier_state_id) {
		this.supplier_state_id = supplier_state_id;
	}

	public String getSupplier_city_id() {
		return supplier_city_id;
	}

	public void setSupplier_city_id(String supplier_city_id) {
		this.supplier_city_id = supplier_city_id;
	}

	public String getSupplier_contacts_ids() {
		return supplier_contacts_ids;
	}

	public void setSupplier_contacts_ids(String supplier_contacts_ids) {
		this.supplier_contacts_ids = supplier_contacts_ids;
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

	public String getApproved_by_id() {
		return approved_by_id;
	}

	public void setApproved_by_id(String approved_by_id) {
		this.approved_by_id = approved_by_id;
	}

	public String getAgent_id() {
		return agent_id;
	}

	public void setAgent_id(String agent_id) {
		this.agent_id = agent_id;
	}


	public Integer getFreight_hsn_code_id() {
		return freight_hsn_code_id;
	}

	public void setFreight_hsn_code_id(Integer freight_hsn_code_id) {
		this.freight_hsn_code_id = freight_hsn_code_id;
	}

	public String getPurchase_order_acceptance_status_desc() {
		return purchase_order_acceptance_status_desc;
	}

	public void setPurchase_order_acceptance_status_desc(String purchase_order_acceptance_status_desc) {
		this.purchase_order_acceptance_status_desc = purchase_order_acceptance_status_desc;
	}

	public CPtPurchaseOrderMasterSummaryRptModel_Not_Used(String purchase_order_master_transaction_id, String company_name,
	                                                      String company_branch_name, String financial_year, String purchase_order_no, String purchase_order_version,
	                                                      String purchase_order_date, String purchase_order_status, String product_type_name,
	                                                      String product_type_short_name, String product_type_group, String purchase_order_type,
	                                                      String purchase_order_life, String purchase_process_entry, String purchase_order_creation_type,
	                                                      String purchase_order_creation_type_desc, String purchase_process_entry_desc,
	                                                      String purchase_order_status_desc, String agent_paid_status_desc, String supplier_type,
	                                                      String supplier_code, String supplier_name, String supplier_short_name, String supp_branch_address1,
	                                                      String supp_branch_pincode, String city_name, String district_name, String state_name, String country_name,
	                                                      String supp_branch_phone_no, String supp_branch_EmailId, String customer_name, String customer_email,
	                                                      String customer_country_name, String customer_state_name, String customer_city_name,
	                                                      String customer_district_name, String customer_order_no, String customer_order_Date,
	                                                      String expected_branch_name, String expected_branch_short_name, String expected_branch_address1,
	                                                      String expected_branch_pincode, String expected_branch_city_name, String expected_branch_district_name,
	                                                      String expected_branch_state_name, String expected_branch_country_name, String expected_branch_phone_no,
	                                                      String expected_branch_EmailId, String approved_by, String approved_date, String expected_schedule_date,
	                                                      String basic_total, String transport_amount, String is_freight_taxable, String hsn_sac_type,
	                                                      String hsn_sac_code, String hsn_sac_description, String hsn_sac_rate, String freight_amount,
	                                                      String packing_amount, String po_discount_percent, String po_discount_amount, String other_amount,
	                                                      String taxable_total, String cgst_total, String sgst_total, String igst_total, String roundoff,
	                                                      String grand_total, String agent_name, String agent_percent, String agent_paid_status,
	                                                      String purchase_order_acceptance_status, String purchase_order_mail_sent_status, String status_remark,
	                                                      String other_terms_conditions, String remark, String is_active, String is_delete, String created_by,
	                                                      String created_on, String modified_by, String modified_on, String deleted_by, String deleted_on,
	                                                      String company_id, String company_branch_id, String purchase_order_type_id, String customer_id,
	                                                      String supplier_id, String supplier_state_id, String supplier_city_id, String supplier_contacts_ids,
	                                                      String expected_branch_id, String expected_branch_state_id, String expected_branch_city_id,
	                                                      String approved_by_id, String agent_id, Integer freight_hsn_code_id, String purchase_order_acceptance_status_desc) {
		super();
		this.purchase_order_master_transaction_id = purchase_order_master_transaction_id;
		this.company_name = company_name;
		this.company_branch_name = company_branch_name;
		this.financial_year = financial_year;
		this.purchase_order_no = purchase_order_no;
		this.purchase_order_version = purchase_order_version;
		this.purchase_order_date = purchase_order_date;
		this.purchase_order_status = purchase_order_status;
		this.product_type_name = product_type_name;
		this.product_type_short_name = product_type_short_name;
		this.product_type_group = product_type_group;
		this.purchase_order_type = purchase_order_type;
		this.purchase_order_life = purchase_order_life;
		this.purchase_process_entry = purchase_process_entry;
		this.purchase_order_creation_type = purchase_order_creation_type;
		this.purchase_order_creation_type_desc = purchase_order_creation_type_desc;
		this.purchase_process_entry_desc = purchase_process_entry_desc;
		this.purchase_order_status_desc = purchase_order_status_desc;
		this.agent_paid_status_desc = agent_paid_status_desc;
		this.supplier_type = supplier_type;
		this.supplier_code = supplier_code;
		this.supplier_name = supplier_name;
		this.supplier_short_name = supplier_short_name;
		this.supp_branch_address1 = supp_branch_address1;
		this.supp_branch_pincode = supp_branch_pincode;
		this.city_name = city_name;
		this.district_name = district_name;
		this.state_name = state_name;
		this.country_name = country_name;
		this.supp_branch_phone_no = supp_branch_phone_no;
		this.supp_branch_EmailId = supp_branch_EmailId;
		this.customer_name = customer_name;
		this.customer_email = customer_email;
		this.customer_country_name = customer_country_name;
		this.customer_state_name = customer_state_name;
		this.customer_city_name = customer_city_name;
		this.customer_district_name = customer_district_name;
		this.customer_order_no = customer_order_no;
		this.customer_order_Date = customer_order_Date;
		this.expected_branch_name = expected_branch_name;
		this.expected_branch_short_name = expected_branch_short_name;
		this.expected_branch_address1 = expected_branch_address1;
		this.expected_branch_pincode = expected_branch_pincode;
		this.expected_branch_city_name = expected_branch_city_name;
		this.expected_branch_district_name = expected_branch_district_name;
		this.expected_branch_state_name = expected_branch_state_name;
		this.expected_branch_country_name = expected_branch_country_name;
		this.expected_branch_phone_no = expected_branch_phone_no;
		this.expected_branch_EmailId = expected_branch_EmailId;
		this.approved_by = approved_by;
		this.approved_date = approved_date;
		this.expected_schedule_date = expected_schedule_date;
		this.basic_total = basic_total;
		this.transport_amount = transport_amount;
		this.is_freight_taxable = is_freight_taxable;
		this.hsn_sac_type = hsn_sac_type;
		this.hsn_sac_code = hsn_sac_code;
		this.hsn_sac_description = hsn_sac_description;
		this.hsn_sac_rate = hsn_sac_rate;
		this.freight_amount = freight_amount;
		this.packing_amount = packing_amount;
		this.po_discount_percent = po_discount_percent;
		this.po_discount_amount = po_discount_amount;
		this.other_amount = other_amount;
		this.taxable_total = taxable_total;
		this.cgst_total = cgst_total;
		this.sgst_total = sgst_total;
		this.igst_total = igst_total;
		this.roundoff = roundoff;
		this.grand_total = grand_total;
		this.agent_name = agent_name;
		this.agent_percent = agent_percent;
		this.agent_paid_status = agent_paid_status;
		this.purchase_order_acceptance_status = purchase_order_acceptance_status;
		this.purchase_order_mail_sent_status = purchase_order_mail_sent_status;
		this.status_remark = status_remark;
		this.other_terms_conditions = other_terms_conditions;
		this.remark = remark;
		this.is_active = is_active;
		this.is_delete = is_delete;
		this.created_by = created_by;
		this.created_on = created_on;
		this.modified_by = modified_by;
		this.modified_on = modified_on;
		this.deleted_by = deleted_by;
		this.deleted_on = deleted_on;
		this.company_id = company_id;
		this.company_branch_id = company_branch_id;
		this.purchase_order_type_id = purchase_order_type_id;
		this.customer_id = customer_id;
		this.supplier_id = supplier_id;
		this.supplier_state_id = supplier_state_id;
		this.supplier_city_id = supplier_city_id;
		this.supplier_contacts_ids = supplier_contacts_ids;
		this.expected_branch_id = expected_branch_id;
		this.expected_branch_state_id = expected_branch_state_id;
		this.expected_branch_city_id = expected_branch_city_id;
		this.approved_by_id = approved_by_id;
		this.agent_id = agent_id;
		this.freight_hsn_code_id = freight_hsn_code_id;
		this.purchase_order_acceptance_status_desc = purchase_order_acceptance_status_desc;
	}

	public CPtPurchaseOrderMasterSummaryRptModel_Not_Used() {
		super();

	}

}
