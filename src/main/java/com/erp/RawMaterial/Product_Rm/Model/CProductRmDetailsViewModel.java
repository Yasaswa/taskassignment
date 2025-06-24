package com.erp.RawMaterial.Product_Rm.Model;

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
@Subselect("select * from smv_product_rm_details")
public class CProductRmDetailsViewModel {

	@Id
	private Integer product_rm_id;
	private String company_name;
	private String company_branch_name;
	private String product_rm_code;
	private String product_rm_name;
	private String product_rm_technical_name;
	private String product_rm_print_name;
	private String product_rm_tech_spect;
	private String product_rm_oem_part_code;
	private String product_rm_our_part_code;
	private String product_type_group;
	private String product_type_name;
	private String product_category1_name;
	private String product_rm_short_name;
	private String product_rm_hsn_sac_code;
	private double product_rm_hsn_sac_rate;
	private String product_rm_purchase_unit_name;
	private String product_rm_sales_unit_name;
	private String product_rm_stock_unit_name;
	private String product_rm_packing_name;
	private String product_rm_item_sr_no;
	private String product_rm_drawing_no;
	private String product_rm_model_no;
	private String product_rm_bar_code;
	private String product_rm_qr_code;
	private String product_consumption_mode;
	private String product_origin_type;
	private String product_origin_country;

	//Remove this field (Testing 1.1)
	private String product_category1_short_name;
	//Remove this field (Testing 1.1)

	private String product_type_short_name;
	private String product_rm_hsn_sac_type;
	private Boolean product_rm_hsn_sac_is_exampted = Boolean.FALSE;
	private double product_rm_quantity_per_packing;
	private double product_rm_weight_per_packing;
	private String product_category2_name;
	private String product_category3_name;
	private String product_category4_name;
	private String product_category5_name;
	private String product_make_name;
	private double product_rm_moq;
	private double product_rm_mpq;
	private double product_rm_mov;
	private double product_rm_eoq;
	private double product_rm_min_stock_level;
	private double product_rm_max_stock_level;
	private double product_rm_reorder_stock_level;
	private double product_rm_depriciation_percent;
	private double purchase_tolerance_percent;

	//Added By Dipti (ERP DB Testing 1.1)
//	private Integer lead_time;
	//Added By Dipti (ERP DB Testing 1.1)

	private String qa_required;
	private String test_certificate_required;
	private String product_material_type_name;
	private String product_material_grade_name;
	private String product_material_shape_name;
	private String product_material_colour;
	private String assembly_scrap_percent;
	private String product_alternate_rm_name;
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
	private Integer product_id;

	//Added By Dipti (ERP DB Testing 1.1)
//	private String Active;
//	private String Deleted;
	//Added By Dipti (ERP DB Testing 1.1)

	private boolean is_active = Boolean.TRUE;
	private boolean is_delete = Boolean.FALSE;
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
	private Integer product_category2_id;
	private Integer product_category3_id;
	private Integer product_category4_id;
	private Integer product_category5_id;
	private Integer product_make_id;
	private Integer product_material_type_id;
	private Integer product_material_grade_id;
	private Integer product_material_shape_id;
	private Integer product_alternate_rm_id;
	private Integer godown_id;
	private Integer godown_section_id;
	private Integer godown_section_beans_id;
	private String field_name;
	private Integer field_id;
	private String profit_center_name;
	private String cost_center_name;
	private String routing_code;
	private Integer profit_center_id;
	private Integer cost_center_id;


}
