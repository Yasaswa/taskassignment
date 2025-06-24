package com.erp.SmProductCurrentRate.Model;

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
@Subselect("select * from  smv_product_current_rate")
public class CSmProductCurrentRateViewModel {

	@Id
	private int product_type_id;
	private String product_type_group;
	private String product_type_name;
	private String product_material_name;
	private String product_material_stock_unit_name;
	private String effective_date;
	private double material_rate;
	private String company_name;
	private String company_branch_name;
	private String product_material_id;
	private Integer product_material_stock_unit_id;
	private Integer company_id;
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
