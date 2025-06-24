package com.erp.FmProfitCenter.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Immutable
@Subselect("select * from  fmv_profit_center")
public class CFmProfitCenterViewModel {

	@Id
	private int profit_center_id;
	private String profit_center_name;
	private String profit_center_short_name;
	private String profit_center_group;
	private String company_name;
	//Added By Dipti (ERP DB Testing 1.1)
	private String company_branch_name;
	private String Active;
	private String Deleted;
	//Added By Dipti (ERP DB Testing 1.1)
	private boolean is_active = Boolean.TRUE;
	private boolean is_delete = Boolean.FALSE;
	private String created_by;
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;
	private Integer company_id;
	//Added By Dipti (ERP DB Testing 1.1)
	private Integer company_branch_id;
	//Added By Dipti (ERP DB Testing 1.1)
	private String field_name;
	private Integer field_id;

}
