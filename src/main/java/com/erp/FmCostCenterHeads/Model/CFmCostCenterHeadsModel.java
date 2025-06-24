package com.erp.FmCostCenterHeads.Model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "fm_cost_center_heads")
public class CFmCostCenterHeadsModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cost_center_heads_id")
	private Integer cost_center_heads_id;
	private int company_id;
	private Integer company_branch_id;
	private Integer cost_center_id;
	private String cost_center_heads_group;
	private String cost_center_heads_name;
	private String cost_center_heads_short_name;
	private boolean is_active = Boolean.TRUE;
	private boolean is_delete = Boolean.FALSE;
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

