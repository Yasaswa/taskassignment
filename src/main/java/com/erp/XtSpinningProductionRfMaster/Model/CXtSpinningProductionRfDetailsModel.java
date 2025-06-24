package com.erp.XtSpinningProductionRfMaster.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "xt_spinning_production_rf_details")
public class CXtSpinningProductionRfDetailsModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "spinning_production_rf_details_id")
	private int spinning_production_rf_details_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String financial_year;
	private Integer spinning_production_rf_master_id;
	private String spinning_production_rf_code;
	private String spinning_production_date;
	private String shift;
	private Integer production_operator_id;
	private Integer sub_section_id;
	private Integer production_count_id;
	private Integer machine_id;
	private double actual_count;
	private double speed;
	private double tpi;
	private double tm;
	private double efficiency_percent;
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
	private String production_count_desc;
	private double conversion_40s;
	private double total_stopage_time;
	private double total_stopage_spindles;
	private double yarn_construction;
	private double work_spindles;
	private double spindle_utilisation_Per;
	private Integer install_spindles;
	private double ideal_spindle;
	private String yarn_count;
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

}
