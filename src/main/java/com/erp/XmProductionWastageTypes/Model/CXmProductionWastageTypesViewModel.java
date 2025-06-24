package com.erp.XmProductionWastageTypes.Model;

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
@Subselect("select * from  xmv_production_wastage_types")
public class CXmProductionWastageTypesViewModel {

	@Id
	private int production_wastage_types_id;
	private String company_name;
	private String company_branch_name;
	private String production_wastage_types_type;
	private String production_wastage_types_name;
	private double std_wastage_percent;
	//Added By Mohit
	private String parent_department;
	private String department_name;
	private String section_name;
	private String sub_section_name;
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
	private Integer production_department_id;
	private Integer production_sub_department_id;
	private Integer section_id;
	private Integer sub_section_id;
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
