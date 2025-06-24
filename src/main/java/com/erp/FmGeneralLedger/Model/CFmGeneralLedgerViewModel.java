package com.erp.FmGeneralLedger.Model;

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
@Subselect("select * from  fmv_general_ledger")
public class CFmGeneralLedgerViewModel {

	@Id
	private int general_ledger_id;
	private String company_name;
	private String company_branch_name;
	private String general_ledger_name;

	//Added By Dipti (ERP DB Testing 1.1)
//	private String Active;
//	private String Deleted;
	//Added By Dipti (ERP DB Testing 1.1)

	private boolean is_active;
	private boolean is_delete;
	private String created_by;
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String deleted_by;
	private Date deleted_on;
	private Integer company_id;
	private Integer company_branch_id;
	private String field_name;
	private Integer field_id;


}
