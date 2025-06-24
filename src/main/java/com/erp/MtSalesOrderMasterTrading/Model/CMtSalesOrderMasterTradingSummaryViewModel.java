package com.erp.MtSalesOrderMasterTrading.Model;

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
@Subselect("select * from  mtv_sales_order_master_trading_summary")
public class CMtSalesOrderMasterTradingSummaryViewModel {

	@Id
	private int sales_order_master_transaction_id;
	private String company_name;
	private String company_branch_name;
	private String financial_year;
	private String sales_order_type;
	private String sales_order_life_desc;
	private String sales_order_creation_type_desc;
	private String sales_order_life;
	private String sales_order_creation_type;
	private String sales_order_no;
	private String sales_order_date;
	private Integer sales_order_version;
	private String sales_quotation_no;
	private String sales_quotation_date;
	private String customer_name;
	private String customer_email;
	private String customer_state_name;
	private String customer_city_name;
	private String customer_district_name;
	private String consignee_name;
	private String consignee_state_name;
	private String job_type;
	private String consignee_city_name;
	private String consignee_district_name;
	private String customer_order_no;
	private String customer_order_Date;
	private String department_name;
	private String sub_department_name;
	private String approved_by_name;
	private String approved_date;
	private String overall_schedule_date;
	private String hsn_sac_type;
	private String hsn_sac_code;
	private String hsn_sac_description;
	private String product_type_short_name;
	private boolean is_freight_taxable;
	private double hsn_sac_rate;
	private double basic_total;
	private double transport_amount;
	private double freight_amount;
	private double packing_amount;
	private double discount_percent;
	private double discount_amount;
	private double other_amount;
	private double taxable_total;
	private double cgst_percent;
	private double cgst_total;
	private double sgst_percent;
	private double sgst_total;
	private double igst_percent;
	private double igst_total;
	private double roundoff;
	private double grand_total;
	private String agent_name;
	private double agent_percent;
	private String agent_paid_status;
	private String agent_paid_status_desc;
	private String sales_order_acceptance_status;
	private String sales_order_mail_sent_status;
	private String sales_order_mail_sent_status_desc;
	private String status_remark;
	private String other_terms_conditions;
	private String sales_order_status;
	private String remark;
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
	private Integer freight_hsn_code_id;
	private Integer sales_order_type_id;
	private Integer customer_id;
	private Integer customer_state_id;
	private Integer customer_city_id;
	private Integer consignee_id;
	private Integer consignee_state_id;
	private Integer consignee_city_id;
	private Integer department_id;
	private Integer approved_by_id;
	private Integer agent_id;
	private String customer_contacts_ids;
	private Integer sub_department_id;
	private Integer supplier_branch_id;
	private String lot_no;
	
	public boolean isIs_freight_taxable() {
		return is_freight_taxable;
	}
	public void setIs_freight_taxable(boolean is_freight_taxable) {
		this.is_freight_taxable = is_freight_taxable;
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
