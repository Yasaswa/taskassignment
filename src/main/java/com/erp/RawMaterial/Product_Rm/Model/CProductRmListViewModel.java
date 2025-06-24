package com.erp.RawMaterial.Product_Rm.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Immutable
@Subselect("Select * From smv_product_rm_list")
public class CProductRmListViewModel {


//	@Id
//	private Integer product_rm_id;
//	private String company_name;
//	private String company_branch_name;
//	private String product_rm_code;
//	private String product_rm_name;
//	private String product_type_group;
//	private String product_type_name;
//	private String product_category1_name;
//	private Boolean is_active = Boolean.TRUE;
//	private String field_name;
//	private Integer field_id;

	/* Added By Dipti (Testing 1.1) */

		@Id
		private Integer product_rm_id;
		private String company_name;
		private String company_branch_name;	
		private String product_rm_name;	
		private String product_rm_code;
		private String product_type_name;
		private String product_category1_name;
		private String product_category2_name;
		private String product_category3_name;
		private String product_category4_name;
		private String product_category5_name;
		private String product_make_name;
		private String Active;
		private String Deleted;
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
		private Integer product_category1_id;
		private Integer product_category2_id;
		private Integer product_category3_id;
		private Integer product_category4_id;
		private Integer product_category5_id;
		private Integer product_make_id;
		private Integer product_type_id;
		private String field_name;
		private Integer field_id;

	/* Added By Dipti (Testing 1.1) */

}
