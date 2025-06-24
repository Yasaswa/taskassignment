package com.erp.SmProductPr.Model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import com.erp.SmProductFgStockDetails.Model.CSmProductFgCustomerStockDetailsHistoryViewModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Immutable
@Subselect("select * from smv_product_pr")
public class CSmProductPrViewModel {

	
	@Id
	private Integer product_id;
	private String product_pr_id;
	private String product_pr_code;
	private String project_name;
	private String project_reference_number;
	private String project_start_date;
	private String project_end_date;
	private String product_pr_name;
	private String product_type_name;
	private String product_category1_name;
	private String product_pr_short_name;
	private String product_pr_print_name;
	private String product_pr_tech_spect;
	private String product_pr_oem_part_code;
	private String product_pr_our_part_code;
	private String product_pr_hsn_sac_code;
	private Double product_pr_hsn_sac_rate;
	private String product_pr_purchase_unit_name;
	private String product_pr_sales_unit_name;
	private String product_pr_stock_unit_name;
	private String product_pr_packing_name;
	private String product_pr_item_sr_no;
	private String product_pr_drawing_no;
	private String product_pr_model_no;
	private String product_pr_bar_code;
	private String product_pr_qr_code;
	private String godown_name;
	private String product_consumption_mode;
	private String product_origin_type;
	private String product_origin_country;
	private String product_pr_hsn_sac_type;
	private Boolean product_pr_hsn_sac_is_exampted;
	private double product_pr_quantity_per_packing;
	private double product_pr_weight_per_packing;
	private String godown_short_name;
	private String godown_section_name;
	private String godown_section_beans_name;
	private String bom_applicable;
	private String remark;
	private String company_name;
	private String company_branch_name;
	private String product_type_group;
	private String product_type_short_name;
	private String Active;
	private String Deleted;
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
	private Integer product_type_id;
	private Integer product_category1_id;
	private Integer product_pr_hsn_sac_code_id;
	private Integer product_pr_purchase_unit_id;
	private Integer product_pr_sales_unit_id;
	private Integer product_pr_stock_unit_id;
	private Integer product_pr_packing_id;
	private Integer godown_id;
	private Integer godown_section_id;
	private Integer godown_section_beans_id;
	private String field_name;
	private Integer field_id;
//	private Integer profit_center_id;
//	private Integer cost_center_id;
	
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
