package com.erp.XmSpinningProdCount.Model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "xm_spinning_prod_count_levels")
public class CXmSpinningProdCountLevelsModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "production_count_levels_id")
	private Integer production_count_levels_id;
	private Integer company_id;
	private Integer company_branch_id;
	private Integer production_count_id;
	private String production_count_target_level;
	private double count_speed;
	private double count_tpi;
	private double count_tm;
	private double count_std_ideal_spindle_percent;
	private double count_std_pnumaphil_waste_percent;
	private double count_std_twist_contraction;
	private double count_target_doffing_loss_percent;
	private double count_target_efficiency_loss_percent;
	private double count_target_efficiency_percent;
	private double count_target_machine_utilization;
	private double count_std_gpss;
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
