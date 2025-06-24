package com.erp.MtSalesInvoiceMasterTrading.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Immutable
@Subselect("select * from ftv_sales_invoice_tax_summary")
public class CMtSalesInvoiceTaxSummaryViewModel {

	@Id
	private int sales_invoice_tax_summary_transaction_id;
	private String company_branch_name;
	private String sales_invoice_no;
	private String sales_invoice_date;
	private Integer sales_invoice_version;
	private String customer_name;
	private String customer_state_name;
	private String customer_gst_no;
	private Integer hsn_code_id;
	private String hsn_sac_code;
	private double hsn_sac_rate;
	private double summary_taxable_amount;
	private double summary_cgst_percent;
	private double summary_cgst_total;
	private double summary_sgst_percent;
	private double summary_sgst_total;
	private double summary_igst_percent;
	private double summary_igst_total;
	private double summary_total_amount;
	private String remark;
	private String company_name;
	private String financial_year;
	private String customer_email;
	private String customer_city_name;
	private boolean is_active;
	private boolean is_delete;
	private String created_by;
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;
	private Integer company_id;
	private Integer sales_invoice_master_transaction_id;
	private Integer company_branch_id;
	private Integer expected_branch_id;
	private Integer customer_id;
	private String customer_contacts_ids;
	private Integer customer_state_id;
	private Integer customer_city_id;
}
