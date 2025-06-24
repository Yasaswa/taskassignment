package com.erp.RawMaterial.Product_Rm.Model;

import lombok.*;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Immutable
@Table(name = "smv_product_rm")
public class CProductRmViewModel {

	@Id
	private int product_id;
	private String product_rm_id;
	private String company_name;
	private String company_branch_name;
	private String product_rm_code;
	private String product_rm_name;
	private String product_type_group;
	private String product_type_name;
	private String product_category1_name;
	private String product_rm_short_name;
	private String product_rm_print_name;
	private String product_rm_tech_spect;
	private String product_rm_oem_part_code;
	private String product_rm_our_part_code;
	private String product_rm_drawing_no;
	private String product_rm_hsn_sac_code;
	private String product_rm_hsn_sac_rate;
	private String product_rm_purchase_unit_name;
	private String product_rm_sales_unit_name;
	private String product_rm_stock_unit_name;
	private String product_rm_packing_name;
	private String product_rm_item_sr_no;
	private String product_rm_model_no;
	private String product_rm_bar_code;
	private String bom_applicable;
	private String product_rm_qr_code;
	private String product_consumption_mode;
	private String product_origin_type;
	private String product_origin_country;
	private String product_category1_short_name;
	private String product_type_short_name;
	private String product_rm_hsn_sac_type;
	private boolean product_rm_hsn_sac_is_exampted = Boolean.FALSE;
	private String product_rm_quantity_per_packing;
	private String product_rm_weight_per_packing;
	private String godown_name;
	private String godown_short_name;
	private double godown_area;
	private String godown_section_name;
	private String godown_section_short_name;
	private Integer godown_section_count;
	private double godown_section_area;
	private String godown_section_beans_name;
	private String godown_section_beans_short_name;
	private double godown_section_beans_area;
	private String remark;
	//Added By Dipti (ERP DB Testing 1.1)
	private String Active;
	private String Deleted;
	//Added By Dipti (ERP DB Testing 1.1)
	private boolean is_active;
	private boolean is_delete;
	private String created_by;
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private Date deleted_on;
	private String deleted_by;
	private Integer company_id;
	private Integer company_branch_id;
	private Integer product_type_id;
	private Integer product_category1_id;
	private Integer product_rm_hsn_sac_code_id;
	private Integer product_rm_purchase_unit_id;
	private Integer product_rm_sales_unit_id;
	private Integer product_rm_stock_unit_id;
	private Integer product_rm_packing_id;
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
