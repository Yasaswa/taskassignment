package com.erp.PtSupplierBillBookingMaster.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Immutable
@Subselect(value = "Select * From ptv_supplier_bill_booking_details_rpt")
public class CPtSupplierBillBookingDetailsRptModel_Not_Used {

	@Id
	private String supplier_bill_booking_details_transaction_id;
	private String supplier_bill_booking_creation_type;
	private String supplier_bill_booking_creation_type_desc;
	private String supplier_name;
	private String supplier_bill_booking_no;
	private String supplier_bill_booking_date;
	private String supplier_bill_booking_version;
	private String supplier_bill_booking_type;
	private String supplier_bill_booking_status;
	private String supplier_bill_item_booking_status_desc;
	private String total_amount;
	private String taxtype_name1;
	private String tax1_percent;
	private String tax1_amount;
	private String taxtype_name2;
	private String tax2_percent;
	private String tax2_amount;
	private String deduction_amount;
	private String payable_amount;
	private String goods_receipt_no;
	private String goods_receipt_date;
	private String goods_receipt_version;
	private String purchase_order_no;
	private String purchase_order_date;
	private String purchase_order_version;
	private String supplier_invoice_no;
	private String supplier_invoice_date;
	private String supplier_challan_no;
	private String supplier_challan_date;
	private String expected_schedule_date;
	private String payment_due_date;
	private String basic_total;
	private String freight_amount;
	private String packing_amount;
	private String discount_amount;
	private String other_amount;
	private String taxable_total;
	private String cgst_total;
	private String sgst_total;
	private String igst_total;
	private String roundoff;
	private String approved_by_name;
	private String finanace_by_name;
	private String approved_date;
	private String deduction_remark;
	private String supp_branch_phone_no;
	private String supp_branch_EmailId;
	private String supp_branch_address1;
	private String supp_branch_pincode;
	private String supp_branch_gst_no;
	private String supp_branch_pan_no;
	private String remark;
	private String company_name;
	private String company_branch_name;
	private String company_pincode;
	private String company_phone_no;
	private String company_branch_state;
	private String company_branch_city;
	private String company_cell_no;
	private String company_EmailId;
	private String company_gst_no;
	private String company_pan_no;
	private String company_address1;
	private String company_address2;
	private String financial_year;
	private String is_active;
	private String is_delete;
	private String created_by;
	private String created_on;
	private String modified_by;
	private String modified_on;
	private String deleted_by;
	private String deleted_on;
	private String company_id;
	private String company_branch_id;
	private String supplier_bill_booking_master_transaction_id;
	private String supplier_bill_booking_type_id;
	private String supplier_bill_item_booking_status;
	private String finance_approved_by_id;
	private String supplier_id;
	private String approved_by_id;
	private String expected_branch_id;

}
