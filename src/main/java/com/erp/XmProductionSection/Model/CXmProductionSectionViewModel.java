package com.erp.XmProductionSection.Model;

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
@Subselect("select * from  xmv_production_section")
public class CXmProductionSectionViewModel {

	@Id
	private int production_section_id;
	private String parent_department;
	private String production_section_cost_center;
	private String production_section_profit_center;
	private String production_section_name;
	private String plant_name;
	private String production_section_head;
	private String production_section_subhead;
	private double production_section_std_staff_strength;
	private double production_section_std_worker_strength;
	private String production_section_short_name;
	private String cost_center_short_name;
	private String profit_center_short_name;
	private String Active;
	private String Deleted;
	private String company_name;
	private String company_branch_name;
	private boolean is_active;
	private boolean is_delete;
	private String created_by;
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;
	private int company_id;
	private int company_branch_id;
	private Integer parent_department_id;
	private Integer cost_center_id;
	private Integer profit_center_id;
	private Integer production_section_head_id;
	private Integer production_section_subhead_id;
	private Integer plant_id;
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
