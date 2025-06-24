package com.erp.MtSalesQuotationMasterServices.Model;

import com.google.errorprone.annotations.Immutable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Immutable
@Subselect("Select * From mtv_sales_quotation_details_services")
public class CMtSalesQuotationDetailsServicesViewModel {

	@Id
	private int quotation_details_transaction_id;
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
	private Double agent_percent;
	private Double quotation_material_cost;
	private Double quotation_process_cost;
	private Double quotation_labour_cost;
	private Double quotation_other_cost;
	private String quotation_other_cost_remark;
	private Double quotation_administration_percent;
	private Double quotation_administration_cost;
	private Double quotation_profit_percent;
	private Double quotation_profit_cost;
	private Double quotation_discount_percent;
	private Double quotation_discount_cost;
	private Double quotation_taxable_cost;
	private Double quotation_cgst_cost;
	private Double quotation_sgst_cost;
	private Double quotation_igst_cost;
	private Double quotation_total_cost;
	private String overall_schedule_date;
	private String quotation_status_desc;
	private String enquiry_no;
	private String enquiry_date;
	private Integer enquiry_version;
	private Integer sr_no;
	private String product_type_short_name;
	private String product_type_name;
	private String product_sr_code;
	private String product_sr_name;
	private String product_sr_tech_spect;
	private String product_sr_std_hours;
	private String product_sr_hsn_sac_code;
	private Double product_sr_hsn_sac_rate;
	private Double hsn_sac_percent;
	private String product_material_tech_spect;
	private String product_material_print_name;
	private String process_duration;
	private Double product_material_quotation_quantity;
	private Double product_material_quotation_weight;
	private Double product_material_moq_quantity;
	private Double material_std_rate;
	private String product_material_notes;
	private Double product_material_conversion_factor;
	private Integer expected_lead_time;
	private String material_expected_schedule_date;
	private String product_fg_bom_no;
	private Integer product_fg_bom_version;
	private String product_costing_no;
	private Double item_material_cost;
	private Double item_process_cost;
	private Double item_labour_cost;
	private Double item_administration_percent;
	private Double item_administration_cost;
	private Double item_profit_percent;
	private Double item_profit_cost;
	private Double item_other_cost;
	private String item_other_cost_remark;
	private Double item_discount_percent;
	private Double item_discount_cost;
	private Double item_taxable_cost;
	private Double item_cgst_percent;
	private Double item_cgst_cost;
	private Double item_sgst_percent;
	private Double item_sgst_cost;
	private Double item_igst_percent;
	private Double item_igst_cost;
	private Double item_total_cost;
	private String quotation_item_status_desc;
	private String quotation_item_status;
	private String material_item_approval_remark;
	private String item_dispatch_note_nos;
	private String item_dispatch_challan_nos;
	private String item_invoice_nos;
	private String item_terms_conditions;
	private String item_remark;
	private String company_name;
	private String company_branch_name;
	private String company_address1;
	private String company_address2;
	private String company_cell_no;
	private String company_phone_no;
	private String company_EmailId;
	private String company_website;
	private String company_gst_no;
	private String company_pan_no;
	private String company_state;
	private String company_pincode;
	private String Active;
	private String Deleted;
	private boolean is_active;
	private boolean is_delete;
	private String created_by;
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private Date deleted_on;
	private Integer quotation_master_transaction_id;
	private Integer company_id;
	private Integer company_branch_id;
	private Integer quotation_type_id;
	private Integer department_id;
	private String product_material_id;
	private Integer hsn_sac_id;
	private Integer product_type_id;
	private Integer product_material_unit_id;
	private Integer product_material_packing_id;
	
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
