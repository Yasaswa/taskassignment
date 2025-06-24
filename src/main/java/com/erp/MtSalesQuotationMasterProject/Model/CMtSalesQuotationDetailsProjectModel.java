package com.erp.MtSalesQuotationMasterProject.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(name = "mt_sales_quotation_details_project")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CMtSalesQuotationDetailsProjectModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "quotation_details_transaction_id")
	private int quotation_details_transaction_id;
	private Integer quotation_master_transaction_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String financial_year;
	private Integer quotation_type_id;
	private String quotation_type;
	private String quotation_no;
	private String quotation_date;
	private Integer quotation_version;
	private Integer department_id;
	private String enquiry_no;
	private String enquiry_date;
	private Integer enquiry_version;
	private Integer sr_no;
	private String product_material_id;
	private String product_type_short_name;
	private Integer hsn_sac_id;
	private double hsn_sac_percent;
	@Transient
	private String product_material_name;    // This field is not present in the database.
	private String product_material_tech_spect;
	private Integer product_material_unit_id;
	private Integer product_material_packing_id;
	private double product_material_quotation_quantity;
	private double product_material_quotation_weight;
	private double product_material_moq_quantity;
	private String product_material_notes;
	private double product_material_conversion_factor;
	private Integer expected_lead_time;
	private String material_expected_schedule_date;
	private String product_fg_bom_no;
	private Integer product_fg_bom_version;
	private String product_costing_no;
	private double item_material_cost;
	private double item_process_cost;
	private double item_labour_cost;
	private double item_administration_percent;
	private double item_administration_cost;
	private double item_profit_percent;
	private double item_profit_cost;
	private double item_other_cost;
	private String item_other_cost_remark;
	private double item_discount_percent;
	private double item_discount_cost;
	private double item_taxable_cost;
	private double item_cgst_percent;
	private double item_cgst_cost;
	private double item_sgst_percent;
	private double item_sgst_cost;
	private double item_igst_percent;
	private double item_igst_cost;
	private double item_total_cost;
	private double material_std_rate;
	private String quotation_item_status;
	private String material_item_approval_remark;
	private String item_dispatch_note_nos;
	private String item_dispatch_challan_nos;
	private String item_invoice_nos;
	private String item_terms_conditions;
	private String item_remark;
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
