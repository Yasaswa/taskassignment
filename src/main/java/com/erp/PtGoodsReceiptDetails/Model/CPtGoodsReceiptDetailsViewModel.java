package com.erp.PtGoodsReceiptDetails.Model;

import lombok.*;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Immutable
@Table(name="ptv_goods_receipt_details")
public class CPtGoodsReceiptDetailsViewModel {

	@Id
	private int goods_receipt_details_transaction_id;
	private String goods_receipt_no;
	private String goods_receipt_date;
	private Integer goods_receipt_version;
	private String grn_item_status_desc;
	private String department_name;
	private String sub_department_name;
	private String cost_center_name;
	private String purchase_order_no;
	private String purchase_order_date;
	private Integer purchase_order_version;
	@Transient
	private String purchase_order_status_desc;
	private String purchase_order_item_status_desc;
	private String customer_order_no;
	private String customer_order_Date;
	private String purchase_order_life;
	private String supplier_challan_no;
	private String supplier_challan_date;
	private String supplier_name;
	private String grn_currency_code;
	private String supplier_city_name;
	private String supplier_state_name;
	private Integer item_qa_by_id;
	private String item_qa_date;
	private String goods_receipt_type;
	private String product_material_tech_spect;
	private Integer lead_time;
	private Integer sr_no;
	private String batch_no;
	@Transient
	private String purchase_order_status;
	private String purchase_order_item_status;
	private String product_material_name;
	private String product_material_stock_unit_name;
	private Double product_material_std_weight;
	private String product_material_type_group;
	private String product_type_name;
	private String product_type_short_name;
	@Transient
	private String product_make_name;
	private String product_category1_name;
	private String product_category2_name;
	@Transient
	private String product_material_type_name;
	@Transient
	private String product_material_grade_name;
	@Transient
	private String product_material_shape_name;
	@Transient
	private String product_material_oem_part_code;
	@Transient
	private String product_material_our_part_code;
	@Transient
	private String product_material_drawing_no;
	private String product_packing_name;
	@Transient
	private String product_material_hsn_sac_code;
	@Transient
	private String hsn_sac_type;
	private String hsn_sac_code;
	@Transient
	private String hsn_sac_description;
	@Transient
	private String hsn_sac_rate;
	private Double product_material_po_approved_quantity;
	private Double product_material_po_approved_weight;
	private Double product_material_grn_quantity;
	private Double product_material_grn_weight;
	private Double product_material_conversion_factor;
	private Double product_material_grn_rejected_quantity;
	private Double product_material_grn_rejected_weight;
	private String product_rejection_type;
	private String product_rejection_parameters_name;
	private String product_rejection_parameters_short_name;
	private Double product_material_grn_accepted_quantity;
	private Double product_material_grn_accepted_weight;
	private Double prev_grn_quantity;
	private Double prev_grn_weight;
	private Double product_material_prev_accepted_quantity;
	private Double product_material_prev_accepted_weight;
	private Double material_rate;
	private Double material_basic_amount;
	private Double material_discount_percent;
	private Double material_discount_amount;
	private Double material_taxable_amount;
	private Double material_freight_amount;
	private Double material_cgst_percent;
	private Double material_cgst_total;
	private Double material_sgst_percent;
	private Double material_sgst_total;
	private Double material_igst_percent;
	private Double material_igst_total;
	private Double material_total_amount;
	private String material_schedule_date;
	private String grn_item_status;
	private Double excess_quantity;
	private Double excess_weight;
	private Double pree_closed_grn_quantity;
	private Double pree_closed_grn_weight;
	private Double purchase_return_quantity;
	private Double purchase_return_weight;
	private Double production_issue_quantity;
	private Double production_issue_weight;
	private Double production_issue_return_quantity;
	private Double production_issue_return_weight;
	private Double production_issue_rejection_quantity;
	private Double production_issue_rejection_weight;
	private Double assembly_production_issue_quantity;
	private Double assembly_production_issue_weight;
	private Double sales_quantity;
	private Double sales_weight;
	private Double sales_return_quantity;
	private Double sales_return_weight;
	private Double sales_rejection_quantity;
	private Double sales_rejection_weight;
	private Double transfer_issue_weight;
	private Double transfer_receipt_quantity;
	private Double transfer_receipt_weight;
	private Double outsources_out_quantity;
	private Double outsources_out_weight;
	private Double outsources_in_quantity;
	private Double outsources_in_weight;
	private Double outsources_rejection_quantity;
	private Double outsources_rejection_weight;
	private Double loan_receipt_quantity;
	private Double loan_receipt_weight;
	private Double loan_issue_quantity;
	private Double loan_issue_weight;
	private Double cancel_quantity;
	private Double cancel_weight;
	private Double difference_quantity;
	private Double difference_weight;
	private String remark;
	private String company_name;
	private String company_branch_name;
	private String financial_year;
	private String expiry_date;
	private String indent_no;
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
	private Integer goods_receipt_master_transaction_id;
	private Integer purchase_order_details_transaction_id;
	private Integer department_id;
	private Integer product_material_unit_id;
	private Integer product_material_packing_id;
	private Integer product_material_hsn_code_id;
	private Integer goods_receipt_type_id;
	private String customer_id;
	private Integer product_material_rejection_reason_id;
	private Integer supplier_id;
	private Integer approved_by_id;
	private Integer supplier_state_id;
	private Integer supplier_city_id;
	private String supplier_contacts_ids;
	private Integer expected_branch_id;
	private Integer expected_branch_state_id;
	private Integer expected_branch_city_id;
	private Integer payment_term_id;
	private Integer agent_id;
	private Integer product_type_id;
	private String product_material_id;
	private Integer godown_id;
	private Integer godown_section_id;
	private Integer godown_section_beans_id;
	@Transient
	private Integer product_material_type_id;
	@Transient
	private Integer product_material_stock_unit_id;
	private Integer product_category1_id;
	private Integer product_category2_id;
	private Double total_box_weight;
	private Double total_quantity_in_box;
	private Double weight_per_box_item;
	private Integer no_of_boxes;
	private String indented_by_name;
	private String approved_by_name;
	private Integer indent_by_id;
	private String sales_order_no;
	private String product_material_code;
	private Integer currency_id;
	private Integer grn_currency_id;
	private double po_material_rate;
	private double currency_exchange_rate;


}
