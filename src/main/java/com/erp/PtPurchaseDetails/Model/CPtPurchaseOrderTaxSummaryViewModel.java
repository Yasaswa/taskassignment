package com.erp.PtPurchaseDetails.Model;

import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
@Immutable
@Subselect("select * from ptv_purchase_order_tax_summary")
public class CPtPurchaseOrderTaxSummaryViewModel {

	@Id
	private int purchase_order_tax_summary_transaction_id;
	private String company_name;
	private String company_branch_name;
	private String financial_year;
	private Integer purchase_order_type_id;
	private String purchase_order_type;
	private Integer purchase_order_master_transaction_id;
	private String supplier_type;
	private String product_material_id;
	private String supplier_code;
	private String supplier_name;
	private String supplier_short_name;
	private String supp_branch_address1;
	private String supp_branch_pincode;
	private String supplier_city_name;
	private String supplier_district_name;
	private String supplier_state_name;
	private String supplier_country_name;
	private String supp_branch_phone_no;
	private String supp_branch_EmailId;
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
	private String purchase_order_no;
	private String purchase_order_date;
	private Integer purchase_order_version;
	private String hsn_sac_type;
	private String hsn_sac_code;
	private String hsn_sac_description;
	private double hsn_sac_rate;
	private double summary_taxable_amount;
	private double summary_cgst_percent;
	private double summary_cgst_total;
	private double summary_sgst_percent;
	private double summary_sgst_total;
	private double summary_igst_percent;
	private double summary_igst_total;
	private double summary_total_amount;
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
	private Integer supplier_id;
	private Integer supplier_state_id;
	private Integer supplier_city_id;
	private String supplier_contacts_ids;
	private Integer expected_branch_id;
	private Integer expected_branch_state_id;
	private Integer expected_branch_city_id;
	private Integer hsn_code_id;

	public CPtPurchaseOrderTaxSummaryViewModel(int purchase_order_tax_summary_transaction_id, String company_name,
	                                           String company_branch_name, String financial_year, Integer purchase_order_type_id,
	                                           String purchase_order_type, Integer purchase_order_master_transaction_id, String supplier_type,
	                                           String supplier_code, String supplier_name, String supplier_short_name, String supp_branch_address1,
	                                           String supp_branch_pincode, String supplier_city_name, String supplier_district_name,
	                                           String supplier_state_name, String supplier_country_name, String supp_branch_phone_no,
	                                           String supp_branch_EmailId, String expected_branch_name, String expected_branch_short_name,
	                                           String expected_branch_address1, String expected_branch_pincode, String expected_branch_city_name,
	                                           String expected_branch_district_name, String expected_branch_state_name,
	                                           String expected_branch_country_name, String expected_branch_phone_no, String expected_branch_EmailId,
	                                           String purchase_order_no, String purchase_order_date, Integer purchase_order_version, String hsn_sac_type,
	                                           String hsn_sac_code, String hsn_sac_description, double hsn_sac_rate, double summary_taxable_amount,
	                                           double summary_cgst_percent, double summary_cgst_total, double summary_sgst_percent,
	                                           double summary_sgst_total, double summary_igst_percent, double summary_igst_total,
	                                           double summary_total_amount, String remark, boolean is_active, boolean is_delete, String created_by,
	                                           Date created_on, String modified_by, Date modified_on, String deleted_by, Date deleted_on,
	                                           Integer company_id, Integer company_branch_id, Integer supplier_id, Integer supplier_state_id,
	                                           Integer supplier_city_id, String supplier_contacts_ids, Integer expected_branch_id,
	                                           Integer expected_branch_state_id, Integer expected_branch_city_id, Integer hsn_code_id) {
		super();
		this.purchase_order_tax_summary_transaction_id = purchase_order_tax_summary_transaction_id;
		this.company_name = company_name;
		this.company_branch_name = company_branch_name;
		this.financial_year = financial_year;
		this.purchase_order_type_id = purchase_order_type_id;
		this.purchase_order_type = purchase_order_type;
		this.purchase_order_master_transaction_id = purchase_order_master_transaction_id;
		this.supplier_type = supplier_type;
		this.supplier_code = supplier_code;
		this.supplier_name = supplier_name;
		this.supplier_short_name = supplier_short_name;
		this.supp_branch_address1 = supp_branch_address1;
		this.supp_branch_pincode = supp_branch_pincode;
		this.supplier_city_name = supplier_city_name;
		this.supplier_district_name = supplier_district_name;
		this.supplier_state_name = supplier_state_name;
		this.supplier_country_name = supplier_country_name;
		this.supp_branch_phone_no = supp_branch_phone_no;
		this.supp_branch_EmailId = supp_branch_EmailId;
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
		this.purchase_order_no = purchase_order_no;
		this.purchase_order_date = purchase_order_date;
		this.purchase_order_version = purchase_order_version;
		this.hsn_sac_type = hsn_sac_type;
		this.hsn_sac_code = hsn_sac_code;
		this.hsn_sac_description = hsn_sac_description;
		this.hsn_sac_rate = hsn_sac_rate;
		this.summary_taxable_amount = summary_taxable_amount;
		this.summary_cgst_percent = summary_cgst_percent;
		this.summary_cgst_total = summary_cgst_total;
		this.summary_sgst_percent = summary_sgst_percent;
		this.summary_sgst_total = summary_sgst_total;
		this.summary_igst_percent = summary_igst_percent;
		this.summary_igst_total = summary_igst_total;
		this.summary_total_amount = summary_total_amount;
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
		this.supplier_id = supplier_id;
		this.supplier_state_id = supplier_state_id;
		this.supplier_city_id = supplier_city_id;
		this.supplier_contacts_ids = supplier_contacts_ids;
		this.expected_branch_id = expected_branch_id;
		this.expected_branch_state_id = expected_branch_state_id;
		this.expected_branch_city_id = expected_branch_city_id;
		this.hsn_code_id = hsn_code_id;
	}

	public int getPurchase_order_tax_summary_transaction_id() {
		return purchase_order_tax_summary_transaction_id;
	}

	public void setPurchase_order_tax_summary_transaction_id(int purchase_order_tax_summary_transaction_id) {
		this.purchase_order_tax_summary_transaction_id = purchase_order_tax_summary_transaction_id;
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

	public Integer getPurchase_order_type_id() {
		return purchase_order_type_id;
	}

	public void setPurchase_order_type_id(Integer purchase_order_type_id) {
		this.purchase_order_type_id = purchase_order_type_id;
	}

	public String getPurchase_order_type() {
		return purchase_order_type;
	}

	public void setPurchase_order_type(String purchase_order_type) {
		this.purchase_order_type = purchase_order_type;
	}

	public Integer getPurchase_order_master_transaction_id() {
		return purchase_order_master_transaction_id;
	}

	public void setPurchase_order_master_transaction_id(Integer purchase_order_master_transaction_id) {
		this.purchase_order_master_transaction_id = purchase_order_master_transaction_id;
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

	public String getSupplier_city_name() {
		return supplier_city_name;
	}

	public void setSupplier_city_name(String supplier_city_name) {
		this.supplier_city_name = supplier_city_name;
	}

	public String getSupplier_district_name() {
		return supplier_district_name;
	}

	public void setSupplier_district_name(String supplier_district_name) {
		this.supplier_district_name = supplier_district_name;
	}

	public String getSupplier_state_name() {
		return supplier_state_name;
	}

	public void setSupplier_state_name(String supplier_state_name) {
		this.supplier_state_name = supplier_state_name;
	}

	public String getSupplier_country_name() {
		return supplier_country_name;
	}

	public void setSupplier_country_name(String supplier_country_name) {
		this.supplier_country_name = supplier_country_name;
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

	public Integer getPurchase_order_version() {
		return purchase_order_version;
	}

	public void setPurchase_order_version(Integer purchase_order_version) {
		this.purchase_order_version = purchase_order_version;
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

	public double getHsn_sac_rate() {
		return hsn_sac_rate;
	}

	public void setHsn_sac_rate(double hsn_sac_rate) {
		this.hsn_sac_rate = hsn_sac_rate;
	}

	public double getSummary_taxable_amount() {
		return summary_taxable_amount;
	}

	public void setSummary_taxable_amount(double summary_taxable_amount) {
		this.summary_taxable_amount = summary_taxable_amount;
	}

	public double getSummary_cgst_percent() {
		return summary_cgst_percent;
	}

	public void setSummary_cgst_percent(double summary_cgst_percent) {
		this.summary_cgst_percent = summary_cgst_percent;
	}

	public double getSummary_cgst_total() {
		return summary_cgst_total;
	}

	public void setSummary_cgst_total(double summary_cgst_total) {
		this.summary_cgst_total = summary_cgst_total;
	}

	public double getSummary_sgst_percent() {
		return summary_sgst_percent;
	}

	public void setSummary_sgst_percent(double summary_sgst_percent) {
		this.summary_sgst_percent = summary_sgst_percent;
	}

	public double getSummary_sgst_total() {
		return summary_sgst_total;
	}

	public void setSummary_sgst_total(double summary_sgst_total) {
		this.summary_sgst_total = summary_sgst_total;
	}

	public double getSummary_igst_percent() {
		return summary_igst_percent;
	}

	public void setSummary_igst_percent(double summary_igst_percent) {
		this.summary_igst_percent = summary_igst_percent;
	}

	public double getSummary_igst_total() {
		return summary_igst_total;
	}

	public void setSummary_igst_total(double summary_igst_total) {
		this.summary_igst_total = summary_igst_total;
	}

	public double getSummary_total_amount() {
		return summary_total_amount;
	}

	public void setSummary_total_amount(double summary_total_amount) {
		this.summary_total_amount = summary_total_amount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public Integer getSupplier_id() {
		return supplier_id;
	}

	public void setSupplier_id(Integer supplier_id) {
		this.supplier_id = supplier_id;
	}

	public Integer getSupplier_state_id() {
		return supplier_state_id;
	}

	public void setSupplier_state_id(Integer supplier_state_id) {
		this.supplier_state_id = supplier_state_id;
	}

	public Integer getSupplier_city_id() {
		return supplier_city_id;
	}

	public void setSupplier_city_id(Integer supplier_city_id) {
		this.supplier_city_id = supplier_city_id;
	}

	public String getSupplier_contacts_ids() {
		return supplier_contacts_ids;
	}

	public void setSupplier_contacts_ids(String supplier_contacts_ids) {
		this.supplier_contacts_ids = supplier_contacts_ids;
	}

	public Integer getExpected_branch_id() {
		return expected_branch_id;
	}

	public void setExpected_branch_id(Integer expected_branch_id) {
		this.expected_branch_id = expected_branch_id;
	}

	public Integer getExpected_branch_state_id() {
		return expected_branch_state_id;
	}

	public void setExpected_branch_state_id(Integer expected_branch_state_id) {
		this.expected_branch_state_id = expected_branch_state_id;
	}

	public Integer getExpected_branch_city_id() {
		return expected_branch_city_id;
	}

	public void setExpected_branch_city_id(Integer expected_branch_city_id) {
		this.expected_branch_city_id = expected_branch_city_id;
	}

	public Integer getHsn_code_id() {
		return hsn_code_id;
	}

	public void setHsn_code_id(Integer hsn_code_id) {
		this.hsn_code_id = hsn_code_id;
	}

	public CPtPurchaseOrderTaxSummaryViewModel() {
		super();

	}
}
