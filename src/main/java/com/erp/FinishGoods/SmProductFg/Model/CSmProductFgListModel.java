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
@Subselect("seslect * from smv_product_fg_list")
public class CSmProductFgListModel {


	// Added by mohit and use for db 1.1
	@Id
	private Integer product_id;
	private String product_fg_id;
	private String product_fg_name;
	private String product_fg_code;
	private String product_type_name;
	private String product_type_group;
	private String company_name;
	private String company_branch_name;
	private String product_category1_name;
	private String product_category2_name;
	private String product_category3_name;
	private String product_category4_name;
	private String product_category5_name;
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
	private Integer product_type_id;
	private String field_name;
	private Integer field_id;
	private String bom_applicable;


	public boolean isIs_active() {
		return is_active;
	}

	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
	}

//	@Id
//	private Integer product_fg_id;
//	private String company_name;
//	private String company_branch_name;
//	private String product_fg_code;
//	private String product_fg_name;
//	private String product_type_group;
//	private String product_type_name;
//	private String product_category1_name;
//	private String product_category2_name;
//	private String product_category3_name;
//	private String product_category4_name;
//	private String product_category5_name;
//	private boolean is_active;
//	private String field_name;
//	private Integer field_id;
//	private String bom_Applicable;


}
