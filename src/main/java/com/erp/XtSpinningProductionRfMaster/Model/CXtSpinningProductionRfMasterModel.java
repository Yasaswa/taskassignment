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
@Table(name = "xt_spinning_production_rf_master")
public class CXtSpinningProductionRfMasterModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "spinning_production_rf_master_id")
	private int spinning_production_rf_master_id;
	private Integer company_id;
	private Integer company_branch_id;
	private String financial_year;
	private String spinning_production_rf_code;
	private String spinning_production_date;
	private Integer production_supervisor_id;
	private Integer prod_month;
	private Integer prod_year;
	private Integer plant_id;
	private Integer section_id;
	private Integer sub_section_id;
	private String shift;
	private double target_efficiency;
	private Integer no_of_machine;
	private String spinn_plan_code;
	private Integer spinning_production_rf_stopage_id;
	private Integer spinning_production_rf_wastage_id;
	private Integer spinning_production_rf_details_id;
	private Integer spinning_production_rf_summary_id;
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

	public String getspinning_production_date() {
		return spinning_production_date;
	}

	public void setspinning_production_date(String spinning_production_date) {
		if (spinning_production_date == null || spinning_production_date.isEmpty()) {
			this.spinning_production_date = null;

		} else {
			this.spinning_production_date = spinning_production_date;

		}
	}

	public boolean getis_active() {
		return is_active;
	}

	public void setis_active(boolean is_active) {
		this.is_active = is_active;
	}

	public boolean getis_delete() {
		return is_delete;
	}

	public void setis_delete(boolean is_delete) {
		this.is_delete = is_delete;
	}

}
