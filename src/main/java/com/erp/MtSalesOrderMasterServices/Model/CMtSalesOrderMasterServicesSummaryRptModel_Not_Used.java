package com.erp.MtSalesOrderMasterServices.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Immutable
@Subselect("select * from  mtv_sales_order_master_services_summary_rpt")
public class CMtSalesOrderMasterServicesSummaryRptModel_Not_Used {

	@Id
	private String sales_order_master_transaction_id;
	private String company_name;
	private String company_branch_name;
	private String sales_order_creation_type;
	private String financial_year;
	private String sales_order_type;
	private String sales_order_life;
	private String sales_order_no;
	private String sales_order_date;
	private String sales_order_version;
	private String customer_name;
	private String customer_email;
	private String customer_country_name;
	private String customer_state_name;
	private String customer_city_name;
	private String customer_district_name;
	private String consignee_name;
	private String consignee_country_name;
	private String consignee_state_name;
	private String consignee_city_name;
	private String consignee_district_name;
	private String customer_order_no;
	private String customer_order_Date;
	private String department_name;
	private String approved_by_name;
	private String approved_date;
	private String overall_schedule_date;
	private String basic_total;
	private String transport_amount;
	private String freight_amount;
	private String packing_amount;
	private String discount_percent;
	private String discount_amount;
	private String other_amount;
	private String taxable_total;
	private String cgst_percent;
	private String cgst_total;
	private String sgst_percent;
	private String sgst_total;
	private String igst_percent;
	private String igst_total;
	private String roundoff;
	private String grand_total;
	private String agent_name;
	private String agent_percent;
	private String agent_paid_status;
	private String sales_order_acceptance_status;
	private String sales_order_mail_sent_status;
	private String status_remark;
	private String sales_quotation_no;
	private String sales_quotation_date;
	private String other_terms_conditions;
	private String sales_order_status;
	private String sales_order_status_desc;
	private String sales_order_acceptance_status_desc;
	private String sales_order_mail_sent_status_desc;
	private String remark;
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
	private String sales_order_type_id;
	private String customer_id;
	private String customer_state_id;
	private String customer_city_id;
	private String consignee_id;
	private String consignee_state_id;
	private String consignee_city_id;
	private String department_id;
	private String approved_by_id;
	private String agent_id;


}
