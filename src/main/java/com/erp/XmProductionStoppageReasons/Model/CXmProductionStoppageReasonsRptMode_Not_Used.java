package com.erp.XmProductionStoppageReasons.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Immutable
@Subselect("select * from  xmv_production_stoppage_reasons_rpt")
public class CXmProductionStoppageReasonsRptMode_Not_Used {

	@Id
	private String production_stoppage_reasons_id;
	private String company_name;
	private String company_branch_name;
	private String production_stoppage_reasons_type;
	private String parent_department;
	private String department_name;
	private String production_stoppage_reasons_name;
	private String std_stoppage_loss_per_hour;
	private String std_stoppage_minutes;
	private String Active;
	private String Deleted;
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
	private String production_department_id;
	private String production_sub_department_id;
	private String field_name;
	private String field_id;

}
