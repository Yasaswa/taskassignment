package com.erp.Product_Packing.Model;

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
@Subselect("Select * From smv_product_packing")
public class CProductPackingViewModel {

	@Id
	private int product_packing_id;
	private String product_packing_name;
	private Double quantity_per_packing;
	private Double weight_per_packing;
	private String product_unit_name;
	private String company_name;
	private String remark;
	private boolean is_active = Boolean.TRUE;
	private boolean is_delete = Boolean.FALSE;
	private String created_by;
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;
	private Integer field_id;
	private String field_name;
	private Integer company_id;
	private Integer product_unit_id;

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
