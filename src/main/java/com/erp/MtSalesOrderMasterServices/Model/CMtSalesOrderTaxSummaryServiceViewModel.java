package com.erp.MtSalesOrderMasterServices.Model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Immutable
@Subselect("select * from mtv_sales_order_tax_summary_services")
public class CMtSalesOrderTaxSummaryServiceViewModel {
	
	@Id
	private int sales_order_tax_summary_transaction_id;
	private String financial_year;
	private String sales_order_type;
	private String sales_order_no;
	private String sales_order_date;
	private Integer sales_order_version;
	private double summary_taxable_amount;
	private double summary_cgst_percent;
	private double summary_cgst_total;
	private double summary_sgst_percent;
	private double summary_sgst_total;
	private double summary_igst_percent;
	private double summary_igst_total;
	private double summary_total_amount;
	private String hsn_sac_type;
	private String hsn_sac_code;
	private String hsn_sac_description;
	private String hsn_sac_rate;
	private String remark;
	private boolean is_active;
	private boolean is_delete;
	private String created_by;
	@CreationTimestamp
	@Column(name = "created_on", updatable = false)
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;
	private Integer expected_branch_id;
	private Integer expected_branch_state_id;
	private Integer expected_branch_city_id;
	private Integer sales_order_master_transaction_id;
	private Integer company_id;
	private Integer company_branch_id;
	private Integer sales_order_type_id;
	private int hsn_code_id;
	
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
