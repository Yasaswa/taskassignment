package com.erp.PtSupplierPurchasePaymentMaster.Model;

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
@Subselect("select * from ftv_supplier_purchase_payment_summary")
public class CPtSupplierPurchasePaymentMasterSummaryViewModel {

	@Id
	private int supplier_purchase_payment_master_transaction_id;
	private String supplier_name;
	private String supplier_purchase_payment_no;
	private String supplier_purchase_payment_date;
	private Integer supplier_purchase_payment_version;
	private String supplier_purchase_payment_type;
	private String supplier_purchase_payment_status_desc;
	private String purchase_payment_mail_sent_status_desc;
	private String approved_by_name;
	private Double total_billing_amount;
	private String taxtype_name1;
	private Double tax1_percent;
	private Double tax1_amount;
	private String taxtype_name2;
	private Double tax2_percent;
	private Double tax2_amount;
	private Double total_paid_amount;
	private Double total_billing_deduction;
	private String payment_type_desc;
	private String bank_name;
	private String bank_branch_name;
	private String bank_account_no;
	private String bank_ifsc_code;
	private String supp_branch_phone_no;
	private String supp_branch_EmailId;
	private String supp_branch_gst_no;
	private String supp_branch_pan_no;
	private String approved_date;
	private String supplier_bank_details;
	private String supplier_bank_name;
	private String supplier_bank_account_number;
	private String supplier_bank_ifsc_code;
	private String payment_transaction_details;
	private String payment_transaction_date;
	private String deduction_remark;
	private String supplier_purchase_payment_status;
	private String purchase_payment_mail_sent_status;
	private String payment_type;
	private String company_name;
	private String company_branch_name;
	private String expected_branch_name;
	private String expected_pincode;
	private String expected_phone_no;
	private String expected_branch_state;
	private String expected_branch_city;
	private String company_pincode;
	private String company_phone_no;
	private String company_branch_state;
	private String company_branch_city;
	private String financial_year;
	private String remark;
	private String Active;
	private String Deleted;
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
	private Integer supplier_id;
	private String supplier_contacts_ids;
	private Integer expected_branch_id;
	private Integer approved_by_id;
	private Integer bank_id;
	private Integer supplier_bank_id;
	private Integer tax1_id;
	private Integer tax2_id;
	private Integer supplier_purchase_payment_type_id;
	private String product_type_short_name;

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
