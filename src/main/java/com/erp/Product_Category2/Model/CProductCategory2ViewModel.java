package com.erp.Product_Category2.Model;

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
@Subselect("Slect * From smv_product_category2")
public class CProductCategory2ViewModel {

	@Id
	private int product_category2_id;
	private String product_category2_name;
	private String product_category2_short_name;
	private String product_type_name;
	private String product_type_short_name;
	private String product_type_group;
	private String product_category1_name;
	private String product_category1_short_name;
	private String company_name;
	private String remark;

	//Added By Dipti (ERP DB Testing 1.1)
	private String Active;
	private String Deleted;
	//Added By Dipti (ERP DB Testing 1.1)

	private boolean is_active = Boolean.TRUE;
	private boolean is_delete = Boolean.FALSE;
	private String created_by;
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private Date deleted_on;
	private String deleted_by;
	private String field_name;
	private Integer field_id;
	private Integer company_id;
	private Integer product_type_id;
	private Integer product_category1_id;

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
