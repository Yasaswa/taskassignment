package com.erp.CmMachineDetails.Model;

import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
@Immutable
@Subselect("select * from  cmv_machine_process")
public class CCmMachineProcessViewModel {

	@Id
	private int machine_process_id;
	private String product_process_name;
	private double product_process_std_production_perday;
	private double product_process_std_production_perhrs;
	private String product_process_type;
	private String product_process_short_name;
	private String company_name;
	private String company_branch_name;
	private String remark;
	//Added By Dipti (ERP DB Testing 1.1)
//	private String Active;
//	private String Deleted;
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
	private Integer machine_id;
	private Integer product_process_id;
	//Added By Dipti (ERP DB Testing 1.1)
//	private Integer company_branch_id;
	//Added By Dipti (ERP DB Testing 1.1)
	private Integer product_parent_process_id;

}
