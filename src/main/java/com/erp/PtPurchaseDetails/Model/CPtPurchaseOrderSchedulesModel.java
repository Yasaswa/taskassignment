package com.erp.PtPurchaseDetails.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "pt_purchase_order_schedules")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CPtPurchaseOrderSchedulesModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "purchase_order_schedules_transaction_id")
	private int purchase_order_schedules_transaction_id;
	private Integer company_id;
	private Integer company_branch_id;
	private Integer purchase_order_master_transaction_id;
	private Integer purchase_order_details_transaction_id;
	private String purchase_order_no;
	private String purchase_order_date;
	private Integer purchase_order_version;
	private String customer_order_no;
	private String customer_order_Date;
	private Integer product_type_id;
	private String product_type;
	private String product_material_id;
	private Integer product_material_unit_id;
	private double material_po_quantity;
	private double material_po_weight;
	private double product_material_po_schedule_quantity;
	private double product_material_po_schedule_weight;
	private String expected_schedule_date;
	private double product_material_receipt_quantity;
	private double product_material_receipt_weight;
	private String material_receipt_date;
	private String purchase_order_schedules_item_status;
	private Integer so_sr_no;
	private String indent_no;
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

	public int getPurchase_order_schedules_transaction_id() {
		return purchase_order_schedules_transaction_id;
	}

	public void setPurchase_order_schedules_transaction_id(int purchase_order_schedules_transaction_id) {
		this.purchase_order_schedules_transaction_id = purchase_order_schedules_transaction_id;
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
		if (customer_order_Date == null || customer_order_Date.isEmpty()) {
			this.customer_order_Date = null;
		} else {
			this.customer_order_Date = customer_order_Date;
		}
	}

	public Integer getProduct_type_id() {
		return product_type_id;
	}

	public void setProduct_type_id(Integer product_type_id) {
		this.product_type_id = product_type_id;
	}

	public String getProduct_type() {
		return product_type;
	}

	public void setProduct_type(String product_type) {
		this.product_type = product_type;
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
		if (expected_schedule_date == null || expected_schedule_date.isEmpty()) {
			this.expected_schedule_date = null;
		} else {
			this.expected_schedule_date = expected_schedule_date;
		}
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
		if (material_receipt_date == null || material_receipt_date.isEmpty()) {
			this.material_receipt_date = null;
		} else {
			this.material_receipt_date = material_receipt_date;
		}

	}

	public String getPurchase_order_schedules_item_status() {
		return purchase_order_schedules_item_status;
	}

	public void setPurchase_order_schedules_item_status(String purchase_order_schedules_item_status) {
		this.purchase_order_schedules_item_status = purchase_order_schedules_item_status;
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

	public CPtPurchaseOrderSchedulesModel(int purchase_order_schedules_transaction_id, Integer company_id,
	                                      Integer company_branch_id, Integer purchase_order_master_transaction_id,
	                                      Integer purchase_order_details_transaction_id, String purchase_order_no, String purchase_order_date,
	                                      Integer purchase_order_version, String customer_order_no, String customer_order_Date,
	                                      Integer product_type_id, String product_type, String product_material_id, Integer product_material_unit_id,
	                                      double material_po_quantity, double material_po_weight, double product_material_po_schedule_quantity,
	                                      double product_material_po_schedule_weight, String expected_schedule_date,
	                                      double product_material_receipt_quantity, double product_material_receipt_weight,
	                                      String material_receipt_date, String purchase_order_schedules_item_status, Integer so_sr_no, String indent_no, String remark,
	                                      boolean is_active, boolean is_delete, String created_by, Date created_on, String modified_by,
	                                      Date modified_on, String deleted_by, Date deleted_on) {
		super();
		this.purchase_order_schedules_transaction_id = purchase_order_schedules_transaction_id;
		this.company_id = company_id;
		this.company_branch_id = company_branch_id;
		this.purchase_order_master_transaction_id = purchase_order_master_transaction_id;
		this.purchase_order_details_transaction_id = purchase_order_details_transaction_id;
		this.purchase_order_no = purchase_order_no;
		this.purchase_order_date = purchase_order_date;
		this.purchase_order_version = purchase_order_version;
		this.customer_order_no = customer_order_no;
		this.customer_order_Date = customer_order_Date;
		this.product_type_id = product_type_id;
		this.product_type = product_type;
		this.product_material_id = product_material_id;
		this.product_material_unit_id = product_material_unit_id;
		this.material_po_quantity = material_po_quantity;
		this.material_po_weight = material_po_weight;
		this.product_material_po_schedule_quantity = product_material_po_schedule_quantity;
		this.product_material_po_schedule_weight = product_material_po_schedule_weight;
		this.expected_schedule_date = expected_schedule_date;
		this.product_material_receipt_quantity = product_material_receipt_quantity;
		this.product_material_receipt_weight = product_material_receipt_weight;
		this.material_receipt_date = material_receipt_date;
		this.purchase_order_schedules_item_status = purchase_order_schedules_item_status;
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
	}

	public CPtPurchaseOrderSchedulesModel() {
		super();

	}

}
