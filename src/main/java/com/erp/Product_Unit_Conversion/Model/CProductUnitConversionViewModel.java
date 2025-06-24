package com.erp.Product_Unit_Conversion.Model;

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
@Subselect("select * from smv_product_unit_conversion")
public class CProductUnitConversionViewModel {

	@Id
	private int conversion_id;
	private String product_from_unit_name;
	private String product_from_unit_short_name;
	private String product_to_unit_name;
	private String product_to_unit_short_name;
	private Double convsersion_factor;
	private String company_name;
	private String remark;
	//Added By Dipti (ERP DB Testing 1.1)
	private String Active;
	private String Deleted;
	//Added By Dipti (ERP DB Testing 1.1)
	private boolean is_active;
	private boolean is_delete;
	private String created_by;
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private Date deleted_on;
	private String deleted_by;
	private Integer company_id;
	private Integer product_from_unit_id;
	private Integer product_to_unit_id;

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
