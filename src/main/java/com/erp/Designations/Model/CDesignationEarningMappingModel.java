package com.erp.Designations.Model;

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
@Table(name = "hm_designation_earning_mapping")
public class CDesignationEarningMappingModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "designation_earning_mapping_id")
	private int designation_earning_mapping_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String effective_date;
	private Integer designation_id;
	private Integer earning_heads_id;
	private String earning_code;
	private String earning_type;
	private String earning_head_short_name;
	private String calculation_type;
	private double calculation_value;
	private String formula;
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

	public String geteffective_date() {
		return effective_date;
	}

	public void seteffective_date(String effective_date) {
		if (effective_date == null || effective_date.isEmpty()) {
			this.effective_date = null;
		} else {
			this.effective_date = effective_date;
		}
	}


}
