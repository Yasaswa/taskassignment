package com.erp.Product_Qa_Parameters.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
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
@Subselect("select * from smv_product_qa_parameters")
public class CProductQaParametersViewModel {

	@Id
	private int product_qa_parameters_id;
	private String company_name;
	private String product_qa_type;
	private String product_qa_parameters_name;
	private String product_qa_parameters_short_name;
	private String product_qa_parameters_desc;
	private double from_range;
	private double to_range;
	private double from_deviation_percent;
	private double to_deviation_percent;
	private Integer product_type_id;
	private String product_type_name;
	private String product_type_short_name;
	private String product_type_group;
	private String remark;
	// Added by mohit and use for db 1.1
	private String Active;
	private String Deleted;
	// Added by mohit and use for db 1.1
	private boolean is_delete = Boolean.FALSE;
	private boolean is_active = Boolean.TRUE;
	private String created_by;
	@CreationTimestamp
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;
	private Integer company_id;
	private String field_name;
	private Integer field_id;

	public boolean isIs_delete() {
		return is_delete;
	}

	public void setIs_delete(boolean is_delete) {
		this.is_delete = is_delete;
	}

	public boolean isIs_active() {
		return is_active;
	}

	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
	}


}
