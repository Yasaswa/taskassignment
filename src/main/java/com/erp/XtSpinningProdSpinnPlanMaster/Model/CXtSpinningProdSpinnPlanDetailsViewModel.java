package com.erp.XtSpinningProdSpinnPlanMaster.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Immutable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Immutable
@Entity
@Table(name = "xtv_spinning_prod_spinn_plan_details")
public class CXtSpinningProdSpinnPlanDetailsViewModel {

	@Id
	private Integer spinn_plan_transaction_id;
	private String spinn_plan_code;
	private String spinn_plan_month;
	private String spinn_plan_year;
	private String spinn_plan_start_date;
	private String spinn_plan_end_date;
	private String plant_name;
	private String customer_name;
	private String spinn_plan_created_by_name;
	private String spinn_plan_approved_by_name;
	private String spinn_plan_description;
	private String spinn_plan_avg_count;
	private String spinn_plan_status;
	private String production_section_name;
	private String production_sub_section_name;
	private String production_spinning_plan_parameter_name;
	private double production_spinning_plan_parameter_value;
	private double production_spin_plan_input;
	private double production_spinning_plan_parameter_sequance;
	private String production_count_name;
	private String production_count_target_level;
	private Double plan_totals;
	private boolean is_active;
	private boolean is_delete;
	private String created_by;
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;
	private String company_name;
	private String financial_year;
	private Integer spinn_plan_id;
	private Integer customer_id;
	private Integer spinn_plan_created_by_id;
	private Integer spinn_plan_approved_by_id;
	private Integer plant_id;
	private Integer section_id;
	private Integer sub_section_id;
	private Integer production_count_id;
	private Integer production_spinning_plan_parameter_id;
	private Integer company_id;
	private String field_name;
	private Integer field_id;

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
