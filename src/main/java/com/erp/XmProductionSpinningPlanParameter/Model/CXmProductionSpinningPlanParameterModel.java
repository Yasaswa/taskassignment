package com.erp.XmProductionSpinningPlanParameter.Model;

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
@Table(name = "xm_production_spinning_plan_parameter")
public class CXmProductionSpinningPlanParameterModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "production_spinning_plan_parameter_id")
	private int production_spinning_plan_parameter_id;
	private Integer company_id;
	private Integer company_branch_id;
	private Integer production_department_id;
	private Integer production_sub_department_id;
	private String production_spinning_plan_parameter_name;
	private String production_plan_calculation_type;
	private String production_plan_formula;
	private double production_plan_value;
	private Integer production_spinning_plan_parameter_sequance;
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
	private Integer formula_plan_parameter_id1;
	private Integer formula_plan_parameter_id2;
	private Integer formula_plan_parameter_id3;
	private Integer formula_plan_parameter_id4;
	private Integer formula_plan_parameter_id5;

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
