package com.erp.PtSupplierBillBookingMaster.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "ft_purchase_bill_tax_summary")
public class CPtPurchaseBillTaxSummaryModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "purchase_bill_tax_summary_transaction_id")
	private int purchase_bill_tax_summary_transaction_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String financial_year;
	private Integer purchase_bill_type_id;
	private String purchase_bill_type;
	private Integer supplier_bill_booking_master_transaction_id;
	private Integer supplier_id;
	private Integer supplier_state_id;
	private Integer supplier_city_id;
	private String supplier_contacts_ids;
	private Integer expected_branch_id;
	private Integer expected_branch_state_id;
	private Integer expected_branch_city_id;
	private String supplier_bill_booking_no;
	private String supplier_bill_booking_date;
	private Integer supplier_bill_booking_version;
	private Integer hsn_code_id;
	private double summary_taxable_amount;
	private double summary_cgst_percent;
	private double summary_cgst_total;
	private double summary_sgst_percent;
	private double summary_sgst_total;
	private double summary_igst_percent;
	private double summary_igst_total;
	private double summary_total_amount;
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

	public String getSupplier_bill_booking_date() {
		return supplier_bill_booking_date;
	}

	public void setSupplier_bill_booking_date(String supplier_bill_booking_date) {

		if (supplier_bill_booking_date == null || supplier_bill_booking_date.isEmpty()) {
			this.supplier_bill_booking_date = null;
		} else {
			this.supplier_bill_booking_date = supplier_bill_booking_date;
		}
	}


}
