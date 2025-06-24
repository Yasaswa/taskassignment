package com.erp.Product_Packing.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Immutable
@Subselect("select * from smv_product_packing_rpt")
public class CProductPackingRptModel_Not_Used {

	@Id
	private String product_packing_id;
	private String product_packing_name;
	private String quantity_per_packing;
	private String weight_per_packing;
	private String product_unit_name;
	private String company_name;
	private String remark;
	private String is_active;
	private String is_delete;
	private String created_by;
	private String created_on;
	private String modified_by;
	private String modified_on;
	private String deleted_by;
	private String deleted_on;
	private String company_id;
	private String product_unit_id;
	private String field_name;
	private String field_id;

}
