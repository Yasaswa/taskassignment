package com.erp.PtPurchaseDetails.Model;

import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@Immutable
@Subselect("select * from ptv_purchase_order_details_rpt")
public class CPtPurchaseOrderDetailsRptModel_Not_Used {

	@Id
	private String purchase_order_details_transaction_id;
	private String sr_no;
	private String company_name;
	private String company_branch_name;
	private String financial_year;
	private String purchase_order_no;
	private String purchase_order_version;
	private String purchase_order_date;
	private String purchase_order_type;
	private String product_type_name;
	private String product_type_short_name;
	private String product_type_group;
	private String indent_no;
	private String indent_date;
	private String indent_version;
	private String product_material_code;
	private String product_material_name;
	private String product_material_print_name;
	private String product_material_tech_spect;
	private String product_material_stock_unit_name;
	private String lead_time;
	private String product_packing_name;
	private String hsn_sac_type;
	private String hsn_sac_code;
	private String hsn_sac_description;
	private String hsn_sac_rate;
	private String product_material_po_quantity;
	private String product_material_conversion_factor;
	private String product_material_po_weight;
	private String product_material_po_approved_quantity;
	private String product_material_po_approved_weight;
	private String product_material_po_rejected_quantity;
	private String product_material_po_rejected_weight;
	private String pree_closed_quantity;
	private String pree_closed_weight;
	private String purchase_return_quantity;
	private String purchase_return_weight;
	private String material_rate;
	private String material_basic_amount;
	private String material_discount_percent;
	private String material_discount_amount;
	private String material_taxable_amount;
	private String material_cgst_percent;
	private String material_cgst_total;
	private String material_sgst_percent;
	private String material_sgst_total;
	private String material_igst_percent;
	private String material_igst_total;
	private String material_total_amount;
	private String material_schedule_date;
	private String purchase_order_item_status;
	private String purchase_order_mail_sent_status;
	private String purchase_order_mail_sent_status_desc;
	private String pending_quantity;
	private String pending_weight;
	private String excess_quantity;
	private String excess_weight;
	private String production_issue_quantity;
	private String production_issue_weight;
	private String production_issue_return_quantity;
	private String production_issue_return_weight;
	private String production_issue_rejection_quantity;
	private String production_issue_rejection_weight;
	private String assembly_production_issue_quantity;
	private String assembly_production_issue_weight;
	private String sales_quantity;
	private String sales_weight;
	private String sales_return_quantity;
	private String sales_return_weight;
	private String sales_rejection_quantity;
	private String sales_rejection_weight;
	private String transfer_issue_quantity;
	private String transfer_issue_weight;
	private String transfer_receipt_quantity;
	private String transfer_receipt_weight;
	private String outsources_out_quantity;
	private String outsources_out_weight;
	private String outsources_in_quantity;
	private String outsources_in_weight;
	private String outsources_rejection_quantity;
	private String outsources_rejection_weight;
	private String loan_receipt_quantity;
	private String loan_receipt_weight;
	private String loan_issue_quantity;
	private String loan_issue_weight;
	private String cancel_quantity;
	private String cancel_weight;
	private String difference_quantity;
	private String difference_weight;
	private String material_po_approval_remark;
	private String department_name;
	private String approved_by_name;
	private String approved_date;
	private String so_sr_no;
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
	private String purchase_order_master_transaction_id;
	private String product_material_id;
	private String product_material_unit_id;
	private String product_material_packing_id;
	private String product_material_hsn_code_id;
	private String department_id;
	private String indented_by_id;
	private String approved_by_id;

	public String getPurchase_order_details_transaction_id() {
		return purchase_order_details_transaction_id;
	}

	public void setPurchase_order_details_transaction_id(String purchase_order_details_transaction_id) {
		this.purchase_order_details_transaction_id = purchase_order_details_transaction_id;
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

	public String getIndent_no() {
		return indent_no;
	}

	public void setIndent_no(String indent_no) {
		this.indent_no = indent_no;
	}

	public String getIndent_date() {
		return indent_date;
	}

	public void setIndent_date(String indent_date) {
		this.indent_date = indent_date;
	}

	public String getIndent_version() {
		return indent_version;
	}

	public void setIndent_version(String indent_version) {
		this.indent_version = indent_version;
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

	public String getProduct_material_print_name() {
		return product_material_print_name;
	}

	public void setProduct_material_print_name(String product_material_print_name) {
		this.product_material_print_name = product_material_print_name;
	}

	public String getProduct_material_tech_spect() {
		return product_material_tech_spect;
	}

	public void setProduct_material_tech_spect(String product_material_tech_spect) {
		this.product_material_tech_spect = product_material_tech_spect;
	}

	public String getLead_time() {
		return lead_time;
	}

	public void setLead_time(String lead_time) {
		this.lead_time = lead_time;
	}

	public String getSr_no() {
		return sr_no;
	}

	public void setSr_no(String sr_no) {
		this.sr_no = sr_no;
	}

	public String getProduct_material_stock_unit_name() {
		return product_material_stock_unit_name;
	}

	public void setProduct_material_stock_unit_name(String product_material_stock_unit_name) {
		this.product_material_stock_unit_name = product_material_stock_unit_name;
	}

	public String getProduct_packing_name() {
		return product_packing_name;
	}

	public void setProduct_packing_name(String product_packing_name) {
		this.product_packing_name = product_packing_name;
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

	public String getProduct_material_po_quantity() {
		return product_material_po_quantity;
	}

	public void setProduct_material_po_quantity(String product_material_po_quantity) {
		this.product_material_po_quantity = product_material_po_quantity;
	}

	public String getProduct_material_conversion_factor() {
		return product_material_conversion_factor;
	}

	public void setProduct_material_conversion_factor(String product_material_conversion_factor) {
		this.product_material_conversion_factor = product_material_conversion_factor;
	}

	public String getProduct_material_po_weight() {
		return product_material_po_weight;
	}

	public void setProduct_material_po_weight(String product_material_po_weight) {
		this.product_material_po_weight = product_material_po_weight;
	}

	public String getProduct_material_po_approved_quantity() {
		return product_material_po_approved_quantity;
	}

	public void setProduct_material_po_approved_quantity(String product_material_po_approved_quantity) {
		this.product_material_po_approved_quantity = product_material_po_approved_quantity;
	}

	public String getProduct_material_po_approved_weight() {
		return product_material_po_approved_weight;
	}

	public void setProduct_material_po_approved_weight(String product_material_po_approved_weight) {
		this.product_material_po_approved_weight = product_material_po_approved_weight;
	}

	public String getProduct_material_po_rejected_quantity() {
		return product_material_po_rejected_quantity;
	}

	public void setProduct_material_po_rejected_quantity(String product_material_po_rejected_quantity) {
		this.product_material_po_rejected_quantity = product_material_po_rejected_quantity;
	}

	public String getProduct_material_po_rejected_weight() {
		return product_material_po_rejected_weight;
	}

	public void setProduct_material_po_rejected_weight(String product_material_po_rejected_weight) {
		this.product_material_po_rejected_weight = product_material_po_rejected_weight;
	}

	public String getPree_closed_quantity() {
		return pree_closed_quantity;
	}

	public void setPree_closed_quantity(String pree_closed_quantity) {
		this.pree_closed_quantity = pree_closed_quantity;
	}

	public String getPree_closed_weight() {
		return pree_closed_weight;
	}

	public void setPree_closed_weight(String pree_closed_weight) {
		this.pree_closed_weight = pree_closed_weight;
	}

	public String getPurchase_return_quantity() {
		return purchase_return_quantity;
	}

	public void setPurchase_return_quantity(String purchase_return_quantity) {
		this.purchase_return_quantity = purchase_return_quantity;
	}

	public String getPurchase_return_weight() {
		return purchase_return_weight;
	}

	public void setPurchase_return_weight(String purchase_return_weight) {
		this.purchase_return_weight = purchase_return_weight;
	}

	public String getMaterial_rate() {
		return material_rate;
	}

	public void setMaterial_rate(String material_rate) {
		this.material_rate = material_rate;
	}

	public String getMaterial_basic_amount() {
		return material_basic_amount;
	}

	public void setMaterial_basic_amount(String material_basic_amount) {
		this.material_basic_amount = material_basic_amount;
	}

	public String getMaterial_discount_percent() {
		return material_discount_percent;
	}

	public void setMaterial_discount_percent(String material_discount_percent) {
		this.material_discount_percent = material_discount_percent;
	}

	public String getMaterial_discount_amount() {
		return material_discount_amount;
	}

	public void setMaterial_discount_amount(String material_discount_amount) {
		this.material_discount_amount = material_discount_amount;
	}

	public String getMaterial_taxable_amount() {
		return material_taxable_amount;
	}

	public void setMaterial_taxable_amount(String material_taxable_amount) {
		this.material_taxable_amount = material_taxable_amount;
	}

	public String getMaterial_cgst_percent() {
		return material_cgst_percent;
	}

	public void setMaterial_cgst_percent(String material_cgst_percent) {
		this.material_cgst_percent = material_cgst_percent;
	}

	public String getMaterial_cgst_total() {
		return material_cgst_total;
	}

	public void setMaterial_cgst_total(String material_cgst_total) {
		this.material_cgst_total = material_cgst_total;
	}

	public String getMaterial_sgst_percent() {
		return material_sgst_percent;
	}

	public void setMaterial_sgst_percent(String material_sgst_percent) {
		this.material_sgst_percent = material_sgst_percent;
	}

	public String getMaterial_sgst_total() {
		return material_sgst_total;
	}

	public void setMaterial_sgst_total(String material_sgst_total) {
		this.material_sgst_total = material_sgst_total;
	}

	public String getMaterial_igst_percent() {
		return material_igst_percent;
	}

	public void setMaterial_igst_percent(String material_igst_percent) {
		this.material_igst_percent = material_igst_percent;
	}

	public String getMaterial_igst_total() {
		return material_igst_total;
	}

	public void setMaterial_igst_total(String material_igst_total) {
		this.material_igst_total = material_igst_total;
	}

	public String getMaterial_total_amount() {
		return material_total_amount;
	}

	public void setMaterial_total_amount(String material_total_amount) {
		this.material_total_amount = material_total_amount;
	}

	public String getMaterial_schedule_date() {
		return material_schedule_date;
	}

	public void setMaterial_schedule_date(String material_schedule_date) {
		this.material_schedule_date = material_schedule_date;
	}

	public String getPurchase_order_item_status() {
		return purchase_order_item_status;
	}

	public void setPurchase_order_item_status(String purchase_order_item_status) {
		this.purchase_order_item_status = purchase_order_item_status;
	}

	public String getPurchase_order_mail_sent_status() {
		return purchase_order_mail_sent_status;
	}

	public void setPurchase_order_mail_sent_status(String purchase_order_mail_sent_status) {
		this.purchase_order_mail_sent_status = purchase_order_mail_sent_status;
	}

	public String getPurchase_order_mail_sent_status_desc() {
		return purchase_order_mail_sent_status_desc;
	}

	public void setPurchase_order_mail_sent_status_desc(String purchase_order_mail_sent_status_desc) {
		this.purchase_order_mail_sent_status_desc = purchase_order_mail_sent_status_desc;
	}

	public String getPending_quantity() {
		return pending_quantity;
	}

	public void setPending_quantity(String pending_quantity) {
		this.pending_quantity = pending_quantity;
	}

	public String getPending_weight() {
		return pending_weight;
	}

	public void setPending_weight(String pending_weight) {
		this.pending_weight = pending_weight;
	}

	public String getExcess_quantity() {
		return excess_quantity;
	}

	public void setExcess_quantity(String excess_quantity) {
		this.excess_quantity = excess_quantity;
	}

	public String getExcess_weight() {
		return excess_weight;
	}

	public void setExcess_weight(String excess_weight) {
		this.excess_weight = excess_weight;
	}

	public String getProduction_issue_quantity() {
		return production_issue_quantity;
	}

	public void setProduction_issue_quantity(String production_issue_quantity) {
		this.production_issue_quantity = production_issue_quantity;
	}

	public String getProduction_issue_weight() {
		return production_issue_weight;
	}

	public void setProduction_issue_weight(String production_issue_weight) {
		this.production_issue_weight = production_issue_weight;
	}

	public String getProduction_issue_return_quantity() {
		return production_issue_return_quantity;
	}

	public void setProduction_issue_return_quantity(String production_issue_return_quantity) {
		this.production_issue_return_quantity = production_issue_return_quantity;
	}

	public String getProduction_issue_return_weight() {
		return production_issue_return_weight;
	}

	public void setProduction_issue_return_weight(String production_issue_return_weight) {
		this.production_issue_return_weight = production_issue_return_weight;
	}

	public String getProduction_issue_rejection_quantity() {
		return production_issue_rejection_quantity;
	}

	public void setProduction_issue_rejection_quantity(String production_issue_rejection_quantity) {
		this.production_issue_rejection_quantity = production_issue_rejection_quantity;
	}

	public String getProduction_issue_rejection_weight() {
		return production_issue_rejection_weight;
	}

	public void setProduction_issue_rejection_weight(String production_issue_rejection_weight) {
		this.production_issue_rejection_weight = production_issue_rejection_weight;
	}

	public String getAssembly_production_issue_quantity() {
		return assembly_production_issue_quantity;
	}

	public void setAssembly_production_issue_quantity(String assembly_production_issue_quantity) {
		this.assembly_production_issue_quantity = assembly_production_issue_quantity;
	}

	public String getAssembly_production_issue_weight() {
		return assembly_production_issue_weight;
	}

	public void setAssembly_production_issue_weight(String assembly_production_issue_weight) {
		this.assembly_production_issue_weight = assembly_production_issue_weight;
	}

	public String getSales_quantity() {
		return sales_quantity;
	}

	public void setSales_quantity(String sales_quantity) {
		this.sales_quantity = sales_quantity;
	}

	public String getSales_weight() {
		return sales_weight;
	}

	public void setSales_weight(String sales_weight) {
		this.sales_weight = sales_weight;
	}

	public String getSales_return_quantity() {
		return sales_return_quantity;
	}

	public void setSales_return_quantity(String sales_return_quantity) {
		this.sales_return_quantity = sales_return_quantity;
	}

	public String getSales_return_weight() {
		return sales_return_weight;
	}

	public void setSales_return_weight(String sales_return_weight) {
		this.sales_return_weight = sales_return_weight;
	}

	public String getSales_rejection_quantity() {
		return sales_rejection_quantity;
	}

	public void setSales_rejection_quantity(String sales_rejection_quantity) {
		this.sales_rejection_quantity = sales_rejection_quantity;
	}

	public String getSales_rejection_weight() {
		return sales_rejection_weight;
	}

	public void setSales_rejection_weight(String sales_rejection_weight) {
		this.sales_rejection_weight = sales_rejection_weight;
	}

	public String getTransfer_issue_quantity() {
		return transfer_issue_quantity;
	}

	public void setTransfer_issue_quantity(String transfer_issue_quantity) {
		this.transfer_issue_quantity = transfer_issue_quantity;
	}

	public String getTransfer_issue_weight() {
		return transfer_issue_weight;
	}

	public void setTransfer_issue_weight(String transfer_issue_weight) {
		this.transfer_issue_weight = transfer_issue_weight;
	}

	public String getTransfer_receipt_quantity() {
		return transfer_receipt_quantity;
	}

	public void setTransfer_receipt_quantity(String transfer_receipt_quantity) {
		this.transfer_receipt_quantity = transfer_receipt_quantity;
	}

	public String getTransfer_receipt_weight() {
		return transfer_receipt_weight;
	}

	public void setTransfer_receipt_weight(String transfer_receipt_weight) {
		this.transfer_receipt_weight = transfer_receipt_weight;
	}

	public String getOutsources_out_quantity() {
		return outsources_out_quantity;
	}

	public void setOutsources_out_quantity(String outsources_out_quantity) {
		this.outsources_out_quantity = outsources_out_quantity;
	}

	public String getOutsources_out_weight() {
		return outsources_out_weight;
	}

	public void setOutsources_out_weight(String outsources_out_weight) {
		this.outsources_out_weight = outsources_out_weight;
	}

	public String getOutsources_in_quantity() {
		return outsources_in_quantity;
	}

	public void setOutsources_in_quantity(String outsources_in_quantity) {
		this.outsources_in_quantity = outsources_in_quantity;
	}

	public String getOutsources_in_weight() {
		return outsources_in_weight;
	}

	public void setOutsources_in_weight(String outsources_in_weight) {
		this.outsources_in_weight = outsources_in_weight;
	}

	public String getOutsources_rejection_quantity() {
		return outsources_rejection_quantity;
	}

	public void setOutsources_rejection_quantity(String outsources_rejection_quantity) {
		this.outsources_rejection_quantity = outsources_rejection_quantity;
	}

	public String getOutsources_rejection_weight() {
		return outsources_rejection_weight;
	}

	public void setOutsources_rejection_weight(String outsources_rejection_weight) {
		this.outsources_rejection_weight = outsources_rejection_weight;
	}

	public String getLoan_receipt_quantity() {
		return loan_receipt_quantity;
	}

	public void setLoan_receipt_quantity(String loan_receipt_quantity) {
		this.loan_receipt_quantity = loan_receipt_quantity;
	}

	public String getLoan_receipt_weight() {
		return loan_receipt_weight;
	}

	public void setLoan_receipt_weight(String loan_receipt_weight) {
		this.loan_receipt_weight = loan_receipt_weight;
	}

	public String getLoan_issue_quantity() {
		return loan_issue_quantity;
	}

	public void setLoan_issue_quantity(String loan_issue_quantity) {
		this.loan_issue_quantity = loan_issue_quantity;
	}

	public String getLoan_issue_weight() {
		return loan_issue_weight;
	}

	public void setLoan_issue_weight(String loan_issue_weight) {
		this.loan_issue_weight = loan_issue_weight;
	}

	public String getCancel_quantity() {
		return cancel_quantity;
	}

	public void setCancel_quantity(String cancel_quantity) {
		this.cancel_quantity = cancel_quantity;
	}

	public String getCancel_weight() {
		return cancel_weight;
	}

	public void setCancel_weight(String cancel_weight) {
		this.cancel_weight = cancel_weight;
	}

	public String getDifference_quantity() {
		return difference_quantity;
	}

	public void setDifference_quantity(String difference_quantity) {
		this.difference_quantity = difference_quantity;
	}

	public String getDifference_weight() {
		return difference_weight;
	}

	public void setDifference_weight(String difference_weight) {
		this.difference_weight = difference_weight;
	}

	public String getMaterial_po_approval_remark() {
		return material_po_approval_remark;
	}

	public void setMaterial_po_approval_remark(String material_po_approval_remark) {
		this.material_po_approval_remark = material_po_approval_remark;
	}

	public String getDepartment_name() {
		return department_name;
	}

	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}

	public String getApproved_by_name() {
		return approved_by_name;
	}

	public void setApproved_by_name(String approved_by_name) {
		this.approved_by_name = approved_by_name;
	}

	public String getApproved_date() {
		return approved_date;
	}

	public void setApproved_date(String approved_date) {
		this.approved_date = approved_date;
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

	public String getPurchase_order_master_transaction_id() {
		return purchase_order_master_transaction_id;
	}

	public void setPurchase_order_master_transaction_id(String purchase_order_master_transaction_id) {
		this.purchase_order_master_transaction_id = purchase_order_master_transaction_id;
	}

	public String getProduct_material_id() {
		return product_material_id;
	}

	public void setProduct_material_id(String product_material_id) {
		this.product_material_id = product_material_id;
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

	public String getProduct_material_hsn_code_id() {
		return product_material_hsn_code_id;
	}

	public void setProduct_material_hsn_code_id(String product_material_hsn_code_id) {
		this.product_material_hsn_code_id = product_material_hsn_code_id;
	}

	public String getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(String department_id) {
		this.department_id = department_id;
	}

	public String getIndented_by_id() {
		return indented_by_id;
	}

	public void setIndented_by_id(String indented_by_id) {
		this.indented_by_id = indented_by_id;
	}

	public String getApproved_by_id() {
		return approved_by_id;
	}

	public void setApproved_by_id(String approved_by_id) {
		this.approved_by_id = approved_by_id;
	}

	public String getSo_sr_no() {
		return so_sr_no;
	}

	public void setSo_sr_no(String so_sr_no) {
		this.so_sr_no = so_sr_no;
	}

	public CPtPurchaseOrderDetailsRptModel_Not_Used(String purchase_order_details_transaction_id, String sr_no,
	                                                String company_name, String company_branch_name, String financial_year, String purchase_order_no,
	                                                String purchase_order_version, String purchase_order_date, String purchase_order_type,
	                                                String product_type_name, String product_type_short_name, String product_type_group, String indent_no,
	                                                String indent_date, String indent_version, String product_material_code, String product_material_name,
	                                                String product_material_print_name, String product_material_tech_spect,
	                                                String product_material_stock_unit_name, String lead_time, String product_packing_name, String hsn_sac_type,
	                                                String hsn_sac_code, String hsn_sac_description, String hsn_sac_rate, String product_material_po_quantity,
	                                                String product_material_conversion_factor, String product_material_po_weight,
	                                                String product_material_po_approved_quantity, String product_material_po_approved_weight,
	                                                String product_material_po_rejected_quantity, String product_material_po_rejected_weight,
	                                                String pree_closed_quantity, String pree_closed_weight, String purchase_return_quantity,
	                                                String purchase_return_weight, String material_rate, String material_basic_amount,
	                                                String material_discount_percent, String material_discount_amount, String material_taxable_amount,
	                                                String material_cgst_percent, String material_cgst_total, String material_sgst_percent,
	                                                String material_sgst_total, String material_igst_percent, String material_igst_total,
	                                                String material_total_amount, String material_schedule_date, String purchase_order_item_status,
	                                                String purchase_order_mail_sent_status, String purchase_order_mail_sent_status_desc,
	                                                String pending_quantity, String pending_weight, String excess_quantity, String excess_weight,
	                                                String production_issue_quantity, String production_issue_weight, String production_issue_return_quantity,
	                                                String production_issue_return_weight, String production_issue_rejection_quantity,
	                                                String production_issue_rejection_weight, String assembly_production_issue_quantity,
	                                                String assembly_production_issue_weight, String sales_quantity, String sales_weight,
	                                                String sales_return_quantity, String sales_return_weight, String sales_rejection_quantity,
	                                                String sales_rejection_weight, String transfer_issue_quantity, String transfer_issue_weight,
	                                                String transfer_receipt_quantity, String transfer_receipt_weight, String outsources_out_quantity,
	                                                String outsources_out_weight, String outsources_in_quantity, String outsources_in_weight,
	                                                String outsources_rejection_quantity, String outsources_rejection_weight, String loan_receipt_quantity,
	                                                String loan_receipt_weight, String loan_issue_quantity, String loan_issue_weight, String cancel_quantity,
	                                                String cancel_weight, String difference_quantity, String difference_weight,
	                                                String material_po_approval_remark, String department_name, String approved_by_name, String approved_date,
	                                                String so_sr_no, String remark, String is_active, String is_delete, String created_by, String created_on,
	                                                String modified_by, String modified_on, String deleted_by, String deleted_on, String company_id,
	                                                String company_branch_id, String purchase_order_type_id, String purchase_order_master_transaction_id,
	                                                String product_material_id, String product_material_unit_id, String product_material_packing_id,
	                                                String product_material_hsn_code_id, String department_id, String indented_by_id, String approved_by_id) {
		super();
		this.purchase_order_details_transaction_id = purchase_order_details_transaction_id;
		this.sr_no = sr_no;
		this.company_name = company_name;
		this.company_branch_name = company_branch_name;
		this.financial_year = financial_year;
		this.purchase_order_no = purchase_order_no;
		this.purchase_order_version = purchase_order_version;
		this.purchase_order_date = purchase_order_date;
		this.purchase_order_type = purchase_order_type;
		this.product_type_name = product_type_name;
		this.product_type_short_name = product_type_short_name;
		this.product_type_group = product_type_group;
		this.indent_no = indent_no;
		this.indent_date = indent_date;
		this.indent_version = indent_version;
		this.product_material_code = product_material_code;
		this.product_material_name = product_material_name;
		this.product_material_print_name = product_material_print_name;
		this.product_material_tech_spect = product_material_tech_spect;
		this.product_material_stock_unit_name = product_material_stock_unit_name;
		this.lead_time = lead_time;
		this.product_packing_name = product_packing_name;
		this.hsn_sac_type = hsn_sac_type;
		this.hsn_sac_code = hsn_sac_code;
		this.hsn_sac_description = hsn_sac_description;
		this.hsn_sac_rate = hsn_sac_rate;
		this.product_material_po_quantity = product_material_po_quantity;
		this.product_material_conversion_factor = product_material_conversion_factor;
		this.product_material_po_weight = product_material_po_weight;
		this.product_material_po_approved_quantity = product_material_po_approved_quantity;
		this.product_material_po_approved_weight = product_material_po_approved_weight;
		this.product_material_po_rejected_quantity = product_material_po_rejected_quantity;
		this.product_material_po_rejected_weight = product_material_po_rejected_weight;
		this.pree_closed_quantity = pree_closed_quantity;
		this.pree_closed_weight = pree_closed_weight;
		this.purchase_return_quantity = purchase_return_quantity;
		this.purchase_return_weight = purchase_return_weight;
		this.material_rate = material_rate;
		this.material_basic_amount = material_basic_amount;
		this.material_discount_percent = material_discount_percent;
		this.material_discount_amount = material_discount_amount;
		this.material_taxable_amount = material_taxable_amount;
		this.material_cgst_percent = material_cgst_percent;
		this.material_cgst_total = material_cgst_total;
		this.material_sgst_percent = material_sgst_percent;
		this.material_sgst_total = material_sgst_total;
		this.material_igst_percent = material_igst_percent;
		this.material_igst_total = material_igst_total;
		this.material_total_amount = material_total_amount;
		this.material_schedule_date = material_schedule_date;
		this.purchase_order_item_status = purchase_order_item_status;
		this.purchase_order_mail_sent_status = purchase_order_mail_sent_status;
		this.purchase_order_mail_sent_status_desc = purchase_order_mail_sent_status_desc;
		this.pending_quantity = pending_quantity;
		this.pending_weight = pending_weight;
		this.excess_quantity = excess_quantity;
		this.excess_weight = excess_weight;
		this.production_issue_quantity = production_issue_quantity;
		this.production_issue_weight = production_issue_weight;
		this.production_issue_return_quantity = production_issue_return_quantity;
		this.production_issue_return_weight = production_issue_return_weight;
		this.production_issue_rejection_quantity = production_issue_rejection_quantity;
		this.production_issue_rejection_weight = production_issue_rejection_weight;
		this.assembly_production_issue_quantity = assembly_production_issue_quantity;
		this.assembly_production_issue_weight = assembly_production_issue_weight;
		this.sales_quantity = sales_quantity;
		this.sales_weight = sales_weight;
		this.sales_return_quantity = sales_return_quantity;
		this.sales_return_weight = sales_return_weight;
		this.sales_rejection_quantity = sales_rejection_quantity;
		this.sales_rejection_weight = sales_rejection_weight;
		this.transfer_issue_quantity = transfer_issue_quantity;
		this.transfer_issue_weight = transfer_issue_weight;
		this.transfer_receipt_quantity = transfer_receipt_quantity;
		this.transfer_receipt_weight = transfer_receipt_weight;
		this.outsources_out_quantity = outsources_out_quantity;
		this.outsources_out_weight = outsources_out_weight;
		this.outsources_in_quantity = outsources_in_quantity;
		this.outsources_in_weight = outsources_in_weight;
		this.outsources_rejection_quantity = outsources_rejection_quantity;
		this.outsources_rejection_weight = outsources_rejection_weight;
		this.loan_receipt_quantity = loan_receipt_quantity;
		this.loan_receipt_weight = loan_receipt_weight;
		this.loan_issue_quantity = loan_issue_quantity;
		this.loan_issue_weight = loan_issue_weight;
		this.cancel_quantity = cancel_quantity;
		this.cancel_weight = cancel_weight;
		this.difference_quantity = difference_quantity;
		this.difference_weight = difference_weight;
		this.material_po_approval_remark = material_po_approval_remark;
		this.department_name = department_name;
		this.approved_by_name = approved_by_name;
		this.approved_date = approved_date;
		this.so_sr_no = so_sr_no;
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
		this.purchase_order_master_transaction_id = purchase_order_master_transaction_id;
		this.product_material_id = product_material_id;
		this.product_material_unit_id = product_material_unit_id;
		this.product_material_packing_id = product_material_packing_id;
		this.product_material_hsn_code_id = product_material_hsn_code_id;
		this.department_id = department_id;
		this.indented_by_id = indented_by_id;
		this.approved_by_id = approved_by_id;
	}

	public CPtPurchaseOrderDetailsRptModel_Not_Used() {
		super();
	}

}
