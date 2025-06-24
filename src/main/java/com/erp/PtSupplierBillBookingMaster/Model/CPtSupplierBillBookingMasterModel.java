package com.erp.PtSupplierBillBookingMaster.Model;

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
@Table(name = "ft_supplier_bill_booking_master")
public class CPtSupplierBillBookingMasterModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "supplier_bill_booking_master_transaction_id")
	private int supplier_bill_booking_master_transaction_id;
	private String supplier_bill_booking_creation_type;
	private Integer company_id;
	private Integer company_branch_id;
	private String financial_year;
	private String supplier_bill_booking_no;
	private String supplier_bill_booking_date;
	private Integer supplier_bill_booking_version;
	private Integer supplier_bill_booking_type_id;
	private String supplier_bill_booking_type;
	private Integer supplier_id;
	private String supplier_contacts_ids;
	private Integer expected_branch_id;
	private Integer approved_by_id;
	private String approved_date;
	private Integer finance_approved_by_id;
	private String finance_approved_date;
	private String supplier_bill_booking_status;
	private double total_amount;
	private Integer tax1_id;
	private double tax1_percent;
	private double tax1_amount;
	private Integer tax2_id;
	private double tax2_percent;
	private double tax2_amount;
	private double deduction_amount;
	private double payable_amount;
	private String deduction_remark;
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

	public String getFinance_approved_date() {
		return finance_approved_date;
	}

	public void setFinance_approved_date(String finance_approved_date) {
		if (finance_approved_date == null || finance_approved_date.isEmpty()) {
			this.finance_approved_date = null;
		} else {
			this.finance_approved_date = finance_approved_date;
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
