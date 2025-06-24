package com.erp.MtSalesQuotationMasterServices.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "mt_sales_quotation_master_services")
public class CMtSalesQuotationMasterServicesModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "quotation_master_transaction_id")
	private int quotation_master_transaction_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String financial_year;
	private Integer quotation_type_id;
	private String quotation_type;
	private String quotation_life;
	private String quotation_transaction_type;
	private String quotation_no;
	private String quotation_date;
	private Integer quotation_version;
	private Integer customer_id;
	private String customer_contacts_ids;
	private Integer department_id;
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
	private String approved_date;
	private String overall_schedule_date;
	private String tax_applicable;
	private String transportation_applicable;
	private String bom_applicable;
	private String costing_applicable;
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
	private Integer agent_id;
	private double agent_percent;
	private String quotation_mail_sent_status;
	private String quotation_status;
	private String status_remark;
	private String other_terms_conditions;
	private String remark;
	private boolean is_active = Boolean.TRUE;
	private boolean is_delete = Boolean.FALSE;
	private String created_by;
	@CreationTimestamp
	@Column(name = "created_on", updatable = false)
	private Date created_on;
	private String modified_by;
	@UpdateTimestamp
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;

	public String getQuotation_date() {
		return quotation_date;
	}

	public void setQuotation_date(String quotation_date) {
		if (quotation_date == null || quotation_date.isEmpty()) {
			this.quotation_date = null;
		} else {
			this.quotation_date = quotation_date;
		}
	}

	public String getApproved_date() {
		return approved_date;
	}

	public void setApproved_date(String approved_date) {
		if (approved_date == null || approved_date.isEmpty()) {
			this.approved_date = null;
		} else {
			this.approved_date = approved_date;
		}
	}

	public String getOverall_schedule_date() {
		return overall_schedule_date;
	}

	public void setOverall_schedule_date(String overall_schedule_date) {
		if (overall_schedule_date == null || overall_schedule_date.isEmpty()) {
			this.overall_schedule_date = null;
		} else {
			this.overall_schedule_date = overall_schedule_date;
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
