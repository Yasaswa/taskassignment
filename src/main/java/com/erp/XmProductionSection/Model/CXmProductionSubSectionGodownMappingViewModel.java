package com.erp.XmProductionSection.Model;

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
@Subselect("select * from  xmv_production_sub_section_godown_mapping")
public class CXmProductionSubSectionGodownMappingViewModel {

	@Id
	private int production_sub_section_godown_id;
	private String parent_department;
	private String production_section_name;
	private String production_sub_section_name;
	private String godown_name;
	private String godown_short_name;
	private String plant_name;
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
	private Integer company_id;
	private Integer company_branch_id;
	private Integer production_section_id;
	private Integer parent_department_id;
	private Integer plant_id;
	private Integer production_sub_section_id;
	private Integer godown_id;
	private Integer field_name;
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
