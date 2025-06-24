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
@Subselect("select * from smv_product_rm_summary")
public class CProductRmSummaryViewModel {

	@Id
	private Integer product_id;
	private String product_type_group;
	private String product_type_name;
	private String product_category1_name;
	private String product_rm_id;
	private String product_rm_code;
	private String product_rm_name;
	private String product_rm_short_name;
	private String product_rm_tech_spect;
	private String product_rm_stock_unit_name;
	private double stock_quantity;
	private double stock_weight;
	private double customer_stock_quantity;
	private double customer_stock_weight;
	private Integer lead_time;
	private double product_rm_moq;
	private double product_rm_mrp;
	private double product_rm_landed_price;
	private String product_rm_technical_name;
	private String product_type_short_name;
	private String product_category2_name;
	private String product_category3_name;
	private String product_category4_name;
	private String product_category5_name;
	private String product_rm_packing_name;
	private String product_make_name;
	private String product_material_type_name;
	private String product_material_grade_name;
	private String product_material_shape_name;
	private String product_rm_oem_part_code;
	private String product_rm_our_part_code;
	private String product_rm_drawing_no;
	private double product_rm_std_weight;
	private String Active;
	private String Deleted;
	private String godown_name;
	private String godown_short_name;
	private double godown_area;
	private String godown_section_name;
	private String godown_section_short_name;
	private double godown_section_area;
	private String godown_section_beans_name;
	private String godown_section_beans_short_name;
	private double godown_section_beans_area;
	private String product_rm_hsn_sac_code;
	private double product_rm_hsn_sac_rate;
	private String company_name;
	private String company_branch_name;
	private Integer product_category1_id;
	private Integer product_type_id;
	private Integer product_rm_stock_unit_id;
	private Integer product_rm_packing_id;
	private Integer product_rm_hsn_sac_code_id;
	private Integer product_category2_id;
	private Integer product_category3_id;
	private Integer product_category4_id;
	private Integer product_category5_id;
	private Integer product_make_id;
	private Integer product_material_type_id;
	private Integer product_material_grade_id;
	private Integer product_material_shape_id;
	private Integer company_id;
	private Integer company_branch_id;
	private Integer godown_id;
	private Integer godown_section_id;
	private Integer godown_section_beans_id;
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
