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
@Subselect("select * from ptv_purchase_order_schedules")
public class CPtPurchaseOrderSchedulesViewModel {

	@Id
	private int purchase_order_schedules_transaction_id;
	private String company_name;
	private String company_branch_name;
	private String purchase_order_no;
	private String purchase_order_date;
	private Integer purchase_order_version;
	private String customer_order_no;
	private String customer_order_Date;
	private String product_type;
	private String product_type_short_name;
	private String product_material_code;
	private String product_material_name;
	private String product_material_unit_name;
	private String product_material_tech_spect;
	private double material_po_quantity;
	private double material_po_weight;
	private double product_material_po_schedule_quantity;
	private double product_material_po_schedule_weight;
	private String expected_schedule_date;
	private double product_material_receipt_quantity;
	private double product_material_receipt_weight;
	private String material_receipt_date;
	private String purchase_order_schedules_item_status;
	private String purchase_order_schedules_item_status_desc;
	private Integer so_sr_no;
	private String indent_no;
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
	private Integer purchase_order_master_transaction_id;
	private Integer purchase_order_details_transaction_id;
	private Integer product_type_id;
	private String product_material_id;
	private Integer product_material_unit_id;

	public int getPurchase_order_schedules_transaction_id() {
		return purchase_order_schedules_transaction_id;
	}

	public void setPurchase_order_schedules_transaction_id(int purchase_order_schedules_transaction_id) {
		this.purchase_order_schedules_transaction_id = purchase_order_schedules_transaction_id;
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

	public String getProduct_type() {
		return product_type;
	}

	public void setProduct_type(String product_type) {
		this.product_type = product_type;
	}

	public String getProduct_type_short_name() {
		return product_type_short_name;
	}

	public void setProduct_type_short_name(String product_type_short_name) {
		this.product_type_short_name = product_type_short_name;
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

	public String getProduct_material_unit_name() {
		return product_material_unit_name;
	}

	public void setProduct_material_unit_name(String product_material_unit_name) {
		this.product_material_unit_name = product_material_unit_name;
	}

	public String getProduct_material_tech_spect() {
		return product_material_tech_spect;
	}

	public void setProduct_material_tech_spect(String product_material_tech_spect) {
		this.product_material_tech_spect = product_material_tech_spect;
	}

	public double getMaterial_po_quantity() {
		return material_po_quantity;
	}

	public void setMaterial_po_quantity(double material_po_quantity) {
		this.material_po_quantity = material_po_quantity;
	}

	public double getMaterial_po_weight() {
		return material_po_weight;
	}

	public void setMaterial_po_weight(double material_po_weight) {
		this.material_po_weight = material_po_weight;
	}

	public double getProduct_material_po_schedule_quantity() {
		return product_material_po_schedule_quantity;
	}

	public void setProduct_material_po_schedule_quantity(double product_material_po_schedule_quantity) {
		this.product_material_po_schedule_quantity = product_material_po_schedule_quantity;
	}

	public double getProduct_material_po_schedule_weight() {
		return product_material_po_schedule_weight;
	}

	public void setProduct_material_po_schedule_weight(double product_material_po_schedule_weight) {
		this.product_material_po_schedule_weight = product_material_po_schedule_weight;
	}

	public String getExpected_schedule_date() {
		return expected_schedule_date;
	}

	public void setExpected_schedule_date(String expected_schedule_date) {
		this.expected_schedule_date = expected_schedule_date;
	}

	public double getProduct_material_receipt_quantity() {
		return product_material_receipt_quantity;
	}

	public void setProduct_material_receipt_quantity(double product_material_receipt_quantity) {
		this.product_material_receipt_quantity = product_material_receipt_quantity;
	}

	public double getProduct_material_receipt_weight() {
		return product_material_receipt_weight;
	}

	public void setProduct_material_receipt_weight(double product_material_receipt_weight) {
		this.product_material_receipt_weight = product_material_receipt_weight;
	}

	public String getMaterial_receipt_date() {
		return material_receipt_date;
	}

	public void setMaterial_receipt_date(String material_receipt_date) {
		this.material_receipt_date = material_receipt_date;
	}

	public String getPurchase_order_schedules_item_status() {
		return purchase_order_schedules_item_status;
	}

	public void setPurchase_order_schedules_item_status(String purchase_order_schedules_item_status) {
		this.purchase_order_schedules_item_status = purchase_order_schedules_item_status;
	}

	public String getPurchase_order_schedules_item_status_desc() {
		return purchase_order_schedules_item_status_desc;
	}

	public void setPurchase_order_schedules_item_status_desc(String purchase_order_schedules_item_status_desc) {
		this.purchase_order_schedules_item_status_desc = purchase_order_schedules_item_status_desc;
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

	public Integer getPurchase_order_master_transaction_id() {
		return purchase_order_master_transaction_id;
	}

	public void setPurchase_order_master_transaction_id(Integer purchase_order_master_transaction_id) {
		this.purchase_order_master_transaction_id = purchase_order_master_transaction_id;
	}

	public Integer getPurchase_order_details_transaction_id() {
		return purchase_order_details_transaction_id;
	}

	public void setPurchase_order_details_transaction_id(Integer purchase_order_details_transaction_id) {
		this.purchase_order_details_transaction_id = purchase_order_details_transaction_id;
	}

	public Integer getProduct_type_id() {
		return product_type_id;
	}

	public void setProduct_type_id(Integer product_type_id) {
		this.product_type_id = product_type_id;
	}

	public String getProduct_material_id() {
		return product_material_id;
	}

	public void setProduct_material_id(String product_material_id) {
		this.product_material_id = product_material_id;
	}

	public Integer getProduct_material_unit_id() {
		return product_material_unit_id;
	}

	public void setProduct_material_unit_id(Integer product_material_unit_id) {
		this.product_material_unit_id = product_material_unit_id;
	}

	public Integer getSo_sr_no() {
		return so_sr_no;
	}

	public void setSo_sr_no(Integer so_sr_no) {
		this.so_sr_no = so_sr_no;
	}

	public String getIndent_no() {
		return indent_no;
	}

	public void setIndent_no(String indent_no) {
		this.indent_no = indent_no;
	}

	public CPtPurchaseOrderSchedulesViewModel(int purchase_order_schedules_transaction_id, String company_name,
	                                          String company_branch_name, String purchase_order_no, String purchase_order_date,
	                                          Integer purchase_order_version, String customer_order_no, String customer_order_Date, String product_type,
	                                          String product_type_short_name, String product_material_code, String product_material_name,
	                                          String product_material_unit_name, String product_material_tech_spect, double material_po_quantity,
	                                          double material_po_weight, double product_material_po_schedule_quantity,
	                                          double product_material_po_schedule_weight, String expected_schedule_date,
	                                          double product_material_receipt_quantity, double product_material_receipt_weight,
	                                          String material_receipt_date, String purchase_order_schedules_item_status,
	                                          String purchase_order_schedules_item_status_desc, Integer so_sr_no, String indent_no, String remark, boolean is_active,
	                                          boolean is_delete, String created_by, Date created_on, String modified_by, Date modified_on,
	                                          String deleted_by, Date deleted_on, Integer company_id, Integer company_branch_id,
	                                          Integer purchase_order_master_transaction_id, Integer purchase_order_details_transaction_id,
	                                          Integer product_type_id, String product_material_id, Integer product_material_unit_id) {
		super();
		this.purchase_order_schedules_transaction_id = purchase_order_schedules_transaction_id;
		this.company_name = company_name;
		this.company_branch_name = company_branch_name;
		this.purchase_order_no = purchase_order_no;
		this.purchase_order_date = purchase_order_date;
		this.purchase_order_version = purchase_order_version;
		this.customer_order_no = customer_order_no;
		this.customer_order_Date = customer_order_Date;
		this.product_type = product_type;
		this.product_type_short_name = product_type_short_name;
		this.product_material_code = product_material_code;
		this.product_material_name = product_material_name;
		this.product_material_unit_name = product_material_unit_name;
		this.product_material_tech_spect = product_material_tech_spect;
		this.material_po_quantity = material_po_quantity;
		this.material_po_weight = material_po_weight;
		this.product_material_po_schedule_quantity = product_material_po_schedule_quantity;
		this.product_material_po_schedule_weight = product_material_po_schedule_weight;
		this.expected_schedule_date = expected_schedule_date;
		this.product_material_receipt_quantity = product_material_receipt_quantity;
		this.product_material_receipt_weight = product_material_receipt_weight;
		this.material_receipt_date = material_receipt_date;
		this.purchase_order_schedules_item_status = purchase_order_schedules_item_status;
		this.purchase_order_schedules_item_status_desc = purchase_order_schedules_item_status_desc;
		this.so_sr_no = so_sr_no;
		this.indent_no = indent_no;
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
		this.purchase_order_master_transaction_id = purchase_order_master_transaction_id;
		this.purchase_order_details_transaction_id = purchase_order_details_transaction_id;
		this.product_type_id = product_type_id;
		this.product_material_id = product_material_id;
		this.product_material_unit_id = product_material_unit_id;
	}

	public CPtPurchaseOrderSchedulesViewModel() {
		super();

	}

}
