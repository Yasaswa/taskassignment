package com.erp.FinishGoods.SmProductFg.Model;

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
@Subselect("select * from smv_product_fg_summary")
public class CSmProductFgSummaryViewModel {

	@Id
	private String product_type_group;
	private String product_type_name;
	private String product_category1_name;
	private String product_fg_id;
	private String product_fg_code;
	private String product_fg_name;
	private String product_short_fg_name;
	private String product_fg_tech_spect;
	private String product_fg_stock_unit_name;
	private double stock_quantity;
	private double stock_weight;
	private double customer_stock_quantity;
	private double customer_stock_weight;
	private Integer lead_time;
	private Integer product_fg_moq;
	private Integer product_fg_mrp;
	private Integer product_fg_landed_price;
	private String product_fg_technical_name;
	private String product_type_short_name;
	private String product_category2_name;
	private String product_category3_name;
	private String product_category4_name;
	private String product_category5_name;
	private String product_fg_packing_name;
	private String product_make_name;
	private String product_material_type_name;
	private String product_material_grade_name;
	private String product_material_shape_name;
	private String product_fg_oem_part_code;
	private String product_fg_our_part_code;
	private String product_fg_drawing_no;
	private Integer product_fg_std_weight;
	private Integer product_fg_std_hours;
	private Integer process_duration;
	private Integer product_sr_std_profit_percent;
	private Integer product_sr_std_discount_percent;
	private String Active;
	private String Deleted;
	private String godown_name;
	private String godown_short_name;
	private String godown_area;
	private String godown_section_name;
	private String godown_section_short_name;
	private String godown_section_area;
	private String godown_section_beans_name;
	private String godown_section_beans_short_name;
	private String godown_section_beans_area;
	private String product_fg_hsn_sac_code;
	private double product_fg_hsn_sac_rate;
	private String company_name;
	private String company_branch_name;
	private Integer product_category1_id;
	private Integer product_type_id;
	private Integer product_fg_stock_unit_id;
	private Integer product_fg_packing_id;
	private Integer product_fg_hsn_sac_code_id;
	private Integer product_fg_category2_id;
	private Integer product_fg_category3_id;
	private Integer product_fg_category4_id;
	private Integer product_fg_category5_id;
	private Integer product_fg_make_id;
	private Integer product_fg_material_type_id;
	private Integer product_fg_material_grade_id;
	private Integer product_material_shape_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String godown_id;
	private String godown_section_id;
	private String godown_section_beans_id;
	private String bom_applicable;
	private boolean is_active = Boolean.TRUE;
	private boolean is_delete = Boolean.FALSE;
	private String created_by;
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;
	private String field_name;
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
