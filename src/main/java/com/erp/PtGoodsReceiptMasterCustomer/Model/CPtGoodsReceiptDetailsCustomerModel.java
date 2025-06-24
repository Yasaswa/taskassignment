package com.erp.PtGoodsReceiptMasterCustomer.Model;

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
@Table(name = "pt_goods_receipt_details_customer")
//@JsonIgnoreProperties(ignoreUnknown = true)
public class CPtGoodsReceiptDetailsCustomerModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_goods_receipt_details_transaction_id")
	private int customer_goods_receipt_details_transaction_id;
	private Integer customer_goods_receipt_master_transaction_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String financial_year;
	private String customer_goods_receipt_no;
	private String customer_goods_receipt_date;
	private Integer customer_goods_receipt_version;
	private Integer customer_goods_receipt_type_id;
	private String customer_goods_receipt_type;
	private String customer_material_order_weight;
	private String customer_batch_no;
	private String customer_material_id;
	private String customer_material_tech_spect;
	private Integer sr_no;
	private Integer customer_material_unit_id;
	private Integer customer_material_packing_id;
	private Integer customer_material_hsn_code_id;
	private double customer_material_order_quantity;
	private double customer_material_accepted_quantity;
	private double customer_material_accepted_weight;
	private double customer_material_conversion_factor;
	private double customer_material_rejected_quantity;
	private double customer_material_rejected_weight;
	private double customer_material_rate;
	private double customer_material_item_amount;
	private Integer customer_material_rejection_reason_id;
	private double customer_material_prev_accepted_quantity;
	private double customer_material_prev_accepted_weight;
	private double pree_closed_customer_quantity;
	private double pree_closed_customer_weight;
	private double customer_return_quantity;
	private double customer_return_weight;
	private double production_issue_quantity;
	private double production_issue_weight;
	private double production_issue_return_quantity;
	private double production_issue_return_weight;
	private double production_issue_rejection_quantity;
	private double production_issue_rejection_weight;
	private double assembly_production_issue_quantity;
	private double assembly_production_issue_weight;
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
	private Integer godown_id;
	private Integer godown_section_id;
	private Integer godown_section_beans_id;
	private Integer department_id;
	private String customer_item_status;
	private String remark;
	private boolean is_active = Boolean.TRUE;
	private boolean is_delete = Boolean.FALSE;
	private String created_by;
	@CreationTimestamp
	@Column(name = "created_on", updatable = false)
	private Date created_on;
	private String modified_by;
	@UpdateTimestamp
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;
	private String expiry_date;
	private double total_box_weight;
	private double total_quantity_in_box;
	private double weight_per_box_item;
	@Transient
	private double prev_accepted_quantity;
	@Transient
	private double prev_accepted_weight;
	@Transient
	private double prev_order_quantity;
	@Transient
	private double prev_order_weight;

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

	public String getCustomer_goods_receipt_date() {
		return customer_goods_receipt_date;
	}

	public void setCustomer_goods_receipt_date(String customer_goods_receipt_date) {
		if (customer_goods_receipt_date == null || customer_goods_receipt_date.isEmpty()) {
			this.customer_goods_receipt_date = null;
		} else {
			this.customer_goods_receipt_date = customer_goods_receipt_date;
		}
	}

	public String getExpiry_date() {
		return expiry_date;
	}

	public void setExpiry_date(String expiry_date) {
		if (expiry_date == null || expiry_date.isEmpty()) {
			this.expiry_date = null;
		} else {
			this.expiry_date = expiry_date;
		}

	}

}
