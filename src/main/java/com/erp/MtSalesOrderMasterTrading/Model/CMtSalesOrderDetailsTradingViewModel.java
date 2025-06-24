package com.erp.MtSalesOrderMasterTrading.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Immutable
@Subselect("select * from  mtv_sales_order_details_trading")
public class CMtSalesOrderDetailsTradingViewModel {

	@Id
	private int sales_order_details_transaction_id;
	private String company_branch_name;
	private Integer sales_order_master_transaction_id;
	private String company_name;
	private String sales_order_no;
	private String sales_order_date;
	private Integer sales_order_version;
	private String sales_order_life_desc;
	private String sales_order_creation_type_desc;
	private String customer_order_no;
	private String customer_order_Date;
	private String product_type;
	private String product_type_group;
	private String product_type_short_name;
	private String product_material_name;
	private String product_material_tech_spect;
	private String product_material_stock_unit_name;
	private Double product_material_std_weight;
	private String product_material_technical_name;
	private String product_material_make_name;
	private String product_material_type_name;
	private String product_material_grade_name;
	private String product_material_shape_name;
	private String product_material_oem_part_code;
	private String product_material_our_part_code;
	private String product_material_print_name;
	private String product_material_packing_name;
	private String product_material_hsn_sac_code;
	private Double product_material_hsn_sac_rate;
	private Double so_rate;
	private Integer sr_no;
	private String so_sr_no;
	private Integer product_material_unit_id;
	private Integer product_material_packing_id;
	private Integer product_material_hsn_code_id;
	private Double material_quantity;
	private Double material_weight;
	private Double quotation_rate;
	private Double material_rate;
	private Double material_basic_amount;
	private Double material_freight_amount;
	private Double material_discount_percent;
	private Double material_discount_amount;
	private Double material_taxable_amount;
	private Double material_cgst_percent;
	private Double material_cgst_total;
	private Double material_sgst_percent;
	private Double material_sgst_total;
	private Double material_igst_percent;
	private Double material_igst_total;
	private Double material_total_amount;
	private Double production_issue_quantity;
	private Double production_issue_weight;
	private Double production_issue_return_quantity;
	private Double production_issue_return_weight;
	private Double production_issue_rejection_quantity;
	private Double production_issue_rejection_weight;
	private String material_schedule_date;
	private String product_recommendation;	// Added For Fabric SO;
	private String special_remark;	// Added For Fabric SO;
	private boolean monogram_is_applicable = Boolean.FALSE;	// Added For Fabric SO;
	private String monogram_is_applicable_desc;	// Added For Fabric SO;
	private String weft_color;	// Added for Fabric SO;
	private String material_style;	// Added for Fabric SO;
	private String material_style_value;	// Added for Fabric SO;
	private String material_style_abbrevation;	// Added for Fabric SO;
	private String sales_order_item_status;
	private String sales_order_item_status_desc;
	private String sales_order_mail_sent_status;
	private String sales_order_mail_sent_status_desc;
	private String sales_order_life;
	private String sales_order_creation_type;
	private String customer_name;
	private String customer_email;
	private String customer_state_name;
	private String customer_city_name;
	private String customer_district_name;
	private String consignee_name;
	private String consignee_state_name;
	private String consignee_city_name;
	private String consignee_district_name;
	private String department_name;
	private String approved_by_name;
	private String approved_date;
	private String overall_schedule_date;
	private Double basic_total;
	private Double transport_amount;
	private Double freight_amount;
	private Double packing_amount;
	private Double discount_percent;
	private Double discount_amount;
	private Double other_amount;
	private Double taxable_total;
	private Double cgst_percent;
	private Double cgst_total;
	private Double sgst_percent;
	private Double sgst_total;
	private Double igst_percent;
	private Double igst_total;
	private Double roundoff;
	private Double grand_total;
	private String sales_quotation_no;
	private String sales_quotation_date;
	private String other_terms_conditions;
	private Integer company_id;
	private Integer company_branch_id;
	private Integer product_type_id;
	private String product_material_id;
	private Integer customer_id;
	private String remark;
	private String company_address1;
	private String company_address2;
	private String company_phone_no;
	private String company_cell_no;
	private String company_EmailId;
	private String company_website;
	private String company_gst_no;
	private String company_pan_no;
	private String company_state;
	private String company_pincode;
	private Boolean is_active;
	private Boolean is_delete;
	private String created_by;
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private Date deleted_on;
	private String deleted_by;
	private String dispatch_note_nos;
	private String dispatch_challan_nos;
	private String invoice_nos;
	private Double previous_dispatch_quantity;
	private Double previous_dispatch_weight;
	private Double stock_quantity;
	private Double Stock_weight;
	private String financial_year;
	private Integer dispatched_quantity;
	private Integer dispatched_weight;
//	private Integer production_count_id;
//	private String production_count_name;
	private String product_material_code;
	private String set_no;
	private Double cancel_quantity;
	private Double cancel_weight;
	private double pree_closed_quantity;
	private double pree_closed_weight;
	private double pending_weight;
	private double pending_quantity;
	private String sales_order_status_desc;
	private String sales_order_status;
	public Boolean getIs_active() {
		return is_active;
	}
	public void setIs_active(Boolean is_active) {
		this.is_active = is_active;
	}
	public Boolean getIs_delete() {
		return is_delete;
	}
	public void setIs_delete(Boolean is_delete) {
		this.is_delete = is_delete;
	}


	private double warp_crimp;
	private double weft_crimp;
	private double warp_waste;
	private double weft_waste;
	private double epi_1;
	private double ppi_1;
	private double width;
	private double warp_count_1__wc1__actual_count;
	private double warp_count_2__wc2__actual_count;
	private double warp_count_3__wc3__actual_count 	;
	private double warp_count_4__wc4__actual_count;
	private double weft_count_1__fc1__actual_count;
	private double weft_count_2__fc2__actual_count;
	private double weft_count_3__fc3__actual_count;
	private double weft_count_4__fc4__actual_count;
}
