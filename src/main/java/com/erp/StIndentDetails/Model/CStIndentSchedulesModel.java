package com.erp.StIndentDetails.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "st_indent_schedules")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CStIndentSchedulesModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "indent_schedule_id")
	private int indent_schedule_id;
	private Integer indent_master_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String indent_no;
	private String indent_date;
	private Integer indent_version;
	private String product_material_id;
	private String product_fg_id;
	private Integer product_material_unit_id;
	private double product_material_schedule_quantity;
	private double product_material_schedule_weight;
	private String expected_schedule_date;
	private double product_material_receipt_quantity;
	private double product_material_receipt_weight;
	private String indent_item_status;
	private String remark;
	private String customer_order_no;
	private Integer so_sr_no;
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
	private Double material_quantity;
	private Double material_weight;
	private String material_receipt_date;
	private String sales_order_details_transaction_id;

	public int getIndent_schedule_id() {
		return indent_schedule_id;
	}

	public void setIndent_schedule_id(int indent_schedule_id) {
		this.indent_schedule_id = indent_schedule_id;
	}

	public Integer getIndent_master_id() {
		return indent_master_id;
	}

	public void setIndent_master_id(Integer indent_master_id) {
		this.indent_master_id = indent_master_id;
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

	public Integer getIndent_version() {
		return indent_version;
	}

	public void setIndent_version(Integer indent_version) {
		this.indent_version = indent_version;
	}

	public String getProduct_material_id() {
		return product_material_id;
	}

	public void setProduct_material_id(String product_material_id) {
		this.product_material_id = product_material_id;
	}


	public String getProduct_fg_id() {
		return product_fg_id;
	}

	public void setProduct_fg_id(String product_fg_id) {
		this.product_fg_id = product_fg_id;
	}

	public Integer getProduct_material_unit_id() {
		return product_material_unit_id;
	}

	public void setProduct_material_unit_id(Integer product_material_unit_id) {
		this.product_material_unit_id = product_material_unit_id;
	}

	public double getProduct_material_schedule_quantity() {
		return product_material_schedule_quantity;
	}

	public void setProduct_material_schedule_quantity(double product_material_schedule_quantity) {
		this.product_material_schedule_quantity = product_material_schedule_quantity;
	}

	public double getProduct_material_schedule_weight() {
		return product_material_schedule_weight;
	}

	public void setProduct_material_schedule_weight(double product_material_schedule_weight) {
		this.product_material_schedule_weight = product_material_schedule_weight;
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

	public String getIndent_item_status() {
		return indent_item_status;
	}

	public void setIndent_item_status(String indent_item_status) {
		this.indent_item_status = indent_item_status;
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

	public Double getMaterial_quantity() {
		return material_quantity;
	}

	public void setMaterial_quantity(Double material_quantity) {
		this.material_quantity = material_quantity;
	}

	public Double getMaterial_weight() {
		return material_weight;
	}

	public void setMaterial_weight(Double material_weight) {
		this.material_weight = material_weight;
	}

	public String getMaterial_receipt_date() {
		return material_receipt_date;
	}

	public String getCustomer_order_no() {
		return customer_order_no;
	}

	public void setCustomer_order_no(String customer_order_no) {
		this.customer_order_no = customer_order_no;
	}

	public Integer getSo_sr_no() {
		return so_sr_no;
	}

	public void setSo_sr_no(Integer so_sr_no) {
		this.so_sr_no = so_sr_no;
	}


	public String getSales_order_details_transaction_id() {
		return sales_order_details_transaction_id;
	}

	public void setSales_order_details_transaction_id(String sales_order_details_transaction_id) {
		this.sales_order_details_transaction_id = sales_order_details_transaction_id;
	}

	public void setMaterial_receipt_date(String material_receipt_date) {
		if (material_receipt_date == null || material_receipt_date.isEmpty() || material_receipt_date == "") {
			this.material_receipt_date = null;
		} else {
			this.material_receipt_date = material_receipt_date;
		}
	}


	public CStIndentSchedulesModel(int indent_schedule_id, Integer indent_master_id, Integer company_id,
	                               Integer company_branch_id, String indent_no, String indent_date, Integer indent_version,
	                               String product_material_id, String product_fg_id, Integer product_material_unit_id, double product_material_schedule_quantity,
	                               double product_material_schedule_weight, String expected_schedule_date,
	                               double product_material_receipt_quantity, double product_material_receipt_weight, String indent_item_status,
	                               String remark, String customer_order_no, Integer so_sr_no, boolean is_active, boolean is_delete, String created_by, Date created_on,
	                               String modified_by, Date modified_on, String deleted_by, Date deleted_on, Double material_quantity,
	                               Double material_weight, String sales_order_details_transaction_id, String material_receipt_date) {
		super();
		this.indent_schedule_id = indent_schedule_id;
		this.indent_master_id = indent_master_id;
		this.company_id = company_id;
		this.company_branch_id = company_branch_id;
		this.indent_no = indent_no;
		this.indent_date = indent_date;
		this.indent_version = indent_version;
		this.product_material_id = product_material_id;
		this.product_fg_id = product_fg_id;
		this.product_material_unit_id = product_material_unit_id;
		this.product_material_schedule_quantity = product_material_schedule_quantity;
		this.product_material_schedule_weight = product_material_schedule_weight;
		this.expected_schedule_date = expected_schedule_date;
		this.product_material_receipt_quantity = product_material_receipt_quantity;
		this.product_material_receipt_weight = product_material_receipt_weight;
		this.indent_item_status = indent_item_status;
		this.remark = remark;
		this.customer_order_no = customer_order_no;
		this.so_sr_no = so_sr_no;
		this.is_active = is_active;
		this.is_delete = is_delete;
		this.created_by = created_by;
		this.created_on = created_on;
		this.modified_by = modified_by;
		this.modified_on = modified_on;
		this.deleted_by = deleted_by;
		this.deleted_on = deleted_on;
		this.material_quantity = material_quantity;
		this.material_weight = material_weight;
		this.material_receipt_date = material_receipt_date;
		this.sales_order_details_transaction_id = sales_order_details_transaction_id;
	}

	public CStIndentSchedulesModel() {
		super();

	}

}
