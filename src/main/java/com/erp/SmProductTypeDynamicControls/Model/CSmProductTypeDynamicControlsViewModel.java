package com.erp.SmProductTypeDynamicControls.Model;

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
@Subselect("select * from  smv_product_type_dynamic_controls")
public class CSmProductTypeDynamicControlsViewModel {

	@Id
	private int product_type_dynamic_controls_id;
	private String control_master;
	private String control_name;
	private String control_type;
	private String product_type_name;
	private String product_type_group;
	private String product_type_short_name;
	private String company_name;
	private String remark;
	private Integer display_sequence;
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
	private Integer product_type_id;
	private Integer field_id;
	private String field_name;
	private Integer product_category_id;
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
