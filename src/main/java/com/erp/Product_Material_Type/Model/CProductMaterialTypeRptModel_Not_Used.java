package com.erp.Product_Material_Type.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import javax.persistence.Entity;
import javax.persistence.Id;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Immutable
public class CProductMaterialTypeRptModel_Not_Used {

	@Id
	private String product_material_type_id;
	private String company_name;
	private String product_material_type_name;
	private String product_material_type_short_name;
	private String product_material_type_density;
	private String product_material_type_rate;
	private String product_type_id;
	private String product_type_name;
	private String product_type_short_name;
	/* Added By Dipti (ERP DB Testing 1.1) */
	private String product_type_group;
	/* Added By Dipti (ERP DB Testing 1.1) */
	private String remark;
	private String is_active;
	private String is_delete;
	private String created_by;
	private String created_on;
	private String modified_by;
	private String modified_on;
	private String deleted_on;
	private String deleted_by;
	private String company_id;
	private String field_name;
	private String field_id;

}
