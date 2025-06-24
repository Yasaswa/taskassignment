package com.erp.Hsn_Sac.Model;

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
@Subselect("Select * From cmv_hsn_sac_rpt")
public class CHsn_SacRptModel_Not_Used {

//	@Id
//	private String hsn_sac_id;
//	private String company_name;
//	private String hsn_sac_type;
//	private String hsn_sac_code;
//	private String hsn_sac_description;
//	private String hsn_sac_rate;
//	private String is_exampted;
//	private String is_active;
//	private String is_delete;
//	private String created_by;
//	private String created_on;
//	private String modified_by;
//	private String modified_on;
//	private String deleted_by;
//	private String deleted_on;
//	private String company_id;
//	private String field_name;
//	private String field_id;


	//Create By Dipti (Remove company_name) As per sheet ERP DB Testig 1.1.

	@Id
	private String hsn_sac_id;
	private String hsn_sac_type;
	private String hsn_sac_code;
	private String hsn_sac_description;
	private String hsn_sac_rate;
	private String is_exampted;
	private String is_active;
	private String is_delete;
	private String created_by;
	private String created_on;
	private String modified_by;
	private String modified_on;
	private String deleted_by;
	private String deleted_on;
	private String company_id;
	private String field_name;
	private String field_id;

}
