package com.erp.XmYarnPackingTypes.Model;

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
@Immutable
@Data
@Entity
@Subselect(value  = "SELECT * FROM xmv_yarn_packing_types")
public class CXmYarnPackingTypesViewModel {

	@Id
	private Integer yarn_packing_types_id;
	private String yarn_packing_types_name;
	private String yarn_packing_types;
	private double cone_weight;
	private double cone_quantity;
	private double packing_weight;
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
	private Integer yarn_packing_types_property_id;
	private String field_name;
	private Integer field_id;
	private String company_name;
	
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
