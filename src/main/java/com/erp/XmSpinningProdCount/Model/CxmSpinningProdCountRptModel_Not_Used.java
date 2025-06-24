package com.erp.XmSpinningProdCount.Model;

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
@Subselect("select * from  xmv_spinning_prod_count_rpt")
public class CxmSpinningProdCountRptModel_Not_Used {

	@Id
	private String company_name;
	private String company_branch_name;
	private String production_mixing;
	private String production_count_desc;
	private String production_count_name;
	private String production_actual_count;
	private String count_speed;
	private String count_tpi;
	private String count_tm;
	private String count_40conversion;
	private String count_40conversion_actual_count;
	private String count_actual_grams;
	private String count_40grams_minutes;
	private String count_yarn_construction;
	private String count_conversionfactor_blowroom;
	private String count_conversionfactor_carding;
	private String count_conversionfactor_drawframe;
	private String count_conversionfactor_speedframe;
	private String count_conversionfactor_ringframe;
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
	private String production_count_id;
	private String field_name;
	private String field_id;

}
