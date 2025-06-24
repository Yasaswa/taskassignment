package com.erp.XtSpinningProductionRfMaster.Model;

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
@Table(name = "xt_spinning_production_rf_stoppage")
public class CXtSpinningProductionRfStoppageModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "spinning_production_rf_stoppage_id")
	private int spinning_production_rf_stoppage_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String financial_year;
	private Integer spinning_production_rf_master_id;
	private String spinning_production_rf_code;
	private String spinning_production_date;
	private Integer prod_month;
	private Integer prod_year;
	private Integer plant_id;
	private String spinn_plan_code;
	private Integer section_id;
	private Integer sub_section_id;
	private String shift;
	private Integer machine_id;
	private Integer production_stoppage_reasons_id;
	private double std_stoppage_loss_per_hour;
	private double stoppage_time;
	private String loss_type;
	private double std_stoppage_loss_kg;
	private double actual_production_loss_kg;
	private double stoppage_production_loss_kg;
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

	public String getSpinning_production_date() {
		return spinning_production_date;
	}

	public void setSpinning_production_date(String spinning_production_date) {
		if (spinning_production_date == null || spinning_production_date.isEmpty()) {
			this.spinning_production_date = null;
		} else {
			this.spinning_production_date = spinning_production_date;

		}

	}

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
