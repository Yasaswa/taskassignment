package com.erp.MtDispatchScheduleDetails.Model;

import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@Immutable
@Subselect("select * from  mtv_dispatch_schedule_details_trading_rpt")
public class CMtDispatchScheduleDetailsTradingRptModel_Not_Used {

	@Id
	private String dispatch_schedule_details_transaction_id;
	private String company_name;
	private String company_branch_name;
	private String financial_year;
	private String dispatch_schedule_type;
	private String dispatch_schedule_creation_type;
	private String dispatch_schedule_creation_type_desc;
	private String dispatch_schedule_no;
	private String dispatch_schedule_date;
	private String dispatch_schedule_version;
	private String customer_order_no;
	private String customer_order_Date;
	private String customer_order_version;
	private String so_sr_no;
	private String batch_no;
	private String product_material_print_name;
	private String product_material_tech_spect;
	private String sr_no;
	private String expected_dispatch_quantity;
	private String expected_dispatch_weight;
	private String actual_dispatch_quantity;
	private String actual_dispatch_weight;
	private String dispatch_return_quantity;
	private String dispatch_return_weight;
	private String pending_quantity;
	private String pending_weight;
	private String expected_schedule_date;
	private String delayed_days;
	private String dispatched_quantity;
	private String dispatched_weight;
	private String invoice_quantity;
	private String invoice_weight;
	private String dispatch_schedule_item_status;
	private String dispatch_schedule_remark;
	private String approved_date;
	private String total_quantity;
	private String total_weight;
	private String actual_quantity;
	private String actual_weight;
	private String dispatch_note_status;
	private String dispatch_note_remark;
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
	private Integer sales_order_details_transaction_id;
	private String product_material_id;
	private String product_material_unit_id;
	private String product_material_packing_id;
	private String dispatch_schedule_type_id;
	private String dispatch_schedule_master_transaction_id;

	public String getDispatch_schedule_details_transaction_id() {
		return dispatch_schedule_details_transaction_id;
	}

	public void setDispatch_schedule_details_transaction_id(String dispatch_schedule_details_transaction_id) {
		this.dispatch_schedule_details_transaction_id = dispatch_schedule_details_transaction_id;
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

	public String getDispatch_schedule_type() {
		return dispatch_schedule_type;
	}

	public void setDispatch_schedule_type(String dispatch_schedule_type) {
		this.dispatch_schedule_type = dispatch_schedule_type;
	}

	public String getDispatch_schedule_creation_type() {
		return dispatch_schedule_creation_type;
	}

	public void setDispatch_schedule_creation_type(String dispatch_schedule_creation_type) {
		this.dispatch_schedule_creation_type = dispatch_schedule_creation_type;
	}

	public String getDispatch_schedule_creation_type_desc() {
		return dispatch_schedule_creation_type_desc;
	}

	public void setDispatch_schedule_creation_type_desc(String dispatch_schedule_creation_type_desc) {
		this.dispatch_schedule_creation_type_desc = dispatch_schedule_creation_type_desc;
	}

	public String getDispatch_schedule_no() {
		return dispatch_schedule_no;
	}

	public void setDispatch_schedule_no(String dispatch_schedule_no) {
		this.dispatch_schedule_no = dispatch_schedule_no;
	}

	public String getDispatch_schedule_date() {
		return dispatch_schedule_date;
	}

	public void setDispatch_schedule_date(String dispatch_schedule_date) {
		this.dispatch_schedule_date = dispatch_schedule_date;
	}

	public String getDispatch_schedule_version() {
		return dispatch_schedule_version;
	}

	public void setDispatch_schedule_version(String dispatch_schedule_version) {
		this.dispatch_schedule_version = dispatch_schedule_version;
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

	public String getCustomer_order_version() {
		return customer_order_version;
	}

	public void setCustomer_order_version(String customer_order_version) {
		this.customer_order_version = customer_order_version;
	}

	public String getSo_sr_no() {
		return so_sr_no;
	}

	public void setSo_sr_no(String so_sr_no) {
		this.so_sr_no = so_sr_no;
	}

	public String getBatch_no() {
		return batch_no;
	}

	public void setBatch_no(String batch_no) {
		this.batch_no = batch_no;
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

	public String getSr_no() {
		return sr_no;
	}

	public void setSr_no(String sr_no) {
		this.sr_no = sr_no;
	}

	public String getExpected_dispatch_quantity() {
		return expected_dispatch_quantity;
	}

	public void setExpected_dispatch_quantity(String expected_dispatch_quantity) {
		this.expected_dispatch_quantity = expected_dispatch_quantity;
	}

	public String getExpected_dispatch_weight() {
		return expected_dispatch_weight;
	}

	public void setExpected_dispatch_weight(String expected_dispatch_weight) {
		this.expected_dispatch_weight = expected_dispatch_weight;
	}

	public String getActual_dispatch_quantity() {
		return actual_dispatch_quantity;
	}

	public void setActual_dispatch_quantity(String actual_dispatch_quantity) {
		this.actual_dispatch_quantity = actual_dispatch_quantity;
	}

	public String getActual_dispatch_weight() {
		return actual_dispatch_weight;
	}

	public void setActual_dispatch_weight(String actual_dispatch_weight) {
		this.actual_dispatch_weight = actual_dispatch_weight;
	}

	public String getDispatch_return_quantity() {
		return dispatch_return_quantity;
	}

	public void setDispatch_return_quantity(String dispatch_return_quantity) {
		this.dispatch_return_quantity = dispatch_return_quantity;
	}

	public String getDispatch_return_weight() {
		return dispatch_return_weight;
	}

	public void setDispatch_return_weight(String dispatch_return_weight) {
		this.dispatch_return_weight = dispatch_return_weight;
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

	public String getExpected_schedule_date() {
		return expected_schedule_date;
	}

	public void setExpected_schedule_date(String expected_schedule_date) {
		this.expected_schedule_date = expected_schedule_date;
	}

	public String getDelayed_days() {
		return delayed_days;
	}

	public void setDelayed_days(String delayed_days) {
		this.delayed_days = delayed_days;
	}

	public String getDispatched_quantity() {
		return dispatched_quantity;
	}

	public void setDispatched_quantity(String dispatched_quantity) {
		this.dispatched_quantity = dispatched_quantity;
	}

	public String getDispatched_weight() {
		return dispatched_weight;
	}

	public void setDispatched_weight(String dispatched_weight) {
		this.dispatched_weight = dispatched_weight;
	}

	public String getInvoice_quantity() {
		return invoice_quantity;
	}

	public void setInvoice_quantity(String invoice_quantity) {
		this.invoice_quantity = invoice_quantity;
	}

	public String getInvoice_weight() {
		return invoice_weight;
	}

	public void setInvoice_weight(String invoice_weight) {
		this.invoice_weight = invoice_weight;
	}

	public String getDispatch_schedule_item_status() {
		return dispatch_schedule_item_status;
	}

	public void setDispatch_schedule_item_status(String dispatch_schedule_item_status) {
		this.dispatch_schedule_item_status = dispatch_schedule_item_status;
	}

	public String getDispatch_schedule_remark() {
		return dispatch_schedule_remark;
	}

	public void setDispatch_schedule_remark(String dispatch_schedule_remark) {
		this.dispatch_schedule_remark = dispatch_schedule_remark;
	}

	public String getApproved_date() {
		return approved_date;
	}

	public void setApproved_date(String approved_date) {
		this.approved_date = approved_date;
	}

	public String getTotal_quantity() {
		return total_quantity;
	}

	public void setTotal_quantity(String total_quantity) {
		this.total_quantity = total_quantity;
	}

	public String getTotal_weight() {
		return total_weight;
	}

	public void setTotal_weight(String total_weight) {
		this.total_weight = total_weight;
	}

	public String getActual_quantity() {
		return actual_quantity;
	}

	public void setActual_quantity(String actual_quantity) {
		this.actual_quantity = actual_quantity;
	}

	public String getActual_weight() {
		return actual_weight;
	}

	public void setActual_weight(String actual_weight) {
		this.actual_weight = actual_weight;
	}

	public String getDispatch_note_status() {
		return dispatch_note_status;
	}

	public void setDispatch_note_status(String dispatch_note_status) {
		this.dispatch_note_status = dispatch_note_status;
	}

	public String getDispatch_note_remark() {
		return dispatch_note_remark;
	}

	public void setDispatch_note_remark(String dispatch_note_remark) {
		this.dispatch_note_remark = dispatch_note_remark;
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

	public Integer getSales_order_details_transaction_id() {
		return sales_order_details_transaction_id;
	}

	public void setSales_order_details_transaction_id(Integer sales_order_details_transaction_id) {
		this.sales_order_details_transaction_id = sales_order_details_transaction_id;
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

	public String getDispatch_schedule_type_id() {
		return dispatch_schedule_type_id;
	}

	public void setDispatch_schedule_type_id(String dispatch_schedule_type_id) {
		this.dispatch_schedule_type_id = dispatch_schedule_type_id;
	}

	public String getDispatch_schedule_master_transaction_id() {
		return dispatch_schedule_master_transaction_id;
	}

	public void setDispatch_schedule_master_transaction_id(String dispatch_schedule_master_transaction_id) {
		this.dispatch_schedule_master_transaction_id = dispatch_schedule_master_transaction_id;
	}

	public CMtDispatchScheduleDetailsTradingRptModel_Not_Used(String dispatch_schedule_details_transaction_id,
	                                                          String company_name, String company_branch_name, String financial_year, String dispatch_schedule_type,
	                                                          String dispatch_schedule_creation_type, String dispatch_schedule_creation_type_desc,
	                                                          String dispatch_schedule_no, String dispatch_schedule_date, String dispatch_schedule_version,
	                                                          String customer_order_no, String customer_order_Date, String customer_order_version, String so_sr_no,
	                                                          String batch_no, String product_material_print_name, String product_material_tech_spect, String sr_no,
	                                                          String expected_dispatch_quantity, String expected_dispatch_weight, String actual_dispatch_quantity,
	                                                          String actual_dispatch_weight, String dispatch_return_quantity, String dispatch_return_weight,
	                                                          String pending_quantity, String pending_weight, String expected_schedule_date, String delayed_days,
	                                                          String dispatched_quantity, String dispatched_weight, String invoice_quantity, String invoice_weight,
	                                                          String dispatch_schedule_item_status, String dispatch_schedule_remark, String approved_date,
	                                                          String total_quantity, String total_weight, String actual_quantity, String actual_weight,
	                                                          String dispatch_note_status, String dispatch_note_remark, String other_terms_conditions, String remark,
	                                                          String is_active, String is_delete, String created_by, String created_on, String modified_by,
	                                                          String modified_on, String deleted_by, String deleted_on, Integer sales_order_details_transaction_id,
	                                                          String product_material_id, String product_material_unit_id, String product_material_packing_id,
	                                                          String dispatch_schedule_type_id, String dispatch_schedule_master_transaction_id) {
		super();
		this.dispatch_schedule_details_transaction_id = dispatch_schedule_details_transaction_id;
		this.company_name = company_name;
		this.company_branch_name = company_branch_name;
		this.financial_year = financial_year;
		this.dispatch_schedule_type = dispatch_schedule_type;
		this.dispatch_schedule_creation_type = dispatch_schedule_creation_type;
		this.dispatch_schedule_creation_type_desc = dispatch_schedule_creation_type_desc;
		this.dispatch_schedule_no = dispatch_schedule_no;
		this.dispatch_schedule_date = dispatch_schedule_date;
		this.dispatch_schedule_version = dispatch_schedule_version;
		this.customer_order_no = customer_order_no;
		this.customer_order_Date = customer_order_Date;
		this.customer_order_version = customer_order_version;
		this.so_sr_no = so_sr_no;
		this.batch_no = batch_no;
		this.product_material_print_name = product_material_print_name;
		this.product_material_tech_spect = product_material_tech_spect;
		this.sr_no = sr_no;
		this.expected_dispatch_quantity = expected_dispatch_quantity;
		this.expected_dispatch_weight = expected_dispatch_weight;
		this.actual_dispatch_quantity = actual_dispatch_quantity;
		this.actual_dispatch_weight = actual_dispatch_weight;
		this.dispatch_return_quantity = dispatch_return_quantity;
		this.dispatch_return_weight = dispatch_return_weight;
		this.pending_quantity = pending_quantity;
		this.pending_weight = pending_weight;
		this.expected_schedule_date = expected_schedule_date;
		this.delayed_days = delayed_days;
		this.dispatched_quantity = dispatched_quantity;
		this.dispatched_weight = dispatched_weight;
		this.invoice_quantity = invoice_quantity;
		this.invoice_weight = invoice_weight;
		this.dispatch_schedule_item_status = dispatch_schedule_item_status;
		this.dispatch_schedule_remark = dispatch_schedule_remark;
		this.approved_date = approved_date;
		this.total_quantity = total_quantity;
		this.total_weight = total_weight;
		this.actual_quantity = actual_quantity;
		this.actual_weight = actual_weight;
		this.dispatch_note_status = dispatch_note_status;
		this.dispatch_note_remark = dispatch_note_remark;
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
		this.sales_order_details_transaction_id = sales_order_details_transaction_id;
		this.product_material_id = product_material_id;
		this.product_material_unit_id = product_material_unit_id;
		this.product_material_packing_id = product_material_packing_id;
		this.dispatch_schedule_type_id = dispatch_schedule_type_id;
		this.dispatch_schedule_master_transaction_id = dispatch_schedule_master_transaction_id;
	}

	public CMtDispatchScheduleDetailsTradingRptModel_Not_Used() {
		super();

	}


}
