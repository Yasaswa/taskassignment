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
@Table(name = "xt_spinning_production_rf_summary")
public class CXtSpinningProductionRfSummaryModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "spinning_production_rf_summary_id")
	private int spinning_production_rf_summary_id;
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
	private Integer no_of_machine;
	private double shift_production_loss;
	private double shift_production_100;
	private double shift_production_target;
	private double shift_production_actual;
	private double shift_efficiency_percent;
	private double shift_stopage_time;
	private double shift_wastage_kgs;
	private double shift_spindles_utilization;
	private double shift_production_upto_date_loss;
	private double shift_production_upto_date_100;
	private double shift_production_upto_date_target;
	private double shift_production_upto_date_actual;
	private double shift_efficiency_upto_date_percent;
	private double shift_stopage_upto_date_time;
	private double shift_wastage_upto_date_kgs;
	private double shift_spindles_upto_date_utilization;
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
