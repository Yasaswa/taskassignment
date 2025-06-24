package com.erp.CmPlant.Model;

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
@Subselect("select * from  cmv_plant_rpt")
public class CCmPlantRptModel_Not_Used {

	@Id
	private String plant_name;
	private String plant_status;
	private String plant_area;
	private String plant_start_date;
	private String plant_production_capacity;
	private String plant_head_name;
	private String company_name;
	private String company_branch_name;
	private String is_active;
	private String is_delete;
	private String created_by;
	private String created_on;
	private String modified_by;
	private String modified_on;
	private String deleted_by;
	private String deleted_on;
	private String company_id;
	private String plant_head_id;
	private String plant_id;
	private String company_branch_id;
	private Integer field_id;
	private String field_name;

}
