package com.erp.PtPurchaseDetails.Model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "pt_purchase_order_details")
public class CPtPurchaseOrderDetailsModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "purchase_order_details_transaction_id")
	private int purchase_order_details_transaction_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String financial_year;
	private Integer purchase_order_type_id;
	private String purchase_order_type;
	private Integer purchase_order_master_transaction_id;
	private String purchase_order_no;
	private String purchase_order_date;
	private Integer purchase_order_version;
	private String indent_no;
	private String indent_date;
	private String indent_version;
	private String product_material_id;
	private String product_material_print_name;
	private String product_material_tech_spect;
	private int lead_time;
	private Integer sr_no;
	private Integer product_material_unit_id;
	private Integer product_material_packing_id;
	private Integer product_material_hsn_code_id;
	private double product_material_po_quantity;
	private double product_material_conversion_factor;
	private double product_material_po_weight;
	private Integer product_rm_purchase_unit_id;
	private double product_material_po_approved_quantity;
	private double product_material_po_approved_weight;
	private double product_material_po_rejected_quantity;
	private double product_material_po_rejected_weight;
	private double pree_closed_quantity;
	private double pree_closed_weight;
	private double purchase_return_quantity;
	private double purchase_return_weight;
	private double material_rate;
	private double material_basic_amount;
	private double material_discount_percent;
	private double material_discount_amount;
	private double material_taxable_amount;
	private double material_cgst_percent;
	private double material_cgst_total;
	private double material_sgst_percent;
	private double material_sgst_total;
	private double material_igst_percent;
	private double material_igst_total;
	private double material_total_amount;
	private double material_freight_amount;
	private String material_schedule_date;
	private String purchase_order_item_status;
	private double pending_quantity;
	private double pending_weight;
	private double excess_quantity;
	private double excess_weight;
	private double production_issue_quantity;
	private double production_issue_weight;
	private double production_issue_return_quantity;
	private double production_issue_return_weight;
	private double production_issue_rejection_quantity;
	private double production_issue_rejection_weight;
	private double assembly_production_issue_quantity;
	private double assembly_production_issue_weight;
	private double sales_quantity;
	private double sales_weight;
	private double sales_return_quantity;
	private double sales_return_weight;
	private double sales_rejection_quantity;
	private double sales_rejection_weight;
	private double transfer_issue_quantity;
	private double transfer_issue_weight;
	private double transfer_receipt_quantity;
	private double transfer_receipt_weight;
	private double outsources_out_quantity;
	private double outsources_out_weight;
	private double outsources_in_quantity;
	private double outsources_in_weight;
	private double outsources_rejection_quantity;
	private double outsources_rejection_weight;
	private double loan_receipt_quantity;
	private double loan_receipt_weight;
	private double loan_issue_quantity;
	private double loan_issue_weight;
	private double cancel_quantity;
	private double cancel_weight;
	private double difference_quantity;
	private double difference_weight;
	private String material_po_approval_remark;
	private Integer department_id;
	private String indented_by_id;
	private Integer approved_by_id;
	private String approved_date;
	private Integer so_sr_no;
	private String remark;
	private String customer_id;
	private String customer_order_no;
	private String customer_order_Date;
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
	private Integer godown_id;
	private Integer godown_section_id;
	private Integer godown_section_beans_id;
	private Double product_material_po_quantity_qunital;
	private String cost_center_id;
	private Integer sub_department_id;
	private String grn_item_status;
	private String pre_closed_remark;
	private Integer currency_id;


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
	public String getIndent_date() {
		return indent_date;
	}

	public void setIndent_date(String indent_date) {
		if (indent_date == null || indent_date.isEmpty()) {
			this.indent_date = null;
		} else {
			this.indent_date = indent_date;
		}
	}
	public String getMaterial_schedule_date() {
		return material_schedule_date;
	}

	public void setMaterial_schedule_date(String material_schedule_date) {
		if (material_schedule_date == null || material_schedule_date.isEmpty()) {
			this.material_schedule_date = null;
		} else {
			this.material_schedule_date = material_schedule_date;
		}

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
	public String getApproved_date() {
		return approved_date;
	}

	public void setApproved_date(String approved_date) {

		if (approved_date == null || approved_date.isEmpty()) {
			this.approved_date = null;
		} else {
			this.approved_date = approved_date;
		}
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
}
