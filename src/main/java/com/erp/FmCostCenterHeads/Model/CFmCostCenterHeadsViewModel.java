package com.erp.FmCostCenterHeads.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Immutable
@Table(name = "fmv_cost_center_heads")
public class CFmCostCenterHeadsViewModel {
	
	@Id
	private int cost_center_heads_id;
	private String cost_center_heads_group;
	private String cost_center_heads_name;
	private String cost_center_name;
	private String cost_center_heads_short_name;
	private String cost_center_group;
	private String cost_center_short_name;
	private String company_name;
	private String company_branch_name;
	private String Active;
	private String Deleted;
	private boolean is_active = Boolean.TRUE;
	private boolean is_delete = Boolean.FALSE;
	private String created_by;
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;
	private Integer company_id;
	private Integer company_branch_id;
	private Integer cost_center_id;
	
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

	


