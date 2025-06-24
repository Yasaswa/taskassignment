package com.erp.Product_Material_Grade.Model;

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
@Subselect("select * from smv_product_material_grade")
public class CProductMaterialGradeViewModel {

	@Id
	private int product_material_grade_id;
	private String product_material_grade_name;
	private String product_material_grade_short_name;
	private Integer product_type_id;
	private String product_type_name;
	private String product_type_short_name;
	private String product_type_group;
	private Integer product_material_type_id;
	private String product_material_type_name;
	private String company_name;
	/* Added By Dipti (ERP DB Testing 1.1) */
	private String product_material_type_short_name;
	/* Added By Dipti (ERP DB Testing 1.1) */
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
