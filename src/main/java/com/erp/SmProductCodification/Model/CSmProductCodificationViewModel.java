package com.erp.SmProductCodification.Model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Immutable
@Subselect("select * from  smv_product_codification")
public class CSmProductCodificationViewModel {

	@Id
	private int product_codification_id;
	private String product_type_group;
	private String product_type_short_name;
	private String product_type_name;
	private String product_category1_short_name;
	private String product_category1_name;
	private String product_category2_short_name;
	private String product_category2_name;
	private String product_category3_short_name;
	private String product_category3_name;
	private String product_category4_short_name;
	private String product_category4_name;
	private String product_category5_short_name;
	private String product_category5_name;
	private String product_specification_name;
	private String remark;
	private String company_name;
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
	private Integer product_category5_id;
	private Integer product_category4_id;
	private Integer product_category3_id;
	private Integer product_category2_id;
	private Integer product_category1_id;
	private Integer product_type_id;
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
