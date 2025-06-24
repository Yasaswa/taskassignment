package com.erp.PtGoodsReceiptDetails.Model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "pt_goods_receipts_notes_tax_summary")
public class CPtGoodsReceiptsNotesTaxSummaryModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "goods_receipts_notes_tax_summary_transaction_id")
	private int goods_receipts_notes_tax_summary_transaction_id;
	private Integer goods_receipt_master_transaction_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String financial_year;
	private String goods_receipt_no;
	private String goods_receipt_date;
	private Integer goods_receipt_version;
	private Integer purchase_order_type_id;
	private String purchase_order_type;
	private Integer purchase_order_master_transaction_id;
	private Integer supplier_id;
	private Integer supplier_state_id;
	private Integer supplier_city_id;
	private String supplier_contacts_ids;
	private Integer expected_branch_id;
	private Integer expected_branch_state_id;
	private Integer expected_branch_city_id;
	private String purchase_order_no;
	private String purchase_order_date;
	private Integer purchase_order_version;
	private Integer hsn_code_id;
	private double summary_taxable_amount;
	private double summary_cgst_percent;
	private double summary_cgst_total;
	private double summary_sgst_percent;
	private double summary_sgst_total;
	private double summary_igst_percent;
	private double summary_igst_total;
	private double summary_total_amount;
	private String tax_upload__status;
	private String tax_upload_id;
	private String tax_upload_date;
	private String remark;
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

	public int getGoods_receipts_notes_tax_summary_transaction_id() {
		return goods_receipts_notes_tax_summary_transaction_id;
	}

	public void setGoods_receipts_notes_tax_summary_transaction_id(
			int goods_receipts_notes_tax_summary_transaction_id) {
		this.goods_receipts_notes_tax_summary_transaction_id = goods_receipts_notes_tax_summary_transaction_id;
	}

	public Integer getGoods_receipt_master_transaction_id() {
		return goods_receipt_master_transaction_id;
	}

	public void setGoods_receipt_master_transaction_id(Integer goods_receipt_master_transaction_id) {
		this.goods_receipt_master_transaction_id = goods_receipt_master_transaction_id;
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
		if (purchase_order_date == null || purchase_order_date.isEmpty()) {
			this.purchase_order_date = null;
		} else {
			this.purchase_order_date = purchase_order_date;
		}
	}

	public Integer getPurchase_order_version() {
		return purchase_order_version;
	}

	public void setPurchase_order_version(Integer purchase_order_version) {
		this.purchase_order_version = purchase_order_version;
	}

	public Integer getHsn_code_id() {
		return hsn_code_id;
	}

	public void setHsn_code_id(Integer hsn_code_id) {
		this.hsn_code_id = hsn_code_id;
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

	public String getTax_upload_id() {
		return tax_upload_id;
	}

	public void setTax_upload_id(String tax_upload_id) {
		this.tax_upload_id = tax_upload_id;
	}

	public String getTax_upload_date() {
		return tax_upload_date;
	}

	public void setTax_upload_date(String tax_upload_date) {
		if (tax_upload_date == null || tax_upload_date.isEmpty()) {
			this.tax_upload_date = null;
		} else {
			this.tax_upload_date = tax_upload_date;
		}
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

	public CPtGoodsReceiptsNotesTaxSummaryModel(int goods_receipts_notes_tax_summary_transaction_id,
	                                            Integer goods_receipt_master_transaction_id, Integer company_id, Integer company_branch_id,
	                                            String financial_year, String goods_receipt_no, String goods_receipt_date, Integer goods_receipt_version,
	                                            Integer purchase_order_type_id, String purchase_order_type, Integer purchase_order_master_transaction_id,
	                                            Integer supplier_id, Integer supplier_state_id, Integer supplier_city_id, String supplier_contacts_ids,
	                                            Integer expected_branch_id, Integer expected_branch_state_id, Integer expected_branch_city_id,
	                                            String purchase_order_no, String purchase_order_date, Integer purchase_order_version, Integer hsn_code_id,
	                                            double summary_taxable_amount, double summary_cgst_percent, double summary_cgst_total,
	                                            double summary_sgst_percent, double summary_sgst_total, double summary_igst_percent,
	                                            double summary_igst_total, double summary_total_amount, String tax_upload__status, String tax_upload_id,
	                                            String tax_upload_date, String remark, boolean is_active, boolean is_delete, String created_by,
	                                            Date created_on, String modified_by, Date modified_on, String deleted_by, Date deleted_on) {
		super();
		this.goods_receipts_notes_tax_summary_transaction_id = goods_receipts_notes_tax_summary_transaction_id;
		this.goods_receipt_master_transaction_id = goods_receipt_master_transaction_id;
		this.company_id = company_id;
		this.company_branch_id = company_branch_id;
		this.financial_year = financial_year;
		this.goods_receipt_no = goods_receipt_no;
		this.goods_receipt_date = goods_receipt_date;
		this.goods_receipt_version = goods_receipt_version;
		this.purchase_order_type_id = purchase_order_type_id;
		this.purchase_order_type = purchase_order_type;
		this.purchase_order_master_transaction_id = purchase_order_master_transaction_id;
		this.supplier_id = supplier_id;
		this.supplier_state_id = supplier_state_id;
		this.supplier_city_id = supplier_city_id;
		this.supplier_contacts_ids = supplier_contacts_ids;
		this.expected_branch_id = expected_branch_id;
		this.expected_branch_state_id = expected_branch_state_id;
		this.expected_branch_city_id = expected_branch_city_id;
		this.purchase_order_no = purchase_order_no;
		this.purchase_order_date = purchase_order_date;
		this.purchase_order_version = purchase_order_version;
		this.hsn_code_id = hsn_code_id;
		this.summary_taxable_amount = summary_taxable_amount;
		this.summary_cgst_percent = summary_cgst_percent;
		this.summary_cgst_total = summary_cgst_total;
		this.summary_sgst_percent = summary_sgst_percent;
		this.summary_sgst_total = summary_sgst_total;
		this.summary_igst_percent = summary_igst_percent;
		this.summary_igst_total = summary_igst_total;
		this.summary_total_amount = summary_total_amount;
		this.tax_upload__status = tax_upload__status;
		this.tax_upload_id = tax_upload_id;
		this.tax_upload_date = tax_upload_date;
		this.remark = remark;
		this.is_active = is_active;
		this.is_delete = is_delete;
		this.created_by = created_by;
		this.created_on = created_on;
		this.modified_by = modified_by;
		this.modified_on = modified_on;
		this.deleted_by = deleted_by;
		this.deleted_on = deleted_on;
	}

	public CPtGoodsReceiptsNotesTaxSummaryModel() {
		super();
	}
}
