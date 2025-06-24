package com.erp.MtQuotationDetails.Model;

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
@Subselect("select * from mtv_sales_quotation_trading_summary")
public class CMtSalesQuotationMasterTradingSummaryViewModel {

	@Id
	private int quotation_master_transaction_id;
	private String financial_year;
	private String quotation_type;
	private String quotation_transaction_type_desc;
	private String quotation_no;
	private Integer quotation_version;
	private String quotation_date;
	private String expected_branch_name;
	private String department_name;
	private String customer_name;
	private String customer_state_name;
	private String quotation_by_name;
	private String enquiry_by_name;
	private String assign_to_head_name;
	private String assign_to_name;
	private String approved_by_name;
	private String agent_name;
	private double agent_percent;
	private double quotation_material_cost;
	private double quotation_process_cost;
	private double quotation_labour_cost;
	private double quotation_other_cost;
	private String quotation_other_cost_remark;
	private double quotation_administration_percent;
	private double quotation_administration_cost;
	private double quotation_profit_percent;
	private double quotation_profit_cost;
	private double quotation_discount_percent;
	private double quotation_discount_cost;
	private double quotation_taxable_cost;
	private double quotation_cgst_cost;
	private double quotation_sgst_cost;
	private double quotation_igst_cost;
	private double quotation_total_cost;
	private String overall_schedule_date;
	private String tax_applicable_desc;
	private String tax_applicable;
	private String transportation_applicable_desc;
	private String transportation_applicable;
	private String bom_applicable_desc;
	private String bom_applicable;
	private String costing_applicable_desc;
	private String costing_applicable;
	private String quotation_life_desc;
	private String quotation_status_desc;
	private String quotation_status;
	private String quotation_mail_sent_status_desc;
	private String approved_date;
	private String quotation_type_short_name;
	private String expected_branch_address1;
	private String expected_branch_pincode;
	private String expected_branch_city_name;
	private String expected_branch_district_name;
	private String expected_branch_state_name;
	private String expected_branch_country_name;
	private String expected_branch_phone_no;
	private String expected_branch_EmailId;
	private String status_remark;
	private String company_name;
	private String company_branch_name;
	private String other_terms_conditions;
	private String remark;
	private String quotation_life;
	private String quotation_transaction_type;
	private String quotation_mail_sent_status;
	private String customer_email;
	private String customer_country_name;
	private String customer_city_name;
	private String customer_district_name;
	private boolean is_active;
	private boolean is_delete;
	private String created_by;
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;
	private Integer company_id;
	private Integer department_id;
	private Integer company_branch_id;
	private Integer quotation_type_id;
	private Integer customer_id;
	private String customer_contacts_ids;
	private Integer customer_state_id;
	private Integer customer_city_id;
	private Integer expected_branch_id;
	private Integer expected_branch_state_id;
	private Integer expected_branch_city_id;
	private Integer quotation_by_id;
	private Integer enquiry_by_id;
	private Integer assign_to_head_id;
	private Integer assign_to_id;
	private Integer approved_by_id;
	private Integer agent_id;
	private Integer field_id;
	private String field_name;

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
