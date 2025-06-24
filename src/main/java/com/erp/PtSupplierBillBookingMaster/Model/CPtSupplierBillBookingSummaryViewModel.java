package com.erp.PtSupplierBillBookingMaster.Model;

import com.google.errorprone.annotations.Immutable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Immutable
@Entity
@Subselect("select * from ftv_supplier_bill_booking_summary")
public class CPtSupplierBillBookingSummaryViewModel {

	@Id
	private int supplier_bill_booking_master_transaction_id;
	private String supplier_bill_booking_creation_type;
	private String supplier_bill_booking_creation_type_desc;
	private String supplier_bill_booking_no;
	private String supplier_bill_booking_date;
	private Integer supplier_bill_booking_version;
	private String supplier_bill_booking_type;
	private String supplier_bill_booking_status_desc;
	private String supplier_contacts_ids;
	private double approved_by_name;
	private String finanace_by_name;
	private String approved_date;
	private String finance_approved_date;
	private double total_amount;
	private String taxtype_name1;
	private double tax1_percent;
	private double tax1_amount;
	private String taxtype_name2;
	private double tax2_percent;
	private double tax2_amount;
	private double deduction_amount;
	private double payable_amount;
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
	private Integer supplier_bill_booking_type_id;
	private Integer finance_approved_by_id;
	private Integer supplier_id;
	private Integer approved_by_id;
	private Integer expected_branch_id;
	private String product_type_short_name;
}
