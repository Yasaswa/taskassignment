package com.erp.PtGoodsReceiptDetails.Model;

import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
@Immutable
@Subselect("select * from ptv_goods_receipts_notes_tax_summary")
public class CPtGoodsReceiptsNotesTaxSummaryViewModel {

	@Id
	private int goods_receipts_notes_tax_summary_transaction_id;
	private String company_name;
	private String company_branch_name;
	private String financial_year;
	private String goods_receipt_no;
	private String goods_receipt_date;
	private Integer goods_receipt_version;
	private String purchase_order_type;
	private String purchase_order_no;
	private String purchase_order_date;
	private Integer purchase_order_version;
	private String state_name;
	private String city_name;
	private String hsn_sac_type;
	private String hsn_sac_code;
	private String hsn_sac_description;
	private String hsn_sac_rate;
	private double summary_taxable_amount;
	private double summary_cgst_percent;
	private double summary_cgst_total;
	private double summary_sgst_percent;
	private double summary_sgst_total;
	private double summary_igst_percent;
	private double summary_igst_total;
	private double summary_total_amount;
	private String tax_upload__status;
	private String tax_upload_date;
	private String supplier_type;
	private String supplier_code;
	private String supplier_name;
	private String supplier_short_name;
	private String supplier_sector;
	private String nature_of_business;
	private String supplier_rating;
	private String supplier_gl_codes;
	private String supplier_history;
	private String username;
	private String password;
	private String remark;
	private boolean is_active;
	private boolean is_delete;
	private String created_by;
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;
	private Integer company_branch_id;
	private Integer company_id;
	private Integer purchase_order_type_id;
	private Integer purchase_order_master_transaction_id;
	private Integer supplier_id;
	private Integer supplier_state_id;
	private Integer supplier_city_id;
	private String supplier_contacts_ids;
	private Integer expected_branch_id;
	private Integer expected_branch_state_id;
	private Integer expected_branch_city_id;
	private Integer hsn_code_id;
	private String tax_upload_id;

	public int getGoods_receipts_notes_tax_summary_transaction_id() {
		return goods_receipts_notes_tax_summary_transaction_id;
	}

	public void setGoods_receipts_notes_tax_summary_transaction_id(
			int goods_receipts_notes_tax_summary_transaction_id) {
		this.goods_receipts_notes_tax_summary_transaction_id = goods_receipts_notes_tax_summary_transaction_id;
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

	public String getGoods_receipt_no() {
		return goods_receipt_no;
	}

	public void setGoods_receipt_no(String goods_receipt_no) {
		this.goods_receipt_no = goods_receipt_no;
	}

	public String getGoods_receipt_date() {
		return goods_receipt_date;
	}

	public void setGoods_receipt_date(String goods_receipt_date) {
		this.goods_receipt_date = goods_receipt_date;
	}

	public Integer getGoods_receipt_version() {
		return goods_receipt_version;
	}

	public void setGoods_receipt_version(Integer goods_receipt_version) {
		this.goods_receipt_version = goods_receipt_version;
	}

	public String getPurchase_order_type() {
		return purchase_order_type;
	}

	public void setPurchase_order_type(String purchase_order_type) {
		this.purchase_order_type = purchase_order_type;
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

	public String getState_name() {
		return state_name;
	}

	public void setState_name(String state_name) {
		this.state_name = state_name;
	}

	public String getCity_name() {
		return city_name;
	}

	public void setCity_name(String city_name) {
		this.city_name = city_name;
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

	public String getTax_upload__status() {
		return tax_upload__status;
	}

	public void setTax_upload__status(String tax_upload__status) {
		this.tax_upload__status = tax_upload__status;
	}

	public String getTax_upload_date() {
		return tax_upload_date;
	}

	public void setTax_upload_date(String tax_upload_date) {
		this.tax_upload_date = tax_upload_date;
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

	public String getSupplier_sector() {
		return supplier_sector;
	}

	public void setSupplier_sector(String supplier_sector) {
		this.supplier_sector = supplier_sector;
	}

	public String getNature_of_business() {
		return nature_of_business;
	}

	public void setNature_of_business(String nature_of_business) {
		this.nature_of_business = nature_of_business;
	}

	public String getSupplier_rating() {
		return supplier_rating;
	}

	public void setSupplier_rating(String supplier_rating) {
		this.supplier_rating = supplier_rating;
	}

	public String getSupplier_gl_codes() {
		return supplier_gl_codes;
	}

	public void setSupplier_gl_codes(String supplier_gl_codes) {
		this.supplier_gl_codes = supplier_gl_codes;
	}

	public String getSupplier_history() {
		return supplier_history;
	}

	public void setSupplier_history(String supplier_history) {
		this.supplier_history = supplier_history;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public Integer getCompany_branch_id() {
		return company_branch_id;
	}

	public void setCompany_branch_id(Integer company_branch_id) {
		this.company_branch_id = company_branch_id;
	}

	public Integer getCompany_id() {
		return company_id;
	}

	public void setCompany_id(Integer company_id) {
		this.company_id = company_id;
	}

	public Integer getPurchase_order_type_id() {
		return purchase_order_type_id;
	}

	public void setPurchase_order_type_id(Integer purchase_order_type_id) {
		this.purchase_order_type_id = purchase_order_type_id;
	}

	public Integer getPurchase_order_master_transaction_id() {
		return purchase_order_master_transaction_id;
	}

	public void setPurchase_order_master_transaction_id(Integer purchase_order_master_transaction_id) {
		this.purchase_order_master_transaction_id = purchase_order_master_transaction_id;
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

	public String getTax_upload_id() {
		return tax_upload_id;
	}

	public void setTax_upload_id(String tax_upload_id) {
		this.tax_upload_id = tax_upload_id;
	}

	public CPtGoodsReceiptsNotesTaxSummaryViewModel(int goods_receipts_notes_tax_summary_transaction_id,
	                                                String company_name, String company_branch_name, String financial_year, String goods_receipt_no,
	                                                String goods_receipt_date, Integer goods_receipt_version, String purchase_order_type,
	                                                String purchase_order_no, String purchase_order_date, Integer purchase_order_version, String state_name,
	                                                String city_name, String hsn_sac_type, String hsn_sac_code, String hsn_sac_description, String hsn_sac_rate,
	                                                double summary_taxable_amount, double summary_cgst_percent, double summary_cgst_total,
	                                                double summary_sgst_percent, double summary_sgst_total, double summary_igst_percent,
	                                                double summary_igst_total, double summary_total_amount, String tax_upload__status, String tax_upload_date,
	                                                String supplier_type, String supplier_code, String supplier_name, String supplier_short_name,
	                                                String supplier_sector, String nature_of_business, String supplier_rating, String supplier_gl_codes,
	                                                String supplier_history, String username, String password, String remark, boolean is_active,
	                                                boolean is_delete, String created_by, Date created_on, String modified_by, Date modified_on,
	                                                String deleted_by, Date deleted_on, Integer company_branch_id, Integer company_id,
	                                                Integer purchase_order_type_id, Integer purchase_order_master_transaction_id, Integer supplier_id,
	                                                Integer supplier_state_id, Integer supplier_city_id, String supplier_contacts_ids,
	                                                Integer expected_branch_id, Integer expected_branch_state_id, Integer expected_branch_city_id,
	                                                Integer hsn_code_id, String tax_upload_id) {
		super();
		this.goods_receipts_notes_tax_summary_transaction_id = goods_receipts_notes_tax_summary_transaction_id;
		this.company_name = company_name;
		this.company_branch_name = company_branch_name;
		this.financial_year = financial_year;
		this.goods_receipt_no = goods_receipt_no;
		this.goods_receipt_date = goods_receipt_date;
		this.goods_receipt_version = goods_receipt_version;
		this.purchase_order_type = purchase_order_type;
		this.purchase_order_no = purchase_order_no;
		this.purchase_order_date = purchase_order_date;
		this.purchase_order_version = purchase_order_version;
		this.state_name = state_name;
		this.city_name = city_name;
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
		this.tax_upload__status = tax_upload__status;
		this.tax_upload_date = tax_upload_date;
		this.supplier_type = supplier_type;
		this.supplier_code = supplier_code;
		this.supplier_name = supplier_name;
		this.supplier_short_name = supplier_short_name;
		this.supplier_sector = supplier_sector;
		this.nature_of_business = nature_of_business;
		this.supplier_rating = supplier_rating;
		this.supplier_gl_codes = supplier_gl_codes;
		this.supplier_history = supplier_history;
		this.username = username;
		this.password = password;
		this.remark = remark;
		this.is_active = is_active;
		this.is_delete = is_delete;
		this.created_by = created_by;
		this.created_on = created_on;
		this.modified_by = modified_by;
		this.modified_on = modified_on;
		this.deleted_by = deleted_by;
		this.deleted_on = deleted_on;
		this.company_branch_id = company_branch_id;
		this.company_id = company_id;
		this.purchase_order_type_id = purchase_order_type_id;
		this.purchase_order_master_transaction_id = purchase_order_master_transaction_id;
		this.supplier_id = supplier_id;
		this.supplier_state_id = supplier_state_id;
		this.supplier_city_id = supplier_city_id;
		this.supplier_contacts_ids = supplier_contacts_ids;
		this.expected_branch_id = expected_branch_id;
		this.expected_branch_state_id = expected_branch_state_id;
		this.expected_branch_city_id = expected_branch_city_id;
		this.hsn_code_id = hsn_code_id;
		this.tax_upload_id = tax_upload_id;
	}

	public CPtGoodsReceiptsNotesTaxSummaryViewModel() {
		super();
		// TODO Auto-generated constructor stub
	}

}
