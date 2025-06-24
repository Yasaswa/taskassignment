package com.erp.XmProductionSpinningPlanParameter.Model;

import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@Immutable
@Subselect("select * from  xmv_production_spinning_plan_parameter_rpt")
public class CXmProductionSpinningPlanParameterRptModel_Not_Used {

	@Id
	private String company_name;
	private String company_branch_name;
	private String department_name;
	private String sub_department_name;
	private String production_spinning_plan_parameter_name;
	private String formula_plan_parameter_name1;
	private String formula_plan_parameter_name2;
	private String formula_plan_parameter_name3;
	private String formula_plan_parameter_name4;
	private String formula_plan_parameter_name5;
	private String production_plan_calculation_type;
	private String production_plan_formula;
	private String production_plan_value;
	private String production_spinning_plan_parameter_sequance;
	private String is_active;
	private String is_delete;
	private String created_by;
	private String created_on;
	private String modified_by;
	private String modified_on;
	private String deleted_by;
	private String deleted_on;
	private String company_id;
	private String company_branch_id;
	private String production_spinning_plan_parameter_id;
	private String production_department_id;
	private String production_sub_department_id;
	private String formula_plan_parameter_id1;
	private String formula_plan_parameter_id2;
	private String formula_plan_parameter_id3;
	private String formula_plan_parameter_id4;
	private String formula_plan_parameter_id5;
	private String field_name;
	private String field_id;

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getCompany_branch_name() {
		return company_branch_name;
	}

	public void setCompany_branch_name(String company_branch_name) {
		this.company_branch_name = company_branch_name;
	}

	public String getDepartment_name() {
		return department_name;
	}

	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}

	public String getSub_department_name() {
		return sub_department_name;
	}

	public void setSub_department_name(String sub_department_name) {
		this.sub_department_name = sub_department_name;
	}

	public String getProduction_spinning_plan_parameter_name() {
		return production_spinning_plan_parameter_name;
	}

	public void setProduction_spinning_plan_parameter_name(String production_spinning_plan_parameter_name) {
		this.production_spinning_plan_parameter_name = production_spinning_plan_parameter_name;
	}

	public String getFormula_plan_parameter_name1() {
		return formula_plan_parameter_name1;
	}

	public void setFormula_plan_parameter_name1(String formula_plan_parameter_name1) {
		this.formula_plan_parameter_name1 = formula_plan_parameter_name1;
	}

	public String getFormula_plan_parameter_name2() {
		return formula_plan_parameter_name2;
	}

	public void setFormula_plan_parameter_name2(String formula_plan_parameter_name2) {
		this.formula_plan_parameter_name2 = formula_plan_parameter_name2;
	}

	public String getFormula_plan_parameter_name3() {
		return formula_plan_parameter_name3;
	}

	public void setFormula_plan_parameter_name3(String formula_plan_parameter_name3) {
		this.formula_plan_parameter_name3 = formula_plan_parameter_name3;
	}

	public String getFormula_plan_parameter_name4() {
		return formula_plan_parameter_name4;
	}

	public void setFormula_plan_parameter_name4(String formula_plan_parameter_name4) {
		this.formula_plan_parameter_name4 = formula_plan_parameter_name4;
	}

	public String getFormula_plan_parameter_name5() {
		return formula_plan_parameter_name5;
	}

	public void setFormula_plan_parameter_name5(String formula_plan_parameter_name5) {
		this.formula_plan_parameter_name5 = formula_plan_parameter_name5;
	}

	public String getProduction_plan_calculation_type() {
		return production_plan_calculation_type;
	}

	public void setProduction_plan_calculation_type(String production_plan_calculation_type) {
		this.production_plan_calculation_type = production_plan_calculation_type;
	}

	public String getProduction_plan_formula() {
		return production_plan_formula;
	}

	public void setProduction_plan_formula(String production_plan_formula) {
		this.production_plan_formula = production_plan_formula;
	}

	public String getProduction_plan_value() {
		return production_plan_value;
	}

	public void setProduction_plan_value(String production_plan_value) {
		this.production_plan_value = production_plan_value;
	}

	public String getProduction_spinning_plan_parameter_sequance() {
		return production_spinning_plan_parameter_sequance;
	}

	public void setProduction_spinning_plan_parameter_sequance(String production_spinning_plan_parameter_sequance) {
		this.production_spinning_plan_parameter_sequance = production_spinning_plan_parameter_sequance;
	}

	public String getIs_active() {
		return is_active;
	}

	public void setIs_active(String is_active) {
		this.is_active = is_active;
	}

	public String getIs_delete() {
		return is_delete;
	}

	public void setIs_delete(String is_delete) {
		this.is_delete = is_delete;
	}

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public String getCreated_on() {
		return created_on;
	}

	public void setCreated_on(String created_on) {
		this.created_on = created_on;
	}

	public String getModified_by() {
		return modified_by;
	}

	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}

	public String getModified_on() {
		return modified_on;
	}

	public void setModified_on(String modified_on) {
		this.modified_on = modified_on;
	}

	public String getDeleted_by() {
		return deleted_by;
	}

	public void setDeleted_by(String deleted_by) {
		this.deleted_by = deleted_by;
	}

	public String getDeleted_on() {
		return deleted_on;
	}

	public void setDeleted_on(String deleted_on) {
		this.deleted_on = deleted_on;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public String getCompany_branch_id() {
		return company_branch_id;
	}

	public void setCompany_branch_id(String company_branch_id) {
		this.company_branch_id = company_branch_id;
	}

	public String getProduction_spinning_plan_parameter_id() {
		return production_spinning_plan_parameter_id;
	}

	public void setProduction_spinning_plan_parameter_id(String production_spinning_plan_parameter_id) {
		this.production_spinning_plan_parameter_id = production_spinning_plan_parameter_id;
	}

	public String getProduction_department_id() {
		return production_department_id;
	}

	public void setProduction_department_id(String production_department_id) {
		this.production_department_id = production_department_id;
	}

	public String getProduction_sub_department_id() {
		return production_sub_department_id;
	}

	public void setProduction_sub_department_id(String production_sub_department_id) {
		this.production_sub_department_id = production_sub_department_id;
	}

	public String getFormula_plan_parameter_id1() {
		return formula_plan_parameter_id1;
	}

	public void setFormula_plan_parameter_id1(String formula_plan_parameter_id1) {
		this.formula_plan_parameter_id1 = formula_plan_parameter_id1;
	}

	public String getFormula_plan_parameter_id2() {
		return formula_plan_parameter_id2;
	}

	public void setFormula_plan_parameter_id2(String formula_plan_parameter_id2) {
		this.formula_plan_parameter_id2 = formula_plan_parameter_id2;
	}

	public String getFormula_plan_parameter_id3() {
		return formula_plan_parameter_id3;
	}

	public void setFormula_plan_parameter_id3(String formula_plan_parameter_id3) {
		this.formula_plan_parameter_id3 = formula_plan_parameter_id3;
	}

	public String getFormula_plan_parameter_id4() {
		return formula_plan_parameter_id4;
	}

	public void setFormula_plan_parameter_id4(String formula_plan_parameter_id4) {
		this.formula_plan_parameter_id4 = formula_plan_parameter_id4;
	}

	public String getFormula_plan_parameter_id5() {
		return formula_plan_parameter_id5;
	}

	public void setFormula_plan_parameter_id5(String formula_plan_parameter_id5) {
		this.formula_plan_parameter_id5 = formula_plan_parameter_id5;
	}

	public String getField_name() {
		return field_name;
	}

	public void setField_name(String field_name) {
		this.field_name = field_name;
	}

	public String getField_id() {
		return field_id;
	}

	public void setField_id(String field_id) {
		this.field_id = field_id;
	}

	public CXmProductionSpinningPlanParameterRptModel_Not_Used(String company_name, String company_branch_name,
	                                                           String department_name, String sub_department_name, String production_spinning_plan_parameter_name,
	                                                           String formula_plan_parameter_name1, String formula_plan_parameter_name2,
	                                                           String formula_plan_parameter_name3, String formula_plan_parameter_name4,
	                                                           String formula_plan_parameter_name5, String production_plan_calculation_type,
	                                                           String production_plan_formula, String production_plan_value,
	                                                           String production_spinning_plan_parameter_sequance, String is_active, String is_delete, String created_by,
	                                                           String created_on, String modified_by, String modified_on, String deleted_by, String deleted_on,
	                                                           String company_id, String company_branch_id, String production_spinning_plan_parameter_id,
	                                                           String production_department_id, String production_sub_department_id, String formula_plan_parameter_id1,
	                                                           String formula_plan_parameter_id2, String formula_plan_parameter_id3, String formula_plan_parameter_id4,
	                                                           String formula_plan_parameter_id5, String field_name, String field_id) {
		super();
		this.company_name = company_name;
		this.company_branch_name = company_branch_name;
		this.department_name = department_name;
		this.sub_department_name = sub_department_name;
		this.production_spinning_plan_parameter_name = production_spinning_plan_parameter_name;
		this.formula_plan_parameter_name1 = formula_plan_parameter_name1;
		this.formula_plan_parameter_name2 = formula_plan_parameter_name2;
		this.formula_plan_parameter_name3 = formula_plan_parameter_name3;
		this.formula_plan_parameter_name4 = formula_plan_parameter_name4;
		this.formula_plan_parameter_name5 = formula_plan_parameter_name5;
		this.production_plan_calculation_type = production_plan_calculation_type;
		this.production_plan_formula = production_plan_formula;
		this.production_plan_value = production_plan_value;
		this.production_spinning_plan_parameter_sequance = production_spinning_plan_parameter_sequance;
		this.is_active = is_active;
		this.is_delete = is_delete;
		this.created_by = created_by;
		this.created_on = created_on;
		this.modified_by = modified_by;
		this.modified_on = modified_on;
		this.deleted_by = deleted_by;
		this.deleted_on = deleted_on;
		this.company_id = company_id;
		this.company_branch_id = company_branch_id;
		this.production_spinning_plan_parameter_id = production_spinning_plan_parameter_id;
		this.production_department_id = production_department_id;
		this.production_sub_department_id = production_sub_department_id;
		this.formula_plan_parameter_id1 = formula_plan_parameter_id1;
		this.formula_plan_parameter_id2 = formula_plan_parameter_id2;
		this.formula_plan_parameter_id3 = formula_plan_parameter_id3;
		this.formula_plan_parameter_id4 = formula_plan_parameter_id4;
		this.formula_plan_parameter_id5 = formula_plan_parameter_id5;
		this.field_name = field_name;
		this.field_id = field_id;
	}

	public CXmProductionSpinningPlanParameterRptModel_Not_Used() {
		super();
	}

}
