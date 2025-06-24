package com.erp.StIndentDetails.Model;

import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;


@Data
@Entity
@Immutable
@Subselect("select * from  stv_indent_schedules")
public class CStIndentSchedulesViewModel {

	@Id
	private int indent_schedule_id;
	private String company_name;
	private String company_branch_name;
	private String financial_year;
	private String indent_no;
	private String indent_date;
	private Integer indent_version;
	private String indent_type;
	private String indent_source;
	private String department_name;
	private String indented_by_name;
	private String approved_by_name;
	private String customer_name;
	private String customer_order_no;
	private String customer_order_date;
	private String indent_status;
	private String indent_status_desc;
	private String remark;
	private boolean is_active;
	private String product_material_id;
	private String product_fg_id;
	private String product_rm_name;
	private double product_material_quantity;
	private double product_material_weight;
	private int lead_time;
	private double product_material_stock_quantity;
	private double product_material_stock_weight;
	private double product_material_reserve_quantity;
	private double product_material_reserve_weight;
	private double product_material_approved_quantity;
	private double product_material_approved_weight;
	private double product_material_rejected_quantity;
	private double product_material_rejected_weight;
	private double product_material_schedule_quantity;
	private double product_material_schedule_weight;
	private String expected_schedule_date;
	private double product_material_receipt_quantity;
	private double product_material_receipt_weight;
	private String issued_by_name;
	private Integer so_sr_no;
	private Integer issued_by_id;
	private Date issued_date;
	private String indent_item_status;
	private String indent_item_status_desc;
	private String field_name;
	private Integer field_id;
	private boolean is_delete;
	private String created_by;
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;
	private Integer indent_master_id;
	private Integer indent_details_id;
	private Integer indent_type_id;
	private Integer customer_id;
	private Integer department_id;
	private Integer indented_by_id;
	private Integer company_id;
	private Integer company_branch_id;
	private Double material_quantity;
	private Double material_weight;
	private String material_receipt_date;
	private Integer product_material_unit_id;
	private String sales_order_details_transaction_id;

	public void setIndent_schedule_id(int indent_schedule_id) {
		this.indent_schedule_id = indent_schedule_id;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public void setCompany_branch_name(String company_branch_name) {
		this.company_branch_name = company_branch_name;
	}

	public void setFinancial_year(String financial_year) {
		this.financial_year = financial_year;
	}

	public void setIndent_no(String indent_no) {
		this.indent_no = indent_no;
	}

	public void setIndent_date(String indent_date) {
		this.indent_date = indent_date;
	}

	public void setIndent_version(Integer indent_version) {
		this.indent_version = indent_version;
	}

	public void setIndent_type(String indent_type) {
		this.indent_type = indent_type;
	}

	public void setIndent_source(String indent_source) {
		this.indent_source = indent_source;
	}

	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}

	public void setIndented_by_name(String indented_by_name) {
		this.indented_by_name = indented_by_name;
	}

	public void setApproved_by_name(String approved_by_name) {
		this.approved_by_name = approved_by_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public void setCustomer_order_no(String customer_order_no) {
		this.customer_order_no = customer_order_no;
	}

	public void setCustomer_order_date(String customer_order_date) {
		this.customer_order_date = customer_order_date;
	}

	public void setIndent_status(String indent_status) {
		this.indent_status = indent_status;
	}

	public void setIndent_status_desc(String indent_status_desc) {
		this.indent_status_desc = indent_status_desc;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
	}

	public void setProduct_material_id(String product_material_id) {
		this.product_material_id = product_material_id;
	}

	public void setProduct_fg_id(String product_fg_id) {
		this.product_fg_id = product_fg_id;
	}

	public void setProduct_rm_name(String product_rm_name) {
		this.product_rm_name = product_rm_name;
	}

	public void setProduct_material_quantity(double product_material_quantity) {
		this.product_material_quantity = product_material_quantity;
	}

	public void setProduct_material_weight(double product_material_weight) {
		this.product_material_weight = product_material_weight;
	}

	public void setLead_time(int lead_time) {
		this.lead_time = lead_time;
	}

	public void setProduct_material_stock_quantity(double product_material_stock_quantity) {
		this.product_material_stock_quantity = product_material_stock_quantity;
	}

	public void setProduct_material_stock_weight(double product_material_stock_weight) {
		this.product_material_stock_weight = product_material_stock_weight;
	}

	public void setProduct_material_reserve_quantity(double product_material_reserve_quantity) {
		this.product_material_reserve_quantity = product_material_reserve_quantity;
	}

	public void setProduct_material_reserve_weight(double product_material_reserve_weight) {
		this.product_material_reserve_weight = product_material_reserve_weight;
	}

	public void setProduct_material_approved_quantity(double product_material_approved_quantity) {
		this.product_material_approved_quantity = product_material_approved_quantity;
	}

	public void setProduct_material_approved_weight(double product_material_approved_weight) {
		this.product_material_approved_weight = product_material_approved_weight;
	}

	public void setProduct_material_rejected_quantity(double product_material_rejected_quantity) {
		this.product_material_rejected_quantity = product_material_rejected_quantity;
	}

	public void setProduct_material_rejected_weight(double product_material_rejected_weight) {
		this.product_material_rejected_weight = product_material_rejected_weight;
	}

	public void setProduct_material_schedule_quantity(double product_material_schedule_quantity) {
		this.product_material_schedule_quantity = product_material_schedule_quantity;
	}

	public void setProduct_material_schedule_weight(double product_material_schedule_weight) {
		this.product_material_schedule_weight = product_material_schedule_weight;
	}

	public void setExpected_schedule_date(String expected_schedule_date) {
		this.expected_schedule_date = expected_schedule_date;
	}

	public void setProduct_material_receipt_quantity(double product_material_receipt_quantity) {
		this.product_material_receipt_quantity = product_material_receipt_quantity;
	}

	public void setProduct_material_receipt_weight(double product_material_receipt_weight) {
		this.product_material_receipt_weight = product_material_receipt_weight;
	}

	public void setIssued_by_name(String issued_by_name) {
		this.issued_by_name = issued_by_name;
	}

	public void setIssued_by_id(Integer issued_by_id) {
		this.issued_by_id = issued_by_id;
	}

	public void setIssued_date(Date issued_date) {
		this.issued_date = issued_date;
	}

	public void setIndent_item_status(String indent_item_status) {
		this.indent_item_status = indent_item_status;
	}

	public void setIndent_item_status_desc(String indent_item_status_desc) {
		this.indent_item_status_desc = indent_item_status_desc;
	}

	public void setField_name(String field_name) {
		this.field_name = field_name;
	}

	public void setField_id(Integer field_id) {
		this.field_id = field_id;
	}

	public void setIs_delete(boolean is_delete) {
		this.is_delete = is_delete;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
	}

	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}

	public void setModified_on(Date modified_on) {
		this.modified_on = modified_on;
	}

	public void setDeleted_by(String deleted_by) {
		this.deleted_by = deleted_by;
	}

	public void setDeleted_on(Date deleted_on) {
		this.deleted_on = deleted_on;
	}

	public void setIndent_master_id(Integer indent_master_id) {
		this.indent_master_id = indent_master_id;
	}

	public void setIndent_details_id(Integer indent_details_id) {
		this.indent_details_id = indent_details_id;
	}

	public void setIndent_type_id(Integer indent_type_id) {
		this.indent_type_id = indent_type_id;
	}

	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}

	public void setDepartment_id(Integer department_id) {
		this.department_id = department_id;
	}

	public void setIndented_by_id(Integer indented_by_id) {
		this.indented_by_id = indented_by_id;
	}

	public void setCompany_id(Integer company_id) {
		this.company_id = company_id;
	}

	public void setCompany_branch_id(Integer company_branch_id) {
		this.company_branch_id = company_branch_id;
	}

	public void setMaterial_quantity(Double material_quantity) {
		this.material_quantity = material_quantity;
	}

	public void setMaterial_weight(Double material_weight) {
		this.material_weight = material_weight;
	}

	public void setMaterial_receipt_date(String material_receipt_date) {
		this.material_receipt_date = material_receipt_date;
	}

	public void setSo_sr_no(Integer so_sr_no) {
		this.so_sr_no = so_sr_no;
	}


	public void setSales_order_details_transaction_id(String sales_order_details_transaction_id) {
		this.sales_order_details_transaction_id = sales_order_details_transaction_id;
	}

	public void setProduct_material_unit_id(Integer product_material_unit_id) {
		this.product_material_unit_id = product_material_unit_id;
	}

	public CStIndentSchedulesViewModel(int indent_schedule_id, String company_name, String company_branch_name,
	                                   String financial_year, String indent_no, String indent_date, Integer indent_version, String indent_type,
	                                   String indent_source, String department_name, String indented_by_name, String approved_by_name,
	                                   String customer_name, String customer_order_no, String customer_order_date, String indent_status,
	                                   String indent_status_desc, String remark, boolean is_active, String product_material_id, String product_fg_id,
	                                   String product_rm_name, double product_material_quantity, double product_material_weight, int lead_time,
	                                   double product_material_stock_quantity, double product_material_stock_weight,
	                                   double product_material_reserve_quantity, double product_material_reserve_weight,
	                                   double product_material_approved_quantity, double product_material_approved_weight,
	                                   double product_material_rejected_quantity, double product_material_rejected_weight,
	                                   double product_material_schedule_quantity, double product_material_schedule_weight,
	                                   String expected_schedule_date, double product_material_receipt_quantity,
	                                   double product_material_receipt_weight, String issued_by_name, Integer so_sr_no, Integer issued_by_id,
	                                   Date issued_date, String indent_item_status, String indent_item_status_desc,									   String field_name, Integer field_id, boolean is_delete, String created_by,
	                                   Date created_on, String modified_by, Date modified_on, String deleted_by, Date deleted_on,
	                                   Integer indent_master_id, Integer indent_details_id, Integer indent_type_id, Integer customer_id,
	                                   Integer department_id, Integer indented_by_id, Integer company_id, Integer company_branch_id,
	                                   Double material_quantity, Double material_weight, String material_receipt_date, Integer product_material_unit_id, String sales_order_details_transaction_id) {
		super();
		this.indent_schedule_id = indent_schedule_id;
		this.company_name = company_name;
		this.company_branch_name = company_branch_name;
		this.financial_year = financial_year;
		this.indent_no = indent_no;
		this.indent_date = indent_date;
		this.indent_version = indent_version;
		this.indent_type = indent_type;
		this.indent_source = indent_source;
		this.department_name = department_name;
		this.indented_by_name = indented_by_name;
		this.approved_by_name = approved_by_name;
		this.customer_name = customer_name;
		this.customer_order_no = customer_order_no;
		this.customer_order_date = customer_order_date;
		this.indent_status = indent_status;
		this.indent_status_desc = indent_status_desc;
		this.remark = remark;
		this.is_active = is_active;
		this.product_material_id = product_material_id;
		this.product_fg_id = product_fg_id;
		this.product_rm_name = product_rm_name;
		this.product_material_quantity = product_material_quantity;
		this.product_material_weight = product_material_weight;
		this.lead_time = lead_time;
		this.product_material_stock_quantity = product_material_stock_quantity;
		this.product_material_stock_weight = product_material_stock_weight;
		this.product_material_reserve_quantity = product_material_reserve_quantity;
		this.product_material_reserve_weight = product_material_reserve_weight;
		this.product_material_approved_quantity = product_material_approved_quantity;
		this.product_material_approved_weight = product_material_approved_weight;
		this.product_material_rejected_quantity = product_material_rejected_quantity;
		this.product_material_rejected_weight = product_material_rejected_weight;
		this.product_material_schedule_quantity = product_material_schedule_quantity;
		this.product_material_schedule_weight = product_material_schedule_weight;
		this.expected_schedule_date = expected_schedule_date;
		this.product_material_receipt_quantity = product_material_receipt_quantity;
		this.product_material_receipt_weight = product_material_receipt_weight;
		this.issued_by_name = issued_by_name;
		this.so_sr_no = so_sr_no;
		this.issued_by_id = issued_by_id;
		this.issued_date = issued_date;
		this.indent_item_status = indent_item_status;
		this.indent_item_status_desc = indent_item_status_desc;
		this.field_name = field_name;
		this.field_id = field_id;
		this.is_delete = is_delete;
		this.created_by = created_by;
		this.created_on = created_on;
		this.modified_by = modified_by;
		this.modified_on = modified_on;
		this.deleted_by = deleted_by;
		this.deleted_on = deleted_on;
		this.indent_master_id = indent_master_id;
		this.indent_details_id = indent_details_id;
		this.indent_type_id = indent_type_id;
		this.customer_id = customer_id;
		this.department_id = department_id;
		this.indented_by_id = indented_by_id;
		this.company_id = company_id;
		this.company_branch_id = company_branch_id;
		this.material_quantity = material_quantity;
		this.material_weight = material_weight;
		this.material_receipt_date = material_receipt_date;
		this.product_material_unit_id = product_material_unit_id;
		this.sales_order_details_transaction_id = sales_order_details_transaction_id;
	}

	public CStIndentSchedulesViewModel() {
		super();

	}

}
