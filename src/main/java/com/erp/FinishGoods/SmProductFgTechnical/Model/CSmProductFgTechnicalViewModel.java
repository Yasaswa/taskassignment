package com.erp.FinishGoods.SmProductFgTechnical.Model;

import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
@Immutable
@Subselect("select * from  smv_product_fg_technical")
public class CSmProductFgTechnicalViewModel {

	@Id
	private int company_name;
	private String company_branch_name;
	private String product_fg_id;
	private String product_fg_code;
	private String product_fg_name;
	private String product_fg_technical_name;
	private String product_type_group;
	private String product_type_name;
	private String product_type_short_name;
	private String product_category1_name;
	private String product_category2_name;
	private String product_category3_name;
	private String product_category4_name;
	private String product_category5_name;
	private String product_material_type_name;
	private String product_material_grade_name;
	private String product_material_shape_name;
	private String product_material_colour;
	private double assembly_scrap_percent;
	private Double product_gsm;
	private Double product_glm;
	private String product_fg_short_name;
	private String product_fg_print_name;
	private String product_fg_tech_spect;
	private String product_fg_oem_part_code;
	private String product_fg_our_part_code;
	private String product_fg_item_sr_no;
	private String product_fg_drawing_no;
	private String product_fg_model_no;
	private String product_alternate_fg_name;
	private String remark;
	// Added by mohit and use for db 1.1
//	private String Active;
//	private String Deleted;
//	private Integer product_fg_technical_id;	
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
	private Integer product_category2_id;
	private Integer product_category3_id;
	private Integer product_category4_id;
	private Integer product_category5_id;
	private Integer product_material_type_id;
	private Integer product_material_grade_id;
	private Integer product_material_shape_id;
	private String product_alternate_fg_id;
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
