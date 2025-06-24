package com.erp.FinishGoods.SmProductFg.Model;

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
@Subselect("select * from smv_product_fg_details")
public class CSmProductFgDetailsViewModel {

	@Id
	private Integer product_id;
	private String product_fg_id;
	private String company_name;
	private String company_branch_name;
	private String product_fg_code;
	private String product_fg_name;
	private String product_fg_technical_name;
	private String product_type_group;
	private String product_type_name;
	private String product_category1_name;
	private String product_fg_short_name;
	private String product_fg_print_name;
	private String product_fg_tech_spect;
	private String product_fg_oem_part_code;
	private String product_fg_our_part_code;
	private String product_fg_hsn_sac_code;
	private Double product_fg_hsn_sac_rate;
	private String product_fg_purchase_unit_name;
	private String product_fg_sales_unit_name;
	private String product_fg_stock_unit_name;
	private String product_fg_packing_name;
	private String product_fg_item_sr_no;
	private String product_fg_drawing_no;
	private String product_fg_model_no;
	private String product_fg_bar_code;
	private String product_fg_qr_code;
	private String product_consumption_mode;
	private String product_origin_type;
	private String product_origin_country;
	private String product_category1_short_name;
	private String product_type_short_name;
	private String product_fg_hsn_sac_type;
	private boolean product_fg_hsn_sac_is_exampted;
	private String product_fg_quantity_per_packing;
	private String product_fg_weight_per_packing;
	private String product_category2_name;
	private String product_category3_name;
	private String product_category4_name;
	private String product_category5_name;
	private String product_material_type_name;
	private String product_material_grade_name;
	private String product_material_shape_name;
	private String product_material_colour;
	private String assembly_scrap_percent;
	private String product_alternate_fg_name;
	private String product_fg_gross_weight;
	private String product_fg_net_weight;
	private String product_fg_std_weight;
	private String product_fg_volume;
	private Double product_fg_mrp;
	private Double product_fg_landed_price;
	private Double product_fg_avg_price;
	private Double product_fg_std_profit_percent;
	private Double product_fg_std_discount_percent;
	private Double product_fg_moq;
	private Double product_fg_mpq;
	private Double product_fg_mov;
	private Double product_fg_eoq;
	private Double product_fg_min_stock_level;
	private Double product_fg_max_stock_level;
	private Double product_fg_reorder_stock_level;
	private Double product_fg_depriciation_percent;
	private Double sales_tolerance_percent;
	private String product_fg_abc_analysis;
	private boolean qa_required;
	private boolean test_certificate_required;
	private String remark;
	private String bom_Applicable;
	// Added by mohit and use for db 1.1
	private String Active;
	private String Deleted;
	// Added by mohit and use for db 1.1
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
	private Integer product_fg_hsn_sac_code_id;
	private Integer product_fg_purchase_unit_id;
	private Integer product_fg_sales_unit_id;
	private Integer product_fg_stock_unit_id;
	private Integer product_fg_packing_id;
	private Integer product_fg_category1_id;
	private Integer product_fg_category2_id;
	private Integer product_fg_category3_id;
	private Integer product_fg_category4_id;
	private Integer product_fg_category5_id;
	private Integer product_material_type_id;
	private Integer product_material_grade_id;
	private Integer product_material_shape_id;
	private Integer product_alternate_fg_id;
	private Integer product_fg_type_id;
	private Integer field_name;
	private Integer field_id;

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
