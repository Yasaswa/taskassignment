package com.erp.MtSalesInvoiceMasterTrading.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "ft_sales_invoice_tax_summary")
public class CMtSalesInvoiceTaxSummaryModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sales_invoice_tax_summary_transaction_id")
	private int sales_invoice_tax_summary_transaction_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String financial_year;
	private Integer sales_invoice_type_id;
	private String sales_invoice_type;
	private Integer sales_invoice_master_transaction_id;
	private Integer customer_id;
	private Integer customer_state_id;
	private Integer customer_city_id;
	private String customer_contacts_ids;
	private Integer expected_branch_id;
	private Integer expected_branch_state_id;
	private Integer expected_branch_city_id;
	private String sales_invoice_no;
	private String sales_invoice_date;
	private Integer sales_invoice_version;
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
	private String created_by;
	@CreationTimestamp
	@Column(name = "created_on", updatable = false)
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;


	public String getSales_invoice_date() {
		return sales_invoice_date;
	}

	public void setSales_invoice_date(String sales_invoice_date) {
		if (sales_invoice_date == null || sales_invoice_date.isEmpty()) {
			this.sales_invoice_date = null;
		} else {
			this.sales_invoice_date = sales_invoice_date;
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
