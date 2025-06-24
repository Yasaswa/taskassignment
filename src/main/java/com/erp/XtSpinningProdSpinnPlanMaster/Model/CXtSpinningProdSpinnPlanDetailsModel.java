package com.erp.XtSpinningProdSpinnPlanMaster.Model;

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
@Table(name = "xt_spinning_prod_spinn_plan_details")
public class CXtSpinningProdSpinnPlanDetailsModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "spinn_plan_transaction_id")
	private Integer spinn_plan_transaction_id;
	private Integer company_id;
	private Integer company_branch_id;
	private Integer spinn_plan_id;
	private Integer plant_id;
	private Integer section_id;
	private Integer sub_section_id;
	private Integer production_count_id;
	private String production_count_target_level;
	private String spinn_plan_code;
	private Integer production_spinning_plan_parameter_id;
	private double production_spinning_plan_parameter_value;
	private double production_spin_plan_input;
	private double production_spinning_plan_parameter_sequance;
	private String production_spinning_plan_parameter_name;
	private Double plan_totals;
	private String production_spinning_plan_machine_ids;
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
