package com.erp.Godown.Model;

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
@Subselect("select * from cmv_godown_rpt")
public class CGodownRptModel_Not_Used {

	@Id
	private String godown_id;
	private String godown_name;
	private String godown_short_name;
	private String godown_area;
	private String godown_section_count;
	private String product_type_name;
	private String product_type_short_name;
	private String product_type_group;
	private String company_name;
	//Added By Dipti (ERP DB Testing 1.1)
	//private String company_branch_name;
	//Added By Dipti (ERP DB Testing 1.1)
	private String is_active;
	private String is_delete;
	private String created_by;
	private String created_on;
	private String modified_by;
	private String modified_on;
	private String deleted_by;
	private String deleted_on;
	private String company_id;
	//Added By Dipti (ERP DB Testing 1.1)
	//	private String company_branch_id;
	//Added By Dipti (ERP DB Testing 1.1)
	private String product_type_id;
	private String field_name;
	private String field_id;

}
