package com.erp.RawMaterial.Product_Rm_Technical.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Immutable
public class CProductRmTechnicalViewModel {

	@Id
	private Integer product_rm_technical_id;
	private String product_rm_id;
	private String company_name;
	private String company_branch_name;
	private String product_rm_code;
	private String product_rm_name;
	private String product_rm_technical_name;
	private String product_type_group;
	private String product_type_name;
	private String product_type_short_name;
	private String product_category1_name;
	private String product_category2_name;
	private String product_category3_name;
	private String product_category4_name;
	private String product_category5_name;
	private String product_make_name;
	private String product_material_type_name;
	private String product_material_grade_name;
	private String product_material_shape_name;
	private String product_material_colour;
	private double assembly_scrap_percent;
	private String product_rm_short_name;
	private String product_rm_print_name;
	private String product_rm_tech_spect;
	private String product_rm_oem_part_code;
	;
	private String product_rm_our_part_code;
	private String product_rm_item_sr_no;
	private String product_rm_drawing_no;
	private String product_rm_model_no;
	private String product_alternate_rm_name;
	private String remark;
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
	private Integer product_category2_id;
	private Integer product_category3_id;
	private Integer product_category4_id;
	private Integer product_category5_id;
	private Integer product_make_id;
	private Integer product_material_type_id;
	private Integer product_material_grade_id;
	private Integer product_material_shape_id;
	private String product_alternate_rm_id;
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
