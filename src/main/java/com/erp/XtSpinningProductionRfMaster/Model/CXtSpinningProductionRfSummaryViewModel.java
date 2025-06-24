package com.erp.XtSpinningProductionRfMaster.Model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Subselect;
import org.springframework.data.annotation.Immutable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Immutable
@Subselect("SELECT * FROM xtv_spinning_production_rf_summary")
public class CXtSpinningProductionRfSummaryViewModel {

	@Id
	private int spinning_production_rf_summary_id;
	private String spinning_production_date;
	private String shift;
	private Integer prod_month;
	private Integer prod_year;
	private String spinning_production_rf_code;
	private String plant_name;
	private String production_sub_section_name;
	private String production_section_name;
	private String spinn_plan_code;
	private int no_of_machine; 
	private String production_supervisor;
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
	private String production_section_short_name;
	private String production_sub_section_short_name;
	private String Active;
	private String Deleted;
	private boolean is_active;
	private boolean is_delete;
	private String created_by;
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;
	private String company_name;
	private String company_branch_name;
	private String financial_year;
	private Integer company_id;
	private Integer company_branch_id;
	private Integer spinning_production_rf_master_id;
	private Integer spinning_production_rf_stopage_id;
	private Integer spinning_production_rf_wastage_id;
	private Integer plant_id;
	private Integer section_id;
	private Integer sub_section_id;
	private Integer production_supervisor_id;
	
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
