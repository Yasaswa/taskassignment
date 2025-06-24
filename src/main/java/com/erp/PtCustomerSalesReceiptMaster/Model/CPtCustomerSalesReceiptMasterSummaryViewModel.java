package com.erp.PtCustomerSalesReceiptMaster.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Immutable
@Entity
@Subselect("SELECT * FROM ftv_customer_sales_receipt_master_summary")
public class CPtCustomerSalesReceiptMasterSummaryViewModel {

	@Id
	private int customer_sales_receipt_master_transaction_id;
	private String customer_name;
	private String customer_sales_receipt_no;
	private String customer_sales_receipt_date;
	private Integer customer_sales_receipt_version;
	private String customer_sales_receipt_type;
	private String customer_sales_receipt_status_desc;
	private String customer_receipt_mail_sent_status_desc;
	private String approved_by_name;
	private String cust_branch_phone_no;
	private String cust_branch_cell_no;
	private String cust_branch_EmailId;
	private String approved_date;
	private String bank_name;
	private String bank_branch_name;
	private String bank_account_no;
	private String bank_ifsc_code;
	private String receipt_type_desc;
	private String customer_bank_details;
	private String customer_bank_name;
	private String customer_bank_account_number;
	private String customer_bank_ifsc_code;
	private Double total_billing_amount;
	private Double total_billing_deduction;
	private String taxtype_name1;
	private Double tax1_percent;
	private Double tax1_amount;
	private String taxtype_name2;
	private Double tax2_percent;
	private Double tax2_amount;
	private Double total_receipt_amount;
	private String receipt_transaction_details;
	private String receipt_transaction_date;
	private String deduction_remark;
	private String expected_branch_name;
	private String expected_pincode;
	private String expected_phone_no;
	private String expected_branch_state;
	private String expected_branch_city;
	private String receipt_type;
	private String company_name;
	private String company_branch_name;
	private String financial_year;
	private String customer_receipt_mail_sent_status;
	private String customer_sales_receipt_status;
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
	private Integer customer_sales_receipt_type_id;
	private Integer customer_id;
	private String customer_contacts_ids;
	private Integer expected_branch_id;
	private Integer approved_by_id;
	private Integer bank_id;
	private Integer customer_bank_id;
	private Integer tax1_id;
	private Integer tax2_id;

}
