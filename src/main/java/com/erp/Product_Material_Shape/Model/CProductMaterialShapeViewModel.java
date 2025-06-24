package com.erp.Product_Material_Shape.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "smv_product_material_shape")
public class CProductMaterialShapeViewModel {

	@Id
	private int product_material_shape_id;
	private String product_material_shape_name;
	private String product_material_shape_short_name;
	private String product_material_shape_formula;
	private Integer product_material_measure_parameter_id1;
	private String product_material_measure_parameter_name1;
	private Integer product_material_measure_parameter_id2;
	private String product_material_measure_parameter_name2;
	private Integer product_material_measure_parameter_id3;
	private String product_material_measure_parameter_name3;
	private Integer product_material_measure_parameter_id4;
	private String product_material_measure_parameter_name4;
	private Integer product_material_measure_parameter_id5;
	private String product_material_measure_parameter_name5;
	private String company_name;
	private Integer product_type_id;
	private String product_type_name;
	private String product_type_short_name;
	private String product_type_group;
	private String remark;
	/* Added By Dipti (ERP DB Testing 1.1) */
	private String Active;
	private String Deleted;
	/* Added By Dipti (ERP DB Testing 1.1) */
	private boolean is_delete = Boolean.FALSE;
	private boolean is_active = Boolean.TRUE;
	private String created_by;
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;
	private Integer company_id;
	private String field_name;
	private Integer field_id;

	public boolean isIs_delete() {
		return is_delete;
	}

	public void setIs_delete(boolean is_delete) {
		this.is_delete = is_delete;
	}

	public boolean isIs_active() {
		return is_active;
	}

	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
	}


}
