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
@Subselect("SELECT * FROM xtv_spinning_production_rf_details")
public class CXtSpinningProductionRfDetailsViewModel {

	@Id
	private int spinning_production_rf_details_id;
	private String spinning_production_date;
	private String shift;
	private Integer prod_month;
	private Integer prod_year;
	private String spinning_production_rf_code;
	private String plant_name;
	private String production_sub_section_name;
	private String production_section_name;
	private String spinn_plan_code;
	private String production_supervisor;
	private String production_operator;
	private String production_section_short_name;
	private String production_sub_section_short_name;
	private String machine_name;
	private String machine_short_name;
	private String production_count_name;
	private double actual_count;
	private double speed;
	private double tpi;
	private double tm;
	private double efficiency_percent;
	private double work_spindles;
	private double grams_100;
	private double grams_target;
	private double grams_actual;
	private double kgs_100;
	private double kgs_target;
	private double kgs_actual;
	private double efficiency_target;
	private double prod_loss_with_reason;
	private double prod_loss_with_out_reason;
	private double waste_percent;
	private double production_mixing;
	private double spindle_utilisation_Per;
	private Integer install_spindles;
	private double ideal_spindle;
	private String production_count_desc;
	private double conversion_40s;
	private double total_stopage_time;
	private double total_stopage_spindles;
	private double yarn_construction;
	private String yarn_count;
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
	private String company_address1;
	private String company_address2;
	private String financial_year;
	private Integer company_id;
	private Integer company_branch_id;
	private Integer spinning_production_rf_summary_id;
	private Integer spinning_production_rf_master_id;
	private Integer spinning_production_rf_stopage_id;
	private Integer spinning_production_rf_wastage_id;
	private Integer plant_id;
	private Integer section_id;
	private Integer sub_section_id;
	private Integer production_operator_id;
	private Integer production_count_id;
	private Integer production_supervisor_id;
	private Integer machine_id;
	
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
