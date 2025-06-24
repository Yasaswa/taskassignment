package com.erp.PtGoodsReceiptDetails.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pt_goods_receipt_details")
public class CPtGoodsReceiptDetailsModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "goods_receipt_details_transaction_id")
	private int goods_receipt_details_transaction_id;
	private Integer goods_receipt_master_transaction_id;
	private Integer purchase_order_details_transaction_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String financial_year;
	private String goods_receipt_no;
	private String goods_receipt_date;
	private Integer goods_receipt_version;
	private String purchase_order_no;
	private String purchase_order_date;
	private Integer purchase_order_version;
	private String customer_id;
	private String customer_order_no;
	private String customer_order_Date;
	private Integer item_qa_by_id;
	private String item_qa_date;
	private Integer goods_receipt_type_id;
	private String goods_receipt_type;
	private String product_material_id;
	private String product_material_tech_spect;
	private Integer lead_time;
	private Integer sr_no;
	private String batch_no;
	private Integer product_material_unit_id;
	private Integer product_material_packing_id;
	private Integer product_material_hsn_code_id;
	private double product_material_po_approved_quantity;
	private double product_material_po_approved_weight;
	private double product_material_grn_quantity;
	private double product_material_grn_weight;
	private double product_material_grn_accepted_quantity;
	private double product_material_grn_accepted_weight;
	private double product_material_conversion_factor;
	private double product_material_grn_rejected_quantity;
	private double product_material_grn_rejected_weight;
	private double material_rate;
	private double material_basic_amount;
	private double material_freight_amount;
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
	private String material_schedule_date;
	private String grn_item_status;
	private String expiry_date;
	private double excess_quantity;
	private double excess_weight;
	private double pree_closed_grn_quantity;
	private double pree_closed_grn_weight;
	private double purchase_return_quantity;
	private double purchase_return_weight;
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
	private double product_material_prev_accepted_quantity;
	private double product_material_prev_accepted_weight;
	private Integer godown_id;
	private Integer godown_section_id;
	private Integer godown_section_beans_id;
	private Integer department_id;
	private Integer product_material_rejection_reason_id;
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
	private double total_box_weight;
	private double total_quantity_in_box;
	private double weight_per_box_item;
	private Integer no_of_boxes;
	private Integer currency_id;
	private Integer grn_currency_id;
	private double po_material_rate;
	private double currency_exchange_rate;


	public void setMaterial_schedule_date(String material_schedule_date) {
		if (material_schedule_date == null || material_schedule_date.isEmpty()) {
			this.material_schedule_date = null;
		} else {
			this.material_schedule_date = material_schedule_date;
		}
	}
	public void setExpiry_date(String expiry_date) {
		if (expiry_date == null || expiry_date.isEmpty()) {
			this.expiry_date = null;
		} else {
			this.expiry_date = expiry_date;
		}
	}

}
