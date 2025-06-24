package com.erp.XmProductionSection.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "xm_production_section")
public class CXmProductionSectionModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "production_section_id")
	private int production_section_id;
	private Integer company_id;
	private Integer company_branch_id;
	private Integer parent_department_id;
	private Integer plant_id;
	private Integer cost_center_id;
	private Integer profit_center_id;
	private String plant_name;
	private String production_section_name;
	private String production_section_short_name;
	private Integer production_section_head_id;
	private Integer production_section_subhead_id;
	private double production_section_std_staff_strength;
	private double production_section_std_worker_strength;
	private boolean is_active = Boolean.TRUE;
	private boolean is_delete = Boolean.FALSE;
	@Column(name = "created_by", updatable = false)
	private String created_by;
	@CreationTimestamp
	@Column(name = "created_on", updatable = false)
	private Date created_on;
	private String modified_by;
	@UpdateTimestamp
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
