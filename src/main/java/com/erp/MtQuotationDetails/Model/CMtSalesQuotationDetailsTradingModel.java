package com.erp.MtQuotationDetails.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "mt_sales_quotation_details_trading")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CMtSalesQuotationDetailsTradingModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "quotation_details_transaction_id")
	private int quotation_details_transaction_id;
	private Integer quotation_master_transaction_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String financial_year;
	private Integer quotation_type_id;
	private String quotation_type;
	private String quotation_no;
	private String quotation_date;
	private Integer quotation_version;
	private Integer department_id;
	private String enquiry_no;
	private String enquiry_date;
	private Integer enquiry_version;
	private Integer sr_no;
	private String product_material_id;
	private Integer product_type_id;
	private String product_type_short_name;
	private Integer hsn_sac_id;
	private double hsn_sac_percent;
	private String product_material_tech_spect;
	private Integer product_material_unit_id;
	private Integer product_material_packing_id;
	private double product_material_quotation_quantity;
	private double product_material_quotation_weight;
	private double product_material_moq_quantity;
	private String product_material_notes;
	private double product_material_conversion_factor;
	private Integer expected_lead_time;
	private String material_expected_schedule_date;
	private String product_fg_bom_no;
	private Integer product_fg_bom_version;
	private String product_costing_no;
	private double item_material_cost;
	private double item_process_cost;
	private double item_labour_cost;
	private double item_administration_percent;
	private double item_administration_cost;
	private double item_profit_percent;
	private double item_profit_cost;
	private double item_other_cost;
	private String item_other_cost_remark;
	private double item_discount_percent;
	private double item_discount_cost;
	private double item_taxable_cost;
	private double item_cgst_percent;
	private double item_cgst_cost;
	private double item_sgst_percent;
	private double item_sgst_cost;
	private double item_igst_percent;
	private double item_igst_cost;
	private double item_total_cost;
	private double material_std_rate;
	private String quotation_item_status;
	private String material_item_approval_remark;
	private String item_dispatch_note_nos;
	private String item_dispatch_challan_nos;
	private String item_invoice_nos;
	private String item_terms_conditions;
	private String item_remark;
	private boolean is_active = Boolean.TRUE;
	private boolean is_delete = Boolean.FALSE;
	@Column(name = "created_by", updatable = false)
	private String created_by;
	@CreationTimestamp
	@Column(name = "created_on", updatable = false)
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;

	public int getQuotation_details_transaction_id() {
		return quotation_details_transaction_id;
	}

	public void setQuotation_details_transaction_id(int quotation_details_transaction_id) {
		this.quotation_details_transaction_id = quotation_details_transaction_id;
	}

	public Integer getQuotation_master_transaction_id() {
		return quotation_master_transaction_id;
	}

	public void setQuotation_master_transaction_id(Integer quotation_master_transaction_id) {
		this.quotation_master_transaction_id = quotation_master_transaction_id;
	}

	public Integer getCompany_id() {
		return company_id;
	}

	public void setCompany_id(Integer company_id) {
		this.company_id = company_id;
	}

	public Integer getCompany_branch_id() {
		return company_branch_id;
	}

	public void setCompany_branch_id(Integer company_branch_id) {
		this.company_branch_id = company_branch_id;
	}

	public String getFinancial_year() {
		return financial_year;
	}

	public void setFinancial_year(String financial_year) {
		this.financial_year = financial_year;
	}

	public Integer getQuotation_type_id() {
		return quotation_type_id;
	}

	public void setQuotation_type_id(Integer quotation_type_id) {
		this.quotation_type_id = quotation_type_id;
	}

	public String getQuotation_type() {
		return quotation_type;
	}

	public void setQuotation_type(String quotation_type) {
		this.quotation_type = quotation_type;
	}

	public String getQuotation_no() {
		return quotation_no;
	}

	public void setQuotation_no(String quotation_no) {
		this.quotation_no = quotation_no;
	}

	public String getQuotation_date() {
		return quotation_date;
	}

	public void setQuotation_date(String quotation_date) {
		this.quotation_date = quotation_date;
	}

	public Integer getQuotation_version() {
		return quotation_version;
	}

	public void setQuotation_version(Integer quotation_version) {
		this.quotation_version = quotation_version;
	}

	public Integer getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(Integer department_id) {
		this.department_id = department_id;
	}

	public String getenquiry_no() {
		return enquiry_no;
	}

	public void setenquiry_no(String enquiry_no) {
		this.enquiry_no = enquiry_no;
	}

	public String getenquiry_date() {
		return enquiry_date;
	}

	public void setenquiry_date(String enquiry_date) {
		if (enquiry_date == null || enquiry_date.isEmpty()) {
			this.enquiry_date = null;
		} else {
			this.enquiry_date = enquiry_date;
		}
	}

	public Integer getenquiry_version() {
		return enquiry_version;
	}

	public void setenquiry_version(Integer enquiry_version) {
		this.enquiry_version = enquiry_version;
	}

	public Integer getSr_no() {
		return sr_no;
	}

	public void setSr_no(Integer sr_no) {
		this.sr_no = sr_no;
	}

	public String getProduct_material_id() {
		return product_material_id;
	}

	public void setProduct_material_id(String product_material_id) {
		this.product_material_id = product_material_id;
	}

	public Integer getHsn_sac_id() {
		return hsn_sac_id;
	}

	public void setHsn_sac_id(Integer hsn_sac_id) {
		this.hsn_sac_id = hsn_sac_id;
	}

	public double getHsn_sac_percent() {
		return hsn_sac_percent;
	}

	public void setHsn_sac_percent(double hsn_sac_percent) {
		this.hsn_sac_percent = hsn_sac_percent;
	}

	public String getProduct_material_tech_spect() {
		return product_material_tech_spect;
	}

	public void setProduct_material_tech_spect(String product_material_tech_spect) {
		this.product_material_tech_spect = product_material_tech_spect;
	}

	public Integer getProduct_material_unit_id() {
		return product_material_unit_id;
	}

	public void setProduct_material_unit_id(Integer product_material_unit_id) {
		this.product_material_unit_id = product_material_unit_id;
	}

	public Integer getProduct_material_packing_id() {
		return product_material_packing_id;
	}

	public void setProduct_material_packing_id(Integer product_material_packing_id) {
		this.product_material_packing_id = product_material_packing_id;
	}

	public double getProduct_material_quotation_quantity() {
		return product_material_quotation_quantity;
	}

	public void setProduct_material_quotation_quantity(double product_material_quotation_quantity) {
		this.product_material_quotation_quantity = product_material_quotation_quantity;
	}

	public double getProduct_material_quotation_weight() {
		return product_material_quotation_weight;
	}

	public void setProduct_material_quotation_weight(double product_material_quotation_weight) {
		this.product_material_quotation_weight = product_material_quotation_weight;
	}

	public double getProduct_material_moq_quantity() {
		return product_material_moq_quantity;
	}

	public void setProduct_material_moq_quantity(double product_material_moq_quantity) {
		this.product_material_moq_quantity = product_material_moq_quantity;
	}

	public String getProduct_material_notes() {
		return product_material_notes;
	}

	public void setProduct_material_notes(String product_material_notes) {
		this.product_material_notes = product_material_notes;
	}

	public double getProduct_material_conversion_factor() {
		return product_material_conversion_factor;
	}

	public void setProduct_material_conversion_factor(double product_material_conversion_factor) {
		this.product_material_conversion_factor = product_material_conversion_factor;
	}

	public Integer getExpected_lead_time() {
		return expected_lead_time;
	}

	public void setExpected_lead_time(Integer expected_lead_time) {
		this.expected_lead_time = expected_lead_time;
	}

	public String getMaterial_expected_schedule_date() {
		return material_expected_schedule_date;
	}

	public void setMaterial_expected_schedule_date(String material_expected_schedule_date) {
		this.material_expected_schedule_date = material_expected_schedule_date;
	}

	public String getProduct_fg_bom_no() {
		return product_fg_bom_no;
	}

	public void setProduct_fg_bom_no(String product_fg_bom_no) {
		this.product_fg_bom_no = product_fg_bom_no;
	}

	public Integer getProduct_fg_bom_version() {
		return product_fg_bom_version;
	}

	public void setProduct_fg_bom_version(Integer product_fg_bom_version) {
		this.product_fg_bom_version = product_fg_bom_version;
	}

	public String getProduct_costing_no() {
		return product_costing_no;
	}

	public void setProduct_costing_no(String product_costing_no) {
		this.product_costing_no = product_costing_no;
	}

	public double getItem_material_cost() {
		return item_material_cost;
	}

	public void setItem_material_cost(double item_material_cost) {
		this.item_material_cost = item_material_cost;
	}

	public double getItem_process_cost() {
		return item_process_cost;
	}

	public void setItem_process_cost(double item_process_cost) {
		this.item_process_cost = item_process_cost;
	}

	public double getItem_labour_cost() {
		return item_labour_cost;
	}

	public void setItem_labour_cost(double item_labour_cost) {
		this.item_labour_cost = item_labour_cost;
	}

	public double getItem_administration_percent() {
		return item_administration_percent;
	}

	public void setItem_administration_percent(double item_administration_percent) {
		this.item_administration_percent = item_administration_percent;
	}

	public double getItem_administration_cost() {
		return item_administration_cost;
	}

	public void setItem_administration_cost(double item_administration_cost) {
		this.item_administration_cost = item_administration_cost;
	}

	public double getItem_profit_percent() {
		return item_profit_percent;
	}

	public void setItem_profit_percent(double item_profit_percent) {
		this.item_profit_percent = item_profit_percent;
	}

	public double getItem_profit_cost() {
		return item_profit_cost;
	}

	public void setItem_profit_cost(double item_profit_cost) {
		this.item_profit_cost = item_profit_cost;
	}

	public double getItem_other_cost() {
		return item_other_cost;
	}

	public void setItem_other_cost(double item_other_cost) {
		this.item_other_cost = item_other_cost;
	}

	public String getItem_other_cost_remark() {
		return item_other_cost_remark;
	}

	public void setItem_other_cost_remark(String item_other_cost_remark) {
		this.item_other_cost_remark = item_other_cost_remark;
	}

	public double getItem_discount_percent() {
		return item_discount_percent;
	}

	public void setItem_discount_percent(double item_discount_percent) {
		this.item_discount_percent = item_discount_percent;
	}

	public double getItem_discount_cost() {
		return item_discount_cost;
	}

	public void setItem_discount_cost(double item_discount_cost) {
		this.item_discount_cost = item_discount_cost;
	}

	public double getItem_taxable_cost() {
		return item_taxable_cost;
	}

	public void setItem_taxable_cost(double item_taxable_cost) {
		this.item_taxable_cost = item_taxable_cost;
	}

	public double getItem_cgst_percent() {
		return item_cgst_percent;
	}

	public void setItem_cgst_percent(double item_cgst_percent) {
		this.item_cgst_percent = item_cgst_percent;
	}

	public double getItem_cgst_cost() {
		return item_cgst_cost;
	}

	public void setItem_cgst_cost(double item_cgst_cost) {
		this.item_cgst_cost = item_cgst_cost;
	}

	public double getItem_sgst_percent() {
		return item_sgst_percent;
	}

	public void setItem_sgst_percent(double item_sgst_percent) {
		this.item_sgst_percent = item_sgst_percent;
	}

	public double getItem_sgst_cost() {
		return item_sgst_cost;
	}

	public void setItem_sgst_cost(double item_sgst_cost) {
		this.item_sgst_cost = item_sgst_cost;
	}

	public double getItem_igst_percent() {
		return item_igst_percent;
	}

	public void setItem_igst_percent(double item_igst_percent) {
		this.item_igst_percent = item_igst_percent;
	}

	public double getItem_igst_cost() {
		return item_igst_cost;
	}

	public void setItem_igst_cost(double item_igst_cost) {
		this.item_igst_cost = item_igst_cost;
	}

	public double getItem_total_cost() {
		return item_total_cost;
	}

	public void setItem_total_cost(double item_total_cost) {
		this.item_total_cost = item_total_cost;
	}

	public double getMaterial_std_rate() {
		return material_std_rate;
	}

	public void setMaterial_std_rate(double material_std_rate) {
		this.material_std_rate = material_std_rate;
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

	public String getItem_terms_conditions() {
		return item_terms_conditions;
	}

	public void setItem_terms_conditions(String item_terms_conditions) {
		this.item_terms_conditions = item_terms_conditions;
	}

	public String getItem_remark() {
		return item_remark;
	}

	public void setItem_remark(String item_remark) {
		this.item_remark = item_remark;
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

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public Date getCreated_on() {
		return created_on;
	}

	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
	}

	public String getModified_by() {
		return modified_by;
	}

	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}

	public Date getModified_on() {
		return modified_on;
	}

	public void setModified_on(Date modified_on) {
		this.modified_on = modified_on;
	}

	public String getDeleted_by() {
		return deleted_by;
	}

	public void setDeleted_by(String deleted_by) {
		this.deleted_by = deleted_by;
	}

	public Date getDeleted_on() {
		return deleted_on;
	}

	public void setDeleted_on(Date deleted_on) {
		this.deleted_on = deleted_on;
	}

	public CMtSalesQuotationDetailsTradingModel(int quotation_details_transaction_id,
	                                            Integer quotation_master_transaction_id, Integer company_id, Integer company_branch_id,
	                                            String financial_year, Integer quotation_type_id, String quotation_type, String quotation_no,
	                                            String quotation_date, Integer quotation_version, Integer department_id, String enquiry_no,
	                                            String enquiry_date, Integer enquiry_version, Integer sr_no, String product_material_id,
	                                            Integer hsn_sac_id, double hsn_sac_percent, String product_material_tech_spect,
	                                            Integer product_material_unit_id, Integer product_material_packing_id,
	                                            double product_material_quotation_quantity, double product_material_quotation_weight,
	                                            double product_material_moq_quantity, String product_material_notes,
	                                            double product_material_conversion_factor, Integer expected_lead_time,
	                                            String material_expected_schedule_date, String product_fg_bom_no, Integer product_fg_bom_version,
	                                            String product_costing_no, double item_material_cost, double item_process_cost, double item_labour_cost,
	                                            double item_administration_percent, double item_administration_cost, double item_profit_percent,
	                                            double item_profit_cost, double item_other_cost, String item_other_cost_remark,
	                                            double item_discount_percent, double item_discount_cost, double item_taxable_cost, double item_cgst_percent,
	                                            double item_cgst_cost, double item_sgst_percent, double item_sgst_cost, double item_igst_percent,
	                                            double item_igst_cost, double item_total_cost, double material_std_rate, String quotation_item_status,
	                                            String material_item_approval_remark, String item_dispatch_note_nos, String item_dispatch_challan_nos,
	                                            String item_invoice_nos, String item_terms_conditions, String item_remark, boolean is_active,
	                                            boolean is_delete, String created_by, Date created_on, String modified_by, Date modified_on,
	                                            String deleted_by, Date deleted_on) {
		super();
		this.quotation_details_transaction_id = quotation_details_transaction_id;
		this.quotation_master_transaction_id = quotation_master_transaction_id;
		this.company_id = company_id;
		this.company_branch_id = company_branch_id;
		this.financial_year = financial_year;
		this.quotation_type_id = quotation_type_id;
		this.quotation_type = quotation_type;
		this.quotation_no = quotation_no;
		this.quotation_date = quotation_date;
		this.quotation_version = quotation_version;
		this.department_id = department_id;
		this.enquiry_no = enquiry_no;
		this.enquiry_date = enquiry_date;
		this.enquiry_version = enquiry_version;
		this.sr_no = sr_no;
		this.product_material_id = product_material_id;
		this.hsn_sac_id = hsn_sac_id;
		this.hsn_sac_percent = hsn_sac_percent;
		this.product_material_tech_spect = product_material_tech_spect;
		this.product_material_unit_id = product_material_unit_id;
		this.product_material_packing_id = product_material_packing_id;
		this.product_material_quotation_quantity = product_material_quotation_quantity;
		this.product_material_quotation_weight = product_material_quotation_weight;
		this.product_material_moq_quantity = product_material_moq_quantity;
		this.product_material_notes = product_material_notes;
		this.product_material_conversion_factor = product_material_conversion_factor;
		this.expected_lead_time = expected_lead_time;
		this.material_expected_schedule_date = material_expected_schedule_date;
		this.product_fg_bom_no = product_fg_bom_no;
		this.product_fg_bom_version = product_fg_bom_version;
		this.product_costing_no = product_costing_no;
		this.item_material_cost = item_material_cost;
		this.item_process_cost = item_process_cost;
		this.item_labour_cost = item_labour_cost;
		this.item_administration_percent = item_administration_percent;
		this.item_administration_cost = item_administration_cost;
		this.item_profit_percent = item_profit_percent;
		this.item_profit_cost = item_profit_cost;
		this.item_other_cost = item_other_cost;
		this.item_other_cost_remark = item_other_cost_remark;
		this.item_discount_percent = item_discount_percent;
		this.item_discount_cost = item_discount_cost;
		this.item_taxable_cost = item_taxable_cost;
		this.item_cgst_percent = item_cgst_percent;
		this.item_cgst_cost = item_cgst_cost;
		this.item_sgst_percent = item_sgst_percent;
		this.item_sgst_cost = item_sgst_cost;
		this.item_igst_percent = item_igst_percent;
		this.item_igst_cost = item_igst_cost;
		this.item_total_cost = item_total_cost;
		this.material_std_rate = material_std_rate;
		this.quotation_item_status = quotation_item_status;
		this.material_item_approval_remark = material_item_approval_remark;
		this.item_dispatch_note_nos = item_dispatch_note_nos;
		this.item_dispatch_challan_nos = item_dispatch_challan_nos;
		this.item_invoice_nos = item_invoice_nos;
		this.item_terms_conditions = item_terms_conditions;
		this.item_remark = item_remark;
		this.is_active = is_active;
		this.is_delete = is_delete;
		this.created_by = created_by;
		this.created_on = created_on;
		this.modified_by = modified_by;
		this.modified_on = modified_on;
		this.deleted_by = deleted_by;
		this.deleted_on = deleted_on;
	}

	public CMtSalesQuotationDetailsTradingModel() {
		super();

	}
}
