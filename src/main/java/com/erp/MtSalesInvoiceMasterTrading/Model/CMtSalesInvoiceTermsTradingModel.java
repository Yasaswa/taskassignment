package com.erp.MtSalesInvoiceMasterTrading.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "ft_sales_invoice_terms_trading")
public class CMtSalesInvoiceTermsTradingModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer sales_invoice_terms_transaction_id;

	private Integer company_id;

	private Integer company_branch_id;

	private Integer sales_invoice_master_transaction_id;

	private Integer sales_invoice_details_transaction_id;

	private String sales_invoice_no;

	private Date sales_invoice_date;

	private Integer sales_invoice_version;

	private Integer sales_invoice_terms_parameters_id;

	private String sales_invoice_terms_parameters_name;

	private String sales_invoice_terms_parameters_value;

	private String sales_invoice_terms_parameters_expected_value;

	private String remark;

	private boolean is_active = Boolean.TRUE;

	private boolean is_delete = Boolean.FALSE;

	@Column(name = "created_by", updatable = false)
	private String created_by;

	@CreationTimestamp
	@Column(name = "created_on", updatable = false)
	private Date created_on;

	private String modified_by;

	private Date modified_on;

	private String deleted_by;

	private Date deleted_on;


}
