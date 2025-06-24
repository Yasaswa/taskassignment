package com.erp.XmProductionProcess.Model;

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
@Subselect("select * from  xmv_production_process_rpt")
public class CXmProductionProcessRptModel_Not_Used {

	@Id
	private String company_name;
	private String company_branch_name;
	private String production_process_work;
	private String production_process_name;
	private String production_process_short_name;
	//Added By Mohit
	//private String production_parent_process_name;
	//private String production_process_type; 
	private String production_process_std_scrap_percent;
	private String production_process_std_production_hrs;
	private String production_process_sequance;
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
	private String production_process_id;
	private String production_parent_process_id;
	private String field_name;
	private String field_id;
}
