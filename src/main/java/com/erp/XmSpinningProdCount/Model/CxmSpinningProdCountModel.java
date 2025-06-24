package com.erp.XmSpinningProdCount.Model;

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
@Table(name = "xm_spinning_prod_count")
public class CxmSpinningProdCountModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "production_count_id")
	private int production_count_id;
	private Integer company_id;
	private Integer company_branch_id;
	private double production_mixing;
	private String production_count_desc;
	private String production_count_name;
	private double production_actual_count;
	private double count_40s_conversion_factor;
	private double count_40s_conversion_electrical;
	private double count_ukg_conversion_factor;
	private double count_tfo_cone_weight;
	private double count_single_cone_weight;
	private double count_weaving_cone_weight;
	private double count_std_doffing_time;
	private double count_std_cop_contents_gms;
	private double count_std_hard_waste_percent;
	private double count_std_doffing_loss_percent;
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
