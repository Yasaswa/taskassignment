package com.erp.Product_Type.Model;

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
@Subselect("select * from smv_product_type_rpt")
public class CProductTypeRptModel_Not_Used {

	@Id
	private String product_type_id;
	private String product_type_name;
	private String product_type_short_name;
	private String product_type_group;
	private String company_name;
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
